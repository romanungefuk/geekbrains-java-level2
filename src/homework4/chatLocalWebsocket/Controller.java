package homework4.chatLocalWebsocket;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public static final int PORT = 8090;
    public static final String HOST = "localhost";

    @FXML
    HBox msgPanel;

    @FXML
    TextField msgField;

    @FXML
    TextArea textField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        msgField.requestFocus();
        System.out.println("Initialized");
    }

    public void showAlert(String msg) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
            alert.showAndWait();
        });
    }

    public void sendMsg() throws IOException {
        Socket socket = null;
        try {
            socket = new Socket(HOST, PORT);
            try (
                    InputStream in = socket.getInputStream();
                    OutputStream out = socket.getOutputStream()) {

                String line = msgField.getText()+"\n";
                out.write(line.getBytes());
                out.flush();

                byte[] data = new byte[32 * 1024];
                int readBytes = in.read(data);
                textField.appendText("Server:"+new String(data, 0, readBytes));
                msgField.clear();
                msgField.requestFocus();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }

    }
}