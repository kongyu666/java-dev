package local.kongyu.netty.netty;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Component
public class MyNettyClient {

    public static String sendMessage(String message) {
        String host = "localhost";
        int port = 27248;
        String response = "";

        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println(message);
            response = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}

