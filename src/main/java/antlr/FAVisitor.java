package antlr;// Generated from H:/calculator/src/main/resource\FA.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link FAParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface FAVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link FAParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(FAParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printExpr}
	 * labeled alternative in {@link FAParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintExpr(FAParser.PrintExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assign}
	 * labeled alternative in {@link FAParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(FAParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blank}
	 * labeled alternative in {@link FAParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlank(FAParser.BlankContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addAndSub}
	 * labeled alternative in {@link FAParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddAndSub(FAParser.AddAndSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code id}
	 * labeled alternative in {@link FAParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(FAParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multiAndDivide}
	 * labeled alternative in {@link FAParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiAndDivide(FAParser.MultiAndDivideContext ctx);
	/**
	 * Visit a parse tree produced by the {@code int}
	 * labeled alternative in {@link FAParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(FAParser.IntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parents}
	 * labeled alternative in {@link FAParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParents(FAParser.ParentsContext ctx);
}