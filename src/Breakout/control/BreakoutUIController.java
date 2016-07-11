package Breakout.control;

import com.almasb.fxgl.ui.UIController;
import com.almasb.fxgl.ui.UIFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Created by Romano on 11.07.2016.
 */
public class BreakoutUIController implements UIController {

    @FXML
    private Label labelScore;

    public Label getLabelScore() {
        return labelScore;
    }

    @Override
    public void init() {

        //labelScore = new Label();
        labelScore.setFont(UIFactory.newFont(18));

    }
}
