package Breakout;

import Breakout.Control.BallControl;
import com.almasb.ents.Entity;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.GameEntity;
import com.almasb.fxgl.entity.component.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import static com.almasb.fxgl.physics.BoundingShape.circle;

/**
 * Created by Romano on 09.07.2016.
 */
public class BallFactory {

    public GameEntity ball;

    public GameEntity getBall() {
        return ball;
    }



    public static PhysicsComponent ballPhysics;



    public Entity createBall(double x, double y){


        BoundingShape Ball = circle(13.5);


        ball = Entities.builder()
                .type(Breakout.Type.BALL)
                .at(x,y)
                .bbox(new HitBox("BallHitBox", Ball))
                .viewFromTexture("Balls/ball_red.png")
                .build();

        ballPhysics = new PhysicsComponent();
        ballPhysics.setBodyType(BodyType.DYNAMIC);

        FixtureDef fd = new FixtureDef();
        fd.setDensity(0.3f);
        fd.setRestitution(1.1f);
        fd.setShape(new CircleShape());
        fd.getShape().setRadius((float) 13.5);
        ballPhysics.setFixtureDef(fd);
        ballPhysics.setOnPhysicsInitialized(() -> ballPhysics.setLinearVelocity(0,2));

        ball.addComponent(ballPhysics);
        ball.addComponent(new CollidableComponent(true));

        ball.addControl(new BallControl());

        return ball;
    }

}


