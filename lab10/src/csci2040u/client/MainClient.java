package csci2040u.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class MainClient extends Application {
    //we can read this from the user too
    public  static String SERVER_ADDRESS = "localhost";
    public  static int SERVER_PORT = 888;

    private static DataOutputStream out;
    private static Socket socket;

    @Override
    public void start(Stage primaryStage) throws IOException {
        /*try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        } catch (Exception e) {
            System.err.println("Cannot connect to the server, try again later.");
            //socket.close();
            System.exit(1);
        }*/

        primaryStage.setTitle("Simple BBS Client");

        GridPane myGrid = new GridPane();
        myGrid.setAlignment(Pos.TOP_LEFT);
        myGrid.setHgap(10);
        myGrid.setVgap(10);
        myGrid.setPadding(new javafx.geometry.Insets(25, 25, 25, 25));

        //ChatServer tester = new ChatServer();

// -- labels
        javafx.scene.control.Label userNameLabel = new javafx.scene.control.Label("Username");
        javafx.scene.control.Label messageLabel = new javafx.scene.control.Label("Message");
// -- inputs
        javafx.scene.control.TextField txUserName = new javafx.scene.control.TextField();
        javafx.scene.control.TextField txMessage = new javafx.scene.control.TextField();

//        -- Button
        javafx.scene.control.Button sendBtn = new Button("Send");
        HBox hbBtn = new HBox(25);
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn.getChildren().add(sendBtn);

        javafx.scene.control.Button exitBtn = new Button("Exit");
        HBox hbExitBtn = new HBox(25);
        hbExitBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbExitBtn.getChildren().add(exitBtn);

        sendBtn.setOnAction((event) -> {
            try {
                socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //get username and message
            String username = txUserName.getText();
            String message = txMessage.getText();

            String messageSend= "[" + username + "]" + message;
            try{
                out = new DataOutputStream(socket.getOutputStream());
                out.writeUTF("[" + username + "]: " + message + "");
                //out.close();
                out.flush();
            }catch(IOException e3){
                System.err.println("Cannot connect to the server, try again later.");
                e3.printStackTrace();
                System.exit(1);
            }
            txUserName.clear();
            txMessage.clear();


        });

        exitBtn.setOnAction((event) -> {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.exit(0);
        });

//        Add the components onto the myGrid pane
        myGrid.add(userNameLabel, 0,1);
        myGrid.add(txUserName, 1,1);
        myGrid.add(messageLabel, 0,2);
        myGrid.add(txMessage, 1,2);
        myGrid.add(hbBtn, 0, 3);
        myGrid.add(hbExitBtn, 0, 4);

        Scene myScene = new Scene(myGrid, 300,180);
        primaryStage.setScene(myScene);
        primaryStage.show();


    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}