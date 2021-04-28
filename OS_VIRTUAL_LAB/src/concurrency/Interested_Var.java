package concurrency;

import javafx.application.Application;

import javafx.scene.layout.StackPane;

import javafx.stage.Stage;

public class Interested_Var extends Application{

	static class InterThread extends Thread {
		StackPane stack = new StackPane();
		int m,n;
		static boolean interest[] = {false,false};
		InterThread(int m,int n,StackPane stack) {
			this.stack = stack;
			this.n=n;
			this.m=m;
		}

		public static synchronized void critical(int m, int n, StackPane stack) throws InterruptedException {
			interest[m]=true;
			while (interest[n]==true);
			Thread.sleep(100);
			template.animate(0, stack);
			for (int i = 0; i <= 100; i++) {
				Thread.sleep(20);					
			}
			
			//template.animComplete(stack);
		}
		
		public void run() {
			// Entry Section
			try {
				Thread.sleep(100);				
				critical(m,n,stack);
				interest[m]=false;
				System.out.println(getName() + " : completed ");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Lock Variable");
		template lv = new template();
		lv.start(primaryStage);
	}	

	public static void main(String[] args)
	{
		launch(args);
	}
}