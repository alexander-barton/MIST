/* Controller Class for the testPages
 * Updates the selection the participant has made
 * as well as the progress bar and the question
 * presented,etc.
 */


package ch.makery.address.view;


import java.io.File;
import java.util.Optional;
import java.util.Random;

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
	private Integer currentAnswer;     // answer for the specific question
	private Timeline questionTimer;    // timer for the experimental question bar

	private double questionSpeed;

	private Timeline conditionTimer;   //timer for each condition

	private int numCorrect;
	private int numWrong;
	private int trialCount;            // keeps track of the number of iterations
	                                   // and shuts it off when gone through
	private Integer successiveAnswers;
	private Integer numQuestionsExp;   // monitors the number of questions in the experimental condition
	                                   // necessary for resetting the time

	private boolean isFirstQuestion;

	private final int redOff = 232;
	private final int greenOff = 235;
	private final int blueOff = 238;

	private final int redOn = 142;
	private final int greenOn = 144;
	private final int blueOn = 147;

	private boolean gettingFeedback = false;

	//doubles keeping track of arrow positions

	private double xPosArrowYou = 493;
	private double xPosArrowThem = 493;

	private AudioClip rightS;
	private AudioClip wrongS;
	private MediaPlayer timeS;




	public TestPageController() {

	    this.numCorrect = 0;
	    this.numWrong = 0;
	    this.trialCount = 0;
	    this.successiveAnswers = 0;
	    this.questionSpeed = 10000;


	}

	public void setMainApp(MainMISTApp mainApp){
		this.mainApp = mainApp;
	}

	public void setOrderIndex(int index){
	    this.orderIndex = index;
	}

	// sets the question speed in the experimental condition
	public void setQuestionSpeed(double dur){

	    if(this.timeS != null){
	        timeS.play();
	    }
	    KeyValue barEnd = new KeyValue(this.barTimer.progressProperty(), 1);
	    KeyFrame barStart = new KeyFrame(Duration.ZERO, new KeyValue(this.barTimer.progressProperty(), 0));
	    KeyFrame barFrame = new KeyFrame(Duration.millis(dur), barEnd);

	    this.questionTimer = new Timeline();
	    this.questionTimer.getKeyFrames().addAll(barStart, barFrame);
	    this.questionTimer.setOnFinished(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {

                feedback();

            }

	    });
	}

	// sets a faster/slower time depending on how many in a row correct were chosen
	public void setNewTime(){

	    if(this.successiveAnswers > 1){
	         this.questionSpeed = this.questionSpeed * 0.85;
	         System.out.println("SPEEDY GONZALEEEEEZ!!!!");
	    }
	    else if(this.successiveAnswers < -2){
	        this.questionSpeed = this.questionSpeed * 1.10;
	        System.out.println("slow...poke...rod..riguez");
	    }
	}


	//Main function of the controller, sets up the page according to
	//the condition.
	//Also keeps track of the condition order.

	public void setPage(){

	    //check to close app if condition is in REST
	    if(trialCount >= mainApp.getTestMath().getNumberOfTrials()){
	        Platform.exit();
	    }
	    resetButtons();
	    this.txtFeedback.clear();
	    this.txtQuestion.clear();
	    this.txtGo.clear();


            /*  //moves the index forward to the next place in the order ArrayList
                if(orderIndex < mainApp.getTestMath().returnOrderSize() - 1){
                    orderIndex ++;
                }
                else{
                    orderIndex = 0;
                }


                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Feedback");
                alert.setHeaderText(null);
                alert.setContentText("Number of correct answers: " + numCorrect + "\nNumber of incorrect answers: " + numWrong);


                if(trialCount >= mainApp.getTestMath().getNumberOfTrials()){
                    ButtonType next = new ButtonType("EXIT", ButtonData.CANCEL_CLOSE);
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
                                    Platform.exit();
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
                                    setPage();
                                }
                        }

                        });
                }


            }

        });*/

	    //sets the txt Area for the nBack go information
	    if(this.mainApp.getTestSettings().getNBack() > 0){
	        this.txtGo.setVisible(true);
	    }
	    else{
	        this.txtGo.setVisible(false);
	    }

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
	    //SETS PAGE FOR REST CONDITION!!!\\\
		if(this.mainApp.getTestMath().returnOrder(this.orderIndex).equals(condition.REST)){

		    this.trialCount++;
		    if(this.mainApp.getTestSettings().getScanSig().equals(ScanSig.TIMED)){

		        KeyFrame testFrame = new KeyFrame(Duration.millis(this.mainApp.getTestSettings().getRestT() * 1000));
		        this.conditionTimer = new Timeline();
		        this.conditionTimer.getKeyFrames().add(testFrame);

		        this.conditionTimer.setOnFinished(new EventHandler<ActionEvent>(){

		            @Override
		            public void handle(ActionEvent event) {

		                if(orderIndex < mainApp.getTestMath().returnOrderSize() - 1){
		                    orderIndex ++;
		                }
		                else{
		                    orderIndex = 0;
		                }

		                setPage();

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

			this.scannerCount = 0;
			this.inputHover = 100;


		}

		//SETS PAGE FOR CONTROL CONDITION!!!\\\\\
		else if(mainApp.getTestMath().returnOrder(this.orderIndex).equals(condition.CONTROL)){

		    this.isFirstQuestion = true;
		    this.trialCount++;
		    if(this.mainApp.getTestSettings().getScanSig().equals(ScanSig.TIMED)){

                KeyFrame testFrame = new KeyFrame(Duration.millis(this.mainApp.getTestSettings().getConT() * 1000));
                this.conditionTimer = new Timeline();
                this.conditionTimer.getKeyFrames().add(testFrame);

                this.conditionTimer.setOnFinished(new EventHandler<ActionEvent>(){

                    public void handle(ActionEvent event) {

                        popUp();

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

            this.scannerCount = 0;
            this.numCorrect = 0;
            this.numWrong = 0;
            this.testPane.requestFocus();

            setNewQuestion(this.mainApp.getTestMath().getDiff());
		}

		//SETS PAGE FOR EXPERIMENTAL CONDITION!!!\\\
		else{


		    //resets the position of the two arrows and labels for feedback
		    this.lblYou.setTranslateX(0);
		    this.arrowYou.setTranslateX(0);
		    this.lblYou.translateXProperty();
		    this.arrowYou.translateXProperty();
		    this.lblThem.setTranslateX(0);
		    this.arrowThem.setTranslateX(0);
		    this.lblThem.translateXProperty();
		    this.arrowThem.translateXProperty();

		    xPosArrowYou = 493;
		    xPosArrowThem = 493;


		    this.isFirstQuestion = true;
		    this.trialCount++;
		    if(this.mainApp.getTestSettings().getScanSig().equals(ScanSig.TIMED)){

                KeyFrame testFrame = new KeyFrame(Duration.millis(this.mainApp.getTestSettings().getExpT() * 1000));
                this.conditionTimer = new Timeline();
                this.conditionTimer.getKeyFrames().add(testFrame);

                this.conditionTimer.setOnFinished(new EventHandler<ActionEvent>(){

                    @Override
                    public void handle(ActionEvent event) {

                       questionTimer.stop();
                       if(timeS != null){
                           timeS.stop();
                       }
                       popUp();
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

            this.numQuestionsExp = 0;
            this.scannerCount = 0;
            this.numCorrect = 0;
            this.numWrong = 0;
            this.questionSpeed = 5000;
            this.testPane.requestFocus();
            setNewQuestion(this.mainApp.getTestMath().getDiff());


		}
	}


	//function that monitors if key is pressed
	@FXML
	public void onKeyTyped(KeyEvent ke){

	    if(!this.gettingFeedback){
	    if(ke.getCharacter().toLowerCase().charAt(0) == mainApp.getTestSettings().getLeftKey()){

	        moveLeft();
        }
        if(ke.getCharacter().toLowerCase().charAt(0) == mainApp.getTestSettings().getRightKey()){

            moveRight();
        }
        if(ke.getCharacter().toLowerCase().charAt(0) == mainApp.getTestSettings().getConfKey()){

            confirm();
        }
        if(mainApp.getTestSettings().getScanSig().equals(ScanSig.KEYBOARD) && ke.getCharacter().toLowerCase().charAt(0) == mainApp.getTestSettings().getScanKey()){

            System.out.println("IT WORKED");
            scanKeyPress();
        }
	    }

	}

	//function that monitors if
	@FXML
	public void onMouseClicked(MouseEvent event){

	    if(mainApp.getTestSettings().getScanSig().equals(ScanSig.MOUSE)){

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

	    this.chosenAnswer = this.inputHover;
	    feedback();

	}

	//function for scan signal pressed (can be mouse or keyboard)
	private void scanKeyPress(){

	    this.scannerCount ++;
	    System.out.println("Scanner Count: " + this.scannerCount);
	    System.out.println("REST: " + mainApp.getTestSettings().getRestT());
	    System.out.println("CONTROL: " + mainApp.getTestSettings().getConT());
	    System.out.println("EXPERIMENTAL: " + mainApp.getTestSettings().getExpT());

	    switch(mainApp.getTestMath().returnOrder(this.orderIndex)){

	    case REST:
	        if(this.scannerCount == mainApp.getTestSettings().getRestT()){

	          //moves the index forward to the next place in the order ArrayList
	            if(this.orderIndex < mainApp.getTestMath().returnOrderSize() - 1){
	                this.orderIndex ++;
	            }
	            else{
	                this.orderIndex = 0;
	            }

	            setPage();
	            break;

	        }
	    case CONTROL:
	        if(this.scannerCount == mainApp.getTestSettings().getConT()){

	          //moves the index forward to the next place in the order ArrayList
	            if(this.orderIndex < mainApp.getTestMath().returnOrderSize() - 1){
	                this.orderIndex ++;
	            }
	            else{
	                this.orderIndex = 0;
	            }

	            Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Feedback");
                alert.setHeaderText(null);
                alert.setContentText("Number of correct answers: " + numCorrect + "\nNumber of incorrect answers: " + numWrong);

                if(trialCount >= mainApp.getTestMath().getNumberOfTrials()){
                    ButtonType next = new ButtonType("EXIT", ButtonData.CANCEL_CLOSE);
                    ButtonType settings = new ButtonType("SETTINGS");
                    alert.getButtonTypes().setAll(next,settings);

                    Optional<ButtonType> result = alert.showAndWait();
                    if(result.get() == settings){
                        mainApp.showSettingsPage();
                    }
                    //user chose exit
                    else{
                        Platform.exit();
                    }

                }


                else{
                    ButtonType next = new ButtonType("CONTINUE", ButtonData.CANCEL_CLOSE);
                    ButtonType settings = new ButtonType("SETTINGS");
                    alert.getButtonTypes().setAll(next,settings);

                    Optional<ButtonType> result = alert.showAndWait();
                    if(result.get() == settings){
                        mainApp.showSettingsPage();
                    }
                    //user chose continue
                    else{
                        setPage();
                    }
                }


	        }

	    case EXPERIMENTAL:
	        if(this.scannerCount == mainApp.getTestSettings().getExpT()){


	          //moves the index forward to the next place in the order ArrayList
	            if(this.orderIndex < mainApp.getTestMath().returnOrderSize() - 1){
	                this.orderIndex ++;
	            }
	            else{
	                this.orderIndex = 0;
	            }


	            this.questionTimer.stop();
	            Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Feedback");
                alert.setHeaderText(null);
                alert.setContentText("Number of correct answers: " + numCorrect + "\nNumber of incorrect answers: " + numWrong);

                if(trialCount >= mainApp.getTestMath().getNumberOfTrials()){
                    ButtonType next = new ButtonType("EXIT", ButtonData.CANCEL_CLOSE);
                    ButtonType settings = new ButtonType("SETTINGS");
                    alert.getButtonTypes().setAll(next,settings);

                    Optional<ButtonType> result = alert.showAndWait();
                    if(result.get() == settings){
                        mainApp.showSettingsPage();
                    }
                    //user chose exit
                    else{
                        Platform.exit();
                    }

                }


                else{
                    ButtonType next = new ButtonType("CONTINUE", ButtonData.CANCEL_CLOSE);
                    ButtonType settings = new ButtonType("SETTINGS");
                    alert.getButtonTypes().setAll(next,settings);

                    Optional<ButtonType> result = alert.showAndWait();
                    if(result.get() == settings){
                        mainApp.showSettingsPage();
                    }
                    //user chose continue
                    else{
                        barTimer.setProgress(0);
                        setPage();
                    }
                }
	        }
	    }
	}

	//function that creates the new math questions that are displayed
	private void setNewQuestion(int difficulty){

	    this.chosenAnswer = 1000;

	    TestQuestion testQuestion = new TestQuestion(difficulty,mainApp.getTestMath().getULimit());

	    this.txtQuestion.clear();

	    this.txtQuestion.setText(testQuestion.getQuestion());

	    //this.txtQuestion.setAlignment(Pos.CENTER);


	    this.currentAnswer = testQuestion.getAnswer();

	    if(this.mainApp.getTestMath().returnOrder(this.orderIndex).equals(condition.EXPERIMENTAL) && !this.isFirstQuestion){

	        this.questionTimer.stop();
	        setQuestionSpeed(this.questionSpeed);
	        this.barTimer.setProgress(0);
	        this.questionTimer.playFromStart();
	        this.numQuestionsExp++;
	        //setQuestionSpeed(this.mainApp.getTestMath().getExpSpeed());
	    }
	    else if(this.mainApp.getTestMath().returnOrder(this.orderIndex).equals(condition.EXPERIMENTAL) && this.isFirstQuestion){

	        setQuestionSpeed(this.questionSpeed);
	        this.barTimer.setProgress(0);
	        this.questionTimer.playFromStart();
	        this.numQuestionsExp++;
	    }
	    this.gettingFeedback = false;
	    this.isFirstQuestion = false;
	}

	private void feedback(){

	    this.gettingFeedback = true;

	    if(this.timeS != null){
	        timeS.stop();
	    }
	    if(this.chosenAnswer.equals(this.currentAnswer)){

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

	        if(this.xPosArrowYou < 650){
	            this.arrowYou.setTranslateX(this.arrowYou.getTranslateX()+25);
	            this.lblYou.setTranslateX(this.lblYou.getTranslateX()+25);
	            this.arrowYou.translateXProperty();
	            this.lblYou.translateXProperty();
	            this.xPosArrowYou += 25;
	        }
	        opponentFeedback();
	    }
	    else{

	        //if block that adjusts the successive answers based on last answer
	           if(this.successiveAnswers <= 0){
	               this.successiveAnswers--;
	           }
	           else{
	               this.successiveAnswers = -1;
	           }

	        this.numWrong++;

	        if(this.wrongS != null){
	            this.wrongS.play();
	        }

	        this.txtFeedback.setText(this.mainApp.getTestSettings().getNCorrect());
	        //add if statement checks

	        if (this.xPosArrowYou > 0){

	            this.arrowYou.setTranslateX(this.arrowYou.getTranslateX()-25);
	            this.lblYou.setTranslateX(this.lblYou.getTranslateX()-25);
	            this.arrowYou.translateXProperty();
	            this.lblYou.translateXProperty();
	            this.xPosArrowYou -= 25;
	        }
	        opponentFeedback();
	    }

	    if(this.mainApp.getTestMath().returnOrder(orderIndex).equals(condition.CONTROL)){
	        this.txtFeedback.appendText("\n" + this.mainApp.getTestSettings().getNRecord());

	    }
	    else{
	        this.txtFeedback.appendText("\n" + this.mainApp.getTestSettings().getRecord());
	        this.questionTimer.pause();
	        setNewTime();
	    }

	    PauseTransition wait = new PauseTransition(Duration.millis(3000));
	    wait.setOnFinished(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {

                txtFeedback.clear();
                //txtQuestion.clear();
                setNewDifficulty();
                setNewQuestion(mainApp.getTestMath().getDiff());
            }

        });

	    wait.play();
	}

	//changes the difficulty level based on the number of
	//correct/incorrect answers in a row
	private void setNewDifficulty(){

	    switch(this.successiveAnswers){

	    case -4:

	        this.mainApp.getTestMath().decreaseULimit();
	        System.out.println("Easier");

	        this.successiveAnswers = 0;
	        break;


	    case 2:

	            this.mainApp.getTestMath().increaseULimit();
	            System.out.println("Harder");

	        this.successiveAnswers = 0;
	        break;
	    }

	}

	//function that moves the opponent/average arrow
	private void opponentFeedback(){

	    if(this.xPosArrowThem < 620){
	        Random rand = new Random();
	        int change = rand.nextInt(5);

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
	        }

	    }
	    else if(this.xPosArrowThem >= 620 && this.xPosArrowThem < 760){
	        Random rand = new Random();
            int change = rand.nextInt(5);

            switch(change){

            case 0:
                this.arrowThem.setTranslateX(this.arrowThem.getTranslateX()-25);
                this.lblThem.setTranslateX(this.lblThem.getTranslateX()-25);
                this.arrowThem.translateXProperty();
                this.lblThem.translateXProperty();
                this.xPosArrowThem -= 25;
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

	private void popUp(){


	    if(orderIndex < mainApp.getTestMath().returnOrderSize() - 1){
            orderIndex ++;
        }
        else{
            orderIndex = 0;
        }


        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Feedback");
        alert.setHeaderText(null);
        alert.setContentText("Number of correct answers: " + numCorrect + "\nNumber of incorrect answers: " + numWrong);


        if(trialCount >= mainApp.getTestMath().getNumberOfTrials()){
            ButtonType next = new ButtonType("EXIT", ButtonData.CANCEL_CLOSE);
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
                            Platform.exit();
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
                            setPage();
                        }
                }

            });
        }


    }
}


