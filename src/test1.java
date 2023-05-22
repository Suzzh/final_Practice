import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class test1 {

    public static void main(String[] args) {

        int port = 3000;
        String serverIP;
        PrintWriter pw=null;

        try{
            System.out.println("안");
            serverIP = InetAddress.getLocalHost().getHostAddress();
            System.out.println(serverIP);
            Socket socket = new Socket (serverIP, port);
            pw = new PrintWriter(socket.getOutputStream());
            pw.println("안녕?");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }



    }
