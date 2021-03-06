package csci2040u.lab05;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    private TableView<StudentRecord> table = new TableView<StudentRecord>();

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Lab05");
        stage.setWidth(650);
        stage.setHeight(500);


        TableColumn sidCol = new TableColumn("SID");
        sidCol.setMinWidth(100);
        sidCol.setCellValueFactory(
                new PropertyValueFactory<StudentRecord, String>("SID"));

        TableColumn assignmentsCol = new TableColumn("Assignments");
        assignmentsCol.setMinWidth(100);
        assignmentsCol.setCellValueFactory(
                new PropertyValueFactory<StudentRecord, String>("Assignments"));

        TableColumn midtermCol = new TableColumn("Midterms");
        midtermCol.setMinWidth(100);
        midtermCol.setCellValueFactory(
                new PropertyValueFactory<StudentRecord, String>("Midterm"));


        TableColumn finalExamCol = new TableColumn("Final Exam");
        finalExamCol.setMinWidth(100);
        finalExamCol.setCellValueFactory(
                new PropertyValueFactory<StudentRecord, String>("FinalExam"));

        TableColumn finalMarkCol = new TableColumn("Final Mark");
        finalMarkCol.setMinWidth(100);
        finalMarkCol.setCellValueFactory(
                new PropertyValueFactory<StudentRecord, String>("FinalMark"));

        TableColumn letterGradeCol = new TableColumn("Letter Grade");
        letterGradeCol.setMinWidth(100);
        letterGradeCol.setCellValueFactory(
                new PropertyValueFactory<StudentRecord, String>("LetterGrade"));

        table.setItems(DataSource.getAllMarks());
        table.getColumns().addAll(sidCol,assignmentsCol,midtermCol,finalExamCol,finalMarkCol,letterGradeCol);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}