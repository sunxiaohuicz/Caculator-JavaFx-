package demo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by XD.Wang on 2017/9/8.
 * 栈消除算法
 */
public class Calculator {

    private static final String EQUATION = "";

    private static final Stack<String> ANALYSIS_STACK = new Stack<>();

    private static final String[] OPER = {"+", "-", "*", "/"};

    private static final HashMap<String, Integer> OPER_PRIORITY = new HashMap<String, Integer>() {{
        put("*", 2);
        put("/", 2);
        put("+", 1);
        put("-", 1);
        put("", 0);
    }};

    public static void main(String[] args) {
        System.out.print(calExpr("1+(1-9)*2"));
        System.out.print(calExpr("9-(20-9)/5"));
    }

    // 根据输入的算式计算
    private static String calExpr(String expr) {
        return suffixToValue(getRPN(getValidExpr(expr)));
    }

    // 输入预处理
    private static String getValidExpr(String expr) {
        return expr.trim();
    }

    /**
     * 将中缀表达式转换成后缀表达式
     * eg：中缀表达式8+(9-1）*8+7/2
     * 后缀表达式8 9 1 - 8 * + 7 2 / +，元素之间之间用空格分隔。
     * 从左到右遍历中缀表达式的每一个数字和运算符
     * 如果数字就输出（即存入后缀表达式）
     * 如果是右括号，则弹出左括号之前的运算符
     * 如果优先级低于栈顶运算符，则弹出栈顶运算符，并将当前运算符进栈
     * 遍历结束后，将栈则剩余运算符弹出。
     *
     * @param expression 中缀表达式
     * @return 后缀表达式
     */
    private static String getRPN(String expression) {
        ANALYSIS_STACK.clear();
        StringBuilder inf = new StringBuilder(expression);
        StringBuilder suf = new StringBuilder();
        String element, tmp; // 中缀表达式的数字或者运算符

        while (inf.length() > 0) {
            element = popNextElement(inf);

            if (isNum(element)) { // 是数字则输出
                suf.append(element).append(" ");
            } else if (")".equals(element)) { // 右括号则将左括号之前的内容全弹出
                tmp = ANALYSIS_STACK.pop();
                while (!"(".equals(tmp)) {
                    suf.append(tmp).append(" ");
                    tmp = ANALYSIS_STACK.pop();
                }
            } else if ("(".equals(element) || OPER_PRIORITY.get(element) >= OPER_PRIORITY.get(getTopOperator())) {
                ANALYSIS_STACK.push(element);
            } else { // 优先级小于栈顶运算符，则弹出
                tmp = ANALYSIS_STACK.pop();
                suf.append(tmp).append(" ").append(element).append(" ");
            }
        }

        // 把栈中剩余运算符都弹出
        while (ANALYSIS_STACK.size() > 0) {
            suf.append(ANALYSIS_STACK.pop()).append(" ");
        }

        return suf.toString();
    }

    /**
     * 根据后缀表达式算出结果
     * eg：中缀表达式8+(9-1）*8+7/2
     * 后缀表达式8 9 1 - 8 * + 7 2 / +，元素之间之间用空格分隔。
     * 从左到右遍历后缀表达式
     * 遇到数字就进栈
     * 遇到符号，就将栈顶的两个数字出栈运算，运算结果进栈，直到获得最终结果。
     * -----------目前只支持整数，由于整数相除会出现浮点数，这里用String作为返回值--------
     *
     * @param expression 后缀表达式
     * @return 结果
     */
    private static String suffixToValue(String expression) {
        // 已经用空格分隔，直接分割
        String[] suffix = expression.split(" ");
        ANALYSIS_STACK.clear();
        double num1, num2; // 注意次序，num2在栈顶，整数运算结果也可能是double
        String tmp;

        for (String aSuffix : suffix) {
            if (isNum(aSuffix)) { // 数字
                ANALYSIS_STACK.push(aSuffix);
            } else { // 是操作符
                num2 = Double.parseDouble(ANALYSIS_STACK.pop());
                num1 = Double.parseDouble(ANALYSIS_STACK.pop());
                tmp = calculate(num1, num2, aSuffix);
                if ("zeroDivided".equals(tmp)) {
                    throw new ArithmeticException("被除数不能为0");
                } else {
                    ANALYSIS_STACK.push(tmp);
                }
            }
        }

        // 最终结果也压在栈中，取出即可
        return ANALYSIS_STACK.pop();
    }

    /**
     * 目前只支持整数，但是整数运算结果也可能是double，故这里传入的参数用double。
     *
     * @param num1     第一个数，在前
     * @param num2     第二个数，在后
     * @param operator 运算符
     * @return 运算结果，除数为0则返回Error
     */
    private static String calculate(double num1, double num2, String operator) {
        double result = 0;
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 == 0) {
                    return "zeroDivided";
                }
                result = 1.0 * num1 / num2;
                break;
            default:
                break;
        }
        return String.valueOf(result);
    }

    /**
     * 检测字符是不是数字
     * 浮点数其实可以写零宽断言，但是
     *
     * @param c 待检测字符
     * @return 检测结果
     */
    private static boolean isNum(char c) {
        return (c >= '0' && c <= '9') || c == '.';
    }

    /**
     * 检测字符串是不是数字
     *
     * @param str 待检测字符串
     * @return 检测结果
     */
    private static boolean isNum(String str) {
        Pattern pattern = Pattern.compile("\\d+\\.?\\d?");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 获取栈顶运算符
     *
     * @return 如果栈中无运算符，则返回空字符串
     */
    private static String getTopOperator() {
        String tmp;
        for (int i = ANALYSIS_STACK.size() - 1; i >= 0; i--) {
            tmp = ANALYSIS_STACK.get(i);
            if ("(".equals(tmp)) {
                break;
            } else if (isOperator(tmp)) {
                return tmp;
            }
        }
        return "";
    }

    /**
     * 检测一个字符是不是运算符
     * 栈中不是运算符就是括号
     *
     * @param str 待检测字符，由于可能有多位数字字符串，这里用的是String
     * @return 检测结果
     */
    private static boolean isOperator(String str) {
        return Arrays.asList(OPER).contains(str);
    }

    /**
     * 获取表达式的下一个运算符或数字，同时从表达式中删除该元素
     *
     * @param expression 表达式
     * @return
     */
    private static String popNextElement(StringBuilder expression) {
        StringBuilder result = new StringBuilder();
        char c = expression.charAt(0);
        expression.deleteCharAt(0);
        result.append(c);

        if (isNum(c)) {
            // 如果第一次取到的是数字，则继续检查
            while (expression.length() > 0 && isNum(c = expression.charAt(0))) {
                expression.deleteCharAt(0);
                result.append(c);
            }
        }

        return result.toString();
    }

}
