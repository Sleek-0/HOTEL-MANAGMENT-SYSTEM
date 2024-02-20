import ClassesProject.StartMenu;

import javax.swing.SwingUtilities;

public class MainProject {
  public static void main(String[] args) {

          SwingUtilities.invokeLater(() -> {
            new StartMenu();
        });

  }
}
