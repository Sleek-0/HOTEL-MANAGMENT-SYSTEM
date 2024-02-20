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

public class LoginMenu extends JFrame implements ActionListener {
    private JPanel leftPanel;
    private JPanel MainRightPanel;
    private JPanel RightPanelCenter;
	private JPanel RightPanelBottom;
	private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JButton signup;
    private final JButton exitButton;
    private final JButton loginButton;
    private final JButton forgot;
    public static String USERNAME;
    protected static boolean loginFlag;
    protected static boolean isAdmin;
    protected static String fullName;
    protected static String oldPassword;
    protected static String phoneNumber;
    protected static String fullUsername;
	
      public LoginMenu() {
        setTitle("Login - Hotel Management System");
        setResizable(false);
		setIconImage(
        Toolkit.getDefaultToolkit().getImage(LoginMenu.class.getResource("../IconsProject/2090700.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 612);
        setLocationRelativeTo(null);

        // Left panel with background image
        leftPanel = new JPanel(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(408, 612));
        ImageIcon obj = new ImageIcon("IconsProject/Loginphoto.jpg");
        JLabel loginImgLabel = new JLabel(obj);
        leftPanel.add(loginImgLabel);
		
		
        // Right panel with login components and buttons
         MainRightPanel = new JPanel(new BorderLayout());
         MainRightPanel.setPreferredSize(new Dimension(592, 612));
		 
        RightPanelCenter = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Login label
        JLabel loginLblTxt = new JLabel("LOGIN");
        loginLblTxt.setFont(new Font("Serif", Font.BOLD, 50));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        RightPanelCenter.add(loginLblTxt, gbc);

        // Username label and field
        gbc.gridy = 1;
        RightPanelCenter.add(new JLabel("Username:"), gbc);
        gbc.gridy = 2;
        usernameField = new JTextField(20);
        RightPanelCenter.add(usernameField, gbc);

        // Password label and field
        gbc.gridy = 3;
        RightPanelCenter.add(new JLabel("Password:"), gbc);
        gbc.gridy = 4;
        passwordField = new JPasswordField(20);
        RightPanelCenter.add(passwordField, gbc);

        // Login button
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        RightPanelCenter.add(loginButton, gbc);

        // Signup button
        gbc.gridy = 6;
        gbc.insets = new Insets(20, 0, 0, 0);
        signup = new JButton("Don't have an account?");
        signup.addActionListener(this);
        RightPanelCenter.add(signup, gbc);

        // Forgot password button
        gbc.gridy = 7;
        gbc.insets = new Insets(20, 0, 0, 0);
        forgot = new JButton("Forgot password?");
        forgot.addActionListener(this);
        RightPanelCenter.add(forgot, gbc);

        
        // Exit button
        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
		
		
        RightPanelBottom = new JPanel();
        RightPanelBottom.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        RightPanelBottom.setBorder(new EmptyBorder(0, 0, 20, 20));
        RightPanelBottom.add(exitButton);

	   MainRightPanel.add(RightPanelCenter, BorderLayout.CENTER);
	   MainRightPanel.add(RightPanelBottom, BorderLayout.SOUTH);
	  	
        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.WEST);
        add(MainRightPanel, BorderLayout.CENTER);

        setVisible(true);
    }

	
	@Override
    public void actionPerformed(ActionEvent e) {
		 // Get the user input from the username and password fields
    String user = usernameField.getText();
    String pass = String.valueOf(passwordField.getPassword());

    // Check if the username and password fields are empty
    boolean userEmpty = user.isEmpty();
    boolean passEmpty = pass.isEmpty();                    
		
        if (e.getSource() == exitButton) {
            int yesORno = JOptionPane.showConfirmDialog(
                    null, "Are you sure?", "Alert!", JOptionPane.YES_NO_OPTION);
            if (yesORno == 0) {
                System.exit(0);
            }
        }
         else if (e.getSource() == forgot) { // If the "forgot" button was clicked, go to the ForgotPass page
      this.setVisible(false);
      System.out.println("starting Forget Menu 1 class...");
      new ForgetPassMenu1();
        }
	     else if (e.getSource() == signup) {
      // If the "signup" button was clicked, go to the signup page
      System.out.println("User is signing up...");
      this.setVisible(false);
      new SignupMenu();
       }
         else if (e.getSource() == loginButton) {
      // If the "login" button was clicked, check if the username and password fields are filled and correct
      if (userEmpty || passEmpty) {
        JOptionPane.showMessageDialog(
            null, "Please fill all of the fields.", "Warning!", JOptionPane.WARNING_MESSAGE);
      } else {
        boolean userbool = false; // Flag to check if the user is an admin or not
        isAdmin = false; // Flag to check if the user is an admin or not
        try {
          // Check if the admin login file exists, if not create it and add default admin
          // credentials
          File file = new File("files/admin_login.txt");
          if (!file.exists()) {
            boolean created = file.createNewFile();
            if (created) {
              System.out.println("File created successfully.");
            } else {
              System.out.println("File creation failed.");
            }
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            printWriter.println("===============================================");
            printWriter.println("User Name : admin");
            printWriter.println("Password : admin");
            printWriter.println("===============================================");
            printWriter.close();
          }

          // Create strings for the username and password
          String uname = "User Name : " + user;
          String pin = "Password : " + pass;

          // Check if the user is an admin by reading the admin login file
          BufferedReader readFile1 = new BufferedReader(new FileReader("files/admin_login.txt"));

          int totalLines1 = 0;
          while (readFile1.readLine() != null) {
            totalLines1++;
          }
          readFile1.close();

          // Iterate over each line in the admin login file and check if the user credentials match
          for (int i = 0; i < totalLines1; i++) {
            Path adminLoginPath = Paths.get("./files/admin_login.txt");
            String line = Files.readAllLines(adminLoginPath).get(i);
            if (line.equals(uname)) {
              String line2 = Files.readAllLines(adminLoginPath).get((i + 1));
              System.out.println("user name matched to admin");
              // Set flags and username, and open the dashboard window
              if (line2.equals(pin)) {
                loginFlag = true;
                isAdmin = true;
                USERNAME = user;
                System.out.println("pin matched to admin user");
                this.setVisible(false);
                System.out.println("Exited from Login class");
                // Show the admin Dashboard
                new DashBoardMenu();
                break;
              } else {
                isAdmin = false;
              }
            } else {
              isAdmin = false;
            }
          }

          // Check if the user is a regular user
          if (!isAdmin) { // Check if the user is not an admin
            // Read the user_login.txt file
            File userfile = new File("./files/user_login.txt");
            if (userfile.exists()) { // Check if the file exists
              // Create a buffered reader to read the file
              BufferedReader readFile =
                  new BufferedReader(new FileReader("./files/user_login.txt"));
              int totalLines = 0;
              // Count the total number of lines in the file
              while (readFile.readLine() != null) {
                totalLines++;
              }
              readFile.close();

              // Loop through each line of the file
              for (int i = 0; i < totalLines; i++) {
                // Get the i-th line of the file
                Path userLoginPath = Paths.get("./files/user_login.txt");
                String line = Files.readAllLines(userLoginPath).get(i);
                // Check if the username matches the i-th line of the file
                if (line.equals(uname)) {
                  // Check if the password matches the (i+1)-th line of the file
                  System.out.println("User found");
                  String line2 = Files.readAllLines(userLoginPath).get((i + 1));
                  if (line2.equals(pin)) {
                    System.out.println("Password matched with user name");
                    // Set login flag, username, full name, phone number, and old password
                    loginFlag = true;
                    userbool = true;
                    USERNAME = user;
                    fullName = Files.readAllLines(userLoginPath).get(i - 1);
                    phoneNumber = Files.readAllLines(userLoginPath).get(i + 2);
                    oldPassword = Files.readAllLines(userLoginPath).get(i + 1);
                    fullUsername = uname;
                    // Hide the login frame and show the User dashboard
                    System.out.println("Exited from Login class");
                    this.setVisible(false);
                    new DashBoardMenu();
                    // Exit the loop
                    break;
                  }
                }
              }
            }
          }
          // If the login is unsuccessful, show an error message
          if (!userbool && !isAdmin) {
            JOptionPane.showMessageDialog(
                null, "Invalid Name or Password!", "Warning!", JOptionPane.WARNING_MESSAGE);
          }
          // Catch any exceptions and show an error message
        } catch (Exception ex) {
          if (!userbool && !isAdmin) {
            JOptionPane.showMessageDialog(
                null, "Invalid Name or Password!", "Warning!", JOptionPane.WARNING_MESSAGE);
          }
        }
      }
    }
	   }
	    
}

