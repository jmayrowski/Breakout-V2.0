package Breakout;

import com.almasb.fxgl.entity.GameEntity;

import java.util.ArrayList;

/**
 * This project is created by Romano Waschewski and Jasmin Mayrowski
 * As mandatory libraries we use FXGL v. 0.2.4 and antlr v.4.5.3
 */
public class MultiPlayFieldFactory {

    public static int getPlayFieldCount() {
        return 6;
    }

    public static ArrayList<GameEntity> getLevel(int levelIndex){

        if(levelIndex == 0){return getPlayFieldByIndex0();}
        if(levelIndex == 1){return getPlayFieldByIndex1();}
        if(levelIndex == 2){return getPlayFieldByIndex2();}
        if(levelIndex == 3){return getPlayFieldByIndex3();}
        if(levelIndex == 4){return getPlayFieldByIndex4();}
        if(levelIndex == 5){return getPlayFieldByIndex5();}

        return null;
    }

    private static ArrayList<GameEntity> getPlayFieldByIndex0(){

        BrickFactory bf = new BrickFactory();
        ArrayList<GameEntity> playField = new ArrayList<>();

        playField.add(bf.initBrick(0, 0, 0));
        playField.add(bf.initBrick(1, 1, 0));
        playField.add(bf.initBrick(2, 3, 0));
        playField.add(bf.initBrick(3, 3, 4));
        playField.add(bf.initBrick(4, 4, 0));
        playField.add(bf.initBrick(5, 5, 0));
        playField.add(bf.initBrick(6, 6, 2));
        playField.add(bf.initBrick(7, 7, 0));
        playField.add(bf.initBrick(8, 8, 0));
        playField.add(bf.initBrick(9, 9, 0));
        playField.add(bf.initBrick(10, 10, 0));
        playField.add(bf.initBrick(11, 11, 0));
        playField.add(bf.initBrick(12, 2, 0));

        return playField;
    }
    private static ArrayList<GameEntity> getPlayFieldByIndex1(){

        BrickFactory bf = new BrickFactory();
        ArrayList<GameEntity> playField = new ArrayList<>();

        playField.add(bf.initBrick(0, 4));
        playField.add(bf.initBrick(1, 5, 0));
        playField.add(bf.initBrick(2, 6, 0));
        playField.add(bf.initBrick(3, 7, 4));
        playField.add(bf.initBrick(4, 8, 0));
        playField.add(bf.initBrick(5, 9, 0));
        playField.add(bf.initBrick(6, 10, 2));
        playField.add(bf.initBrick(7, 11, 0));
        playField.add(bf.initBrick(8, 0, 0));
        playField.add(bf.initBrick(9, 2, 3));

        return playField;
    }
    private static ArrayList<GameEntity> getPlayFieldByIndex2(){

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

        return playField;
    }
    private static ArrayList<GameEntity> getPlayFieldByIndex3(){

        BrickFactory bf = new BrickFactory();
        ArrayList<GameEntity> playField = new ArrayList<>();

        playField.add(bf.initBrick(0, 5));
        playField.add(bf.initBrick(1, 5, 0));
        playField.add(bf.initBrick(2, 5, 0));
        playField.add(bf.initBrick(3, 5, 4));
        playField.add(bf.initBrick(4, 5, 0));
        playField.add(bf.initBrick(5, 5, 0));
        playField.add(bf.initBrick(6, 5, 2));
        playField.add(bf.initBrick(7, 5, 0));
        playField.add(bf.initBrick(8, 5, 0));
        playField.add(bf.initBrick(9, 5, 3));

        return playField;
    }
    private static ArrayList<GameEntity> getPlayFieldByIndex4(){

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

        return playField;
    }
    private static ArrayList<GameEntity> getPlayFieldByIndex5(){

        BrickFactory bf = new BrickFactory();
        ArrayList<GameEntity> playField = new ArrayList<>();

        playField.add(bf.initBrick(0, 1));
        playField.add(bf.initBrick(1, 1, 0));
        playField.add(bf.initBrick(2, 1, 0));
        playField.add(bf.initBrick(3, 1, 4));
        playField.add(bf.initBrick(4, 1, 0));
        playField.add(bf.initBrick(5, 1, 0));
        playField.add(bf.initBrick(6, 1, 2));
        playField.add(bf.initBrick(7, 1, 0));
        playField.add(bf.initBrick(8, 1, 0));
        playField.add(bf.initBrick(9, 1, 3));

        return playField;
    }
}
