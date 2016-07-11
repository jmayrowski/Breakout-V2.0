package Breakout.control;

import Breakout.BatFactory;
import Breakout.Breakout;
import com.almasb.ents.AbstractControl;
import com.almasb.ents.Entity;
import com.almasb.fxgl.entity.component.BoundingBoxComponent;
import com.almasb.fxgl.entity.component.MainViewComponent;
import com.almasb.fxgl.entity.component.PositionComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.geometry.Point2D;

/**
 * Created by Romano on 09.07.2016.
 */
public class BatControl extends AbstractControl {

    protected PositionComponent position;
    protected PhysicsComponent batPhysics;
    protected BoundingBoxComponent bbc;
    protected MainViewComponent viewComponent;

    //protected String BatTexture;

    @Override
    public void onAdded(Entity entity) {
        batPhysics = entity.getComponentUnsafe(PhysicsComponent.class);
        position = entity.getComponentUnsafe(PositionComponent.class);
        bbc = entity.getComponentUnsafe(BoundingBoxComponent.class);
        viewComponent = entity.getComponentUnsafe(MainViewComponent.class);

    }

    @Override
    public void onUpdate(Entity entity, double tpf) {}

    public void left() {

        if(batPhysics != null){

            Point2D p = position.getValue();
            if (p.getX() < 65) {
                batPhysics.setLinearVelocity(0, 0);
            }
            else{batPhysics.setLinearVelocity(-20, 0);}
        }


    }

    public void right() {

        if(batPhysics != null) {

            Point2D p = position.getValue();
            if (p.getX() > (Breakout.ApplicationWidth - 195)) {
                batPhysics.setLinearVelocity(0, 0);
            }
            else {batPhysics.setLinearVelocity(20, 0);}

        }
    }

    public void stop() {
        batPhysics.setLinearVelocity(0, 0);
    }

    public void increaseBatWidth(){

        if(viewComponent != null  ) {

            Entity bat = new Entity();

            double batWidth = viewComponent.getView().getBoundsInLocal().getWidth();

            if(batWidth <= 135 ) {

                bbc.clearHitBoxes();
                viewComponent.getView().removeFromScene();
                BatFactory bf = new BatFactory();

                Breakout.gameWorld.removeEntities(Breakout.bat, bat);

                bat = bf.createBat( position.getX() - (135 / 3), Breakout.ApplicationHeight - 32, "bats/bat_black_big.png");
                Breakout.gameWorld.addEntities(bat);
                Breakout.batControl = bat.getControlUnsafe(BatControl.class);

            }
            else if(batWidth == 200) {


                bbc.clearHitBoxes();
                viewComponent.getView().removeFromScene();
                BatFactory bf = new BatFactory();

                Breakout.gameWorld.removeEntities(Breakout.bat, bat );

                bat = bf.createBat( position.getX() - (300 / 3), Breakout.ApplicationHeight - 32, "bats/bat_black_bigger.png");
                Breakout.gameWorld.addEntities(bat);
                Breakout.batControl = bat.getControlUnsafe(BatControl.class);

            }
            else if(batWidth == 350 ) {

                bbc.clearHitBoxes();
                viewComponent.getView().removeFromScene();
                BatFactory bf = new BatFactory();

                Breakout.gameWorld.removeEntities(Breakout.bat, bat);

                bat = bf.createBat( position.getX() - 500 / 5  , Breakout.ApplicationHeight - 32, "bats/bat_black_biggest.png");
                Breakout.gameWorld.addEntities(bat);
                Breakout.batControl = bat.getControlUnsafe(BatControl.class);

            }
        }
    }

    public void decreaseBatWidth(){

        if(viewComponent != null){

            double batWidth = viewComponent.getView().getBoundsInLocal().getWidth();

            if(batWidth >= 135 ) {

                bbc.clearHitBoxes();
                viewComponent.getView().removeFromScene();

                BatFactory bf = new BatFactory();

                Breakout.gameWorld.removeEntities(Breakout.bat);

                Entity bat = bf.createBat( position.getX() + batWidth / 2  , Breakout.ApplicationHeight - 32, "bats/bat_black_mini.png");
                Breakout.gameWorld.addEntities(bat);
                Breakout.batControl = bat.getControlUnsafe(BatControl.class);

            }
        }

    }
}