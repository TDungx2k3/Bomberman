package entities.enermies.AI;

import java.util.Random;

public abstract class AI {
    protected Random random = new Random();
  //Xuống - 0, phải - 1, trái - 2, lên - 3

    public abstract int calculateDirection();
}
