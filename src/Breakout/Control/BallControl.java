package Breakout.control;

import Breakout.BallFactory;
import Breakout.Breakout;
import com.almasb.ents.AbstractControl;
import com.almasb.ents.Entity;
import com.almasb.fxgl.entity.component.PositionComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.geometry.Point2D;

/**
 * This Projekt is created by Romano Waschewski and Jasmin Mayrowski
 * As mandatory libraries we use FXGL v. 0.2.4 and antlr v.4.5.3
 */

// Diese Klasse stellt Steuerungseinheiten für den Ball bereit
public class BallControl extends AbstractControl {

    private static PositionComponent position;
    private static PhysicsComponent ball;

    @Override
    public void onAdded(Entity entity) {

        ball = entity.getComponentUnsafe(PhysicsComponent.class);
        position = entity.getComponentUnsafe(PositionComponent.class);
    }

    @Override
    public void onUpdate(Entity entity, double tpf) {

        //Ball Geschwindigkeit runter bzw hochregeln, wenn außerhalb der Grenzen
        Point2D v = ball.getLinearVelocity();
        if (Math.abs(v.getY()) < 3 && Math.abs(v.getX()) < 3 ){
            ball.setLinearVelocity(Math.signum(v.getX()) * 3, Math.signum( v.getY()) * 3);
        }

        if (Math.abs(v.getX()) > 15 ){
            ball.setLinearVelocity(Math.signum(v.getX()) * 15,  v.getY());
        }
        if (Math.abs(v.getY()) > 15 ){
            ball.setLinearVelocity(v.getX() , Math.signum(v.getY()) * 15);
        }
    }

    public void speedUpBall(){
        //Ball beschleunigen nach FASTER Powerup
        if(ball != null){
            Point2D v = ball.getLinearVelocity();
            if (Math.abs(v.getX()) <= 15 || Math.abs(v.getY()) <= 15 ){
                ball.setLinearVelocity(Math.signum(v.getX()) * 15, Math.signum(v.getY()) * 15);
            }
        }
    }

    public void slowDownBall(){
        //Ball verlangsamen nach SLOWER Powerup
        if(ball != null){
            Point2D v = ball.getLinearVelocity();
            if (Math.abs(v.getX()) >= 3 || Math.abs(v.getY()) >= 3 ){
                ball.setLinearVelocity(Math.signum(v.getX()) * 3, Math.signum(v.getY()) * 3);
            }
        }
    }

    public void multiBall(){
        //Extraball nach MULTIBALL Powerup
        if(ball != null){
            Point2D p = position.getValue();
            BallFactory bf = new BallFactory();
            Breakout.gameWorld.addEntities(bf.createMultiball(p, 1, "green"));
        }
    }
}
