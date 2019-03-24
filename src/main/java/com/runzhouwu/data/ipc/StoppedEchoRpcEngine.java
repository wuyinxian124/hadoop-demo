package com.runzhouwu.data.ipc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.retry.RetryPolicy;
import org.apache.hadoop.ipc.Client;
import org.apache.hadoop.ipc.ProtocolMetaInfoPB;
import org.apache.hadoop.ipc.ProtocolProxy;
import org.apache.hadoop.ipc.RpcEngine;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.security.token.SecretManager;
import org.apache.hadoop.security.token.TokenIdentifier;

import javax.net.SocketFactory;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicBoolean;

public class StoppedEchoRpcEngine implements RpcEngine {

    @Override
    public <T> ProtocolProxy<T> getProxy(Class<T> protocol, long clientVersion,
                                         InetSocketAddress addr, UserGroupInformation ticket, Configuration conf,
                                         SocketFactory factory, int rpcTimeout, RetryPolicy connectionRetryPolicy
    ) throws IOException {
        return getProxy(protocol, clientVersion, addr, ticket, conf, factory,
                rpcTimeout, connectionRetryPolicy, null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> ProtocolProxy<T> getProxy(Class<T> protocol, long clientVersion,
                                         InetSocketAddress addr, UserGroupInformation ticket, Configuration conf,
                                         SocketFactory factory, int rpcTimeout,
                                         RetryPolicy connectionRetryPolicy, AtomicBoolean fallbackToSimpleAuth
    ) throws IOException {
        T proxy = (T) Proxy.newProxyInstance(protocol.getClassLoader(),
                new Class[] { protocol }, new StoppedEchoHandler());
        return new ProtocolProxy<T>(protocol, proxy, false);
    }

    @Override
    public org.apache.hadoop.ipc.RPC.Server getServer(Class<?> protocol,
                                                      Object instance, String bindAddress, int port, int numHandlers,
                                                      int numReaders, int queueSizePerHandler, boolean verbose, Configuration conf,
                                                      SecretManager<? extends TokenIdentifier> secretManager,
                                                      String portRangeConfig) throws IOException {
        return null;
    }

    @Override
    public ProtocolProxy<ProtocolMetaInfoPB> getProtocolMetaInfoProxy(
            Client.ConnectionId connId, Configuration conf, SocketFactory factory)
            throws IOException {
        throw new UnsupportedOperationException("This proxy is not supported");
    }
}