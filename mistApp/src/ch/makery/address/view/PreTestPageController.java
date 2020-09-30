package ch.makery.address.view;

import ch.makery.address.MainMISTApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class PreTestPageController {

    @FXML
    private AnchorPane preTestPane;

    @FXML
    private Label lblStartInstruc;


    private MainMISTApp mainApp;

    public PreTestPageController(){

    }

    public void setMainApp(MainMISTApp mainApp){
        this.mainApp = mainApp;
    }

    public void callFocus(){
        this.preTestPane.requestFocus();
    }

    @FXML
    public void OnEnterPressed(KeyEvent ke){

        if(ke.getCode() == KeyCode.ENTER){
            this.mainApp.showTestPage();
        }


    }

}
