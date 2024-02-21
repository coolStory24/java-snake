import java.util.Random;

public class FoodManager {
  private final Random random = new Random();
  private int score = 0;
  private final int FIELD_SIZE;
  private Cords currentFood;

  public FoodManager(int fieldSize) {
    FIELD_SIZE = fieldSize;
    generateFood();
  }

  private void generateFood() {
    this.currentFood = new Cords(random.nextInt(FIELD_SIZE), random.nextInt(FIELD_SIZE));
  }

  public boolean eat(Cords cord) {
    if (cord.equals(currentFood)) {
      score++;
      generateFood();
      return true;
    } else {
      return false;
    }
  }

  public int getScore() {
    return score;
  }

  public Cords getCurrentFood() {
    return currentFood;
  }
}
