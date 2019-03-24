package com.runzhouwu.data;

import com.runzhouwu.data.ipc.EchoImpl;
import com.runzhouwu.data.ipc.EchoProtocol;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.RpcEngine;
import org.apache.hadoop.ipc.Server;
import org.apache.hadoop.net.NetUtils;
import org.apache.hadoop.security.UserGroupInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Hello world!
 *
 */
public class App 
{

    private final static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main( String[] args ) throws IOException {

        Configuration conf;
        conf = new Configuration();
//        conf.setClass("rpc.engine." + StoppedProtocol.class.getName(),
//                StoppedRpcEngine.class, RpcEngine.class);
        UserGroupInformation.setConfiguration(conf);


        final String ADDRESS = "0.0.0.0";
        logger.info("hello word");
        System.out.println( "Hello World!" );

        logger.info("start ------");
        // create a server with two handlers
        Server server = new RPC.Builder(conf).setProtocol(EchoProtocol.class)
                .setInstance(new EchoImpl()).setBindAddress(ADDRESS).setPort(0)
                .setNumHandlers(2).setVerbose(false).build();

        EchoProtocol proxy = null;

        try {
            server.start();

            InetSocketAddress addr = NetUtils.getConnectAddress(server);

            // create a client
            proxy = RPC.getProxy(EchoProtocol.class, EchoProtocol.versionID, addr, conf);

            logger.info(proxy.echo());
            logger.info(proxy.echo("xilo"));
        }catch (Exception e){
            logger.error("",e);
        }finally {
            server.stop();
            if (proxy != null) {
                RPC.stopProxy(proxy);
            }
            System.out.println("Down slow rpc testing");
        }
    }
}
