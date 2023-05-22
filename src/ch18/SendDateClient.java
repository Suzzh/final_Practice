package ch18;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class SendDateClient {

    public static void main(String[] args) {
        try(Socket cSocket = new Socket("localhost", 12907);
            BufferedReader br = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));){
            String tmp="";
            System.out.println("데이터를 읽어들이겠습니다.");
            tmp = br.readLine();


            while(tmp!=null) {
                System.out.println(tmp);
                tmp = br.readLine();
            }

            System.out.println("프로그램을 종료합니다.");

        }catch(Exception e) {
            e.printStackTrace();
        }




    }

}
