package com.runzhouwu.data.ipc;

import org.apache.hadoop.ipc.VersionedProtocol;

public interface EchoProtocol extends VersionedProtocol  {

    public static final long versionID = 1L;

     String echo();
     String echo(String say);
}
