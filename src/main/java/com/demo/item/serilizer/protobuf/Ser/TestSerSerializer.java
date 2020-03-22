package com.demo.item.serilizer.protobuf.Ser;

import com.demo.item.serilizer.Ser;
import com.google.common.collect.Lists;
import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.poi.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestSerSerializer {

    public static void main(String[] args) throws InvalidProtocolBufferException {
        byte[] serializer = serializer();
        decliazer(serializer);

    }

    private static byte[] serializer(){
        Ser ser = new Ser();
        ser.setAge(12);
        ser.setName("李四");
        ser.getDemos().add(new Ser.SerDemo("李四",123));
        System.out.println(ser);
        Ser ser1 = new Ser();
        ser1.setAge(14);
        ser1.setName("李四1");
        ser1.getDemos().add(new Ser.SerDemo("李四22",122223));
        List<Ser> sers = Lists.newArrayList();
        sers.add(ser);
        sers.add(ser1);
        byte[] serialize = ProtostuffUtil.serialize(sers);
        System.out.println(Arrays.toString(serialize));
        System.out.println("-------------");
        return serialize;
    }


    private static void decliazer(byte[] b) throws InvalidProtocolBufferException {
        List<Ser> deserialize = ProtostuffUtil.deserialize(b, ArrayList.class);
        System.out.println(deserialize);
    }
}
