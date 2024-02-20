package ClassesProject;

import ClassesProject.GetRoom;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
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
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.Border;
import java.util.List;
import java.util.ArrayList;

public class CheckInMenu extends JFrame implements ActionListener, interfaces.CheckInInfo{
	
    private JPanel leftPanel;
    private JPanel rightPanel;
  private final JTextField name_field;
  private final JTextField mbl_fld;
  private final JTextField nationality_fld;
  private final JTextField gmail_fld;
  private final JTextField address_fld;
  private final JTextField date_fld;
   private final JTextField check_out_date_fld;
  private final JTextField cost_fld;

  // Create JComboBoxes that can only accept Strings as items
  private final JComboBox<String> bed_Box;
  private final JComboBox<String> floorNo_Box;
  private final JComboBox<String> roomNo_Box;
  private final JComboBox<String> gender_Box;

  private final JButton back_btn;
  private final JButton confirm_btn;
  private final JButton clear_btn;
  private final JButton logOut_Btn;
  private final JButton Refresh_Btn;
  
  public String roomNo;
  public CheckInMenu() {
	    setTitle("Check In - Hotel Management System");
        setResizable(false);
		setIconImage(
        Toolkit.getDefaultToolkit().getImage(CheckInMenu.class.getResource("../IconsProject/2090700.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1212, 612);
        setLocationRelativeTo(null);
        
		 // Left panel with background image
        leftPanel = new JPanel(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(408, 612));
        ImageIcon obj = new ImageIcon("IconsProject/checkIn.jpg");
        JLabel CheckIntImg = new JLabel(obj);
        leftPanel.add(CheckIntImg);
		
		// Main Right panel with 2 Subpanels and stuff
		rightPanel = new JPanel();
		rightPanel.setPreferredSize(new Dimension(800, 612));
		rightPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(Color.DARK_GRAY);
						
		JPanel personalInfoPanel = new JPanel(new GridBagLayout());
	
   
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(45, 10, 1, 25);
        gbc.weightx = 1.0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		personalInfoPanel.add(new JLabel("Full Name:"), gbc);
		gbc.gridx = 1;
		name_field = new JTextField(15);
		personalInfoPanel.add(name_field, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		personalInfoPanel.add(new JLabel("Phone Number:"), gbc);
		gbc.gridx = 1;
		mbl_fld = new JTextField(15);
		personalInfoPanel.add(mbl_fld, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		personalInfoPanel.add(new JLabel("Nationality:"), gbc);
		gbc.gridx = 1;
		nationality_fld = new JTextField(15);
		personalInfoPanel.add(nationality_fld, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		personalInfoPanel.add(new JLabel("Gender:"), gbc);
		gbc.gridx = 1;
		gender_Box = new JComboBox<>(new String[]{"Male", "Female"});
		personalInfoPanel.add(gender_Box, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		personalInfoPanel.add(new JLabel("G-Mail:"), gbc);
		gbc.gridx = 1;
		gmail_fld = new JTextField(15);
		personalInfoPanel.add(gmail_fld, gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		personalInfoPanel.add(new JLabel("Address:"), gbc);
		gbc.gridx = 1;
		address_fld = new JTextField(15);
		personalInfoPanel.add(address_fld, gbc);


        // Dates Panel
					
		JPanel datePanel = new JPanel(new GridBagLayout());
		GridBagConstraints dateGBC = new GridBagConstraints();
		dateGBC.anchor = GridBagConstraints.WEST;
		dateGBC.insets = new Insets(17, 10, 5, 20);

		dateGBC.gridx = 0;
		dateGBC.gridy = 0;
		datePanel.add(new JLabel("Todays Date:"), dateGBC);
		dateGBC.gridx = 1;
		date_fld = new JTextField(15);
		date_fld.setEditable(false);
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();
		date_fld.setText(myFormat.format(cal.getTime()));
		datePanel.add(date_fld, dateGBC);

		dateGBC.gridx = 0;
		dateGBC.gridy = 1;
		datePanel.add(new JLabel("Check Out Date:"), dateGBC);
		dateGBC.gridx = 1;
		check_out_date_fld = new JTextField(10);
		check_out_date_fld.setText("yyyy/MM/dd");
		datePanel.add(check_out_date_fld, dateGBC);

		// Booking Information Panel
		
		JPanel bookingInfoPanel = new JPanel(new GridBagLayout());
	 
		GridBagConstraints bookingGBC = new GridBagConstraints();
		bookingGBC.anchor = GridBagConstraints.WEST;
		bookingGBC.weightx = 1.0;
		bookingGBC.insets = new Insets(10, 10, 45, 25);
		bookingGBC.gridx = 0;
		bookingGBC.gridy = 0;

	
		bookingInfoPanel.add(new JLabel("Bed Type:"), bookingGBC);
		bookingGBC.gridx = 1;
		bed_Box = new JComboBox<>();
		bed_Box.setModel(new DefaultComboBoxModel<>(new String[]{"Single", "Double", "Triple"}));
		bookingInfoPanel.add(bed_Box, bookingGBC);
        
		
		bookingGBC.gridx = 0;
		bookingGBC.gridy = 1;
		bookingInfoPanel.add(new JLabel("Floor:"), bookingGBC);
		bookingGBC.gridx = 1;
		floorNo_Box = new JComboBox<>();
		floorNo_Box.setModel(new DefaultComboBoxModel<>(new String[]{"Floor 1", "Floor 2", "Floor 3"}));
		bookingInfoPanel.add(floorNo_Box, bookingGBC);

		bookingGBC.gridx = 0;
		bookingGBC.gridy = 2;
		bookingInfoPanel.add(new JLabel("Room:"), bookingGBC);
		bookingGBC.gridx = 1;
		roomNo_Box = new JComboBox<>();
		roomNo_Box.addActionListener(this);
		bookingInfoPanel.add(roomNo_Box, bookingGBC);

		bookingGBC.gridx = 0;
		bookingGBC.gridy = 3;
		bookingInfoPanel.add(new JLabel("Room Cost:"), bookingGBC);
		bookingGBC.gridx = 1;
		cost_fld = new JTextField(6);
		cost_fld.setText("     -   $");
		bookingInfoPanel.add(cost_fld, bookingGBC);
		  
		// Buttons Panel
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
		buttonsPanel.setBackground(Color.DARK_GRAY);
		
		
		   confirm_btn = new JButton("Confirm");
           confirm_btn.addActionListener(this);
		   clear_btn = new JButton("Clear All");
           clear_btn.addActionListener(this);
		   back_btn = new JButton("Back");
           back_btn.addActionListener(this);
		   logOut_Btn = new JButton("Log Out");
           logOut_Btn.addActionListener(this);
		   Refresh_Btn = new JButton("Refresh Rooms and Cost");
           Refresh_Btn.addActionListener(this);
		buttonsPanel.add(confirm_btn);
		buttonsPanel.add(clear_btn);
		buttonsPanel.add(back_btn);
		buttonsPanel.add(logOut_Btn);
		buttonsPanel.add(Refresh_Btn);

	     // Create a new BorderLayout for the west side of right panel containing the personal information panel
		JPanel westPanel = new JPanel(new BorderLayout());
		  
        // Add personal info panel to the center of the westPanel
		westPanel.add(personalInfoPanel, BorderLayout.CENTER);

		// Create a new BorderLayout for the east side of right panel containing the dates and booking information panels
		JPanel eastPanel = new JPanel(new BorderLayout());
		
		
		// Add datePanel to the north and bookingInfoPanel to the center of the eastPanel
		eastPanel.add(datePanel, BorderLayout.NORTH);
		eastPanel.add(bookingInfoPanel, BorderLayout.CENTER);

		// Add Panels to rightPanel
		rightPanel.add(westPanel, BorderLayout.WEST);
		rightPanel.add(buttonsPanel, BorderLayout.SOUTH);
		rightPanel.add(eastPanel, BorderLayout.EAST);
				
	

				// Border for Personal Info Panel
		Border personalInfoBorder = BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(100, 100, 100), 2),
				BorderFactory.createEmptyBorder(10, 10, 10, 10));
		personalInfoPanel.setBorder(BorderFactory.createTitledBorder(
				personalInfoBorder, "Personal Information", TitledBorder.CENTER, TitledBorder.TOP,
				new Font("SansSerif", Font.BOLD, 20), Color.DARK_GRAY));

		// Border for Dates Panel
		Border datePanelBorder = BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(100, 100, 100), 2),
				BorderFactory.createEmptyBorder(10, 10, 10, 10));
		datePanel.setBorder(BorderFactory.createTitledBorder(
				datePanelBorder, "Dates", TitledBorder.CENTER, TitledBorder.TOP,
				new Font("SansSerif", Font.BOLD, 20), Color.DARK_GRAY));

		// Border for Booking Information Panel
		Border bookingInfoBorder = BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(100, 100, 100), 2),
				BorderFactory.createEmptyBorder(10, 10 , 10, 10));
		bookingInfoPanel.setBorder(BorderFactory.createTitledBorder(
				bookingInfoBorder, "Booking Information", TitledBorder.CENTER, TitledBorder.TOP,
				new Font("SansSerif", Font.BOLD, 20), Color.DARK_GRAY));

		// Border for Buttons Panel
		Border buttonsPanelBorder = BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.DARK_GRAY, 2),
				BorderFactory.createEmptyBorder(20, 10, 10, 10));
		buttonsPanel.setBorder(buttonsPanelBorder);

		// Border for West Panel (Personal Info)
		Border westPanelBorder = BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(100, 100, 100), 2),
				BorderFactory.createEmptyBorder(20, 16, 20, 16));
		westPanel.setBorder(westPanelBorder);
        
		// Border for East Panel (Dates and Booking Info)
		Border eastPanelBorder = BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(100, 100, 100), 2),
				BorderFactory.createEmptyBorder(20, 24, 20, 16));
		eastPanel.setBorder(eastPanelBorder);

		// Border for Right Panel
		Border rightPanelBorder = BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(169, 169, 169), 2),
				BorderFactory.createEmptyBorder(5, 5, 5, 5));
		rightPanel.setBorder(rightPanelBorder);

		
		
        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        setVisible(true);
		
		updateRoomComboBox();
		pack();
        repaint();
  }
  
  	private void updateRoomComboBox() {
    // Get the selected floor and bed type
    String selectedFloor = (String) floorNo_Box.getSelectedItem();
    String selectedBedType = (String) bed_Box.getSelectedItem();

    // Get the available rooms based on the selected floor and bed type
    GetRoom roomInstance = new GetRoom();
    List<String> availableRooms = roomInstance.getAvailableRooms(selectedFloor, selectedBedType, roomNo_Box, cost_fld);
     // Update GUI components based on available rooms
        roomInstance.updateGUIComponents(roomNo_Box, cost_fld, availableRooms);
}
  
  
	  @Override
  public void actionPerformed(ActionEvent e) {

    String name = name_field.getText();
    String mobileNumber = mbl_fld.getText();
    String nationality = nationality_fld.getText();
    String gmail = gmail_fld.getText();
    String address = address_fld.getText();
    String checkindate = date_fld.getText();
    String cost = cost_fld.getText();
    String gender = (String) gender_Box.getSelectedItem();
    String roomNo_B = (String) roomNo_Box.getSelectedItem();

    boolean isNameFieldEmpty = name_field.getText().isEmpty();
    boolean isMobileNumberFieldEmpty = mbl_fld.getText().isEmpty();
    boolean isNationalityFieldEmpty = nationality_fld.getText().isEmpty();
    boolean isGmailEmpty = gmail_fld.getText().isEmpty();
    boolean isAddressEmpty = address_fld.getText().isEmpty();
    boolean isCheckinDateEmptyField = date_fld.getText().isEmpty();
    boolean isCostFieldEmpty = cost_fld.getText().isEmpty();

    if (e.getSource() == back_btn) {
      // Navigate back to the dashboard
      setVisible(false);
      System.out.println("Exited from CheckIn class");
      new DashBoardMenu();
    } else if (e.getSource() == logOut_Btn) {
      // Prompt for confirmation and logout if confirmed
      int yesORno =
          JOptionPane.showConfirmDialog(
              null, "Are you sure ?", "Alert!", JOptionPane.YES_NO_OPTION);

      if (yesORno == JOptionPane.YES_OPTION) {
        // Logout and show the login screen
        setVisible(false);
        System.out.println("Exited from CheckIn class");
        new LoginMenu();
      }
    } else if (e.getSource() == clear_btn) {
      // Clear all input fields
      name_field.setText(null);
      mbl_fld.setText(null);
      nationality_fld.setText(null);
      gmail_fld.setText(null);
      address_fld.setText(null);
      cost_fld.setText(null);
      gender_Box.setSelectedIndex(0);
      bed_Box.setSelectedIndex(0);
      floorNo_Box.setSelectedIndex(0);
      System.out.println("All data cleared from Text Field and Combo Box set to Default");
    } else if (e.getSource() == confirm_btn) {
      if (!isNameFieldEmpty
          && !isMobileNumberFieldEmpty
          && !isNationalityFieldEmpty
          && !isGmailEmpty
          && !isAddressEmpty
          && !isCheckinDateEmptyField
          && !isCostFieldEmpty) {

        // Checks if Nationality contains numbers or special character
        if (!nationality.matches("[a-zA-Z]+")) {
          JOptionPane.showMessageDialog(
              null,
              "Nationality cannot contain numbers or special character",
              "Error",
              JOptionPane.WARNING_MESSAGE);
          nationality_fld.setText(null);
          // checks if gmail contains @ and .com
        } else if (!(gmail.contains("@") && gmail.contains(".com"))) {
          JOptionPane.showMessageDialog(
              null, "Gmail must contain @ and .com", "Error", JOptionPane.WARNING_MESSAGE);
          gmail_fld.setText(null);
          // checks if address contain
        } else if (address.matches("^\\d+$")) {
          JOptionPane.showMessageDialog(
              null, "Address can not contain only number", "Error", JOptionPane.WARNING_MESSAGE);
          address_fld.setText(null);
          // checks if the mobile number is valid
        } else if (!(mobileNumber.length() == 11 && mobileNumber.matches("\\d+"))) {
          JOptionPane.showMessageDialog(
              null, "mobile number must be only 11 digit", "Error", JOptionPane.WARNING_MESSAGE);
        } else {
          try {
            String CheckinFilePath = "./files/checkIn.txt";
            try {
              // Create a new file or use an existing file for check-in data
              File file = new File(CheckinFilePath);
              if (!file.exists()) {
                boolean created = file.createNewFile();
                if (created) {
                  System.out.println("File created successfully.");
                } else {
                  System.out.println("File creation failed.");
                }
                // Open the file for writing
                FileWriter fileWriter = new FileWriter(file, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                PrintWriter printWriter = new PrintWriter(bufferedWriter);
                printWriter.close();
              } else {
                System.out.println("File already exists.");
              }
              // Write check-in data to the file
              WriteCheckinData(
                  nationality,
                  gmail,
                  address,
                  checkindate,
                  cost,
                  gender,
                  roomNo_B,
                  file,
                  name,
                  mobileNumber);

              try {
                // Input room number to search for
                String roomNo = Objects.requireNonNull(roomNo_Box.getSelectedItem()).toString();
                // Create a temporary file to write updated data to
                File tempFile = new File("./files/temp.txt");
                System.out.println("temp file created");
                PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
                System.out.println("Writting into temp file");
                BufferedReader br = new BufferedReader(new FileReader("./files/rooms.txt"));
                System.out.println("Reading from room.txt");
                String line2;
                while ((line2 = br.readLine()) != null) {
                  if (line2.equals("Rooms Details")) {
                    String[] rowData = new String[5]; // create an array with 5 elements
                    for (int i = 0; i < 5; i++) {
                      // read the next 5 lines and add the data to the corresponding column
                      rowData[i] = br.readLine();
                      System.out.println("reading room.txt");
                    }
                    if (rowData[0].equals(roomNo)) { // if the room number is a match
                      System.out.println("Room found");
                      rowData[4] = "Booked"; // update the status
                      System.out.println("Updated room status to Booked");
                    }
                    // write the updated row data to the temporary file
                    pw.println("Rooms Details");
                    System.out.println("Updating data to temp file");
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

              JOptionPane.showMessageDialog(
                  null,
                  "Congratulation Check In successfully",
                  "Congratulation",
                  JOptionPane.INFORMATION_MESSAGE);
              System.out.println("Check in done successfully");
              // Clearing the input fields
              name_field.setText(null);
              mbl_fld.setText(null);
              nationality_fld.setText(null);
              gmail_fld.setText(null);
              address_fld.setText(null);
              cost_fld.setText(null);
              gender_Box.setSelectedIndex(0);
              bed_Box.setSelectedIndex(0);
              floorNo_Box.setSelectedIndex(0);
              System.out.println("All data cleared from Text Field and Combo Box set to Default");

            } catch (Exception ex) {
              ex.printStackTrace();
            }
          } catch (Exception ex) {
            ex.printStackTrace();
          }
        }
      } else {
        JOptionPane.showMessageDialog(
            null, "Please Fill all the box", "Error", JOptionPane.WARNING_MESSAGE);
      }
    } else if (e.getSource() == Refresh_Btn) {
      // Update room details based on selected floor number
        updateRoomComboBox();
		pack();
        repaint();

    } else if (e.getSource() == roomNo_Box) {
      roomNo = (String) roomNo_Box.getSelectedItem(); // Get the selected room number
      try (BufferedReader br = new BufferedReader(new FileReader("./files/rooms.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
          if (line.equals("Rooms Details")) {
            String[] rowData = new String[5]; // create an array with 5 elements
            for (int i = 0; i < 5; i++) {
              // read the next 4 lines and add the data to the corresponding column
              rowData[i] = br.readLine();
            }
            if (rowData[0].equals(roomNo)) {
              // Set the cost in the cost_fld
              cost_fld.setText(rowData[3]);
            }
          }
        }
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }
  public void WriteCheckinData(
      String nationality,
      String gmail,
      String address,
      String CheckInDate,
      String cost,
      String gender,
      String roomNo_B,
      File file,
      String fullName,
      String mobileNumber)
      throws IOException {
    System.out.println("WriteCheckinData funtion called");
    FileWriter fileWriter = new FileWriter(file, true);
    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
    PrintWriter printWriter = new PrintWriter(bufferedWriter);
    // Write check-in details to the file
    printWriter.println("Check In Details");
    printWriter.println(fullName);
    printWriter.println(roomNo_B);
    printWriter.println(gender);
    printWriter.println(mobileNumber);
    printWriter.println(nationality);
    printWriter.println(gmail);
    printWriter.println(address);
    printWriter.println(CheckInDate);
    printWriter.println(cost);
    printWriter.println("Booked");
    printWriter.println();

    printWriter.close();
    System.out.println("WriteCheckinData funtion executed successfully");
    System.out.println("New checkIn data stored successfully");
  }
}
