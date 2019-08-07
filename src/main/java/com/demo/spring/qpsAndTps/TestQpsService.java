package com.demo.spring.qpsAndTps;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
public class TestQpsService {
    @Data
    class Request{
        Long id;
        CompletableFuture<Map<Long,Object>> future;
    }

    @PostConstruct
    public void init(){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(()->{
//                取出队列的请求
            int size = queue.size();
            if(size == 0){
                return;
            }
            ArrayList<Request> requests = Lists.newArrayList();
            for (int i = 0; i < size; i++) {
                Request request = queue.poll();
                requests.add(request);
            }
            System.out.println("批量处理的数量："+size);

            //组装一个批量查询
            ArrayList<Long> ids = requests.stream().map(Request::getId).collect(Collectors.toCollection(ArrayList::new));

            List<Map<Long,Object>> responses = Lists.newArrayList();//假设拿到了批量结果集

            HashMap<Long,Map<Long,Object>> responseMap = Maps.newHashMap();

            for (Map<Long,Object> response:responses){
//                Long id = response.get(123);
//                responseMap.put(id,response.get(id));
            }

            for (Request request: requests){
                //通过请求中的唯一参数 去响应
                Map<Long,Object> res = responseMap.get(request.getId());
                //通知准备好了
                request.future.complete(res);

            }
        },0,10, TimeUnit.MILLISECONDS);
    }

    //收集请求
    LinkedBlockingQueue<Request> queue = new LinkedBlockingQueue<>();

    public Map<Long,Object> queryDetailById(Long id) throws ExecutionException, InterruptedException {
        Request request = new Request();
        request.setId(id);
        CompletableFuture<Map<Long,Object>> future = new CompletableFuture<>();
        request.future = future;
        queue.add(request);
        return future.get();
    }
}
