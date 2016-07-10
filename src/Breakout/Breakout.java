package Breakout;/**
 * Created by Romano on 01.07.2016.
 */


import Breakout.Control.BatControl;
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
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.PhysicsWorld;
import com.almasb.fxgl.settings.GameSettings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;


public class Breakout extends GameApplication {

    /*public static Assets assets;



    public Assets getAssets() {
        return assets;
    }

    public void setAssets(Assets assets) {
        this.assets = assets;
    }*/

    /*private PhysicsComponent viewComponent;
    private PhysicsComponent ball;*/



    private PhysicsComponent batPhysics;
    private PhysicsComponent ballPhysics;

    private String batTexture;
    private String ballTexture;
    private String brickTexture;
    private String wallTexture;

    private Text scoreText;

    public static BatControl batControl;
    public static double ApplicationWidth;
    public static double ApplicationHeight;
    public static GameWorld gameWorld;
    public static Entity bat;

    private ArrayList<GameEntity> playField;

    private IntegerProperty score = new SimpleIntegerProperty();

    public enum Type {
        BAT, BALL, BRICK, WALL, GROUND
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Breakout");
        settings.setVersion("dev");
        settings.setWidth(1280);
        settings.setHeight(720);
        settings.setIntroEnabled(false);
        settings.setApplicationMode(ApplicationMode.DEBUG);
    }

    @Override
    protected void initInput() {

        Input input = getInput();

        input.addInputMapping(new InputMapping("left", KeyCode.A));

        input.addInputMapping(new InputMapping("right", KeyCode.D));

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


    @Override
    protected void initAssets() {


        ballTexture = "Balls/ball_red.png";
        batTexture = "Bats/bat_black.png";
        brickTexture = "Bricks/brick_blue_small.png";
        wallTexture = "Walls/brick_red.png";

    }

    @Override
    protected void initGame() {

        gameWorld = getGameWorld();

        initWalls();
        initBat();
        initBall();
        initBrick();
        initBackground();
        //initScreenBounds();


    }

    @Override
    protected void initPhysics() {
        //Kollisionsabfrage zw. Ball und Brick
        PhysicsWorld physics = getPhysicsWorld();

        physics.setGravity(0, 1);
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
                BatPowerUp bpu = new BatPowerUp();
                bpu.pickedUp(PowerUp.PowerUpType.BIGGER);
            }
        });

        physics.addCollisionHandler(new CollisionHandler(Type.BALL, Type.GROUND) {
            @Override
            public void onCollisionBegin(Entity a, Entity b) {
                //Was passiert wenn der Ball den Boden berührt?

                score.set(score.get() - 1000);
                a.removeFromWorld();
                initBall();
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

        scoreText = new Text();

        scoreText.setTranslateY(20);
        scoreText.setTranslateX(5);
        scoreText.setFont(Font.font(20));
        scoreText.textProperty().bind(score.asString());

        getGameScene().addUINode(scoreText);
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

        ApplicationWidth = getWidth();
        ApplicationHeight = getHeight();
        BatFactory bf = new BatFactory();
        bat = bf.createBat(ApplicationWidth / 2 - 135 / 2, ApplicationHeight - 32, batTexture);
        getGameWorld().addEntities(bat);

        batControl = bat.getControlUnsafe(BatControl.class);
    }

    private void initBall() {

        BallFactory bf = new BallFactory();
        getGameWorld().addEntities(bf.createBall(getWidth() / 2 - 35 / 2, getHeight() / 2 - 35 / 2));
        //ballPhysics = bf.ballPhysics;
    }

    private void initBrick() {

        PlayField pf = new PlayField();

        playField = new ArrayList<GameEntity>();
        playField = pf.getPlayField();

        for (int i = 0; i < 12; i++) {

            getGameWorld().addEntities(playField.get(i));
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

        //Geschwindigkeit des Balls regeln. Wenn Geschw. unter 5 -> auf 5 setzen
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

