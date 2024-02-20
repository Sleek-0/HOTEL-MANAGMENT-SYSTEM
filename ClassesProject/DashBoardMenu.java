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

// Custom UI to allow tabs to span the full width
class FullWidthTabbedPaneUI extends BasicTabbedPaneUI {
    @Override
    protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
        return super.calculateTabWidth(tabPlacement, tabIndex, metrics) + 77; // Add some padding
    }
}

public class DashBoardMenu extends JFrame implements ActionListener {
    private JPanel leftPanel;
    private JPanel rightPanel;
	private JPanel leftNorthPanel;
	private JPanel Button_Left_South_Panel;
    private final JButton checkoutButton;
    private final JButton exitButton;
	private final JButton logoutButton;
    private final JButton checkinButton;
    private final JButton ManageRoomsButton;
    private int y_layout;
	private int x_layout;
	
      public DashBoardMenu() {
        setTitle("Dash Board - Hotel Management System");
        setResizable(false);
		setIconImage(
        Toolkit.getDefaultToolkit().getImage(DashBoardMenu.class.getResource("../IconsProject/2090700.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(980, 612);
        setLocationRelativeTo(null);


        // right panel with card layout
		//to do
        rightPanel = new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(800, 612));
        rightPanel.setBackground(Color.DARK_GRAY);
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setUI(new FullWidthTabbedPaneUI());
		ImageIcon iconnumb1 = new ImageIcon("IconsProject/number1icon.png");
		ImageIcon iconnumb2 = new ImageIcon("IconsProject/number2icon.png");
		ImageIcon iconnumb3 = new ImageIcon("IconsProject/number3icon.png");

		JComponent panel1 = makeRoomPanel("Floor 1");
		tabbedPane.addTab("         		Floor 1 		        ", iconnumb1, panel1,
						  "hey");

		JComponent panel2 = makeRoomPanel("Floor 2");
		tabbedPane.addTab("      	   Floor 2     	    ", iconnumb2, panel2,
						  "hru");


		JComponent panel3 = makeRoomPanel("Floor 3");
		tabbedPane.addTab("         Floor 3         ", iconnumb3, panel3,
						  "have a good day");

		rightPanel.add(tabbedPane, BorderLayout.CENTER);

        // Left panel with buttons
        leftPanel = new JPanel(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(180, 612));
        leftPanel.setBackground(Color.DARK_GRAY);
        leftPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        // check in button

		ImageIcon CheckInIcon = new ImageIcon("IconsProject/checkiniconv1.png");
        checkinButton = new JButton(CheckInIcon);
		checkinButton.setOpaque(false);
        checkinButton.setContentAreaFilled(false);
        checkinButton.setBorderPainted(false);
        checkinButton.setFocusPainted(true);
        checkinButton.addActionListener(this);

        // check out button

		ImageIcon CheckOutIcon = new ImageIcon("IconsProject/checkouticonv1.png");
        checkoutButton = new JButton(CheckOutIcon);
		checkoutButton.setOpaque(false);
        checkoutButton.setContentAreaFilled(false);
        checkoutButton.setBorderPainted(false);
        checkoutButton.setFocusPainted(true);
        checkoutButton.addActionListener(this);
        
        // ManageRooms button
		ImageIcon manageicon = new ImageIcon("IconsProject/manageiconv1.png");
        ManageRoomsButton = new JButton(manageicon);
		ManageRoomsButton.setOpaque(false);
        ManageRoomsButton.setContentAreaFilled(false);
        ManageRoomsButton.setBorderPainted(false);
        ManageRoomsButton.setFocusPainted(true);
        ManageRoomsButton.addActionListener(this);
        
		
		leftNorthPanel = new JPanel((new FlowLayout(FlowLayout.LEFT, 20, 30)));
		leftNorthPanel.setBackground(Color.DARK_GRAY);
        leftNorthPanel.setSize(180, 612);
		
		leftNorthPanel.add(ManageRoomsButton);
		leftNorthPanel.add(checkoutButton);
		leftNorthPanel.add(checkinButton);
		
		
		leftPanel.add(leftNorthPanel, BorderLayout.CENTER);
	   
	   
        // Exit button
        JPanel Button_Left_South_Panel = new JPanel();
	    Button_Left_South_Panel.setBackground(Color.DARK_GRAY);
	    Button_Left_South_Panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        Button_Left_South_Panel.setBorder(new EmptyBorder(5, 5, 10, 5));
   
        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
		Button_Left_South_Panel.add(exitButton);
       
		
        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(this);
		Button_Left_South_Panel.add(logoutButton);

        leftPanel.add(Button_Left_South_Panel, BorderLayout.SOUTH);
        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        setVisible(true);
    }
    protected JComponent makeRoomPanel(String FloorNB_entered) {
    System.out.println("roomSearch function called for floor: " + FloorNB_entered);
    
    // Create a list to store the rooms
    ArrayList<String> total_rooms = new ArrayList<>();
    
    try (BufferedReader br = new BufferedReader(new FileReader("./files/rooms.txt"))) {
        String line;

        while ((line = br.readLine()) != null) {
            if (line.equals("Rooms Details")) {
                String[] rowData = new String[5]; // create an array with 5 elements
                for (int i = 0; i < 5; i++) {
                    rowData[i] = br.readLine();
                }
                if (rowData[1].equals(FloorNB_entered)) {
                    total_rooms.add(rowData[0]);
                    System.out.println("Floor number: " + FloorNB_entered + " rooms found: " + total_rooms);
                }

            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    System.out.println("roomSearch function for floor: " + FloorNB_entered + " executed successfully");
    
    System.out.println("draw function called for floor: " + FloorNB_entered);

    // Create a panel to hold the buttons
    JPanel panel = new JPanel();
	panel.setBackground(Color.DARK_GRAY);

    // Check if any rooms were found
    if (!total_rooms.isEmpty()) {
        int array_of_rooms_length = total_rooms.size();
		
		// formula to keep the rooms created looking nice
			 y_layout = (int ) Math.ceil(Math.sqrt((array_of_rooms_length))); 
             x_layout =  (int ) Math.ceil((double) (array_of_rooms_length/ y_layout)); 		 
		

        
        // Adjust the layout based on the number of rooms
        panel.setLayout(new GridLayout(y_layout, x_layout, 20, 25));
		panel.setBorder(new EmptyBorder(30, 30, 30, 30));

        for (String room : total_rooms) {
            JButton btnsample = new JButton(room);
			btnsample.setForeground(Color.BLACK);
            Font font = new Font("Serif", Font.BOLD, 15); // You can adjust the size and style as needed
            btnsample.setFont(font);
            // Set an action listener to show/hide the customer information panel when the room button is clicked
            btnsample.addActionListener(e -> {
				// Create a custom panel for customer information
          new RoomInfoPanel(room);

            });	
			
			// read through the file and if it is booked set the button background to red, if not then green
					try (BufferedReader br2 = new BufferedReader(new FileReader("./files/rooms.txt"))) {
			String line5;

			while ((line5 = br2.readLine()) != null) {
				if (line5.equals("Rooms Details")) {
					String[] rowData5 = new String[5]; // create an array with 5 elements
					for (int i = 0; i < 5; i++) {
						rowData5[i] = br2.readLine();
					}
					if (rowData5[0].equals(room)) { // Check if the room number matches
						if (rowData5[4].equals("Booked")) {
							btnsample.setBackground(Color.RED);
						} else {
							btnsample.setBackground(Color.GREEN);
						}
						break; // Exit the loop once the room is found
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
            panel.add(btnsample);
        }
    } else {
        // If no rooms were found, display a message
        JLabel noRoomsLabel = new JLabel("No rooms found on Floor " + FloorNB_entered);
        panel.add(noRoomsLabel);
    }

    System.out.println("draw function called for floor: " + FloorNB_entered + " executed successfully");
    
    return panel;
}

	
	@Override
    public void actionPerformed(ActionEvent e) {
	 
		         
        if (e.getSource() == logoutButton) {
            int yesORno = JOptionPane.showConfirmDialog(
                    null, "Are you sure?", "Alert!", JOptionPane.YES_NO_OPTION);
            if (yesORno == 0) {
			this.setVisible(false);
               new LoginMenu();
            }
        }
       else if (e.getSource() == exitButton) {
            int yesORno = JOptionPane.showConfirmDialog(
                    null, "Are you sure?", "Alert!", JOptionPane.YES_NO_OPTION);
            if (yesORno == 0) {
                System.exit(0);
            }
        }
         else if (e.getSource() == checkinButton) { // If the "checkinButton" button was clicked, go to the 
      this.setVisible(false);
      System.out.println("User is checking in...");
      new CheckInMenu();
        }
	     else if (e.getSource() == checkoutButton) {
      // If the checkout button was clicked, 
      System.out.println("User is checking out");
      this.setVisible(false);
           new CheckOutMenu();
       }
         else if (e.getSource() == ManageRoomsButton) {
			  // If the manage button was clicked, 
      System.out.println("User is managing rooms...");
      this.setVisible(false);
      new SetupMenu();
     
      }
    }
	   }
	    

