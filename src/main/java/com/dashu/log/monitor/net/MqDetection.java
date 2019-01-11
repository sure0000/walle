package com.dashu.log.monitor.net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.*;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2019/1/11 上午10:30
 **/
public class MqDetection {
    private static final Logger logger = LoggerFactory.getLogger(MqDetection.class);
    private Socket socket;


    public boolean isConnect(String ip,Integer port) throws SocketException {

        //创建一个Socket实例：构造函数向指定的远程主机和端口建立一个TCP连接
        socket = new Socket();
        socket.setReceiveBufferSize(8192);
        socket.setSoTimeout(1000);// socket.setSoTimeout(2000);
        SocketAddress address = new InetSocketAddress(ip, port);

        try {
            socket.connect(address,1000);//判断ip、端口是否可连接
            logger.info("mq is connected success!");
            return true;
        } catch (IOException e) {
            logger.error("mq is connected fail"+e.toString());
            return false;
        }

    }


}
