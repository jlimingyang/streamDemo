package com.demo.item.serilizer;

import java.io.*;
import java.util.Arrays;

/**
 * @author Lee
 * @description java原始序列化
 * @date 2020-03-22 17:12
 **/
public class TestSerializer {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        byte[] serializer = serializer();
        System.out.println(Arrays.toString(serializer));
        final Ser deserialize = deserialize(serializer);
        System.out.println(deserialize);
    }

    private static byte[] serializer() throws IOException {
        Ser ser = new Ser();
        ser.setAge(12);
        ser.setName("李明阳");
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream ob = new ObjectOutputStream(bo);
        ob.writeObject(ser);
        return bo.toByteArray();
    }


    public static Ser deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
        ObjectInputStream oi = new ObjectInputStream(bi);
        return (Ser) oi.readObject();
    }
}
