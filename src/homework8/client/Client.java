package client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("BrainsChat Client");
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> {
            ((Controller) loader.getController()).sendExit();
            Platform.exit();
            System.exit(0);
        });

        new Thread(() -> {
            try {
                Thread.sleep(120000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           if( !((Controller) loader.getController()).getNetwork().isAuthenticated()){
               ((Controller) loader.getController()).sendExit();
               Platform.exit();
               System.exit(0);
           }
        }).start();

    }

    public static void main(String[] args) {
        launch(args);
    }
}