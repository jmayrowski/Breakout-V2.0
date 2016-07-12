package Breakout;

import Breakout.control.BallControl;
import com.almasb.ents.Entity;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.GameEntity;
import com.almasb.fxgl.entity.component.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.geometry.Point2D;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import static com.almasb.fxgl.physics.BoundingShape.circle;

/**
 * Created by Romano on 09.07.2016.
 */
public class BallFactory {

    public static GameEntity ball;

    public GameEntity getBall() {
        return ball;
    }

    public void setBall(GameEntity ball) {
        this.ball = ball;
    }


    public GameEntity multiball;

    public GameEntity getMultiball() {
        return multiball;
    }

    public void setMultiball(GameEntity multiball) {
        this.multiball = multiball;
    }



    public Entity createBall(double x, double y, String ballColor){

        PhysicsComponent ballPhysics;

        BoundingShape Ball = circle(13.5);


        ball = Entities.builder()
                .type(Breakout.Type.BALL)
                .at(x,y)
                .bbox(new HitBox("BallHitBox", Ball))
                .viewFromTexture("balls/ball_" + ballColor +".png")
                .build();

        ballPhysics = new PhysicsComponent();
        ballPhysics.setBodyType(BodyType.DYNAMIC);

        FixtureDef fd = new FixtureDef();
        fd.setDensity(0.3f);
        fd.setRestitution(1.07f);
        fd.setShape(new CircleShape());
        fd.getShape().setRadius((float) 13.5);
        ballPhysics.setFixtureDef(fd);
        ballPhysics.setOnPhysicsInitialized(()->ballPhysics.setLinearVelocity(0,4));


        ball.addComponent(ballPhysics);
        ball.addComponent(new CollidableComponent(true));

        ball.addControl(new BallControl());

        return ball;
    }

    public Entity createMultiball(Point2D position, double number, String ballColor){

        PhysicsComponent ballPhysics;

        BoundingShape Ball = circle(13.5);


        multiball = Entities.builder()
                .type(Breakout.Type.MULTIBALL)
                .at(position.getX(),position.getY())
                .bbox(new HitBox("BallHitBox", Ball))
                .viewFromTexture("balls/ball_" + ballColor +".png")
                .build();

        ballPhysics = new PhysicsComponent();
        ballPhysics.setBodyType(BodyType.DYNAMIC);


        FixtureDef fd = new FixtureDef();
        fd.setDensity(0.3f);
        fd.setRestitution(1.1f);
        fd.setShape(new CircleShape());
        fd.getShape().setRadius((float) 13.5);

        ballPhysics.setFixtureDef(fd);

        ballPhysics.setOnPhysicsInitialized(() -> ballPhysics.setLinearVelocity(0,-4));

        multiball.addComponent(ballPhysics);
        multiball.addComponent(new CollidableComponent(true));

        multiball.addControl(new BallControl());

        return multiball;
    }

}


