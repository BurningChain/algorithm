package com.netty.rpc.encode;

import com.netty.rpc.util.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.springframework.util.SerializationUtils;

import java.util.List;

public class RpcDecoder extends ByteToMessageDecoder {

    private Class<?> genericClass;

    public RpcDecoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }


    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() < 4) {
            return;
        }

        byteBuf.markReaderIndex();
        int length = byteBuf.readInt();
        if (length < 0) {
            channelHandlerContext.close();
        }
        if (byteBuf.readableBytes() < length) {
            byteBuf.resetReaderIndex();
            return;
        }
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);
        Object obj = SerializationUtil.deSerialize(bytes, genericClass);
        list.add(obj);
    }
}
