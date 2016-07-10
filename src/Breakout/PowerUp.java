package Breakout;

/**
 * Created by Romano on 10.07.2016.
 */
public interface PowerUp {


    enum PowerUpType{

        FASTER, SLOWER, BIGGER, SMALLER, MULTIBALL

    }

    void pickedUp(PowerUpType effect);
}
