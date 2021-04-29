package concurrency;

import java.io.IOException;
import java.util.ArrayList;
import concurrency.tableSchema;
import concurrency.tableSchema.Data;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Bankers extends Application{
	
	static int numProcesses = 0; // Number of processes
	static int numResources; // Number of resources
	static ArrayList<ArrayList<Integer>> need = new ArrayList<>();
	static ArrayList<ArrayList<Integer>> max = new ArrayList<>();
	static ArrayList<ArrayList<Integer>> alloc = new ArrayList<>();
	static ArrayList<Integer> maxResources = new ArrayList<>();
	static ArrayList<Integer> safeSequence = new ArrayList<>();
	
	static TextArea txtOutput = new TextArea("");
	static Label lblOutput = new Label("Safe Sequence");
	static Label lblResult = new Label("");
	static TextArea tfInstructions = new TextArea("You have provided the number of Resources!\nYou need to add processes one by one in two steps for each process.\nEach process will have Allocated resources and Max resourses.\nAfter filling the fields press \"Add Process\"\nRepeat the same steps for adding each process.\nThen enter the available resources and press \"Calculate\"\nRest will be done itself!");                                       
	private TableView tableAllocation = new TableView();
	private TableView tableMax = new TableView();
	private TableView tableNeed = new TableView();
	private TableView tableAvailable = new TableView();

	private final ObservableList<Data> dataAlloc =
	        FXCollections.observableArrayList(
	        );
	private final ObservableList<Data> dataMax =
	        FXCollections.observableArrayList(
	        );
	private final ObservableList<Data> dataNeed =
	        FXCollections.observableArrayList(
	        );
	private final ObservableList<Data> dataAvail =
	        FXCollections.observableArrayList(
	        );
	public void isSafe()
	{
		int count=0;

		//visited array to find the already allocated process
		boolean visited[] = new boolean[numProcesses];
		for (int i = 0;i < numProcesses; i++)
			visited[i] = false;
			
		//work array to store the copy of available resources
		System.out.println("Work size: " + numResources);
		int work[] = new int[numResources];	
		for (int i = 0;i < numResources; i++)
			work[i] = maxResources.get(i);
	
		while (count<numProcesses)
		{
			boolean flag = false;
			for (int i = 0;i < numProcesses; i++)
			{
				if (visited[i] == false)
				{
					int j;
					for (j = 0;j < numResources; j++)
					{		
						if (need.get(i).get(j) > work[j])
						break;
					}
					if (j == numResources)
					{
						tableSchema ts = new tableSchema();
						Integer[] temp= {0,0,0,0,0,0,0,0,0,0};
						safeSequence.add(count,i);
						for(int k=0;k<numResources;k++)
						{
							maxResources.set(k,maxResources.get(k) + alloc.get(i).get(k));
							temp[k]=maxResources.get(k);
						}
						dataAvail.add(ts.new Data("P"+String.valueOf(i), temp));
						count++;
						visited[i]=true;
						flag=true;
						for (j = 0;j < numResources; j++)
							work[j] = work[j]+alloc.get(i).get(j);
					}
				}
			}
			if (flag == false)
				break;
		}
		if (count < numProcesses)
			System.out.println("The System is UnSafe!");
		else
		{
			System.out.println("The given System is Safe");
			System.out.println("Following is the SAFE Sequence");
			for (int i = 0;i < numProcesses; i++)
			{
				lblResult.setText("Safely Allocated");
				System.out.print("P" + safeSequence.get(i));
				txtOutput.appendText("P" + safeSequence.get(i));
				if (i != numProcesses-1)
				{
				System.out.print(" -> ");
				txtOutput.appendText(" -> ");
				}
			}
		}
	}
	
	public void calculateNeed()
	{
		Integer[] temp = {0,0,0,0,0,0,0,0,0,0};
		tableSchema ts = new tableSchema();
		for (int i = 0;i < numProcesses; i++)
		{
			for (int j = 0;j < numResources; j++)
			{
				need.add(new ArrayList<Integer>());
				need.get(i).add(max.get(i).get(j)-alloc.get(i).get(j));
				temp[j]=need.get(i).get(j);
			}
			dataNeed.add(ts.new Data("P" + i, temp));
		}
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Banker's Algorithm");
		BorderPane border = new BorderPane();
		Label header = new Label("Banker's Algorithm");
		header.setPadding(new Insets(10,10,10,10));
		header.setFont(Font.font("Verdana",FontWeight.EXTRA_BOLD,30.0));
		header.setTextFill(Color.WHITESMOKE);
		header.setStyle("-fx-background-color: #009688;");
		border.setStyle("-fx-background-color: #F5F5DC;");
		border.setTop(header);
		header.setMinHeight(50.0);
		header.setMinWidth(1600);
		tableAllocation.setPrefHeight(350.0);
		tableMax.setPrefHeight(350.0);
		tableNeed.setPrefHeight(350.0);
		tableAvailable.setPrefHeight(350.0);

		ScrollPane spAlloc = new ScrollPane();
		spAlloc.setContent(tableAllocation);
		spAlloc.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		spAlloc.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
		ScrollPane spMax = new ScrollPane();
		spMax.setContent(tableMax);
		spMax.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		spMax.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
		ScrollPane spNeed = new ScrollPane();
		spNeed.setContent(tableNeed);
		spNeed.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		spNeed.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
		ScrollPane spAvail = new ScrollPane();
		spAvail.setContent(tableAvailable);
		spAvail.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		spAvail.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);

		VBox inputArea = new VBox();
		VBox outputArea = new VBox();
		HBox inNumResources = new HBox();
		HBox allocation = new HBox();
		HBox bottom = new HBox();
		VBox allocbox = new VBox();
		VBox maxbox = new VBox();
		VBox needbox = new VBox();
		VBox availbox = new VBox();
		
		Label lblalloc = new Label();
		lblalloc.setText("Allocation Table");
		allocbox.getChildren().addAll(lblalloc,spAlloc);
		Label lblmax = new Label();
		lblmax.setText("Max Table");
		maxbox.getChildren().addAll(lblmax,spMax);
		Label lblneed = new Label();
		lblneed.setText("Need Table");
		needbox.getChildren().addAll(lblneed,spNeed);
		Label lblavail = new Label();
		lblavail.setText("Available Resources");
		availbox.getChildren().addAll(lblavail,spAvail);
		
		GridPane tableSection = new GridPane();
		
		Button btnBack = new Button("Back");
		btnBack.setFont(Font.font("Verdana",FontWeight.LIGHT,13.0));
		btnBack.setTranslateX(10);
		btnBack.setTranslateY(-10);
		btnBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Index.back((Stage)btnBack.getScene().getWindow());
			}
		});
		
		Button btnReset = new Button("Reset");
		btnReset.setFont(Font.font("Verdana",FontWeight.LIGHT,13.0));
		bottom.getChildren().addAll(btnBack,btnReset);
		border.setBottom(bottom);
		btnReset.setTranslateX(20);
		btnReset.setTranslateY(-10);
		btnReset.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					start(primaryStage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	
		Label lblResources = new Label("No. of Resources:");
		lblResources.setPadding(new Insets(0,2,8,10));
		lblResources.setFont(Font.font("Verdana",FontWeight.BOLD, 15.0));
		TextField nResources = new TextField();
		nResources.setTranslateY(-2);
		Button btnNumResource = new Button("OK");
		btnNumResource.setTranslateY(-2);
		btnNumResource.setTranslateX(10);
		
		Button btnCalculate = new Button("Calculate");
		
		//inputArea.setPadding(new Insets(10,10,10,10));
		inputArea.setPrefWidth(400.0);
		inputArea.setPadding(new Insets(60,0,0,10));
		tableSection.setPrefWidth(340.0);
		tableSection.setAlignment(Pos.CENTER);
		//tableSection.setMaxWidth(600.0);
		//outputArea.setPrefWidth(340.0);
		outputArea.setPadding(new Insets(60,20,0,20));
		btnNumResource.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				numResources=Integer.valueOf(nResources.getText());
				nResources.setEditable(false);
				btnNumResource.disableProperty();
				VBox[] column = new VBox[3*numResources + 2];
				int k=0;
				VBox col = new VBox();
				HBox process = new HBox();
				Label lblCol = new Label("Process");
				TextField txtProcess = new TextField("P" + numProcesses);
				txtProcess.setMaxWidth(50.0);
				process.getChildren().addAll(lblCol,txtProcess);
				process.setAlignment(Pos.CENTER);
				Label lblResource = new Label("Resource:");
				Label lblInAlloc = new Label("Allocated:");
				Label lblInReq = new Label("Total Required:");
				lblInAlloc.setPadding(new Insets(7,0,10,0));
				//lblInReq.setPadding(new Insets(7,0,10,0));
				col.getChildren().addAll(lblResource,lblInAlloc,lblInReq);
				allocation.getChildren().add(col);
				column[k++]=col;
				
				for(int j=0;j<numResources;j++)
				{
					VBox colR = new VBox();
					Label lblColR = new Label("R" + (j+1));
					TextField txtInR = new TextField();
					TextField txtInR2 = new TextField();
					txtInR.setMaxWidth(50.0);
					txtInR2.setMaxWidth(50.0);
					colR.getChildren().addAll(lblColR,txtInR,txtInR2);
					allocation.getChildren().add(colR);
					column[k++]=colR;
				}
		
				Label lblAvailable = new Label("Available Resources");
				HBox available = new HBox();
				for(int j=0;j<numResources;j++)
				{
					VBox colR = new VBox();
					Label lblColR = new Label("R" + (j+1));
					TextField txtInR = new TextField();
					txtInR.setMaxWidth(50.0);
					colR.getChildren().addAll(lblColR,txtInR);
					available.getChildren().add(colR);
					//System.out.println(k);
					column[k++]=colR;
				}
				tableAllocation.setEditable(true);
				 
		        TableColumn ProcessCol = new TableColumn("Process");
		        TableColumn bProcessCol = new TableColumn("Process");
		        TableColumn cProcessCol = new TableColumn("Process");
		        TableColumn dProcessCol = new TableColumn("Process");
		  
		        ProcessCol.setCellValueFactory(new PropertyValueFactory<Data, String>("process"));
		        bProcessCol.setCellValueFactory(new PropertyValueFactory<Data, String>("process"));
		        cProcessCol.setCellValueFactory(new PropertyValueFactory<Data, String>("process"));
		        dProcessCol.setCellValueFactory(new PropertyValueFactory<Data, String>("process"));
		        TableColumn[] ResourceCol = new TableColumn[numResources];
		        TableColumn[] bResourceCol = new TableColumn[numResources];
		        TableColumn[] cResourceCol = new TableColumn[numResources];
		        TableColumn[] dResourceCol = new TableColumn[numResources];
				for(int i=0;i<numResources;i++)
				{
					ResourceCol[i] = new TableColumn("R" + (i+1));
					bResourceCol[i] = new TableColumn("R" + (i+1));
					cResourceCol[i] = new TableColumn("R" + (i+1));
					dResourceCol[i] = new TableColumn("R" + (i+1));
					
					ResourceCol[i].setCellValueFactory(new PropertyValueFactory<Data, Integer>("cResource"+i));
					bResourceCol[i].setCellValueFactory(new PropertyValueFactory<Data, Integer>("cResource"+i));
					cResourceCol[i].setCellValueFactory(new PropertyValueFactory<Data, Integer>("cResource"+i));
					dResourceCol[i].setCellValueFactory(new PropertyValueFactory<Data, Integer>("cResource"+i));
				}
		        //ProcessCol.setMinWidth(100);
		        tableAllocation.setItems(dataAlloc);
		        tableAllocation.getColumns().addAll(ProcessCol);//,ResourceCol[0]);
		        tableMax.setItems(dataMax);
		        tableMax.getColumns().addAll(bProcessCol);//,ResourceCol[0]);
		        tableNeed.setItems(dataNeed);
		        tableNeed.getColumns().addAll(cProcessCol);
		        tableAvailable.setItems(dataAvail);
		        tableAvailable.getColumns().addAll(dProcessCol);
				for(int i=0;i<numResources;i++)
				{
					tableAllocation.getColumns().add(ResourceCol[i]);
					tableMax.getColumns().add(bResourceCol[i]);
					tableNeed.getColumns().add(cResourceCol[i]);
			        tableAvailable.getColumns().add(dResourceCol[i]);
				}

				tableSection.add(allocbox, 0, 0);
				tableSection.add(maxbox, 1, 0);
				tableSection.add(needbox, 0, 1);
				tableSection.add(availbox, 1, 1);//All(tableAllocation,tableMax,tableNeed,tableAvailable);
		
				//outputArea.setPadding(new Insets(10,10,10,10));
				outputArea.getChildren().addAll(lblOutput,txtOutput,lblResult,tfInstructions);		
				
				Button btnAddProcess = new Button("Add Process");
				btnAddProcess.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						for(int i=1;i<=numResources;i++)
						{
							alloc.add(new ArrayList());
							alloc.get(numProcesses).add(Integer.valueOf(((TextField)column[i].getChildren().get(1)).getText()));
							max.add(new ArrayList());
							max.get(numProcesses).add(Integer.valueOf(((TextField)column[i].getChildren().get(2)).getText()));
							((TextField)column[i].getChildren().get(1)).clear();
							((TextField)column[i].getChildren().get(2)).clear();

						}
						Integer[] temp = {0,0,0,0,0,0,0,0,0,0};
						for(int i=0;i<numResources;i++)
						{
							temp[i]=alloc.get(numProcesses).get(i);
						}
						tableSchema ts = new tableSchema();
						dataAlloc.add(ts.new Data("P" + numProcesses, temp));
						for(int i=0;i<numResources;i++)
						{
							temp[i]=max.get(numProcesses).get(i);
						}
						dataMax.add(ts.new Data("P" + numProcesses, temp));
						txtProcess.setText("P"+ ++numProcesses);
					}
				});
				btnCalculate.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						for(int i= numResources+1;i<2*numResources+1;i++)
						{
							maxResources.add(Integer.valueOf(((TextField)column[i].getChildren().get(1)).getText()));
							//System.out.println(maxResources.get(i-(numResources + 1)));
						}	
						//Calculate the Need Matrix
						calculateNeed();			
						//Check whether system is in safe state or not
						isSafe();
					}
				});
				inputArea.getChildren().addAll(process,allocation,btnAddProcess,lblAvailable,available,btnCalculate);
			}
		});
		
		inNumResources.getChildren().addAll(lblResources,nResources,btnNumResource);
		inputArea.getChildren().add(inNumResources);

		border.setTop(header);
		border.setCenter(tableSection);
		border.setLeft(inputArea);
		border.setRight(outputArea);

		Scene scene = new Scene(border, 1500, 700, Color.BLACK);
		primaryStage.setScene(scene);
		primaryStage.show();
	}	

	public static void main(String[] args)
	{
		launch(args);
	}
}