package concurrency;
import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Binary_Semaphore extends Application{
	static class BSemThread extends Thread {
	    StackPane stack = new StackPane();
		final int number;
	    BSemThread(Integer number, StackPane stack) {
	    	this.number = number;
	    	this.stack = stack;
		}
		
    public void run(){
        try{
			System.out.println("How many processes can be there at the same time: " + template.sem.availablePermits());
			System.out.println(getName() + " wants to enter the critical section...added to queue");
			for(int i=0;i<number;i++)
			{
				if(getName().equals("Thread"+ i))
				{
		        	System.out.println(getName() + " started");
		        	Thread.sleep(100);
					template.sem.acquire();
					template.animate(0,stack);
				}
			}
			String t=Integer.toString(template.sem.availablePermits());
			System.out.println("No. of available Permits : "+t);
			try {
				Thread.sleep(1000);
				System.out.println(getName() + " is still in CS. No. of processes which can still enter the CS: " + template.sem.availablePermits());
				try{
					for(int i=0;i<=100;i++) {
						Thread.sleep(20);
						for(int j=0;j<number;j++)
						{
						if(getName().equals("Thread" + j))
						{
							if(i==0)
							System.out.println(getName() + i + "%");
							break;
						}
						}
					}
					template.animComplete(stack);
				}catch(Exception o){
				}
			} finally {
				template.sem.release();
				System.out.println(getName() + "after loading");
				System.out.println(getName() + " completed and exited the CS.");
				System.out.println("Now  " + template.sem.availablePermits() + " process can enter CS");
				t=Integer.toString(template.sem.availablePermits());
				System.out.println("No. of available Permits : "+t);
			}
	    }
	    catch(InterruptedException ie){
        ie.printStackTrace();
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