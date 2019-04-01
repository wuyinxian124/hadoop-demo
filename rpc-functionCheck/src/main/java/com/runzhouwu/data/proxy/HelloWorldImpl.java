package com.runzhouwu.data.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldImpl implements HelloWorld{

    private final static Logger logger = LoggerFactory.getLogger(HelloWorldImpl.class);

    @Override
    public void ping() {
     logger.info("ping here");
    }
}
