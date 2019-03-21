import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {

    JPanel panel = new JPanel();
    JButton btn = new JButton("Добавить Шарик");

    public MyFrame() {
        setTitle("*** Мяяяя-чииии-кииии ***");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        setSize(width / 2, height / 2);
        setLocationRelativeTo(null);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        btn.setBounds((int) (width * 0.01), (int) (height * 0.37), (int) (width * 0.47), (int) (height * 0.06));
        btn.setBackground(Color.decode("#199b8cc"));
        btn.setFont(new Font(null, Font.BOLD, 70));
        btn.setFocusPainted(false);
        contentPane.add(btn);
        panel.setBounds((int) (width * 0.01), (int) (height * 0.02), (int) (width * 0.47), (int) (height * 0.34));
        panel.setBackground(Color.decode("#70b8cc"));

        final BallThread b1 = new BallThread(panel);
        final BallThread b2 = new BallThread(panel);
        final BallThread b3 = new BallThread(panel);
        contentPane.add(panel);
        b1.start();
        b2.start();
        b3.start();

        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                new BallThread(panel).start();
            }
        });
    }
}
