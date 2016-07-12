package Breakout;

import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.GameEntity;
import com.almasb.fxgl.entity.component.CollidableComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.geometry.Point2D;
import org.jbox2d.dynamics.BodyType;

/**
 * Created by Romano on 11.07.2016.
 */
public class PowerUpSpawner implements PowerUp {

    public GameEntity powerUp;

    public GameEntity getPowerUp() {
        return powerUp;
    }

    public void setPowerUp(GameEntity powerUp) {
        this.powerUp = powerUp;
    }

    public GameEntity spawnPowerUp(Point2D position, PowerUpType PUType, String PUColor ){

        PhysicsComponent powerUpPhysics;

        powerUp = Entities.builder()
                .type(PUType)
                .at(position)
                .viewFromTextureWithBBox("power-ups/star_" + PUColor + ".png")
                .build();

        powerUpPhysics = new PhysicsComponent();

        powerUpPhysics.setBodyType(BodyType.DYNAMIC);

        powerUpPhysics.setOnPhysicsInitialized(()-> powerUpPhysics.setLinearVelocity(0, 4));

        powerUp.addComponent(powerUpPhysics);
        powerUp.addComponent(new CollidableComponent(true));

        return powerUp;
    }

    public void pickedUp(PowerUp.PowerUpType effect) {

    }
}
