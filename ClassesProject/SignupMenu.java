package ClassesProject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SignupMenu extends JFrame implements ActionListener {
	
    private JPanel leftPanel;
    private JPanel MainRightPanel;
    private JPanel RightPanelCenter;
	private JPanel RightPanelBottom;
  private final JTextField usernameField;
  private final JPasswordField passwordField;
  private final JPasswordField confirmPassField;
  private final JTextField fullnameField;
  private final JButton signup;
  private final JButton exitButton;
  private final JTextField phoneNumberField;
  
      public SignupMenu() {
        setTitle("Signup - Hotel Management System");
        setResizable(false);
		setIconImage(
        Toolkit.getDefaultToolkit().getImage(SignupMenu.class.getResource("../IconsProject/2090700.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 612);
        setLocationRelativeTo(null);

        // Left panel with background image
        leftPanel = new JPanel(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(612, 612));
        ImageIcon obj1 = new ImageIcon("IconsProject/Keys.jpg");
        JLabel SignupImgLabel = new JLabel(obj1);
		SignupImgLabel.setBounds(0, 0, leftPanel.getPreferredSize().width, leftPanel.getPreferredSize().height);
        leftPanel.add(SignupImgLabel);
		if (obj1.getImageLoadStatus() == MediaTracker.COMPLETE) {
          System.out.println(" Sign up Image loaded successfully.");
          } else {
                    System.out.println(" Sign up Image loading failed.");}

  
 
        // Right panel with SignUp components and buttons
         MainRightPanel = new JPanel(new BorderLayout());
         MainRightPanel.setPreferredSize(new Dimension(398, 612));
		
 
        RightPanelCenter = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
 
        gbc.insets = new Insets(5, 5, 5, 5);

        // Sign up label
        JLabel SignUpText = new JLabel("SIGN UP");
        SignUpText.setFont(new Font("Serif", Font.BOLD, 50));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        RightPanelCenter.add(SignUpText, gbc);

        // Full name label and field
        gbc.gridy = 1;
        RightPanelCenter.add(new JLabel("Full Name:"), gbc);
        gbc.gridy = 2;
        fullnameField = new JTextField(15);
        RightPanelCenter.add(fullnameField, gbc);

        // Username label and field
        gbc.gridy = 3;
        RightPanelCenter.add(new JLabel("User Name:"), gbc);
        gbc.gridy = 4;
        usernameField = new JTextField(15);
        RightPanelCenter.add(usernameField, gbc);

        // phone number label and field
        gbc.gridy = 5;
		RightPanelCenter.add(new JLabel("Phone Number:"), gbc);
		gbc.gridy = 6;
		phoneNumberField = new JTextField(15);
        RightPanelCenter.add(phoneNumberField, gbc);
		
        //passwordlabel and field 
		gbc.gridy = 7;
		RightPanelCenter.add(new JLabel("Password:"), gbc);
		gbc.gridy = 8;
		passwordField = new JPasswordField(15);
        RightPanelCenter.add(passwordField, gbc);

        // confirm password label and field 
		gbc.gridy = 9;
		RightPanelCenter.add(new JLabel("Confirm Password:"), gbc);
		gbc.gridy = 10;
		confirmPassField = new JPasswordField(15);
        RightPanelCenter.add(confirmPassField, gbc);
		
		
        // Sign up  button
        gbc.gridy = 11;
        gbc.insets = new Insets(20, 0, 0, 0);
        signup = new JButton("Sign up");
        signup.addActionListener(this);
        RightPanelCenter.add(signup, gbc);

       
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

        setVisible(true);
    }
	
	public static boolean isValidFullname(String fullname) {
    System.out.println("isValidFullname function called");
    String pattern = "^(?!.*\\d)(?!.*[^a-zA-Z0-9 .'-])(?!.*[ .'-]{2,})[a-zA-Z0-9 .'-]+$";
    System.out.println("isValidFullname function executed successfully");
    return fullname.matches(pattern);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // Get user input
    String user = usernameField.getText(); // Get username
    String pass = String.valueOf(passwordField.getPassword()); // Get password
    String confpass = String.valueOf(confirmPassField.getPassword()); // Get confirmed password
    String name = fullnameField.getText(); // Get full name
    String PhoneNumber = phoneNumberField.getText(); // Get phone number

    // Check if fields are empty
    boolean userEmpty = user.isEmpty(); // Check if username is empty
    boolean passEmpty = pass.isEmpty(); // Check if password is empty
    boolean confEmpty = confpass.isEmpty(); // Check if confirmed password is empty
    boolean nameEmpty = name.isEmpty(); // Check if full name is empty
    boolean emailEmpty = PhoneNumber.isEmpty(); // Check if phone number is empty

    // Check if password matches confirmed password
    boolean check = pass.equals(confpass);
    int numcount = 0;

    try {
      // Check phone number length
      if (PhoneNumber.length() != 11) {
        numcount++; // Increment numcount if phone number length is not equal to 11
      }
    } catch (Exception ex) {
      numcount = 1; // Set numcount to 1 if an exception occurs
    }

    if (e.getSource() == signup) {
      if (userEmpty || passEmpty || confEmpty || nameEmpty || emailEmpty) {
        // Display an error message if any field is empty
        JOptionPane.showMessageDialog(
            null, "Please fill all of the fields.", "Error!", JOptionPane.WARNING_MESSAGE);
      } else if (!isValidFullname(name)) {
        // Display an error message for invalid Fullname
        JOptionPane.showMessageDialog(
            null,
            "Invalid fullname. Please enter a valid fullname.",
            "Error!",
            JOptionPane.WARNING_MESSAGE);
      } else if (!validateUsername(user)) {
        // Display an error message for invalid username
        JOptionPane.showMessageDialog(
            null,
            "Invalid username. Please enter a valid username.",
            "Error!",
            JOptionPane.WARNING_MESSAGE);
      } else if (numcount > 0) {
        // Display an error message for invalid phone number
        JOptionPane.showMessageDialog(
            null, "Invalid Phone Number", " Error!", JOptionPane.WARNING_MESSAGE);
      } else if (!check) {
        // Display an error message if password doesn't match
        JOptionPane.showMessageDialog(
            null, "Password is not matching", " Error!", JOptionPane.WARNING_MESSAGE);
      } else {
        try {
          File file = new File("./files/user_login.txt");
          if (!file.exists()) {
            // Create a new file if it doesn't exist
            boolean created = file.createNewFile();
            if (created) {
              System.out.println("User Login File created successfully.");
            } else {
              System.out.println("User Login File creation failed.");
            }
          }
          FileWriter fileWriter = new FileWriter(file, true);
          BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
          PrintWriter printWriter = new PrintWriter(bufferedWriter);

          LocalDateTime myDateObj = LocalDateTime.now();
          DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm a, dd/MM/yyyy");

          String timeAndDate = myDateObj.format(myFormatObj);

          // User Login file checked
          int totalLines = 0; // Check the total lines in User Login file
          BufferedReader readFile = new BufferedReader(new FileReader("./files/user_login.txt"));
          while (readFile.readLine() != null) {
            totalLines++;
          }
          readFile.close();

          // Admin login file checked
          BufferedReader adminFile = new BufferedReader(new FileReader("./files/admin_login.txt"));
          int totalLines2 = 0; // Check the total lines in Admin Login file
          while (adminFile.readLine() != null) {
            totalLines2++;
          }
          adminFile.close();

          boolean userflag = false;
          boolean adminflag = false;

          // for user
          for (int i = 0; i < totalLines; i++) { // Check if the username already exists in User Login file
            String line = Files.readAllLines(Paths.get("./files/user_login.txt")).get(i);
            if (line.equals("User Name : " + user)) {
              userflag = true;
              System.out.println("User name exists");
              break;
            }
          }

          // for admin
          for (int i = 0; i < totalLines2; i++) { // Check if the username already exists in Admin Login file
            String line = Files.readAllLines(Paths.get("./files/admin_login.txt")).get(i);
            if (line.equals("User Name : " + user)) {
              System.out.println("User name exists as Admin");
              adminflag = true;
              break;
            }
          }
          if (!userflag && !adminflag) {
            // If username is not taken, add user information to the file
            printWriter.println("===============================================");
            printWriter.println("Full Name : " + name);
            printWriter.println("User Name : " + user);
            printWriter.println("Password : " + pass);
            printWriter.println("Phone : " + PhoneNumber);
            printWriter.println("Time & Date : " + timeAndDate);
            printWriter.println("===============================================");

            System.out.println("New User details added");

            this.setVisible(false);
            System.out.println("Exited from Signup class, back to the login menu");
            LoginMenu loginMenu = new LoginMenu();
            loginMenu.setVisible(true);
          } else {
            // Display a warning message if username is already taken
            JOptionPane.showMessageDialog(
                null, "User name already taken", "Warning", JOptionPane.WARNING_MESSAGE);
          }

          printWriter.close();

        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }

    } else if (e.getSource() == exitButton) {
      int yesORno =
          JOptionPane.showConfirmDialog(
              null, "Are you sure ?", "Alert!", JOptionPane.YES_NO_OPTION);

      if (yesORno == 0) {
	   this.setVisible(false);
       System.out.println("Exited from Signup class, back to the login menu"); 
       // Open the login window
      LoginMenu loginMenu = new LoginMenu();
      System.out.println("LoginMenu instance created");

       // Print the state of the new instance
      System.out.println("LoginMenu isVisible: " + loginMenu.isVisible());

        loginMenu.setVisible(true);
        System.out.println("LoginMenu setVisible(true) called");
      }
    }
  }

  public boolean validateUsername(String username) {
    System.out.println("validateUsername function called");
    // Check for spaces
    if (username.contains(" ")) {
      return false;
    }

    // Check for symbols and allowed formats using regular expression
    if (!username.matches("^[a-zA-Z0-9]+$")
        && !username.matches("^[a-zA-Z]+$")
        && !username.matches("^[a-zA-Z]+[0-9]+$")) {
      return false;
    }

    // Check length
    int length = username.length();
    System.out.println("validateUsername function executed successfully");
    return length >= 3 && length <= 20;

    // Additional validation rules can be added here
}
}