import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class ControlEngine extends JPanel implements Runnable {
    
    InputManager keyH = new InputManager();
    public Thread gameThread;
    boolean isRunning;
    int FPS = 60; 

    GameState currentState = GameState.PLAY;

    
    Player player = new Player(); 

    public ControlEngine() {
        this.setFocusable(true);
        this.addKeyListener(keyH);
    }

    public void startGameThread() {
        isRunning = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        
        if (keyH.shootPressed) {
            currentState = GameState.SHOOT;
        } else {
            currentState = GameState.PLAY;
        }

        
        if (currentState == GameState.PLAY) {
            player.update(keyH);
        } 
        else if (currentState == GameState.SHOOT) {
            // Player is stationary. 
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        Graphics2D g2 = (Graphics2D) g;
        
        
        if (currentState == GameState.SHOOT) {
            g2.setColor(Color.RED);
        } else {
            g2.setColor(Color.WHITE);
        }
        
        
        player.draw(g2); 
        
        g2.dispose();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS; 
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (isRunning) {
            update();
            repaint();
            
            try {
                double remainingTime = (nextDrawTime - System.nanoTime()) / 1000000; 
                if (remainingTime < 0) remainingTime = 0;
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}