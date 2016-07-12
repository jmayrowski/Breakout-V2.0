package CodeGenerator;


import CodeGenerator.CodeGenerator.grammar.LevelBaseListener;
import CodeGenerator.grammar.LevelLexer;
import CodeGenerator.grammar.LevelParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Jasmin on 10.07.2016.
 */
public class CodeGen extends LevelBaseListener {

    public int counter = 0;
    public String Tupelwert;
    public static int numberBricks;
    /*static String inpath = "D:\\Programmierprojekte\\Breakout V2.0\\src\\CodeGenerator\\csv\\level_one.csv"; // Pfad
    static String outpath = "D:\\Programmierprojekte\\Breakout V2.0\\src\\CodeGenerator\\out\\PlayField.java"; // Pfad*/

    static String inpath = "csv\\level_one.csv"; // Pfad
    static String outpath = "out\\PlayField.java"; // Pfad
    public String output = "";



    public static void main(String[] args) throws IOException {



      /*  String pfad = file.getAbsolutePath();

        System.out.println(file.toString() + " successfully generated");

        System.out.println(pfad);*/

        File inputFile = new File(inpath);
        System.out.println(inputFile.getAbsolutePath());


        LevelLexer lexer = new LevelLexer(new ANTLRInputStream(new FileReader(inputFile)));

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        LevelParser parser = new LevelParser(tokens);

        LevelParser.FileContext fileContext = parser.file();

        ParseTreeWalker walker = new ParseTreeWalker();

        LevelBaseListener listener = new CodeGen();

        walker.walk(listener,fileContext);
        FileWriter writer = new FileWriter(outpath);
        new File (outpath).createNewFile();


        System.out.print("Anzahl der bricks " + ((CodeGen)listener).getCount());



        writer.write(((CodeGen)listener).getOutput());
        writer.flush();
        writer.close();

    }

    public int getCount(){
        return counter;
    }

    public String getOutput(){

        return output;
    }

    @Override
    public void enterFile(LevelParser.FileContext ctx) {
        output += "package Breakout;\n" +
                "\n" +
                "import com.almasb.fxgl.entity.GameEntity;\n" +
                "\n" +
                "import java.util.ArrayList;\n" +
                "\n" +
                "/**\n" +
                " * Created by Jasmin on 12.07.2016.\n" +
                " */\n" +
                "public class PlayField {\n" +
                "\n" +
                "\tpublic ArrayList<GameEntity> getPlayField(){\n" +
                "\n" +
                "\t\tBrickFactory bf = new BrickFactory();\n\n " +
                "\t\tArrayList<GameEntity> playField = new ArrayList<>();\n"
        ;
    }


    @Override
    public void exitFile(LevelParser.FileContext ctx) {
        //  System.out.println("/**Wenn kein Fehler auftaucht, dann wurde das File korrekt geparsed.*/");
        output += "\t\treturn playField;\n" +
                "\t}\n" +
                "}\n";
    }

    @Override
    public void visitTerminal(TerminalNode node) {

    }


    @Override
    public void enterTupel(LevelParser.TupelContext ctx) {
        System.out.print(ctx.getText());
        String[] tmp = ctx.getText().split("\\)");
        output += "\t\tplayField.add(bf.initBrick"+tmp[0]+"));\n ";
        counter++;
    }

}