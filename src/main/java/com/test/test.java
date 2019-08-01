package com.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.sun.xml.internal.ws.api.model.ExceptionType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class test {
    /**
     * @author Lee
     * @description 获取各时间端   处理特殊时间段 处理隔天时间 处理下单时间
     * @date 2019/7/4 13:53
     **/
    private final static String HIGHT_TIME_TEXT = "此段时间为商家高峰时间段，可能出现排队提货的情况";
    private final static String NO_TIME_TEXT = "该时段为商家非接单时段，暂时不能接单";
    private final static String RES = "{\n" +
            "    \"isEnable\": 1,\n" +
            "    \"depotCode\": \"CQ00231000098\",\n" +
            "    \"details\": [\n" +
            "      {\n" +
            "        \"settingType\": 4,\n" +
            "        \"beginTime\": \"2019-06-25 00:00\",\n" +
            "        \"endTime\": \"2019-06-25 23:59\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"settingType\": 1,\n" +
            "        \"beginTime\": \"07:51\",\n" +
            "        \"endTime\": \"08:48\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"settingType\": 2,\n" +
            "        \"beginTime\": \"14:51\",\n" +
            "        \"endTime\": \"17:51\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"settingType\": 3,\n" +
            "        \"beginTime\": \"18:48\",\n" +
            "        \"endTime\": \"00:51\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"settingType\": 5,\n" +
            "        \"beginTime\": \"08:00\",\n" +
            "        \"endTime\": \"22:00\"\n" +
            "      }\n" +
            "    ]\n" +
            "  }";

    public Map<String, ReceiveTimeSettingRespVo> getReceiveTime02() {
//        log.debug("接收到的参数:{}", JSON.toJSONString(receiveTimeReqVo));
        Map<String, ReceiveTimeSettingRespVo> receiveTimeMap = Maps.newHashMap();
//        JsonResult<ReceiveTimeSettingVo> jsonResult = bossApiServiceFeignClient.getReceiveTime(receiveTimeReqVo.getDepotCode());
//        log.debug("获取到的时间端结果:{}", JSON.toJSONString(jsonResult));
        ReceiveTimeSettingVo receiveTimeSettingVo = JSON.parseObject(RES, ReceiveTimeSettingVo.class);
        Assert.notNull(receiveTimeSettingVo, "未获取到时间段设置");
        //不能通过不等于4判断  可能会有营业时间
        List<Integer> receiveTypes = Arrays.asList(1, 2, 3);
        Map<Integer, ReceiveTimeSettingDetailVo> receiveTimeSettingDetailVosMap = receiveTimeSettingVo.getDetails().stream()
                .filter(s -> receiveTypes.contains(s.getSettingType()))
                .collect(Collectors.toMap(ReceiveTimeSettingDetailVo::getSettingType, a -> a));
        Assert.notNull(receiveTimeSettingDetailVosMap, "未获取到早晚时间段设置");
        //如果晚上设置的时间隔天了
        ReceiveTimeSettingDetailVo receiveTimeSettingDetailVo = receiveTimeSettingDetailVosMap.get(3);
        if (Objects.nonNull(receiveTimeSettingDetailVo)) {
            LocalTime end = getByTimeStr(receiveTimeSettingDetailVo.getEndTime());
            if (end.isBefore(LocalTime.NOON)) {
                receiveTimeSettingDetailVo.setEndTime(getTimeFormat(LocalTime.MAX));
                receiveTimeSettingDetailVosMap.put(3, receiveTimeSettingDetailVo);
                ReceiveTimeSettingDetailVo receiveTimeSettingDetailVo1 = new ReceiveTimeSettingDetailVo();
                receiveTimeSettingDetailVo1.setSettingType(1);
                receiveTimeSettingDetailVo1.setBeginTime(getTimeFormat(LocalTime.MIN));
                receiveTimeSettingDetailVo1.setEndTime(getTimeFormat(end));
                receiveTimeSettingDetailVosMap.put(4, receiveTimeSettingDetailVo1);
            }
        }

        //当前日期
        LocalDateTime currentDateTime = LocalDateTime.now();
        //处理最近三天的时间
        for (int i = 0; i < 3; i++) {
            LocalDateTime dateTime = currentDateTime.plusDays(i);
            switch (i) {
                case 0:
                    ReceiveTimeSettingRespVo respVo = new ReceiveTimeSettingRespVo(receiveTimeSettingVo.getIsEnable(), receiveTimeSettingVo.getDepotCode());
                    List<ReceiveTimeSettingDetailVo> specialTimes0 = this.getSpecialTimes(dateTime, receiveTimeSettingVo);
                    respVo.setDetails(this.handleTime(dateTime, receiveTimeSettingDetailVosMap, specialTimes0));
                    receiveTimeMap.put("today", respVo);
                    break;
                case 1:
                    ReceiveTimeSettingRespVo respVo1 = new ReceiveTimeSettingRespVo(receiveTimeSettingVo.getIsEnable(), receiveTimeSettingVo.getDepotCode());
                    List<ReceiveTimeSettingDetailVo> specialTimes1 = this.getSpecialTimes(dateTime, receiveTimeSettingVo);
                    respVo1.setDetails(this.handleTime(dateTime, receiveTimeSettingDetailVosMap, specialTimes1));
                    receiveTimeMap.put("tomorrow", respVo1);
                    break;
                case 2:
                    ReceiveTimeSettingRespVo respVo2 = new ReceiveTimeSettingRespVo(receiveTimeSettingVo.getIsEnable(), receiveTimeSettingVo.getDepotCode());
                    List<ReceiveTimeSettingDetailVo> specialTimes2 = this.getSpecialTimes(dateTime, receiveTimeSettingVo);
                    respVo2.setDetails(this.handleTime(dateTime, receiveTimeSettingDetailVosMap, specialTimes2));
                    receiveTimeMap.put("lastday", respVo2);
                    break;
                default:
                    break;
            }
        }
        return MapUtils.isNotEmpty(receiveTimeMap) ? receiveTimeMap : null;
    }

    /**
     * @author Lee
     * @description 获取自提时间之后的特殊时间段 与当前时间相同的时间段 这里没有支持隔日特殊时间段设置
     * @date 2019/7/4 18:07
     **/

    private List<ReceiveTimeSettingDetailVo> getSpecialTimes(LocalDateTime handleDateTime, ReceiveTimeSettingVo receiveTimeSettingVo) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<ReceiveTimeSettingDetailVo> res = Lists.newArrayList();
        if (Integer.valueOf(1).equals(receiveTimeSettingVo.getIsEnable())) {
            res = Optional.ofNullable(receiveTimeSettingVo.getDetails())
                    .orElse(Lists.newArrayList()).stream()
                    .filter(s -> Integer.valueOf(4).equals(s.getSettingType())).filter(s -> {
                        LocalDateTime localDateTime = this.getByDateTimeStr(s.getEndTime());
                        return localDateTime.toLocalDate().isEqual(handleDateTime.toLocalDate())
                                && localDateTime.isAfter(currentDateTime);
                    })
                    .peek(s -> {
                        if (this.getByDateTimeStr(s.getBeginTime()).isBefore(currentDateTime)) {
                            //如果特殊时间开始时间在当前时间之前 则从当前时间开始截取
                            s.setBeginTime(currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                        }
                    })
                    .collect(Collectors.toList());
        }
        return res;
    }

    /**
     * @author Lee
     * @description 处理时间
     * @date 2019/7/4 17:26
     **/

    private List<ReceiveTimeSettingDetailVo> handleTime(LocalDateTime handleDateTime, Map<Integer, ReceiveTimeSettingDetailVo> receiveTimeSettingDetailVosMap, List<ReceiveTimeSettingDetailVo> specialTimes) {
        List<ReceiveTimeSettingDetailVo> recevieTimes = Lists.newArrayList();
        List<ReceiveTimeSettingDetailVo> recevieTimeList = Lists.newArrayList();
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDate currentDate = currentDateTime.toLocalDate();
        LocalTime currentTime = currentDateTime.toLocalTime().plusMinutes(30);
        LocalDate handleDate = handleDateTime.toLocalDate();
        //处理时间段
        for (Integer key : receiveTimeSettingDetailVosMap.keySet()) {
            ReceiveTimeSettingDetailVo receiveTimeSettingDetailVo = new ReceiveTimeSettingDetailVo();
            BeanUtils.copyProperties(receiveTimeSettingDetailVosMap.get(key),receiveTimeSettingDetailVo);
            if (Objects.nonNull(receiveTimeSettingDetailVo)) {
                log.debug("处理时间段:{}-{}", receiveTimeSettingDetailVo.getBeginTime(), receiveTimeSettingDetailVo.getEndTime());
                //如果没有设置 则不管高峰时间什么的
                if (StringUtils.isNotEmpty(receiveTimeSettingDetailVo.getBeginTime()) && StringUtils.isNotEmpty(receiveTimeSettingDetailVo.getEndTime())) {
                    LocalTime begin = this.getByTimeStr(receiveTimeSettingDetailVo.getBeginTime());
                    LocalTime end = this.getByTimeStr(receiveTimeSettingDetailVo.getEndTime());
                    LocalDateTime beginDateTime = LocalDateTime.of(handleDate, begin);
                    LocalDateTime endDateTime = LocalDateTime.of(handleDate, end);
                    if (CollectionUtils.isNotEmpty(specialTimes)) {
                        Boolean specialFlag = true;
                        for (int i = 0; i < specialTimes.size(); i++) {
                            ReceiveTimeSettingDetailVo s = specialTimes.get(i);
                            LocalDateTime sBegin = this.getByDateTimeStr(s.getBeginTime());
                            LocalDateTime sEnd = this.getByDateTimeStr(s.getEndTime());
                            String flag = handleSpecialTime(beginDateTime, endDateTime, sBegin, sEnd);
                            LocalTime speEndTime = this.getByDateTimeStr(s.getEndTime()).toLocalTime();
                            LocalTime speBeginTime = this.getByDateTimeStr(s.getBeginTime()).toLocalTime();
                            LocalTime endTime = this.getByTimeStr(receiveTimeSettingDetailVo.getEndTime());
                            LocalTime beginTime = this.getByTimeStr(receiveTimeSettingDetailVo.getBeginTime());
                            String speBeginTimeStr = this.getTimeFormat(speBeginTime);
                            String speEndTimeStr = this.getTimeFormat(speEndTime);
                            switch (flag) {
                                case "b":
                                case "c":
                                    recevieTimes.add(ReceiveTimeSettingDetailVo.builder()
                                            .beginTime(speBeginTimeStr)
                                            .endTime(speEndTimeStr)
                                            .settingType(4)
                                            .text(NO_TIME_TEXT).build());
                                    if ("b".equals(flag)
                                            && this.checkTimeLocalTime(speEndTime, endTime)) {
                                        receiveTimeSettingDetailVo.setBeginTime(speEndTimeStr);
                                        recevieTimes.add(receiveTimeSettingDetailVo);
                                    }
                                    specialFlag = false;
                                    break;
                                case "d":
                                    recevieTimes.add(ReceiveTimeSettingDetailVo.builder()
                                            .beginTime(speBeginTimeStr)
                                            .endTime(speEndTimeStr)
                                            .settingType(4)
                                            .text(NO_TIME_TEXT).build());
                                    if (checkTimeLocalTime(beginTime, speBeginTime)) {
                                        recevieTimes.add(ReceiveTimeSettingDetailVo.builder()
                                                .beginTime(receiveTimeSettingDetailVo.getBeginTime())
                                                .endTime(speBeginTimeStr)
                                                .settingType(receiveTimeSettingDetailVo.getSettingType()).build());
                                    }
                                    if (checkTimeLocalTime(speEndTime, endTime)) {
                                        recevieTimes.add(ReceiveTimeSettingDetailVo.builder()
                                                .beginTime(speEndTimeStr)
                                                .endTime(receiveTimeSettingDetailVo.getEndTime())
                                                .settingType(receiveTimeSettingDetailVo.getSettingType()).build());
                                    }
                                    specialFlag = false;
                                    break;
                                case "e":
                                    if (checkTimeLocalTime(beginTime, speBeginTime)) {
                                        recevieTimes.add(ReceiveTimeSettingDetailVo.builder()
                                                .beginTime(receiveTimeSettingDetailVo.getBeginTime())
                                                .endTime(speBeginTimeStr)
                                                .settingType(receiveTimeSettingDetailVo.getSettingType()).build());
                                    }
                                        recevieTimes.add(ReceiveTimeSettingDetailVo.builder()
                                                .beginTime(speBeginTimeStr)
                                                .endTime(speEndTimeStr)
                                                .settingType(4).text(NO_TIME_TEXT).build());
                                    specialFlag = false;
                                    break;
                                default:
                                    break;
                            }
                        }
                        if (specialFlag) {
                            recevieTimes.add(receiveTimeSettingDetailVo);
                        }
                    } else {
                        recevieTimes.add(receiveTimeSettingDetailVo);
                    }
                }
            }
        }
        //处理高峰时间
        ReceiveTimeSettingDetailVo temp = null;
        List<ReceiveTimeSettingDetailVo> collect = recevieTimes.stream().distinct()
                .sorted(Comparator.comparing(ReceiveTimeSettingDetailVo::getBeginTime))
                .collect(Collectors.toList());
        for (ReceiveTimeSettingDetailVo r : collect) {
            if (Objects.isNull(temp)) {
                recevieTimeList.add(r);
                if (this.getByTimeStr(r.getEndTime()).isAfter(LocalTime.of(6, 0))
                        && Arrays.asList(1, 2).contains(r.getSettingType())) {
                    temp = r;
                }
            } else {
                if (this.getByTimeStr(temp.getEndTime()).isBefore(this.getByTimeStr(r.getBeginTime()))) {
                    log.debug("before:{}=========after:{}",temp.getEndTime(),r.getBeginTime());
                    recevieTimeList.add(ReceiveTimeSettingDetailVo.builder()
                            .beginTime(temp.getEndTime())
                            .endTime(r.getBeginTime())
                            .settingType(6).text(HIGHT_TIME_TEXT).build());
                }
                recevieTimeList.add(r);
                temp = r;
            }
        }
        for (int i = 0; i < recevieTimeList.size(); i++) {
            //如果是当天 获取当前时间节点之后的时间
            ReceiveTimeSettingDetailVo receiveTimeSettingDetailVo = recevieTimeList.get(i);
            LocalTime begin = this.getByTimeStr(receiveTimeSettingDetailVo.getBeginTime());
            LocalTime end = this.getByTimeStr(receiveTimeSettingDetailVo.getEndTime());
            if (currentDate.isEqual(handleDate)) {
                if (currentTime.isBefore(begin)) {
                    continue;
                }
                if (currentTime.isAfter(begin) && currentTime.isBefore(end)) {
                    //判断是否在三十分钟外
                    if (this.checkTimeLocalTime(currentTime, end)) {
                        //覆盖开始时间
                        receiveTimeSettingDetailVo.setBeginTime(getTimeFormat(currentTime));
                    } else {
                        recevieTimeList.remove(i);
                        i--;
                    }
                }
                if (currentTime.isAfter(end)) {
                    recevieTimeList.remove(i);
                    i--;
                }
            }
        }
        return recevieTimeList.stream()
                .sorted(Comparator.comparing(ReceiveTimeSettingDetailVo::getBeginTime))
                .collect(Collectors.toList());
    }

    private String getTimeFormat(LocalTime beginTime) {
        return beginTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    /**
     * @author Lee
     * @description 时间段判断  小于30分钟的不展示
     * @date 2019/7/4 16:18
     **/

    private Boolean checkTimeStr(String startTime, String endTime) {
        return this.checkTimeLocalTime(this.getByTimeStr(startTime), this.getByTimeStr(endTime));
    }

    private Boolean checkTimeLocalTime(LocalTime startTime, LocalTime endTime) {
        return startTime.plusMinutes(30).isBefore(endTime);
    }

    private LocalDateTime getByDateTimeStr(String timeStr) {
//        AssertUtils.hasText(timeStr, ExceptionType.of("5021", "特殊时间段设置错误 error:" + timeStr));
        return LocalDateTime.parse(timeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    private LocalTime getByTimeStr(String timeStr) {
        return LocalTime.parse(timeStr, DateTimeFormatter.ofPattern("HH:mm"));
    }

    /**
     * @author Lee
     * @description 如果处于特殊时间段:
     * * a 没有包含在特殊时间段
     * * 特殊时间段开始时间在自提开始时间之前
     * *      b:特殊时间段结束时间在自提结束时间之前,在开始时间之后
     * *      c:特殊时间结束时间在自提结束时间之后或相等
     * * 特殊时间段开始时间在自提开始时间内:
     * *      d:特殊时间段结束时间在自提结束时间之前
     * *      e:特殊时间段结束时间在自提结束时间之后或相等
     * @date 2019/7/5 15:30
     **/
    private String handleSpecialTime(LocalDateTime start, LocalDateTime end, LocalDateTime specialStart, LocalDateTime specialEnd) {
        String flag = "a";
        if (specialStart.isBefore(start)) {
            if (specialEnd.isBefore(end) && specialEnd.isAfter(start)) {
                flag = "b";
            }
            if (specialEnd.isAfter(end) || specialEnd.isEqual(end)) {
                flag = "c";
            }
        } else if (specialStart.isBefore(end)) {
            if (specialEnd.isBefore(end)) {
                flag = "d";
            } else {
                flag = "e";
            }
        }
//        log.debug("特殊时间段flag,", flag);
        return flag;
    }

    @Test
    public void testAA() {
        Map<String, ReceiveTimeSettingRespVo> receiveTime02 = getReceiveTime02();
        System.out.println(new Gson().toJson(receiveTime02));
    }

    @Test
    public void testBB(){
        ReceiveTimeSettingVo receiveTimeSettingVo = JSON.parseObject(RES, ReceiveTimeSettingVo.class);
        Assert.notNull(receiveTimeSettingVo, "未获取到时间段设置");
        //不能通过不等于4判断  可能会有营业时间
        List<Integer> receiveTypes = Arrays.asList(1, 2, 3);
        Map<Integer, ReceiveTimeSettingDetailVo> receiveTimeSettingDetailVosMap = receiveTimeSettingVo.getDetails().stream()
                .filter(s -> receiveTypes.contains(s.getSettingType()))
                .collect(Collectors.toMap(ReceiveTimeSettingDetailVo::getSettingType, a -> a));
        Assert.notNull(receiveTimeSettingDetailVosMap, "未获取到早晚时间段设置");
        //如果晚上设置的时间隔天了
        ReceiveTimeSettingDetailVo receiveTimeSettingDetailVo = receiveTimeSettingDetailVosMap.get(3);
        if (Objects.nonNull(receiveTimeSettingDetailVo)) {
            LocalTime end = getByTimeStr(receiveTimeSettingDetailVo.getEndTime());
            if (end.isBefore(LocalTime.NOON)) {
                receiveTimeSettingDetailVo.setEndTime(getTimeFormat(LocalTime.MAX));
                receiveTimeSettingDetailVosMap.put(3, receiveTimeSettingDetailVo);
                ReceiveTimeSettingDetailVo receiveTimeSettingDetailVo1 = new ReceiveTimeSettingDetailVo();
                receiveTimeSettingDetailVo1.setSettingType(1);
                receiveTimeSettingDetailVo1.setBeginTime(getTimeFormat(LocalTime.MIN));
                receiveTimeSettingDetailVo1.setEndTime(getTimeFormat(end));
                receiveTimeSettingDetailVosMap.put(4, receiveTimeSettingDetailVo1);
            }
        }

        //当前日期
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<ReceiveTimeSettingDetailVo> specialTimes0 = this.getSpecialTimes(currentDateTime, receiveTimeSettingVo);
        List<ReceiveTimeSettingDetailVo> receiveTimeSettingDetailVos = this.handleTime(currentDateTime, receiveTimeSettingDetailVosMap, specialTimes0);
        System.out.println(JSON.toJSONString(receiveTimeSettingDetailVos));
        //分段

    }

    /**
     * @author Lee
     * 当前时间
     *  大于00:
     *      A:  <30:
     *          a: <15 =  00
     *          b: >= 15 = 30
     *      B:  >30
     *          a: <45 = 30
     *          b: >=45 = t+1 00
     * @description
     * @date 2019/7/18 18:43
     **/


    @Test
    public void testCC(){
        LocalTime localTime = LocalTime.of(1,55);
        LocalTime hourTime = LocalTime.of(localTime.getHour(),00);
        LocalTime halfHourTime = LocalTime.of(localTime.getHour(),30);
        LocalTime lastHalfHourTime = LocalTime.of(localTime.getHour(),15);
        LocalTime nextHourTime = LocalTime.of(localTime.getHour()+1,00);
        LocalTime nextHalfHourTime = LocalTime.of(localTime.getHour(),45);
        if(localTime.isBefore(halfHourTime)){
            localTime = localTime.isBefore(lastHalfHourTime)?hourTime:halfHourTime;
        }
        if(localTime.isAfter(halfHourTime)){
            localTime = localTime.isBefore(nextHalfHourTime)?halfHourTime:nextHourTime;
        }
        System.out.println(localTime.format(DateTimeFormatter.ofPattern("HH:mm")));
    }

    @Test
    public void testCCC(){
        LocalTime a = LocalTime.of(1,00);
        LocalTime b = LocalTime.of(10,00);
        new Thread(() -> {
            log.debug("测试");
        }).start();
        int c = 1;
        while (a.isBefore(b)){
            a = a.plusHours(1);
            System.out.println(c++);
        }

    }

}
