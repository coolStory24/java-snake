import java.awt.*;

public class Snake {
  public static enum Directions {
    UP,
    RIGHT,
    DOWN,
    LEFT,
    STAY
  }

  private int cordX = 100;
  private int cordY = 100;
  private int size = 25;
  private Directions direction = Directions.STAY;

  public Snake() {}

  public Snake(int cordX, int cordY, int size) {
    this.cordX = cordX;
    this.cordY = cordY;
    this.size = size;
  }

  public void draw(Graphics g) {
    g.drawRect(cordX, cordY, size, size);
  }

  Snake move() {

    switch (direction) {
      case UP -> cordY -= size;
      case RIGHT -> cordX += size;
      case DOWN -> cordY += size;
      case LEFT -> cordX -= size;
    }

    return this;
  }

  Snake changeDirection(Directions direction) {
    this.direction = direction;

    return this;
  }
}
