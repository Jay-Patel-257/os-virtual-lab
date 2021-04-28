package concurrency;

import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Turn_Var extends Application{
	static class TurnThread1 extends Thread {
		StackPane stack = new StackPane();
		int m,n;
		
		TurnThread1(int m,int n,StackPane stack) {
			this.stack = stack;
			this.n=n;
			this.m=m;
		}

		public static synchronized void critical(int m, int n, StackPane stack) throws InterruptedException {
			while (template.turn!=m) System.out.println("Waiting in while loop");
			template.animate(0, stack);
			template.turn=n;
		}
		
		public void run() {
			// Entry Section
			try {			
				critical(m,n,stack);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	static class TurnThread2 extends Thread {
		StackPane stack = new StackPane();
		int m,n;
		
		TurnThread2(int m,int n,StackPane stack) {
			this.stack = stack;
			this.n=n;
			this.m=m;
			//turn=m;
		}

		public static synchronized void critical(int m, int n, StackPane stack) throws InterruptedException {
			while (template.turn!=n) System.out.println("Waiting in while loop");
			Thread.sleep(2000);
			template.animate(0, stack);
			template.turn=m;
		}
		
		public void run() {
			// Entry Section
			try {			
				critical(m,n,stack);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
//		
//		primaryStage.setTitle("Turn Variable");
//		
//		GridPane grid = new GridPane();
//		grid.setHgap(10);
//		grid.setVgap(10);
//		grid.setPadding(new Insets(25,25,25,25));
//		Button btnStart = new Button("START");
//		Button btnReset = new Button("RESET");
//		grid.add(btnStart, 2, 1);
//		grid.add(btnReset, 5, 5);
//		AnchorPane workingArea = new AnchorPane();
//		VBox entrySection = new VBox();
//		VBox criticalSection = new VBox();
//		
//		StackPane[] stack = new StackPane[2];
//		Circle[] process = new Circle[2];
//		for(int i=0;i<2;i++)
//		{
//			Circle p = new Circle(30, Color.color(Math.random(),Math.random(),Math.random(), 0.30));
//			p.setStrokeType(StrokeType.OUTSIDE);
//			p.setStroke(Color.web("white",0.16));
//			p.setStrokeWidth(4);
//			
//			StackPane s = new StackPane();
//			Text pnum = new Text("P" + i);
//			s.getChildren().addAll(p,pnum);
//			entrySection.getChildren().add(s);
//			process[i] = p;
//			stack[i] = s;
//		}
//		for(int i=0;i<2;i++)
//		{
//			Circle p = new Circle(30, Color.color(Math.random(),Math.random(),Math.random(), 0.30));
//			p.setStrokeType(StrokeType.OUTSIDE);
//			p.setStroke(Color.web("white",0.16));
//			p.setStrokeWidth(4);
//			
//			StackPane s = new StackPane();
//			Text pnum = new Text("P" + i);
//			s.getChildren().addAll(p,pnum);
//			criticalSection.getChildren().add(s);
//			process[i] = p;
//			stack[i] = s;
//		}
//		workingArea.getChildren().add(entrySection);
//		workingArea.getChildren().add(criticalSection);
//		AnchorPane.setLeftAnchor(criticalSection, 5.0d);
//		grid.add(workingArea, 0,2);
//		btnStart.setOnAction(new EventHandler<ActionEvent>() {
//			public void handle(ActionEvent eve) {
//			Exec e = new Exec();
//			for(int i=0;i<2;i++)
//			{
//				new Processes(e, stack[i], i);
//			}
//			}
//		});
//		btnReset.setOnAction(new EventHandler<ActionEvent>() {
//			public void handle(ActionEvent eve) {
				
//			}
//		});
		
//		String border = "-fx-border-color: black;\n" + "-fx-border-width: 3;\n";
//		criticalSection.setStyle(border);
//		Scene scene = new Scene(grid, 1022, 572, Color.BEIGE);
//		primaryStage.setScene(scene);	
//		primaryStage.show();
	}
}