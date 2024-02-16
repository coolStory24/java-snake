import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class GameField extends JPanel implements KeyListener, ActionListener {
  private Timer timer;
  private Snake snake;

  public GameField() {

    snake = new Snake();
    setFocusable(true);
    addKeyListener(this);

    timer = new Timer(200, this);
    timer.start();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    snake.draw(g);
  }

  @Override
  public void keyTyped(KeyEvent e) {}

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case 37 -> snake.changeDirection(Snake.Directions.LEFT);
      case 38 -> snake.changeDirection(Snake.Directions.UP);
      case 39 -> snake.changeDirection(Snake.Directions.RIGHT);
      case 40 -> snake.changeDirection(Snake.Directions.DOWN);
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {}

  @Override
  public void actionPerformed(ActionEvent e) {
    snake.move();
    repaint();
  }
}
