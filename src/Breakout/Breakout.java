package Breakout;

/**
 * This project is created by Romano Waschewski and Jasmin Mayrowski
 * As mandatory libraries we use FXGL v. 0.2.4 and antlr v.4.5.3
 */
import Breakout.control.BatControl;
import Breakout.control.BreakoutUIController;
import com.almasb.ents.Entity;
import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.GameEntity;
import com.almasb.fxgl.entity.component.PositionComponent;
import com.almasb.fxgl.gameplay.GameWorld;
import com.almasb.fxgl.input.ActionType;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.InputMapping;
import com.almasb.fxgl.input.OnUserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.PhysicsWorld;
import com.almasb.fxgl.scene.menu.MenuStyle;
import com.almasb.fxgl.settings.GameSettings;
import com.almasb.fxgl.texture.Texture;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Breakout extends GameApplication {

    private String batTexture;
    private String ballColor;
    private Texture bgTexture;

    private BreakoutUIController controller;

    private Text scoreText;
    private Text lifesText;

    public static BatControl batControl;
    public static double ApplicationWidth;
    public static double ApplicationHeight;
    public static Texture lifeTexture;
    public static GameWorld gameWorld;
    public static Entity bat;
    public PositionComponent position;
    public GameEntity powerUp;

    private ArrayList<GameEntity> playField;

    private IntegerProperty score;
    private IntegerProperty lifes;
    private IntegerProperty bricks;
    private IntegerProperty levelCounter;

    public enum Type {
        BAT, WALL, GROUND, BALL, MULTIBALL,
        BRICK, BRICK_FASTER_POWERUP, BRICK_SLOWER_POWERUP,
        BRICK_BIGGER_POWERUP, BRICK_SMALLER_POWERUP, BRICK_MULTIBALL_POWERUP

    }

    private enum RenderLayer implements com.almasb.fxgl.entity.RenderLayer{

        BACKGROUND(100);

        private final int index;

        RenderLayer(int index) {
            this.index = index;
        }

        @Override
            public int index() {
                return index;
            }
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Breakout");
        settings.setVersion("dev 1.0");
        settings.setWidth(1280);
        settings.setHeight(720);
        settings.setIntroEnabled(false);
        settings.setMenuEnabled(false);
        settings.setApplicationMode(ApplicationMode.DEVELOPER);
        settings.setMenuStyle(MenuStyle.FXGL_DEFAULT);
    }

    @Override
    protected void initAchievements() {
        /*Achievement levelAchievement = new Achievement("Rookie", "You finished the first level. Good Job!");
        getAchievementManager().registerAchievement(levelAchievement);*/
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
        if (bf.getBall() == null)
        {
            initBall();
        }
    }

    @Override
    protected void initAssets() {
        batTexture = "bats/bat_black.png";
    }

    @Override
    protected void initGame() {
        ballColor = "red";
        score = new SimpleIntegerProperty();
        lifes = new SimpleIntegerProperty(100);
        levelCounter = new SimpleIntegerProperty();
        gameWorld = getGameWorld();
        //getAchievementManager().getAchievementByName("Rookie").achievedProperty().
        initWalls();
        initBat();
        initBrick();
        initBackground();
        //initScreenBounds();
        getAudioPlayer().playMusic(getAssetLoader().loadMusic("gamemusic.wav"));
    }

    @Override
    protected void initUI() {

        controller = new BreakoutUIController(getGameScene());

        Parent fxmlUI = getAssetLoader().loadFXML("breakout_ui.fxml", controller);
        fxmlUI.setTranslateX(getWidth() - 350);
        fxmlUI.setTranslateY(getHeight() -155);

        controller.getLabelScore().textProperty().bind(score.asString("Score: [%d]"));
        controller.getLabelLifes().textProperty().bind(lifes.asString("Lives: [%d]"));

        getGameScene().addUINodes(fxmlUI);
    }

    private void initBackground() {
        GameEntity bg = new GameEntity();
        BackgroundFactory bf = new BackgroundFactory();

        bgTexture = getAssetLoader().loadTexture(bf.getBackground(levelCounter.get()));
        bgTexture.setFitWidth(getWidth());
        bgTexture.setFitHeight(getHeight());

        bg.getMainViewComponent().setView(bgTexture);
        bg.getMainViewComponent().setRenderLayer(RenderLayer.BACKGROUND);

        getGameWorld().addEntity(bg);

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

        Entity bot = wall.createWalls("bot", getWidth(), getHeight(), 0);
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
        getGameWorld().addEntities(bf.createBall(getWidth() / 2 - 35 / 2, getHeight() / 2 - 35 / 2, ballColor));
    }

    private void initBrick() {
        playField = new ArrayList<>();
        playField = MultiPlayFieldFactory.getLevel(levelCounter.get());

        for (int i = 0; i < 65; i++) {

            if ((playField != null ? playField.size() : 0) > i) {
                    {
                        getGameWorld().addEntities(playField.get(i));

                    }
            } else break;
        }
        if (playField != null){
            bricks = new SimpleIntegerProperty(playField.size());
            playField.clear();
        }

        getInput().setProcessInput(true);

        /// Dieser Code generiert 1 Playfield
        /*PlayField pf = new PlayField();

        playField = new ArrayList<GameEntity>();
        playField = pf.getPlayField();

        for (int i = 0; i < 65; i++) {

            if (playField.size() > i) {
                { getGameWorld().addEntities(playField.get(i));}
            } else break;
        }
        bricks = new SimpleIntegerProperty(playField.size());

        playField.clear();*/
    }

    private void loseLife(){

        lifes.set(lifes.get() - 1);

        if (lifes.get() == 0)
            showGameOver();

    }
    private void tutorial(){

        //TODO: Anfangstutorial für Spieler

    }

    private void onBrickRemoved(){
        bricks.set(bricks.get() - 1);

        if(bricks.get() == 0){
            getAudioPlayer().stopAllMusic();
            getAudioPlayer().playSound(getAssetLoader().loadSound("win.mp3"));
            getDisplay().showConfirmationBox("Congratulation!\nYou have won the Game.\nNext Level?", yes -> {
                if (yes) {
                    nextLevel();
                }
                else {
                    exit();
                }
            });
        }
        else{playBounceSound();}
    }

    private void nextLevel(){

        getInput().setProcessInput(false);
        cleanupLevel();
        ballColor = "red";
        score = new SimpleIntegerProperty();
        lifes = new SimpleIntegerProperty(10);
        levelCounter.set(levelCounter.get() + 1);

        if (levelCounter.get() < MultiPlayFieldFactory.getPlayFieldCount()) {

            gameWorld = getGameWorld();

            //initGame();
            initWalls();
            initBat();
            initBrick();
            initBackground();

            getAudioPlayer().stopAllMusic();
            getAudioPlayer().stopAllSounds();
            getAudioPlayer().playMusic(getAssetLoader().loadMusic("gamemusic.wav"));
        }
        else{

            getDisplay().showConfirmationBox("You have finishes all Levels.\nStart a new Game?", yes -> {
            if (yes) {
                startNewGame();
            }
            else {
                exit();
            }
        });}
    }
    private void showGameOver(){

        getAudioPlayer().stopAllMusic();
        getAudioPlayer().playSound(getAssetLoader().loadSound("game_over.mp3"));
        getDisplay().showConfirmationBox("Game Over.\nContinue?", yes -> {
            if (yes) {
                startNewGame();
            }
            else {
                exit();
            }
        });
    }
    private void cleanupLevel() {
        getGameWorld().getEntitiesByType(
                Type.BALL, Type.WALL, Type.BAT, Type.GROUND, Type.BRICK, Type.BRICK_FASTER_POWERUP, Type.BRICK_SLOWER_POWERUP,
                Type.BRICK_MULTIBALL_POWERUP,  Type.BRICK_BIGGER_POWERUP, Type.BRICK_SMALLER_POWERUP,
                PowerUp.PowerUpType.FASTER, PowerUp.PowerUpType.SLOWER, PowerUp.PowerUpType.MULTIBALL,
                PowerUp.PowerUpType.BIGGER, PowerUp.PowerUpType.SMALLER)
                .forEach(Entity::removeFromWorld);
    }

    private void playBounceSound(){

        getAudioPlayer().playSound(getAssetLoader().loadSound("tock_one.wav"));

    }
    @Override
    protected void onUpdate(double tpf) {}

    @Override
    protected void initPhysics() {
        //Kollisionsabfrage zw. Ball und Brick
        PhysicsWorld physics = getPhysicsWorld();
        physics.setGravity(0,0.5f);
        physics.addCollisionHandler(new CollisionHandler(Type.BALL, Type.BRICK) {
            @Override
            public void onCollisionBegin(Entity a, Entity b) {
                score.set(score.get() + 100);
            }

            @Override
            public void onCollisionEnd(Entity a, Entity b) {
                b.removeFromWorld();
                onBrickRemoved();
            }
        });

        physics.addCollisionHandler(new CollisionHandler(Type.BALL, Type.BRICK_FASTER_POWERUP) {
            @Override
            public void onCollisionBegin(Entity a, Entity b) {
                score.set(score.get() + 100);
            }

            @Override
            public void onCollisionEnd(Entity a, Entity b) {

                position = b.getComponentUnsafe(PositionComponent.class);
                Point2D p = position.getValue();
                b.removeFromWorld();
                PowerUpSpawner PUSpawner = new PowerUpSpawner();
                powerUp = PUSpawner.spawnPowerUp(p, PowerUp.PowerUpType.FASTER, "yellow");
                getGameWorld().addEntity(powerUp);
                onBrickRemoved();
            }
        });

        physics.addCollisionHandler(new CollisionHandler(Type.BALL, Type.BRICK_SLOWER_POWERUP) {
            @Override
            public void onCollisionBegin(Entity a, Entity b) {
                score.set(score.get() + 100);
            }

            @Override
            public void onCollisionEnd(Entity a, Entity b) {

                position = b.getComponentUnsafe(PositionComponent.class);
                Point2D p = position.getValue();
                b.removeFromWorld();
                PowerUpSpawner PUSpawner = new PowerUpSpawner();
                powerUp = PUSpawner.spawnPowerUp(p, PowerUp.PowerUpType.SLOWER, "purple");
                getGameWorld().addEntity(powerUp);
                onBrickRemoved();
            }
        });

        physics.addCollisionHandler(new CollisionHandler(Type.BALL, Type.BRICK_MULTIBALL_POWERUP) {
            @Override
            public void onCollisionBegin(Entity a, Entity b) {
                score.set(score.get() + 100);
            }

            @Override
            public void onCollisionEnd(Entity a, Entity b) {
                position = b.getComponentUnsafe(PositionComponent.class);
                Point2D p = position.getValue();
                b.removeFromWorld();
                PowerUpSpawner PUSpawner = new PowerUpSpawner();
                powerUp = PUSpawner.spawnPowerUp(p, PowerUp.PowerUpType.MULTIBALL, "green");
                getGameWorld().addEntity(powerUp);
                onBrickRemoved();

            }
        });

        physics.addCollisionHandler(new CollisionHandler(Type.BALL, Type.BRICK_BIGGER_POWERUP) {
            @Override
            public void onCollisionBegin(Entity a, Entity b) {
                score.set(score.get() + 100);
            }

            @Override
            public void onCollisionEnd(Entity a, Entity b) {

                position = b.getComponentUnsafe(PositionComponent.class);
                Point2D p = position.getValue();
                b.removeFromWorld();
                PowerUpSpawner PUSpawner = new PowerUpSpawner();
                powerUp = PUSpawner.spawnPowerUp(p, PowerUp.PowerUpType.BIGGER, "blue");
                getGameWorld().addEntity(powerUp);
                onBrickRemoved();
            }
        });

        physics.addCollisionHandler(new CollisionHandler(Type.BALL, Type.BRICK_SMALLER_POWERUP) {
            @Override
            public void onCollisionBegin(Entity a, Entity b) {
                score.set(score.get() + 100);
            }

            @Override
            public void onCollisionEnd(Entity a, Entity b) {

                position = b.getComponentUnsafe(PositionComponent.class);
                Point2D p = position.getValue();
                b.removeFromWorld();
                PowerUpSpawner PUSpawner = new PowerUpSpawner();
                powerUp = PUSpawner.spawnPowerUp(p, PowerUp.PowerUpType.SMALLER, "red");
                getGameWorld().addEntity(powerUp);
                onBrickRemoved();
            }
        });
        //Kollisionsabfrage zwischen den Zusatzbällen und den Bricks
        physics.addCollisionHandler(new CollisionHandler(Type.MULTIBALL, Type.BRICK) {
            @Override
            public void onCollisionBegin(Entity a, Entity b) {
                //Block wird zerstört und es gibt Punkte, aber es werden keine PowerUps erzeugt
                score.set(score.get() + 100);
                b.removeFromWorld();
                onBrickRemoved();
            }
        });

        physics.addCollisionHandler(new CollisionHandler(Type.MULTIBALL, Type.BRICK_FASTER_POWERUP) {
            @Override
            public void onCollisionBegin(Entity a, Entity b) {
                //Block wird zerstört und es gibt Punkte, aber es werden keine PowerUps erzeugt
                score.set(score.get() + 100);
                b.removeFromWorld();
                onBrickRemoved();

            }
        });

        physics.addCollisionHandler(new CollisionHandler(Type.MULTIBALL, Type.BRICK_SLOWER_POWERUP) {
            @Override
            public void onCollisionBegin(Entity a, Entity b) {
                //Block wird zerstört und es gibt Punkte, aber es werden keine PowerUps erzeugt
                score.set(score.get() + 100);
                b.removeFromWorld();
                onBrickRemoved();
            }
        });

        physics.addCollisionHandler(new CollisionHandler(Type.MULTIBALL, Type.BRICK_MULTIBALL_POWERUP) {
            @Override
            public void onCollisionBegin(Entity a, Entity b) {
                //Block wird zerstört und es gibt Punkte, aber es werden keine PowerUps erzeugt
                score.set(score.get() + 100);
                b.removeFromWorld();
                onBrickRemoved();
            }
        });

        physics.addCollisionHandler(new CollisionHandler(Type.MULTIBALL, Type.BRICK_BIGGER_POWERUP) {
            @Override
            public void onCollisionBegin(Entity a, Entity b) {
                //Block wird zerstört und es gibt Punkte, aber es werden keine PowerUps erzeugt
                score.set(score.get() + 100);
                b.removeFromWorld();
                onBrickRemoved();
            }
        });

        physics.addCollisionHandler(new CollisionHandler(Type.MULTIBALL, Type.BRICK_SMALLER_POWERUP) {
            @Override
            public void onCollisionBegin(Entity a, Entity b) {
                //Block wird zerstört und es gibt Punkte, aber es werden keine PowerUps erzeugt
                score.set(score.get() + 100);
                b.removeFromWorld();
                onBrickRemoved();
            }
        });

        //Kollisionsabfrage zwischen Ball und Boden
        physics.addCollisionHandler(new CollisionHandler(Type.BALL, Type.GROUND) {
            @Override
            public void onCollisionBegin(Entity a, Entity b) {
                //Was passiert wenn der Ball den Boden berührt?
                //Er verschwindet, der Spieler verliert Punkte bzw. Leben und Paddel wird auf normalgröße zurückgesetzt
                loseLife();
                a.removeFromWorld();
                Breakout.batControl.normalizeBatWidth();
            }
        });

        //Kollisionsabfrage zwischen den Zusatzbällen und Boden
        physics.addCollisionHandler(new CollisionHandler(Type.MULTIBALL, Type.GROUND) {
            @Override
            public void onCollisionBegin(Entity a, Entity b) {
                //Was passiert wenn der ZusatzBall den Boden berührt?
                //Er wird aus dem Spiel entfernt, es gibt dabei kein Punktabzug

                a.removeFromWorld();
            }
        });

        //Kollisionsabfragen zwischen PowerUp und Spielerpaddel
        physics.addCollisionHandler(new CollisionHandler(PowerUp.PowerUpType.FASTER, Type.BAT) {
            @Override
            public void onCollisionBegin(Entity a, Entity b) {
                //Was passiert wenn das PowerUp vom Spieler aufgefangen wird?
                //Es wird der BallPowerUp Klasse mitgeteilt, dass ein PowerUp aufgesammelt wurde
                //Anschließend, wird das PowerUp wieder aus der Spielwelt entfernt

                BallPowerUp bpu = new BallPowerUp();
                bpu.pickedUp(PowerUp.PowerUpType.FASTER);
                a.removeFromWorld();
            }
        });

        physics.addCollisionHandler(new CollisionHandler(PowerUp.PowerUpType.SLOWER, Type.BAT) {
            @Override
            public void onCollisionBegin(Entity a, Entity b) {
                //Was passiert wenn das PowerUp vom Spieler aufgefangen wird?
                //Es wird der BallPowerUp Klasse mitgeteilt, dass ein PowerUp aufgesammelt wurde
                //Anschließend, wird das PowerUp wieder aus der Spielwelt entfernt

                BallPowerUp bpu = new BallPowerUp();
                bpu.pickedUp(PowerUp.PowerUpType.SLOWER);
                a.removeFromWorld();
            }
        });

        physics.addCollisionHandler(new CollisionHandler(PowerUp.PowerUpType.MULTIBALL, Type.BAT) {
            @Override
            public void onCollisionBegin(Entity a, Entity b) {
                //Was passiert wenn das PowerUp vom Spieler aufgefangen wird?
                //Es wird der BallPowerUp Klasse mitgeteilt, dass ein PowerUp aufgesammelt wurde
                //Anschließend, wird das PowerUp wieder aus der Spielwelt entfernt

                BallPowerUp bpu = new BallPowerUp();
                bpu.pickedUp(PowerUp.PowerUpType.MULTIBALL);
                a.removeFromWorld();
            }
        });

        physics.addCollisionHandler(new CollisionHandler(PowerUp.PowerUpType.BIGGER, Type.BAT) {
            @Override
            public void onCollisionBegin(Entity a, Entity b) {
                //Was passiert wenn das PowerUp vom Spieler aufgefangen wird?
                //Es wird der BallPowerUp Klasse mitgeteilt, dass ein PowerUp aufgesammelt wurde
                //Anschließend, wird das PowerUp wieder aus der Spielwelt entfernt

                BatPowerUp bpu = new BatPowerUp();
                bpu.pickedUp(PowerUp.PowerUpType.BIGGER);
                a.removeFromWorld();
            }
        });

        physics.addCollisionHandler(new CollisionHandler(PowerUp.PowerUpType.SMALLER, Type.BAT) {
            @Override
            public void onCollisionBegin(Entity a, Entity b) {
                //Was passiert wenn das PowerUp vom Spieler aufgefangen wird?
                //Es wird der BallPowerUp Klasse mitgeteilt, dass ein PowerUp aufgesammelt wurde
                //Anschließend, wird das PowerUp wieder aus der Spielwelt entfernt

                BatPowerUp bpu = new BatPowerUp();
                bpu.pickedUp(PowerUp.PowerUpType.SMALLER);
                a.removeFromWorld();
            }
        });

        //Kollisionsabfrage zwischen PowerUp und Boden
        physics.addCollisionHandler(new CollisionHandler(PowerUp.PowerUpType.FASTER, Type.GROUND) {
            @Override
            public void onCollisionBegin(Entity a, Entity b) {
                //Was passiert wenn das PowerUp den Boden berührt?
                //Es wird aus dem Spiel entfernt

                a.removeFromWorld();
            }
        });
        //Kollisionsabfrage zwischen PowerUp und Boden
        physics.addCollisionHandler(new CollisionHandler(PowerUp.PowerUpType.SLOWER, Type.GROUND) {
            @Override
            public void onCollisionBegin(Entity a, Entity b) {
                //Was passiert wenn das PowerUp den Boden berührt?
                //Es wird aus dem Spiel entfernt

                a.removeFromWorld();
            }
        });

        //Kollisionsabfrage zwischen PowerUp und Boden
        physics.addCollisionHandler(new CollisionHandler(PowerUp.PowerUpType.MULTIBALL, Type.GROUND) {
            @Override
            public void onCollisionBegin(Entity a, Entity b) {
                //Was passiert wenn das PowerUp den Boden berührt?
                //Es wird aus dem Spiel entfernt

                a.removeFromWorld();
            }
        });
        //Kollisionsabfrage zwischen PowerUp und Boden
        physics.addCollisionHandler(new CollisionHandler(PowerUp.PowerUpType.BIGGER, Type.GROUND) {
            @Override
            public void onCollisionBegin(Entity a, Entity b) {
                //Was passiert wenn das PowerUp den Boden berührt?
                //Es wird aus dem Spiel entfernt

                a.removeFromWorld();
            }
        });

        //Kollisionsabfrage zwischen PowerUp und Boden
        physics.addCollisionHandler(new CollisionHandler(PowerUp.PowerUpType.SMALLER, Type.GROUND) {
            @Override
            public void onCollisionBegin(Entity a, Entity b) {
                //Was passiert wenn das PowerUp den Boden berührt?
                //Es wird aus dem Spiel entfernt

                a.removeFromWorld();
            }
        });

        physics.addCollisionHandler(new CollisionHandler(Type.BALL, Type.BAT) {
            @Override
            public void onCollisionBegin(Entity a, Entity b) {
                playBounceSound();
            }
        });
        physics.addCollisionHandler(new CollisionHandler(Type.MULTIBALL, Type.BAT) {
            @Override
            public void onCollisionBegin(Entity a, Entity b) {
                playBounceSound();
            }
        });

    }
    public static void main(java.lang.String[] args) {
        launch(args);
    }

}

