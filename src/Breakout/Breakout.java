package Breakout;/**
 * Created by Romano on 01.07.2016.
 */


import Breakout.control.BatControl;
import Breakout.control.BreakoutUIController;
import com.almasb.ents.Entity;
import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.GameEntity;
import com.almasb.fxgl.entity.component.CollidableComponent;
import com.almasb.fxgl.entity.component.TypeComponent;
import com.almasb.fxgl.gameplay.GameWorld;
import com.almasb.fxgl.input.ActionType;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.InputMapping;
import com.almasb.fxgl.input.OnUserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.PhysicsWorld;
import com.almasb.fxgl.scene.menu.MenuStyle;
import com.almasb.fxgl.settings.GameSettings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

import java.util.ArrayList;


public class Breakout extends GameApplication {

    private String batTexture;
    private String ballTexture;
    private String brickTexture;
    private String wallTexture;
    private String ballColor;

    private Text scoreText;

    public static BatControl batControl;
    public static double ApplicationWidth;
    public static double ApplicationHeight;
    public static GameWorld gameWorld;
    public static Entity bat;

    private ArrayList<GameEntity> playField;

    private IntegerProperty score;

    public enum Type {
        BAT, BALL, MULTIBALL, BRICK, WALL, GROUND
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Breakout");
        settings.setVersion("dev");
        settings.setWidth(1280);
        settings.setHeight(720);
        settings.setIntroEnabled(false);
        settings.setMenuEnabled(false);
        settings.setApplicationMode(ApplicationMode.DEBUG);
        settings.setMenuStyle(MenuStyle.FXGL_DEFAULT);
    }

    @Override
    protected void initInput() {

        Input input = getInput();

        input.addInputMapping(new InputMapping("left", KeyCode.A));

        input.addInputMapping(new InputMapping("right", KeyCode.D));

        input.addInputMapping(new InputMapping("release ball", KeyCode.SPACE));

    }

    @OnUserAction(name = "left", type = ActionType.ON_ACTION)
    public void left() {
        if(batControl != null){
            batControl.left();
        }
    }

    @OnUserAction(name = "right", type = ActionType.ON_ACTION)
    public void right() {
        if(batControl != null){
            batControl.right();
        }
    }

    @OnUserAction(name = "left", type = ActionType.ON_ACTION_END)
    public void stopBat() {
        batControl.stop();
    }

    @OnUserAction(name = "right", type = ActionType.ON_ACTION_END)
    public void stopBat2() {
        batControl.stop();
    }

    @OnUserAction(name = "release ball", type = ActionType.ON_ACTION_END)
    public void releaseBall(){
        BallFactory bf = new BallFactory();
        if (bf.getBall() != null);
        {
            initBall();
        }

    }

    @Override
    protected void initAssets() {


        ballTexture = "balls/ball_red.png";
        batTexture = "bats/bat_black.png";
        brickTexture = "bricks/brick_blue_small.png";
        wallTexture = "walls/brick_red.png";

    }

    @Override
    protected void initGame() {

        ballColor = "red";
        score = new SimpleIntegerProperty();

        gameWorld = getGameWorld();

        initWalls();
        initBat();
        initBrick();
        initBackground();
        //initScreenBounds();


    }

    @Override
    protected void initPhysics() {
        //Kollisionsabfrage zw. Ball und Brick
        PhysicsWorld physics = getPhysicsWorld();

        //physics.setGravity(0, 0.01f);
        physics.addCollisionHandler(new CollisionHandler(Type.BALL, Type.BRICK) {
            @Override
            public void onCollisionBegin(Entity a, Entity b) {

                score.set(score.get() + 100);

            }

            @Override
            public void onCollision(Entity a, Entity b) {


            }

            @Override
            public void onCollisionEnd(Entity a, Entity b) {

                b.removeFromWorld();
                //BatPowerUp bpu = new BatPowerUp();
                //bpu.pickedUp(PowerUp.PowerUpType.BIGGER);
                BallPowerUp ballPU = new BallPowerUp();
                //ballPU.pickedUp(PowerUp.PowerUpType.MULTIBALL);
                //ballPU.pickedUp(PowerUp.PowerUpType.FASTER);
                //ballPU.pickedUp(PowerUp.PowerUpType.MULTIBALL);
            }
        });

        physics.addCollisionHandler(new CollisionHandler(Type.MULTIBALL, Type.BRICK) {
            @Override
            public void onCollisionBegin(Entity a, Entity b) {

                score.set(score.get() + 100);

            }

            @Override
            public void onCollision(Entity a, Entity b) {


            }

            @Override
            public void onCollisionEnd(Entity a, Entity b) {

                b.removeFromWorld();

            }
        });

        physics.addCollisionHandler(new CollisionHandler(Type.BALL, Type.GROUND) {
            @Override
            public void onCollisionBegin(Entity a, Entity b) {
                //Was passiert wenn der Ball den Boden berührt?

                score.set(score.get() - 1000);
                BallFactory bf = new BallFactory();
                bf.setBall(null);
                a.removeFromWorld();

            }

            @Override
            public void onCollision(Entity a, Entity b) {
            }

            @Override
            public void onCollisionEnd(Entity a, Entity b) {

            }
        });

        physics.addCollisionHandler(new CollisionHandler(Type.MULTIBALL, Type.GROUND) {
            @Override
            public void onCollisionBegin(Entity a, Entity b) {
                //Was passiert wenn der Ball den Boden berührt?

                a.removeFromWorld();
            }

            @Override
            public void onCollision(Entity a, Entity b) {
            }

            @Override
            public void onCollisionEnd(Entity a, Entity b) {
            }
        });
    }

    @Override
    protected void initUI() {

        BreakoutUIController controller = new BreakoutUIController();

        Parent fxmlUI = getAssetLoader().loadFXML("breakout_ui.fxml", controller);
        fxmlUI.setTranslateX(getWidth() - 250);
        fxmlUI.setTranslateY(getHeight() - 150);

        controller.getLabelScore().textProperty().bind(score.asString("Score: [%d]"));

        getGameScene().addUINode(fxmlUI);
    }

    private void initBackground() {
    }

    private void initWalls() {


        WallFactory wall = new WallFactory();
        int i;

        for (i = 0; i < 60; i++) {

            Entity top = wall.createWalls("top", getWidth(), getHeight(), i);
            getGameWorld().addEntities(top);

        }


        for (i = 0; i < 60; i++) {

            Entity left = wall.createWalls("left", getWidth(), getHeight(), i);
            getGameWorld().addEntities(left);
        }

        for (i = 0; i < 60; i++) {

            Entity right = wall.createWalls("right", getWidth(), getHeight(), i);
            getGameWorld().addEntities(right);
        }

        Entity bot = wall.createWalls("bot", getWidth(), getHeight(), i = 0);
        getGameWorld().addEntities(bot);
    }

    private void initBat() {

        //Paddel initialisieren
        ApplicationWidth = getWidth();
        ApplicationHeight = getHeight();
        BatFactory bf = new BatFactory();
        bat = bf.createBat(ApplicationWidth / 2 - 135 / 2, ApplicationHeight - 32, batTexture);
        getGameWorld().addEntities(bat);

        batControl = bat.getControlUnsafe(BatControl.class);
    }

    private void initBall() {

        //Ball initialisieren

        BallFactory bf = new BallFactory();
        //getGameWorld().addEntities(bf.createBall(getWidth(), getHeight() / 2 - 35 / 2));
        getGameWorld().addEntities(bf.createBall(getWidth() / 2 - 35 / 2, getHeight() / 2 - 35 / 2, ballColor));
        //ballPhysics = bf.ballPhysics;
    }

    private void initBrick() {

        PlayField pf = new PlayField();

        playField = new ArrayList<GameEntity>();
        playField = pf.getPlayField();

        for (int i = 0; i < 65; i++) {

            if (playField.size() > i) {
                { getGameWorld().addEntities(playField.get(i));}
            } else break;
        }

        playField.clear();
    }

    private void initScreenBounds() {
        Entity walls = Entities.makeScreenBounds(150);
        walls.addComponent(new TypeComponent(Type.WALL));
        walls.addComponent(new CollidableComponent(true));

        getGameWorld().addEntity(walls);
    }

    @Override
    protected void onUpdate(double tpf) {

        /*batPhysics = new PhysicsComponent();
        ballPhysics = new PhysicsComponent();
        batPhysics.setLinearVelocity(0,0);

        //Geschwindigkeit des balls regeln. Wenn Geschw. unter 5 -> auf 5 setzen
        Point2D v = ballPhysics.getLinearVelocity();
        if(Math.abs(v.getY())<5) {
            double x = v.getX();
            double signY = Math.signum(v.getY());
            ballPhysics.setLinearVelocity(x, signY * 5);
            }*/

}

    public static void main(java.lang.String[] args) {
        launch(args);
    }

}

