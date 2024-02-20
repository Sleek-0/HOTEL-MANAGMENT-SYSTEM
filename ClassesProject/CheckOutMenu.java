package ClassesProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Calendar;
import java.util.Objects;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;


public class CheckOutMenu extends JFrame
    implements ActionListener
	{
	private JPanel rightPanel;
    private	JPanel leftPanel;
	private JPanel rightPanel_LeftSide;
	private JPanel rightPanel_CenterSide;	
	private JPanel rightPanel_RightSide;	
	private JPanel rightPanel_BotSide;	
	private JPanel rightPanel_TopSide;

  private final JTextField CustomerName_fld;
  private final JTextField CustomerNum_fld;
  private final JTextField pricePerDay_fld;
  private final JTextField dayStay_fld;
  private final JTextField checkInDate_fld;
  private final JTextField totalAmount_fld;
  private final JTextField email_fld;
  private final  JTextField SubscriptionsField;
  private final JComboBox<String>
      search_combo;
	  
  private final JButton back_btn;
  private final JButton checkOut_btn;
  private final JButton Refresh_Btn;
  
   // private final JButton search_btn;
   
   private int CostOfSubscribtions = 0;
   
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
  String roomNumToDelete;
  
		
		public CheckOutMenu() {
			
			setTitle("Check Out - Hotel Management System");
        setResizable(false);
		setIconImage(
        Toolkit.getDefaultToolkit().getImage(CheckInMenu.class.getResource("../IconsProject/2090700.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1208, 408);
        setLocationRelativeTo(null);
		
		 // Left panel with background image
        leftPanel = new JPanel(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(612, 408));
        ImageIcon obj = new ImageIcon("IconsProject/checkout.jpg");
        JLabel CheckIntImg = new JLabel(obj);
        leftPanel.add(CheckIntImg);
		
       // Main Right panel with 4 Subpanels and stuff
		rightPanel = new JPanel();
		rightPanel.setPreferredSize(new Dimension(596, 408));
		rightPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		rightPanel.setLayout(new BorderLayout());
		
		// Panel for room select combo box
		rightPanel_TopSide = new JPanel();
		rightPanel_TopSide.setPreferredSize(new Dimension(50, 50));
		rightPanel_TopSide.setBorder(new EmptyBorder(5, 5, 5, 5));
		rightPanel_TopSide.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		rightPanel_TopSide.add(new JLabel("Room Number"));
		search_combo = new JComboBox<>();
		rightPanel_TopSide.add(search_combo);
		search_combo.addActionListener(this);
		// panel for customer name, number and email
		
		rightPanel_LeftSide = new JPanel();
		rightPanel_LeftSide.setPreferredSize(new Dimension(198, 350));
		rightPanel_LeftSide.setBorder(new EmptyBorder(5, 5, 5, 5));
		rightPanel_LeftSide.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));
		
		rightPanel_LeftSide.add(new JLabel("Customer Name"));
		 CustomerName_fld = new JTextField(13);
		 CustomerName_fld.setEditable(false);
		rightPanel_LeftSide.add(CustomerName_fld);
		
		rightPanel_LeftSide.add(new JLabel("Customer Mobile Number"));
		 CustomerNum_fld = new JTextField(13);
		 CustomerNum_fld.setEditable(false);
		rightPanel_LeftSide.add(CustomerNum_fld);
		
		rightPanel_LeftSide.add(new JLabel("Customer Email"));
		 email_fld = new JTextField(13);
		 email_fld.setEditable(false);
		rightPanel_LeftSide.add(email_fld);
		// panel for check in date, check out date, number of days stayed
		
		rightPanel_CenterSide = new JPanel();
		rightPanel_CenterSide.setPreferredSize(new Dimension(170, 350));
		rightPanel_CenterSide.setBorder(new EmptyBorder(5, 5, 5, 5));
	   rightPanel_CenterSide.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));
		
		rightPanel_CenterSide.add(new JLabel("Check In Date"));
		 checkInDate_fld = new JTextField(13);
		 checkInDate_fld.setEditable(false);
		rightPanel_CenterSide.add(checkInDate_fld);
		
		rightPanel_CenterSide.add(new JLabel("Check Out Date"));
		JTextField checkOut_fld = new JTextField();
        checkOut_fld.setColumns(13);
        checkOut_fld.setEditable(false);
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();
        checkOut_fld.setText(myFormat.format(cal.getTime()));
        rightPanel_CenterSide.add(checkOut_fld);
		 
		rightPanel_CenterSide.add(new JLabel("Number Of Days Stayed"));
		 dayStay_fld = new JTextField(13);
		 dayStay_fld.setEditable(false);
		rightPanel_CenterSide.add(dayStay_fld);
		
	
		// panel for total amount, price per day , and subscription total
		
		rightPanel_RightSide= new JPanel();
		rightPanel_RightSide.setPreferredSize(new Dimension(198, 350));
		rightPanel_RightSide.setBorder(new EmptyBorder(5, 5, 5, 5));
		rightPanel_RightSide.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 20));
		
		rightPanel_RightSide.add(new JLabel("Price Per Day"));
		 pricePerDay_fld = new JTextField(13);
		 pricePerDay_fld.setEditable(false);
		rightPanel_RightSide.add(pricePerDay_fld);
		
		
		// Create and customize JLabel for total cost
		JLabel SubscriptionsLabel = new JLabel("Subscriptions Cost");
		SubscriptionsLabel.setFont(new Font("Arial", Font.BOLD, 14));
		SubscriptionsLabel.setForeground(Color.BLUE);
		
		SubscriptionsField = new JTextField(8);
		SubscriptionsField.setFont(new Font("Arial", Font.PLAIN, 14));
		SubscriptionsField.setEditable(false);
		
		// Create and customize JLabel for total cost
		JLabel totalCostLabel = new JLabel("Total Cost");
		totalCostLabel.setFont(new Font("Arial", Font.BOLD, 14));
		totalCostLabel.setForeground(Color.BLUE);
		

		// Create JTextField for total cost input
		totalAmount_fld = new JTextField(8);
		totalAmount_fld.setFont(new Font("Arial", Font.PLAIN, 14));
		totalAmount_fld.setEditable(false);

		// Add components to the panel

		rightPanel_RightSide.add(SubscriptionsLabel);
		rightPanel_RightSide.add(SubscriptionsField);
		rightPanel_RightSide.add(totalCostLabel);
		rightPanel_RightSide.add(totalAmount_fld);

		// panel for check out, clear back buttons
		
		rightPanel_BotSide= new JPanel();
		rightPanel_BotSide.setBorder(new EmptyBorder(5, 5, 5, 5));
		rightPanel_BotSide.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
		
		
		
		Refresh_Btn = new JButton("Refresh");
        Refresh_Btn.addActionListener(this);
		checkOut_btn = new JButton("Check Out");
        checkOut_btn.addActionListener(this);
		back_btn = new JButton("Back");
        back_btn.addActionListener(this);
		   
		rightPanel_BotSide.add(checkOut_btn);
		rightPanel_BotSide.add(back_btn);
		rightPanel_BotSide.add(Refresh_Btn);
		
		
		rightPanel.add(rightPanel_TopSide, BorderLayout.NORTH);
	    rightPanel.add(rightPanel_CenterSide, BorderLayout.CENTER);
		rightPanel.add(rightPanel_LeftSide, BorderLayout.WEST);
		rightPanel.add(rightPanel_RightSide, BorderLayout.EAST);
		rightPanel.add(rightPanel_BotSide, BorderLayout.SOUTH);
		
		
	    setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
		
		
		search_combo.removeAllItems();
	    ClearCheckoutField();
		roomSearch();
		pack();
	    repaint();

        setVisible(true);
  }
		
 @Override
  public void actionPerformed(ActionEvent e) {
     if (e.getSource() == back_btn) { // Back button action
      // Hide current window and show dashboard window
      setVisible(false);
      System.out.println("Exited from CheckOut class");
      new DashBoardMenu();
    }  else if (e.getSource() == Refresh_Btn) {
		
	  System.out.println("refresh button clicked");
		search_combo.removeAllItems();
	    ClearCheckoutField();
		roomSearch();
		pack();
	    repaint();

	}
	 
	 else if (e.getSource() == checkOut_btn) {
      if (search_combo.getSelectedItem() == null) { // Show error if Search bar is Empty
        JOptionPane.showMessageDialog(
            null, "Error", "No room is selected", JOptionPane.WARNING_MESSAGE);
      } else {
        if (JOptionPane.showConfirmDialog(
                null, "Confirmation", "Are You Sure?", JOptionPane.YES_NO_OPTION)
            == JOptionPane.YES_OPTION) {
          try {
            // Input room number to search for
            String roomNo = (String) search_combo.getSelectedItem();
            // Create a temporary file to write updated data to
            File tempFile = new File("./files/temp.txt");
            System.out.println("temp.txt file created");
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
            BufferedReader br = new BufferedReader(new FileReader("./files/rooms.txt"));
            String line2;
            while ((line2 = br.readLine()) != null) {
              if (line2.equals("Rooms Details")) {
                String[] rowData = new String[5]; // create an array with 5 elements
                for (int i = 0; i < 5; i++) {
                  // read the next 5 lines and add the data to the corresponding column
                  rowData[i] = br.readLine();
                }
                if (rowData[0].equals(roomNo)) { // if the room number is a match
                  System.out.println("Room number found");
                  rowData[4] = "Not Booked"; // update the status
                  System.out.println("Updated room details to Not Booked");
                }
                // write the updated row data to the temporary file
                pw.println("Rooms Details");
                System.out.println("Updated details to temp file");
                for (int i = 0; i < 5; i++) {
                  pw.println(rowData[i]);
                }
              } else {
                // write non-"Rooms Details" lines to the temporary file unchanged
                pw.println(line2);
              }
            }
            br.close();
            pw.close();
            // Replace the original file with the temporary file
            File originalFile = new File("./files/rooms.txt");
            if (originalFile.delete()) {
              boolean renamed = tempFile.renameTo(originalFile);
              if (renamed) {
                System.out.println("File renamed successfully.");
              } else {
                System.out.println("Failed to rename the file.");
              }
            } else {
              System.out.println("Failed to delete the original file.");
            }
          } catch (Exception ex) {
            ex.printStackTrace();
          }
          roomNumToDelete =
              (String)
                  search_combo
                      .getSelectedItem(); // change this to whatever room number you want to delete

          deleteRoomEntry();

          JOptionPane.showMessageDialog(
              null, "Check Out Successful", "Check Out", JOptionPane.INFORMATION_MESSAGE);
          System.out.println("CheckOut Done Successfully");
          ClearCheckoutField();

          // Clearing the input fields
          name = null;
          mobileNumber = null;
          nationality = null;
          gmail = null;
          address = null;
          CheckinDate = null;
          gender = null;
          roomNo_B = null;
          bed = null;
          roomPrice = null;
		  CostOfSubscribtions = 0;
        }
        System.out.println("All data cleared from Text Field and Combo Box set to Default");
      }
    }

   else if (e.getSource() == search_combo) {
    ClearCheckoutField(); 
    String search = (String) search_combo.getSelectedItem();
    
    // Pass the selected room number to getCustomerData
	
    getCustomerData(search);
	repaint();
    revalidate();

  }}

public void getCustomerData(String selectedRoomNo) {
    System.out.println("getCustomerData function called");
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
    
	    // Calculate the number of days stayed
          SimpleDateFormat myFormat = new SimpleDateFormat("yyyy/MM/dd");
          Calendar cal = Calendar.getInstance();
          String dateBeforeString = CheckinDate;
		 if (CheckinDate == null) {CheckinDate = myFormat.format(cal.getTime());}
          Date dateBefore;
          try {
            dateBefore = myFormat.parse(dateBeforeString);
          } catch (ParseException ex) {

            throw new RuntimeException(ex);
          }
          String dateAfterString = myFormat.format(cal.getTime());
          Date dateAfter;
          try {
            dateAfter = myFormat.parse(dateAfterString);
          } catch (ParseException ex) {
            throw new RuntimeException(ex);
          }
          long difference = dateAfter.getTime() - dateBefore.getTime();
          int noOfDayStay = (int) (difference / (1000 * 60 * 60 * 24));
          // Ensure a minimum of 1 day stay
          if (noOfDayStay == 0) {
            noOfDayStay = 1;
          }
		  
        dayStay_fld.setText(String.valueOf(noOfDayStay));

        CustomerName_fld.setText(name);
        checkInDate_fld.setText(CheckinDate);
        CustomerNum_fld.setText(mobileNumber);
        pricePerDay_fld.setText(roomPrice);
        email_fld.setText(gmail);
		
		 String filePath2 = "./files/"+ selectedRoomNo +".txt";
		 if ( selectedRoomNo != null) {
		 try (BufferedReader br4 = new BufferedReader(new FileReader(filePath2))) {
            String line;
			
            while ((line = br4.readLine()) != null) {
                if (line.equals("Subscriptions Details")) {
                    String[] rowData = new String[5]; // create an array with 5 elements
                    for (int i = 0; i < 5; i++) {
                        // read the next 5 lines and add the data to the corresponding column
                        rowData[i] = br4.readLine();
                    }
                    if (rowData[0].equals(selectedRoomNo)) {
						if (rowData[1].equals("Breakfast Subscribed")) { CostOfSubscribtions+= 5 ;  }
						if (rowData[2].equals("Pool Subscribed")) { CostOfSubscribtions+= 4; }
						if (rowData[3].equals("Bar Subscribed")) { CostOfSubscribtions+= 7;}
						if (rowData[4].equals("Internet Subscribed")) { CostOfSubscribtions+= 2; }
					}
					 
                    }
                    }}  catch (IOException e) {
            e.printStackTrace();
		 } } else {
    System.out.println("File path is null.");
		 }
		SubscriptionsField.setText(String.valueOf(CostOfSubscribtions) + " $");
		
		// Calculate and display the total amount
         float price = Float.parseFloat(pricePerDay_fld.getText());
        totalAmount_fld.setText(String.valueOf((noOfDayStay * price)+ (CostOfSubscribtions * noOfDayStay) ) + " $");
    
     CostOfSubscribtions = 0;
    System.out.println("getCustomerData function executed successfully");

}

  

  public void deleteRoomEntry() {
    System.out.println("deleteRoomEntry funtion called");
    try {
      File inputFile = new File("./files/checkIn.txt");
      File tempFile = new File("./files/checkIn_temp.txt");
      System.out.println("temp file created");

      BufferedReader reader = new BufferedReader(new FileReader(inputFile));
      BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

      String currentLine;
      int lineCounter = 0;
      while ((currentLine = reader.readLine()) != null) {
        lineCounter++;
        if (currentLine.contains(roomNumToDelete)) {
          break;
          // skip the lines that contain the room number to delete
        }
      }

      reader.close();
      reader = new BufferedReader(new FileReader(inputFile));
      int k = 0;
      while ((currentLine = reader.readLine()) != null) {
        k++;
        if (k > (lineCounter - 3) && k < (lineCounter + 10)) {
          // skip lines before the room number to delete

        } else {
          // write all other lines to the temp file

          writer.write(currentLine + System.getProperty("line.separator"));
          System.out.println("writting into temp file");
        }
      }

      writer.close();
      reader.close();

      // delete the original file
      inputFile.delete();
      System.out.println("Orginal file deleted");

      // rename the temp file to the original file name
      tempFile.renameTo(inputFile);
      System.out.println("temp file renamed to orginal file");

    } catch (IOException ex) {
      ex.printStackTrace();
    }
    System.out.println("deleteRoomEntry funtion executed successfully");
  }


  public void roomSearch() {
    System.out.println("roomSearch funtion called");
    try (BufferedReader br = new BufferedReader(new FileReader("./files/rooms.txt"))) {
      String line;
      while ((line = br.readLine()) != null) {
        if (line.equals("Rooms Details")) {
          String[] rowData = new String[5]; // create an array with 5 elements
          for (int i = 0; i < 5; i++) {
            
            rowData[i] = br.readLine();
          }
          if (rowData[4].equals("Booked")) {
            search_combo.addItem(rowData[0]);
			roomNo_B = rowData[0];
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("roomSearch funtion executed successfully");
  }
 
  public void ClearCheckoutField()
  {
    System.out.println("ClearCheckoutField funtion called");
	SubscriptionsField.setText(null);
    CustomerName_fld.setText(null);
    CustomerNum_fld.setText(null);
	pricePerDay_fld.setText(null);
	dayStay_fld.setText(null);
	checkInDate_fld.setText(null);
	totalAmount_fld.setText(null);
	email_fld.setText(null);
    repaint();
    revalidate();
    System.out.println("ClearCheckoutField funtion executed successfully");
  }
  
}
	