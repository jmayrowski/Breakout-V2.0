package CodeGenerator;


import CodeGenerator.grammar.LevelBaseListener;
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

    public static int counter = 0;
    public String Tupelwert;
    public static int numberBricks;
    static File file = new File("out\\PlayField.java"); // Pfad



    public static void main(String[] args) throws IOException {



        String pfad = file.getAbsolutePath();

        System.out.println(file.toString() + " successfully generated");

        System.out.println(pfad);

        File inputFile = new File("csv\\level_one.csv");
        System.out.println(inputFile.getAbsolutePath());


        LevelLexer lexer = new LevelLexer(new ANTLRInputStream(new FileReader(inputFile)));

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        LevelParser parser = new LevelParser(tokens);

        LevelParser.FileContext fileContext = parser.file();

        ParseTreeWalker walker = new ParseTreeWalker();

        LevelBaseListener listener = new CodeGen();

        walker.walk(listener,fileContext);
        FileWriter writer = new FileWriter(file);
        file.createNewFile();

        for (int i = 0; i < numberBricks; i++){
            writer.write("Anzahl der Bricks " + counter);
        }


        writer.flush();
        writer.close();

    }




    @Override
    public void exitFile(LevelParser.FileContext ctx) {
        System.out.println("/**Wenn kein Fehler auftaucht, dann wurde das File korrekt geparsed.*/");

    }

    @Override
    public void visitTerminal(TerminalNode node) {

    }


}
