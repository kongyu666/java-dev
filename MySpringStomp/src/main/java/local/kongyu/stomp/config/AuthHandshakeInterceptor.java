package local.kongyu.stomp.config;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * HandshakeInterceptor用于在WebSocket连接建立之前进行用户认证。
 * 该示例代码展示了如何在WebSocket连接建立之前进行简单的token认证。
 *
 * 作者：孔余
 * 日期：2024-05-11 20:56
 */
@Component
public class AuthHandshakeInterceptor implements HandshakeInterceptor {

    /**
     * 在握手之前调用，用于用户认证。
     *
     * @param request     当前的HTTP请求
     * @param response    当前的HTTP响应
     * @param wsHandler   将要处理WebSocket消息的处理器
     * @param attributes  将传递给WebSocket会话的属性
     * @return 是否同意握手，true表示同意，false表示拒绝
     * @throws Exception 如果发生错误
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        // 获取URI中的查询参数，并提取token
        String query = request.getURI().getQuery();
        String token = null;

        if (query != null && query.contains("=")) {
            token = query.split("=")[1];
        }

        // 如果token为空，则拒绝握手
        if (token == null) {
            return false;
        }

        // 将token放入attributes中，供WebSocket处理器使用
        attributes.put("token", token);

        // 调用用户认证方法
        return authenticateUser(token);
    }

    /**
     * 在握手之后调用。
     *
     * @param request    当前的HTTP请求
     * @param response   当前的HTTP响应
     * @param wsHandler  将要处理WebSocket消息的处理器
     * @param exception  握手过程中发生的异常
     */
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) {
        // 握手成功后的处理，可以在此处进行日志记录或其他操作
    }

    /**
     * 用户认证方法。
     *
     * @param token 要认证的token
     * @return 认证是否通过，true表示通过，false表示不通过
     */
    private boolean authenticateUser(String token) {
        // 实现你的用户认证逻辑
        // 示例中简单地假设只有token为"123456"才是合法的
        return "123456".equals(token);
    }
}
