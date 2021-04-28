package concurrency;

import java.io.IOException;

import home.Home;
import javafx.animation.FillTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Index {

	public Index() {
		
	}
	public static synchronized void animate(int x, StackPane stack) {
		System.out.println("enter animation");
		TranslateTransition pathTrans = new TranslateTransition(Duration.millis(1000), stack);
		pathTrans.setToX(x);
		pathTrans.setToX(x + 250);
		pathTrans.play();
	}

	public static synchronized void animFill(StackPane stack) {
		FillTransition fillTransition = new FillTransition(Duration.millis(1000));
		fillTransition.setShape((Circle) stack.getChildren().get(0));
		fillTransition.setToValue(Color.TRANSPARENT);
		fillTransition.setAutoReverse(false);
		fillTransition.play();
	}

	public static synchronized void animComplete(StackPane stack) {
		animFill(stack);
		animate(250, stack);
		((Text) stack.getChildren().get(1)).setFill(Color.BLACK);
	}
	
	public static void back(Stage stage) {
        stage.close();
        Main a = new Main();
        try {
			a.start(Main.getStage());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	@FXML
	public void demoLock() throws IOException{
		template stg = new template();
		try {
			template.val = 1;
			stg.start(Main.getStage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void demoTest() throws IOException{
		template stg = new template();
		try {
			template.val = 2;
			stg.start(Main.getStage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void demoTurn() throws IOException{
		template stg = new template();
		try {
			template.val = 3;
			stg.start(Main.getStage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void demoInterest() throws IOException{
		template stg = new template();
		try {
			template.val = 4;
			stg.start(Main.getStage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void demoPete() throws IOException{
		template stg = new template();
		try {
			template.val = 5;
			stg.start(Main.getStage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void demoBinary() throws IOException{
		template stg = new template();
		try {
			template.val = 6;
			stg.start(Main.getStage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void demoCounting() throws IOException{
		template stg = new template();
		try {
			template.val = 7;
			stg.start(Main.getStage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void demoCompare() throws IOException{
		Main stg = new Main();
		stg.changeScene("Compare.fxml");
	}
	
	@FXML
	private void demoBanker() throws IOException{
		Bankers stg = new Bankers();
		try {
			stg.start(Main.getStage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void backToHome() throws IOException{
		Home a = new Home();
		a.setLocationRelativeTo(null);
		a.setVisible(true);
		Platform.exit();
	}
	
	@FXML
	private void infoLock() throws IOException{
		Main stg = new Main();
		stg.changeScene("Lock_Var_Info.fxml");
	}
	
	@FXML
	private void infoTest() throws IOException{
		Main stg = new Main();
		stg.changeScene("Test_Set_Info.fxml");
	}
	
	@FXML
	private void infoTurn() throws IOException{
		Main stg = new Main();
		stg.changeScene("Turn_Var_Info.fxml");
	}
	
	@FXML
	private void infoInterest() throws IOException{
		Main stg = new Main();
		stg.changeScene("Interested_Var_Info.fxml");
	}
	
	@FXML
	private void infoPete() throws IOException{
		Main stg = new Main();
		stg.changeScene("Petersons_Info.fxml");
	}
	
	@FXML
	private void infoBinary() throws IOException{
		Main stg = new Main();
		stg.changeScene("Binary_Sema_Info.fxml");
	}
	
	@FXML
	private void infoCounting() throws IOException{
		Main stg = new Main();
		stg.changeScene("Counting_Sema_Info.fxml");
	}
	
	@FXML
	private void infoBanker() throws IOException{
		Main stg = new Main();
		stg.changeScene("Bankers_Info.fxml");
	}
}
