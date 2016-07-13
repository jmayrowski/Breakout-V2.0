package Breakout;

import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.GameEntity;
import com.almasb.fxgl.entity.component.CollidableComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.geometry.Point2D;
import org.jbox2d.dynamics.BodyType;

/**
 * This project is created by Romano Waschewski and Jasmin Mayrowski
 * As mandatory libraries we use FXGL v. 0.2.4 and antlr v.4.5.3
 */
public class BrickFactory {

    private BrickFactory brickFactory;
    private String textureName;
    private GameEntity brick;
    private Breakout.Type brickType;
    private static PhysicsComponent brickPhysics;


    public BrickFactory getBrickFactory() {
        return brickFactory;
    }

    public void setBrickFactory(BrickFactory brickFactory) {
        this.brickFactory = brickFactory;
    }

    public String chooseTextureColor(int colorKey){

        switch (colorKey) {
            case 0:  textureName = "bricks/brick_blue_small.png";
                break;
            case 1:  textureName = "bricks/brick_blue_small_cracked.png";
                break;
            case 2:  textureName = "bricks/brick_green_small.png";
                break;
            case 3:  textureName = "bricks/brick_green_small_cracked.png";
                break;
            case 4:  textureName = "bricks/brick_pink_small.png";
                break;
            case 5:  textureName = "bricks/brick_pink_small_cracked.png";
                break;
            case 6:  textureName = "bricks/brick_violet_small.png";
                break;
            case 7:  textureName = "bricks/brick_violet_small_cracked.png";
                break;
            case 8:  textureName = "bricks/brick_yellow_small.png";
                break;
            case 9:  textureName = "bricks/brick_yellow_small_cracked.png";
                break;
            case 10: textureName = "bricks/brick_silver_small.png";
                break;
            case 11:  textureName = "bricks/brick_gold_small.png";
                break;
            default: textureName = "bricks/brick_blue_small.png";
                break;
        }

        return textureName;
    }

    public Breakout.Type getBrickType(int brickTypeNumber){
        //Bricktype wählen auf Basis der PlayField Eigenschaften für jeden Brick
        switch (brickTypeNumber){

            case 0:
                brickType = Breakout.Type.BRICK;
                break;
            case 1:
                brickType = Breakout.Type.BRICK_FASTER_POWERUP;
                break;
            case 2:
                brickType = Breakout.Type.BRICK_SLOWER_POWERUP;
                break;
            case 3:
                brickType = Breakout.Type.BRICK_MULTIBALL_POWERUP;
                break;
            case 4:
                brickType = Breakout.Type.BRICK_BIGGER_POWERUP;
                break;
            case 5:
                brickType = Breakout.Type.BRICK_SMALLER_POWERUP;
                break;
            default:
                brickType = Breakout.Type.BRICK;
                break;
        }
        return brickType;
    }
    public GameEntity initBrick(int position, int colorKey){

        //BoundingShape Box = BoundingShape.box(90,40);
        Point2D p = new Point2D((position % 11) * 100 + 95, (position / 11 ) * 45 + 30);

        brick = Entities.builder()
                .type(Breakout.Type.BRICK)
                .at(p.getX(),p.getY())
                //.bbox(new HitBox("BrickHitBox", Box))
                .viewFromTextureWithBBox(chooseTextureColor(colorKey))
                .build();

        brickPhysics = new PhysicsComponent();
        brickPhysics.setBodyType(BodyType.STATIC);

        brick.addComponent(brickPhysics);
        brick.addComponent(new CollidableComponent(true));

        return brick;
    }
    public GameEntity initBrick(int position, int colorKey, int brickTypeNumber){

        Point2D p;
        if (position <= 65) {
            //BoundingShape Box = BoundingShape.box(90,40);
            p = new Point2D((position % 11) * 100 + 95, (position / 11) * 45 + 30);
        }
        else {p = new Point2D( 95, 30);}
            brick = Entities.builder()
                    .type(getBrickType(brickTypeNumber))
                    .at(p.getX(), p.getY())
                    //.bbox(new HitBox("BrickHitBox", Box))
                    .viewFromTextureWithBBox(chooseTextureColor(colorKey))
                    .build();

            brickPhysics = new PhysicsComponent();
            brickPhysics.setBodyType(BodyType.STATIC);

            brick.addComponent(brickPhysics);
            brick.addComponent(new CollidableComponent(true));
            return brick;

    }
}


