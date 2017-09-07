package demo;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by XD.Wang on 2017/9/7.
 * 组件相关
 */
public class UI {

    private static final int MAX_COL = 4;
    private static final int MAX_ROW = 6;
    private static final int FUNC_BTN_INDEX = 3;
    private static final int OPER_BTN_INDEX = 2;

    private static final String EQUATION = "";

    static Pane getMainPane() {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(MAX_COL);
        pane.setVgap(MAX_ROW);

        Button btn1 = new Button("清零");
        Button btn2 = new Button("退格");
        List<Button> numBtnList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Button btn = new Button(i + "");
            numBtnList.add(btn);
        }
        pane.add(btn1, MAX_COL, FUNC_BTN_INDEX);
        pane.add(btn2, MAX_COL, FUNC_BTN_INDEX + 1);
        Iterator<Button> numBtnListIter = numBtnList.iterator();
        for (int i = FUNC_BTN_INDEX; i < MAX_ROW; i++) {
            for (int j = 1; j < MAX_COL; j++) {
                if (numBtnListIter.hasNext()) pane.add(numBtnListIter.next(), j, i);
            }
        }

        Label inputLabel = new Label("当前输入:");
        TextField inputField = new TextField();
        inputField.setDisable(true);
        Label arithmeticLabel = new Label("算式:");
        TextField arithmeticField = new TextField();
        arithmeticField.setDisable(true);
        pane.add(arithmeticLabel, 0, 0);
        pane.add(arithmeticField, 1, 0, 4, 1);
        pane.add(inputLabel, 0, 1);
        pane.add(inputField, 1, 1, 4, 1);

        Button plusBtn = new Button("+");
        Button minorBtn = new Button("-");
        Button multiBtn = new Button("x");
        Button divBtn = new Button("÷");
        Button equalBtn = new Button("=");
        pane.add(equalBtn, MAX_COL, FUNC_BTN_INDEX + 2);
        pane.add(plusBtn, 1, OPER_BTN_INDEX);
        pane.add(minorBtn, 2, OPER_BTN_INDEX);
        pane.add(multiBtn, 3, OPER_BTN_INDEX);
        pane.add(divBtn, 4, OPER_BTN_INDEX);

        return pane;
    }

}
