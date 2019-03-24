package com.runzhouwu.data.ipc;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class StoppedEchoHandler implements InvocationHandler, Closeable {

    private int closeCalled = 0;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        return null;
    }

    @Override
    public void close() throws IOException {
        closeCalled++;
    }

    public int getCloseCalled() {
        return closeCalled;
    }

}