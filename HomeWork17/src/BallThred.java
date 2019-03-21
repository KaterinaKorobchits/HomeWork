import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

class BallThread implements ActionListener {

    private Timer timer;
    private JPanel panel;
    private int posX, posY;
    private final int BALL_SIZE = 80;
    private double alpha;
    private int SPEED;
    final Color[] colors = {Color.BLACK, Color.WHITE, Color.GREEN, Color.ORANGE, Color.YELLOW, Color.BLUE, Color.MAGENTA, Color.RED};
    private Color color;

    BallThread(JPanel panel) {
        this.panel = panel;
        posX = (int) ((panel.getWidth() - BALL_SIZE) * Math.random());
        posY = (int) ((panel.getHeight() - BALL_SIZE) * Math.random());
        alpha = Math.random() * 10;
        SPEED = new Random().nextInt(20);
        timer = new Timer(31, this);
        color = colors[new Random().nextInt(colors.length)];
    }

    public void start() {
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        delete(panel.getGraphics());

        posX += (int) (SPEED * Math.cos(alpha));
        posY += (int) (SPEED * Math.sin(alpha));

        if (posX >= panel.getWidth() - BALL_SIZE)
            alpha = alpha + Math.PI - 2 * alpha;
        else if (posX <= 0)
            alpha = Math.PI - alpha;
        if (posY >= panel.getHeight() - BALL_SIZE )
            alpha = -alpha;
        else if (posY <= 0)
            alpha = -alpha;
        repaint(panel.getGraphics());
    }

    protected void repaint(Graphics g) {
        g.setColor(color);
        g.fillArc(posX, posY, BALL_SIZE, BALL_SIZE, 0, 360);
    }

    protected void delete(Graphics g) {
        g.setColor(panel.getBackground());
        g.fillArc(posX, posY, BALL_SIZE, BALL_SIZE, 0, 360);
    }

}




