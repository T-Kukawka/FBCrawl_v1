package FBCrawl;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.lang.Object;

import javax.swing.*;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import facebook4j.FacebookException;


    public class JButtonDemo2 extends  javax.swing.JFrame {
    	JFrame jtfMainFrame;
    	JButton jbnButton1, jbnButton2, jbnButton3, jbnButton4, jbnButton5;
    	JTextField jtfInput, jtfInput2; 
    	Integer[][] eventCount = new Integer[100][100];
    	String[][] shortCount = new String[100][100];
    	JLabel Search1, DatePicker;
		JLabel results, search2, results2;
    	
    	int i5=0;
    	int j=0;
    	int index=0;
    	String[][] allEvents = new String[100][100];
    	String[][] sortedEvents = new String[100][100];
    	
    	
    	
    	JPanel jplPanel, jplPanel2,jplPanel3, jplPanel4, jplPanel25;
    	
    	public JButtonDemo2(final fbCrawl fbCrawl) throws IOException, ParseException {
        		
        	jtfMainFrame = new JFrame("Which Button Demo"); 
        	jtfMainFrame.setSize(300,400);
    		jbnButton1 = new JButton("Read IDs"); 
    		jbnButton2 = new JButton("Read events");
    		jbnButton3 = new JButton("search");
    		jbnButton4 = new JButton("count events");
    		jtfInput = new JTextField(20);
    		jtfInput2 = new JTextField(20);
    		jplPanel = new JPanel();
    		jplPanel2 = new JPanel();
    		jplPanel3 = new JPanel();
    		jplPanel4 = new JPanel();
    		jplPanel25 = new JPanel();
    		final JList List = new javax.swing.JList();
            JScrollPane ScrollPane = new javax.swing.JScrollPane();
            final JList List2 = new javax.swing.JList();
            JScrollPane ScrollPane2 = new javax.swing.JScrollPane();
    		
    		
    		
    		jbnButton1.setMnemonic(KeyEvent.VK_I);	//Set ShortCut Keys
    		jtfInput.setText("Amsterdam");
    		jtfInput2.setText("Amsterdam");
    		
    		UtilDateModel modelFrom = new UtilDateModel();
    		UtilDateModel modelTo = new UtilDateModel();
    		//model.setDate(20,04,2014);
    		// Need this...
    		Properties p = new Properties();
    		p.put("text.today", "Today");
    		p.put("text.month", "Month");
    		p.put("text.year", "Year");
    		JDatePanelImpl datePanelFrom = new JDatePanelImpl(modelFrom, p);
    		JDatePanelImpl datePanelTo = new JDatePanelImpl(modelTo, p);
    		// Don't know about the formatter, but there it is...
    		final JDatePickerImpl datePickerFrom = new JDatePickerImpl(datePanelFrom, new DateLabelFormatter());
    		final JDatePickerImpl datePickerTo = new JDatePickerImpl(datePanelTo, new DateLabelFormatter());
    		
    		
    		
    		
    		
            ScrollPane.setViewportView(List);
            ScrollPane2.setViewportView(List2);
    	
    	     jbnButton1.addActionListener(new ActionListener() {
    	            public void actionPerformed(ActionEvent e) {
    	            	
    	            	String input = jtfInput.getText();
    	            	Date selectedDateFrom = (Date) datePickerFrom.getModel().getValue();
    	            	Date selectedDateTo = (Date) datePickerTo.getModel().getValue();
    	            	
    	            	long timeFrom = selectedDateFrom.getTime();
    	            	long timeTo = selectedDateTo.getTime();
    	            	
    	            	timeFrom = timeFrom/1000L;
    	            	timeTo = timeTo/1000L;
    	            
    	           
						try {
							System.out.println(timeTo);
							System.out.println(timeFrom);
							fbCrawl.readIDs(input, timeFrom, timeTo);
						} catch (FileNotFoundException
								| UnsupportedEncodingException
								| FacebookException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
    	            	
    	            }

    	        });

        	jbnButton2.setMnemonic(KeyEvent.VK_I);
            jbnButton2.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		
            		String file_name = jtfInput.getText();
            		
            		Date selectedDateFrom = (Date) datePickerFrom.getModel().getValue();
	            	Date selectedDateTo = (Date) datePickerTo.getModel().getValue();
	            	
            		long timeFrom = selectedDateFrom.getTime();
	            	long timeTo = selectedDateTo.getTime();
	            	
	            	timeFrom = timeFrom/1000L;
	            	timeTo = timeTo/1000L;
            		try {
						fbCrawl.crawlAndIndex(file_name+timeFrom+timeTo+".txt");
					}
					 catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            	}
            });
            
            jbnButton3.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		String search_term = jtfInput.getText();
            		String search_term2 = jtfInput2.getText();
            		
                	
                	index = 0;
            		String date = Integer.toString(eventCount[List.getSelectedIndex()][0]);
            		
            		
            		
            		try {
            			allEvents = fbCrawl.search(search_term, search_term2, date);
					}
					 catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            		
            		int i=0;
            		try{
	            		while(allEvents[i][0]!=null){
	            			if(allEvents[i][0].length()>20){
	            			sortedEvents[i][0]=(String) allEvents[i][0].subSequence(0, 20);
	            			}else{
	            				sortedEvents[i][0]=allEvents[i][0];
	            			}
	            			if(allEvents[i][1].length()>20){
		            			sortedEvents[i][1]=(String) allEvents[i][1].subSequence(0, 20);
		            			}else{
		            				sortedEvents[i][1]=allEvents[i][1];
		            			}
	            			if(allEvents[i][2].length()>20){
		            			sortedEvents[i][2]=(String) allEvents[i][2].subSequence(0, 20);
		            			}else{
		            				sortedEvents[i][2]=allEvents[i][2];
		            			}
	            			if(allEvents[i][3].length()>20){
		            			sortedEvents[i][3]=(String) allEvents[i][3].subSequence(0, 20);
		            			}else{
		            				sortedEvents[i][3]=allEvents[i][3];
		            			}
	            			if(allEvents[i][4].length()>20){
		            			sortedEvents[i][4]=(String) allEvents[i][4].subSequence(0, 20);
		            			}else{
		            				sortedEvents[i][4]=allEvents[i][4];
		            			}
	            			if(allEvents[i][5].length()>100){
		            			sortedEvents[i][5]=(String) allEvents[i][5].subSequence(0, 100);
		            			}else{
		            				sortedEvents[i][5]=allEvents[i][5];
		            			}
	            			i++;
	            		}
	            		
            		}catch(ArrayIndexOutOfBoundsException e2)
            		{
            			
            		} 
            		index=i;
            		List2.setModel(new javax.swing.AbstractListModel() {
            			  public int getSize() { 
            				  return (index); }
            			  
                        public Object getElementAt(int i) { 
                      	  return sortedEvents[i][1]+"...  || "+sortedEvents[i][2]+" || "+sortedEvents[i][4]+" || "+sortedEvents[i][5]; 
                        	}
                    });
            		
            		
            	}
            });
            
            jbnButton4.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
					String search_term = jtfInput.getText();
					String search_term2 = jtfInput2.getText();
					String dateTo= null;  
					String dateFrom = null;
					Date selectedDateFrom = (Date) datePickerFrom.getModel().getValue();
					Date selectedDateTo = (Date) datePickerTo.getModel().getValue();
					
					if(selectedDateTo.getMonth()<9){
						dateTo = selectedDateTo.getYear()+1900+"0"+(selectedDateTo.getMonth()+1)+""+selectedDateTo.getDate()+"";
						if(selectedDateTo.getDate()<=9){
							dateTo = selectedDateTo.getYear()+1900+"0"+(selectedDateTo.getMonth()+1)+"0"+selectedDateTo.getDate()+"";
						}
					}else if(selectedDateTo.getDate()<=9){
						dateTo = selectedDateTo.getYear()+1900+""+(selectedDateTo.getMonth()+1)+"0"+selectedDateTo.getDate()+"";
					}
					
					if(selectedDateFrom.getMonth()<9){
						dateFrom = selectedDateFrom.getYear()+1900+"0"+(selectedDateFrom.getMonth()+1)+""+selectedDateFrom.getDate()+"";
						if(selectedDateFrom.getDate()<=9){
							dateFrom = selectedDateFrom.getYear()+1900+"0"+(selectedDateFrom.getMonth()+1)+"0"+selectedDateFrom.getDate()+"";
						}
					}else if(selectedDateFrom.getDate()<=9){
						dateFrom = selectedDateFrom.getYear()+1900+""+(selectedDateFrom.getMonth()+1)+"0"+selectedDateFrom.getDate()+"";
					}
					
					
				
            		try {
            			eventCount = fbCrawl.countDailyEvents(search_term,search_term2, dateTo, dateFrom);
            			
            			try{
            				
            			while(eventCount[i5][0]!=null){
            				int count = eventCount[i5][0];
            				shortCount[i5][0]=Integer.toString(count/10000)+"-"+Integer.toString((count%20150000)/100)+"-"+Integer.toString((count%20150000)%100);
            				shortCount[i5][1]=Integer.toString(eventCount[i5][1]);
            				i5++;
            			}
            			}catch(NullPointerException e1){
            				
            			}
					}
					 catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            		
            	
            		List.setModel(new javax.swing.AbstractListModel() {
          			  public int getSize() { 
          				  return (i5); }
                      public Object getElementAt(int i) { 
                    	  return shortCount[i][0]+" "+shortCount[i][1]+" events"; 
                      	}
                  });
            	}
            	
            });
            
            List.addMouseListener(new MouseAdapter() {
           
      	
            	int index=0;
                public void mouseClicked(MouseEvent evt) {
                	
                    JList list = (JList)evt.getSource();
                    if (evt.getClickCount() == 2) {
                    	String search_term2 = jtfInput2.getText();
                    	String search_term = jtfInput.getText();
                    	System.out.println(search_term+search_term2);
                        // Double-click detected
                        
                		String date = Integer.toString(eventCount[List.getSelectedIndex()][0]);

                        
                        	
                        	try {
								allEvents = fbCrawl.search(search_term, search_term, date);
							} catch (FacebookException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (org.apache.lucene.queryparser.classic.ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                        	int i=0;
                    		try{
        	            		while(allEvents[i][0]!=null){
        	            			if(allEvents[i][0].length()>20){
        	            			sortedEvents[i][0]=(String) allEvents[i][0].subSequence(0, 20);
        	            			}else{
        	            				sortedEvents[i][0]=allEvents[i][0];
        	            			}
        	            			if(allEvents[i][1].length()>20){
        		            			sortedEvents[i][1]=(String) allEvents[i][1].subSequence(0, 20);
        		            			}else{
        		            				sortedEvents[i][1]=allEvents[i][1];
        		            			}
        	            			if(allEvents[i][2].length()>20){
        		            			sortedEvents[i][2]=(String) allEvents[i][2].subSequence(0, 20);
        		            			}else{
        		            				sortedEvents[i][2]=allEvents[i][2];
        		            			}
        	            			if(allEvents[i][3].length()>20){
        		            			sortedEvents[i][3]=(String) allEvents[i][3].subSequence(0, 20);
        		            			}else{
        		            				sortedEvents[i][3]=allEvents[i][3];
        		            			}
        	            			if(allEvents[i][4].length()>20){
        		            			sortedEvents[i][4]=(String) allEvents[i][4].subSequence(0, 20);
        		            			}else{
        		            				sortedEvents[i][4]=allEvents[i][4];
        		            			}
        	            			if(allEvents[i][5].length()>100){
        		            			sortedEvents[i][5]=(String) allEvents[i][5].subSequence(0, 100);
        		            			}else{
        		            				sortedEvents[i][5]=allEvents[i][5];
        		            			}
        	            			i++;
        	            		}
        	            		
                    		}catch(ArrayIndexOutOfBoundsException e2)
                    		{
                    			
                    		} 
                    		index=i;
                    		
                    		List2.setModel(new javax.swing.AbstractListModel() {
                    			  public int getSize() { 
                    				  return (index); }
                    			  
                                public Object getElementAt(int i) { 
                              	  return sortedEvents[i][1]+"...  || "+sortedEvents[i][2]+" || "+sortedEvents[i][4]+" || "+sortedEvents[i][5]; 
                                	}
                            });
                        	
                        	
                        
                        
                        
                    } else if (evt.getClickCount() == 3) {

                        // Triple-click detected
                        int index = list.locationToIndex(evt.getPoint());
                    }
                    
                }
            });

            Search1 = new JLabel("Type in city you are interested in", SwingConstants.CENTER);
           // Search1.setText("Type in city you are interested in");
            //Search1.setHorizontalAlignment(SwingConstants.RIGHT);
            
            
            DatePicker = new JLabel("Pick up the time interval you want to investigate", SwingConstants.CENTER);
            
            
            results = new JLabel("Pick up date to see all events", SwingConstants.CENTER);
            JLabel results4 = new JLabel("Date || Number of events", SwingConstants.LEFT);
            results4.setFont(new Font("Serif", Font.PLAIN, 12));
            
            search2 = new JLabel("Search for events in picked up date", SwingConstants.CENTER);
            
            
            results2 = new JLabel("List of events", SwingConstants.CENTER);
            JLabel results3 = new JLabel("Name || Attending PPL || Invited PPL || Description", SwingConstants.LEFT);
            results3.setFont(new Font("Serif", Font.PLAIN, 12));
            
            
            
    		//jplPanel.setLayout(new BoxLayout(jplPanel, FlowLayout.CENTER));
    		
    		jplPanel.setLayout(new GridLayout(0, 1));
    		jplPanel2.setLayout(new GridLayout(0, 1));
    		jplPanel3.setLayout(new GridLayout(0, 1));
    		jplPanel25.setLayout(new BoxLayout(jplPanel25, FlowLayout.CENTER));
    		jplPanel4.setLayout(new BoxLayout(jplPanel4, FlowLayout.CENTER));
    		
    		jplPanel.add(Search1);
    		jplPanel.add(jtfInput);
    		jplPanel.add(jbnButton1); 
    		jplPanel.add(jbnButton2);
    		
    		
    		
    		jplPanel.add(DatePicker);
    		jplPanel.add(datePickerFrom);
    		jplPanel.add(datePickerTo);
    		jplPanel.add(jbnButton4);
    		

    		jplPanel3.add(search2); 
    		jplPanel3.add(jtfInput2);
    		jplPanel3.add(jbnButton3);
    		
    		jplPanel25.add(results);
    		jplPanel25.add(results4);
    		
    		jplPanel25.add(ScrollPane);
    		jplPanel4.add(results2);
    		jplPanel4.add(results3);
    		jplPanel4.add(ScrollPane2);
    		
    		jplPanel25.setPreferredSize(new Dimension(50, 30));
    		
    		jplPanel4.setPreferredSize(new Dimension(300, 300));
        		
    		jtfMainFrame.setLayout(new GridLayout(2,2));
        	jtfMainFrame.getContentPane().add(jplPanel);
        	
        	jtfMainFrame.getContentPane().add(jplPanel3);
        	jtfMainFrame.getContentPane().add(jplPanel25);
        	jtfMainFrame.getContentPane().add(jplPanel4);

        	jtfMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        	jtfMainFrame.pack();
        	jtfMainFrame.setVisible(true);
    }




    }