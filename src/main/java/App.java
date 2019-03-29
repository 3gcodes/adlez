import javax.swing.*;
import java.awt.*;

public class App {

    private GamePanel gamePanel;
    private int fps = 60;
    private int frameCount = 0;

    public App() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(new Dimension(800, 600));

        gamePanel = new GamePanel();

        window.setContentPane(gamePanel);

        window.setVisible(true);

        runGameLoop();
    }

    public void runGameLoop()
    {
        Thread loop = new Thread(this::gameLoop);
        loop.start();
    }

    private void gameLoop() {

        final double GAME_HERTZ = 30.0;
        //Calculate how many ns each frame should take for our target game hertz.
        final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
        //At the very most we will update the game this many times before a new render.
        final int MAX_UPDATES_BEFORE_RENDER = 5;

        double lastUpdateTime = System.nanoTime();

        double lastRenderTime = System.nanoTime();

        //If we are able to get as high as this FPS, don't render again.
        final double TARGET_FPS = 60;
        final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;

        //Simple way of finding FPS.
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);


        while(true) {

            double now = System.nanoTime();
            int updateCount = 0;

            while( now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER )
            {
                updateGame();
                lastUpdateTime += TIME_BETWEEN_UPDATES;
                updateCount++;
            }

            if ( now - lastUpdateTime > TIME_BETWEEN_UPDATES)
            {
                lastUpdateTime = now - TIME_BETWEEN_UPDATES;
            }

            //Render. To do so, we need to calculate interpolation for a smooth render.
            float interpolation = Math.min(1.0f, (float) ((now - lastUpdateTime) / TIME_BETWEEN_UPDATES) );
            drawGame(interpolation);
            lastRenderTime = now;

            //Update the frames we got.
            int thisSecond = (int) (lastUpdateTime / 1000000000);
            if (thisSecond > lastSecondTime)
            {
                System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
                fps = frameCount;
                frameCount = 0;
                lastSecondTime = thisSecond;
            }

            //Yield until it has been at least the target time between renders. This saves the CPU from hogging.
            while ( now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BETWEEN_UPDATES)
            {
                Thread.yield();

                //This stops the app from consuming all your CPU. It makes this slightly less accurate, but is worth it.
                //You can remove this line and it will still work (better), your CPU just climbs on certain OSes.
                //FYI on some OS's this can cause pretty bad stuttering. Scroll down and have a look at different peoples' solutions to this.
                try {Thread.sleep(1);} catch(Exception e) {}

                now = System.nanoTime();
            }
        }

    }

    private void updateGame() {
        gamePanel.update();
    }

    private void drawGame(float interpolation) {
        gamePanel.setInterpolation(interpolation);
        gamePanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::new);

    }
}
