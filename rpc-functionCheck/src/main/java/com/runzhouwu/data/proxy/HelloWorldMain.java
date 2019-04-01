package com.runzhouwu.data.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class HelloWorldMain {

    private final static Logger logger = LoggerFactory.getLogger(HelloWorldMain.class);

    public static void main(String[] args) {
        HelloWorld helloWorld=new HelloWorldImpl();
        InvocationHandler handler=new HelloWorldHandler(helloWorld);

        //创建动态代理对象
        HelloWorld proxy=(HelloWorld) Proxy.newProxyInstance(
                helloWorld.getClass().getClassLoader(),
                helloWorld.getClass().getInterfaces(),
                handler);
        logger.info("here");
        proxy.ping();
    }

}
