package antlr;// Generated from H:/calculator/src/main/resource\FA.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FAParser}.
 */
public interface FAListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FAParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(FAParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(FAParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by the {@code printExpr}
	 * labeled alternative in {@link FAParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterPrintExpr(FAParser.PrintExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code printExpr}
	 * labeled alternative in {@link FAParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitPrintExpr(FAParser.PrintExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assign}
	 * labeled alternative in {@link FAParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterAssign(FAParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assign}
	 * labeled alternative in {@link FAParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitAssign(FAParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code blank}
	 * labeled alternative in {@link FAParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterBlank(FAParser.BlankContext ctx);
	/**
	 * Exit a parse tree produced by the {@code blank}
	 * labeled alternative in {@link FAParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitBlank(FAParser.BlankContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addAndSub}
	 * labeled alternative in {@link FAParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddAndSub(FAParser.AddAndSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addAndSub}
	 * labeled alternative in {@link FAParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddAndSub(FAParser.AddAndSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code id}
	 * labeled alternative in {@link FAParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterId(FAParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code id}
	 * labeled alternative in {@link FAParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitId(FAParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multiAndDivide}
	 * labeled alternative in {@link FAParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMultiAndDivide(FAParser.MultiAndDivideContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multiAndDivide}
	 * labeled alternative in {@link FAParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMultiAndDivide(FAParser.MultiAndDivideContext ctx);
	/**
	 * Enter a parse tree produced by the {@code int}
	 * labeled alternative in {@link FAParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterInt(FAParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code int}
	 * labeled alternative in {@link FAParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitInt(FAParser.IntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parents}
	 * labeled alternative in {@link FAParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParents(FAParser.ParentsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parents}
	 * labeled alternative in {@link FAParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParents(FAParser.ParentsContext ctx);
}