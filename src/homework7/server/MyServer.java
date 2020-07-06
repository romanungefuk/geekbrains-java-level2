package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class MyServer {
    private final int PORT = 8189;

    private Map<String, ClientHandler> clients;
    private AuthService authService;

    public AuthService getAuthService() {
        return authService;
    }

    public MyServer() {
        try (ServerSocket server = new ServerSocket(PORT)) {
            authService = new BaseAuthService();
            authService.start();
            clients = new HashMap<>();

            while (true) {
                System.out.println("Сервер ожидает подключения");
                Socket socket = server.accept();
                System.out.println("Клиент подключился");
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            System.out.println("Ошибка в работе сервера");
        } finally {
            if (authService != null) {
                authService.stop();
            }
        }
    }

    public synchronized boolean isNickBusy(String nick) {
        return clients.containsKey(nick);
    }

    public synchronized void broadcastMsg(String msg) {
        if (msg.contains("/w nick")) {
            for (ClientHandler o : clients.values()) {
                if (o.getName().equals(msg.substring((msg.indexOf("/w nick") + 3), (msg.indexOf("/w nick") + 8)))) {
                    o.sendMsg(msg.substring((msg.indexOf(msg)), (msg.indexOf(":")))+":"+
                            msg.substring((msg.indexOf("/w nick") + 8)));
                }

            }
        } else {
        for (ClientHandler o : clients.values()) {
            o.sendMsg(msg);
        } }
    }

    public synchronized void broadcastMsg(String from, String msg) {
        broadcastMsg(formatMessage(from, msg));
    }

    public synchronized void unsubscribe(ClientHandler o) {
        clients.remove(o.getName());
    }

    public synchronized void subscribe(ClientHandler o) {
        clients.put(o.getName(), o);
    }

    private String formatMessage(String from, String msg) {
        return from + ": " + msg;
    }
}
