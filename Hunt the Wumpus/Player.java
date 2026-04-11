import java.awt.Graphics2D;

public class Player {
    float x, y;
    float velX, velY;
    float speed; 

    public Player() {
        this.x = 100f;
        this.y = 100f;
        this.speed = 4f;
    }

    public void update(InputManager keyH) {
        velX = 0;
        velY = 0;

        if (keyH.upPressed) {
            velY -= speed;
        }
        if (keyH.downPressed) {
            velY += speed;
        }
        if (keyH.leftPressed) {
            velX -= speed;
        }
        if (keyH.rightPressed) {
            velX += speed;
        }

        x += velX;
        y += velY;
    }

    
    public void draw(Graphics2D g2) {
        g2.fillRect((int)x, (int)y, 48, 48);
    }
}