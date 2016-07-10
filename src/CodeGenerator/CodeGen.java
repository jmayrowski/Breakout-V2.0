package CodeGenerator;


import CodeGenerator.grammar.LevelBaseListener;
import CodeGenerator.grammar.LevelLexer;
import CodeGenerator.grammar.LevelParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Jasmin on 10.07.2016.
 */
public class CodeGen extends LevelBaseListener {
    public static void main(String[] args) throws IOException {

        LevelLexer lexer = new LevelLexer(new ANTLRInputStream(new FileReader("D:\\Programmierprojekte\\Breakout V2.0\\src\\CodeGenerator\\csv\\level_one.csv")));

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        LevelParser parser = new LevelParser(tokens);

        LevelParser.FileContext fileContext = parser.file();

        ParseTreeWalker walker = new ParseTreeWalker();

        LevelBaseListener listener = new CodeGen();

        walker.walk(listener,fileContext);
    }

    @Override
    public void exitFile(LevelParser.FileContext ctx) {
        System.out.println("Wenn kein Fehler auftaucht, dann wurde das File korrekt geparsed.");
        System.out.println(ctx.getText());
    }

}
