package ClassesProject;

import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForgetPassMenu2 extends JFrame implements ActionListener {

	private JPanel leftPanel;
    private JPanel MainRightPanel;
    private JPanel RightPanelCenter;
	private JPanel RightPanelBottom;
	private final JTextField phoneField;
	private final JButton exitButton;
	private final JButton continueButton;

  public ForgetPassMenu2() {
    System.out.println("Currently in ForgetPassMenu2 class");
    setTitle("Forgot Password - Hotel Management System");
        setResizable(false);
		setIconImage(
        Toolkit.getDefaultToolkit().getImage(ForgetPassMenu2.class.getResource("../IconsProject/2090700.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1208, 460);
        setLocationRelativeTo(null);
		
		
		
    // Left panel with background image
        leftPanel = new JPanel(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(612, 460));
        ImageIcon obj1 = new ImageIcon("IconsProject/resetimg.jpg");
        JLabel SignupImgLabel = new JLabel(obj1);
		SignupImgLabel.setBounds(0, 0, leftPanel.getPreferredSize().width, leftPanel.getPreferredSize().height);
        leftPanel.add(SignupImgLabel);
		if (obj1.getImageLoadStatus() == MediaTracker.COMPLETE) {
          System.out.println(" Forget pass menu 2 Image loaded successfully.");
          } else {
                    System.out.println("Forget pass menu 2 Image loading failed.");}  
					
       
		// Right panel with forget pass components
         MainRightPanel = new JPanel(new BorderLayout());
         MainRightPanel.setPreferredSize(new Dimension(596, 460));
  
        RightPanelCenter = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
 
        gbc.insets = new Insets(5, 5, 5, 5);
		
     // Reset pass title 
        JLabel ForgetTxt = new JLabel("RESET PASSWORD");
        ForgetTxt.setFont(new Font("Serif", Font.BOLD, 50));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        RightPanelCenter.add(ForgetTxt, gbc);
		
	 //hint text	
	
		try {
			int k = ((ForgetPassMenu1.deleteLine) + 2);
			String line1 = Files.readAllLines(Paths.get("./files/user_login.txt")).get(k);
			String line2 = "";
			line2 = line2 + line1.charAt(16);
			line2 = line2 + line1.charAt(17);
			line2 = line2 + line1.charAt(18);

			JLabel hintPhnNum = new JLabel("Hint :xxxxxxxx" + line2);
			Font hintphnFont = new Font("Verdana", Font.BOLD, 14);
			hintPhnNum.setFont(hintphnFont);
			
			gbc.gridy = 1;
			RightPanelCenter.add(hintPhnNum, gbc);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
     // phone number label and field
        gbc.gridy = 2;
        RightPanelCenter.add(new JLabel("Enter Phone Number:"), gbc);
        gbc.gridy = 3;
        phoneField = new JTextField(15);
        RightPanelCenter.add(phoneField, gbc);

     // continue  button
        gbc.gridy = 4;
        gbc.insets = new Insets(20, 0, 0, 0);
        continueButton = new JButton("continue");
		continueButton.setFocusable(false);
        continueButton.addActionListener(this);
        RightPanelCenter.add(continueButton, gbc);
      
	  
	   // Exit button
        exitButton = new JButton("Back");
        exitButton.addActionListener(this);
        
		RightPanelBottom = new JPanel();
        RightPanelBottom.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        RightPanelBottom.setBorder(new EmptyBorder(0, 0, 20, 20));
        RightPanelBottom.add(exitButton);
		
	   MainRightPanel.add(RightPanelCenter, BorderLayout.CENTER);
	   MainRightPanel.add(RightPanelBottom, BorderLayout.SOUTH);
		
        add(leftPanel, BorderLayout.WEST);
        add(MainRightPanel, BorderLayout.CENTER);

    this.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String user =
        "Phone : "
            + phoneField
                .getText(); // Get the value of the phoneField and format it as "Phone : {phone
                            // number}"
    String user1 = phoneField.getText(); // Get the value of the phoneField
    boolean userEmpty = user1.isEmpty(); // Check if the phoneField is empty
    boolean isFound =
        false; // Initialize a boolean variable to keep track of whether the user's phone number is
               // found

    // Check which button was clicked
    if (e.getSource() == continueButton) {
      try {
        if (userEmpty) {
          // Show a warning message if the phoneField is empty
          JOptionPane.showMessageDialog(
              null, "Enter phone number", "Error", JOptionPane.WARNING_MESSAGE);

        } else {
          // Calculate the line number to read from the file
          int n = ((ForgetPassMenu1.deleteLine) + 2);
          // Read the specified line from the user_login.txt file
          BufferedReader readFile = new BufferedReader(new FileReader("./files/user_login.txt"));
          String line = Files.readAllLines(Paths.get("./files/user_login.txt")).get(n);
          // Check if the line matches the formatted phone number
          if (line.equals(user)) {
            System.out.println("User phonenumber found");
            isFound = true;
          }

          if (!isFound) {
            // Show a warning message if the phone number is not found in the file
            System.out.println("User phonenumber not found");
            JOptionPane.showMessageDialog(
                null, "Phone number not found!", "Error", JOptionPane.WARNING_MESSAGE);
          } else {
            this.setVisible(false); // Hide the current frame and show the ForgetPass3 frame
            System.out.println("Exited from ForgetPassMenu2 class");
            new ForgetPassMenu3();
          }
          readFile.close();
        }

      } catch (
          Exception
              ex) { // Show a warning message if an exception occurs while reading from the file
        System.out.println("User phone number not found error in file");
        JOptionPane.showMessageDialog(
            null, "Phone number not found!", "Error", JOptionPane.WARNING_MESSAGE);
      }
    } else if (e.getSource() == exitButton) { // Show a confirmation dialog before exiting the program
      int yesORno =
          JOptionPane.showConfirmDialog(
              null, "Are you sure ?", "Alert!", JOptionPane.YES_NO_OPTION);

      if (yesORno == 0) {
          // If Yes then exit 
        System.out.println("Exited from ForgetPassMenu3 class");
        System.out.println("Back to Login menu");
		this.setVisible(false); 
        new LoginMenu();
      }} 
    }
  }

