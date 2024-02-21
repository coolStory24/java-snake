import java.util.Objects;

public class Cords {
  private int x;
  private int y;

  public Cords(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  synchronized public Cords getMovedCopy(Directions direction, int mod) {
    var copy = new Cords(x, y);

    switch (direction) {
      case UP -> copy.y--;
      case RIGHT -> copy.x++;
      case DOWN -> copy.y++;
      case LEFT -> copy.x--;
    }

    copy.x = (copy.x + mod) % mod;
    copy.y = (copy.y + mod) % mod;

    return copy;
  }

  @Override
  public String toString() {
    return "(" + x + ", " + y + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Cords cords = (Cords) o;
    return x == cords.x && y == cords.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}