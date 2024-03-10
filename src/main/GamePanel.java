package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GamePanel extends JPanel {
  private MouseInputs mouseInputs;
  private float xDelta = 100, yDelta = 100;
  private BufferedImage img;

  public GamePanel() {
    mouseInputs = new MouseInputs(this);
    try {
      importImg();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    setPanelSize();
    addKeyListener(new KeyboardInputs(this));
    addMouseListener(mouseInputs);
    addMouseMotionListener(mouseInputs);
  }

  private void importImg() throws IOException {
    InputStream is = getClass().getResourceAsStream("/player_sprites.png");

    try {
      assert is != null;
      img = ImageIO.read(is);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void setPanelSize() {
    Dimension size = new Dimension(1280, 800);
    setPreferredSize(size);
  }

  public void changeXDelta(int value) {
    this.xDelta += value;
  }

  public void changeYDelta(int value) {
    this.yDelta += value;
  }

  public void setRectPos(int x, int y) {
    this.xDelta = x;
    this.yDelta = y;
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
//    g.drawImage(img, 0, 0, null);
  }

}
