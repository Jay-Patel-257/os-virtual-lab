package concurrency;
import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class Test_Set extends Application{
	

	static class TestThread extends Thread {
		static Object Lock = 1;
		Integer number,i=0;
	    StackPane stack = new StackPane();
	    TestThread(Integer number, StackPane stack) {
	    	this.number = number;
	    	this.stack = stack;
		}
		public void entry() throws InterruptedException {
	    	synchronized(Lock) {
	    		while(TestAndSet(Lock)!=0) {
	    			template.animate(0,stack);
	    			System.out.println("Lock acquired by " + getName());
	    			Thread.sleep(2000);
	    			Lock=0;
	    			System.out.println("Lock released by " + getName());
	    		}
	    	}
	    }
// 
//	    public void critical() throws InterruptedException {
//            System.out.println("Lock acquired by " + getName());
//			
//			System.out.println("Lock released by " + getName());
//        }
        int TestAndSet(Object l) {
        	int initial=(int) l;
        	Lock=1;
			return initial;
        }

        public void run(){
            try{
            	entry();
            	System.out.println("Out " + getName());
   // 			critical();
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