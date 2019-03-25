package com.runzhouwu.data.ipc;

import org.apache.hadoop.ipc.ProtocolSignature;

import java.io.IOException;

public class EchoImpl implements EchoProtocol {
    @Override
    public long getProtocolVersion(String protocol, long clientVersion) throws IOException {
        return 0;
    }

    @Override
    public ProtocolSignature getProtocolSignature(String protocol, long clientVersion, int clientMethodsHash) throws IOException {
        return null;
    }

    @Override
    public String echo() {
        return "nothing";
    }

    @Override
    public String echo(String say) {
        return "echo " + say;
    }
}
