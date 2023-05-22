import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java ChatClient <username> <server-port>");
            return;
        }
        String username = args[0];
        int serverPort = Integer.parseInt(args[1]);

        try {
            Socket socket = new Socket("localhost", serverPort);
            System.out.println("Connected to chat server");

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));

            out.println(username);

            Thread receivingThread = new ReceivingThread(socket, in);
            receivingThread.start();

            String message;
            while ((message = keyboardInput.readLine()) != null) {
                out.println(message);
                if (message.equals("/quit")) {
                    break;
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ReceivingThread extends Thread {
        private Socket socket;
        private BufferedReader in;

        public ReceivingThread(Socket socket, BufferedReader in) {
            this.socket = socket;
            this.in = in;
        }

        @Override
        public void run() {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println(message);
                }

                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
