package Breakout.control;

import Breakout.BallFactory;
import Breakout.Breakout;
import com.almasb.ents.AbstractControl;
import com.almasb.ents.Entity;
import com.almasb.fxgl.entity.component.PositionComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.geometry.Point2D;

/**
 * Created by Romano on 09.07.2016.
 */
public class BallControl extends AbstractControl {

    public static PositionComponent position;
    public static PhysicsComponent ball;

    @Override
    public void onAdded(Entity entity) {

        ball = entity.getComponentUnsafe(PhysicsComponent.class);
        position = entity.getComponentUnsafe(PositionComponent.class);
    }

    @Override
    public void onUpdate(Entity entity, double tpf) {

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

        if(ball != null){
            Point2D v = ball.getLinearVelocity();
            if (Math.abs(v.getX()) <= 15 || Math.abs(v.getY()) <= 15 ){
                ball.setLinearVelocity(Math.signum(v.getX()) * 15, Math.signum(v.getY()) * 15);
            }

        }

    }

    public void slowDownBall(){

        if(ball != null){
            Point2D v = ball.getLinearVelocity();
            if (Math.abs(v.getX()) >= 3 || Math.abs(v.getY()) >= 3 ){
                ball.setLinearVelocity(Math.signum(v.getX()) * 3, Math.signum(v.getY()) * 3);
            }

        }
    }

    public void multiBall(){

        if(ball != null){

            Point2D p = position.getValue();
            BallFactory bf = new BallFactory();
            Breakout.gameWorld.addEntities(bf.createMultiball(p, 1, "green"));

        }


    }
}
