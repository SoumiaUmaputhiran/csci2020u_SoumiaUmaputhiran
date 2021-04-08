package csci2040u.client;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket socket = null;
    private BufferedReader requestInput = null;
    private DataOutputStream responseOutput = null;
    // Get message from the client
    private DataInputStream input = null;
    String message;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        requestInput = new BufferedReader( new InputStreamReader(socket.getInputStream()));
        responseOutput = new DataOutputStream(socket.getOutputStream());
    }

    public void run(){
        try{
            message = input.readUTF();
        }catch(IOException e){
            e.printStackTrace();

        }
        /*while (true) {
            try {
                message = input.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }*/
    }
}
