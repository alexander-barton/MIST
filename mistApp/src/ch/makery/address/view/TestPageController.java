/* Controller Class for the testPages
 * Updates the selection the participant has made
 * as well as the progress bar and the question
 * presented,etc.
 */


package ch.makery.address.view;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import ch.makery.address.MainMISTApp;
import ch.makery.address.model.TestMath.condition;
import ch.makery.address.model.TestQuestion;
import ch.makery.address.model.TestSettings.ScanSig;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class TestPageController {

	@FXML
	private ProgressBar barTimer;

	@FXML
	private TextField txtQuestion;

	@FXML
	private TextArea txtFeedback;

	@FXML
	private TextField txtGo; // only for n-back

	@FXML
	private AnchorPane testPane;

	@FXML
	private Group groupInput;

	@FXML
    private ToggleGroup numGroup;

	   	@FXML
	   	private Group g0;
	    @FXML
	    private Rectangle rec0;
	    @FXML
	    private Label num0;

	    @FXML
	    private Group g1;
	    @FXML
	    private Rectangle rec1;
	    @FXML
	    private Label num1;

	    @FXML
	    private Group g2;
	    @FXML
	    private Rectangle rec2;
	    @FXML
	    private Label num2;

	    @FXML
	    private Group g3;
	    @FXML
	    private Rectangle rec3;
	    @FXML
	    private Label num3;

	    @FXML
	    private Group g4;
	    @FXML
	    private Rectangle rec4;
	    @FXML
	    private Label num4;

	    @FXML
	    private Group g5;
	    @FXML
	    private Rectangle rec5;
	    @FXML
	    private Label num5;

	    @FXML
	    private Group g6;
	    @FXML
	    private Rectangle rec6;
	    @FXML
	    private Label num6;

	    @FXML
	    private Group g7;
	    @FXML
	    private Rectangle rec7;
	    @FXML
	    private Label num7;

	    @FXML
	    private Group g8;
	    @FXML
	    private Rectangle rec8;
	    @FXML
	    private Label num8;

	    @FXML
	    private Group g9;
	    @FXML
	    private Rectangle rec9;
	    @FXML
	    private Label num9;


	@FXML
	private Polygon arrowYou;

	@FXML
	private Polygon arrowThem;

	@FXML
	private Label lblYou;
	@FXML
	private Label lblThem;


	private MainMISTApp mainApp;


	private int scannerCount;          // counts number of times scanner signal has been pressed
	private Integer inputHover;        // keeps track of where the input button is
	private Integer chosenAnswer;      // is the official choice of the participant
	private int orderIndex;
	private int popUpOrderIndex;
	private Integer currentAnswer;     // answer for the specific question
	private ArrayList<Integer> oldAnswers;

	private Timeline questionTimer;    // timer for the experimental question bar
	private Timeline timerControl;      // timer for the control w/timeout condition

	private double questionSpeedVar;

	private Timeline conditionTimer;   //timer for each condition

	private int numCorrect;
	private int numWrong;
	private int numTime;
	private int numQuestions;
	private int trialCount;            // keeps track of the number of iterations
	                                   // and shuts it off when gone through
	private Integer successiveAnswers;

	private boolean isFirstQuestion;
	private boolean duringFirstQuestion;  // check so that feedback from questions
	                                    // in old conditions won't reset the first question
	                                    // in a new condition

	private boolean hasTimedOut;           //check for if questions timeout
	                                        //i.e. was not answered

	private final int redOff = 232;        //RGB values for the input keys (ON = highlighted, OFF = neutral)
	private final int greenOff = 235;
	private final int blueOff = 238;

	private final int redOn = 142;
	private final int greenOn = 144;
	private final int blueOn = 147;

	private boolean gettingFeedback;       //is true when getting feedback
	                                       //BETWEEN questions
	private boolean gettingTotalFeedback;      //is true when getting feedback
	                                           //AT THE END of a condition
	private boolean clickingAnswer;        //is true when mouse answer selection has been selected
	private boolean isScanSig;                 //is true if the scanner signal is mouse
	                                           //so as not to confuse choosing answers
	                                           //with the scanner signal

	//doubles keeping track of arrow positions
	private double xPosArrowYou = 493;
	private double xPosArrowThem = 493;

	private AudioClip rightS;
	private AudioClip wrongS;
	private MediaPlayer timeS;

	private String format = "%-20d\t%-20s\t%-20s\t%-20d%n";
	private String dataFile;
	private long qStartTime;

	/* For Thomas
	private boolean isPause; //for Thomas's version
    */
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\\
//~~~~~~BEGINNING OF FUNCTIONS~~~~~~\\
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\\


	//constructor
	public TestPageController() {

	    this.numCorrect = 0;
	    this.numWrong = 0;
	    this.trialCount = 0;
	    this.successiveAnswers = 0;
	    this.questionSpeedVar = 1500;
	    this.gettingTotalFeedback = false;
	    this.clickingAnswer = false;
	    this.isScanSig = false;
	    this.hasTimedOut = false;
	    // Thomas code this.isPause = false;

	}

	public void setMainApp(MainMISTApp mainApp){
		this.mainApp = mainApp;
	}

	public void setFilePath(){
	    this.dataFile = this.mainApp.getTestSettings().getSaveDir() + this.mainApp.getTestSettings().getID() + ".txt";
	}


	public void setOrderIndex(int index){
	    this.orderIndex = index;
	}


	// sets the question speed in the experimental condition
	public void setQuestionSpeed(double dur){

	    KeyValue barEnd = new KeyValue(this.barTimer.progressProperty(), 1);
	    KeyFrame barStart = new KeyFrame(Duration.ZERO, new KeyValue(this.barTimer.progressProperty(), 0));
	    KeyFrame barFrame = new KeyFrame(Duration.millis(dur), barEnd);

	    this.questionTimer = new Timeline();
	    this.questionTimer.getKeyFrames().addAll(barStart, barFrame);
	    this.questionTimer.setOnFinished(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {

                if(duringFirstQuestion){
                    duringFirstQuestion = false;
                }
                if(mainApp.getTestSettings().getNBack() > 0){
                    hasTimedOut = true;
                    nBackFeedback();
                }
                else{
                    hasTimedOut = true;
                    feedback();
                }

            }

	    });
	}


	// sets a faster/slower time depending on how many in a row correct were chosen
	public void setNewTime(){

	    if(this.successiveAnswers > 2 && this.questionSpeedVar > 0){
	         this.questionSpeedVar = this.questionSpeedVar - 500;
	    }
	    else if(this.successiveAnswers < -3 && this.questionSpeedVar < 5000){
	        this.questionSpeedVar = this.questionSpeedVar + 250;
	    }
	}


	//Main function of the controller, sets up the page according to
	//the condition.
	//Also keeps track of the condition order.
	public void setPage(){

	    //resets the test settings for the N-Back
	    this.oldAnswers = new ArrayList<Integer>();
	    this.numQuestions = 0;

	    if(this.gettingTotalFeedback){
	        return;
	    }
	    if(this.isScanSig){
	        return;
	    }

	    if(this.timerControl != null){
            this.timerControl.stop();
        }
	    if(this.timeS != null){
	        this.timeS.stop();
	    }
	    if(this.questionTimer != null){
	        this.questionTimer.stop();
	    }
	    resetButtons();
	    this.txtFeedback.clear();
	    this.txtQuestion.clear();
	    this.txtGo.clear();

	    //sets the sound settings (if chosen)
	    if(this.mainApp.getTestSettings().getIsSound()){

	        this.rightS = new AudioClip(new File("resources/sound/correct_answer_sound.wav").toURI().toString());
	        this.wrongS = new AudioClip(new File("resources/sound/wrong_answer_sound.wav").toURI().toString());
	        Media time = new Media(new File("resources/sound/time_lapsing_sound_long.wav").toURI().toString());
	        this.timeS = new MediaPlayer(time);

	    }
	    else{

            this.rightS = null;
            this.wrongS = null;
            this.timeS = null;

	    }

	     //!!!!!!!!!!!!!!!!!!!!!!!!!!\\
	    //SETS PAGE FOR REST CONDITION\\
	   //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\\
		if(this.mainApp.getTestMath().returnOrder(this.orderIndex).equals(condition.REST)){

		    this.txtGo.setVisible(false);
		    this.trialCount++;

		    try {
                FileWriter fileWriter = new FileWriter(this.dataFile,true);
                fileWriter.write(String.format(this.format,System.currentTimeMillis(),"New Condition","REST",null));
                fileWriter.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


		    if(this.mainApp.getTestSettings().getScanSig().equals(ScanSig.TIMED)){

		        /* Section for Thomas
		        PauseTransition wait = new PauseTransition(Duration.millis(this.mainApp.getTestSettings().getSigDuration() * 1000));
                wait.setOnFinished(new EventHandler<ActionEvent>(){

                    @Override
                    public void handle(ActionEvent event) {

                        isPause = false;
                        restPopUp();

                    }

                });
                */

		        KeyFrame testFrame = new KeyFrame(Duration.millis(this.mainApp.getTestSettings().getRestT() * 1000));
		        this.conditionTimer = new Timeline();
		        this.conditionTimer.getKeyFrames().add(testFrame);

		        this.conditionTimer.setOnFinished(new EventHandler<ActionEvent>(){

		            @Override
		            public void handle(ActionEvent event) {

		                popUpOrderIndex = orderIndex;

		                if(orderIndex < mainApp.getTestMath().returnOrderSize() - 1){
		                    orderIndex ++;
		                }
		                else{
		                    orderIndex = 0;
		                }

		                restPopUp();


		                /* section for Thomas
		                txtFeedback.clear();
		                txtQuestion.clear();
		                txtGo.clear();
		                isPause = true;
		                wait.play();
		                */

		            }

		        });

		        this.conditionTimer.playFromStart();

		    }
			this.mainApp.getRootLayout().setStyle("-fx-border-color: CHARTREUSE; -fx-border-style: SOLID; -fx-border-width: 10; -fx-border-radius: null;");

			//setBorder(new Border(new BorderStroke(Color.valueOf("CHARTRUESE"),new BorderStrokeStyle(StrokeType.valueOf("OUTSIDE"),StrokeLineJoin.valueOf("MITER"),StrokeLineCap.valueOf("SQUARE"),10,0,null),new CornerRadii(0),new BorderWidths(10))));
			this.barTimer.setVisible(false);
			this.lblYou.setVisible(false);
			this.lblThem.setVisible(false);
			this.arrowYou.setVisible(false);
			this.arrowThem.setVisible(false);
			this.testPane.requestFocus();

			this.inputHover = 100;


		}

		 //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\\
		//SETS PAGE FOR CONTROL CONDITION\\
	   //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\\
		else if(mainApp.getTestMath().returnOrder(this.orderIndex).equals(condition.CONTROL)){

		    try {
                FileWriter fileWriter = new FileWriter(this.dataFile,true);
                fileWriter.write(String.format(this.format,System.currentTimeMillis(),"New Condition","CONTROL",null));
                //System.out.format(this.format,System.currentTimeMillis(),"New Condition","CONTROL",null);
                fileWriter.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

		    //sets the txt Area for the nBack go information
	        if(this.mainApp.getTestSettings().getNBack() > 0){
	            this.txtGo.setVisible(true);
	        }
	        else{
	            this.txtGo.setVisible(false);
	        }

		    this.isFirstQuestion = true;
		    this.trialCount++;

		    if(this.mainApp.getTestSettings().getScanSig().equals(ScanSig.TIMED)){

		        /* Section for Thomas
		        PauseTransition wait = new PauseTransition(Duration.millis(this.mainApp.getTestSettings().getSigDuration() * 1000));
                wait.setOnFinished(new EventHandler<ActionEvent>(){

                    @Override
                    public void handle(ActionEvent event) {

                        isPause = false;
                        popUp();

                    }

                });
                */

                KeyFrame testFrame = new KeyFrame(Duration.millis(this.mainApp.getTestSettings().getConT() * 1000));
                this.conditionTimer = new Timeline();
                this.conditionTimer.getKeyFrames().add(testFrame);

                this.conditionTimer.setOnFinished(new EventHandler<ActionEvent>(){

                    public void handle(ActionEvent event) {

                        popUpOrderIndex = orderIndex;

                        if(orderIndex < mainApp.getTestMath().returnOrderSize() - 1){
                            orderIndex ++;
                        }
                        else{
                            orderIndex = 0;
                        }

                        if(timerControl != null){
                            timerControl.stop();
                        }

                        popUp();

                        /* for Thomas, remove popUp (plays above)
                        txtFeedback.clear();
                        txtQuestion.clear();
                        txtGo.clear();
                        isPause = true;
                        wait.play();
                        */

                    }

                });

                this.conditionTimer.playFromStart();
            }
			this.mainApp.getRootLayout().setStyle("-fx-border-color: YELLOW; -fx-border-style: SOLID; -fx-border-width: 10; -fx-border-radius: null;");

			//setBorder(new Border(new BorderStroke(Color.valueOf("YELLOW"),new BorderStrokeStyle(StrokeType.valueOf("OUTSIDE"),StrokeLineJoin.valueOf("MITER"),StrokeLineCap.valueOf("SQUARE"),10,0,null),new CornerRadii(0),new BorderWidths(10))));
			this.barTimer.setVisible(false);
			this.lblYou.setVisible(false);
            this.lblThem.setVisible(false);
            this.arrowYou.setVisible(false);
            this.arrowThem.setVisible(false);
            this.inputHover = 0;
            this.rec0.setFill(Color.rgb(redOn,greenOn,blueOn));

            this.numCorrect = 0;
            this.numWrong = 0;
            this.testPane.requestFocus();

            setNewQuestion(this.mainApp.getTestMath().getDiff());
            this.duringFirstQuestion = true;
		}


		 //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\\
		//SETS PAGE FOR EXPERIMENTAL CONDITION\\
	   //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\\
		else{

		    try {
                FileWriter fileWriter = new FileWriter(this.dataFile,true);
                fileWriter.write(String.format(this.format,System.currentTimeMillis(),"New Condition","EXPERIMENTAL",null));
                fileWriter.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
		    //sets the txt Area for the nBack go information
	        if(this.mainApp.getTestSettings().getNBack() > 0){
	            this.txtGo.setVisible(true);
	        }
	        else{
	            this.txtGo.setVisible(false);
	        }

		    //resets the position of the two arrows and labels for feedback
		    this.lblYou.setTranslateX(0);
		    this.arrowYou.setTranslateX(0);
		    this.lblYou.translateXProperty();
		    this.arrowYou.translateXProperty();

		    if(this.mainApp.getTestSettings().getIsStaticFeedback()){
		        this.xPosArrowThem = 628;
		        //reset lbl positions for repeated runs
		        this.lblThem.setTranslateX(0);
		        this.arrowThem.setTranslateX(0);
		        this.lblThem.translateXProperty();
		        this.arrowThem.translateXProperty();

		        //move to higher "average"
		        this.lblThem.setTranslateX(this.lblThem.getTranslateX() + 135);
                this.arrowThem.setTranslateX(this.arrowThem.getTranslateX() + 135);
                this.lblThem.translateXProperty();
                this.arrowThem.translateXProperty();

		    }
		    else{
		        this.lblThem.setTranslateX(0);
		        this.arrowThem.setTranslateX(0);
		        this.lblThem.translateXProperty();
		        this.arrowThem.translateXProperty();
		        this.xPosArrowThem = 493;
		    }

		    int topLength = this.mainApp.getTestSettings().getNameTop().length();
		    int bottomLength = this.mainApp.getTestSettings().getNameBottom().length();

		    //sets the font size for the labels based on the length of the names
		    if(bottomLength > 12){
		        this.lblYou.setFont(Font.font ("System", FontWeight.BOLD, 8));
		    }
		    else if(bottomLength > 9 && bottomLength <= 12){
		        this.lblYou.setFont(Font.font ("System", FontWeight.BOLD, 10));
		    }
		    else if(bottomLength > 7 && bottomLength <= 9){
		        this.lblYou.setFont(Font.font ("System", FontWeight.BOLD, 12));
		    }
		    else{
		        this.lblYou.setFont(Font.font ("System", FontWeight.BOLD, 15));
		    }

		    if(topLength > 12){
                this.lblThem.setFont(Font.font ("System", FontWeight.BOLD, 8));
            }
            else if(topLength > 9 && topLength <= 12){
                this.lblThem.setFont(Font.font ("System", FontWeight.BOLD, 10));
            }
            else if(topLength > 7 && topLength <= 9){
                this.lblThem.setFont(Font.font ("System", FontWeight.BOLD, 12));
            }
            else{
                this.lblThem.setFont(Font.font ("System", FontWeight.BOLD, 15));
            }

		    this.lblYou.setText(this.mainApp.getTestSettings().getNameBottom());
		    this.lblThem.setText(this.mainApp.getTestSettings().getNameTop());

		    this.xPosArrowYou = 493;



		    this.isFirstQuestion = true;
		    this.trialCount++;

		    if(this.mainApp.getTestSettings().getScanSig().equals(ScanSig.TIMED)){

		        /*Section for Thomas
		        PauseTransition wait = new PauseTransition(Duration.millis(this.mainApp.getTestSettings().getSigDuration() * 1000));
                wait.setOnFinished(new EventHandler<ActionEvent>(){

                    @Override
                    public void handle(ActionEvent event) {

                       isPause = false;
                       popUp();

                    }

                });
                */

                KeyFrame testFrame = new KeyFrame(Duration.millis(this.mainApp.getTestSettings().getExpT() * 1000));
                this.conditionTimer = new Timeline();
                this.conditionTimer.getKeyFrames().add(testFrame);

                this.conditionTimer.setOnFinished(new EventHandler<ActionEvent>(){

                    @Override
                    public void handle(ActionEvent event) {

                        popUpOrderIndex = orderIndex;

                        if(orderIndex < mainApp.getTestMath().returnOrderSize() - 1){
                            orderIndex ++;
                        }
                        else{
                            orderIndex = 0;
                        }

                       questionTimer.stop();
                       if(timeS != null){
                           timeS.stop();
                       }



                       popUp();


                       /* for Thomas, remove popUp(), plays above
                       txtFeedback.clear();
                       txtQuestion.clear();
                       txtGo.clear();
                       isPause = true;
                       wait.play();
                       */
                    }

                });

                this.conditionTimer.playFromStart();
            }

			this.mainApp.getRootLayout().setStyle( "-fx-border-color: RED; -fx-border-style: SOLID; -fx-border-width: 10; -fx-border-radius: null;");

			//setBorder(new Border(new BorderStroke(Color.valueOf("RED"),new BorderStrokeStyle(StrokeType.valueOf("OUTSIDE"),StrokeLineJoin.valueOf("MITER"),StrokeLineCap.valueOf("SQUARE"),10,0,null),new CornerRadii(0),new BorderWidths(10))));
			this.barTimer.setVisible(true);
			this.lblYou.setVisible(true);
            this.lblThem.setVisible(true);
            this.arrowYou.setVisible(true);
            this.arrowThem.setVisible(true);
            this.inputHover = 0;
            this.rec0.setFill(Color.rgb(redOn,greenOn,blueOn));

            this.numCorrect = 0;
            this.numWrong = 0;
            this.questionSpeedVar = 1500;
            this.testPane.requestFocus();

            setNewQuestion(this.mainApp.getTestMath().getDiff());
            this.duringFirstQuestion = true;


		}
	}


	//function that monitors if key is pressed
	@FXML
	public void onKeyTyped(KeyEvent ke){


	    if(!this.gettingFeedback /* **for thomas** && !this.isPause*/    ){
	    if(ke.getCharacter().toLowerCase().charAt(0) == mainApp.getTestSettings().getLeftKey()&&
                !this.mainApp.getTestMath().returnOrder(this.orderIndex).equals(condition.REST)){

	        moveLeft();
        }
        if(ke.getCharacter().toLowerCase().charAt(0) == mainApp.getTestSettings().getRightKey()&&
                !this.mainApp.getTestMath().returnOrder(this.orderIndex).equals(condition.REST)){

            moveRight();
        }
        if(ke.getCharacter().toLowerCase().charAt(0) == mainApp.getTestSettings().getConfKey() &&
                !this.mainApp.getTestMath().returnOrder(this.orderIndex).equals(condition.REST)){

            confirm();
        }
	    }
        if(this.mainApp.getTestSettings().getScanSig().equals(ScanSig.KEYBOARD) && ke.getCharacter().toLowerCase().charAt(0) == this.mainApp.getTestSettings().getScanKey()){

            scanKeyPress();
        }


	}

	//function that monitors if the mouse has been clicked at all
	@FXML
	public void onMouseClicked(MouseEvent event){

	    if(mainApp.getTestSettings().getScanSig().equals(ScanSig.MOUSE)
	            && !this.clickingAnswer){
	        scanKeyPress();
	    }


	}


	//function for moving dial left
	private void moveLeft(){


	    switch(this.inputHover){

        case 0:
           this.rec0.setFill(Color.rgb(redOff,greenOff,blueOff));
           this.rec9.setFill(Color.rgb(redOn,greenOn,blueOn));
           this.inputHover = 9;
           break;

        case 1:
            this.rec1.setFill(Color.rgb(redOff,greenOff,blueOff));
            this.rec0.setFill(Color.rgb(redOn,greenOn,blueOn));
            this.inputHover--;
            break;

        case 2:
            this.rec2.setFill(Color.rgb(redOff,greenOff,blueOff));
            this.rec1.setFill(Color.rgb(redOn,greenOn,blueOn));
            this.inputHover--;
            break;

        case 3:
            this.rec3.setFill(Color.rgb(redOff,greenOff,blueOff));
            this.rec2.setFill(Color.rgb(redOn,greenOn,blueOn));
            this.inputHover--;
            break;

        case 4:
            this.rec4.setFill(Color.rgb(redOff,greenOff,blueOff));
            this.rec3.setFill(Color.rgb(redOn,greenOn,blueOn));
            this.inputHover--;
            break;

        case 5:
            this.rec5.setFill(Color.rgb(redOff,greenOff,blueOff));
            this.rec4.setFill(Color.rgb(redOn,greenOn,blueOn));
            this.inputHover--;
            break;

        case 6:
            this.rec6.setFill(Color.rgb(redOff,greenOff,blueOff));
            this.rec5.setFill(Color.rgb(redOn,greenOn,blueOn));
            this.inputHover--;
            break;

        case 7:
            this.rec7.setFill(Color.rgb(redOff,greenOff,blueOff));
            this.rec6.setFill(Color.rgb(redOn,greenOn,blueOn));
            this.inputHover--;
            break;

        case 8:
            this.rec8.setFill(Color.rgb(redOff,greenOff,blueOff));
            this.rec7.setFill(Color.rgb(redOn,greenOn,blueOn));
            this.inputHover--;
            break;

        case 9:
            this.rec9.setFill(Color.rgb(redOff,greenOff,blueOff));
            this.rec8.setFill(Color.rgb(redOn,greenOn,blueOn));
            this.inputHover--;
            break;

        default:
            break;

	    }
	}

	//function for moving dial right
	private void moveRight(){


	    switch(this.inputHover){

	        case 0:
	           this.rec0.setFill(Color.rgb(redOff,greenOff,blueOff));
	           this.rec1.setFill(Color.rgb(redOn,greenOn,blueOn));
	           this.inputHover++;
	           break;

	        case 1:
	            this.rec1.setFill(Color.rgb(redOff,greenOff,blueOff));
	            this.rec2.setFill(Color.rgb(redOn,greenOn,blueOn));
	            this.inputHover++;
	            break;

	        case 2:
	            this.rec2.setFill(Color.rgb(redOff,greenOff,blueOff));
	            this.rec3.setFill(Color.rgb(redOn,greenOn,blueOn));
	            this.inputHover++;
	            break;

	        case 3:
	            this.rec3.setFill(Color.rgb(redOff,greenOff,blueOff));
	            this.rec4.setFill(Color.rgb(redOn,greenOn,blueOn));
	            this.inputHover++;
	            break;

	        case 4:
	            this.rec4.setFill(Color.rgb(redOff,greenOff,blueOff));
	            this.rec5.setFill(Color.rgb(redOn,greenOn,blueOn));
	            this.inputHover++;
	            break;

	        case 5:
	            this.rec5.setFill(Color.rgb(redOff,greenOff,blueOff));
	            this.rec6.setFill(Color.rgb(redOn,greenOn,blueOn));
	            this.inputHover++;
	            break;

	        case 6:
	            this.rec6.setFill(Color.rgb(redOff,greenOff,blueOff));
	            this.rec7.setFill(Color.rgb(redOn,greenOn,blueOn));
	            this.inputHover++;
	            break;

	        case 7:
	            this.rec7.setFill(Color.rgb(redOff,greenOff,blueOff));
	            this.rec8.setFill(Color.rgb(redOn,greenOn,blueOn));
	            this.inputHover++;
	            break;

	        case 8:
	            this.rec8.setFill(Color.rgb(redOff,greenOff,blueOff));
	            this.rec9.setFill(Color.rgb(redOn,greenOn,blueOn));
	            this.inputHover++;
	            break;

	        case 9:
	            this.rec9.setFill(Color.rgb(redOff,greenOff,blueOff));
	            this.rec0.setFill(Color.rgb(redOn,greenOn,blueOn));
	            this.inputHover = 0;
	            break;

	        default:
	            break;

	    }

	}

	//function for confirming entry
	private void confirm(){

	    if(this.duringFirstQuestion){
	        this.duringFirstQuestion = false;
	    }

	    if(this.mainApp.getTestSettings().getNBack() > 0
                && this.numQuestions <= this.mainApp.getTestSettings().getNBack()){
            return;
        }

	    if(this.mainApp.getTestSettings().getNBack() > 0
	            && this.numQuestions > this.mainApp.getTestSettings().getNBack()){
	        this.chosenAnswer = this.inputHover;
	        nBackFeedback();
	    }
	    else{
	        this.chosenAnswer = this.inputHover;
	        feedback();
	    }

	}


	//function for scan signal pressed (can be mouse or keyboard)
	private void scanKeyPress(){

	    this.isScanSig = true;
	    this.scannerCount ++;

	    try {
            FileWriter fileWriter = new FileWriter(this.dataFile,true);
            fileWriter.write(String.format(this.format,System.currentTimeMillis(),"Scanner Signal",
                    mainApp.getTestSettings().getScanSig() + ", Count = " +this.scannerCount,null));
            fileWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

	    switch(mainApp.getTestMath().returnOrder(this.orderIndex)){

	    case REST:
	        if(this.scannerCount == mainApp.getTestSettings().getRestT()){

	            this.scannerCount = 0;
	            popUpOrderIndex = orderIndex;

	          //moves the index forward to the next place in the order ArrayList
	            if(this.orderIndex < mainApp.getTestMath().returnOrderSize() - 1){
	                this.orderIndex ++;
	            }
	            else{
	                this.orderIndex = 0;
	            }

	            /* for thomas
	            this.isPause = true;



	            /* code for Thomas
	            PauseTransition wait = new PauseTransition(Duration.millis(this.mainApp.getTestSettings().getSigDuration() * 1000));
	            wait.setOnFinished(new EventHandler<ActionEvent>(){

	                @Override
	                public void handle(ActionEvent event) {

	                    isPause = false;
	                    restPopUp();

	                }

	            });

	            wait.play();

	             */

	            //get rid of this section for Thomas
	            KeyFrame sigDurFrame = new KeyFrame(Duration.millis(this.mainApp.getTestSettings().getSigDuration() * 1000));
                Timeline lastScanSigTimer = new Timeline();
                lastScanSigTimer.getKeyFrames().add(sigDurFrame);

                lastScanSigTimer.setOnFinished(new EventHandler<ActionEvent>(){

                    @Override
                    public void handle(ActionEvent event) {

                        restPopUp();

                    }

                });

                lastScanSigTimer.playFromStart();

	            break;

	        }
	    case CONTROL:
	        if(this.scannerCount == mainApp.getTestSettings().getConT()){

	            this.scannerCount = 0;
	            popUpOrderIndex = orderIndex;

	          //moves the index forward to the next place in the order ArrayList
	            if(this.orderIndex < mainApp.getTestMath().returnOrderSize() - 1){
	                this.orderIndex ++;
	            }
	            else{
	                this.orderIndex = 0;
	            }


	            /* For Thomas
	            if(timerControl != null){
                    timerControl.stop();
                }

	            txtFeedback.clear();
                txtQuestion.clear();
                txtGo.clear();
                this.isPause = true;

	            PauseTransition wait = new PauseTransition(Duration.millis(this.mainApp.getTestSettings().getSigDuration() * 1000));
                wait.setOnFinished(new EventHandler<ActionEvent>(){

                    @Override
                    public void handle(ActionEvent event) {

                        isPause = false;
                        popUp();

                    }

                });

                wait.play();

                */

	            KeyFrame sigDurFrame = new KeyFrame(Duration.millis(this.mainApp.getTestSettings().getSigDuration() * 1000));
                Timeline lastScanSigTimer = new Timeline();
                lastScanSigTimer.getKeyFrames().add(sigDurFrame);

                lastScanSigTimer.setOnFinished(new EventHandler<ActionEvent>(){

                    @Override
                    public void handle(ActionEvent event) {

                        if(timerControl != null){
                            timerControl.stop();
                        }

                        popUp();

                    }

                });

                lastScanSigTimer.playFromStart();

	            break;
	        }

	    case EXPERIMENTAL:
	        if(this.scannerCount == mainApp.getTestSettings().getExpT()){

	            this.scannerCount = 0;
	            popUpOrderIndex = orderIndex;

	          //moves the index forward to the next place in the order ArrayList
	            if(this.orderIndex < mainApp.getTestMath().returnOrderSize() - 1){
	                this.orderIndex ++;
	            }
	            else{
	                this.orderIndex = 0;
	            }


	            /* For Thomas
	            if(questionTimer != null){
                    questionTimer.stop();
                }

                if(timeS != null){
                    timeS.stop();
                }

	            txtFeedback.clear();
                txtQuestion.clear();
                txtGo.clear();
                this.isPause = true;

	            PauseTransition wait = new PauseTransition(Duration.millis(this.mainApp.getTestSettings().getSigDuration() * 1000));
                wait.setOnFinished(new EventHandler<ActionEvent>(){

                    @Override
                    public void handle(ActionEvent event) {

                        isPause = false;
                        popUp();

                    }

                });

                wait.play();
                */


	            KeyFrame sigDurFrame = new KeyFrame(Duration.millis(this.mainApp.getTestSettings().getSigDuration() * 1000));
                Timeline lastScanSigTimer = new Timeline();
                lastScanSigTimer.getKeyFrames().add(sigDurFrame);

                lastScanSigTimer.setOnFinished(new EventHandler<ActionEvent>(){

                    @Override
                    public void handle(ActionEvent event) {

                        if(questionTimer != null){
                            questionTimer.stop();
                        }

                        if(timeS != null){
                            timeS.stop();
                        }
                        popUp();

                    }

                });

                lastScanSigTimer.playFromStart();

	            break;
	        }

	    }
	    this.isScanSig = false;
	}

	//function that creates the new math questions that are displayed
	private void setNewQuestion(int difficulty){

	    if(this.gettingTotalFeedback){
            return;
        }
        if(this.isScanSig){
            return;
        }
        if(this.mainApp.getTestMath().returnOrder(this.orderIndex).equals(condition.REST)){
            return;
        }

        /*for Thomas
        if(this.isPause){
            return;
        }
        */

        this.testPane.requestFocus();
        this.chosenAnswer = 1000;
        this.numQuestions++;

        TestQuestion testQuestion = new TestQuestion(difficulty,mainApp.getTestMath().getULimit());
        this.currentAnswer = testQuestion.getAnswer();
        this.txtQuestion.clear();
        this.qStartTime = System.currentTimeMillis();

        //sets the question box with the question text (changes size depending on length
        if(testQuestion.getBaseTime() == 5800){
            this.txtQuestion.setFont(Font.font ("System", FontWeight.BOLD, 40));
            this.txtQuestion.setText(testQuestion.getQuestion());
        }
        else if(testQuestion.getBaseTime() == 4300 || testQuestion.getBaseTime() == 3300){
            this.txtQuestion.setFont(Font.font ("System", FontWeight.BOLD, 48));
            this.txtQuestion.setText(testQuestion.getQuestion());
        }
        else{
            this.txtQuestion.setFont(Font.font ("System", FontWeight.BOLD, 55));
            this.txtQuestion.setText(testQuestion.getQuestion());
        }

      //!!!!********!!!!\\
     //!!!!!********!!!!!\\

        //IF CONTROL CONDITION
        if(this.mainApp.getTestMath().returnOrder(this.orderIndex).equals(condition.CONTROL)){

            //checks for N-Back
            //IF N-Back
            if(this.mainApp.getTestSettings().getNBack() > 0){

                try {
                    FileWriter fileWriter = new FileWriter(this.dataFile,true);
                    fileWriter.write(String.format(this.format,this.qStartTime,
                            "New Question","Lvl " + testQuestion.getDiffLevel() + " - N-Back = " +
                            this.mainApp.getTestSettings().getNBack(),null));
                    fileWriter.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                this.oldAnswers.add(this.currentAnswer);

                //checks if question number is greater than the N
                //IF Q > N
                if(this.numQuestions > this.mainApp.getTestSettings().getNBack()){

                    this.txtGo.setText("Answer Now");

                    //checks for timeout
                    //IF timeout
                    if(this.mainApp.getTestSettings().getIsTimeout()){

                        KeyFrame questionFrame = new KeyFrame(Duration.millis(this.mainApp.getTestSettings().getConTimeout() * 1000));
                        this.timerControl = new Timeline();
                        this.timerControl.getKeyFrames().add(questionFrame);
                        this.timerControl.setOnFinished(new EventHandler<ActionEvent>(){

                            @Override
                            public void handle(ActionEvent event){
                                if(duringFirstQuestion = true){
                                    duringFirstQuestion = false;
                                }
                                hasTimedOut = true;
                                nBackFeedback();
                            }
                        });

                        this.timerControl.playFromStart();
                    }
                    //ELSE no timeout
                }

                //ELSE Q <= N
                else{

                    this.txtGo.setText("Do Not Answer Yet!");

                    //checks if timeout is enabled
                    //IF timeout
                    if(this.mainApp.getTestSettings().getIsTimeout()){

                        KeyFrame questionFrame = new KeyFrame(Duration.millis(this.mainApp.getTestSettings().getConTimeout() * 1000));
                        this.timerControl = new Timeline();
                        this.timerControl.getKeyFrames().add(questionFrame);
                        this.timerControl.setOnFinished(new EventHandler<ActionEvent>(){

                            @Override
                            public void handle(ActionEvent event){
                                if(duringFirstQuestion){
                                    duringFirstQuestion = false;
                                }
                                //maybe don't need as it is supposed to time out
                                hasTimedOut = true;
                                nBackFeedback();
                            }
                        });

                        this.timerControl.playFromStart();

                    }

                    //ELSE no timeout
                    else{

                        //need to set a timeout for questions that can't be answered
                        KeyFrame questionFrame = new KeyFrame(Duration.millis(8000));
                        Timeline nBackTimer = new Timeline();
                        nBackTimer.getKeyFrames().add(questionFrame);
                        nBackTimer.setOnFinished(new EventHandler<ActionEvent>(){

                            @Override
                            public void handle(ActionEvent event){

                                if(duringFirstQuestion){
                                    duringFirstQuestion = false;
                                }

                                    nBackFeedback();
                            }
                        });

                        nBackTimer.playFromStart();
                    }
                }
            }

            //ELSE not N-Back
            else{

                try {
                    FileWriter fileWriter = new FileWriter(this.dataFile,true);
                    fileWriter.write(String.format(this.format,this.qStartTime,
                            "New Question","Lvl " + testQuestion.getDiffLevel(),null));
                    fileWriter.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                //checks for timeout
                //IF timeout enabled
                if(this.mainApp.getTestSettings().getIsTimeout()){

                    KeyFrame questionFrame = new KeyFrame(Duration.millis(this.mainApp.getTestSettings().getConTimeout() * 1000));
                    this.timerControl = new Timeline();
                    this.timerControl.getKeyFrames().add(questionFrame);
                    this.timerControl.setOnFinished(new EventHandler<ActionEvent>(){

                        @Override
                        public void handle(ActionEvent event){
                            if(duringFirstQuestion){
                                duringFirstQuestion = false;
                            }

                            hasTimedOut = true;
                            feedback();
                        }
                    });

                    this.timerControl.playFromStart();
                }

                //ELSE timeout not enabled
                else{

                }


            }
        }

        //!!!!********!!!!\\
       //!!!!!********!!!!!\\

        //ELSE EXPERIMENTAL CONDITION
        else{

            //checks for N-back
            if(this.mainApp.getTestSettings().getNBack() > 0){

                try {
                    FileWriter fileWriter = new FileWriter(this.dataFile,true);
                    fileWriter.write(String.format(this.format,this.qStartTime,
                            "New Question","Lvl " + testQuestion.getDiffLevel() + " - N-Back = " +
                            this.mainApp.getTestSettings().getNBack(),null));
                    fileWriter.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                this.oldAnswers.add(this.currentAnswer);

                //checks if past the N-Back point
                //IF Q > N
                if(this.numQuestions > this.mainApp.getTestSettings().getNBack()){
                    this.txtGo.setText("Answer Now");
                }

                //ELSE Q <= N
                else{
                    this.txtGo.setText("Do Not Answer Yet!");
                }

                //sets the question (same as below)
                if(!this.isFirstQuestion){

                    this.questionTimer.stop();
                    setQuestionSpeed(testQuestion.getBaseTime() + this.questionSpeedVar);
                    this.barTimer.setProgress(0);
                    this.questionTimer.playFromStart();

                    if(this.timeS != null){
                        this.timeS.play();
                    }
                }
                else{

                    setQuestionSpeed(testQuestion.getBaseTime() + this.questionSpeedVar);
                    this.barTimer.setProgress(0);
                    this.questionTimer.playFromStart();
                    this.isFirstQuestion = false;

                    if(this.timeS != null){
                        this.timeS.play();
                    }

                }



            }


            //ELSE not N-back
            else{

                try {
                    FileWriter fileWriter = new FileWriter(this.dataFile,true);
                    fileWriter.write(String.format(this.format,this.qStartTime,
                            "New Question","Lvl " + testQuestion.getDiffLevel(),null));
                    fileWriter.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                //sets the question (same as ABOVE)
                if(!this.isFirstQuestion){

                    this.questionTimer.stop();
                    setQuestionSpeed(testQuestion.getBaseTime() + this.questionSpeedVar);
                    this.barTimer.setProgress(0);
                    this.questionTimer.playFromStart();

                    if(this.timeS != null){
                        this.timeS.play();
                    }
                }
                else{

                    setQuestionSpeed(testQuestion.getBaseTime() + this.questionSpeedVar);
                    this.barTimer.setProgress(0);
                    this.questionTimer.playFromStart();
                    this.isFirstQuestion = false;

                    if(this.timeS != null){
                        this.timeS.play();
                    }

                }
            }

        }

        this.gettingFeedback = false;
	}



	//function that provides feedback to the user
	//additionally, continues the logic forward to
	//set-up new questions
	private void feedback(){

	    this.gettingFeedback = true;

	    if(this.timeS != null){
	        this.timeS.stop();
	    }
	    if(this.timerControl != null){
	        this.timerControl.stop();
	    }
	    if(this.questionTimer != null){
	        this.questionTimer.stop();
	    }


	    if(this.chosenAnswer.equals(this.currentAnswer)){

	        try {
                FileWriter fileWriter = new FileWriter(this.dataFile,true);
                fileWriter.write(String.format(this.format,System.currentTimeMillis(),
                        "Response","CORRECT",System.currentTimeMillis() - this.qStartTime));
                fileWriter.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

	       //if block that adjusts the successive answers based on last answer
	       if(this.successiveAnswers >= 0){
	           this.successiveAnswers++;
	       }
	       else{
	           this.successiveAnswers = 1;
	       }
	        this.numCorrect++;

	        if(this.rightS != null){
                this.rightS.play();
            }
	        this.txtFeedback.setText(this.mainApp.getTestSettings().getCorrect());

	        if(this.xPosArrowYou < 763){
	            this.arrowYou.setTranslateX(this.arrowYou.getTranslateX()+25);
	            this.lblYou.setTranslateX(this.lblYou.getTranslateX()+25);
	            this.arrowYou.translateXProperty();
	            this.lblYou.translateXProperty();
	            this.xPosArrowYou += 25;
	        }
	        if(!this.mainApp.getTestSettings().getIsStaticFeedback()){
	            opponentFeedback();
	        }

	    }
	    else{

	        try {
                FileWriter fileWriter = new FileWriter(this.dataFile,true);
                if(!this.hasTimedOut){
                    fileWriter.write(String.format(this.format,System.currentTimeMillis(),
                        "Response","WRONG",System.currentTimeMillis() - this.qStartTime));
                    fileWriter.close();
                }

                else{
                    fileWriter.write(String.format(this.format,System.currentTimeMillis(),
                            "Response","TIMEOUT",System.currentTimeMillis() - this.qStartTime));
                    fileWriter.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
	        //if block that adjusts the successive answers based on last answer
	           if(this.successiveAnswers <= 0){
	               this.successiveAnswers--;
	           }
	           else{
	               this.successiveAnswers = -1;
	           }


	        if(this.wrongS != null){
	            this.wrongS.play();
	        }

	        if(!this.hasTimedOut &&
	                this.mainApp.getTestMath().returnOrder(orderIndex).equals(condition.CONTROL)){
	            this.txtFeedback.setText(this.mainApp.getTestSettings().getNCorrect());
	            this.numWrong++;
	        }
	        else if(this.hasTimedOut &&
	                this.mainApp.getTestMath().returnOrder(orderIndex).equals(condition.CONTROL)){
	            this.txtFeedback.setText("TIMEOUT");
	            this.numTime++;
	            this.hasTimedOut = false;
	        }
	        else{
	            this.txtFeedback.setText(this.mainApp.getTestSettings().getNCorrect());
	            this.numWrong++;
	            this.hasTimedOut = false;
	        }

	        if (this.xPosArrowYou > 103){

	            this.arrowYou.setTranslateX(this.arrowYou.getTranslateX()-25);
	            this.lblYou.setTranslateX(this.lblYou.getTranslateX()-25);
	            this.arrowYou.translateXProperty();
	            this.lblYou.translateXProperty();
	            this.xPosArrowYou -= 25;
	        }
	        if(!this.mainApp.getTestSettings().getIsStaticFeedback()){
                opponentFeedback();
            }
	    }

	    if(this.mainApp.getTestMath().returnOrder(orderIndex).equals(condition.CONTROL)){
	        this.txtFeedback.appendText("\n" + this.mainApp.getTestSettings().getNRecord());

	    }
	    else{
	        this.txtFeedback.appendText("\n" + this.mainApp.getTestSettings().getRecord());
	        if(this.questionTimer != null){
	            this.questionTimer.pause();
	        }
	        setNewTime();
	        setNewDifficulty();

	    }


	    PauseTransition wait = new PauseTransition(Duration.millis(3000));
	    wait.setOnFinished(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {

                txtFeedback.clear();
                //txtQuestion.clear();
                if(duringFirstQuestion){
                    duringFirstQuestion = false;
                }
                else{
                    setNewQuestion(mainApp.getTestMath().getDiff());
                }

            }

        });

	    wait.play();
	}

	//function that provides feedback like the
	//above except for the case where the n-back
	//number is greater than zero
	private void nBackFeedback(){

	    this.gettingFeedback = true;

        if(this.timeS != null){
            timeS.stop();
        }
        if(this.questionTimer != null){
            this.questionTimer.stop();
        }

        if(this.timerControl != null){
            this.timerControl.stop();
        }

        if(this.numQuestions > this.mainApp.getTestSettings().getNBack()){

            if(this.chosenAnswer.equals(this.oldAnswers.get(
                this.numQuestions - this.mainApp.getTestSettings().getNBack() - 1))){

                try {
                    FileWriter fileWriter = new FileWriter(this.dataFile,true);
                    fileWriter.write(String.format(this.format,System.currentTimeMillis(),
                            "Response","CORRECT",System.currentTimeMillis() - this.qStartTime));
                    fileWriter.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

              //if block that adjusts the successive answers based on last answer
                if(this.successiveAnswers >= 0){
                    this.successiveAnswers++;
                }
                else{
                    this.successiveAnswers = 1;
                }
                 this.numCorrect++;

                 if(this.rightS != null){
                     this.rightS.play();
                 }
                 this.txtFeedback.setText(this.mainApp.getTestSettings().getCorrect());

                 if(this.xPosArrowYou < 763){
                     this.arrowYou.setTranslateX(this.arrowYou.getTranslateX()+25);
                     this.lblYou.setTranslateX(this.lblYou.getTranslateX()+25);
                     this.arrowYou.translateXProperty();
                     this.lblYou.translateXProperty();
                     this.xPosArrowYou += 25;
                 }
                 if(!this.mainApp.getTestSettings().getIsStaticFeedback()){
                     opponentFeedback();
                 }
            }

            else{

                try {
                    FileWriter fileWriter = new FileWriter(this.dataFile,true);
                    if(!this.hasTimedOut){
                        fileWriter.write(String.format(this.format,System.currentTimeMillis(),
                            "Response","WRONG",System.currentTimeMillis() - this.qStartTime));
                        fileWriter.close();
                    }

                    else{
                        fileWriter.write(String.format(this.format,System.currentTimeMillis(),
                                "Response","TIMEOUT",System.currentTimeMillis() - this.qStartTime));
                        fileWriter.close();
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                if(this.successiveAnswers <= 0){
                    this.successiveAnswers--;
                }
                else{
                    this.successiveAnswers = -1;
                }

                if(!this.hasTimedOut){
                    this.txtFeedback.setText(this.mainApp.getTestSettings().getNCorrect());
                    this.numWrong++;
                }
                else if(this.hasTimedOut &&
                        this.mainApp.getTestMath().returnOrder(orderIndex).equals(condition.CONTROL)){
                    this.txtFeedback.setText("TIMEOUT");
                    this.numTime++;
                    this.hasTimedOut = false;
                }
                else{
                    this.txtFeedback.setText(this.mainApp.getTestSettings().getNCorrect());
                    this.numWrong++;
                    this.hasTimedOut = false;
                }

             if(this.wrongS != null){
                 this.wrongS.play();
             }

             //add if statement checks

             if (this.xPosArrowYou > 103){

                 this.arrowYou.setTranslateX(this.arrowYou.getTranslateX()-25);
                 this.lblYou.setTranslateX(this.lblYou.getTranslateX()-25);
                 this.arrowYou.translateXProperty();
                 this.lblYou.translateXProperty();
                 this.xPosArrowYou -= 25;
             }
             if(!this.mainApp.getTestSettings().getIsStaticFeedback()){
                 opponentFeedback();
             }
            }

            if(this.mainApp.getTestMath().returnOrder(orderIndex).equals(condition.CONTROL)){
                this.txtFeedback.appendText("\n" + this.mainApp.getTestSettings().getNRecord());

            }
            else{
                this.txtFeedback.appendText("\n" + this.mainApp.getTestSettings().getRecord());
                this.questionTimer.pause();
                setNewTime();
                setNewDifficulty();
            }
        }

        //N-Back before N is reached
        else{
            this.txtFeedback.setText("\n" + this.mainApp.getTestSettings().getNRecord());
        }


        PauseTransition wait = new PauseTransition(Duration.millis(3000));
        wait.setOnFinished(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {

                txtFeedback.clear();
                //txtQuestion.clear();
                if(duringFirstQuestion){
                    duringFirstQuestion = false;
                }
                else{
                    setNewQuestion(mainApp.getTestMath().getDiff());
                }

            }

        });

        wait.play();

	}


	//changes the difficulty level based on the number of
	//correct/incorrect answers in a row
	private void setNewDifficulty(){

	    if(this.successiveAnswers > 2){

	        this.mainApp.getTestMath().increaseULimit();

	    }

	    else if(this.successiveAnswers < -3){

	            this.mainApp.getTestMath().decreaseULimit();
	    }

	}


	//function that moves the opponent/average arrow
	private void opponentFeedback(){

	    if(this.xPosArrowThem == 493){

            int change = ThreadLocalRandom.current().nextInt(2);

            switch(change){

            case 0:
                this.arrowThem.setTranslateX(this.arrowThem.getTranslateX()+25);
                this.lblThem.setTranslateX(this.lblThem.getTranslateX()+25);
                this.arrowThem.translateXProperty();
                this.lblThem.translateXProperty();
                this.xPosArrowThem += 25;
                break;
            case 1:
                this.arrowThem.setTranslateX(this.arrowThem.getTranslateX()+25);
                this.lblThem.setTranslateX(this.lblThem.getTranslateX()+25);
                this.arrowThem.translateXProperty();
                this.lblThem.translateXProperty();
                this.xPosArrowThem += 25;
                break;
            case 2:
                break;
            }
	    }
	    else if(this.xPosArrowThem < 613){

	        int change = ThreadLocalRandom.current().nextInt(6);

	        switch(change){

	        case 0:
	            this.arrowThem.setTranslateX(this.arrowThem.getTranslateX()+25);
	            this.lblThem.setTranslateX(this.lblThem.getTranslateX()+25);
	            this.arrowThem.translateXProperty();
	            this.lblThem.translateXProperty();
	            this.xPosArrowThem += 25;
	            break;
	        case 1:
	            this.arrowThem.setTranslateX(this.arrowThem.getTranslateX()+25);
                this.lblThem.setTranslateX(this.lblThem.getTranslateX()+25);
                this.arrowThem.translateXProperty();
                this.lblThem.translateXProperty();
                this.xPosArrowThem += 25;
                break;
	        case 2:
	            this.arrowThem.setTranslateX(this.arrowThem.getTranslateX()+25);
                this.lblThem.setTranslateX(this.lblThem.getTranslateX()+25);
                this.arrowThem.translateXProperty();
                this.lblThem.translateXProperty();
                this.xPosArrowThem += 25;
                break;
	        case 3:
	            this.arrowThem.setTranslateX(this.arrowThem.getTranslateX()-25);
                this.lblThem.setTranslateX(this.lblThem.getTranslateX()-25);
                this.arrowThem.translateXProperty();
                this.lblThem.translateXProperty();
                this.xPosArrowThem -= 25;
                break;
	        case 4:
	            break;
	        case 5:
	            this.arrowThem.setTranslateX(this.arrowThem.getTranslateX()+25);
                this.lblThem.setTranslateX(this.lblThem.getTranslateX()+25);
                this.arrowThem.translateXProperty();
                this.lblThem.translateXProperty();
                this.xPosArrowThem += 25;
                break;
	        case 6:
	            this.arrowThem.setTranslateX(this.arrowThem.getTranslateX()-25);
                this.lblThem.setTranslateX(this.lblThem.getTranslateX()-25);
                this.arrowThem.translateXProperty();
                this.lblThem.translateXProperty();
                this.xPosArrowThem -= 25;
                break;
	        }

	    }
	    else if(this.xPosArrowThem >= 613 && this.xPosArrowThem < 718){

            int change = ThreadLocalRandom.current().nextInt(2);

            switch(change){

            case 0:
                this.arrowThem.setTranslateX(this.arrowThem.getTranslateX()+25);
                this.lblThem.setTranslateX(this.lblThem.getTranslateX()+25);
                this.arrowThem.translateXProperty();
                this.lblThem.translateXProperty();
                this.xPosArrowThem += 25;
                break;
            case 1:
                this.arrowThem.setTranslateX(this.arrowThem.getTranslateX()-25);
                this.lblThem.setTranslateX(this.lblThem.getTranslateX()-25);
                this.arrowThem.translateXProperty();
                this.lblThem.translateXProperty();
                this.xPosArrowThem -= 25;
                break;
            case 2:
                break;
            }
	    }
	    else if(this.xPosArrowThem >= 718 && this.xPosArrowThem < 763){

            int change = ThreadLocalRandom.current().nextInt(3);

            switch(change){
            case 1:
                this.arrowThem.setTranslateX(this.arrowThem.getTranslateX()-25);
                this.lblThem.setTranslateX(this.lblThem.getTranslateX()-25);
                this.arrowThem.translateXProperty();
                this.lblThem.translateXProperty();
                this.xPosArrowThem -= 25;
                break;
            case 2:
                this.arrowThem.setTranslateX(this.arrowThem.getTranslateX()-25);
                this.lblThem.setTranslateX(this.lblThem.getTranslateX()-25);
                this.arrowThem.translateXProperty();
                this.lblThem.translateXProperty();
                this.xPosArrowThem -= 25;
                break;
            case 3:
                break;
            case 4:
                this.arrowThem.setTranslateX(this.arrowThem.getTranslateX()+25);
                this.lblThem.setTranslateX(this.lblThem.getTranslateX()+25);
                this.arrowThem.translateXProperty();
                this.lblThem.translateXProperty();
                this.xPosArrowThem += 25;
                break;
            }
	    }
	    //this means the arrow is at its limit
	    else{

            int change = ThreadLocalRandom.current().nextInt(1);

            switch(change){
            case 0:
                this.arrowThem.setTranslateX(this.arrowThem.getTranslateX()-25);
                this.lblThem.setTranslateX(this.lblThem.getTranslateX()-25);
                this.arrowThem.translateXProperty();
                this.lblThem.translateXProperty();
                this.xPosArrowThem -= 25;
                break;
            case 1:
                break;
            }
	    }

	}


	//private function that resets all of the input buttons to unselected
	private void resetButtons(){
	    this.rec0.setFill(Color.rgb(redOff,greenOff,blueOff));
	    this.rec1.setFill(Color.rgb(redOff,greenOff,blueOff));
	    this.rec2.setFill(Color.rgb(redOff,greenOff,blueOff));
	    this.rec3.setFill(Color.rgb(redOff,greenOff,blueOff));
	    this.rec4.setFill(Color.rgb(redOff,greenOff,blueOff));
	    this.rec5.setFill(Color.rgb(redOff,greenOff,blueOff));
	    this.rec6.setFill(Color.rgb(redOff,greenOff,blueOff));
	    this.rec7.setFill(Color.rgb(redOff,greenOff,blueOff));
	    this.rec8.setFill(Color.rgb(redOff,greenOff,blueOff));
	    this.rec9.setFill(Color.rgb(redOff,greenOff,blueOff));
	}


	//function that provides the pop-Up menu for when a condition is over
	private void popUp(){


	    if(this.timeS != null){
            this.timeS.stop();
        }
        if(this.timerControl != null){
            this.timerControl.stop();
        }

        this.gettingTotalFeedback = true;

	    //section for if Pop-Up is enabled
	    if(this.mainApp.getTestSettings().getIsPopUp()){


        Alert alert = new Alert(AlertType.INFORMATION);
        if(this.mainApp.getTestMath().returnOrder(this.popUpOrderIndex).equals(condition.CONTROL) &&
                this.mainApp.getTestSettings().getIsTimeout()){

            alert.setTitle("Feedback");
            alert.setHeaderText(null);
            alert.setContentText("Number of correct answers: " + numCorrect +
                    "\nNumber of timeouts: " + numTime +
                    "\nNumber of incorrect answers: " + numWrong );
        }
        else{
            alert.setTitle("Feedback");
            alert.setHeaderText(null);
            alert.setContentText("Number of correct answers: " + numCorrect +
                    "\nNumber of incorrect answers: " + numWrong );
        }



        if(trialCount >= mainApp.getTestMath().getNumberOfTrials()){
            ButtonType next = new ButtonType("CONTINUE", ButtonData.CANCEL_CLOSE);
            ButtonType settings = new ButtonType("SETTINGS");
            alert.getButtonTypes().setAll(next,settings);

            Platform.runLater(new Runnable() {

                @Override
                public void run(){

                        Optional<ButtonType> result = alert.showAndWait();
                        if(result.get() == settings){
                            mainApp.showSettingsPage();

                        }
                        //user chose exit
                        else{
                            mainApp.showEndPage();
                        }
                }

            });

        }


        else{
            ButtonType next = new ButtonType("CONTINUE", ButtonData.CANCEL_CLOSE);
            ButtonType settings = new ButtonType("SETTINGS");
            alert.getButtonTypes().setAll(next,settings);

            Platform.runLater(new Runnable() {

                @Override
                public void run(){

                        Optional<ButtonType> result = alert.showAndWait();
                        if(result.get() == settings){
                            mainApp.showSettingsPage();
                        }
                        //user chose exit
                        else{
                            gettingTotalFeedback = false;
                            setPage();

                        }
                }

            });
        }
	    }

	    //section for if popUp is disabled
	    else{

	        if(this.trialCount >= this.mainApp.getTestMath().getNumberOfTrials()){

                this.mainApp.showEndPage();


	        }

	        else{

	            this.gettingTotalFeedback = false;
                this.isScanSig = false;
                setPage();


	        }
	    }

    }

	private void restPopUp(){


	    this.gettingTotalFeedback = true;
	    if(this.mainApp.getTestSettings().getIsPopUp()){


        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Feedback");
        alert.setHeaderText(null);
        alert.setContentText("No Questions Presented");


        if(trialCount >= mainApp.getTestMath().getNumberOfTrials()){
            ButtonType next = new ButtonType("CONTINUE", ButtonData.CANCEL_CLOSE);
            ButtonType settings = new ButtonType("SETTINGS");
            alert.getButtonTypes().setAll(next,settings);

            Platform.runLater(new Runnable() {

                @Override
                public void run(){

                        Optional<ButtonType> result = alert.showAndWait();
                        if(result.get() == settings){
                            mainApp.showSettingsPage();

                        }
                        //user chose exit
                        else{
                            mainApp.showEndPage();
                        }
                }

            });

        }


        else{
            ButtonType next = new ButtonType("CONTINUE", ButtonData.CANCEL_CLOSE);
            ButtonType settings = new ButtonType("SETTINGS");
            alert.getButtonTypes().setAll(next,settings);

            Platform.runLater(new Runnable() {

                @Override
                public void run(){

                        Optional<ButtonType> result = alert.showAndWait();
                        if(result.get() == settings){
                            mainApp.showSettingsPage();
                        }
                        //user chose continue
                        else{
                            gettingTotalFeedback = false;
                            setPage();

                        }
                }

            });
        }
	    }

	    else{

	        if(this.trialCount >= this.mainApp.getTestMath().getNumberOfTrials()){
	            this.mainApp.showEndPage();
	        }
	        else{

	            this.gettingTotalFeedback = false;
                this.isScanSig = false;
                setPage();
	        }

	    }

	}


	//***MOUSE/TOUCH INPUT FUNCTIONS FOR WHEEL ANSWERS***\\

	@FXML
	public void onClickOne(MouseEvent event){

	    if(this.gettingFeedback){
	        return;
	    }

	    if(this.duringFirstQuestion){
            this.duringFirstQuestion = false;
        }

	    if(this.mainApp.getTestSettings().getNBack() > 0
                && this.numQuestions <= this.mainApp.getTestSettings().getNBack()){
            return;
        }

	    if(this.mainApp.getTestSettings().getIsClick()){
	        resetButtons();
	        this.chosenAnswer = 1;
	        feedback();
	    }

	    else if(this.mainApp.getTestSettings().getIsClick()
	            && this.mainApp.getTestSettings().getNBack() > 0
	            && this.numQuestions > this.mainApp.getTestSettings().getNBack()){
	        resetButtons();
	        this.chosenAnswer = 1;
	        nBackFeedback();

	    }
	}

	@FXML
    public void onClickTwo(MouseEvent event){

	    if(this.gettingFeedback){
            return;
        }

	    if(this.duringFirstQuestion){
            this.duringFirstQuestion = false;
        }

	    if(this.mainApp.getTestSettings().getNBack() > 0
                && this.numQuestions <= this.mainApp.getTestSettings().getNBack()){
            return;
        }

	    if(this.mainApp.getTestSettings().getIsClick()){
            resetButtons();
            this.chosenAnswer = 2;
            feedback();
        }
	    else if(this.mainApp.getTestSettings().getIsClick()
                && this.mainApp.getTestSettings().getNBack() > 0
                && this.numQuestions > this.mainApp.getTestSettings().getNBack()){
            resetButtons();
            this.chosenAnswer = 2;
            nBackFeedback();

        }
	}

	@FXML
    public void onClickThree(MouseEvent event){

	    if(this.gettingFeedback){
            return;
        }

	    if(this.duringFirstQuestion){
            this.duringFirstQuestion = false;
        }

	    if(this.mainApp.getTestSettings().getNBack() > 0
                && this.numQuestions <= this.mainApp.getTestSettings().getNBack()){
            return;
        }

	    if(this.mainApp.getTestSettings().getIsClick()){
            resetButtons();
            this.chosenAnswer = 3;
            feedback();
        }
	    else if(this.mainApp.getTestSettings().getIsClick()
                && this.mainApp.getTestSettings().getNBack() > 0
                && this.numQuestions > this.mainApp.getTestSettings().getNBack()){
            resetButtons();
            this.chosenAnswer = 3;
            nBackFeedback();

        }
	}

	@FXML
    public void onClickFour(MouseEvent event){

	    if(this.gettingFeedback){
            return;
        }

	    if(this.duringFirstQuestion){
            this.duringFirstQuestion = false;
        }

	    if(this.mainApp.getTestSettings().getNBack() > 0
                && this.numQuestions <= this.mainApp.getTestSettings().getNBack()){
            return;
        }

	    if(this.mainApp.getTestSettings().getIsClick()){
            resetButtons();
            this.chosenAnswer = 4;
            feedback();
        }
	    else if(this.mainApp.getTestSettings().getIsClick()
                && this.mainApp.getTestSettings().getNBack() > 0
                && this.numQuestions > this.mainApp.getTestSettings().getNBack()){
            resetButtons();
            this.chosenAnswer = 4;
            nBackFeedback();

        }
	}

	@FXML
    public void onClickFive(MouseEvent event){

	    if(this.gettingFeedback){
            return;
        }

	    if(this.duringFirstQuestion){
            this.duringFirstQuestion = false;
        }

	    if(this.mainApp.getTestSettings().getNBack() > 0
                && this.numQuestions <= this.mainApp.getTestSettings().getNBack()){
            return;
        }

	    if(this.mainApp.getTestSettings().getIsClick()){
            resetButtons();
            this.chosenAnswer = 5;
            feedback();
        }
	    else if(this.mainApp.getTestSettings().getIsClick()
                && this.mainApp.getTestSettings().getNBack() > 0
                && this.numQuestions > this.mainApp.getTestSettings().getNBack()){
            resetButtons();
            this.chosenAnswer = 5;
            nBackFeedback();

        }
	}

	@FXML
    public void onClickSix(MouseEvent event){

	    if(this.gettingFeedback){
            return;
        }

	    if(this.duringFirstQuestion){
            this.duringFirstQuestion = false;
        }

	    if(this.mainApp.getTestSettings().getNBack() > 0
                && this.numQuestions <= this.mainApp.getTestSettings().getNBack()){
            return;
        }

	    if(this.mainApp.getTestSettings().getIsClick()){
            resetButtons();
            this.chosenAnswer = 6;
            feedback();
        }
	    else if(this.mainApp.getTestSettings().getIsClick()
                && this.mainApp.getTestSettings().getNBack() > 0
                && this.numQuestions > this.mainApp.getTestSettings().getNBack()){
            resetButtons();
            this.chosenAnswer = 6;
            nBackFeedback();

        }
	}

	@FXML
    public void onClickSeven(MouseEvent event){

	    if(this.gettingFeedback){
            return;
        }

	    if(this.duringFirstQuestion){
            this.duringFirstQuestion = false;
        }

	    if(this.mainApp.getTestSettings().getNBack() > 0
                && this.numQuestions <= this.mainApp.getTestSettings().getNBack()){
            return;
        }

	    if(this.mainApp.getTestSettings().getIsClick()){
            resetButtons();
            this.chosenAnswer = 7;
            feedback();
        }
	    else if(this.mainApp.getTestSettings().getIsClick()
                && this.mainApp.getTestSettings().getNBack() > 0
                && this.numQuestions > this.mainApp.getTestSettings().getNBack()){
            resetButtons();
            this.chosenAnswer = 7;
            nBackFeedback();

        }

	}

	@FXML
    public void onClickEight(MouseEvent event){

	    if(this.gettingFeedback){
            return;
        }

	    if(this.duringFirstQuestion){
            this.duringFirstQuestion = false;
        }

	    if(this.mainApp.getTestSettings().getNBack() > 0
                && this.numQuestions <= this.mainApp.getTestSettings().getNBack()){
            return;
        }

	    if(this.mainApp.getTestSettings().getIsClick()){
            resetButtons();
            this.chosenAnswer = 8;
            feedback();
        }
	    else if(this.mainApp.getTestSettings().getIsClick()
                && this.mainApp.getTestSettings().getNBack() > 0
                && this.numQuestions > this.mainApp.getTestSettings().getNBack()){
            resetButtons();
            this.chosenAnswer = 8;
            nBackFeedback();

        }

	}

	@FXML
    public void onClickNine(MouseEvent event){

	    if(this.gettingFeedback){
            return;
        }

	    if(this.duringFirstQuestion){
            this.duringFirstQuestion = false;
        }

	    if(this.mainApp.getTestSettings().getNBack() > 0
                && this.numQuestions <= this.mainApp.getTestSettings().getNBack()){
            return;
        }

	    if(this.mainApp.getTestSettings().getIsClick()){
            resetButtons();
            this.chosenAnswer = 9;
            feedback();
        }
	    else if(this.mainApp.getTestSettings().getIsClick()
                && this.mainApp.getTestSettings().getNBack() > 0
                && this.numQuestions > this.mainApp.getTestSettings().getNBack()){
            resetButtons();
            this.chosenAnswer = 9;
            nBackFeedback();

        }

	}

	@FXML
    public void onClickZero(MouseEvent event){

	    if(this.gettingFeedback){
            return;
        }

	    if(this.duringFirstQuestion){
            this.duringFirstQuestion = false;
        }

	    if(this.mainApp.getTestSettings().getNBack() > 0
                && this.numQuestions <= this.mainApp.getTestSettings().getNBack()){
            return;
        }

        if(this.mainApp.getTestSettings().getIsClick()){
            resetButtons();
            this.chosenAnswer = 0;
            feedback();
        }
        else if(this.mainApp.getTestSettings().getIsClick()
                && this.mainApp.getTestSettings().getNBack() > 0
                && this.numQuestions > this.mainApp.getTestSettings().getNBack()){
            resetButtons();
            this.chosenAnswer = 0;
            nBackFeedback();

        }

    }

	//the below two monitor when the mouse is over
	//the input buttons so as not to confuse the input
	//of an answer with the clicking of a scanner signal
	@FXML
	public void onMouseEnter(MouseEvent event){

	    if(this.mainApp.getTestSettings().getIsClick()){
	        this.clickingAnswer = true;
	    }
	}

	@FXML
	public void onMouseExit(MouseEvent event){
	    if(this.mainApp.getTestSettings().getIsClick()){
	        this.clickingAnswer = false;
	    }
	}



}


