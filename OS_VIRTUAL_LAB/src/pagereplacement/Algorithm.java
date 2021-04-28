package pagereplacement;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet; 
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList; 
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;
import java.util.StringTokenizer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
public class Algorithm extends javax.swing.JFrame {
	// Method to find page faults using FIFO 
	static int pageFaultsFifo(int pages[], int n, int capacity) 
	{
            int s1[]; 
            s1 = new int[20];
		// To represent set of current pages. We use 
		// an unordered_set so that we quickly check 
		// if a page is present in set or not 
		HashSet<Integer> s = new HashSet<>(capacity); 
		// To store the pages in FIFO manner 
		Queue<Integer> indexes = new LinkedList<>() ; 
		// Start from initial page 
		int page_faults = 0; 
		for (int i=0; i<n; i++) 
		{ 
			// Check if the set can hold more pages 
			if (s.size() < capacity) 
			{ 
				// Insert it into set if not present 
				// already which represents page fault 
				if (!s.contains(pages[i])) 
				{ 
					s.add(pages[i]); 
					// increment page fault 
					page_faults++; 
					// Push the current page into the queue 
					indexes.add(pages[i]); 
				} 
			} 
			// If the set is full then need to perform FIFO 
			// i.e. remove the first page of the queue from 
			// set and queue both and insert the current page 
			else
			{ 
				// Check if current page is not already 
				// present in the set 
				if (!s.contains(pages[i])) 
				{ 
					//Pop the first page from the queue 
					int val = indexes.peek(); 
					indexes.poll(); 
					// Remove the indexes page 
					s.remove(val); 
					// insert the current page 
					s.add(pages[i]); 
					// push the current page into 
					// the queue 
					indexes.add(pages[i]); 
					// Increment page faults 
					page_faults++; 
				} 
			} 
                  		} 
                return page_faults; 
	} 
	static int pageFaultsLru(int pages[], int n, int capacity) 
    { 
        // To represent set of current pages. We use 
        // an unordered_set so that we quickly check 
        // if a page is present in set or not 
        HashSet<Integer> s = new HashSet<>(capacity); 
        // To store least recently used indexes 
        // of pages. 
        HashMap<Integer, Integer> indexes = new HashMap<>(); 
        // Start from initial page 
        int page_faults = 0; 
        for (int i=0; i<n; i++) 
        { 
            // Check if the set can hold more pages 
            if (s.size() < capacity) 
            { 
                // Insert it into set if not present 
                // already which represents page fault 
                if (!s.contains(pages[i])) 
                { 
                    s.add(pages[i]); 
                    // increment page fault 
                    page_faults++; 
                } 
                // Store the recently used index of 
                // each page 
                indexes.put(pages[i], i); 
            } 
            // If the set is full then need to perform lru 
            // i.e. remove the least recently used page 
            // and insert the current page 
            else
            { 
                // Check if current page is not already 
                // present in the set 
                if (!s.contains(pages[i])) 
                { 
                    // Find the least recently used pages 
                    // that is present in the set 
                    int lru = Integer.MAX_VALUE, val=Integer.MIN_VALUE;  
                    Iterator<Integer> itr = s.iterator(); 
                    while (itr.hasNext()) { 
                        int temp = itr.next(); 
                        if (indexes.get(temp) < lru) 
                        { 
                            lru = indexes.get(temp); 
                            val = temp; 
                        } 
                    } 
                    // Remove the indexes page 
                    s.remove(val); 
                   //remove lru from hashmap 
                   indexes.remove(val); 
                    // insert the current page 
                    s.add(pages[i]); 
                    // Increment page faults 
                    page_faults++; 
                } 
                // Update the current page index 
                indexes.put(pages[i], i); 
            } 
        }    
       return page_faults; 
    } 
    private Object obj;
//    private static int page_faults(int page, int[] pf, int n) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    private boolean search(int key, ArrayList<Integer> fr)
{
	for (int i = 0; i < fr.size(); i++)
	{
		if (fr.get(i) == key)
		{
			return true;
		}
	}
	return false;
}
// Function to find the frame that will not be used 
// recently in future after given index in pg[0..pn-1] 
private int predict(int[] pg, ArrayList<Integer> fr, int pn, int index)
{
	// Store the index of pages which are going 
	// to be used recently in future 
	int res = -1;
	int farthest = index;
	for (int i = 0; i < fr.size(); i++)
	{
		int j;
		for (j = index; j < pn; j++)
		{
			if (fr.get(i) == pg[j])
			{
				if (j > farthest)
				{
					farthest = j;
					res = i;
				}
				break;
			}
		}
		// If a page is never referenced in future, 
		// return it. 
		if (j == pn)
		{
			return i;
		}
	}
	// If all of the fn were not in future, 
	// return any of them, we return 0. Otherwise 
	// we return res. 
	return (res == -1) ? 0 : res;
}
private int pageFaultsOpr(int[] pg, int pn, int fn)
{
	// Create an array for given number of 
	// fn and initialize it as empty. 
	ArrayList<Integer> fr = new ArrayList<Integer>();
	// Traverse through page reference array 
	// and check for miss and hit. 
	int hit = 0;
	for (int i = 0; i < pn; i++)
	{
		// Page found in a frame : HIT 
		if (search(pg[i], fr))
		{
			hit++;
			continue;
		}
		// Page not found in a frame : MISS 
		// If there is space available in fn. 
		if (fr.size() < fn)
		{
			fr.add(pg[i]);
		}
		// Find the page to be replaced. 
		else
		{
			int j = predict(pg, fr, pn, i + 1);
			fr.set(j, pg[i]);
		}
	}
	return hit;
}
	static int pageFaultsRandom(int pages[], int n, int capacity) {
//            String s = String.valueOf(n);
//            System.out.println(s);
//	//	ArrayList<Integer> fr = new ArrayList<Integer>();
//		int faults = 0;
//		Random rdm = new Random();
//		int[] array = new int [capacity];
//		for(int i = 0; i < capacity; i++){			//fill fn with -1, because they are
//			array[i] = -1;							//are first initialized with 0 and that alters
//		}											//the result
//		int pointer = rdm.nextInt(capacity);
//		StringTokenizer tok = new StringTokenizer(s);
//		while(tok.hasMoreTokens()){
//			int value = Integer.parseInt(tok.nextToken());
//			if (isInfn.isInfn(value, capacity, array)){
//			} 
//			else {
//                            faults++;
//                            array[pointer] = value;
//                            pointer = rdm.nextInt(capacity);
//                    }
//		}
//		return faults;
//		int s1[]; 
//        s1 = new int[20];
	// To represent set of current pages. We use 
	// an unordered_set so that we quickly check 
	// if a page is present in set or not 
	Random rdm = new Random();
	int pointer = rdm.nextInt(capacity);
	HashSet<Integer> s = new HashSet<>(capacity); 
	// To store the pages in FIFO manner 
	ArrayList<Integer> indexes = new ArrayList<>() ; 
	// Start from initial page 
	int page_faults = 0; 
	for (int i=0; i<n; i++) 
	{ 
		// Check if the set can hold more pages 
		if (s.size() < capacity) 
		{ 
			// Insert it into set if not present 
			// already which represents page fault 
			if (!s.contains(pages[i])) 
			{ 
				s.add(pages[i]); 
				// increment page fault 
				page_faults++; 
				// Push the current page into the queue 
				indexes.add(pages[i]); 
			} 
		} 
		// If the set is full then need to perform Random 
		// i.e. remove the random page of the queue from 
		// set and queue both and insert the current page 
		else
		{ 
			// Check if current page is not already 
			// present in the set 
			if (!s.contains(pages[i])) 
			{ 
				//Pop the first page from the queue 
				int val = indexes.get(pointer); 
//				indexes.poll(); 
				// Remove the indexes page 
				s.remove(val); 
				// insert the current page 
				s.add(pages[i]); 
				// push the current page into 
				// the queue 
				indexes.set(pointer,pages[i]);
				pointer = rdm.nextInt(capacity);
				System.out.println("Value:"+val);
				// Increment page faults 
				page_faults++; 
			} 
		} 
    } 
            return page_faults;
}
 static int pageFaultsLifo(int pages[], int n, int capacity) {
	 HashSet<Integer> s = new HashSet<>(capacity); 
		// To store the pages in FIFO manner 
	 Stack<Integer> stack = new Stack<>();
	 int page_faults = 0; 
		for (int i=0; i<n; i++) 
		{ 
			// Check if the set can hold more pages 
			if (s.size() < capacity) 
			{ 
				// Insert it into set if not present 
				// already which represents page fault 
				if (!s.contains(pages[i])) 
				{ 
					s.add(pages[i]); 
					// increment page fault 
					page_faults++; 
					// Push the current page into the queue 
					stack.add(pages[i]); 
				} 
			} 
			// If the set is full then need to perform FIFO 
			// i.e. remove the first page of the queue from 
			// set and queue both and insert the current page 
			else
			{ 
				// Check if current page is not already 
				// present in the set 
				if (!s.contains(pages[i])) 
				{ 
					//Pop the first page from the queue 
					int val = stack.peek(); 
					stack.pop(); 
					// Remove the indexes page 
					s.remove(val); 
					// insert the current page 
					s.add(pages[i]); 
					// push the current page into 
					// the queue 
					stack.add(pages[i]); 
					// Increment page faults 
					page_faults++; 
				} 
			} 
               		} 
             return page_faults; 
 }
    /**
     * Creates new form Algorithm
     */
    public Algorithm() {
    	setPreferredSize(new Dimension(1022, 572));
        initComponents();
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
        jPanel1.setMaximumSize(new Dimension(32780, 32767));
        jPanel1.setPreferredSize(new Dimension(40, 10));
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmb_GetCap = new javax.swing.JComboBox<>();
        cmb_GetCap.setBackground(new Color(0, 128, 128));
        AlgoSelect = new javax.swing.JComboBox<>();
        AlgoSelect.setBackground(new Color(0, 128, 128));
        txtF_GetString = new javax.swing.JTextField();
        btn_Submit = new javax.swing.JButton();
        btn_Submit.setFont(new Font("Tahoma", Font.BOLD, 12));
        btn_Submit.setBackground(new Color(0,150,136));
        btn_AutoGen = new javax.swing.JButton();
        btn_AutoGen.setBackground(new Color(0,150,136));
        txtF_ShowHits = new javax.swing.JTextField();
        txtF_ShowHits.setEditable(false);
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtF_ShowFaults = new javax.swing.JTextField();
        txtF_ShowFaults.setEditable(false);
        jButton2 = new javax.swing.JButton();
        jButton2.setFont(new Font("Tahoma", Font.BOLD, 12));
        jButton2.setBackground(new Color(0,150,136));
        jLabel1 = new javax.swing.JLabel();
        jLabel1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        btnFifoGraph = new javax.swing.JButton();
        btnFifoGraph.setBackground(new Color(0,150,136));
        btnFifoGraph.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnLruGraph = new javax.swing.JButton();
        btnLruGraph.setBackground(new Color(0,150,136));
        btnLruGraph.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnOprGraph = new javax.swing.JButton();
        btnOprGraph.setBackground(new Color(0,150,136));
        btnOprGraph.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnRandomGraph = new javax.swing.JButton();
        btnRandomGraph.setBackground(new Color(0,150,136));
        btnRandomGraph.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnLifoGraph = new javax.swing.JButton();
        btnLifoGraph.setBackground(new Color(0,150,136));
        btnLifoGraph.setFont(new Font("Tahoma", Font.PLAIN, 12));
        JScrollPane graphPanel = new JScrollPane();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Algorithm");
        setBackground(new java.awt.Color(0, 153, 153));
        setForeground(new java.awt.Color(0, 153, 153));
        setMinimumSize(new java.awt.Dimension(1293, 800));
        setPreferredSize(new java.awt.Dimension(1293, 800));
        setResizable(false);
        jPanel1.setBackground(UIManager.getColor("ComboBox.buttonShadow"));
        jLabel2.setFont(new Font("Verdana", Font.BOLD, 14)); // NOI18N
        jLabel2.setText("No. of frames");
        jLabel3.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel3.setText("Enter String");
        jLabel4.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel4.setText("Choose Algorithm");
        cmb_GetCap.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" ,"5","6"}));
        cmb_GetCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_GetCapActionPerformed(evt);
            }
        });
        AlgoSelect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[---Select---]", "Fifo", "Lru", "Opr", "Random", "Lifo" }));
       btnFifoGraph.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	DefaultCategoryDataset dataset= new DefaultCategoryDataset();
            	double x[] = {1,2,3,4,5,6,7,8}; // x axis no of frames
            	double y[] = new double[8];
            	  //String selectedValue = (String) AlgoSelect.getSelectedItem();
                      String sector = txtF_GetString.getText();
                      String[] numbers = sector.split(",");
                      List<String> SectorString = Arrays.asList(numbers);
                      ArrayList<String> SectorNumber = new ArrayList<String>(SectorString);
              //				System.out.println("list from comma separated String : " + SectorNumber); 
              //				System.out.println("size of ArrayList : " + SectorNumber.size());
                      Object[] ArraySector = SectorNumber.toArray();
                      int[] pages = new int[ArraySector.length];
                      for (int i = 0; i < ArraySector.length; i++) {
                          pages[i] = Integer.valueOf(numbers[i]);
                          System.out.println(pages[i]);
                      }
                     // pageFaultsFifo
            	 for (int i = 0;i<=5;i++)
            	 {
            		 y[i]=pageFaultsFifo(pages,pages.length,(int)x[i]);
              		 dataset.setValue(y[i],"Faults",String.valueOf(x[i]));
            	 }
                      JFreeChart chart= ChartFactory.createLineChart("Graph of First In First Out","String","Fault", dataset, PlotOrientation.VERTICAL, false, true, false); //title x-axis then y-axis+
                      CategoryPlot p=chart.getCategoryPlot();
                      LineAndShapeRenderer renderer = new LineAndShapeRenderer();
                      p.setRenderer(renderer);                     
                      chart.getPlot().setBackgroundPaint(Color.BLACK);
                     p.setDomainGridlinesVisible(true);              
                      p.setDomainGridlinePaint(Color.WHITE);
                      p.getRenderer().setSeriesPaint(0, new Color(0, 255, 255));                     
                      p.setRangeGridlinePaint(Color.WHITE); // BLUE
                      ChartPanel CP =new ChartPanel(chart);
                      CP.setVisible(true);
                      CP.setPreferredSize(new java.awt.Dimension(graphPanel.getWidth(), graphPanel.getHeight()));
                      graphPanel.setViewportView(CP);
            }
        });
       
       btnOprGraph.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
           	 DefaultCategoryDataset dataset= new DefaultCategoryDataset();
           	double x[] = {1,2,3,4,5,6,7,8}; // x axis no of frames
           	double y[] = new double[8];
           	  //String selectedValue = (String) AlgoSelect.getSelectedItem();
                     String sector = txtF_GetString.getText();
                     String[] numbers = sector.split(",");
                     List<String> SectorString = Arrays.asList(numbers);
                     ArrayList<String> SectorNumber = new ArrayList<String>(SectorString);

             //				System.out.println("list from comma separated String : " + SectorNumber); 
             //				System.out.println("size of ArrayList : " + SectorNumber.size());
                     Object[] ArraySector = SectorNumber.toArray();
                     int[] pages = new int[ArraySector.length];
                     for (int i = 0; i < ArraySector.length; i++) {
                         pages[i] = Integer.valueOf(numbers[i]);
                         System.out.println(pages[i]);
                     }
                     // pageFaultsFifo
           	 for (int i = 0;i<=5;i++)
           	 {
           		 y[i]=pages.length - pageFaultsOpr(pages,pages.length,(int)x[i]);
           		 dataset.setValue(y[i],"Faults",String.valueOf(x[i]));
           	 }
                     JFreeChart chart= ChartFactory.createLineChart("Graph of Optimal Page Replacement","String","Fault", dataset, PlotOrientation.VERTICAL, false, true, false); //title x-axis then y-axis               
                     CategoryPlot p=chart.getCategoryPlot();
                     LineAndShapeRenderer renderer = new LineAndShapeRenderer();
                     p.setRenderer(renderer);                    
                    chart.getPlot().setBackgroundPaint(Color.black);
                    p.setDomainGridlinesVisible(true);            
                     p.setDomainGridlinePaint(Color.WHITE);
                     p.getRenderer().setSeriesPaint(0, new Color(0, 255, 255));                    
                     p.setRangeGridlinePaint(Color.WHITE); 
                     ChartPanel CP =new ChartPanel(chart);
                     CP.setVisible(true);
                     CP.setPreferredSize(new java.awt.Dimension(graphPanel.getWidth(), graphPanel.getHeight()));
                     graphPanel.setViewportView(CP);
           }
       });
       btnRandomGraph.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
           	 DefaultCategoryDataset dataset= new DefaultCategoryDataset();
           	double x[] = {1,2,3,4,5,6,7,8}; // x axis no of frames
           	double y[] = new double[8];
           
           	  //String selectedValue = (String) AlgoSelect.getSelectedItem();
                     String sector = txtF_GetString.getText();
                     String[] numbers = sector.split(",");
                     List<String> SectorString = Arrays.asList(numbers);
                     ArrayList<String> SectorNumber = new ArrayList<String>(SectorString);
             //				System.out.println("list from comma separated String : " + SectorNumber); 
             //				System.out.println("size of ArrayList : " + SectorNumber.size());
                     Object[] ArraySector = SectorNumber.toArray();
                     int[] pages = new int[ArraySector.length];
                     for (int i = 0; i < ArraySector.length; i++) {
                         pages[i] = Integer.valueOf(numbers[i]);
                         System.out.println(pages[i]);
                     }
                     // pageFaultsFifo
           	 for (int i = 0;i<=5;i++)
           	 {
           		 y[i]=pageFaultsRandom(pages,pages.length,(int)x[i]);
           		 dataset.setValue(y[i],"Faults",String.valueOf(x[i]));       	
           	 }
                     JFreeChart chart= ChartFactory.createLineChart("Graph of Random Page Replacement","String","Fault", dataset, PlotOrientation.VERTICAL, false, true, false); //title x-axis then y-axis
                     CategoryPlot p=chart.getCategoryPlot();
                     LineAndShapeRenderer renderer = new LineAndShapeRenderer();
                     p.setRenderer(renderer);                   
                    chart.getPlot().setBackgroundPaint(Color.black);
                    p.setDomainGridlinesVisible(true);            
                     p.setDomainGridlinePaint(Color.WHITE);
                     p.getRenderer().setSeriesPaint(0, new Color(0, 255, 255));                     
                     p.setRangeGridlinePaint(Color.WHITE); 
                     ChartPanel CP =new ChartPanel(chart);
                     CP.setVisible(true);
                     CP.setPreferredSize(new java.awt.Dimension(graphPanel.getWidth(), graphPanel.getHeight()));
                     graphPanel.setViewportView(CP);                	
                    //Creating the instance of linechart with the specified axis  
           }
       });
          btnLruGraph.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
           	 DefaultCategoryDataset dataset= new DefaultCategoryDataset();
           	double x[] = {1,2,3,4,5,6,7,8}; // x axis no of frames
           	double y[] = new double[8];
           	  //String selectedValue = (String) AlgoSelect.getSelectedItem();
                     String sector = txtF_GetString.getText();
                     String[] numbers = sector.split(",");
                     List<String> SectorString = Arrays.asList(numbers);
                     ArrayList<String> SectorNumber = new ArrayList<String>(SectorString);
             //				System.out.println("list from comma separated String : " + SectorNumber); 
             //				System.out.println("size of ArrayList : " + SectorNumber.size());
                     Object[] ArraySector = SectorNumber.toArray();
                     int[] pages = new int[ArraySector.length];
                     for (int i = 0; i < ArraySector.length; i++) {
                         pages[i] = Integer.valueOf(numbers[i]);
                         System.out.println(pages[i]);
                     }
                     // pageFaultsFifo
           	 for (int i = 0;i<=5;i++)
           	 {
           		 y[i]=pageFaultsLru(pages,pages.length,(int)x[i]);
           		 dataset.setValue(y[i],"Faults",String.valueOf(x[i]));
           	 }                
                     JFreeChart chart= ChartFactory.createLineChart("Graph of Least Recently Used","String","Fault", dataset, PlotOrientation.VERTICAL, false, true, false); //title x-axis then y-axis
                     CategoryPlot p=chart.getCategoryPlot();
                     LineAndShapeRenderer renderer = new LineAndShapeRenderer();
                     p.setRenderer(renderer);                     
                    chart.getPlot().setBackgroundPaint(Color.black);
                    p.setDomainGridlinesVisible(true);             
                     p.setDomainGridlinePaint(Color.WHITE);
                     p.getRenderer().setSeriesPaint(0, new Color(0, 255, 255));                     
                     p.setRangeGridlinePaint(Color.WHITE);            
                     ChartPanel CP =new ChartPanel(chart);
                     CP.setVisible(true);
                     CP.setPreferredSize(new java.awt.Dimension(graphPanel.getWidth(), graphPanel.getHeight()));
                     graphPanel.setViewportView(CP);              	
                    //Creating the instance of linechart with the specified axis  
           }
       });      
       btnLifoGraph.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
           	DefaultCategoryDataset dataset= new DefaultCategoryDataset();
           	double x[] = {1,2,3,4,5,6,7,8}; // x axis no of frames
           	double y[] = new double[8];
           	  //String selectedValue = (String) AlgoSelect.getSelectedItem();
                     String sector = txtF_GetString.getText();
                     String[] numbers = sector.split(",");
                     List<String> SectorString = Arrays.asList(numbers);
                     ArrayList<String> SectorNumber = new ArrayList<String>(SectorString);
             //				System.out.println("list from comma separated String : " + SectorNumber); 
             //				System.out.println("size of ArrayList : " + SectorNumber.size());
                     Object[] ArraySector = SectorNumber.toArray();
                     int[] pages = new int[ArraySector.length];
                     for (int i = 0; i < ArraySector.length; i++) {
                         pages[i] = Integer.valueOf(numbers[i]);
                         System.out.println(pages[i]);
                     }
                     // pageFaultsFifo
           	 for (int i = 0;i<=5;i++)
           	 {
           		 y[i]=pageFaultsLifo(pages,pages.length,(int)x[i]);
           		 dataset.setValue(y[i],"Faults",String.valueOf(x[i]));
           	 }                 
                     JFreeChart chart= ChartFactory.createLineChart("Graph of Last In First Out","String","Fault", dataset, PlotOrientation.VERTICAL, false, true, false); //title x-axis then y-axis
                     CategoryPlot p=chart.getCategoryPlot();
                     LineAndShapeRenderer renderer = new LineAndShapeRenderer();
                     p.setRenderer(renderer);                    
                    chart.getPlot().setBackgroundPaint(Color.black);
                    p.setDomainGridlinesVisible(true);            
                     p.setDomainGridlinePaint(Color.WHITE);
                     p.getRenderer().setSeriesPaint(0, new Color(0, 255, 255));                   
                     p.setRangeGridlinePaint(Color.WHITE); 
                     ChartPanel CP =new ChartPanel(chart);
                     CP.setVisible(true);
                     CP.setPreferredSize(new java.awt.Dimension(graphPanel.getWidth(), graphPanel.getHeight()));
                     graphPanel.setViewportView(CP);           	 
                    //Creating the instance of linechart with the specified axis  
           }
       });
        btn_Submit.setText("Submit");
        btn_Submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SubmitActionPerformed(evt);
            }
        });
        btn_AutoGen.setText("AutoGen");
        btn_AutoGen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AutoGenActionPerformed(evt);
            }
        });
        jLabel6.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel6.setText("Hit");
        jLabel7.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel7.setText("Fault");
        txtF_ShowFaults.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtF_ShowFaultsActionPerformed(evt);
            }
        });
        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jLabel1.setBackground(new Color(0, 128, 128));
        jLabel1.setFont(new java.awt.Font("Verdana", 1, 48)); // NOI18N
        jLabel1.setForeground(new Color(230, 230, 250));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ALGORITHMS");
        jLabel1.setOpaque(true);
        btnFifoGraph.setText("Fifo Graph");
        btnLruGraph.setText("Lru Graph");
        btnOprGraph.setText("Opr Graph");
        btnRandomGraph.setText("Random Graph");
        btnLifoGraph.setText("Lifo Graph");    
       btnCompare = new JButton("COMPARE ALL");
       btnCompare.setBackground(new Color(0,128,128));
       btnCompare.setFont(new Font("Tahoma", Font.BOLD, 12));
       btnCompare.addActionListener(new java.awt.event.ActionListener() {
           
		public void actionPerformed(java.awt.event.ActionEvent evt) {
			
			String sector = txtF_GetString.getText();
            String[] numbers = sector.split(",");
            List<String> SectorString = Arrays.asList(numbers);
            ArrayList<String> SectorNumber = new ArrayList<String>(SectorString);
    //				System.out.println("list from comma separated String : " + SectorNumber); 
    //				System.out.println("size of ArrayList : " + SectorNumber.size());
            Object[] ArraySector = SectorNumber.toArray();
            int[] pages = new int[ArraySector.length];
            for (int i = 0; i < ArraySector.length; i++) {
                pages[i] = Integer.valueOf(numbers[i]);
                System.out.println(pages[i]);
            }           
            String cap = (String) cmb_GetCap.getSelectedItem();
            int capacity = Integer.valueOf(cap);
           int fifo = pageFaultsFifo(pages, pages.length, capacity);
            int lru = pageFaultsLru(pages, pages.length, capacity);
            int random = pageFaultsRandom(pages, pages.length, capacity);
            int opr = pages.length - pageFaultsOpr(pages, pages.length, capacity);
            int lifo = pageFaultsLifo(pages, pages.length, capacity);
    	   
    	   DefaultPieDataset dataset = new DefaultPieDataset();
    	   dataset.setValue("FIfo", new Integer(fifo));
    	   dataset.setValue("Lru", new Integer(lru));
    	   dataset.setValue("Random", new Integer(random));
    	   dataset.setValue("Lifo", new Integer(lifo));
    	   dataset.setValue("Opr", new Integer(opr));
    	   		JFreeChart piechart= ChartFactory.createPieChart("Comparsion of  Algorithms (Faults)", dataset,true, true,true); //title x-axis then y-axis+
        	   PiePlot plot = (PiePlot) piechart.getPlot(); 
        	   plot.setForegroundAlpha(0.10f);
        	   plot.setStartAngle(290);
               plot.setDirection(Rotation.CLOCKWISE);
               plot.setForegroundAlpha(0.5f);
             
        	   ChartFrame frame = new ChartFrame("Comparison Of all Algorithm", piechart);
        	   frame.setVisible(true);
        	   frame.setSize(500,500);
        	   return;
           	   }
       });
       
       JButton VButton = new JButton("visualization");
       VButton.setBackground(new Color(0, 150, 136));
       VButton.setFont(new Font("Tahoma", Font.BOLD, 12));
       VButton.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent e) {
       	 String selectedValue = (String) AlgoSelect.getSelectedItem();   
         if(selectedValue=="Fifo")
         { 
         int frame_no  = Integer.parseInt((String)(cmb_GetCap.getSelectedItem()));			//rename FRAME combobox
         int page_fault=0;

         ArrayList<Integer> values = new ArrayList<>();

         String reference_string=txtF_GetString.getText();						//rename STRING textfield

 	for(int i=0;i<reference_string.length();i++)
         {
             if(reference_string.charAt(i)==',')
                 ;
             else
             {
                 String s="";
                 s=s+reference_string.charAt(i);
                 values.add(Integer.parseInt(s));
             }
         }
 
         JTable fifo_table = new JTable(frame_no+1,values.size()+1);
         Font font = new Font("Tohima", Font.BOLD, 16);
         fifo_table.setFont(font);
         fifo_table.setRowHeight(fifo_table.getRowHeight() + 40);
         for(int r=0;r<frame_no;r++)
         {
        	 
             fifo_table.setValueAt("Frame: "+(r+1), r, 0);
         }

         ArrayList<Integer> FIFO = new ArrayList<>();
         int var=0;
         for(int i=0;i<values.size();i++)
         {
             boolean pf=false;
             if(FIFO.size()<frame_no)
             {
                 if(!FIFO.contains(values.get(i)))
                 {
                     FIFO.add(values.get(i));
                     page_fault++;
                     pf=true;
                 }
             }
             else
             {
                 if(!FIFO.contains(values.get(i)))
                 {
                     FIFO.remove(var);
                     FIFO.add(var,values.get(i));
                     page_fault++;
                     var++;
                     var=var%frame_no;
                     pf=true;
                 }
             }
             for(int j=0;j<FIFO.size();j++)
             {
                 fifo_table.setValueAt(FIFO.get(j), j, i+1);
             }

             if(pf==true)
                 fifo_table.setValueAt("Miss", frame_no, i+1);
             else
                 fifo_table.setValueAt("Hit", frame_no, i+1);
         }

//         JLabel fifo_label= new JLabel("  FIFO:");
//         JLabel fifo_pf = new JLabel("  Page Faults: "+page_fault);
         TableColumnModel column = fifo_table.getColumnModel();
         javax.swing.table.TableColumn tablecolumn = column.getColumn(0);
         
         tablecolumn.setHeaderValue("String"); 
         JTableHeader tableHeader = fifo_table.getTableHeader();
         Font headerFont = new Font("tohima", Font.BOLD, 14);
         tableHeader.setFont(headerFont);
         fifo_table.getTableHeader().setReorderingAllowed(false);
         
         for(int c=1;c<values.size()+1;c++)
         {
             tablecolumn = column.getColumn(c);
             tablecolumn.setHeaderValue(values.get(c-1));
             fifo_table.setBackground(new Color		(0,139,139));
             fifo_table.setForeground(new Color		(230,230,250));
         }
         repaint();
         revalidate();
         graphPanel.setViewportView(fifo_table);
            }
         if(selectedValue=="Lru")
         {
        	
             int frame_no = Integer.parseInt((String)(cmb_GetCap.getSelectedItem()));				//rename FRAME combobox

             int page_fault=0;

             ArrayList<Integer> values = new ArrayList<>();

             String reference_string=txtF_GetString.getText();							//rename STRING textfield

             for(int i=0;i<reference_string.length();i++)
             {
                 if(reference_string.charAt(i)==',')
                     ;
                 else
                 {
                     String s="";
                     s=s+reference_string.charAt(i);
                     values.add(Integer.parseInt(s));
                 }
             }

             LinkedHashSet<Integer> s = new LinkedHashSet<>(frame_no);
             LinkedHashMap<Integer, Integer> LRU = new LinkedHashMap<>();
             HashMap<Integer, Integer> positions = new HashMap<>();
             JTable lru_table = new JTable(frame_no+1,values.size()+1);
             Font font = new Font("Tohima", Font.BOLD, 16);
             lru_table.setFont(font);
             lru_table.setRowHeight(lru_table.getRowHeight() + 40);
             for(int r=0;r<frame_no;r++)
             {
                 lru_table.setValueAt("Frame: "+(r+1), r, 0);
             }
             int k=0;
             for(int i=0;i<values.size();i++)
             {

                 boolean pf=false;
                 if(s.size()<frame_no)
                 {
                     if(!s.contains(values.get(i)))
                     {
                         s.add(values.get(i));
                         page_fault++;
                         pf=true;
                         positions.put(k,values.get(i));
                         LRU.put(values.get(i),k);
                         k++;
                     }    
                 }
                 else
                 {
                     if(!s.contains(values.get(i)))
                     {
                         int lru = Integer.MAX_VALUE, val=Integer.MIN_VALUE;
                         Iterator<Integer> itr = s.iterator(); 
                         while (itr.hasNext()) { 
                             int temp = itr.next(); 
                             if (LRU.get(temp) < lru) 
                             { 
                                 lru = LRU.get(temp); 
                                 val = temp; 
                             } 
                         }
                         s.remove(val); 
                         s.add(values.get(i));
                         int remove=Integer.MIN_VALUE;
                         for (Integer key : positions.keySet()) {
                             if(positions.get(key)==val)
                                 remove=key;
                         }
                         positions.replace(remove, values.get(i));
                         page_fault++; 
                         pf=true;
                     }
                 }

                 LRU.put(values.get(i), i);
                 for(int j=0;j<positions.size();j++)
                 {
                         lru_table.setValueAt(positions.get(j), j, i+1);                
                 }
                 if(pf==true)
                     lru_table.setValueAt("Miss", frame_no, i+1);
                 else
                     lru_table.setValueAt("Hit", frame_no, i+1);
             }

             TableColumnModel column = lru_table.getColumnModel();
             javax.swing.table.TableColumn tablecolumn = column.getColumn(0);
             tablecolumn.setHeaderValue("String"); 
             JTableHeader tableHeader = lru_table.getTableHeader();
             Font headerFont = new Font("tohima", Font.BOLD, 14);
             tableHeader.setFont(headerFont);
             lru_table.getTableHeader().setReorderingAllowed(false);
           
             lru_table.setBackground(new Color		(0,139,139));
             lru_table.setForeground(new Color		(230,230,250));
             for(int c=1;c<values.size()+1;c++)
             {
                 tablecolumn = column.getColumn(c);
                 tablecolumn.setHeaderValue(values.get(c-1));
             }

             JLabel lru_label= new JLabel("  LRU:");
             JLabel lru_pf = new JLabel("  Page Faults: "+page_fault);
            
             repaint();
             revalidate();      
             graphPanel.setViewportView(lru_table);

         }
         if(selectedValue=="Opr")
         {
        	 //opt_panel.removeAll();

             int frame_no = Integer.parseInt((String)(cmb_GetCap.getSelectedItem()));				//rename FRAME combobox
     	int page_fault=0;

             ArrayList<Integer> values = new ArrayList<>();

             String reference_string=txtF_GetString.getText();							//rename STRING textfield

             for(int i=0;i<reference_string.length();i++)
             {
                 if(reference_string.charAt(i)==',')
                     ;
                 else
                 {
                     String s="";
                     s=s+reference_string.charAt(i);
                     values.add(Integer.parseInt(s));
                 }
             }

             JTable opt_table = new JTable(frame_no+1,values.size()+1);
             Font font = new Font("Tohima", Font.BOLD, 16);
            opt_table.setFont(font);
             opt_table.setRowHeight(opt_table.getRowHeight() + 40);
             for(int i=0;i<frame_no;i++)
             {
                 opt_table.setValueAt("Frame: "+(i+1), i, 0);
             }
             ArrayList<String> list=new ArrayList<>();
             ArrayList<Integer> OPT=new ArrayList<>();
             for(int i=0;i<frame_no;i++)
               OPT.add(Integer.MIN_VALUE);
             for(int i=0;i<values.size();i++)
             {
                 boolean inlist=false;
                 int temp=values.get(i);
                 for(int j=0;j<OPT.size();j++)
                 {
                     if(OPT.get(j)==(temp))
                     {
                         inlist=true;
                         break;
                     }
                 }
                 if(inlist)
                 {
                     list.add("Hit");
                 }
                 else
                 {
                     list.add("Miss");
                     boolean changed=false;
                     for(int j=0;j<OPT.size();j++)
                     {
                         if(OPT.get(j)==-1000)
                         {
                             OPT.set(j,values.get(i));
                             changed=true;
                             break;
                         }
                     }
                     if(!changed)
                     {
                         ArrayList<Integer> positions=new ArrayList<>();
                         for(int j=0;j<OPT.size();j++)
                         {
                             temp=OPT.get(j);
                             boolean found=false;
                             for(int k=i+1;k<values.size();k++)
                             {
                                 if(temp==values.get(k))
                                 {
                                     positions.add(k);found=true;
                                     break;
                                 }
                             }
                             if(!found)
                             {
                                 positions.add(Integer.MAX_VALUE);
                             }
                         }
                         int maxim=-1;int maxv=-1;
                         for(int j=0;j<positions.size();j++)
                         {
                             if(positions.get(j)>=maxv)
                             {
                                 maxim=j;
                                 maxv=positions.get(j);
                             }
                             if(positions.get(j)==Integer.MAX_VALUE)
                                 break;
                         }
                         OPT.set(maxim,values.get(i));
                     }
                 }
                 for(int j=0;j<OPT.size();j++)
                 {
                     if(OPT.get(j)!= Integer.MIN_VALUE)
                         opt_table.setValueAt(OPT.get(j),j,i+1);
                 }
             }
             for(int i=0;i<list.size();i++)
             {
                 if(list.get(i).equalsIgnoreCase("Miss"))
                     page_fault++;
                 opt_table.setValueAt(list.get(i),frame_no,i+1);
             }
             TableColumnModel column = opt_table.getColumnModel();
             javax.swing.table.TableColumn tablecolumn = column.getColumn(0);
             tablecolumn.setHeaderValue("String"); 
             JTableHeader tableHeader = opt_table.getTableHeader();
             Font headerFont = new Font("tohima", Font.BOLD, 14);
             tableHeader.setFont(headerFont);
             opt_table.getTableHeader().setReorderingAllowed(false);
             opt_table.setBackground(new Color		(0,139,139));
             opt_table.setForeground(new Color		(230,230,250));
             for(int c=1;c<values.size()+1;c++)
             {
                 tablecolumn = column.getColumn(c);
                 tablecolumn.setHeaderValue(values.get(c-1));
             }
//             JLabel opt_label= new JLabel("  OPT: ");
//             JLabel opt_pf = new JLabel("  Page Faults: "+page_fault);
             repaint();
             revalidate();
             graphPanel.setViewportView(opt_table);
            }
         if(selectedValue=="Random")
         {
        	 int frame_no  = Integer.parseInt((String)(cmb_GetCap.getSelectedItem()));			//rename FRAME combobox
             int page_fault=0;

             ArrayList<Integer> values = new ArrayList<>();

             String reference_string=txtF_GetString.getText();						//rename STRING textfield

     	for(int i=0;i<reference_string.length();i++)
             {
                 if(reference_string.charAt(i)==',')
                     ;
                 else
                 {
                     String s="";
                     s=s+reference_string.charAt(i);
                     values.add(Integer.parseInt(s));
                 }
             }
     
             JTable fifo_table = new JTable(frame_no+1,values.size()+1);
             Font font = new Font("Tohima", Font.BOLD, 16);
             fifo_table.setFont(font);
             fifo_table.setRowHeight(fifo_table.getRowHeight() + 40);
             for(int r=0;r<frame_no;r++)
             {
            	 
                 fifo_table.setValueAt("Frame: "+(r+1), r, 0);
             }

             ArrayList<Integer> FIFO = new ArrayList<>();
             Random rdm = new Random();
         	 int pointer = rdm.nextInt(frame_no);
             for(int i=0;i<values.size();i++)
             {
                 boolean pf=false;
                 if(FIFO.size()<frame_no)
                 {
                     if(!FIFO.contains(values.get(i)))
                     {
                         FIFO.add(values.get(i));
                         page_fault++;
                         pf=true;
                     }
                 }
                 else
                 {
                     if(!FIFO.contains(values.get(i)))
                     {
                         //FIFO.remove(var);
                         FIFO.set(pointer,values.get(i));
                         page_fault++;
//                         var++;
//                         var=var%frame_no;
                         pf=true;
                     }
                 }
                 for(int j=0;j<FIFO.size();j++)
                 {
                     fifo_table.setValueAt(FIFO.get(j), j, i+1);
                 }

                 if(pf==true)
                     fifo_table.setValueAt("Miss", frame_no, i+1);
                 else
                     fifo_table.setValueAt("Hit", frame_no, i+1);
             }

//             JLabel fifo_label= new JLabel("  FIFO:");
//             JLabel fifo_pf = new JLabel("  Page Faults: "+page_fault);
             TableColumnModel column = fifo_table.getColumnModel();
             javax.swing.table.TableColumn tablecolumn = column.getColumn(0);
             
             tablecolumn.setHeaderValue("String"); 
             JTableHeader tableHeader = fifo_table.getTableHeader();
             Font headerFont = new Font("tohima", Font.BOLD, 14);
             tableHeader.setFont(headerFont);
             fifo_table.getTableHeader().setReorderingAllowed(false);
             
             for(int c=1;c<values.size()+1;c++)
             {
                 tablecolumn = column.getColumn(c);
                 tablecolumn.setHeaderValue(values.get(c-1));
                 fifo_table.setBackground(new Color		(0,139,139));
                 fifo_table.setForeground(new Color		(230,230,250));
             }
             repaint();
             revalidate();
             graphPanel.setViewportView(fifo_table);
             }
     if(selectedValue=="Lifo")
     {
    	 int frame_no  = Integer.parseInt((String)(cmb_GetCap.getSelectedItem()));			//rename FRAME combobox
         int page_fault=0;

         ArrayList<Integer> values = new ArrayList<>();

         String reference_string=txtF_GetString.getText();						//rename STRING textfield

 	for(int i=0;i<reference_string.length();i++)
         {
             if(reference_string.charAt(i)==',') ;
             else
             {
                 String s="";
                 s=s+reference_string.charAt(i);
                 values.add(Integer.parseInt(s));
             }
         }
 	
         JTable lifo_table = new JTable(frame_no+1,values.size()+1);
         Font font = new Font("Tohima", Font.BOLD, 16);
        lifo_table.setFont(font);
         lifo_table.setRowHeight(lifo_table.getRowHeight() + 40);
         
         for(int r=0;r<frame_no;r++)
         {
             lifo_table.setValueAt("Frame: "+(r+1), r, 0);
         }

         Stack<Integer> LIFO = new Stack<>();
        // int var=frame_no-1;
         for(int i=0;i<values.size();i++)
         {
             boolean pf=false;
             if(LIFO.size()<frame_no)
             {
                 if(!LIFO.contains(values.get(i)))
                 {
                	 LIFO.add(values.get(i));
                     page_fault++;
                     pf=true;
                 }
             }
             else
             {
                 if(!LIFO.contains(values.get(i)))
                 {
                	 LIFO.pop();
                	 LIFO.push(values.get(i));
                     page_fault++;           
                     pf=true;
                 }
             }

             for(int j=0;j<LIFO.size();j++)
             {
                 lifo_table.setValueAt(LIFO.get(j), j, i+1);
             }

             if(pf==true)
                 lifo_table.setValueAt("Miss", frame_no, i+1);
             else
                 lifo_table.setValueAt("Hit", frame_no, i+1);
         }

       //  JLabel fifo_label= new JLabel("  FIFO:");
       //  JLabel fifo_pf = new JLabel("  Page Faults: "+page_fault);
         TableColumnModel column = lifo_table.getColumnModel();
         javax.swing.table.TableColumn tablecolumn = column.getColumn(0);
         lifo_table.setBackground(new Color		(0,139,139));
         lifo_table.setForeground(new Color		(230,230,250));
         tablecolumn.setHeaderValue("String"); 
         JTableHeader tableHeader = lifo_table.getTableHeader();
         Font headerFont = new Font("tohima", Font.BOLD, 14);
         tableHeader.setFont(headerFont);
         lifo_table.getTableHeader().setReorderingAllowed(false);
         for(int c=1;c<values.size()+1;c++)
         {
             tablecolumn = column.getColumn(c);
             tablecolumn.setHeaderValue(values.get(c-1));
         }
         repaint();
         revalidate();
         graphPanel.setViewportView(lifo_table);
     }        

       	}
       });
       javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
       jPanel1Layout.setHorizontalGroup(
       	jPanel1Layout.createParallelGroup(Alignment.TRAILING)
       		.addGroup(jPanel1Layout.createSequentialGroup()
       			.addContainerGap(31, Short.MAX_VALUE)
       			.addComponent(jLabel2)
       			.addGap(10)
       			.addComponent(cmb_GetCap, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
       			.addGap(18)
       			.addComponent(jLabel3)
       			.addGap(18)
       			.addComponent(txtF_GetString, GroupLayout.PREFERRED_SIZE, 575, GroupLayout.PREFERRED_SIZE)
       			.addGap(71)
       			.addComponent(btn_AutoGen)
       			.addGap(238))
       		.addGroup(jPanel1Layout.createSequentialGroup()
       			.addGap(44)
       			.addComponent(btnCompare, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
       			.addPreferredGap(ComponentPlacement.RELATED, 953, Short.MAX_VALUE)
       			.addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
       			.addGap(42))
       		.addGroup(jPanel1Layout.createSequentialGroup()
       			.addGap(18)
       			.addComponent(graphPanel, GroupLayout.PREFERRED_SIZE, 1254, GroupLayout.PREFERRED_SIZE)
       			.addContainerGap(21, Short.MAX_VALUE))
       		.addGroup(jPanel1Layout.createSequentialGroup()
       			.addGap(37)
       			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
       				.addComponent(jLabel4)
       				.addComponent(jLabel6, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
       				.addComponent(jLabel7))
       			.addGap(55)
       			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
       				.addGroup(jPanel1Layout.createSequentialGroup()
       					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
       						.addComponent(txtF_ShowFaults, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
       						.addComponent(AlgoSelect, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
       					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
       						.addGroup(jPanel1Layout.createSequentialGroup()
       							.addGap(52)
       							.addComponent(btnFifoGraph)
       							.addPreferredGap(ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
       							.addComponent(btnLruGraph, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
       							.addGap(47)
       							.addComponent(btnOprGraph, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
       							.addGap(38)
       							.addComponent(btnLifoGraph, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
       							.addGap(49)
       							.addComponent(btnRandomGraph, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
       							.addGap(81))
       						.addGroup(jPanel1Layout.createSequentialGroup()
       							.addGap(81)
       							.addComponent(btn_Submit, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
       							.addGap(8))))
       				.addGroup(jPanel1Layout.createSequentialGroup()
       					.addComponent(txtF_ShowHits, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
       					.addPreferredGap(ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
       					.addComponent(VButton, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
       					.addGap(658)))
       			.addContainerGap(107, GroupLayout.PREFERRED_SIZE))
       		.addGroup(jPanel1Layout.createSequentialGroup()
       			.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 1283, Short.MAX_VALUE)
       			.addContainerGap())
       );
       jPanel1Layout.setVerticalGroup(
       	jPanel1Layout.createParallelGroup(Alignment.LEADING)
       		.addGroup(jPanel1Layout.createSequentialGroup()
       			.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
       			.addGap(18)
       			.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
       				.addComponent(jLabel2)
       				.addComponent(cmb_GetCap, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
       				.addComponent(jLabel3)
       				.addComponent(txtF_GetString, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
       				.addComponent(btn_AutoGen))
       			.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
       			.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
       				.addComponent(jLabel4)
       				.addComponent(AlgoSelect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
       				.addComponent(btn_Submit, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
       			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
       				.addGroup(jPanel1Layout.createSequentialGroup()
       					.addGap(27)
       					.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
       						.addComponent(jLabel6)
       						.addComponent(txtF_ShowHits, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
       				.addGroup(jPanel1Layout.createSequentialGroup()
       					.addGap(18)
       					.addComponent(VButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
       			.addGap(34)
       			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
       				.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
       					.addComponent(jLabel7)
       					.addComponent(txtF_ShowFaults, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
       				.addGroup(jPanel1Layout.createSequentialGroup()
       					.addPreferredGap(ComponentPlacement.RELATED)
       					.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
       						.addComponent(btnFifoGraph)
       						.addComponent(btnLifoGraph)
       						.addComponent(btnOprGraph)
       						.addComponent(btnLruGraph)
       						.addComponent(btnRandomGraph))))
       			.addGap(45)
       			.addComponent(graphPanel, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE)
       			.addPreferredGap(ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
       			.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
       				.addComponent(btnCompare, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
       				.addComponent(jButton2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
       			.addGap(42))
       );
        jPanel1.setLayout(jPanel1Layout);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 1293, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(79, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        getContentPane().setLayout(layout);
        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void btn_SubmitActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        String selectedValue = (String) AlgoSelect.getSelectedItem();   
        if(selectedValue=="Fifo"){
            String sector = txtF_GetString.getText();
            String[] numbers = sector.split(",");
            List<String> SectorString = Arrays.asList(numbers);
            ArrayList<String> SectorNumber = new ArrayList<String>(SectorString);
    //				System.out.println("list from comma separated String : " + SectorNumber); 
    //				System.out.println("size of ArrayList : " + SectorNumber.size());
            Object[] ArraySector = SectorNumber.toArray();
            int[] pages = new int[ArraySector.length];
            for (int i = 0; i < ArraySector.length; i++) {
                pages[i] = Integer.valueOf(numbers[i]);
                System.out.println(pages[i]);
            }           
            String cap = (String) cmb_GetCap.getSelectedItem();
            int capacity = Integer.valueOf(cap);
            int faults = pageFaultsFifo(pages, pages.length, capacity);
            int hits = pages.length - faults;
            String hitString = String.valueOf(hits);
            txtF_ShowHits.setText(hitString);
            String faultString = String.valueOf(faults);
            txtF_ShowFaults.setText(faultString);
        }
        if(selectedValue=="Lru"){
            String sector = txtF_GetString.getText();
            String[] numbers = sector.split(",");
            List<String> SectorString = Arrays.asList(numbers);
            ArrayList<String> SectorNumber = new ArrayList<String>(SectorString);
    		Object[] ArraySector = SectorNumber.toArray();
            int[] pages = new int[ArraySector.length];
            for (int i = 0; i < ArraySector.length; i++) {
                pages[i] = Integer.valueOf(numbers[i]);
            }        
            String cap = (String) cmb_GetCap.getSelectedItem();
            int capacity = Integer.valueOf(cap);
            int faults = pageFaultsLru(pages, pages.length, capacity);
            int hits = pages.length - faults;
            String hitString = String.valueOf(hits);
            txtF_ShowHits.setText(hitString);
            String faultString = String.valueOf(faults);
            txtF_ShowFaults.setText(faultString);
        }
        if(selectedValue=="Opr"){
            String sector = txtF_GetString.getText();
            String[] numbers = sector.split(",");
            List<String> SectorString = Arrays.asList(numbers);
            ArrayList<String> SectorNumber = new ArrayList<String>(SectorString);

            Object[] ArraySector = SectorNumber.toArray();
            int[] pages = new int[ArraySector.length];
            for (int i = 0; i < ArraySector.length; i++) {
                pages[i] = Integer.valueOf(numbers[i]);
            }       
            String cap = (String) cmb_GetCap.getSelectedItem();
            int capacity = Integer.valueOf(cap);
            int hits = pageFaultsOpr(pages, pages.length, capacity);
            int faults = pages.length - hits;
            String hitString = String.valueOf(hits);
            txtF_ShowHits.setText(hitString);
            String faultString = String.valueOf(faults);
            txtF_ShowFaults.setText(faultString);  
          
}
        if(selectedValue=="Random"){
            String sector = txtF_GetString.getText();
            String[] numbers = sector.split(",");
            List<String> SectorString = Arrays.asList(numbers);
            ArrayList<String> SectorNumber = new ArrayList<String>(SectorString);
    //				System.out.println("list from comma separated String : " + SectorNumber); 
    //				System.out.println("size of ArrayList : " + SectorNumber.size());
            Object[] ArraySector = SectorNumber.toArray();
            int[] pages = new int[ArraySector.length];
            for (int i = 0; i < ArraySector.length; i++) {
                pages[i] = Integer.valueOf(numbers[i]);
            }       
               //String cap;
                String cap = (String) cmb_GetCap.getSelectedItem();
                System.out.println(cap);
                //int capacity;
                int capacity = Integer.valueOf(cap);
                System.out.println(capacity);
                //int faults;
                int faults = pageFaultsRandom(pages, pages.length, capacity);
                //int hits;
                int hits = pages.length - faults;
                //String hitString;
                String hitString = String.valueOf(hits);
                txtF_ShowHits.setText(hitString);
                //String faultString;
                String faultString = String.valueOf(faults);
                txtF_ShowFaults.setText(faultString);
            }
    if(selectedValue=="Lifo"){
            String sector = txtF_GetString.getText();
            String[] numbers = sector.split(",");
            List<String> SectorString = Arrays.asList(numbers);
            ArrayList<String> SectorNumber = new ArrayList<String>(SectorString);
    //				System.out.println("list from comma separated String : " + SectorNumber); 
    //				System.out.println("size of ArrayList : " + SectorNumber.size());
            Object[] ArraySector = SectorNumber.toArray();
            int[] pages = new int[ArraySector.length];
            for (int i = 0; i < ArraySector.length; i++) {
                pages[i] = Integer.valueOf(numbers[i]);
                System.out.println(pages[i]);
            }        
            String cap = (String) cmb_GetCap.getSelectedItem();
            int capacity = Integer.valueOf(cap);
            int faults = pageFaultsLifo(pages, pages.length, capacity);
            int hits = pages.length - faults;
            String hitString = String.valueOf(hits);
            txtF_ShowHits.setText(hitString);
            String faultString = String.valueOf(faults);
            txtF_ShowFaults.setText(faultString);
        }        
}//GEN-LAST:event_btn_SubmitActionPerformed
    private void txtF_ShowFaultsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtF_ShowFaultsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtF_ShowFaultsActionPerformed
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        index a = new index();
        a.setLocationRelativeTo(null);
        a.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed
    private void txtF_GetStringActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtF_GetStringActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtF_GetStringActionPerformed
    private void cmb_GetCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_GetCapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_GetCapActionPerformed
    private void btn_AutoGenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AutoGenActionPerformed
        // TODO add your handling code here:       
        java.util.Random r = new java.util.Random();
        int[] random = new int[15];
        for (int i = 0; i < random.length; i++) {
            random[i] = r.nextInt(10 - 1) + 1; // storing random integers in an requestay
            //System.out.println(ranom[i]); // printing each requestay element
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
        txtF_GetString.setText(result);
    }//GEN-LAST:event_btn_AutoGenAction
 
    public static void main(String args[])  {
//    	try {
//			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
//		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
//				| UnsupportedLookAndFeelException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	 
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
            java.util.logging.Logger.getLogger(Algorithm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Algorithm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Algorithm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Algorithm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Algorithm a = new Algorithm();
                a.setLocationRelativeTo(null);
                a.setVisible(true);
            }
        });
    }    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> AlgoSelect;
    private javax.swing.JButton btnFifoGraph;
    private javax.swing.JButton btnLifoGraph;
    private javax.swing.JButton btnLruGraph;
    private javax.swing.JButton btnOprGraph;
    private javax.swing.JButton btnRandomGraph;
    private javax.swing.JButton btn_AutoGen;
    private javax.swing.JButton btn_Submit;
    private javax.swing.JComboBox<String> cmb_GetCap;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JTextField txtF_GetString;
    public javax.swing.JTextField txtF_ShowFaults;
    public javax.swing.JTextField txtF_ShowHits;
    private JButton btnCompare;
    // End of variables declaration//GEN-END:variables
    private static class isInfn {      
            static boolean isInfn(int value, int frames, int[] array) {
		for (int i = 0; i < frames; i++){
			if (array[i] == value){
				return true;
			}
		}
		return false;
	}
    }
  public static int page_faults(int el, int[] pf, int n)
{
  int j;
  int f = 0;
  for (j = 0;j < n;j++)
  {
	if (pf[j] == el)
	{
	  f = 1;
	  break;
	}
	else
	{
	  continue;
	}
  }
  return f;
}    
    private static class textField {
        private static String getText() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        public textField() {
            JTextField textField = new JTextField();
		textField.setFont(new Font("Century Schoolbook", Font.PLAIN, 20));
		textField.setColumns(10);		           
        }
    }
    private static class textField_1 {
        private static String getText() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        public textField_1() {
            JTextField textField_1 = new JTextField();
		textField_1.setFont(new Font("Century Schoolbook", Font.PLAIN, 20));
		textField_1.setColumns(10);
        }
    }
}