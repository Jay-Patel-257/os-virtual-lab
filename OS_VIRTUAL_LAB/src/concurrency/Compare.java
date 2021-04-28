package concurrency;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Compare implements Initializable{
	
	@FXML
	private TableView myTable;
	
	@FXML
	private TableColumn algo;
	
	@FXML
	private TableColumn mutex;
	
	@FXML
	private TableColumn bound;
	
	@FXML
	private TableColumn progress;
	
	@FXML
	private TableColumn deadlock;
	
	public void backToIndex() throws IOException{
		Main stg = new Main();
		stg.changeScene("Index.fxml");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		final ObservableList<Data> data = FXCollections.observableArrayList(
			new Data("Lock Variable", "✖", "✖", "✔", "✖"),
			new Data("Test and Set Lock", "✔", "✖", "✔", "✖"),
			new Data("Turn Variable", "✔", "✔", "✖", "✖"),
			new Data("Interested Variable", "✔", "✖", "✔", "✔"),
			new Data("Peterson's Algorithm", "✔", "✔", "✔", "✖"),
			new Data("Binary Semaphore", "✔", "✔", "✔", "✖"),
			new Data("Counting Semaphore", "✔", "✔", "✖", "✖")
		);
		
		algo.setCellValueFactory(new PropertyValueFactory<Data,String>("algo"));
		mutex.setCellValueFactory(new PropertyValueFactory<Data,String>("mutex"));
		bound.setCellValueFactory(new PropertyValueFactory<Data,String>("bound"));
		progress.setCellValueFactory(new PropertyValueFactory<Data,String>("progress"));
		deadlock.setCellValueFactory(new PropertyValueFactory<Data,String>("deadlock"));
		
		myTable.setItems(data);
	}
} 
