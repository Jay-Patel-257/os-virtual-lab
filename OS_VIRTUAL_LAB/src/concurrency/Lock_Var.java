package concurrency;
import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class Lock_Var extends Application {
//	static int lock = 0;

	static class LockThread extends Thread {
		StackPane stack = new StackPane();

		final int number;

		LockThread(Integer number, StackPane stack) {
			this.number = number;
			this.stack = stack;
		}
		public void run() {
			try {
				// Entry Section
				while (template.lock != 0) {
					for (int i = 0; i < number; i++)
						if (getName().contentEquals("Thread" + i))
						{
							Thread.sleep(100);
							break;
						}
				}
				template.lock = 1;
				System.out.println("LOCK  VARIABLE : 1");
				//template.log.setText("LOCK  VARIABLE : 1");

				for (int i = 0; i < number;i++) {
					if (getName().contentEquals("Thread" + i))
					{
						Thread.sleep(100);
						template.animate(0,stack);
						break;
					}
				}
				// Critical Section
				for (int i = 0; i <= 100; i++) {
					Thread.sleep(20);					
				}
				// Exit section
				for (int i = 0; i < number; i++) {
					if (getName().contentEquals("Thread" + i))
					{
						//template.animComplete(stack);
						template.lock=0;
						break;
					}
				}
				System.out.println(getName() + " : completed ");
				//template.log.appendText("\n"+getName() + " : completed ");
				System.out.println("LOCK  VARIABLE : 0");
				//template.log.appendText("\nLOCK  VARIABLE : 0");
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}

	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Lock Variable");
		template lv = new template();
		lv.start(primaryStage);
	}

	public static void main(String[] args) {
		launch(args);
	}
}