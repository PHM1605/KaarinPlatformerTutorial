package main;

import javax.swing.*;

public class GameWindow extends JFrame {
  public GameWindow(GamePanel gamePanel) {
    super();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    add(gamePanel);
    setLocationRelativeTo(null);
    setResizable(false);
    pack(); // Set size of Window to the size of its component (JPanel in this case)
    setVisible(true);
  }
}
