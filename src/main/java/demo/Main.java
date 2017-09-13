package demo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by XD.Wang on 2017/9/7.
 * 界面
 */
public class Main extends Application {

    private static TextField inputField;
    private static TextField arithmeticField;
    private static boolean INIT = false;

    private static final String PLUS = "+";
    private static final String MINOR = "-";
    private static final String MULTI = "x";
    private static final String DIVIDE = "÷";
    private static final String EQUAL = "=";

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = getMainPane();
        Scene display = new Scene(pane);
        primaryStage.setScene(display);
        primaryStage.setTitle("计算器");
        primaryStage.setMaximized(false);
        primaryStage.setMinHeight(280);
        primaryStage.setMinWidth(200);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(Main.class);
    }

    private static final int MAX_COL = 4;
    private static final int MAX_ROW = 7;
    private static final int OPER_BTN_INDEX = 2;

    private static Pane getMainPane() {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(MAX_COL);
        pane.setVgap(MAX_ROW);
        setInputAndLabel(pane);
        setOperBtn(pane);
        setNumberBtn(pane);
        setFuncBtn(pane);
        return pane;
    }

    private static void setNumberBtn(GridPane pane) {
        List<Button> numBtnList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            Button btn = new Button(i + "");
            btn.setOnAction((final ActionEvent e) -> {
                appendEquation(((Button) e.getSource()).getText());
            });
            numBtnList.add(btn);
        }
        Iterator<Button> numBtnListIter = numBtnList.iterator();
        for (int i = 3; i < MAX_ROW - 1; i++) {
            for (int j = 1; j < MAX_COL; j++) {
                if (numBtnListIter.hasNext()) pane.add(numBtnListIter.next(), j, i);
            }
        }
        Button zeroBtn = new Button("0");
        zeroBtn.setMinHeight(55);
        pane.add(zeroBtn, 4, OPER_BTN_INDEX + 2, 1, 2);
    }

    private static void setOperBtn(GridPane pane) {
        Button plusBtn = new Button(PLUS);
        plusBtn.setOnAction((final ActionEvent e) -> {
            appendEquation(((Button) e.getSource()).getText());
        });
        Button minorBtn = new Button(MINOR);
        minorBtn.setOnAction((final ActionEvent e) -> {
            appendEquation(((Button) e.getSource()).getText());
        });
        Button multiBtn = new Button(MULTI);
        multiBtn.setOnAction((final ActionEvent e) -> {
            appendEquation(((Button) e.getSource()).getText());
        });
        Button divBtn = new Button(DIVIDE);
        divBtn.setOnAction((final ActionEvent e) -> {
            appendEquation(((Button) e.getSource()).getText());
        });
        Button equalBtn = new Button(EQUAL);
        equalBtn.setOnAction((final ActionEvent e) -> {
            appendEquation(((Button) e.getSource()).getText());
        });

        pane.add(equalBtn, MAX_COL, 3);
        pane.add(plusBtn, 1, OPER_BTN_INDEX);
        pane.add(minorBtn, 2, OPER_BTN_INDEX);
        pane.add(multiBtn, 3, OPER_BTN_INDEX);
        pane.add(divBtn, 4, OPER_BTN_INDEX);
    }

    private static void setFuncBtn(GridPane pane) {
        Button toZeroBtn = new Button("清零");
        toZeroBtn.setOnAction((final ActionEvent e) -> {
            initCalculator();
        });
        toZeroBtn.setMinWidth(55);
        Button backspaceBtn = new Button("退格");
        backspaceBtn.setOnAction((final ActionEvent e) -> {
            backSpace();
        });
        backspaceBtn.setMinWidth(55);

        pane.add(toZeroBtn, 1, MAX_ROW, 2, 1);
        pane.add(backspaceBtn, 3, MAX_ROW, 2, 1);
    }

    private static void setInputAndLabel(GridPane pane) {
        Label inputLabel = new Label("输入:");
        TextField inputField = new TextField();
        inputField.setDisable(true);
        Label arithmeticLabel = new Label("算式:");
        TextField arithmeticField = new TextField();
        arithmeticField.setDisable(true);
        pane.add(arithmeticLabel, 0, 0);
        pane.add(arithmeticField, 1, 0, 4, 1);
        pane.add(inputLabel, 0, 1);
        pane.add(inputField, 1, 1, 4, 1);
        Main.inputField = inputField;
        Main.arithmeticField = arithmeticField;
    }

    private static void appendEquation(String token) {
        if (INIT) {
            initCalculator();
        }
        if (EQUAL.equals(token)) {
            String expr = Calculator.EQUATION.toString();
            System.out.println("[" + new Date() + "] 待计算的表达式：" + expr);
            String result = Calculator.calExpr(expr);
            arithmeticField.setText(Calculator.EQUATION.append(EQUAL).append(result).toString());
            inputField.setText(result);
            INIT = true;
            return;
        }
        if (!Calculator.isNum(token) && StringUtils.isEmpty(inputField.getText())) {
            Calculator.EQUATION.delete(Calculator.EQUATION.length() - 1, Calculator.EQUATION.length());
            Calculator.EQUATION.append(token);
        } else {
            Calculator.EQUATION.append(token);
        }
        inputField.setText(Calculator.isNum(token) ? inputField.getText() + token : "");
        arithmeticField.setText(Calculator.EQUATION.toString());
    }

    private static void initCalculator() {
        Calculator.EQUATION.delete(0, Calculator.EQUATION.length());
        inputField.setText("");
        arithmeticField.setText("");
        INIT = false;
    }

    private static void backSpace() {
        String text = inputField.getText();
        if (StringUtils.isNoneEmpty(text) && !INIT) {
            inputField.setText(text.substring(0, text.length() - 1));
            arithmeticField.setText(Calculator.EQUATION.substring(0, Calculator.EQUATION.length() - 1));
            Calculator.EQUATION.delete(Calculator.EQUATION.length() - 1, Calculator.EQUATION.length());
        }
    }

}
