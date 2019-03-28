import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Player implements Thing {

    private Color color;
    private Point origin;
    private int x = 0;
    private int y = 0;

    @Override
    public void setColor(Color c) {
        this.color = c;
    }

    @Override
    public void setOrigin(Point origin) {
        this.origin = origin;
    }

    @Override
    public void move(Point toPosition) {
        this.origin = toPosition;
    }

    @Override
    public void paintComponent(Graphics2D g2d) {
        x = origin.x;
        y = origin.y;
        g2d.setColor(this.color);
        g2d.fillOval(x, y, 10, 10);
    }
}
