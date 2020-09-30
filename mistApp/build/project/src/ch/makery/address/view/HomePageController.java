package ch.makery.address.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


import ch.makery.address.MainMISTApp;

public class HomePageController {

	@FXML
	private Button btnSettings;

	private MainMISTApp mainApp;

	public HomePageController(){
	}


	public void setMainApp(MainMISTApp mainApp){
		this.mainApp = mainApp;
	}

	@FXML
	public void OnBtnAction(ActionEvent event){

		mainApp.showSettingsPage();

	}


}
