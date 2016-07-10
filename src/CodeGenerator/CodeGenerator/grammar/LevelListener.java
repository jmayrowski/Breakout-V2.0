// Generated from D:/Programmierprojekte/Breakout V2.0/src/CodeGenerator/grammar\Level.g4 by ANTLR 4.5.3
package CodeGenerator.grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LevelParser}.
 */
public interface LevelListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LevelParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(LevelParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link LevelParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(LevelParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link LevelParser#tupel}.
	 * @param ctx the parse tree
	 */
	void enterTupel(LevelParser.TupelContext ctx);
	/**
	 * Exit a parse tree produced by {@link LevelParser#tupel}.
	 * @param ctx the parse tree
	 */
	void exitTupel(LevelParser.TupelContext ctx);
	/**
	 * Enter a parse tree produced by {@link LevelParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(LevelParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link LevelParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(LevelParser.ValueContext ctx);
}