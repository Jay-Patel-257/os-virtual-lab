package concurrency;

import java.io.IOException;

public class Info {
	
	public Info() {
		
	}
	
	public void backToIndex() throws IOException{
		Main stg = new Main();
		try {
			stg.start(Main.getStage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}