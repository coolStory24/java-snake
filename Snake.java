import java.util.*;

public class Snake {

  private Deque<Cords> body;

  private int FIELD_SIZE;

  private Directions direction = Directions.STAY;

  public Snake() {}

  public Snake(int cordX, int cordY, int fieldSize) {
    FIELD_SIZE = fieldSize;
    this.body = new ArrayDeque<>();
    body.add(new Cords(cordX, cordY));
  }

  private Cords getHead() {
    return body.getFirst();
  }

  synchronized void move(FoodManager foodManager) {
    var nextHead = getHead().getMovedCopy(direction, FIELD_SIZE);
    if (body.contains(nextHead) && direction != Directions.STAY) {
      System.exit(0);
    }

    body.addFirst(nextHead);
    if (!foodManager.eat(nextHead)) {
      body.pollLast();
    }
  }

  void changeDirection(Directions direction) {
    if (this.direction == Directions.UP && direction == Directions.DOWN
        || this.direction == Directions.DOWN && direction == Directions.UP
        || this.direction == Directions.LEFT && direction == Directions.RIGHT
        || this.direction == Directions.RIGHT && direction == Directions.LEFT) return;

    this.direction = direction;
  }

  List<Cords> getBody() {
    return body.stream().toList();
  }
}
