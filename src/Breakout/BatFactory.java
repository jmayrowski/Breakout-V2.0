package Breakout;

import Breakout.control.BatControl;
import com.almasb.ents.Entity;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.GameEntity;
import com.almasb.fxgl.entity.component.CollidableComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import org.jbox2d.dynamics.BodyType;

/**
 * This project is created by Romano Waschewski and Jasmin Mayrowski
 * As mandatory libraries we use FXGL v. 0.2.4 and antlr v.4.5.3
 */
public class BatFactory {

    private GameEntity bat;

    public GameEntity getBat() {
        return bat;
    }

    public void setBat(GameEntity bat) {
        this.bat = bat;
    }

    public static PhysicsComponent batPhysics;

    public Entity createBat(double x, double y, String batTexture){
        //BoundingShape Box = box(135,30);
        //Entity erstellen
        bat = new GameEntity();
        bat = Entities.builder()
                .type(Breakout.Type.BAT)
                .at(x,y)
                .rotate(0)
                //.bbox(new HitBox("BatHitBox", Box))
                .viewFromTextureWithBBox(batTexture)
                .build();

        batPhysics = new PhysicsComponent();
        batPhysics.setBodyType(BodyType.KINEMATIC);

        bat.addComponent(batPhysics);
        bat.addComponent(new CollidableComponent(true));
        bat.addControl(new BatControl());

        return bat;
    }
}
