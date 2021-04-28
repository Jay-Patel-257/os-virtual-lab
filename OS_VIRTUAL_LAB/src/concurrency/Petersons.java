package concurrency;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Petersons extends Application{

	static class PetersonThread extends Thread {
		StackPane stack = new StackPane();
		int m,n;
		static int turn=0;
		static boolean interest[] = {false,false};
		PetersonThread(int m,int n,StackPane stack) {
			this.stack = stack;
			this.n=n;
			this.m=m;
		}

		public static synchronized void critical(int m, int n, StackPane stack) throws InterruptedException {
				interest[m]=true;
				turn=n;
				while (interest[n] && turn==n);
//critical section
				Thread.sleep(100);
				template.animate(0, stack);
				
				for (int i = 0; i <= 100; i++) {
					Thread.sleep(20);					
				}
				System.out.println("completeanim");
				template.animComplete(stack);
				
				interest[m]=false;
		}
		
		public void run() {
			// Entry Section
			try {
				Thread.sleep(100);
				critical(m,n,stack);
				System.out.println(getName() + " : completed ");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Peterson's Method");
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25,25,25,25));

		Button btnSubmit = new Button("SUBMIT");
		grid.add(btnSubmit, 7, 1);
		
		btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				VBox processes = new VBox();
				StackPane[] stack = new StackPane[2];
				Circle[] process = new Circle[2];
				for(int i=0;i<2;i++)
				{
					Circle p = new Circle(30, Color.color(Math.random(),Math.random(),Math.random(), 0.70));
					p.setStrokeType(StrokeType.OUTSIDE);
					p.setStroke(Color.web("black",0.10));
					p.setStrokeWidth(4);
					
					StackPane s = new StackPane();
					Text pnum = new Text("P" + i);
					pnum.setFill(Color.WHITE);
					s.getChildren().addAll(p,pnum);
					processes.getChildren().add(s);
					process[i] = p;
					stack[i] = s;
				}
				grid.add(processes, 0,2);
				
				for (int i = 0,j=1; i < 2; i++,j--) {
		    	PetersonThread t = new PetersonThread(i,j,stack[i]);
		    	t.setName("Thread" + i);
		   		t.start();		   		
		        }
			}
		});
		grid.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, new Insets(0, 0, 0, 0))));
		Scene scene = new Scene(grid, 1022, 572, Color.BLACK);
		primaryStage.setScene(scene);
		primaryStage.show();
	}	

	public static void main(String[] args)
	{
		launch(args);
	}
}