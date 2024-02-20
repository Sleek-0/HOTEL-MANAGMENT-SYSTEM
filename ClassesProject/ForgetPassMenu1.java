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

public class ForgetPassMenu1 extends JFrame implements ActionListener {


    private JPanel leftPanel;
    private JPanel MainRightPanel;
    private JPanel RightPanelCenter;
	private JPanel RightPanelBottom;
  protected static int deleteLine;
  private final JTextField userField;
  private final JButton exitButton;
  private final JButton continueButton;

  public ForgetPassMenu1() {
    System.out.println("Currently in ForgetPassMenu1 class");
    setTitle("Forgot Password - Hotel Management System");
        setResizable(false);
		setIconImage(
        Toolkit.getDefaultToolkit().getImage(ForgetPassMenu1.class.getResource("../IconsProject/2090700.png")));
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
          System.out.println(" Forget pass menu 1 Image loaded successfully.");
          } else {
                    System.out.println("Forget pass menu 1 Image loading failed.");}
					
					
  
  
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

     // Username label and field
        gbc.gridy = 1;
        RightPanelCenter.add(new JLabel("User Name:"), gbc);
        gbc.gridy = 2;
        userField = new JTextField(15);
        RightPanelCenter.add(userField, gbc);

     // continue  button
        gbc.gridy = 3;
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
    // Get the user input from a text field and check if it is empty
    String user = "User Name : " + userField.getText();
    String user1 = userField.getText();
    boolean userEmpty = user1.isEmpty();
    // Initialize some variables
    boolean isFound = false;
    int totalLines = 0;

    // Check which button was clicked
    if (e.getSource() == continueButton) {
      try {
        // Open the file containing user login information
        File userfile = new File("./files/user_login.txt");
        if (userfile.exists()) {
          // Count the total number of lines in the file
          BufferedReader readFile = new BufferedReader(new FileReader("./files/user_login.txt"));
          while (readFile.readLine() != null) {
            totalLines++;
          }
          readFile.close();
        }
        // If the user input is empty, show an error message
        if (userEmpty) {
          JOptionPane.showMessageDialog(
              null, "Enter User Name", "Error", JOptionPane.WARNING_MESSAGE);
        } else {
          // Loop through each line in the file and compare it to the user input
          for (int i = 0; i < totalLines; i++) {
            String line = Files.readAllLines(Paths.get("./files/user_login.txt")).get(i);
            if (line.equals(user)) {
              // If a match is found, set a flag variable and exit the loop
              System.out.println("User name found");
              deleteLine = i;
              isFound = true;
              break;
            }
          }
          // If a match was found, hide the current window and show the next one
          // Otherwise, show an error message
          if (isFound) {
            this.setVisible(false);
            System.out.println("Exited from ForgetPassMenu1 class");
            new ForgetPassMenu2();
          } else {
            System.out.println("User Name not found");
            JOptionPane.showMessageDialog(
                null, "Username not found", "Error", JOptionPane.WARNING_MESSAGE);
          }
        }
      } catch (Exception ex) {
        // Show an error message if there was a problem accessing the file
        System.out.println("User name not found error in file");
        JOptionPane.showMessageDialog(
            null, "Username not found", "Error", JOptionPane.WARNING_MESSAGE);
      }

    } else if (e.getSource() == exitButton) {
      // If the exit button was clicked, ask the user if they really want to exit
      int yesORno =
          JOptionPane.showConfirmDialog(
              null, "Are you sure ?", "Alert!", JOptionPane.YES_NO_OPTION);
      if (yesORno == 0) {
        // If Yes then exit 
        System.out.println("Exited from ForgetPassMenu 1 class");
        System.out.println("Back to Login menu");
		this.setVisible(false); 
        new LoginMenu();
      }
    }
  }
}
