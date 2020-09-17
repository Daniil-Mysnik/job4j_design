package ru.jo4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean stopped = false;
            while (!stopped) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    String echo = "";
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println(str);
                        if (str.contains("GET")) {
                            echo = str.substring(10, str.length() - 9);
                        }
                    }
                    switch (echo) {
                        case "Hello" :
                            out.write(("Hello, dear friend" + System.lineSeparator()).getBytes());
                            break;
                        case "Exit" :
                            out.write(("Server stopped" + System.lineSeparator()).getBytes());
                            stopped = true;
                            break;
                        default:
                            out.write((echo + System.lineSeparator()).getBytes());
                    }
                }
            }
        }
    }
}
