import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public interface Thing {
    int x = 0;
    int y = 0;
    int dx = 0;
    int dy = 0;

    void setColor(Color c);
    void setOrigin(Point origin);
    void move(Point toPosition);
    void paintComponent(Graphics2D g2d);
}
