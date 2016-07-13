package Breakout.control;

import Breakout.Breakout;
import com.almasb.fxgl.scene.GameScene;
import com.almasb.fxgl.scene.Viewport;
import com.almasb.fxgl.texture.Texture;
import com.almasb.fxgl.ui.UIController;
import com.almasb.fxgl.ui.UIFactory;
import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * This project is created by Romano Waschewski and Jasmin Mayrowski
 * As mandatory libraries we use FXGL v. 0.2.4 and antlr v.4.5.3
 */
public class BreakoutUIController implements UIController {

    @FXML
    private Label labelScore = new Label();

    @FXML
    private Label labelLifes = new Label();

    @FXML
    private double livesX;

    @FXML
    private double livesY;

    private ArrayList<Texture> lives = new ArrayList<>();

    private GameScene gameScene;

    public BreakoutUIController(GameScene gameScene) {
        this.gameScene = gameScene;
    }

    public Label getLabelScore() {
        return labelScore;
    }

    public Label getLabelLifes(){
        return labelLifes;
    }

    @Override
    public void init() {
        labelScore.setFont(UIFactory.newFont(18));
        labelLifes.setFont(UIFactory.newFont(18));
    }

    public void addLife() {
        int numLives = lives.size();
        Texture texture = Breakout.lifeTexture;
        texture.setTranslateX(livesX + 32 * numLives);
        texture.setTranslateY(livesY);
        texture.setFitWidth(16);
        texture.setFitHeight(16);
        lives.add(texture);
    }
    public void loseLife(){

        Texture t = lives.get(lives.size() - 1);

        Animation animation = getAnimationLoseLife(t);
        animation.setOnFinished(e -> lives.remove(t));
        animation.play();
    }

    private Animation getAnimationLoseLife(Texture texture) {
        texture.setFitWidth(64);
        texture.setFitHeight(64);

        Viewport viewport = gameScene.getViewport();

        TranslateTransition tt = new TranslateTransition(Duration.seconds(0.66), texture);
        tt.setToX(viewport.getWidth() / 2 - texture.getFitWidth() / 2);
        tt.setToY(viewport.getHeight() / 2 - texture.getFitHeight() / 2);

        ScaleTransition st = new ScaleTransition(Duration.seconds(0.66), texture);
        st.setToX(0);
        st.setToY(0);

        return new SequentialTransition(tt, st);
    }
}
