/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diskscheduling;

/**
 *
 * @author Anushka Sharma
 */

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;


/**
 *
 * @author Anushka Sharma
 */
  


//class nodes extends node{
//
//    // represent difference between 
//    // head position and track number 
//    int distance = 0;
//
//    // true if track has been accessed 
//    boolean accessed = false;
//}

public class GUI extends javax.swing.JFrame implements ActionListener {

    /**
     * Creates new form GUI
     */
    public int[] array;
    
    public static int fcfs1;
    public static int sstf1;
    public static int scan1 ;
    public static int scan2 ;
    public static int cscan1;
    public static int clook1;
    public static int look1;
    public static int look2;
    
    
    public GUI() {
       initComponents(); 
      
    }   
    
    public GUI(String title){
       super( title ); 
      setContentPane(createDemoPanel( ));
    }

    private void initUI() {

        XYDataset dataset = createDataset();
        
        JFreeChart chart = createChart(dataset);

        //   chart.setSize(500,150);
        ChartPanel objpnl = new ChartPanel(chart);
        objpnl.setVisible(true);
        objpnl.setPreferredSize(new java.awt.Dimension(plotPanel.getWidth(), plotPanel.getHeight()));

        plotPanel.setViewportView(objpnl);
        
    }
     
    
 
   
    private XYDataset createDataset() {

        int[] x = array;
        int[] y = new int[x.length];

        for (int i = 0; i < y.length; i++) {
            y[i] = i + 1;
        }

//        System.out.println(x);
        var series = new XYSeries("Seek", false);

        for (int j = 0; j < x.length; j++) {
            series.add(x[j], y[j]);
//            System.out.println("X=" + x[j] + "  Y= " + y[j]);

        }
        
        var dataset= new XYSeriesCollection();
        dataset.addSeries(series);

        return dataset;
    }

    private JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createScatterPlot(
                "Disk Scheduling Chart",
                "Time(ms)",
                " ",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

//        chart.getPlot().setBackgroundPaint(Color.BLUE);
        var renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.YELLOW);

        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.black);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Disk Scheduling Chart",
                new Font("Serif", java.awt.Font.BOLD, 18)
        )
        );

        return chart;
    }

    
     
   
   
   private static PieDataset createDataset1( ) {
      DefaultPieDataset dataset1 = new DefaultPieDataset( );
            
             dataset1.setValue( "FCFS" , new Double(fcfs1 ) );  
           dataset1.setValue( "SSTF" , new Double(sstf1 ) );  
            dataset1.setValue( "SCAN-RIGHT" , new Double(scan1 ) );  
             dataset1.setValue( "SCAN-LEFT" , new Double(scan2 ) );  
              dataset1.setValue( "CSCAN" , new Double(cscan1 ) );  
               dataset1.setValue( "CLOOK" , new Double(clook1 ) );  
                dataset1.setValue( "LOOK-RIGHT" , new Double(look1 ) );  
                 dataset1.setValue( "LOOK-LEFT" , new Double(look2 ) );  
      
      return dataset1;         
   }
   
   private static JFreeChart createChart1( PieDataset dataset1 ) {
      JFreeChart chart1 = ChartFactory.createPieChart(      
         "Seek Time Comparison Chart",   // chart title 
         dataset1,          // data    
         true,             // include legend   
         true, 
         false);

      return chart1;
   }
   
   public static JPanel createDemoPanel( ) {
      JFreeChart chart1 = createChart1(createDataset1( ) );  
      return new ChartPanel( chart1 ); 
   }
    
    
    
    public int[] FCFS(int[] arr) {

        int size = arr.length;
        int seek_count = 0;
        int distance, cur_track;
        int head = Integer.parseInt(textFieldStartPos.getText());
        //System.out.println(head);

        for (int i = 0; i < size; i++) {
            cur_track = arr[i];

            // calculate absolute distance 
            distance = Math.abs(cur_track - head);

            // increase the total count 
            seek_count += distance;

            // accessed track is now new head 
            head = cur_track;
        }

        /*  System.out.println("Total number of seek operations = " + seek_count); 
	  
	    // Seek sequence would be the same 
	    // as request array sequence 
	    System.out.println("Seek Sequence is"); 
	  
	    for (int i = 0; i < size; i++) 
	    { 
	        System.out.println(arr[i]); 
	    }
         */
        //String[] data1 = {"FCFS", String.valueOf(Arrays.toString(arr)), textFieldStartPos.getText(), String.valueOf(seek_count)};
        DefaultTableModel tblModel = (DefaultTableModel) tableDisplay.getModel();
        tblModel.setValueAt(String.valueOf(Arrays.toString(arr)), 0, 1);
        tblModel.setValueAt(textFieldStartPos.getText(), 0, 2);
        tblModel.setValueAt(String.valueOf(seek_count), 0, 3);
        
        fcfs1=seek_count;
        return arr;
    }

    private int parseInt(Object object) {
        // TODO Auto-generated method stub
        return 0;
    }

    public static void calculateDifference(int queue[],
            int head, node diff[]) {
        for (int i = 0; i < diff.length; i++) {
            diff[i].distance = Math.abs(queue[i] - head);
        }
    }

// find unaccessed track 
// which is at minimum distance from head 
    public static int findMin(node diff[]) {
        int index = -1, minimum = Integer.MAX_VALUE;

        for (int i = 0; i < diff.length; i++) {
            if (!diff[i].accessed && minimum > diff[i].distance) {

                minimum = diff[i].distance;
                index = i;
            }
        }
        
        return index;
    }

    public int[] SSTF(int request[]) {
//		int[] request= new int[array.length];
//		for(int i=0;i<array.length;i++)
//			request[i]=(int)array[i];

        int head = Integer.parseInt(textFieldStartPos.getText().toString());
        int seek_count;
        int[] seek_sequence = new int[request.length + 1];

        {
            if (request.length == 0) {
                return null;
            }

            // create array of objects of class node	 
            node diff[] = new node[request.length];

            // initialize array 
            for (int i = 0; i < diff.length; i++) {
                diff[i] = new node();
            }

            // count total number of seek operation	 
            seek_count = 0;

            // stores sequence in which disk access is done 
            for (int i = 0; i < request.length; i++) {

                seek_sequence[i] = head;
                calculateDifference(request, head, diff);

                int index = findMin(diff);

                diff[index].accessed = true;

                // increase the total count 
                seek_count += diff[index].distance;

                // accessed track is now new head 
                head = request[index];
            }

            // for last accessed track 
            seek_sequence[seek_sequence.length - 1] = head;

            //System.out.println("Total number of seek operations = "+ seek_count); 
            // print the sequence 
            /*for (int i = 0; i < seek_sequence.length; i++) 
			System.out.println(seek_sequence[i]); 
		} */
            //String[] data1 = {"SSTF", String.valueOf(Arrays.toString(seek_sequence)), textFieldStartPos.getText(), String.valueOf(seek_count)};
            DefaultTableModel tblModel = (DefaultTableModel) tableDisplay.getModel();
            tblModel.setValueAt(String.valueOf(Arrays.toString(seek_sequence)), 1, 1);
            tblModel.setValueAt(textFieldStartPos.getText(), 1, 2);
            tblModel.setValueAt(String.valueOf(seek_count), 1, 3);
        }
        
        sstf1=seek_count;
        return seek_sequence;
    }

    public int[] SCAN(int[] arr, String direction) {
        String given=direction;
        int head = Integer.parseInt(textFieldStartPos.getText().toString());
        int disk_size = 200;
        int size = arr.length;

        int seek_count = 0;
        int distance, cur_track;
        Vector<Integer> left = new Vector<Integer>(),
                right = new Vector<Integer>();
        Vector<Integer> seek_sequence = new Vector<Integer>();

        // appending end values 
        // which has to be visited 
        // before reversing the direction 
        if (direction == "left") {
            left.add(0);
        } else if (direction == "right") {
            right.add(disk_size - 1);
        }

        for (int i = 0; i < size; i++) {
            if (arr[i] < head) {
                left.add(arr[i]);
            }
            if (arr[i] > head) {
                right.add(arr[i]);
            }
        }

        // sorting left and right vectors 
        Collections.sort(left);
        Collections.sort(right);

        // run the while loop two times. 
        // one by one scanning right 
        // and left of the head 
        int run = 2;
        while (run-- > 0) {
            if (direction == "left") {
                for (int i = left.size() - 1; i >= 0; i--) {
                    cur_track = left.get(i);

                    // appending current track to seek sequence 
                    seek_sequence.add(cur_track);

                    // calculate absolute distance 
                    distance = Math.abs(cur_track - head);

                    // increase the total count 
                    seek_count += distance;

                    // accessed track is now the new head 
                    head = cur_track;
                }
                direction = "right";
             
        
                
            } else if (direction == "right") {
                for (int i = 0; i < right.size(); i++) {
                    cur_track = right.get(i);

                    // appending current track to seek sequence 
                    seek_sequence.add(cur_track);

                    // calculate absolute distance 
                    distance = Math.abs(cur_track - head);

                    // increase the total count 
                    seek_count += distance;

                    // accessed track is now new head 
                    head = cur_track;
                }
                direction = "left";
              
               
            }
             
               
        }

        /*	    System.out.print("Total number of seek operations = "
	                        + seek_count + "\n"); 
	  
	    System.out.print("Seek Sequence is" + "\n"); 
	  
	    for (int i = 0; i < seek_sequence.size(); i++) 
	    { 
	        System.out.print(seek_sequence.get(i) + "\n"); 
	    } */
        //String[] data1 = {"SCAN", String.valueOf(seek_sequence), textFieldStartPos.getText(), String.valueOf(seek_count)};
        
        /*DefaultTableModel tblModel = (DefaultTableModel) tableDisplay.getModel();
        tblModel.setValueAt(String.valueOf(seek_sequence), 2, 1);
        tblModel.setValueAt(textFieldStartPos.getText(), 2, 2);
        tblModel.setValueAt(String.valueOf(seek_count), 2, 3);
        */
//METHO1
//        String[] Array1 ;
        //String[] Array2 = new String[seek_sequence.size()];
//        Array2 = seek_sequence.toArray(Array2);
        //int[] a = new int[Array2.length];
//        int[] ar = Arrays.asList(Array2).stream().mapToInt(Integer::parseInt).toArray();

//METHOD2
//        Object[] objArray = seek_sequence.toArray();
//        String[] array = Arrays.copyOf(objArray,objArray.length,String[].class);4
//METHOD3
//        String[] array = seek_sequence.toArray(new String[seek_sequence.size()]);
//        int[] ar = new int[array.length];
//        for (int i = 0; i < array.length; i++) {
//            ar[i] = Integer.parseInt(array[i]);
//        }
//        System.out.println(ar);
//        return ar;
//    }
//METHOD 4

        String[] stringArray = new String[seek_sequence.size()];
        for (int i = 0; i < seek_sequence.size(); i++) {
            stringArray[i] = seek_sequence.get(i).toString();
        }
        int[] ar = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            ar[i] = Integer.parseInt(stringArray[i]);
        }
        
        if(given=="right"){
            scan1=seek_count;
                    DefaultTableModel tblModel = (DefaultTableModel) tableDisplay.getModel();
                tblModel.setValueAt(String.valueOf(seek_sequence), 2, 1);
                tblModel.setValueAt(textFieldStartPos.getText(), 2, 2);
                tblModel.setValueAt(String.valueOf(seek_count),2, 3);
               }
               else if(given=="left"){
                   scan2=seek_count;
                    DefaultTableModel tblModel = (DefaultTableModel) tableDisplay.getModel();
                tblModel.setValueAt(String.valueOf(seek_sequence), 3, 1);
                tblModel.setValueAt(textFieldStartPos.getText(), 3, 2);
                tblModel.setValueAt(String.valueOf(seek_count),3, 3);
               }
        

        return ar;

    }

    public int[] CSCAN(int[] arr) {
        int head = Integer.parseInt(textFieldStartPos.getText().toString());
        int disk_size = 200;
        int size = arr.length;
        int seek_count = 0;
        int distance, cur_track;
        Vector<Integer> left = new Vector<Integer>(), right = new Vector<Integer>();
        Vector<Integer> seek_sequence = new Vector<Integer>();

        left.add(0);
        right.add(disk_size - 1);

        for (int i = 0; i < size; i++) {
            if (arr[i] < head) {
                left.add(arr[i]);
            }
            if (arr[i] > head) {
                right.add(arr[i]);
            }
        }

        Collections.sort(left);
        // System.out.println("Components of the vector after sorting: "+left); 
        Collections.sort(right);

        for (int i = 0; i < right.size(); i++) {
            cur_track = right.get(i);

            // appending current track to seek sequence 
            seek_sequence.add(cur_track);

            // calculate absolute distance 
            distance = Math.abs(cur_track - head);

            // increase the total count 
            seek_count += distance;

            // accessed track is now new head 
            head = cur_track;
        }

        head = 0;

        for (int i = left.size() - 1; i >= 0; i--) {
            cur_track = left.get(i);

            // appending current track to seek sequence 
            seek_sequence.add(cur_track);

            // calculate absolute distance 
            distance = Math.abs(cur_track - head);

            // increase the total count 
            seek_count += distance;

            // accessed track is now the new head 
            head = cur_track;
        }

        /* System.out.print("Total number of seek operations = "+ seek_count + "\n"); 
		
		System.out.print("Seek Sequence is" + "\n"); 
		
		for (int i = 0; i < seek_sequence.size(); i++) 
		{ 
		 System.out.print(seek_sequence.get(i) + "\n"); 
} */
        //String[] data1 = {"CSCAN", String.valueOf(seek_sequence), textFieldStartPos.getText(), String.valueOf(seek_count)};
        DefaultTableModel tblModel = (DefaultTableModel) tableDisplay.getModel();
        tblModel.setValueAt(String.valueOf(seek_sequence), 4, 1);
        tblModel.setValueAt(textFieldStartPos.getText(), 4, 2);
        tblModel.setValueAt(String.valueOf(seek_count), 4, 3);

//METHOD1
//        String[] Array2 = new String[seek_sequence.size()];
//        Array2 = seek_sequence.toArray(Array2);
//        int[] ar = Arrays.asList(Array2).stream().mapToInt(Integer::parseInt).toArray();
//        return ar;
//METHOD2
//        String[] array = seek_sequence.toArray(new String[seek_sequence.size()]);
//        int[] ar = new int[array.length];
//        for (int i = 0; i < array.length; i++) {
//            ar[i] = Integer.parseInt(array[i]);
//        }
//        System.out.println(ar);
//        return ar;
        String[] stringArray = new String[seek_sequence.size()];
        for (int i = 0; i < seek_sequence.size(); i++) {
            stringArray[i] = seek_sequence.get(i).toString();
        }
        int[] ar = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            ar[i] = Integer.parseInt(stringArray[i]);
        }
        
        cscan1=seek_count;
        return ar;
    }
    
    public int[] CLOOK(int[] request){
              int size=request.length;
        int head = Integer.parseInt(textFieldStartPos.getText().toString());
        
         int seek_count = 0; 
        int distance, cur_track; 
     Vector<Integer> left = new Vector<Integer>(),
                right = new Vector<Integer>();
        Vector<Integer> seek_sequence = new Vector<Integer>();
 
  
    // Tracks on the left of the 
    // head will be serviced when 
    // once the head comes back 
    // to the beggining (left end) 
    for (int i = 0; i < size; i++) { 
        if (request[i] < head) 
            left.add(request[i]); 
        if (request[i] > head) 
            right.add(request[i]); 
    } 
  
    // Sorting left and right vectors 
    Collections.sort(left);
    Collections.sort(right);
  
  
    // First service the requests 
    // on the right side of the 
    // head 
    for (int i = 0; i < right.size(); i++) { 
        cur_track = right.get(i); 
  
        // Appending current track to seek sequence 
        seek_sequence.add(cur_track); 
  
        // Calculate absolute distance 
        distance = abs(cur_track - head); 
  
        // Increase the total count 
        seek_count += distance; 
  
        // Accessed track is now new head 
        head = cur_track; 
    } 
  
    // Once reached the right end 
    // jump to the last track that 
    // is needed to be serviced in 
    // left direction 
    seek_count += abs(head - left.get(0)); 
    head=left.get(0);
  
    // Now service the requests again 
    // which are left 
    for (int i = 0; i < left.size(); i++) { 
//        left.set(i, left.get(i)); 
        cur_track = left.get(i); 
//        cur_track -=left.get(i);
        // Appending current track to seek sequence 
        seek_sequence.add(cur_track);
  
        // Calculate absolute distance 
        distance = abs(cur_track - head); 
  
        // Increase the total count 
        seek_count += distance; 
  
        // Accessed track is now the new head 
        head = cur_track; 
    } 
    
    String[] stringArray = new String[seek_sequence.size()];
        for (int i = 0; i < seek_sequence.size(); i++) {
            stringArray[i] = seek_sequence.get(i).toString();
        }
        int[] ar = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            ar[i] = Integer.parseInt(stringArray[i]);
        }
       
        clook1=seek_count;
//        System.out.println("Clook seek seq : "+clook1);

        DefaultTableModel tblModel = (DefaultTableModel) tableDisplay.getModel();
        tblModel.setValueAt(String.valueOf(seek_sequence), 5, 1);
        tblModel.setValueAt(textFieldStartPos.getText(), 5, 2);
        tblModel.setValueAt(String.valueOf(seek_count),5, 3);
        return ar;
    }
    
    public int[] LOOK(int[] request, String direction){
        
        int size=request.length;
        int head = Integer.parseInt(textFieldStartPos.getText().toString());
        String given=direction;
        int seek_count = 0;
        int distance, cur_track;
        Vector<Integer> left = new Vector<Integer>(),
        right = new Vector<Integer>();
        Vector<Integer> seek_sequence = new Vector<Integer>();

        // Tracks on the left of the
        // head will be serviced when
        // once the head comes back
        // to the beggining (left end)
        for (int i = 0; i < size; i++) {
            if (request[i] < head)
            left.add(request[i]);
            if (request[i] > head)
            right.add(request[i]);
        }

        // Sorting left and right vectors
        Collections.sort(left);
        Collections.sort(right);

       // run the while loop two times. 
    // one by one scanning right 
    // and left side of the head 
    int run = 2; 
    while(run-- > 0) { 
        if (direction == "left") { 
            for (int i = left.size() - 1; i >= 0; i--) { 
                cur_track = left.get(i); 
  
                // appending current track to seek sequence 
                seek_sequence.add(cur_track); 
  
                // calculate absolute distance 
                distance = abs(cur_track - head); 
  
                // increase the total count 
                seek_count += distance; 
  
                // accessed track is now the new head 
                head = cur_track; 
            } 
            // reversing the direction 
            direction = "right"; 
        } 
        else if (direction == "right") { 
            for (int i = 0; i < right.size(); i++) { 
                cur_track = right.get(i); 
                // appending current track to seek sequence 
                seek_sequence.add(cur_track); 
  
                // calculate absolute distance 
                distance = abs(cur_track - head); 
  
                // increase the total count 
                seek_count += distance; 
  
                // accessed track is now new head 
                head = cur_track; 
            } 
            // reversing the direction 
            direction = "left"; 
        }
        
    } 
  
        String[] stringArray = new String[seek_sequence.size()];
        for (int i = 0; i < seek_sequence.size(); i++) {
            stringArray[i] = seek_sequence.get(i).toString();
        }
        int[] ar = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            ar[i] = Integer.parseInt(stringArray[i]);
        }
       
      
       if(given=="right"){
             look1=seek_count;
            DefaultTableModel tblModel = (DefaultTableModel) tableDisplay.getModel();
        tblModel.setValueAt(String.valueOf(seek_sequence), 6, 1);
        tblModel.setValueAt(textFieldStartPos.getText(), 6, 2);
        tblModel.setValueAt(String.valueOf(seek_count), 6, 3);
       }
       else if(given=="left"){
             look2=seek_count;
            DefaultTableModel tblModel = (DefaultTableModel) tableDisplay.getModel();
        tblModel.setValueAt(String.valueOf(seek_sequence), 7, 1);
        tblModel.setValueAt(textFieldStartPos.getText(), 7, 2);
        tblModel.setValueAt(String.valueOf(seek_count), 7, 3);
       }
        return ar;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        comboBoxAlgo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        textFieldStartPos = new javax.swing.JTextField();
        textFieldSectors = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDisplay = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        plotPanel = new javax.swing.JScrollPane();
        submit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1022, 572));
        setPreferredSize(new java.awt.Dimension(1022, 572));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("InputArea"));

        jLabel1.setText("StartPosition");

        comboBoxAlgo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FCFS", "SSTF", "SCAN(Right)", "SCAN(Left)", "CSCAN", "CLOOK", "LOOK(left)", "LOOK(right)" }));
        comboBoxAlgo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxAlgoActionPerformed(evt);
            }
        });

        jLabel2.setText("Algorithm");

        jLabel3.setText("Tracks");

        jButton1.setText("AutoGen");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        textFieldStartPos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldStartPosActionPerformed(evt);
            }
        });

        textFieldSectors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldSectorsActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("DISK SCHEDULING ALGORITHMS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textFieldStartPos, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(textFieldSectors, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addComponent(comboBoxAlgo, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(187, 187, 187))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldStartPos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldSectors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(comboBoxAlgo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        tableDisplay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"FCFS", null, null, null},
                {"STF", null, null, null},
                {"SCAN(Right)", null, null, null},
                {"SCAN(Left)", null, null, null},
                {"CSCAN", null, null, null},
                {"CLOOK", null, null, null},
                {"LOOK(Right)", null, null, null},
                {"LOOK(Left)", null, null, null}
            },
            new String [] {
                "Algorithm", "Process Order", "Start Position", "Seek time"
            }
        ));
       jScrollPane1.setViewportView(tableDisplay);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("EXIT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        plotPanel.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        plotPanel.setToolTipText("");
        plotPanel.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        plotPanel.setEnabled(false);

        submit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        submit.setText("SUBMIT");
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(186, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(plotPanel)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(206, 206, 206))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(268, 268, 268)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(421, 421, 421)
                        .addComponent(submit, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(430, 430, 430)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(submit, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(plotPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textFieldStartPosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldStartPosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldStartPosActionPerformed

    private void textFieldSectorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldSectorsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldSectorsActionPerformed

    private void comboBoxAlgoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxAlgoActionPerformed

        // TODO add your handling code here:

    }//GEN-LAST:event_comboBoxAlgoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Random r = new Random();
        int[] random = new int[8];
        for (int i = 0; i < random.length; i++) {
            random[i] = r.nextInt(100 - 1) + 1; // storing random integers in an array
            //System.out.println(random[i]); // printing each array element
        }

        String[] strRandom = new String[random.length];
        for (int i = 0; i < random.length; i++) {
            strRandom[i] = String.valueOf(random[i]);
        }

        String result = "";

        if (strRandom.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (String s : strRandom) {
                sb.append(s).append(",");
            }

            result = sb.deleteCharAt(sb.length() - 1).toString();
        }
        textFieldSectors.setText(result);

    }//GEN-LAST:event_jButton1ActionPerformed


    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
        // TODO add your handling code here:
        String sector = textFieldSectors.getText();

        String[] numbers = sector.split(",");

        List<String> SectorString = Arrays.asList(numbers);

        ArrayList<String> SectorNumber = new ArrayList<String>(SectorString);

//				System.out.println("list from comma separated String : " + SectorNumber); 
//				System.out.println("size of ArrayList : " + SectorNumber.size());
        Object[] ArraySector = SectorNumber.toArray();
        int[] request = new int[ArraySector.length];
        for (int i = 0; i < ArraySector.length; i++) {
            request[i] = Integer.valueOf(numbers[i]);
        }
        
      

        if (comboBoxAlgo.getSelectedItem().equals("FCFS")) {
            array = FCFS(request);
            initUI();
            
            SSTF(request);
            SCAN(request, "right");
            SCAN(request, "left");
            CSCAN(request);
            CLOOK(request);
            LOOK(request,"right");
            LOOK(request,"left");

        } else if (comboBoxAlgo.getSelectedItem().equals("SSTF")) {
            FCFS(request);
            array = SSTF(request);
            SCAN(request, "right");
            SCAN(request, "left");
            CSCAN(request);
             CLOOK(request);
             LOOK(request,"right");
            LOOK(request,"left");
            initUI();
 

        } else if (comboBoxAlgo.getSelectedItem().equals("SCAN(Left)")) {
            FCFS(request);
            SSTF(request);
            
            array = SCAN(request, "left");
//            array = SCAN(request, "right");
            LOOK(request,"right");
            LOOK(request,"left");
            CLOOK(request);
            CSCAN(request);
//            initUI();
        } else if (comboBoxAlgo.getSelectedItem().equals("SCAN(Right)")) {
            FCFS(request);
            SSTF(request);

            array = SCAN(request, "right");
            CSCAN(request);
            CLOOK(request);
            LOOK(request,"right");
            LOOK(request,"left");
            initUI();
        } else if (comboBoxAlgo.getSelectedItem().equals("CSCAN")) {
            FCFS(request);
            SSTF(request);
            SCAN(request, "right");
            array = CSCAN(request);
            SCAN(request,"left");
            CLOOK(request);
            LOOK(request,"right");
            LOOK(request,"left");
            initUI();
            
        }
        else if (comboBoxAlgo.getSelectedItem().equals("CLOOK")) {
            FCFS(request);
            SSTF(request);
            CSCAN(request);
            SCAN(request, "right");
            SCAN(request,"left");
            array = CLOOK(request);
            LOOK(request,"right");
            LOOK(request,"left");
            initUI();   
        }
        else if (comboBoxAlgo.getSelectedItem().equals("LOOK(left)")) {
            FCFS(request);
            SSTF(request);
             CSCAN(request);
             SCAN(request, "right");
            SCAN(request,"left");
            CLOOK(request);
            LOOK(request,"right");
            array=LOOK(request,"left");
//            initUI();   
        }
        else if (comboBoxAlgo.getSelectedItem().equals("LOOK(right)")) {
            FCFS(request);
            SSTF(request);
             CSCAN(request);
             SCAN(request, "right");
            SCAN(request,"left");
            CLOOK(request);
            LOOK(request,"left");
            array=LOOK(request,"right");
            initUI();   
        }
        
      GUI demo = new GUI( "Disk Scheduling Algorithms" );  
      demo.setSize( 560 , 367 );    
      RefineryUtilities.centerFrameOnScreen( demo );    
      demo.setVisible( true );


    }//GEN-LAST:event_submitActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
       Menu a=new Menu();
       a.setLocationRelativeTo(null);
       a.setVisible(true);
       dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     * 
     */
   
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUI a = new GUI();
                a.setLocationRelativeTo(null);
                a.setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboBoxAlgo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane plotPanel;
    private javax.swing.JButton submit;
    private javax.swing.JTable tableDisplay;
    private javax.swing.JTextField textFieldSectors;
    private javax.swing.JTextField textFieldStartPos;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
