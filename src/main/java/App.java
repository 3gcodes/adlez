import javax.swing.*;
import java.awt.*;

public class App {

    public App() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(new Dimension(800, 600));

        window.setContentPane(new GamePanel());


        window.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::new);

    }
}
