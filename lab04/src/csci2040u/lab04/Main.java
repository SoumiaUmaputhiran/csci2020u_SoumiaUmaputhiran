package csci2040u.lab04;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
//import jdk.internal.org.objectweb.asm.TypeReference;

import java.awt.*;
import java.time.format.DateTimeFormatter;


import static javafx.scene.text.Font.font;

public class Main<format> extends Application {

    private Label display;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Lab04");

        //Creating layout gridPane
        GridPane myGrid = new GridPane();
        myGrid.setAlignment(Pos.TOP_LEFT);
        myGrid.setHgap(10);
        myGrid.setVgap(10);
        myGrid.setPadding(new javafx.geometry.Insets(25, 25, 25, 25));


// -- labels
        javafx.scene.control.Label lbUserName = new javafx.scene.control.Label("Username:");
        javafx.scene.control.Label lbPassword = new javafx.scene.control.Label("Password:");
        javafx.scene.control.Label lbFullName = new javafx.scene.control.Label("Full Name:");
        javafx.scene.control.Label lbEmail = new javafx.scene.control.Label("E-mail:");
        javafx.scene.control.Label lbPhoneNumber = new javafx.scene.control.Label("Phone #:");
        javafx.scene.control.Label lbDate = new javafx.scene.control.Label("Date:");
// -- inputs
        javafx.scene.control.TextField txUserName = new javafx.scene.control.TextField();
        PasswordField psPassword = new PasswordField();
        javafx.scene.control.TextField txFullName = new javafx.scene.control.TextField();
        javafx.scene.control.TextField txEmail = new javafx.scene.control.TextField();
        javafx.scene.control.TextField txPhoneNumber = new javafx.scene.control.TextField();
        javafx.scene.control.DatePicker dpDate = new javafx.scene.control.DatePicker();

//        -- Button
        javafx.scene.control.Button btn = new Button("Register");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);

//        Add the components onto the myGrid pane
        myGrid.add(lbUserName, 0,1);
        myGrid.add(txUserName, 1,1);
        myGrid.add(lbPassword, 0,2);
        myGrid.add(psPassword, 1,2);
        myGrid.add(lbFullName, 0,3);
        myGrid.add(txFullName, 1,3);
        myGrid.add(lbEmail, 0,4);
        myGrid.add(txEmail, 1,4);
        myGrid.add(lbPhoneNumber, 0,5);
        myGrid.add(txPhoneNumber, 1,5);
        myGrid.add(lbDate, 0,6);
        myGrid.add(dpDate, 1,6);
        myGrid.add(hbBtn, 1, 7);

        /*Learned from: https://www.educba.com/javafx-textfield/ and
          https://www.codejava.net/java-se/swing/how-to-use-jdatepicker-to-display-calendar-component
         */
        //Changing the format of the date to print in month day, year format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        //Setting the button event
        //Prinitng Full Name, E-mail, Phone Number and Date to console
        btn.setOnAction((event) -> {
            System.out.println("\nFull Name: "+txFullName.getText()
                    + "\nE-mail: "+txEmail.getText()
                    + "\nPhone #: "+txPhoneNumber.getText()
                    + "\nDate: "+ formatter.format(dpDate.getValue()));
        });

//        Creating myScene with custom layout
        Scene myScene = new Scene(myGrid, 600,300);
        primaryStage.setScene(myScene);
        primaryStage.show();

}

    public static void main(String[] args) {
        launch(args);
    }
}


/*
<Label text="User Name:" GridPane.columnIndex="0" GridPane.rowIndex="1" />
<TextField GridPane.columnIndex="1" GridPane.rowIndex="1" />

<Label text="Password:" GridPane.columnIndex="0" GridPane.rowIndex="2" />
<PasswordField fx:id="passwordField" GridPane.columnIndex="1" GridPane.rowIndex="2" />

<Label text="Full Name:" GridPane.columnIndex="0" GridPane.rowIndex="3" />
<TextField GridPane.columnIndex="1" GridPane.rowIndex="3" />

<Label text="E-mail:" GridPane.columnIndex="0" GridPane.rowIndex="4" />
<TextField GridPane.columnIndex="1" GridPane.rowIndex="4" />

<Label text="Phone Number:" GridPane.columnIndex="0" GridPane.rowIndex="5" />
<TextField GridPane.columnIndex="1" GridPane.rowIndex="5" />

<DatePicker date="Date:" GridPane.columnIndex="0" GridPane.rowIndex="7" />
<DatePicker GridPane.columnIndex="1" GridPane.rowIndex="7" />

<HBox alignment="bottom_right" spacing="10" GridPane.columnIndex="1" GridPane.rowIndex="8">
<Button onAction="#handleSubmitButtonAction" text="Sign In" />
</HBox>

<Text fx:id="actiontarget" GridPane.columnIndex="1" GridPane.rowIndex="6" />

System.out.print("Username:" + txUserName.toString());
                System.out.print("Password:" + psPassword);
                System.out.print("Full Name:" +  txFullName);
                System.out.print("E-mail:" + txEmail);
                System.out.print("Phone Number:" + txPhoneNumber);
                System.out.print("Date:" + dpDate);
*/