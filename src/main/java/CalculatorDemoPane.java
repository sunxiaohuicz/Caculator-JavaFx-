import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by XD.Wang on 2017/9/6.
 */
// 1. Application的子类代表一个JavaFX程序
public class CalculatorDemoPane extends Application {

    // 2. Application的子类必须实现这个方法，作为JavaFX程序的入口，一切从这里开始
    @Override
    public void start(Stage primaryStage) throws Exception {
        // 2.1 所谓JavaFX程序的界面部分开发，无非就是把各种各样的UI组件先写出来，然后把他们放整齐
        // 2.2 首先，我们先设计三个按钮，按钮通过javafx.scene.control.Button实现（注意不要和awt的Button混淆了)
        // 2.3 Button的构造函数可以传入一个String类型的对象，代表着这个Button显示的文案
        Button btn1 = new Button("清零");
        Button btn2 = new Button("退格");
        Button btn3 = new Button("=");
        // 2.4 在设计10个数字按钮
        List<Button> numBtnList = new ArrayList<Button>();
        for (int i = 0; i < 10; i++) {
            Button btn = new Button(i + "");
            numBtnList.add(btn);
        }
        // 2.5 为用户创建一个输入框
        Label label1 = new Label("请输入:");
        TextField textField = new TextField();
        // 2.6 按钮、输入框等UI控件设计完了后，我们需要把他们放整齐，Pane类的子类可以用来进行布局，让你的控件整齐，此处我们使用Pane类的子类GridPane
        // 2.7 GridPane把整个页面分为很多小格子，可以根据需要把我们写好的UI组件放到小格子里去
        // 2.8 我们把页面分为横4格，纵4格，并居中对齐
        GridPane pane = new GridPane();
        pane.setHgap(4);
        pane.setVgap(4);
        pane.setAlignment(Pos.CENTER);
        // 2.9 把三个功能按钮放在最下面的三格里，add方法的后四个参数是坐标和横跨属性，所谓横跨属性，就是你这个控件占几行或几列add（控件, x坐标，y坐标，横跨几列，横跨几行）
        pane.add(btn1, 4, 1);
        pane.add(btn2, 4, 2);
        pane.add(btn3, 4, 3);
        // 2.10 然后把数字按钮放到规定的地方
        Iterator<Button> numBtnListIter = numBtnList.iterator();
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                pane.add(numBtnListIter.next(), i, j);
            }
        }
        // 2.11 把输入框放到规定的地方
        pane.add(label1, 0, 0);
        pane.add(textField, 1, 0, 4, 1);
        // 3. 页面布局做好了，接下来为我们的界面添加些附加信息，Scene就承载着这些附加信息，此处我们不需要添加什么附加信息
        Scene display = new Scene(pane);
        primaryStage.setScene(display);
        // 4. Stage代表着我们做的这个界面，我们为它起个名字，并设置默认它不最大化，在设置下大小
        primaryStage.setTitle("calculator demo");
        primaryStage.setMaximized(false);
        primaryStage.setMinHeight(300);
        primaryStage.setMinWidth(300);
        primaryStage.show();
    }

    // 1.1 当你的JavaFX程序写好后，记得用main方法启动它
    public static void main(String[] args) {
        // 1.2 launch方法用来启动JavaFX程序
        launch(CalculatorDemoPane.class);
    }

}
