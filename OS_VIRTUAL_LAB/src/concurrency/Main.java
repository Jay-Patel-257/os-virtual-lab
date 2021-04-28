package concurrency;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	private static Stage stg;
	private static BorderPane pane;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.stg = primaryStage;
		this.stg.setTitle("Concurrency and Deadlock");
		showMainView();
	}
	
	private void showMainView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("Index.fxml"));
		pane = loader.load();
		Scene scene = new Scene(pane);
		stg.setScene(scene);
		stg.show();
	}
	
	public static void changeScene(String fxml) throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource(fxml));
		BorderPane newPane = loader.load();
		Scene scene = new Scene(newPane);
		stg.setScene(scene);
		stg.show();
	}
	
	public static Stage getStage() {
		return stg;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}