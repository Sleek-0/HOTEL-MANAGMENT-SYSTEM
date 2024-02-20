package ClassesProject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JComponent;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameAdapter;

public class RoomInfoPanel extends JFrame implements ActionListener {
	private JPanel mainPanel;
	private JPanel ImgPanel;
	private JLabel Imglabel; 
    private JLabel nameLabel;
	private JButton ShowInf;
    private JLabel TotalRoomPrice;
    private JTextArea notesArea;
    private JCheckBox BreakfastSub;
    private JCheckBox PoolSub;
    private JCheckBox BarSub;
     private JCheckBox InternetSub;
	 private int RoomP_int;
	 private int finalCost = 0;
     String name;
     String mobileNumber;
     String nationality;
     String gmail;
     String address;
     String CheckinDate;
     String gender;
     String roomNo_B;
     String FloorNo;
     String bed;
     String roomPrice;
     private JLabel RoomNO_temp;
	 
    public RoomInfoPanel(String roomName) {
		setSize(625, 350);
        setTitle("Room " + roomName);
		setLayout(new BorderLayout());
        setResizable(false);
		setIconImage(
        Toolkit.getDefaultToolkit().getImage(DashBoardMenu.class.getResource("../IconsProject/2090700.png")));
	    setLocationRelativeTo(null);

		// panel for the room photo 
		ImgPanel = new JPanel();
		ImgPanel.setSize(325, 240);
		
		getCustomerData(roomName);
		
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainPanel.setSize(300, 350);
		mainPanel.setLayout(new GridLayout(0, 1));
        TotalRoomPrice = new JLabel("Price / Day : " + roomPrice + " $ ");
		
        nameLabel = new JLabel("Guest Name : " + name );
        notesArea = new JTextArea(5, 20);
        BreakfastSub = new JCheckBox("Breakfast          5$ / Day");
        PoolSub = new JCheckBox("Pool access      4$ / Day");
        BarSub = new JCheckBox("Open Bar           7$ / Day");
		InternetSub = new JCheckBox("Wifi         	       2$ / Day");
     
	    RoomNO_temp = new JLabel(roomName);
		String RoomNo_temp2 = RoomNO_temp.getText();
		SetCheckboxesInitial(RoomNo_temp2);
		
		
		 // Adding ActionListeners to handle the increase and decrease of roomPrice
        BreakfastSub.addActionListener(this);
        PoolSub.addActionListener(this);
        BarSub.addActionListener(this);
        InternetSub.addActionListener(this);

		mainPanel.add(TotalRoomPrice);
        mainPanel.add(nameLabel);
        
		
        // Create an internal frame for the table
        JInternalFrame internalFrame = createInternalFrame();
		ShowInf = new JButton("Show All");
		ShowInf.addActionListener(this);
        mainPanel.add(ShowInf);
        mainPanel.add(new JLabel("Notes:"));
        mainPanel.add(new JScrollPane(notesArea));
        mainPanel.add(BreakfastSub);
        mainPanel.add(PoolSub);
        mainPanel.add(BarSub);
		mainPanel.add(InternetSub);
		mainPanel.add(ImgPanel);
		
		add(ImgPanel, BorderLayout.EAST);
        add(mainPanel, BorderLayout.CENTER);
		setVisible(true);
		
		
    }
	
	
	 public void SetCheckboxesInitial(String Roomnb)
	 {
		 String filePath2 = "./files/"+ Roomnb +".txt";
		 try (BufferedReader br4 = new BufferedReader(new FileReader(filePath2))) {
            String line;
            while ((line = br4.readLine()) != null) {
                if (line.equals("Subscriptions Details")) {
                    String[] rowData = new String[5]; // create an array with 5 elements
                    for (int i = 0; i < 5; i++) {
                        // read the next 5 lines and add the data to the corresponding column
                        rowData[i] = br4.readLine();
                    }
                    if (rowData[0].equals(Roomnb)) {
						if (rowData[1].equals("Breakfast Subscribed")) { BreakfastSub.setSelected(true);  }
						if (rowData[2].equals("Pool Subscribed")) { PoolSub.setSelected(true); }
						if (rowData[3].equals("Bar Subscribed")) { BarSub.setSelected(true);}
						if (rowData[4].equals("Internet Subscribed")) { InternetSub.setSelected(true); }
					}
					 
                    }
                    }}  catch (IOException e) {
            e.printStackTrace();
	 } }
		 
	 

	 private JInternalFrame createInternalFrame() {
		  System.out.println("Create internal table frame function called");
        JInternalFrame internalFrame = new JInternalFrame("Customer Data", true, true, true, true);
        internalFrame.setLayout(new BorderLayout());

        // Create a table with two columns
        String[] columnNames = {"Attribute", "Value"};
        String[][] rowData = {
                {"Name", name},
                {"Mobile Number", mobileNumber},
                {"Nationality", nationality},
                {"Gender", gender},
                {"Email", gmail},
                {"Address", address},
                {"Check-in Date", CheckinDate},
                {"Room Price", roomPrice},
                {"Room Number", roomNo_B},
                {"Bed", bed},
                {"Floor", FloorNo}
        };

        JTable dataTable = new JTable(rowData, columnNames);
        JScrollPane scrollPane = new JScrollPane(dataTable);

        internalFrame.add(scrollPane, BorderLayout.CENTER);

        internalFrame.setSize(500, 500);
        internalFrame.setLocation(330, 0);
        internalFrame.setVisible(true);
         
		 System.out.println("Create internal table frame function executed successfully");
        return internalFrame;
		
    }
	
	public void ClearFileContent ( String Room_to_delete){ 
        // Specify the path to the file
        String filePath = "./files/"+ Room_to_delete +".txt";

        // Read the content of the file
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Skip reading lines (effectively deleting them)
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Open the file in write mode and truncate its content
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            // Write the modified content (which is empty in this case)
            writer.print(fileContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Cleared subscribtions of rooom : " + Room_to_delete + " , waiting new subs to be rewritten...");
    }

	
	public void RoomSubscription(String roomNum, boolean isBreakfast, boolean isPool, boolean isBar, boolean isInternet) {
    boolean flag = false;
      System.out.println("Changing subscribtions of room: " + roomNum );
             
    if (!flag) {
		
		
		 ClearFileContent(roomNum);
        // Room doesn't exist, create a new entry
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("./files/"+ roomNum +".txt", true)))) {
            printWriter.println("Subscriptions Details");
            printWriter.println(roomNum);
            printWriter.println(isBreakfast ? "Breakfast Subscribed" : "Breakfast Not Subscribed");
            printWriter.println(isPool ? "Pool Subscribed" : "Pool Not Subscribed");
            printWriter.println(isBar ? "Bar Subscribed" : "Bar Not Subscribed");
            printWriter.println(isInternet ? "Internet Subscribed" : "Internet Not Subscribed");
            printWriter.println(); // Add an empty line
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		flag = true;
    }
	
	System.out.println("Subscribtions of room: " + roomNum + " Changed successfully" );
}

	
	
	public void getCustomerData(String selectedRoomNo) {
    System.out.println("Get Customer Data function for internal frame function called");
    try (BufferedReader br1 = new BufferedReader(new FileReader("./files/checkIn.txt"))) {
        String line1;

           while ((line1 = br1.readLine()) != null) {
            if (line1.equals("Check In Details")) {
                String[] rowData1 = new String[11];
                rowData1[0] = br1.readLine(); // Name
                rowData1[1] = br1.readLine(); // Room Number
                rowData1[2] = br1.readLine(); // Gender
                rowData1[3] = br1.readLine(); // Mobile Number
                rowData1[4] = br1.readLine(); // Nationality
                rowData1[5] = br1.readLine(); // Email
                rowData1[6] = br1.readLine(); // Address
                rowData1[7] = br1.readLine(); // Check-in Date
                rowData1[8] = br1.readLine(); // Room Price
                br1.readLine(); // Status (Booked)

                if (rowData1[1].equals(selectedRoomNo)) {
                    name = rowData1[0];
                    gender = rowData1[2];
                    mobileNumber = rowData1[3];
                    nationality = rowData1[4];
                    gmail = rowData1[5];
                    address = rowData1[6];
                    CheckinDate = rowData1[7];
                    roomPrice = rowData1[8];
                    break; // Exit the loop since the room is found
                }
            }
        }

        if (name != null) {
            String[] data = {
                    name,
                    mobileNumber,
                    nationality,
                    gender,
                    gmail,
                    address,
                    CheckinDate,
                    roomPrice,
                    selectedRoomNo, // Use the selected room number here
                    bed,
                    FloorNo
            };
        }

    } catch (Exception ex) {
        ex.printStackTrace();
    }
	
	// read through the file and set the image of the room depending on bed type
					try (BufferedReader br2 = new BufferedReader(new FileReader("./files/rooms.txt"))) {
			String line5;

			while ((line5 = br2.readLine()) != null) {
				if (line5.equals("Rooms Details")) {
					String[] rowData5 = new String[5]; // create an array with 5 elements
					for (int i = 0; i < 5; i++) {
						rowData5[i] = br2.readLine();
					}
					if (rowData5[0].equals(selectedRoomNo)) { // Check if the room number matches
					 if (rowData5[2].equals("Double")) {
					ImageIcon obj1 = new ImageIcon("IconsProject/hotelimg2bed.png");
				    Imglabel = new JLabel(obj1);
				   Imglabel.setBounds(0, 0, ImgPanel.getPreferredSize().width, ImgPanel.getPreferredSize().height);
				    ImgPanel.add(Imglabel);
						} else {
							ImageIcon obj2 = new ImageIcon("IconsProject/hotelimg1bed.png");
				    Imglabel = new JLabel(obj2);
				   Imglabel.setBounds(0, 0, ImgPanel.getPreferredSize().width, ImgPanel.getPreferredSize().height);
				    ImgPanel.add(Imglabel);
						}
						break; // Exit the loop once the room is found
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

    System.out.println("Get Customer Data function for internal frame function executed successfully");

}

	private void updateTotalPrice(boolean isSelected, int subscriptionPrice) {
		System.out.println("Updating Room price....");
    	int RoomP_int = Integer.parseInt(roomPrice);

			if (isSelected) {
				finalCost += subscriptionPrice;
			   } else {
						finalCost -= subscriptionPrice;
			          }

					TotalRoomPrice.setText("Price / Day : " + (RoomP_int + finalCost) + " $");
	}

 

@Override
public void actionPerformed(ActionEvent e) {
	
	
    if (e.getSource() == ShowInf) {
        JInternalFrame internalFrame = createInternalFrame();
        add(internalFrame);
        mainPanel.setVisible(false);

        internalFrame.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                // Handle the close operation (e.g., hide the internal frame)
                internalFrame.setVisible(false);
				mainPanel.setVisible(true);
            }
        });
		
    }
		
				// Inside your actionPerformed method or event handling logic
				if (e.getSource() == PoolSub) {
					int PoolSubPrice = 4;
					updateTotalPrice(PoolSub.isSelected(), PoolSubPrice);
				}

				if (e.getSource() == BarSub) {
					int BarSubPrice = 7;
					updateTotalPrice(BarSub.isSelected(), BarSubPrice);
				}

				if (e.getSource() == InternetSub) {
					int InternetSubPrice = 2;
					updateTotalPrice(InternetSub.isSelected(), InternetSubPrice);
				}

				if (e.getSource() == BreakfastSub) {
					int BreakfastSubPrice = 5; 
					updateTotalPrice(BreakfastSub.isSelected(), BreakfastSubPrice);
				}
				
			boolean isBreakfastSelected = BreakfastSub.isSelected();
			boolean isPoolSelected = PoolSub.isSelected();
			boolean isBarSelected = BarSub.isSelected();
			boolean isInternetSelected = InternetSub.isSelected();
            
			    
				String Roomnb = RoomNO_temp.getText();
                System.out.println(Roomnb);
				
				if (isBreakfastSelected || isPoolSelected || isBarSelected || isInternetSelected) {
					
				RoomSubscription(Roomnb, isBreakfastSelected, isPoolSelected, isBarSelected, isInternetSelected);
			     } 
			// Call the RoomSubscription method


}
}

