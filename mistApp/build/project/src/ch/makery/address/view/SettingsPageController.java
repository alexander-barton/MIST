/* Controller Class for the Settings Page
 * Stores values
 */

package ch.makery.address.view;

import ch.makery.address.MainMISTApp;
import ch.makery.address.model.TestSettings.ScanSig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import jidefx.scene.control.field.IntegerField;
import jidefx.scene.control.field.MaskTextField;
import org.controlsfx.control.ToggleSwitch;


public class SettingsPageController {


    @FXML
    private RadioButton radioBtnMouse;

    @FXML
    private ToggleGroup scanGroup;

    @FXML
    private RadioButton radioBtnKey;

    @FXML
    private RadioButton radioBtnTime;

    @FXML
    private Button btnStart;

    @FXML
    private Slider sliderConSpeed;

    @FXML
    private RadioButton radioBtnStrict;

    @FXML
    private ToggleGroup conditionsGroup;

    @FXML
    private RadioButton radioBtnRan;

    @FXML
    private Slider sliderExpDiff;

    @FXML
    private TextField txtCor;

    @FXML
    private TextField txtInCor;

    @FXML
    private TextField txtRec;

    @FXML
    private TextField txtNotRec;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtSaveDir;

    @FXML
    private Button btnID;

    @FXML
    private Button btnDir;

    @FXML
    private MaskTextField txtLeftKey;

    @FXML
    private MaskTextField txtRightKey;

    @FXML
    private MaskTextField txtConfKey;

    @FXML
    private IntegerField txtRestT;

    @FXML
    private IntegerField txtConT;

    @FXML
    private IntegerField txtExpT;

    @FXML
    private IntegerField txtRep;

    @FXML
    private IntegerField txtNBack;

    @FXML
    private MaskTextField txtKeySig;

    @FXML
    private ToggleSwitch toggleRest;

    @FXML
    private ToggleSwitch toggleCon;

    @FXML
    private ToggleSwitch toggleExp;

    @FXML
    private ToggleSwitch toggleTimeout;

    @FXML
    private ToggleSwitch toggleSound;

    private MainMISTApp mainApp;

	 //constructor
    public SettingsPageController(){
	    }

    public void setMainApp(MainMISTApp mainApp){
    	this.mainApp = mainApp;
	    }

    @FXML
    public void OnBtnAction(ActionEvent event) {


        //create arrays to going into mainApp.makeTests()
        int[] intVar = {txtRep.getValue(), (int) sliderExpDiff.getValue(), txtNBack.getValue(), txtRestT.getValue(), txtConT.getValue(), txtExpT.getValue()};
        boolean[] booVar = {radioBtnStrict.isSelected(),toggleRest.isSelected(),toggleCon.isSelected(),toggleExp.isSelected(),toggleSound.isSelected()};
        char[] charVar = {txtKeySig.getText().charAt(0),txtLeftKey.getCharacters().charAt(0),txtRightKey.getCharacters().charAt(0),txtConfKey.getCharacters().charAt(0)};


        ScanSig scanSig;
        if(radioBtnMouse.isSelected()){
            scanSig = ScanSig.MOUSE;
        }

        else if(radioBtnKey.isSelected()){
            scanSig = ScanSig.KEYBOARD;
        }
        else{
            scanSig = ScanSig.TIMED;
        }

        String[] stringVar = {txtCor.getCharacters().toString(),txtInCor.getCharacters().toString(),txtRec.getCharacters().toString(),txtNotRec.getCharacters().toString(),txtID.getCharacters().toString(),txtSaveDir.getCharacters().toString()};

       // mainApp.makeTests();
        mainApp.makeTests(intVar,booVar,charVar,stringVar,scanSig);
    	mainApp.showTestPage();


	    }
}
