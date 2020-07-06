package homework6;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class Server {
    private static final int SERVER_PORT = 443;
    private static final String END_MESSAGE = "/end";

    private static DataInputStream in;
    private static DataOutputStream out;

    public static void main(String[] args) {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {

                System.out.println("Сервер запущен, ожидаем подключения...");
                Socket socket;
                socket = serverSocket.accept();

                System.out.println("Клиент подключился");
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
                while (true) {
                    String str = in.readUTF();
                    if (str.equals(END_MESSAGE)) {
                        break;
                    }
                    System.out.println("Client: " + str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                try {
                    System.out.print("Введите техт для отправки клиенту:");
                    Scanner scanner = new Scanner(System.in);
                    String textForSending = scanner.nextLine();
                    if (textForSending != "") {

                        out.writeUTF(textForSending);

                    } else if (textForSending.equals("")) {

                    } else {
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}