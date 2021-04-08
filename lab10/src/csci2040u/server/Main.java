package csci2040u.server;

import csci2040u.client.ClientHandler;
import csci2040u.client.MainClient;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;


public class Main extends Application {
    private Socket socket = null;
    private DataInputStream in;

    //we can read this from the user too
    public  static String SERVER_ADDRESS = "localhost";
    public  static int SERVER_PORT = 888;

    private ServerSocket serverSocket = null;
    private int port;

    @Override
    public void start(Stage primaryStage){

        primaryStage.setTitle("Simple BBS Server");

        GridPane myGrid = new GridPane();
        myGrid.setAlignment(Pos.TOP_LEFT);
        myGrid.setHgap(10);
        myGrid.setVgap(10);
        myGrid.setPadding(new javafx.geometry.Insets(25, 25, 25, 25));


// -- inputs
        TextArea textArea = new TextArea();
        //Setting number of pages
        //area.setPrefColumnCount(15);
        textArea.setPrefHeight(180);
        textArea.setPrefWidth(300);
        textArea.setEditable(true);

        javafx.scene.control.Button exitBtn = new Button("Exit");
        HBox hbExitBtn = new HBox(25);
        hbExitBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbExitBtn.getChildren().add(exitBtn);

        exitBtn.setOnAction((event) -> {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.exit(0);
        });


        new Thread(() -> {
            try {
                // Create a server socket
                ServerSocket serverSocket = new ServerSocket(SERVER_PORT);

                //continous loop
                while (true) {
                    // Listen for a connection request, add new connection to the list
                    Socket socket = serverSocket.accept();
                    ClientHandler connection = new ClientHandler(socket);
                    in = new DataInputStream(socket.getInputStream());
                    textArea.appendText(in.readUTF() + "\n");
                    //System.out.println(in.readUTF() + "\n");
                    //create a new thread
                    Thread thread = new Thread(connection);
                    thread.start();

                }
            } catch (IOException e) {
                //txtAreaDisplay.appendText(ex.toString() + '\n');
            }
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        //        Add the components onto the myGrid pane
        myGrid.add(textArea, 0, 0);
        myGrid.add(exitBtn, 0, 1);
        Scene myScene = new Scene(myGrid, 300, 200);
        primaryStage.setScene(myScene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);

    }
}

/*
int port = 888;
        // port to listen default 8080, or the port from the argument
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        try{
            Main server = new Main(port);
            launch(args);
        } catch (IOException e) {
            e.printStackTrace();
        }
 */