package uz.micros;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {

        try {
            ServerSocket srvSocket = new ServerSocket(80);

            while (true){
            final Socket socket = srvSocket.accept();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    new HttpHandler(socket).run();
                }
            }).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
