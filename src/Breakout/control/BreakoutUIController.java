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
    private Label labelScore = new Label();

    @FXML
    private Label labelLifes = new Label();

    public Label getLabelScore() {
        return labelScore;
    }

    public Label getLabelLifes(){
        return labelLifes;
    }

    @Override
    public void init() {

        //labelScore = new Label();
        labelScore.setFont(UIFactory.newFont(18));
        labelLifes.setFont(UIFactory.newFont(18));
    }
}
