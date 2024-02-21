import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
  public GameFrame() throws HeadlessException {
    var foodManager = new FoodManager(30);
    var gameField = new GameField(20, 30, new Cords(200, 200), foodManager);
    add(gameField);
    setBounds(200, 200, gameField.getCalculatedBorder().getX(), gameField.getCalculatedBorder().getY());
    setVisible(true);
  }
}
