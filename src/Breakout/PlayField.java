package Breakout;

import com.almasb.fxgl.entity.GameEntity;

import java.util.ArrayList;

/**
 * Created by Romano on 09.07.2016.
 */
public class PlayField {

    public ArrayList<GameEntity> getPlayField(){

        BrickFactory bf = new BrickFactory();

        GameEntity brick0 = bf.initBrick(0, 7);
        GameEntity brick1 = bf.initBrick(1, 1);
        GameEntity brick2 = bf.initBrick(2, 0);
        GameEntity brick3 = bf.initBrick(3, 0);
        GameEntity brick4 = bf.initBrick(4, 0);
        GameEntity brick5 = bf.initBrick(5, 0);
        GameEntity brick6 = bf.initBrick(6, 0);
        GameEntity brick7 = bf.initBrick(7, 0);
        GameEntity brick8 = bf.initBrick(8, 0);
        GameEntity brick9 = bf.initBrick(9, 0);
        GameEntity brick10 = bf.initBrick(10, 5);
        GameEntity brick11 = bf.initBrick(11,5);
        GameEntity brick12 = bf.initBrick(12,3);
        GameEntity brick13 = bf.initBrick(13,3);
        GameEntity brick14 = bf.initBrick(14,3);
        GameEntity brick15 = bf.initBrick(15,3);
        GameEntity brick16 = bf.initBrick(16,3);
        GameEntity brick17 = bf.initBrick(17,3);
        GameEntity brick18 = bf.initBrick(18,3);
        GameEntity brick19 = bf.initBrick(19,5);
        GameEntity brick20 = bf.initBrick(20,5);
        GameEntity brick21 = bf.initBrick(21,5);
        GameEntity brick22 = bf.initBrick(22,5);
        GameEntity brick23 = bf.initBrick(23,5);
        GameEntity brick24 = bf.initBrick(24,5);





        ArrayList<GameEntity> playField = new ArrayList<>();

        playField.add(brick0);
        playField.add(brick1);
        playField.add(brick2);
        playField.add(brick3);
        playField.add(brick4);
        playField.add(brick5);
        playField.add(brick6);
        playField.add(brick7);
        playField.add(brick8);
        playField.add(brick9);
        playField.add(brick10);
        playField.add(brick11);
        playField.add(brick12);
        playField.add(brick13);
        playField.add(brick14);
        playField.add(brick15);
        playField.add(brick16);
        playField.add(brick17);
        playField.add(brick18);
        playField.add(brick19);
        playField.add(brick20);
        playField.add(brick21);
        playField.add(brick22);
        playField.add(brick23);
        playField.add(brick24);




        return playField;
    }
}
