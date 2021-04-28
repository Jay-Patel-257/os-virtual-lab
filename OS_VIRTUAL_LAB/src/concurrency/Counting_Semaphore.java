package concurrency;
import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Counting_Semaphore extends Application{
		
//	static TextArea log = new TextArea("");

	static class CSemThread extends Thread {
	    StackPane stack = new StackPane();
		final int number;
	    CSemThread(Integer number, StackPane stack) {
	    	this.number = number;
	    	this.stack = stack;
		}
		
    public void run(){
        try{
			System.out.println("How many processes can be there at the same time: " + template.sem.availablePermits());
			System.out.println(getName() + " wants to enter the critical section...added to queue");
//			log.setText(getName() + " wants to enter the critical section...added to queue\n");
			for(int i=0;i<number;i++)
			{
				if(getName().equals("Thread"+ i))
				{
		        	System.out.println(getName() + " started");
		 //       	log.appendText(getName() + " started\n");
		        	Thread.sleep(100);
					template.sem.acquire();
					template.animate(0,stack);
				}
			}
			String t=Integer.toString(template.sem.availablePermits());
			System.out.println("No. of available Permits : "+t);
			//log.appendText("No. of available Permits : "+t + "\n");
			try {
				Thread.sleep(1000);
				System.out.println(getName() + " is still in CS. No. of processes which can still enter the CS: " + template.sem.availablePermits());
			//	log.appendText(getName() + " is still in CS. No. of processes which can still enter the CS: " + template.sem.availablePermits() + "\n");
				try{
					for(int i=0;i<=100;i++) {
						Thread.sleep(20);
						for(int j=0;j<number;j++)
						{
						if(getName().equals("Thread" + j))
						{
							//if(i==0)
							System.out.println(getName() + i + "%");
							//break;
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

				//text += getName() + " completed and exited the CS.\n";
//				log.appendText(getName() + " completed and exited the CS.\n");
//				//text += "Now " + template.sem.availablePermits() + " process can enter CS\n";
//				log.appendText("Now " + template.sem.availablePermits() + " process can enter CS\n");
//				t=Integer.toString(template.sem.availablePermits());
//				//text+= "No. of available Permits : "+ t + "\n\n";
//				log.appendText("No. of available Permits : "+ t + "\n\n");
			}
	    }
	    catch(InterruptedException ie){
        ie.printStackTrace();
	    }
	}
}

	public void start(Stage primaryStage) throws Exception {

	}

//	public static void main(String[] args)
//	{
//		launch(args);
//	}
}