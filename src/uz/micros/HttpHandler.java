package uz.micros;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HttpHandler {
    private final Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public HttpHandler(Socket socket) {
        this.socket = socket;

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void run() {
        String msg = "1";
        List<String> packet = new ArrayList<String>();
        try {
            while (msg != null && !msg.equals("")) {
                msg = in.readLine();
                packet.add(msg);
            }
            handlePacket(packet);
            socket.close();
        } catch (IOException ex) {

        }
    }

    private void handlePacket(List<String> packet) {

        for(String s: packet)
            System.out.println(s);
        out.println("HTTP/1.1 200 Ok");
        out.println("Content-Type: text/html; charset=UTF-8");
        out.println("Content-Length: 500");
        out.println("");
        String  file = loadFile();
        out.println(file);
        String st = "<h3>Server TIme:<h3>" + new Date();
        out.println(st);
    }

    private String loadFile() {

        try {
            FileReader f = new FileReader("C:\\Users\\java\\JWebServer\\out\\production\\JWebServer\\uz\\micros\\index.html");
            BufferedReader buf = new BufferedReader(f);

            StringBuilder sb = new StringBuilder();
            String line = buf.readLine();

            while (line !=null){
                sb.append(line);
                sb.append(System.lineSeparator());
                line = buf.readLine();

            }
            return  sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    return "Empty";
    }

}
