package concurrency;
import javafx.animation.FillTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import concurrency.Lock_Var.LockThread;
import concurrency.Petersons.PetersonThread;
import concurrency.Test_Set.TestThread;
import concurrency.Binary_Semaphore.BSemThread;
import concurrency.Turn_Var.TurnThread1;
import concurrency.Turn_Var.TurnThread2;
import java.util.Random;
import java.util.concurrent.Semaphore;
import concurrency.Counting_Semaphore.CSemThread;
import concurrency.Interested_Var.InterThread;

public class template extends Application{

	Stage primaryStage = new Stage();
	public Stage getPrimaryStage()
	{
		return primaryStage;
	}
	public static synchronized void animFill(StackPane stack) {
		FillTransition fillTransition = new FillTransition(Duration.millis(750));
		fillTransition.setShape((Circle) stack.getChildren().get(0));
		fillTransition.setToValue(Color.TRANSPARENT);
		fillTransition.setAutoReverse(false);
		fillTransition.play();
	}
	
	public static synchronized void animate(double x, StackPane stack) throws InterruptedException {
		TranslateTransition pathTrans = new TranslateTransition(Duration.millis(750), stack);
		pathTrans.setToX(x);
		pathTrans.setToX(x + 58 + k*58);
		pathTrans.play();
		
		if(x==0)
		{
			pathTrans.setOnFinished(e -> progressbar(stack));
		}
	}
	
	public static synchronized void animComplete(StackPane stack) throws InterruptedException {
		animFill(stack);
		animate(75+k*58, stack);
		((Text) stack.getChildren().get(1)).setFill(Color.GREEN);
		bar.setProgress(0.0);
	}
	
	public static synchronized void progress(ProgressIndicator indicator) {
		Timeline load = new Timeline(
		        new KeyFrame(
		                Duration.ZERO,       
		                new KeyValue(indicator.progressProperty(), 0)
		        ),
		        new KeyFrame(
		                Duration.seconds(2), 
		                new KeyValue(indicator.progressProperty(), 1)
		        )
		    );
		load.playFromStart();
	}
	
	public static synchronized void progressbar(StackPane stack) {
		Timeline load = new Timeline(
		        new KeyFrame(
		                Duration.ZERO,       
		                new KeyValue(bar.progressProperty(), 0)
		        ),
		        new KeyFrame(
		                Duration.millis(1000), 
		                new KeyValue(bar.progressProperty(), 1)
		        )
		    );
		load.playFromStart();
		load.setOnFinished(e -> {
			try {
				animComplete(stack);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		});
	}
	
	public void start(Stage primaryStage) throws Exception {
		BorderPane borderDemo = new BorderPane();
		Label header = new Label("Concurrency and Deadlock");
		header.setPadding(new Insets(10,10,10,10));
		header.setFont(Font.font("Verdana",FontWeight.EXTRA_BOLD,30.0));
		header.setTextFill(Color.WHITESMOKE);
		header.setStyle("-fx-background-color: #009688;");
		borderDemo.setStyle("-fx-background-color: #F5F5DC;");
		borderDemo.setTop(header);
		header.setMinHeight(50.0);
		header.setMinWidth(1600);
		borderDemo.setTop(header);
		HBox workingArea = new HBox();
//		AnchorPane workingArea = new AnchorPane();
//		workingArea.getChildren().add(workingAreaHBox);
		FlowPane entrySection = new FlowPane();
		VBox criticalSection = new VBox();
		VBox exitSection = new VBox();
		
//		Label lbl = new Label("Hi");
//		log.setPrefSize(100, 100);
//		log.setText("Hi");
//		AnchorPane.setRightAnchor(log, 30.0);
//		AnchorPane.setTopAnchor(log, 150.0);
//		ProgressIndicator indicator = new ProgressIndicator();

	    Timeline task = new Timeline(
	        new KeyFrame(
	                Duration.ZERO,       
	                new KeyValue(bar.progressProperty(), 0)
	        ),
	        new KeyFrame(
	                Duration.millis(1500), 
	                new KeyValue(bar.progressProperty(), 1)
	        )
	    );
	    //timeline.setCycleCount(Animation.INDEFINITE);

//	    criticalSection.getChildren().add(indicator);
	    bar.setPrefSize(200, 24);
	    Scene sceneDemo = new Scene(borderDemo, 1022, 572, Color.BLACK);
	    VBox centre = new VBox();
VBox info = new VBox();
//info.getChildren().add(log);
		workingArea.getChildren().add(entrySection);
		workingArea.getChildren().add(criticalSection);
		workingArea.getChildren().add(exitSection);
		info.setAlignment(Pos.TOP_RIGHT);
		workingArea.getChildren().add(info);
		ComboBox<String> list = new ComboBox<>();
		centre.setMinWidth(workingArea.getWidth());
		list.setPadding(new Insets(0,0,0,0));
		list.getItems().add("Lock Variable");
		list.getItems().add("Test and Set Lock");
		list.getItems().add("Turn Variable");
		list.getItems().add("Interested Variable");
		list.getItems().add("Peterson's Algorithm");
		list.getItems().add("Binary Semaphore");
		list.getItems().add("Counting Sempahore");
//		list.getItems().add("Banker's Algorithm");
		list.setEditable(false);
		
		
		
		AnchorPane.setTopAnchor(entrySection, 100.0);
		AnchorPane.setLeftAnchor(entrySection, 100.0);
		AnchorPane.setRightAnchor(exitSection, 300.0);
//		AnchorPane.setLeftAnchor(criticalSection, 285.0);
		AnchorPane.setTopAnchor(criticalSection, 90.0);
//		AnchorPane.setTopAnchor(log, 90.0);
//		AnchorPane.setRightAnchor(log, 2.0);
		//AnchorPane.setRightAnchor(criticalSection, 4.0);
		borderDemo.setCenter(centre);
		HBox.setMargin(entrySection, new Insets(0,0,0,100));
		HBox.setMargin(criticalSection, new Insets(-5,0,0,50));
//		HBox.setMargin(exitSection, new Insets(0,0,0,70));
		
//		VBox.setMargin(log, new Insets(0,30,0,140 + k*58));
//		borderDemo.setRight(info);
		//log.setVisible(true);
		AnchorPane.setTopAnchor(workingArea, 2000.0);
		HBox controlArea = new HBox();
		controlArea.setPadding(new Insets(25,25,25,25));
		controlArea.getChildren().add(list);
		Label lblnumber = new Label("Number of processes:");
		lblnumber.setPadding(new Insets(0,2,0,10));
		lblnumber.setFont(Font.font("Verdana",FontWeight.BOLD, 15.0));
//		controlArea.getChildren().add(lblnumber);
		VBox numberInput = new VBox();
		TextField numberTextField = new TextField();
		Label numberLabel = new Label("(Max. 15)");
		numberLabel.setFont(Font.font("Verdana",FontPosture.ITALIC, 10.0));
		numberInput.getChildren().addAll(numberTextField,numberLabel);
		numberTextField.setPadding(new Insets(0,0,0,5));
		numberTextField.setBackground(null);
		numberTextField.setPromptText("Number of processes");
		numberTextField.setPrefHeight(31.0);
		HBox.setMargin(numberInput, new Insets(0,10,0,10));
		numberTextField.setStyle("-fx-border-style: solid;\n-fx-border-width:0 0 2 0;\n -fx-border-color:#8A2BE2  ");
		controlArea.getChildren().add(numberInput);
		
		Label lblsem = new Label("Permits:");
		lblsem.setPadding(new Insets(0,2,0,10));
		lblsem.setFont(Font.font("Verdana",FontWeight.BOLD, 15.0));								
		
		TextField semaphoreTextField = new TextField();
		semaphoreTextField.setPadding(new Insets(0,0,0,5));
		semaphoreTextField.setPadding(new Insets(0,0,0,5));
		semaphoreTextField.setBackground(null);
		semaphoreTextField.setPromptText("Number of permits");
		semaphoreTextField.setStyle("-fx-prompt-text-fill:#000000");
		semaphoreTextField.setPrefHeight(31.0);
		HBox.setMargin(semaphoreTextField, new Insets(0,10,0,10));
		semaphoreTextField.setStyle("-fx-border-style: solid;\n-fx-border-width:0 0 2 0;\n -fx-border-color:#8A2BE2  ");
		semaphoreTextField.setDisable(true);
		controlArea.getChildren().add(semaphoreTextField);
		Button btnSubmit = new Button("VISUALIZE");
		controlArea.getChildren().add(btnSubmit);

		System.out.println(btnSubmit.getHeight());
		centre.getChildren().addAll(controlArea,workingArea);
		AnchorPane.setTopAnchor(workingArea, 50.0);
		AnchorPane.setTopAnchor(controlArea, 5.0);
		HBox.setMargin(btnSubmit, new Insets(0,0,0,10));
		
		if(val==1) {
			numberTextField.setDisable(false);
			semaphoreTextField.setDisable(true);
			list.setValue("Lock Variable");
		}
		else if(val==2) {
			numberTextField.setDisable(false);
			semaphoreTextField.setDisable(true);
			list.setValue("Test and Set Lock");
		}
		else if(val==3) {
			numberTextField.setDisable(true);
			semaphoreTextField.setDisable(true);
			list.setValue("Turn Variable");
		}
		else if(val==4) {
			numberTextField.setDisable(true);
			semaphoreTextField.setDisable(true);
			list.setValue("Interested Variable");
		}
		else if(val==5) {
			numberTextField.setDisable(true);
			semaphoreTextField.setDisable(true);
			list.setValue("Peterson's Algorithm");
		}
		else if(val==6) {
			numberTextField.setDisable(false);
			semaphoreTextField.setDisable(true);
			list.setValue("Binary Semaphore");
		}
		else if(val==7) {
			numberTextField.setDisable(false);
			semaphoreTextField.setDisable(false);
			list.setValue("Counting Sempahore");
		}
		
		list.setOnAction((event) -> {
			flag = list.getSelectionModel().getSelectedIndex() + 1;
			if(flag==1||flag==2||flag==6)
			{
				numberTextField.setDisable(false);
				semaphoreTextField.setDisable(true);
			}
			else if(flag==3||flag==4||flag==5)
			{
				numberTextField.setDisable(true);
				semaphoreTextField.setDisable(true);
			} 
			else if(flag==7)
			{
				numberTextField.setDisable(false);
				semaphoreTextField.setDisable(false);
			}
		});
		btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				int number=0;
				
				boolean exec=true;
				System.out.println(btnSubmit.getHeight());
				System.out.println("flag=" + flag);
				if(flag==3||flag==4||flag==5)
				{		
					number = 2;
				}
				else
				{
					int test=0;
					try {
						test = Integer.valueOf(numberTextField.getText());
					}catch (NumberFormatException err){
						numberLabel.setText("Not an integer");
						numberLabel.setTextFill(Color.web("#ff0000", 0.8));
					};
					
					if(test>0&&test<=15)
					{
						number = test;//Integer.valueOf(numberTextField.getText());
						numberLabel.setText("(Max. 15)");
						numberLabel.setTextFill(Color.web("#000000", 0.8));
						exec=true;
					}
					else
					{
						exec=false;
						numberLabel.setText("(Max. 15) Invalid input");
						numberLabel.setTextFill(Color.web("#ff0000", 0.8));
					}
				}
				if(exec) {
				entrySection.getChildren().clear();
			    entrySection.setPrefWidth(60);
			    entrySection.setMaxHeight(sceneDemo.getHeight() - 271);
			    System.out.println(sceneDemo.getHeight() + " " + sceneDemo.getWidth() + " " + entrySection.getHeight());
			    k=1;
			    
			    while(sceneDemo.getHeight()< (number*58)/k + 271)
			    {	    	
			    	k++;
			    	System.out.println("True");
			    }
			    //VBox.setMargin(log, new Insets(0,30,0,140 + k*58));
			    entrySection.setPrefWidth(k*60);
				criticalSection.getChildren().clear();
//				DropShadow ds = new DropShadow();
//				ds.setColor(Color.color(0.4, 0.4, 0.4));
//				ds.setOffsetX(-5.0f);
//				ds.setOffsetY(5.0f);
				ProgressIndicator[] indicator = new ProgressIndicator[number];
				StackPane[] stack = new StackPane[number];
				Circle[] process = new Circle[number];
				for(int i=0;i<number;i++)
				{
					Circle p = new Circle(23, Color.color(Math.random(),Math.random(),Math.random(), 0.90));
					p.setStrokeType(StrokeType.OUTSIDE);
					p.setStroke(Color.web("black",0.10));
					p.setStrokeWidth(4);
					
					ProgressIndicator ind = new ProgressIndicator();
					
					StackPane s = new StackPane();
					Text pnum = new Text("P" + i);
					pnum.setFill(Color.WHITE);
					s.getChildren().addAll(p,pnum);
					s.setPadding(new Insets(2,2,2,2));
//					System.out.println(s.minHeight(Double.MAX_VALUE));
//					p.setEffect(ds);
//					System.out.println(s.MAX);
					ind.setBlendMode(BlendMode.SRC_OVER);
					ind.setMinSize(70, 70);
					ind.setCenterShape(true);
					ind.setStyle("-fx-progress-colour: white;text-color:white");
					entrySection.getChildren().add(s);
					process[i] = p;
					stack[i] = s;
					indicator[i] = ind;
					progress(ind);
				}
				VBox criticalBox = new VBox();
				String borderstyle = "-fx-border-color: red;\n" + "-fx-border-width: 3;\n";
				criticalBox.setStyle(borderstyle);
				criticalSection.setMinWidth(68);
				criticalSection.setPrefWidth(k*58 + 10);
				criticalBox.setMinHeight(70);
				criticalBox.setPrefHeight((number*63)/k + 10);
				//bar.setMinWidth(criticalBox.getPrefWidth());
				criticalSection.setAlignment(Pos.CENTER);
			//	criticalBox.setMinHeight(sceneDemo.getHeight() - 271);
				criticalSection.getChildren().addAll(criticalBox,bar);

				switch(flag) {
				case 1:
					for (int i = 0; i < number; i++) {
						LockThread t = new LockThread(number, stack[i]);
				    	t.setName("Thread" + i);
				   		t.start();	
					}
					break;
				case 2:
					for (int i = 0; i < number; i++)
					{
						TestThread t = new TestThread(number, stack[i]);
				    	t.setName("Thread" + i);
				   		t.start();
					}
					break;
				case 3:
					TurnThread1 t1=new TurnThread1(0,1,stack[0]);
					TurnThread2 t2=new TurnThread2(0,1,stack[1]);
					t1.start();
					t2.start();
					
					break;
				case 4:
					for (int i = 0,j=1; i < 2; i++,j--) {
				    	InterThread t = new InterThread(i,j,stack[i]);
				    	t.setName("Thread" + i);
				   		t.start();		   		
				    }
					break;
				case 5:
					for (int i = 0,j=1; i < 2; i++,j--) {
				    	PetersonThread t = new PetersonThread(i,j,stack[i]);
				    	t.setName("Thread" + i);
				   		t.start();		   		
				    }
					break;
				case 6:
					sem = new Semaphore(1);
					for (int i = 0; i < number; i++) {
				    	BSemThread t = new BSemThread(number, stack[i]);
				    	t.setName("Thread" + i);
				   		t.start();	   		
				    }
					break;
				case 7:
					int semaNum = Integer.valueOf(semaphoreTextField.getText());
					sem = new Semaphore(semaNum);
					for (int i = 0; i < number; i++) {
				    	CSemThread t = new CSemThread(number, stack[i]);
				    	t.setName("Thread" + i);
				   		t.start();		   		
				    }
					break;
				case 8:
					Bankers a = new Bankers();
					try {
						a.start(primaryStage);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				}
			};
		}
			});
		HBox bottom = new HBox();
		Button btnBack = new Button("Back");
		btnBack.setFont(Font.font("Verdana",FontWeight.LIGHT,13.0));
		btnBack.setTranslateX(10);
		btnBack.setTranslateY(-10);
		Button btnReset = new Button("Reset");
		btnReset.setFont(Font.font("Verdana",FontWeight.LIGHT,13.0));
		btnReset.setTranslateX(20);
		btnReset.setTranslateY(-10);
		bottom.getChildren().addAll(btnBack,btnReset);
		borderDemo.setBottom(bottom);
		workingArea.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, new Insets(0, 0, 0, 0))));		
		
		
//		BorderPane border = new BorderPane();
////		Label header = new Label("Concurrency and Deadlock");
//		Button btnLock = new Button("Lock Variable");
//		Button btnTestSet = new Button("Test and  Set Variable");
//		Button btnTurn = new Button("Turn Variable");
//		Button btnInterested = new Button("Interested Variable");
//		Button btnPeterson = new Button("Peterson's Algorithm");
//		Button btnBSem = new Button("Binary Semaphore");
//		Button btnCSem = new Button("Counting Semaphore");
//		Button btnBanker = new Button("Banker's Algorithm");
//		VBox buttons = new VBox();
//		buttons.getChildren().addAll(btnLock,btnTestSet,btnTurn,btnInterested,btnPeterson,btnBSem,btnCSem,btnBanker);
//		border.setCenter(buttons);
//		Scene sceneHome = new Scene(border, 1022, 572, Color.BLACK);
		
		btnBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Index.back((Stage)btnBack.getScene().getWindow());
			}
		});
		
		btnReset.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				entrySection.getChildren().clear();
				criticalSection.getChildren().clear();
				numberTextField.clear();
			}
		});
		
//		btnLock.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent arg0) {
//				flag = 1;
//				numberTextField.setDisable(false);
//				semaphoreTextField.setDisable(true);
//				list.setValue("Lock Variable");
//				//header.setText("Lock Variable");
//				primaryStage.setScene(sceneDemo);
//			}
//		});
//		btnTestSet.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent arg0) {
//				flag = 2;
//				numberTextField.setDisable(false);
//				semaphoreTextField.setDisable(true);
//				//header.setText("Test and Set Variable");
//				primaryStage.setScene(sceneDemo);
//			}
//		});
//		btnTurn.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent arg0) {
//				flag = 3;
//				numberTextField.setDisable(true);
//				semaphoreTextField.setDisable(true);
//				//header.setText("Turn Variable");
//				primaryStage.setScene(sceneDemo);
//			}
//		});
//		btnInterested.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent arg0) {
//				flag = 4;
//				numberTextField.setDisable(true);
//				semaphoreTextField.setDisable(true);
//				//header.setText("Interested Variable");
//				primaryStage.setScene(sceneDemo);
//			}
//		});
//		btnPeterson.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent arg0) {
//				flag = 5;
//				numberTextField.setDisable(true);
//				semaphoreTextField.setDisable(true);
//				//header.setText("Peterson Variable");
//				primaryStage.setScene(sceneDemo);
//			}
//		});
//		btnBSem.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent arg0) {
//				flag = 6;
//				numberTextField.setDisable(false);
//				semaphoreTextField.setDisable(true);
//				//header.setText("Binary Semaphore");
//				primaryStage.setScene(sceneDemo);
//			}
//		});
//		btnCSem.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent arg0) {
//				flag = 7;
//				numberTextField.setDisable(false);
//				semaphoreTextField.setDisable(false);
//				//header.setText("Counting Semaphore");
//				primaryStage.setScene(sceneDemo);
//			}
//		});
//		btnBanker.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent arg0) {
//				Bankers ba = new Bankers();
//				try {
//					ba.start(primaryStage);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		});
		primaryStage.setHeight(619.0);
		primaryStage.setWidth(1040.0);
		primaryStage.setScene(sceneDemo);
		primaryStage.show();
	}	

	public static void main(String[] args)
	{
		launch(args);
	}
	static int val=0; 
	static int k=1;
	//static TextArea log = new TextArea();
	static int flag=0;
	static Semaphore sem;
	static int lock=0;
	static Random rand = new Random();
	static int turn = (int)Math.random()*2;
	static ProgressBar bar = new ProgressBar(0);
}