package ch.makery.address.view;

import ch.makery.address.MainMISTApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class EndPageController {


    @FXML
    private AnchorPane endPane;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnExit;

    @FXML
    private Label lblEndInstruc;

    private MainMISTApp mainApp;

    public EndPageController(){

    }

    public void setMainApp(MainMISTApp mainApp){
        this.mainApp = mainApp;
    }

    public void callFocus(){
        this.endPane.requestFocus();
    }

    @FXML
    public void BtnSettingsPress(ActionEvent event){

        mainApp.showSettingsPage();

    }
}
