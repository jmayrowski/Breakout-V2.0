package Breakout;

import Breakout.Control.BatControl;

/**
 * Created by Romano on 10.07.2016.
 */
public class BatPowerUp implements PowerUp{

    private BatControl bc;

    public void pickedUp(PowerUpType effect) {

        bc = Breakout.batControl;

        switch (effect){

            case FASTER:
                break;
            case SLOWER:
                break;
            case BIGGER:
                bc.increaseBatWidth();
                break;

            case SMALLER:
                bc.decreaseBatWidth();
                break;
        }
    }
}
