package ch18;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class SendDateServer {
    //클라이언트에게 현재 시간을 보내주고 끝내는 간단한 프로그램
    public static void main(String[] args) {
        ServerSocket ss = null;
        Socket sSocket = null;
        PrintWriter ps = null;

        try {
            System.out.println("클라이언트 접속을 기다립니다.");
            ss = new ServerSocket(12907);
            sSocket = ss.accept();
            System.out.println("접속되었습니다.");
            ps = new PrintWriter(sSocket.getOutputStream());
            //1번포인트 flush()
            ZonedDateTime now = ZonedDateTime.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY년 MM월 DD일 HH시 mm분입니다.");
            ps.println(now.format(dtf));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("연결을 종료합니다.");
                ss.close();
                sSocket.close();
                ps.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }

        }


    }


}
