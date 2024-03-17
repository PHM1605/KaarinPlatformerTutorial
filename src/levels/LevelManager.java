package levels;

import main.Game;
import utils.LoadSave;

import java.awt.image.BufferedImage;

public class LevelManager {
  private Game game;
  private BufferedImage levelSprite;

  public LevelManager(Game game) {
    this.game = game;
    levelSprite = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
  }
}
