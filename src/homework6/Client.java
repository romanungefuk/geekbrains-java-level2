package homework6;

import javax.swing.*;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class Client {

    private static final String SERVER_ADDR = "localhost";
    private static final int SERVER_PORT = 443;
    private static final String END_MESSAGE = "/end";


    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Scanner scanner;
    private String textForSending;

    public Client() {
        try {
            openConnection();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка соединения с сервером. " + e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void openConnection() throws IOException, InterruptedException {
        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        try {
            new Thread(() -> {
                try {
                    while (true) {
                        String strFromServer = in.readUTF();
                        if (strFromServer.equalsIgnoreCase(END_MESSAGE)) {
                            break;
                        }
                        System.out.println("Сервер:" + strFromServer);
                    }


                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Соединение не доступно",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                    // e.printStackTrace();
                }

            }).start();
            new Thread(() -> {
            while (true) {
                System.out.print("Введите техт для отправки на сервер:");
                scanner = new Scanner(System.in);
                textForSending = scanner.nextLine();
                if (textForSending != "") {
                    sendMessageToServer();
                } else if (textForSending.equals("")) {

                } else {
                    break;
                }
            }}).start();


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Соединение не доступно",
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void close(Closeable... objects) {
        for (Closeable o : objects) {
            try {
                if (o != null) {
                    o.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void sendMessageToServer() {
        if (socket.isClosed() || out == null) {
            JOptionPane.showMessageDialog(null, "Ошибка отправки сообщения. Сервер не доступен. ",
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            out.writeUTF(textForSending);
            out.flush();

            if (END_MESSAGE.equals(textForSending)) {
                close(in, out, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ошибка отправки сообщения. " + e.getMessage(),
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void main(String... args) {
        System.out.println("In main. Thread: " + Thread.currentThread().getName());
        new Client();
    }


}
