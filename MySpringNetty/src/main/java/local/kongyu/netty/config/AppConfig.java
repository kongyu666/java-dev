package local.kongyu.netty.config;

import local.kongyu.netty.netty.MyNettyClient;
import local.kongyu.netty.netty.MyNettyServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MyNettyClient nettyClient() {
        return new MyNettyClient();
    }

    /**
     * 创建并返回一个 CommandLineRunner bean，用于在应用程序启动时执行指定的操作
     *
     * @param myNettyServer MyNettyServer 对象，用于启动 Netty 服务器
     * @return CommandLineRunner 对象，用于启动 Netty 服务器
     */
    @Bean
    CommandLineRunner run(MyNettyServer myNettyServer) {
        return args -> {
            new Thread(() -> {
                try {
                    myNettyServer.start();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        };
    }
}

