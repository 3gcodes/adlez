import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private int x = 100;
    private int y = 30;
    private int dx = 0;
    private int dy = 0;
    private int velocityX = 1;
    private int velocityY = 1;
    private int maxDx = 3;
    private int maxDy = 3;
    private ThingManager thingManager;

    public GamePanel() {
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setRequestFocusEnabled(true);
        thingManager = new ThingManager();

        Player player = new Player();
        player.setColor(Color.red);
        player.setOrigin(new Point(50, 50));

        thingManager.addThing(player);

        Timer timer = new Timer(25, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        thingManager.getThings().forEach(thing -> {
            thing.move(new Point(x, y));
            thing.paintComponent(g2d);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x += dx;
        y += dy;
        if (x >= 790) {
            x = 10;
        }
        if (x <= 0) {
            x = 785;
        }
        if (y >= 590) {
            y = 10;
        }
        if (y <= 0) {
            y = 585;
        }
        this.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // ignore
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_D:
                if (dx < maxDx) {
                    dx += velocityX;
                }
                break;
            case KeyEvent.VK_A:
                if (dx > -maxDx) {
                    dx -= velocityX;
                }
                break;
            case KeyEvent.VK_W:
                if (dy > -maxDy) {
                    dy -= velocityY;
                }
                break;
            case KeyEvent.VK_S:
                if (dy < maxDy) {
                    dy += velocityY;
                }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // ignore
    }
}
