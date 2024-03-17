package main;

import entities.Player;

import java.awt.*;

public class Game implements Runnable {
  private GameWindow gameWindow;
  private GamePanel gamePanel;
  private Thread gameThread;
  private final int FPS_SET = 120;
  private final int UPS_SET = 200;
  public final static int TILES_DEFAULT_SIZE = 32;
  public final static float SCALE = 1.5f;
  public final static int TILES_IN_WIDTH = 26;
  public final static int TILES_IN_HEIGHT = 14;
  public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
  public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
  public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

  Player player;


  public Game() {
    initClasses();
    gamePanel = new GamePanel(this);
    gameWindow = new GameWindow(gamePanel);
    gamePanel.requestFocus();

    startGameLoop();
  }

  private void initClasses() {
    player = new Player(200, 200);
  }

  private void startGameLoop(){
    gameThread = new Thread(this);
    gameThread.start();
  }

  public void update() {
    player.update();
  }

  public void render(Graphics g) {
    player.render(g);
  }

  @Override
  public void run() {
    double timePerFrame = 1_000_000_000 /FPS_SET;
    double timePerUpdate = 1_000_000_000 / UPS_SET;

    long previousTime = System.nanoTime();
    int frames = 0;
    int updates = 0;

    // for counting FPS
    long lastCheck = System.currentTimeMillis();

    double deltaF = 0; // how many frames (e.g. 0.1 frames) we should render until current time? -> enough 1.0 or more then rendering 1 frame
    double deltaU = 0; // how many updates (e.g. 0.2 updates) we should have until current time? -> enough 1.0 or more then updating 1 update

    while(true) {
      long currentTime = System.nanoTime();
      deltaU += (currentTime - previousTime) / timePerUpdate;
      deltaF += (currentTime - previousTime) / timePerFrame;
      previousTime = currentTime;

      // at this time we should have 1 update
      if (deltaU >= 1) {
        update();
        updates++;
        deltaU--;
      }

      // at this time we should render 1 frame
      if (deltaF >= 1) {
        gamePanel.repaint();
        frames++;
        deltaF--;
      }

      // Check fps
      if(System.currentTimeMillis() - lastCheck >= 1000) {
        lastCheck = System.currentTimeMillis();
        System.out.println(("FPS: " + frames + " | UPS: " + updates));
        frames = 0;
        updates = 0;
      }
    }
  }

  public void windowFocusLost() {
    player.resetDirBooleans();
  }

  public Player getPlayer() {
    return player;
  }
}
