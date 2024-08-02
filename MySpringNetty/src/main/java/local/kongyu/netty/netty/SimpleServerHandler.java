package local.kongyu.netty.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 简单的 Netty 服务器处理器，用于处理客户端的请求
 *
 * @author 孔余
 * @since 2024-05-16 15:37:26
 */
public class SimpleServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 当接收到客户端消息时调用
     *
     * @param ctx ChannelHandlerContext 对象，用于和客户端进行通信
     * @param msg 接收到的消息对象
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        String message = (String) msg;
        System.out.println("Received message: " + message);
        ctx.writeAndFlush("Hello from Netty!\n");  // 确保换行字符正确响应
    }

    /**
     * 处理异常情况
     *
     * @param ctx    ChannelHandlerContext 对象，用于和客户端进行通信
     * @param cause  异常原因
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
