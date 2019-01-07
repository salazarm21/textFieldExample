/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textfieldexample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author marina
 */
public class TextFieldExample extends Application {

    Label labelFirstName;
    Label labelMiddleName;
    Label labelLastName;

    TextField tfFirstName;
    TextField tfMiddleName;
    TextField tfLastName;

    private GridPane createTextFieldPane() {
        GridPane gPane = new GridPane();

        labelFirstName = new Label("First Name: ");
        labelMiddleName = new Label("Middle Name: ");
        labelLastName = new Label("Last Name: ");

        tfFirstName = new TextField();
        tfMiddleName = new TextField();
        tfLastName = new TextField();

        gPane.add(labelFirstName, 0, 0);
        gPane.add(tfFirstName, 1, 0); //column before row
        gPane.add(labelMiddleName, 0, 1);
        gPane.add(tfMiddleName, 1, 1);
        gPane.add(labelLastName, 0, 2);
        gPane.add(tfLastName, 1, 2);

        return gPane;
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane gPane = createTextFieldPane();
        Scene scene = new Scene(gPane, 400, 400, Color.ORANGERED);

        Button btOkay = new Button("Okay");
        Button btCancel = new Button("Cancel");
        
        btCancel.setOnAction( (ActionEvent event) -> {
            System.exit(0);
        });

        btOkay.setOnAction( (ActionEvent event) -> {
            gPane.getChildren().clear();
            Text textName = getNameFromField();
            scene.setOnKeyPressed((KeyEvent keyEvent) -> {
                changeSceneText(keyEvent, textName);
            });
            gPane.getChildren().add(textName);
        });
        
        gPane.add(btOkay, 0, 3);
        gPane.add(btCancel, 1, 3);

        primaryStage.setTitle("Menu Items");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private Text getNameFromField() {
        String name = tfLastName.getText() + ", " + tfFirstName.getText() + " " + tfMiddleName.getText();

        Text textName = new Text(name);
        textName.setFont(new Font(80));
        
        return textName; 
    }

    private void changeSceneText(KeyEvent keyEvent, Text textName) {
        int fontSize = (int) textName.getFont().getSize();
        if(keyEvent.getText().equalsIgnoreCase("L") && fontSize < 150){
        fontSize += 10;   
        } else if (keyEvent.getText().equalsIgnoreCase("S") && fontSize > 30){
            fontSize -= 10;
        }
        textName.setFont(new Font (fontSize));
       
    }

}
