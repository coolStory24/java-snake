import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.*;

public class GameField extends JPanel implements KeyListener, ActionListener {
  private final Snake snake;
  private final FoodManager foodManager;

  private final int CELL_SIZE;
  private final int FIELD_CELLS;

  private final Cords FIELD_OFFSET;

  public GameField(int cellSize, int fieldCells, Cords fieldOffset, FoodManager foodManager) {
    CELL_SIZE = cellSize;
    FIELD_CELLS = fieldCells;
    FIELD_OFFSET = fieldOffset;

    snake = new Snake(0, 0, FIELD_CELLS);
    setFocusable(true);
    addKeyListener(this);

    this.foodManager = foodManager;

    Timer timer = new Timer(200, this);
    timer.start();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    paintSnake(snake.getBody(), g);
    paintBorders(g);
    paintFood(g);
    paintScore(g);
  }

  public void paintSnake(List<Cords> rectangles, Graphics g) {
    g.setColor(Color.GREEN);
    for (var rectangle : rectangles) {
      g.fillOval(
          rectangle.getX() * CELL_SIZE + FIELD_OFFSET.getX(),
          rectangle.getY() * CELL_SIZE + FIELD_OFFSET.getY(),
          CELL_SIZE,
          CELL_SIZE);
    }
  }

  public void paintBorders(Graphics g) {
    g.setColor(Color.BLACK);
    g.drawRect(
        FIELD_OFFSET.getX(), FIELD_OFFSET.getY(), CELL_SIZE * FIELD_CELLS, CELL_SIZE * FIELD_CELLS);
  }

  public void paintFood(Graphics g) {
    g.setColor(Color.red);
    g.fillOval(foodManager.getCurrentFood().getX() * CELL_SIZE + FIELD_OFFSET.getX(), foodManager.getCurrentFood().getY() * CELL_SIZE + FIELD_OFFSET.getY(), CELL_SIZE, CELL_SIZE);
  }

  public void paintScore(Graphics g) {
    g.setColor(Color.black);
    g.drawString("Score: " + foodManager.getScore(), FIELD_OFFSET.getX(), FIELD_OFFSET.getY());
  }

  @Override
  public void keyTyped(KeyEvent e) {}

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case 37 -> snake.changeDirection(Directions.LEFT);
      case 38 -> snake.changeDirection(Directions.UP);
      case 39 -> snake.changeDirection(Directions.RIGHT);
      case 40 -> snake.changeDirection(Directions.DOWN);
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {}

  @Override
  public void actionPerformed(ActionEvent e) {
    snake.move(foodManager);
    repaint();
  }

  public Cords getCalculatedBorder() {
    return new Cords(
        FIELD_CELLS * CELL_SIZE + FIELD_OFFSET.getX() * 2,
        FIELD_CELLS * CELL_SIZE + FIELD_OFFSET.getY() * 2);
  }
}
