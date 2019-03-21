import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MyFrame frame = new MyFrame();
                frame.setVisible(true);
            }
        });
    }
}