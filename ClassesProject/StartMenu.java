package ClassesProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu extends JFrame implements ActionListener {

  private final JButton exitButton;
  private final JButton startButton;

 public StartMenu() {
  System.out.println("Program started, waiting for user to initiate the program or end....");
    setResizable(false);
    setTitle("Hotel Management System");
    setIconImage(
        Toolkit.getDefaultToolkit().getImage(StartMenu.class.getResource("../IconsProject/2090700.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(500,500);
    setLocation(200,200);
    JPanel contentPane = new JPanel();
    contentPane.setForeground(Color.LIGHT_GRAY);
    contentPane.setBackground(Color.DARK_GRAY);
    contentPane.setBorder(null);

    setContentPane(contentPane);
    contentPane.setLayout(null);


	JLabel StartMenuTitle = new JLabel("Hotel Managment Project");
    StartMenuTitle.setForeground(Color.GRAY);

    StartMenuTitle.setFont(new Font("Serif", Font.BOLD, 40));
    StartMenuTitle.setSize(500, 50);
    setLocationRelativeTo(contentPane);
	StartMenuTitle.setLocation(20,50);
  
    contentPane.add(StartMenuTitle);

    startButton = new JButton("Start");
	setLocationRelativeTo(contentPane);
    startButton.setBounds(168, 200, 153, 40);
    startButton.setFocusable(false);
    contentPane.add(startButton);

    exitButton = new JButton("Exit");
	setLocationRelativeTo(contentPane);
    exitButton.setBounds(168, 300, 150, 40);
    exitButton.setFocusable(false);
    contentPane.add(exitButton);
	
	

   
    startButton.addActionListener(this);
    exitButton.addActionListener(this);

    this.setVisible(true);
 }
	public void actionPerformed (ActionEvent e) {
	if (e.getSource() == startButton) {
      // If the "startButton" button was clicked, initiate the program
      System.out.println("starting Program..");
      this.setVisible(false);
      new LoginMenu();
    } else if (e.getSource() == exitButton) {
      // If the "exit" button was clicked, prompt the user to confirm if they want to exit and exit
      int yesORno =
          JOptionPane.showConfirmDialog(
              null, "Are you sure ?", "Alert!", JOptionPane.YES_NO_OPTION);

      if (yesORno == 0) {
        System.out.println("Exited from start menu.");
        System.out.println("Exited from The program.");
        System.exit(1); // If the user chooses "yes", exit the program
                        }
}}}
	