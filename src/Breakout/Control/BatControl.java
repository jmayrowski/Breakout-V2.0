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
 * This project is created by Romano Waschewski and Jasmin Mayrowski
 * As mandatory libraries we use FXGL v. 0.2.4 and antlr v.4.5.3
 */

///Die Klasse stellt Steuerungselemente für das Spielerpaddel bereit
public class BatControl extends AbstractControl {

    private PositionComponent position;
    private   PhysicsComponent batPhysics;
    private BoundingBoxComponent bbc;
    private MainViewComponent viewComponent;

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
        //Geschwindigkeitsänderungen bei Spielereingaben und Wandbegrenzungen
        if(batPhysics != null){
            Point2D p = position.getValue();
            if (p.getX() < 65) {
                batPhysics.setLinearVelocity(0, 0);
            }
            else{batPhysics.setLinearVelocity(-20, 0);}
        }
    }

    public void right() {
        //Geschwindigkeitsänderungen bei Spielereingaben und Wandbegrenzungen
        if(batPhysics != null) {
            double batWidth = viewComponent.getView().getBoundsInLocal().getWidth();
            Point2D p = position.getValue();
            if (p.getX() > (Breakout.ApplicationWidth - (batWidth + 60 ))) {
                batPhysics.setLinearVelocity(0, 0);
            }
            else {batPhysics.setLinearVelocity(20, 0);}
        }
    }

    public void stop() {
        batPhysics.setLinearVelocity(0, 0);
    }

    public void increaseBatWidth(){
        //Paddel vergrößern nach BIGGER Powerup
        if(viewComponent != null  ) {
            Entity bat;
            double batWidth = viewComponent.getView().getBoundsInLocal().getWidth();

            if(batWidth <= 135 ) {
                bbc.clearHitBoxes();
                viewComponent.getView().removeFromScene();
                BatFactory bf = new BatFactory();
                Breakout.gameWorld.getEntitiesByType(Breakout.Type.BAT).forEach(Entity::removeFromWorld);
                bat = bf.createBat( position.getX() - (135 / 3), Breakout.ApplicationHeight - 32, "bats/bat_black_big.png");
                Breakout.gameWorld.addEntities(bat);
                Breakout.batControl = bat.getControlUnsafe(BatControl.class);
            }
            else if(batWidth == 200) {
                bbc.clearHitBoxes();
                viewComponent.getView().removeFromScene();
                BatFactory bf = new BatFactory();
                Breakout.gameWorld.getEntitiesByType(Breakout.Type.BAT).forEach(Entity::removeFromWorld);
                bat = bf.createBat( position.getX() - (300 / 3), Breakout.ApplicationHeight - 32, "bats/bat_black_bigger.png");
                Breakout.gameWorld.addEntities(bat);
                Breakout.batControl = bat.getControlUnsafe(BatControl.class);
            }
            else if(batWidth == 350 ) {
                bbc.clearHitBoxes();
                viewComponent.getView().removeFromScene();
                BatFactory bf = new BatFactory();
                Breakout.gameWorld.getEntitiesByType(Breakout.Type.BAT).forEach(Entity::removeFromWorld);
                bat = bf.createBat( position.getX() - 500 / 5  , Breakout.ApplicationHeight - 32, "bats/bat_black_biggest.png");
                Breakout.gameWorld.addEntities(bat);
                Breakout.batControl = bat.getControlUnsafe(BatControl.class);
            }
        }
    }

    public void decreaseBatWidth(){
        //Paddel verkleinern nach SMALLER Powerup
        if(viewComponent != null){

            double batWidth = viewComponent.getView().getBoundsInLocal().getWidth();

            if(batWidth >= 135 ) {
                bbc.clearHitBoxes();
                viewComponent.getView().removeFromScene();
                BatFactory bf = new BatFactory();
                Breakout.gameWorld.getEntitiesByType(Breakout.Type.BAT).forEach(Entity::removeFromWorld);
                Entity bat = bf.createBat( position.getX() + batWidth / 2  , Breakout.ApplicationHeight - 32, "bats/bat_black_mini.png");
                Breakout.gameWorld.addEntities(bat);
                Breakout.batControl = bat.getControlUnsafe(BatControl.class);
            }
        }
    }
    public void normalizeBatWidth(){
        //Paddelgröße auf normal zurücksetzen nachdem Ball verloren wurde
        if(viewComponent != null){

            double batWidth = viewComponent.getView().getBoundsInLocal().getWidth();
            Point2D p = position.getValue();

            if(batWidth > 135 || batWidth <= 135 ) {
                bbc.clearHitBoxes();
                viewComponent.getView().removeFromScene();
                BatFactory bf = new BatFactory();
                Breakout.gameWorld.getEntitiesByType(Breakout.Type.BAT).forEach(Entity::removeFromWorld);
                Entity bat = bf.createBat( p.getX(), p.getY(), "bats/bat_black.png");
                Breakout.gameWorld.addEntities(bat);
                Breakout.batControl = bat.getControlUnsafe(BatControl.class);
            }
        }
    }
}