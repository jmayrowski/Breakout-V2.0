package Breakout;

import com.almasb.fxgl.entity.GameEntity;

import java.util.ArrayList;

/**
 * Created by Romano on 09.07.2016.
 *
 * Klasse zur Erstellung genau 1 vorgefertigten Levels
 * Wird durch MultiPlayFieldFactory ersetzt
 */
public class PlayField {

    public ArrayList<GameEntity>  getPlayField() {


        BrickFactory bf = new BrickFactory();
        ArrayList<GameEntity> playField = new ArrayList<>();

        playField.add(bf.initBrick(0, 4));
        playField.add(bf.initBrick(1, 4, 0));
        playField.add(bf.initBrick(2, 4, 0));
        playField.add(bf.initBrick(3, 4, 4));
        playField.add(bf.initBrick(4, 4, 0));
        playField.add(bf.initBrick(5, 4, 0));
        playField.add(bf.initBrick(6, 4, 2));
        playField.add(bf.initBrick(7, 4, 0));
        playField.add(bf.initBrick(8, 4, 0));
        playField.add(bf.initBrick(9, 4, 3));
        playField.add(bf.initBrick(10, 4, 0));
        playField.add(bf.initBrick(11, 6, 1));
        playField.add(bf.initBrick(12, 6, 0));
        playField.add(bf.initBrick(13, 6, 0));
        playField.add(bf.initBrick(14, 6, 0));
        playField.add(bf.initBrick(15, 6, 0));
        playField.add(bf.initBrick(16, 6, 4));
        playField.add(bf.initBrick(17, 6, 0));
        playField.add(bf.initBrick(18, 6, 2));
        playField.add(bf.initBrick(19, 6, 0));
        playField.add(bf.initBrick(20, 6, 0));
        playField.add(bf.initBrick(21, 6, 0));
        playField.add(bf.initBrick(22, 0, 0));
        playField.add(bf.initBrick(23, 0, 0));
        playField.add(bf.initBrick(24, 0, 0));
        playField.add(bf.initBrick(25, 0, 4));
        playField.add(bf.initBrick(26, 0, 0));
        playField.add(bf.initBrick(27, 0, 0));
        playField.add(bf.initBrick(28, 0, 5));
        playField.add(bf.initBrick(29, 0, 0));
        playField.add(bf.initBrick(30, 0, 0));
        playField.add(bf.initBrick(31, 0));
        playField.add(bf.initBrick(32, 0, 1));
        playField.add(bf.initBrick(33, 2, 0));
        playField.add(bf.initBrick(34, 2, 5));
        playField.add(bf.initBrick(35, 2, 0));
        playField.add(bf.initBrick(36, 2, 0));
        playField.add(bf.initBrick(37, 2, 4));
        playField.add(bf.initBrick(38, 2, 0));
        playField.add(bf.initBrick(39, 2, 0));
        playField.add(bf.initBrick(40, 2, 1));
        playField.add(bf.initBrick(41, 2, 0));
        playField.add(bf.initBrick(42, 2));
        playField.add(bf.initBrick(43, 2, 3));
        playField.add(bf.initBrick(44, 8, 0));
        playField.add(bf.initBrick(45, 8, 4));
        playField.add(bf.initBrick(46, 8, 0));
        playField.add(bf.initBrick(47, 8, 0));
        playField.add(bf.initBrick(48, 8, 0));
        playField.add(bf.initBrick(49, 8, 5));
        playField.add(bf.initBrick(50, 8, 0));
        playField.add(bf.initBrick(51, 8, 0));
        playField.add(bf.initBrick(52, 8, 3));
        playField.add(bf.initBrick(53, 8));
        playField.add(bf.initBrick(54, 8, 0));


        return playField;
    }
}
