package Breakout;

import com.almasb.fxgl.entity.GameEntity;

import java.util.ArrayList;

/**
 * Created by Romano on 09.07.2016.
 */
public class PlayField {

    public ArrayList<GameEntity> getPlayField(){

        BrickFactory bf = new BrickFactory();

        GameEntity brick0 = bf.initBrick(0, 4);
        GameEntity brick1 = bf.initBrick(1, 4);
        GameEntity brick2 = bf.initBrick(2, 4);
        GameEntity brick3 = bf.initBrick(3, 4);
        GameEntity brick4 = bf.initBrick(4, 4);
        GameEntity brick5 = bf.initBrick(5, 4);
        GameEntity brick6 = bf.initBrick(6, 4);
        GameEntity brick7 = bf.initBrick(7, 4);
        GameEntity brick8 = bf.initBrick(8, 4);
        GameEntity brick9 = bf.initBrick(9, 4);
        GameEntity brick10 = bf.initBrick(10,4);
        GameEntity brick11 = bf.initBrick(11,6);
        GameEntity brick12 = bf.initBrick(12,6);
        GameEntity brick13 = bf.initBrick(13,6);
        GameEntity brick14 = bf.initBrick(14,6);
        GameEntity brick15 = bf.initBrick(15,6);
        GameEntity brick16 = bf.initBrick(16,6);
        GameEntity brick17 = bf.initBrick(17,6);
        GameEntity brick18 = bf.initBrick(18,6);
        GameEntity brick19 = bf.initBrick(19,6);
        GameEntity brick20 = bf.initBrick(20,6);
        GameEntity brick21 = bf.initBrick(21,6);
        GameEntity brick22 = bf.initBrick(22,0);
        GameEntity brick23 = bf.initBrick(23,0);
        GameEntity brick24 = bf.initBrick(24,0);
        GameEntity brick25 = bf.initBrick(25,0);
        GameEntity brick26 = bf.initBrick(26,0);
        GameEntity brick27 = bf.initBrick(27,0);
        GameEntity brick28 = bf.initBrick(28,0);
        GameEntity brick29 = bf.initBrick(29,0);
        GameEntity brick30 = bf.initBrick(30,0);
        GameEntity brick31 = bf.initBrick(31,0);
        GameEntity brick32 = bf.initBrick(32,0);


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
        playField.add(brick25);
        playField.add(brick26);
        playField.add(brick27);
        playField.add(brick28);
        playField.add(brick29);
        playField.add(brick30);
        playField.add(brick31);
        playField.add(brick32);

        return playField;
    }
}
