package local.kongyu.netty.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.stereotype.Component;

/**
 * Netty 服务器类，用于启动和配置服务器
 *
 * @author 孔余
 * @since 2024-05-16 15:36:37
 */
@Component
public class MyNettyServer {

    private final int port = 27248; // Netty 服务器监听的端口

    /**
     * 启动 Netty 服务器
     *
     * @throws InterruptedException 如果启动过程中发生中断异常
     */
    public void start() throws InterruptedException {
        // 使用 Epoll 传输，提高 Linux 系统下的性能
//        EventLoopGroup bossGroup = new EpollEventLoopGroup(1); // 单线程用于接受连接
//        EventLoopGroup workerGroup = new EpollEventLoopGroup(); // 默认线程数用于处理连接
        EventLoopGroup bossGroup = new NioEventLoopGroup(1); // 单线程用于接受连接
        EventLoopGroup workerGroup = new NioEventLoopGroup(); // 默认线程数用于处理连接

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
//                    .channel(EpollServerSocketChannel.class) // 使用 Epoll 传输
                    .channel(NioServerSocketChannel.class) // 使用 NIO 传输
                    //.handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new StringDecoder(), new StringEncoder(), new SimpleServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 1024) // 接受连接的队列大小
                    .childOption(ChannelOption.SO_KEEPALIVE, true) // TCP keepalive
                    .childOption(ChannelOption.TCP_NODELAY, true); // 禁用 Nagle 算法

            /*
             * 指定 EventLoop 线程数
             * 默认情况下，线程数是 CPU 核心数的两倍，但可以根据实际情况进行调整
             * 如果线程数设置过高，可能会造成线程竞争，降低性能
             * 如果线程数设置过低，可能会造成 CPU 利用率不高，无法充分利用系统资源
             * 可以通过在构造函数中传入参数来指定线程数，例如 NioEventLoopGroup(4)
             *
             * 单线程用于接受连接，因为接受连接是轻量级的任务，一个线程足以处理
             * 默认线程数用于处理连接，根据系统资源和负载情况进行调整
             */
            ChannelFuture future = serverBootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully(); // 优雅关闭 workerGroup
            bossGroup.shutdownGracefully(); // 优雅关闭 bossGroup
        }
    }
}
