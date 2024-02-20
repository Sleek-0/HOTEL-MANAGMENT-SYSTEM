package ClassesProject;

import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.*;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForgetPassMenu3 extends JFrame implements ActionListener {
	
    private JPanel leftPanel;
     private JPanel MainRightPanel;
    private JPanel RightPanelCenter;
	private JPanel RightPanelBottom;
  private final JPasswordField newPass;
  private final JPasswordField confirmPass;
  private final JButton exitButton;
  private final JButton continueButton;

  public ForgetPassMenu3() {
    System.out.println("Currently in ForgetPassMenu3 class");
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
          System.out.println(" Forget pass menu 3 Image loaded successfully.");
          } else {
                    System.out.println("Forget pass menu 3 Image loading failed.");}  	
		
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
		
		
     // password label and field 
		gbc.gridy = 1;
		RightPanelCenter.add(new JLabel("Password:"), gbc);
		gbc.gridy = 2;
		newPass = new JPasswordField(15);
        RightPanelCenter.add(newPass, gbc);

     // confirm password label and field 
		gbc.gridy = 3;
		RightPanelCenter.add(new JLabel("Confirm Password:"), gbc);
		gbc.gridy = 4;
		confirmPass = new JPasswordField(15);
        RightPanelCenter.add(confirmPass, gbc);
   
     // continue  button
        gbc.gridy = 5;
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
    if (e.getSource() == continueButton) {
      int yesORno =
          JOptionPane.showConfirmDialog(
              null,
              "Are you sure You Want to Change Password?",
              "Alert!",
              JOptionPane.YES_NO_OPTION);

      if (yesORno == JOptionPane.YES_OPTION) {

        try {
          // Read the user_login.txt file
          File userfile = new File("./files/user_login.txt");
          if (userfile.exists()) {
            System.out.println("Reading Text From user_login.txt");
          }
          // Get the new password
          String newpass1 = String.valueOf(newPass.getPassword());
          boolean newpass3 = newpass1.isEmpty();

          // Get the confirmed password
          String confpass1 = String.valueOf(confirmPass.getPassword());
          String confpass2 = "Password : " + String.valueOf(confirmPass.getPassword());
          boolean confpass3 = confpass1.isEmpty();
          boolean check = newpass1.equals(confpass1);

          // Check for empty passwords
          if (newpass3 || confpass3) {
            JOptionPane.showMessageDialog(
                null, "Enter password", "Error", JOptionPane.WARNING_MESSAGE);
          } else if (!check) {
            // Check if passwords match
            JOptionPane.showMessageDialog(
                null, "Password not matching", "Error", JOptionPane.WARNING_MESSAGE);
          } else {
            String tempfile = "./files/temp.txt";
            File oldFile = new File("./files/user_login.txt");
            File newFile = new File("./files/temp.txt");
            System.out.println("temp file created");
            int l = 0;

            String currentline;

            // Write to the temp file
            FileWriter fileWriter = new FileWriter(tempfile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            FileReader fr = new FileReader("./files/user_login.txt");
            BufferedReader br = new BufferedReader(fr);

            int n = (ForgetPassMenu1.deleteLine) + 2;
            while ((currentline = br.readLine()) != null) {
              l++;
              if (n != l) {
                printWriter.println(currentline);
              } else {
                printWriter.println(confpass2); // Replace the line with the new password
                System.out.println("New password replace with old password");
              }
            }
            printWriter.flush();
            printWriter.close();
            fr.close();
            br.close();
            bufferedWriter.close();
            fileWriter.close();

            // Delete the old file and rename the temp file
            oldFile.delete();
            System.out.println("Original file deleted");
            File dumb = new File("./files/user_login.txt");
            newFile.renameTo(dumb);
            System.out.println("temp file rename to original file");
            // Close the current window and open the Login window
            this.setVisible(false);
            System.out.println("Exited from ForgetPassMenu3 class");
            new LoginMenu();
          }
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }

    } else if (e.getSource() == exitButton) {
      int yesORno =
          JOptionPane.showConfirmDialog(
              null, "Are you sure ?", "Alert!", JOptionPane.YES_NO_OPTION);

      if (yesORno == 0) {
          // If Yes then exit 
        System.out.println("Exited from ForgetPassMenu3 class");
        System.out.println("Back to Login menu");
		this.setVisible(false); 
        new LoginMenu();
      }
    }
  }
}
