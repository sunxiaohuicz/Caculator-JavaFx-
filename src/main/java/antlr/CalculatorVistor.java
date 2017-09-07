package antlr;

/**
 * Created by XD.Wang on 2017/9/7.
 */
public class CalculatorVistor extends FABaseVisitor<Integer>  {

    @Override
    public Integer visitAssign(FAParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        int value = visit(ctx.expr());
        Calculator.container.put(id, value); // store it inour memory
        return value;
    }

}
