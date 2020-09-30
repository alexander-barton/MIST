package ch.makery.address;


import java.io.IOException;


import ch.makery.address.model.TestMath;
import ch.makery.address.model.TestSettings;
import ch.makery.address.model.TestSettings.ScanSig;
import ch.makery.address.view.EndPageController;
import ch.makery.address.view.HomePageController;
import ch.makery.address.view.SettingsPageController;
import ch.makery.address.view.PreTestPageController;
import ch.makery.address.view.TestPageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainMISTApp extends Application {

	//initialize main stage and pane
	private Stage primaryStage;
	private Region rootLayout;
	private Scene scene;
	final double origW = 800;
	final double origH = 650;
	private double newW;
	private double newH;
	private TestSettings testSettings;
	private TestMath testMath;

	@Override
	public void start(Stage primaryStage){

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("M I S T");

		showHomePage();

	}

	 public void showHomePage(){
	        try {
	            // Load person overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainMISTApp.class.getResource("view/homePage.fxml"));
	            rootLayout = (Region) loader.load();


	            // Set person overview into the center of root layout.

	            Group group = new Group(rootLayout);
	            //Place the Group in a StackPane, which will keep it centered
	            StackPane rootPane = new StackPane();
	            rootPane.getChildren().add(group);


	            //Create the scene initially at the "100%" size
	            scene = new Scene(rootPane, origW, origH);
	            //Bind the scene's width and height to the scaling parameters on the group
	            group.scaleXProperty().bind(scene.widthProperty().divide(origW));
	            group.scaleYProperty().bind(scene.heightProperty().divide(origH));


	            primaryStage.setScene(scene);
	            primaryStage.show();

	            //Give controller access
	            HomePageController homePageController = loader.getController();
	            homePageController.setMainApp(this);

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	public void showSettingsPage(){

		 try {

			 	//get any changes to height and width
			 	newW = this.scene.getWidth();
			 	newH = this.scene.getHeight();


	            // Load person overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainMISTApp.class.getResource("view/settingsPage.fxml"));
	            rootLayout = (Region) loader.load();

	            // Set person overview into the center of root layout.
	            Group group = new Group(rootLayout);
	            //Place the Group in a StackPane, which will keep it centered
	            StackPane rootPane = new StackPane();
	            rootPane.getChildren().add(group);


	            //Create the scene initially at the previous size
	            scene = new Scene(rootPane, newW, newH);
	            //Bind the scene's width and height to the scaling parameters on the group
	            //use the original values to ensure proper scaling
	            group.scaleXProperty().bind(scene.widthProperty().divide(origW));
	            group.scaleYProperty().bind(scene.heightProperty().divide(origH));

	            //Give Controller Access
                SettingsPageController settingsPageController = loader.getController();
                settingsPageController.setMainApp(this);
                settingsPageController.loadPreferences();
                settingsPageController.setUpPage();


	            primaryStage.setScene(scene);
	            primaryStage.show();


	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}

	public void showPreTestPage(){

	    try {

            //get any changes to height and width
            newW = this.scene.getWidth();
            newH = this.scene.getHeight();


            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainMISTApp.class.getResource("view/preTestPage.fxml"));
            rootLayout = (Region) loader.load();

            // Set person overview into the center of root layout.
            Group group = new Group(rootLayout);
            //Place the Group in a StackPane, which will keep it centered
            StackPane rootPane = new StackPane();
            rootPane.getChildren().add(group);
            rootPane.setFocusTraversable(true);


            //Create the scene initially at the previous size
            scene = new Scene(rootPane, newW, newH);
            //Bind the scene's width and height to the scaling parameters on the group
            //use the original values to ensure proper scaling
            group.scaleXProperty().bind(scene.widthProperty().divide(origW));
            group.scaleYProperty().bind(scene.heightProperty().divide(origH));

            //Give controller access
            PreTestPageController preTestPageController = loader.getController();
            preTestPageController.setMainApp(this);


            primaryStage.setScene(scene);
            primaryStage.show();

            preTestPageController.callFocus();


        } catch (IOException e) {
            e.printStackTrace();
        }

	}

	public void showTestPage(){

		 try {

			 	//get any changes to height
			 	newW = this.scene.getWidth();
			 	newH = this.scene.getHeight();

	            // Load person overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainMISTApp.class.getResource("view/testPage.fxml"));
	            rootLayout = (Region) loader.load();

	            // Set person overview into the center of root layout.

	            Group group = new Group(rootLayout);
	            //Place the Group in a StackPane, which will keep it centered
	            StackPane rootPane = new StackPane();
	            rootPane.getChildren().add(group);


	            //Create the scene initially at the current size
	            scene = new Scene(rootPane, newW, newH);
	            //Bind the scene's width and height to the scaling parameters on the group
	            group.scaleXProperty().bind(scene.widthProperty().divide(origW));
	            group.scaleYProperty().bind(scene.heightProperty().divide(origH));

	            TestPageController testPageController = loader.getController();

	            testPageController.setMainApp(this);
	            testPageController.setOrderIndex(0);
	            testPageController.setFilePath();


	            primaryStage.setScene(scene);
	            primaryStage.show();
	            group.requestFocus();
	            testPageController.setPage();


	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}

	public void showEndPage(){

	    try {

            //get any changes to height and width
            newW = this.scene.getWidth();
            newH = this.scene.getHeight();


            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainMISTApp.class.getResource("view/endPage.fxml"));
            rootLayout = (Region) loader.load();

            // Set person overview into the center of root layout.
            Group group = new Group(rootLayout);
            //Place the Group in a StackPane, which will keep it centered
            StackPane rootPane = new StackPane();
            rootPane.getChildren().add(group);


            //Create the scene initially at the previous size
            scene = new Scene(rootPane, newW, newH);
            //Bind the scene's width and height to the scaling parameters on the group
            //use the original values to ensure proper scaling
            group.scaleXProperty().bind(scene.widthProperty().divide(origW));
            group.scaleYProperty().bind(scene.heightProperty().divide(origH));

            //Give controller access
            EndPageController endPageController = loader.getController();
            endPageController.setMainApp(this);

            primaryStage.setScene(scene);
            primaryStage.show();
            group.requestFocus();

            endPageController.callFocus();

        } catch (IOException e) {
            e.printStackTrace();
        }

	}

	public void makeTests(int[] intVar, boolean[] booVar, char[] charVar, String[] stringVar, ScanSig scanSig, double doubleVar){

	    this.testSettings = new TestSettings(intVar, booVar, charVar, stringVar, scanSig, doubleVar);
	    this.testMath = new TestMath(this.testSettings);
	    this.testMath.callSetOrder();

	}

	public void setFile(String filename, String userDir){

	}

	public Stage getPrimaryStage(){
		return this.primaryStage;
	}

	public Region getRootLayout(){
	    return this.rootLayout;
	}

	public TestSettings getTestSettings(){
		return this.testSettings;
	}

	public TestMath getTestMath(){
		return this.testMath;
	}


	public static void main(String[] args) {
		launch(args);
	}
}
