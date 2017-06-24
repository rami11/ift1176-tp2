import view.MainView;

import javax.swing.*;

/**
 * Created by rsn on 2017-06-09.
 */
public class TP2 extends JFrame {

    public static void main(String[] args) {
        JFrame mainView = MainView.getInstance();
        ((MainView) mainView).showView();
    }
}
