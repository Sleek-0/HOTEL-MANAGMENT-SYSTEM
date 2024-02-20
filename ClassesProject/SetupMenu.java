package ClassesProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.Border;
import javax.swing.DefaultComboBoxModel;

public class SetupMenu extends JFrame implements ActionListener {

  private JPanel leftPanel;
  private final JTable table;
  private final JTextField roomNum_fld;
  private final JTextField price_fld;
  private final JButton logOut_btn;
  private final JButton back_btn;
  private final JButton add_btn;
  private final JButton del_btn;
  private final JComboBox<String> FloorCombobox;
  private final JComboBox<String> bed_box;
  private JPanel MainRightPanel;
  private JPanel RightPanelCenter;
  private JPanel RightPanelBottom;
  
  
  
  public SetupMenu() {
        setTitle("Setup - Hotel Management System");
        setResizable(false);
		setIconImage(
        Toolkit.getDefaultToolkit().getImage(SetupMenu.class.getResource("../IconsProject/2090700.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1208, 612);
        setLocationRelativeTo(null);
	
		
      //create a left panel for the table 
	    leftPanel = new JPanel(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(612, 612));
	    leftPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        leftPanel.setBackground(Color.DARK_GRAY);
		
		JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(22, 91, 536, 366);
        leftPanel.add(scrollPane);	
			
    table = new JTable();
    scrollPane.setViewportView(table);

    DefaultTableModel model =
        new DefaultTableModel(
            new Object[][] {},
            new String[] {"Room Number", "Floor", "Bed", "Price", "Status"}) {
          @Override
          public boolean isCellEditable(int row, int column) {
            return false; // Make all cells non-editable
          }
        };

    table.getTableHeader().setReorderingAllowed(false);
    table.setModel(model);
    table.getColumnModel().getColumn(0).setPreferredWidth(80);
    table.getColumnModel().getColumn(1).setPreferredWidth(82);

    model.setRowCount(0);

    // Set custom background colors for alternate rows
    table.setDefaultRenderer(
        Object.class,
        new DefaultTableCellRenderer() {
			@Override
          public Component getTableCellRendererComponent(
              JTable table,
              Object value,
              boolean isSelected,
              boolean hasFocus,
              int row,
              int column) {
            Component component =
                super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);
            if (row % 2 == 0) {
              component.setBackground(new Color(230, 230, 230)); // Light gray for even rows
            } else {
              component.setBackground(Color.WHITE); // White for odd rows
            }
            // Customize selection colors
            if (isSelected) {
              component.setBackground(Color.RED); // Set red background for selected row
            }

            return component;
          }
        });

    // Customize table header names
    JTableHeader header = table.getTableHeader();
    header.setBackground(new Color(150, 150, 150)); // Dark gray for header background
    header.setForeground(Color.WHITE); // White text color for header
    Font headerFont = header.getFont();
    header.setFont(headerFont.deriveFont(Font.BOLD)); // Make the font bold

    try (BufferedReader br = new BufferedReader(new FileReader("./files/rooms.txt"))) {
      String line;
      while ((line = br.readLine()) != null) {
        if (!line.equals("Rooms Details")) {
          String[] rowData = new String[5]; // create an array with 5 elements
          rowData[0] = line; // add the first element to the Room Number column
          for (int i = 1; i < 5; i++) {
            // read the next 4 lines and add the data to the corresponding column
            rowData[i] = br.readLine();
          }
          model.addRow(rowData); // add the row to the JTable
          br.readLine();
          br.readLine();
        }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
	
	    
		// Right panel with setup components and buttons
         MainRightPanel = new JPanel(new BorderLayout());
         MainRightPanel.setPreferredSize(new Dimension(596, 612));
         
		 
		 
        RightPanelCenter = new JPanel(new GridBagLayout());
		// Border for Right Panel
		Border rightPanelBorder = BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.DARK_GRAY, 70),
				BorderFactory.createLineBorder(Color.GRAY, 3));
		RightPanelCenter.setBorder(rightPanelBorder);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Sign up label
        JLabel SetUpText = new JLabel("SETUP");
        SetUpText.setFont(new Font("Serif", Font.BOLD, 50));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        RightPanelCenter.add(SetUpText, gbc);

        // room number label and field
        gbc.gridy = 1;
        RightPanelCenter.add(new JLabel("Room Number :"), gbc);
        gbc.gridy = 2;
        roomNum_fld = new JTextField(15);
		roomNum_fld.setColumns(10);
        RightPanelCenter.add(roomNum_fld, gbc);

        // room floor combo box
        gbc.gridy = 3;
        RightPanelCenter.add(new JLabel("Room Floor:"), gbc);
        gbc.gridy = 4;
        FloorCombobox = new JComboBox<>();
        FloorCombobox.setModel(new DefaultComboBoxModel<>(new String[] {"Floor 1", "Floor 2", "Floor 3"})); 
        RightPanelCenter.add(FloorCombobox, gbc);

        // bed type combo box
        gbc.gridy = 5;
		RightPanelCenter.add(new JLabel("Bed Type:"), gbc);
		gbc.gridy = 6;
        bed_box = new JComboBox<>();
        bed_box.setModel(new DefaultComboBoxModel<>(new String[] {"Single", "Double"}));
        RightPanelCenter.add(bed_box, gbc);
		
        //price label and field 
		gbc.gridy = 7;
		RightPanelCenter.add(new JLabel("Price:"), gbc);
		gbc.gridy = 8;
		price_fld = new JTextField(15);
        price_fld.setColumns(10);
        RightPanelCenter.add(price_fld, gbc);

		
        // add  button
        gbc.gridy = 9;
        gbc.insets = new Insets(20, 0, 0, 0);
        add_btn = new JButton("Add");
		add_btn.setFocusable(false);
        add_btn.addActionListener(this);
        RightPanelCenter.add(add_btn, gbc);
   
			 // remove button
        del_btn = new JButton("Delete");
        del_btn.addActionListener(this);
		
		
		 // back   button
        back_btn = new JButton("Back");
        back_btn.addActionListener(this);

        // exit button
        logOut_btn = new JButton("Exit");
        logOut_btn.addActionListener(this);
        
		RightPanelBottom = new JPanel();
		RightPanelBottom.setBackground(Color.DARK_GRAY);
        RightPanelBottom.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        RightPanelBottom.setBorder(new EmptyBorder(0, 0, 20, 20));
        RightPanelBottom.add(del_btn);
	    RightPanelBottom.add(back_btn);
		RightPanelBottom.add(logOut_btn);
		
	   MainRightPanel.add(RightPanelCenter, BorderLayout.CENTER);
	   MainRightPanel.add(RightPanelBottom, BorderLayout.SOUTH);
	 

        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.WEST);
        add(MainRightPanel, BorderLayout.CENTER);

        setVisible(true);
  }

 @Override
  public void actionPerformed(ActionEvent e) {
    // Get the input values from the text fields and combo boxes
    String romNum = roomNum_fld.getText(); // Room number
    String Floor_box = (String) FloorCombobox.getSelectedItem(); // Floor number
    String bed = (String) bed_box.getSelectedItem(); // Bed type
    String price = price_fld.getText().trim(); // Room price

    // Check if room number and price fields are empty
    boolean romNumEmpty = roomNum_fld.getText().isEmpty();
    boolean priceEmpty = price_fld.getText().isEmpty();

    if (e.getSource() == logOut_btn) {
      // Prompt for confirmation before logging out
      int yesORno =
          JOptionPane.showConfirmDialog(
              null, "Are you sure ?", "Alert!", JOptionPane.YES_NO_OPTION);

      if (yesORno == JOptionPane.YES_OPTION) {
        this.setVisible(false);
        System.out.println("Exited from ManageRoom class");
        System.exit(1);
      }
    } else if (e.getSource() == back_btn) {
      setVisible(false);
      System.out.println("Exited from ManageRoom class");
        new DashBoardMenu();
    } else if (e.getSource() == add_btn) {
      // Check if room number and price are not empty
      if (!romNumEmpty && !priceEmpty) {
        boolean flag = false;
        try (BufferedReader br = new BufferedReader(new FileReader("./files/rooms.txt"))) {
          String line;
          // Check if the room number already exists in the file
          System.out.println("Checking if room number already exists");
          while ((line = br.readLine()) != null) {
            if (line.equals(roomNum_fld.getText())) {
              flag = true;
              break;
            }
          }

          if (flag) {
            System.out.println("Room number already exists");
          } else {
            System.out.println("Room number available to add");
          }
        } catch (Exception ex) {
          ex.printStackTrace();
        }

        if (flag) {
          // Display a warning message if the room number already exists
          JOptionPane.showMessageDialog(
              null, "Room number already exist", "Error", JOptionPane.WARNING_MESSAGE);
        } else if (!price.matches("\\d+")) {
          JOptionPane.showMessageDialog(
              null, "Invalid Price", "Error", JOptionPane.WARNING_MESSAGE);
        } else {
          try {
            String line = "./files/rooms.txt";
            try {
              File file = new File(line);
              if (!file.exists()) {
                file.createNewFile();
                // Append data to the file
                FileWriter fileWriter = new FileWriter(file, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                PrintWriter printWriter = new PrintWriter(bufferedWriter);
                printWriter.close();
              }

              BufferedReader rdfile3 = new BufferedReader(new FileReader("./files/rooms.txt"));
              int ttlLines3 = 0;
              while (rdfile3.readLine() != null) {
                ttlLines3++;
              }
              rdfile3.close();

              // Append room details to the file
              FileWriter fileWriter = new FileWriter(file, true);
              BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
              PrintWriter printWriter = new PrintWriter(bufferedWriter);
              printWriter.println("Rooms Details");
              printWriter.println(romNum);
              printWriter.println(Floor_box);
              printWriter.println(bed);
              printWriter.println(price);
              printWriter.println("Not Booked");
              printWriter.println();
              printWriter.close();

              // Clear text fields
              roomNum_fld.setText(null);
              price_fld.setText(null);

              System.out.println("New Room added");

            } catch (Exception ex) {
              ex.printStackTrace();
            }
          } catch (Exception ex) {
            ex.printStackTrace();
          }
        }
      } else {
        // Display a warning message if any box is not filled
        JOptionPane.showMessageDialog(
            null, "Please Fill all the box", "Error", JOptionPane.WARNING_MESSAGE);
      }
      DefaultTableModel model = (DefaultTableModel) table.getModel();
      model.setRowCount(0);

      try (BufferedReader br = new BufferedReader(new FileReader("./files/rooms.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
          if (!line.equals("Rooms Details")) {
            String[] rowData = new String[5]; // create an array with 5 elements
            rowData[0] = line; // add the first element to the Room Number column
            for (int i = 1; i < 5; i++) {
              // read the next 4 lines and add the data to the corresponding column
              rowData[i] = br.readLine();
            }
            model.addRow(rowData); // add the row to the JTable
            br.readLine(); // skip two empty lines
            br.readLine();
          }
        }
      } catch (Exception ex) {
        ex.printStackTrace();
      }

    } else if (e.getSource() == del_btn) {
      if (JOptionPane.showConfirmDialog(
              null, "Confirmation", "Remove This Room?", JOptionPane.YES_NO_OPTION)
          == JOptionPane.YES_OPTION) {
        DefaultTableModel tempTbl = (DefaultTableModel) table.getModel();
        int selectedRow = table.getSelectedRow();

        // Check if a row is selected
        if (table.getSelectedRow() != -1) {

          // Get data from the selected row
          String[] data = new String[5];
          for (int i = 0; i < 5; i++) {
            data[i] = tempTbl.getValueAt(selectedRow, i).toString();
          }

          // Check if the room is not booked
          if (data[4].equals("Not Booked")) {
            try {
              File inputFile = new File("./files/rooms.txt");
              File tempFile = new File("./files/rooms_temp.txt");
              System.out.println("temp file created");

              // Read the original file and write to the temp file, excluding the room to delete
              BufferedReader reader = new BufferedReader(new FileReader(inputFile));
              BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
              System.out.println("temp file updated");

              String currentLine;
              int lineCounter = 0;
              while ((currentLine = reader.readLine()) != null) {
                lineCounter++;
                if (currentLine.contains(data[0])) {
                  break;
                  // skip the lines that contain the room number to delete
                }
              }

              reader.close();
              reader = new BufferedReader(new FileReader(inputFile));
              int k = 0;
              while ((currentLine = reader.readLine()) != null) {
                k++;
                if (k > (lineCounter - 2) && k < (lineCounter + 6)) {
                } else {
                  // write all other lines to the temp file
                  writer.write(currentLine + System.getProperty("line.separator"));
                }
              }

              System.out.println("Room deleted");

              writer.close();
              reader.close();

              // delete the original file
              inputFile.delete();
              System.out.println("Original file deleted");

              // rename the temp file to the original file name
              tempFile.renameTo(inputFile);
              System.out.println("temp file renamed as original file");

            } catch (IOException ex) {
              ex.printStackTrace();
            }
            // Remove the selected row from the table
            tempTbl.removeRow(table.getSelectedRow());
          } else {
            JOptionPane.showMessageDialog(this, "Room is Booked Please check out it first");
          }

        } else {
          if (table.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Table is Empty!");

          } else {
            JOptionPane.showMessageDialog(this, "Please select A row to delete ");
          }
        }
      }
    }
  }
}
