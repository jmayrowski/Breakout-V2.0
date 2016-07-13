package Breakout;

/**
 * This project is created by Romano Waschewski and Jasmin Mayrowski
 * As mandatory libraries we use FXGL v. 0.2.4 and antlr v.4.5.3
 */
public interface PowerUp {

    enum PowerUpType{FASTER, SLOWER, BIGGER, SMALLER, MULTIBALL}

    void pickedUp(PowerUpType effect);
}
