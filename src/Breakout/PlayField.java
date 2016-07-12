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
        GameEntity brick1 = bf.initBrick(1, 4, 0);
        GameEntity brick2 = bf.initBrick(2, 4, 0);
        GameEntity brick3 = bf.initBrick(3, 4, 4);
        GameEntity brick4 = bf.initBrick(4, 4, 0);
        GameEntity brick5 = bf.initBrick(5, 4, 0);
        GameEntity brick6 = bf.initBrick(6, 4, 2);
        GameEntity brick7 = bf.initBrick(7, 4, 0);
        GameEntity brick8 = bf.initBrick(8, 4, 0);
        GameEntity brick9 = bf.initBrick(9, 4, 3);
        GameEntity brick10 = bf.initBrick(10, 4, 0);
        GameEntity brick11 = bf.initBrick(11, 6, 1);
        GameEntity brick12 = bf.initBrick(12, 6, 0);
        GameEntity brick13 = bf.initBrick(13, 6, 0);
        GameEntity brick14 = bf.initBrick(14, 6, 0);
        GameEntity brick15 = bf.initBrick(15, 6, 0);
        GameEntity brick16 = bf.initBrick(16, 6, 4);
        GameEntity brick17 = bf.initBrick(17, 6, 0);
        GameEntity brick18 = bf.initBrick(18, 6, 2);
        GameEntity brick19 = bf.initBrick(19, 6, 0);
        GameEntity brick20 = bf.initBrick(20, 6, 0);
        GameEntity brick21 = bf.initBrick(21, 6, 0);
        GameEntity brick22 = bf.initBrick(22, 0, 0);
        GameEntity brick23 = bf.initBrick(23, 0, 0);
        GameEntity brick24 = bf.initBrick(24, 0, 0);
        GameEntity brick25 = bf.initBrick(25, 0, 4);
        GameEntity brick26 = bf.initBrick(26, 0, 0);
        GameEntity brick27 = bf.initBrick(27, 0, 0);
        GameEntity brick28 = bf.initBrick(28, 0, 5);
        GameEntity brick29 = bf.initBrick(29, 0, 0);
        GameEntity brick30 = bf.initBrick(30, 0, 0);
        GameEntity brick31 = bf.initBrick(31, 0);
        GameEntity brick32 = bf.initBrick(32, 0, 1);
        GameEntity brick33 = bf.initBrick(33, 2, 0);
        GameEntity brick34 = bf.initBrick(34, 2, 5);
        GameEntity brick35 = bf.initBrick(35, 2, 0);
        GameEntity brick36 = bf.initBrick(36, 2, 0);
        GameEntity brick37 = bf.initBrick(37, 2, 4);
        GameEntity brick38 = bf.initBrick(38, 2, 0);
        GameEntity brick39 = bf.initBrick(39, 2, 0);
        GameEntity brick40 = bf.initBrick(40, 2, 1);
        GameEntity brick41 = bf.initBrick(41, 2, 0);
        GameEntity brick42 = bf.initBrick(42, 2);
        GameEntity brick43 = bf.initBrick(43, 2, 3);
        GameEntity brick44 = bf.initBrick(44, 8, 0);
        GameEntity brick45 = bf.initBrick(45, 8, 4);
        GameEntity brick46 = bf.initBrick(46, 8, 0);
        GameEntity brick47 = bf.initBrick(47, 8, 0);
        GameEntity brick48 = bf.initBrick(48, 8, 0);
        GameEntity brick49 = bf.initBrick(49, 8, 5);
        GameEntity brick50 = bf.initBrick(50, 8, 0);
        GameEntity brick51 = bf.initBrick(51, 8, 0);
        GameEntity brick52 = bf.initBrick(52, 8, 3);
        GameEntity brick53 = bf.initBrick(53, 8);
        GameEntity brick54 = bf.initBrick(54, 8, 0);

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
        playField.add(brick33);
        playField.add(brick34);
        playField.add(brick35);
        playField.add(brick36);
        playField.add(brick37);
        playField.add(brick38);
        playField.add(brick39);
        playField.add(brick40);
        playField.add(brick41);
        playField.add(brick42);
        playField.add(brick43);
        playField.add(brick44);
        playField.add(brick45);
        playField.add(brick46);
        playField.add(brick47);
        playField.add(brick48);
        playField.add(brick49);
        playField.add(brick50);
        playField.add(brick51);
        playField.add(brick52);
        playField.add(brick53);
        playField.add(brick54);

        return playField;
    }
}
