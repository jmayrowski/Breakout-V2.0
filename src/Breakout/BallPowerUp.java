package Breakout;

import Breakout.Control.BallControl;

/**
 * Created by Romano on 10.07.2016.
 */
public class BallPowerUp implements PowerUp {

    private BallControl bc;

    @Override
    public void pickedUp(PowerUpType effect) {

        bc = new BallControl();

        switch (effect){

            case FASTER:
                bc.speedUpBall();
                break;
            case SLOWER:
                bc.slowDownBall();
                break;
            case MULTIBALL:
                bc.multiBall();
                break;
        }

    }
}
