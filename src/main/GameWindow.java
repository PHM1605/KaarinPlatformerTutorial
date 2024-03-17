package main;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class GameWindow extends JFrame {
  public GameWindow(GamePanel gamePanel) {
    super();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    add(gamePanel);
    setLocationRelativeTo(null);
    setResizable(false);
    pack(); // Set size of Window to the size of its component (JPanel in this case)
    setVisible(true);
    addWindowFocusListener(new WindowFocusListener() {
      @Override
      public void windowGainedFocus(WindowEvent e) {

      }

      @Override
      public void windowLostFocus(WindowEvent e) {
        gamePanel.getGame().windowFocusLost();
      }
    });
  }
}
