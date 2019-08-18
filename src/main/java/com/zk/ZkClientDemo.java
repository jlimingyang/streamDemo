package com.zk;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.ZkSerializer;

public class ZkClientDemo {

    public static void main(String[] args) {
        ZkClient client = new ZkClient("127.0.0.1:2181");
        client.setZkSerializer(new MyZkSerializer());
        client.subscribeDataChanges("/lmy", new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("数据节点改变：");
                System.out.println(s);
                System.out.println(o);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("数据节点删除：");
                System.out.println(s);
            }
        });
    }

}
