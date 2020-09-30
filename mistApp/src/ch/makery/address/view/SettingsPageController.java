/* Controller Class for the Settings Page
 * Stores values
 */

package ch.makery.address.view;

import ch.makery.address.MainMISTApp;
import ch.makery.address.model.TestSettings.ScanSig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.DirectoryChooser;
import jidefx.scene.control.field.DoubleField;
import jidefx.scene.control.field.IntegerField;
import jidefx.scene.control.field.MaskTextField;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.prefs.Preferences;

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

    @FXML
    private ToggleSwitch toggleClick;

    @FXML
    private ToggleSwitch togglePopUp;

    @FXML
    private DoubleField txtSigDur;

    @FXML
    private TextField txtBottomName;

    @FXML
    private TextField txtTopName;

    @FXML
    private ToggleSwitch toggleMoveFeedback;

    private MainMISTApp mainApp;

    private String userDir;
    private String filename;

    private File initialFile;
    private DirectoryChooser saveDir = new DirectoryChooser();
    private int controlSpeed;
    private Preferences userPrefs;


	//constructor
    public SettingsPageController(){

	    }

    public void setMainApp(MainMISTApp mainApp){
    	this.mainApp = mainApp;
	    }

    public void loadPreferences(){

        this.userPrefs = Preferences.userNodeForPackage(this.getClass());

        this.txtRep.setValue(this.userPrefs.getInt("REPETITIONS",1));
        this.sliderExpDiff.setValue(this.userPrefs.getInt("DIFFICULTY",1));
        this.txtNBack.setValue(this.userPrefs.getInt("N_BACK",0));
        this.txtRestT.setValue(this.userPrefs.getInt("REST_TIME",10));
        this.txtConT.setValue(this.userPrefs.getInt("CONTROL_TIME",10));
        this.txtExpT.setValue(this.userPrefs.getInt("EXP_TIME",10));
        this.sliderConSpeed.setValue(this.userPrefs.getInt("CONTROL_SPEED",1));


        this.radioBtnStrict.setSelected(this.userPrefs.getBoolean("IS_STRICT",true));
        this.radioBtnRan.setSelected(!this.radioBtnStrict.isSelected());
        this.toggleRest.setSelected(this.userPrefs.getBoolean("IS_REST",false));
        this.toggleCon.setSelected(this.userPrefs.getBoolean("IS_CONTROL",false));
        this.toggleExp.setSelected(this.userPrefs.getBoolean("IS_EXP",false));
        this.toggleSound.setSelected(this.userPrefs.getBoolean("IS_SOUND",false));
        this.toggleClick.setSelected(this.userPrefs.getBoolean("IS_CLICK",false));
        this.toggleTimeout.setSelected(this.userPrefs.getBoolean("IS_TIMEOUT",false));
        this.togglePopUp.setSelected(this.userPrefs.getBoolean("IS_POP_UP",false));
        this.toggleMoveFeedback.setSelected(this.userPrefs.getBoolean("IS_STATIC_FEEDBACK",false));

        this.radioBtnMouse.setSelected(this.userPrefs.getBoolean("IS_MOUSE",true));
        this.radioBtnKey.setSelected(this.userPrefs.getBoolean("IS_KEYBOARD",false));
        this.radioBtnTime.setSelected(this.userPrefs.getBoolean("IS_TIMED",false));

        this.txtCor.setText(this.userPrefs.get("FEEDBACK_CORRECT","CORRECT"));
        this.txtInCor.setText(this.userPrefs.get("FEEDBACK_WRONG","INCORRECT"));
        this.txtRec.setText(this.userPrefs.get("FEEDBACK_RECORD","RECORDED"));
        this.txtNotRec.setText(this.userPrefs.get("FEEDBACK_NOT_RECORD","NOT RECORDED"));
        this.txtID.setText(this.userPrefs.get("FILENAME","01"));
        this.userDir = this.userPrefs.get("USER_DIRECTORY",System.getProperty("user.home") + File.separator);
        this.txtTopName.setText(this.userPrefs.get("NAME_TOP","YOU"));
        this.txtBottomName.setText(this.userPrefs.get("NAME_BOTTOM","AVERAGE"));

        this.txtKeySig.setText(this.userPrefs.get("KEY_SCAN_SIGNAL","a"));
        this.txtLeftKey.setText(this.userPrefs.get("KEY_LEFT","1"));
        this.txtRightKey.setText(this.userPrefs.get("KEY_RIGHT","3"));
        this.txtConfKey.setText(this.userPrefs.get("KEY_CONF","2"));

        this.txtSigDur.setValue(this.userPrefs.getDouble("SIGNAL_LENGTH",2.5));


    }

    public void setUpPage(){

        DateFormat dFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();

        this.filename = this.txtID.getText() + "_" + dFormat.format(date);
        this.initialFile = new File(this.userDir);
        //makes sure the old preferences directory still exists
        if(this.initialFile.exists() && this.initialFile.isDirectory()){
            this.saveDir.setInitialDirectory(this.initialFile);
            this.txtSaveDir.setText(this.userDir + this.filename + ".txt");
        }
        else{
            this.userDir = System.getProperty("user.home") + File.separator;
            this.initialFile = new File(this.userDir);
            this.saveDir.setInitialDirectory(this.initialFile);
            this.txtSaveDir.setText(this.userDir + this.filename + ".txt");
        }

    }

    @FXML
    public void OnBtnAction(ActionEvent event) {

        //makes sure that at least one condition is selected
        if(!toggleRest.isSelected() && !toggleCon.isSelected() && !toggleExp.isSelected()){

            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("!");
            alert.setHeaderText(null);
            alert.setContentText("Please select at least one condtion type");
            alert.showAndWait();
        }
        //makes sure that the scan sig key does not equal one of the input keys
        else if(radioBtnKey.isSelected() &&
                (txtKeySig.getText().charAt(0) == txtLeftKey.getCharacters().charAt(0)
                || txtKeySig.getText().charAt(0) == txtRightKey.getCharacters().charAt(0)
                || txtKeySig.getText().charAt(0) == txtConfKey.getCharacters().charAt(0))){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("!");
            alert.setHeaderText(null);
            alert.setContentText("The scan signal key is the same as one of the control keys");
            alert.showAndWait();

        }

        //create arrays to going into mainApp.makeTests()
        else{
        switch((int) this.sliderConSpeed.getValue()){

        case 1:
           this.controlSpeed = 20;
           break;

        case 2:
            this.controlSpeed = 18;
            break;

        case 3:
            this.controlSpeed = 16;
            break;

        case 4:
            this.controlSpeed = 14;
            break;

        case 5:
            this.controlSpeed = 12;
            break;

        case 6:
            this.controlSpeed = 10;
            break;

        case 7:
            this.controlSpeed = 8;
            break;

        case 8:
            this.controlSpeed = 6;
            break;

        }

        int[] intVar = {txtRep.getValue(), (int) sliderExpDiff.getValue(), txtNBack.getValue(),
                txtRestT.getValue(), txtConT.getValue(), txtExpT.getValue(), this.controlSpeed};

        double doubleVar;
        if(this.txtSigDur.getValue() == null){
            doubleVar = 2.5;
        }
        else{
            doubleVar = txtSigDur.getValue();
        }

        boolean[] booVar = {radioBtnStrict.isSelected(),toggleRest.isSelected(),toggleCon.isSelected(),
                toggleExp.isSelected(),toggleSound.isSelected(),toggleClick.isSelected(),
                !toggleTimeout.isSelected(),!togglePopUp.isSelected(),toggleMoveFeedback.isSelected()};
        char[] charVar = {txtKeySig.getText().charAt(0),txtLeftKey.getCharacters().charAt(0),
                txtRightKey.getCharacters().charAt(0),txtConfKey.getCharacters().charAt(0)};

        //String version of the above for savePreferences() method
        String[] charVarString = {this.txtKeySig.getText(),this.txtLeftKey.getText(),
                this.txtRightKey.getText(),this.txtConfKey.getText()};


        boolean[] sigBoo = {false,false,false};
        ScanSig scanSig;
        if(radioBtnMouse.isSelected()){
            scanSig = ScanSig.MOUSE;
            sigBoo[0] = true;
        }

        else if(radioBtnKey.isSelected()){
            scanSig = ScanSig.KEYBOARD;
            sigBoo[1] = true;
        }
        else{
            scanSig = ScanSig.TIMED;
            sigBoo[2] = true;
        }


        //make a new file for save info
        File finalFile = new File(this.userDir + this.filename + ".txt");
        Integer n = 1;
        while(finalFile.exists()){
            DateFormat dFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();

            this.filename = this.txtID.getText() + "-" + n.toString() + "_" + dFormat.format(date);
            finalFile = new File(this.userDir + this.filename + ".txt");
            n++;
        }
        String[] stringVar = {txtCor.getCharacters().toString(),txtInCor.getCharacters().toString(),
                txtRec.getCharacters().toString(),txtNotRec.getCharacters().toString(),
                this.filename,this.userDir, this.txtBottomName.getCharacters().toString(),
                this.txtTopName.getCharacters().toString()};

        try {
            finalFile.createNewFile();
        } catch (IOException e) {

            e.printStackTrace();
            System.out.println(this.userDir + this.filename);
        }

        String format = "%-20s\t%-20s\t%-20s\t%-20s%n";
        try {
            FileWriter fileWriter = new FileWriter(finalFile);
            fileWriter.write(String.format(format,"TIME","EVENT","INFO","RT (ms)"));
            fileWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        savePreferences(intVar,booVar,charVarString,stringVar,sigBoo,doubleVar,(int) this.sliderConSpeed.getValue(),this.txtID.getText());
        this.mainApp.makeTests(intVar,booVar,charVar,stringVar,scanSig,doubleVar);
    	this.mainApp.showPreTestPage();
        }

	    }

    @FXML
    public void OnBtnID(ActionEvent event){

        DateFormat dFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();

        TextInputDialog dialog = new TextInputDialog();

        dialog.setTitle(null);
        dialog.setHeaderText(null);
        dialog.setContentText("Please enter your participant ID:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(ID -> this.txtID.setText(ID));
        result.ifPresent(ID -> this.filename = ID + "_" + dFormat.format(date));

        this.txtSaveDir.setText(this.userDir + this.filename + ".txt");
    }

    @FXML
    public void OnDirectoryBtn(ActionEvent event){


        File newFile = this.saveDir.showDialog(this.mainApp.getPrimaryStage());
        if(newFile != null){
            this.userDir = newFile.toString() + File.separator;
            this.txtSaveDir.setText(this.userDir + this.filename + ".txt");
        }



    }



    private void savePreferences(int[] intVar, boolean[] booVar, String[] charVar, String[] stringVar, boolean[] sigBoo, double doubleVar,int conSpeed,String filename){

        this.userPrefs.putInt("REPETITIONS",intVar[0]);
        this.userPrefs.putInt("DIFFICULTY",intVar[1]);
        this.userPrefs.putInt("N_BACK",intVar[2]);
        this.userPrefs.putInt("REST_TIME",intVar[3]);
        this.userPrefs.putInt("CONTROL_TIME",intVar[4]);
        this.userPrefs.putInt("EXP_TIME",intVar[5]);
        this.userPrefs.putInt("CONTROL_SPEED",conSpeed);

        this.userPrefs.putBoolean("IS_STRICT",booVar[0]);
        this.userPrefs.putBoolean("IS_REST",booVar[1]);
        this.userPrefs.putBoolean("IS_CONTROL",booVar[2]);
        this.userPrefs.putBoolean("IS_EXP",booVar[3]);
        this.userPrefs.putBoolean("IS_SOUND",booVar[4]);
        this.userPrefs.putBoolean("IS_CLICK",booVar[5]);
        this.userPrefs.putBoolean("IS_TIMEOUT",!booVar[6]);
        this.userPrefs.putBoolean("IS_POP_UP",!booVar[7]);
        this.userPrefs.putBoolean("IS_STATIC_FEEDBACK",booVar[8]);

        this.userPrefs.putBoolean("IS_MOUSE",sigBoo[0]);
        this.userPrefs.putBoolean("IS_KEYBOARD",sigBoo[1]);
        this.userPrefs.putBoolean("IS_TIMED",sigBoo[2]);

        this.userPrefs.put("FEEDBACK_CORRECT",stringVar[0]);
        this.userPrefs.put("FEEDBACK_WRONG",stringVar[1]);
        this.userPrefs.put("FEEDBACK_RECORD",stringVar[2]);
        this.userPrefs.put("FEEDBACK_NOT_RECORD",stringVar[3]);

        this.userPrefs.put("FILENAME",filename);
        this.userPrefs.put("USER_DIRECTORY",stringVar[5]);
        this.userPrefs.put("NAME_BOTTOM",stringVar[6]);
        this.userPrefs.put("NAME_TOP",stringVar[7]);

        this.userPrefs.put("KEY_SCAN_SIGNAL",charVar[0]);
        this.userPrefs.put("KEY_LEFT",charVar[1]);
        this.userPrefs.put("KEY_RIGHT",charVar[2]);
        this.userPrefs.put("KEY_CONF",charVar[3]);

        this.userPrefs.putDouble("SIGNAL_LENGTH",doubleVar);


    }

}
