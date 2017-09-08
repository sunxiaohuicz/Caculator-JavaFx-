package demo;

import java.util.Stack;

/**
 * Created by XD.Wang on 2017/9/8.
 */
public class Calculator {

    private static final String EQUATION = "";

    private static final Stack<String> ANALYSIS_STACK = new Stack<>();

    private static final String[] OPER = {"+", "-", "*", "/"};

    public static String calExpr(String expr) {
    }

    public static String getRPN(char[] expr) {
        ANALYSIS_STACK.clear();
        StringBuilder sb = new StringBuilder();
        for (char s : expr) {
            if()
        }
    }

    private static String popNextElement(StringBuilder expression) {
        StringBuilder result = new StringBuilder();
        char c = expression.charAt(0);
        expression.deleteCharAt(0);
        result.append(c);

        if (isNum(c)) {
            // 如果第一次取到的是数字，则继续检查
            while ( expression.length() >0 && isNum(c = expression.charAt(0))) {
                expression.deleteCharAt(0);
                result.append(c);
            }
        }

        return result.toString();
    }


}
