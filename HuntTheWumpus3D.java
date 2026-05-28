import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;

public class HuntTheWumpus3D extends JPanel implements ActionListener {
    
    class SATQuestion {
        String question;
        String[] options;
        int correctIndex;

        SATQuestion(String q, String[] o, int c) {
            this.question = q;
            this.options = o;
            this.correctIndex = c;
        }
    }

    private ArrayList<SATQuestion> triviaBank = new ArrayList<>();
    
    private void initTrivia() {
        triviaBank.add(new SATQuestion("Discriminant: x² + cx + 16 = 0 has exactly one solution. If c > 0, what is c?", new String[]{"4", "8", "16", "32"}, 1));
        triviaBank.add(new SATQuestion("What is the sum of the roots of 3x² - 12x + 7 = 0?", new String[]{"-4", "4", "7/3", "12"}, 1));
        triviaBank.add(new SATQuestion("Vertex of parabola y = 2(x-3)² + 4?", new String[]{"(-3, 4)", "(3, 4)", "(3, -4)", "(-3, -4)"}, 1));
        triviaBank.add(new SATQuestion("Evaluate i^45 where i = √(-1).", new String[]{"1", "-1", "i", "-i"}, 2));
        triviaBank.add(new SATQuestion("f(x) = x² - 1, g(x) = 2x. Find f(g(3)).", new String[]{"11", "17", "35", "36"}, 2));
        triviaBank.add(new SATQuestion("Circle eq: x² + y² - 6x + 8y = 0. What is the area?", new String[]{"10π", "25π", "50π", "100π"}, 1));
        triviaBank.add(new SATQuestion("If sin(3a) = cos(2a), what is the value of a in degrees?", new String[]{"18", "30", "45", "90"}, 0));
        triviaBank.add(new SATQuestion("Two similar triangles have side ratio 1:3. What is their area ratio?", new String[]{"1:3", "1:6", "1:9", "1:27"}, 2));
        triviaBank.add(new SATQuestion("Interior angle sum of an octagon?", new String[]{"720", "900", "1080", "1440"}, 2));
        triviaBank.add(new SATQuestion("If a cylinder and cone have the same radius and height, what is the ratio of cone to cylinder volume?", new String[]{"1:2", "1:3", "3:1", "1:4"}, 1));
        triviaBank.add(new SATQuestion("System: 2x+3y=12 and 4x+6y=c. For infinite solutions, c = ?", new String[]{"12", "18", "24", "36"}, 2));
        triviaBank.add(new SATQuestion("Which describes a system of equations with no solutions?", new String[]{"Intersecting", "Parallel", "Same line", "Perpendicular"}, 1));
        triviaBank.add(new SATQuestion("What is the slope of a line perpendicular to y = (-2/3)x + 4?", new String[]{"-2/3", "2/3", "-3/2", "3/2"}, 3));
        triviaBank.add(new SATQuestion("Solve for x: |2x - 1| = -5", new String[]{"-2", "3", "Both", "No solution"}, 3));
        triviaBank.add(new SATQuestion("What is the result of (a+b=10) and (a-b=4) applied to a² - b²?", new String[]{"14", "40", "6", "100"}, 1));
        triviaBank.add(new SATQuestion("If x is 150% of y, then y is what percent of x?", new String[]{"33.3%", "50%", "66.7%", "75%"}, 2));
        triviaBank.add(new SATQuestion("y = 10(0.5)^(t/3). What is the half-life?", new String[]{"0.5", "1", "3", "10"}, 2));
        triviaBank.add(new SATQuestion("Probability of rolling a sum of 7 with two six-sided dice?", new String[]{"1/6", "1/7", "1/12", "1/36"}, 0));
        triviaBank.add(new SATQuestion("Convert 60 miles per hour to feet per second. (1 mi = 5280 ft)", new String[]{"60", "88", "100", "120"}, 1));
        triviaBank.add(new SATQuestion("If 4^x = 8^(x-1), what is x?", new String[]{"1", "2", "3", "4"}, 2));
        triviaBank.add(new SATQuestion("The author’s argument was intentionally _____, making it hard to follow.", new String[]{"Lucid", "Obfuscated", "Pithy", "Candid"}, 1));
        triviaBank.add(new SATQuestion("To _____ the pain, she took an aspirin.", new String[]{"Aggravate", "Mitigate", "Instigate", "Elevate"}, 1));
        triviaBank.add(new SATQuestion("A trend that lasts only a few weeks is best described as _____.", new String[]{"Perennial", "Ephemeral", "Enduring", "Ubiquitous"}, 1));
        triviaBank.add(new SATQuestion("A cacophony of sounds can be described as _____.", new String[]{"Harmonious", "Melodic", "Discordant", "Soothing"}, 2));
        triviaBank.add(new SATQuestion("The word 'Pragmatic' most nearly means:", new String[]{"Idealistic", "Practical", "Emotional", "Stubborn"}, 1));
        triviaBank.add(new SATQuestion("He was known for his _____ behavior, changing his mind constantly.", new String[]{"Capricious", "Steadfast", "Resolute", "Dogmatic"}, 0));
        triviaBank.add(new SATQuestion("A 'specious' argument is one that is:", new String[]{"Factually correct", "Deceptively attractive", "Boringly long", "Highly emotional"}, 1));
        triviaBank.add(new SATQuestion("The word 'Alleviate' most nearly means:", new String[]{"To worsen", "To elevate", "To relieve", "To create"}, 2));
        triviaBank.add(new SATQuestion("The jury _____ reached a verdict.", new String[]{"has", "have", "are", "were"}, 0));
        triviaBank.add(new SATQuestion("Identify the correct modifier: Walking down the street, _____.", new String[]{"the trees were beautiful.", "the sky turned red.", "John saw a dog.", "a car drove by."}, 2));
        triviaBank.add(new SATQuestion("Which shows correct parallel structure?", new String[]{"Skiing, to run, and jumping", "Skiing, running, and jumping", "To ski, running, and to jump", "Ski, ran, jumping"}, 1));
        triviaBank.add(new SATQuestion("The painting, _____ was hung in the lobby, is a masterpiece.", new String[]{"who", "whom", "which", "whose"}, 2));
        triviaBank.add(new SATQuestion("The CEO, along with the board members, _____ attending the meeting.", new String[]{"is", "are", "were", "have been"}, 0));
        triviaBank.add(new SATQuestion("Choose the correct form: It is a secret between you and _____.", new String[]{"I", "me", "he", "they"}, 1));
        triviaBank.add(new SATQuestion("Which indicates possession for 'it'?", new String[]{"It's", "Its'", "Its", "Its's"}, 2));
        triviaBank.add(new SATQuestion("She is the student _____ the teacher praised.", new String[]{"who", "whom", "which", "whose"}, 1));
        triviaBank.add(new SATQuestion("Which punctuation joins two independent clauses without a conjunction?", new String[]{"Comma", "Semicolon", "Apostrophe", "Hyphen"}, 1));
        triviaBank.add(new SATQuestion("She loved cooking; _____, she opened a restaurant.", new String[]{"Conversely", "Consequently", "Nevertheless", "However"}, 1));
        triviaBank.add(new SATQuestion("The team lost the game. _____, they celebrated their hard work.", new String[]{"Furthermore", "Indeed", "Nevertheless", "Ergo"}, 2));
        triviaBank.add(new SATQuestion("Correct punctuation: My favorite colors are: blue, red, and green.", new String[]{"Keep colon", "Remove colon", "Replace with semicolon", "Replace with comma"}, 1));

    }

    private boolean askTrivia(String title) {
        SATQuestion q = triviaBank.get(rand.nextInt(triviaBank.size()));
        int selection = JOptionPane.showOptionDialog(this, q.question, title,
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, q.options, q.options[0]);
        return selection == q.correctIndex;
    }

    private final int WIDTH = 1024;
    private final int HEIGHT = 640;
    private double posX = 1.5, posY = 1.5;
    private double dirX = 1.0, dirY = 0.0;
    private double planeX = 0.0, planeY = 0.66;
    private boolean moveFwd, moveBack, strafeL, strafeR, turnL, turnR;
    private BufferedImage screen;
    private int[] pixels;
    private double[] zBuffer;
    private BufferedImage wumpusTex, batTex;
    private int[] wumpusPixels, batPixels;
    private final int TEX_SIZE = 64;
    private Random rand = new Random();
    private ArrayList<Entity> entities = new ArrayList<>();

    class Entity {
        double x, y;
        int type; 
        double moveCooldown = 0; 
        Entity(double x, double y, int type) { this.x = x; this.y = y; this.type = type; }
    }

    private final int[][] map = {
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
        {1,0,1,1,0,1,0,0,0,1,1,1,0,0,1},
        {1,0,0,0,0,1,0,1,0,0,0,1,0,0,1},
        {1,1,1,0,1,1,0,1,1,1,0,1,1,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };

    public HuntTheWumpus3D() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        initTrivia();
        screen = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) screen.getRaster().getDataBuffer()).getData();
        zBuffer = new double[WIDTH];
        loadTextures();
        setupKeyBindings();
        entities.add(new Entity(5.5, 5.5, 2)); 
        for(int i = 0; i < 6; i++) spawnRandomEntity(3);
        new Timer(16, this).start();
    }

    private void shoot() {
        Entity target = null;
        for (Entity en : entities) {
            double dist = Math.sqrt(Math.pow(posX - en.x, 2) + Math.pow(posY - en.y, 2));
            if (en.type == 2 && dist < 4.0) { 
                target = en;
                break;
            }
        }

        if (target != null) {
            if (askTrivia("SAT Challenge: Aiming your Arrow!")) {
                entities.remove(target);
                JOptionPane.showMessageDialog(this, "Correct! Your arrow flies true. The Wumpus is slain!");
                System.exit(0);
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect! You missed the shot. The Wumpus is angered!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nothing in range to shoot.");
        }
    }

    private void checkHazards() {
        for (Entity en : entities) {
            double dist = Math.sqrt(Math.pow(posX - en.x, 2) + Math.pow(posY - en.y, 2));
            if (dist < 0.4) {
                if (en.type == 2) {
                    if (askTrivia("THE WUMPUS ATTACKS! Solve to escape!")) {
                        JOptionPane.showMessageDialog(this, "Correct! You dodged the Wumpus. You are spared.");
                        teleportEntity(en); 
                    } else {
                        JOptionPane.showMessageDialog(this, "Incorrect. The Wumpus ate you!");
                        System.exit(0);
                    }
                } else if (en.type == 3) {
                    if (askTrivia("A BAT GRABBED YOU! Solve to break free!")) {
                        JOptionPane.showMessageDialog(this, "Correct! You wrestled away from the bat and are spared.");
                        teleportEntity(en); 
                    } else {
                        JOptionPane.showMessageDialog(this, "Incorrect. The bat carries you to a random room!");
                        teleportPlayer();
                    }
                }
                break; 
            }
        }
    }

    private void spawnRandomEntity(int type) {
        int rx, ry;
        do {
            rx = rand.nextInt(map.length);
            ry = rand.nextInt(map[0].length);
        } while (map[rx][ry] != 0 || (rx == (int)posX && ry == (int)posY));
        entities.add(new Entity(rx + 0.5, ry + 0.5, type));
    }

    private void loadTextures() {
        wumpusPixels = loadSingleTexture("/Users/sidhaanthkapoor/Desktop/Hunt The Wumpus/The-Wumpus-goons/wumpus_front.png", Color.MAGENTA);
        batPixels = loadSingleTexture("/Users/sidhaanthkapoor/Desktop/Hunt The Wumpus/The-Wumpus-goons/bat_front.png", Color.CYAN);
    }

    private int[] loadSingleTexture(String path, Color fallback) {
        BufferedImage img;
        try {
            File file = new File(path);
            if(file.exists()) img = ImageIO.read(file);
            else throw new IOException();
        } catch (IOException e) {
            img = new BufferedImage(TEX_SIZE, TEX_SIZE, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = img.createGraphics();
            g.setColor(fallback); g.fillRect(0, 0, TEX_SIZE, TEX_SIZE); g.dispose();
        }
        Image tmp = img.getScaledInstance(TEX_SIZE, TEX_SIZE, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(TEX_SIZE, TEX_SIZE, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = dimg.createGraphics(); g2d.drawImage(tmp, 0, 0, null); g2d.dispose();
        int[] texPixels = new int[TEX_SIZE * TEX_SIZE];
        dimg.getRGB(0, 0, TEX_SIZE, TEX_SIZE, texPixels, 0, TEX_SIZE);
        return texPixels;
    }

    private void updateEntities() {
        for (Entity en : entities) {
            en.moveCooldown++;
            int speedThreshold = (en.type == 3) ? 20 : 45; 
            if (en.moveCooldown > speedThreshold) { 
                double moveAmt = (en.type == 3) ? 0.4 : 0.2;
                double nextX = en.x + (rand.nextDouble() * (moveAmt * 2) - moveAmt);
                double nextY = en.y + (rand.nextDouble() * (moveAmt * 2) - moveAmt);
                if (nextX > 0 && nextX < map.length && nextY > 0 && nextY < map[0].length) {
                    if (map[(int)nextX][(int)nextY] == 0) { en.x = nextX; en.y = nextY; }
                }
                en.moveCooldown = 0;
            }
        }
    }

    private void setupKeyBindings() {
        InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();
        String[] keys = {"W", "S", "A", "D", "LEFT", "RIGHT", "SPACE"};
        for (String k : keys) {
            im.put(KeyStroke.getKeyStroke("pressed " + k), "p" + k);
            im.put(KeyStroke.getKeyStroke("released " + k), "r" + k);
        }
        am.put("pW", new AbstractAction() { public void actionPerformed(ActionEvent e) { moveFwd = true; }});
        am.put("rW", new AbstractAction() { public void actionPerformed(ActionEvent e) { moveFwd = false; }});
        am.put("pS", new AbstractAction() { public void actionPerformed(ActionEvent e) { moveBack = true; }});
        am.put("rS", new AbstractAction() { public void actionPerformed(ActionEvent e) { moveBack = false; }});
        am.put("pA", new AbstractAction() { public void actionPerformed(ActionEvent e) { strafeL = true; }});
        am.put("rA", new AbstractAction() { public void actionPerformed(ActionEvent e) { strafeL = false; }});
        am.put("pD", new AbstractAction() { public void actionPerformed(ActionEvent e) { strafeR = true; }});
        am.put("rD", new AbstractAction() { public void actionPerformed(ActionEvent e) { strafeR = false; }});
        am.put("pLEFT", new AbstractAction() { public void actionPerformed(ActionEvent e) { turnR = true; }});
        am.put("rLEFT", new AbstractAction() { public void actionPerformed(ActionEvent e) { turnR = false; }});
        am.put("pRIGHT", new AbstractAction() { public void actionPerformed(ActionEvent e) { turnL = true; }});
        am.put("rRIGHT", new AbstractAction() { public void actionPerformed(ActionEvent e) { turnL = false; }});
        am.put("pSPACE", new AbstractAction() { public void actionPerformed(ActionEvent e) { shoot(); }});
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (turnL) rotate(-0.04); if (turnR) rotate(0.04);
        double mx = 0, my = 0, speed = 0.06;
        if (moveFwd) { mx += dirX * speed; my += dirY * speed; }
        if (moveBack) { mx -= dirX * speed; my -= dirY * speed; }
        if (strafeL) { mx -= planeX * speed; my -= planeY * speed; }
        if (strafeR) { mx += planeX * speed; my += planeY * speed; }
        if (map[(int)(posX + mx)][(int)posY] == 0) posX += mx;
        if (map[(int)posX][(int)(posY + my)] == 0) posY += my;
        updateEntities();
        checkHazards();
        repaint();
    }

    private void teleportPlayer() {
        int rx, ry;
        do {
            rx = rand.nextInt(map.length);
            ry = rand.nextInt(map[0].length);
        } while (map[rx][ry] != 0);
        posX = rx + 0.5; posY = ry + 0.5;
    }

    private void teleportEntity(Entity en) {
        int rx, ry;
        do {
            rx = rand.nextInt(map.length);
            ry = rand.nextInt(map[0].length);
        } while (map[rx][ry] != 0 || (rx == (int)posX && ry == (int)posY));
        en.x = rx + 0.5; en.y = ry + 0.5;
    }

    private void rotate(double angle) {
        double oldDx = dirX;
        dirX = dirX * Math.cos(angle) - dirY * Math.sin(angle);
        dirY = oldDx * Math.sin(angle) + dirY * Math.cos(angle);
        double oldPx = planeX;
        planeX = planeX * Math.cos(angle) - planeY * Math.sin(angle);
        planeY = oldPx * Math.sin(angle) + planeY * Math.cos(angle);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < pixels.length / 2; i++) pixels[i] = 0x111111;
        for (int i = pixels.length / 2; i < pixels.length; i++) pixels[i] = 0x222222; 
        for (int x = 0; x < WIDTH; x++) {
            double cameraX = 2 * x / (double)WIDTH - 1;
            double rDx = dirX + planeX * cameraX;
            double rDy = dirY + planeY * cameraX;
            int mX = (int)posX, mY = (int)posY;
            double dDx = Math.abs(1/rDx), dDy = Math.abs(1/rDy), sDx, sDy;
            int stX, stY, hit = 0, side = 0;
            if (rDx < 0) { stX = -1; sDx = (posX - mX) * dDx; }
            else { stX = 1; sDx = (mX + 1.0 - posX) * dDx; }
            if (rDy < 0) { stY = -1; sDy = (posY - mY) * dDy; }
            else { stY = 1; sDy = (mY + 1.0 - posY) * dDy; }
            while (hit == 0) {
                if (sDx < sDy) { sDx += dDx; mX += stX; side = 0; }
                else { sDy += dDy; mY += stY; side = 1; }
                hit = map[mX][mY];
            }
            double dist = (side == 0) ? (mX - posX + (1 - stX)/2.0)/rDx : (mY - posY + (1 - stY)/2.0)/rDy;
            zBuffer[x] = dist;
            int h = (int)(HEIGHT / dist);
            int start = Math.max(0, -h/2 + HEIGHT/2);
            int end = Math.min(HEIGHT-1, h/2 + HEIGHT/2);
            int clr = (side == 1) ? 0x0044AA : 0x0066FF;
            for (int y = start; y < end; y++) pixels[y * WIDTH + x] = clr;
        }

        for (Entity en : entities) {
            double sprX = en.x - posX; double sprY = en.y - posY;
            double invDet = 1.0 / (planeX * dirY - dirX * planeY);
            double trX = invDet * (dirY * sprX - dirX * sprY);
            double trY = invDet * (-planeY * sprX + planeX * sprY);
            if (trY > 0.1) {
                int sprScrX = (int)((WIDTH / 2) * (1 + trX / trY));
                int sprH = Math.abs((int)(HEIGHT / trY));
                int dSY = Math.max(0, -sprH/2 + HEIGHT/2);
                int dEY = Math.min(HEIGHT-1, sprH/2 + HEIGHT/2);
                int sprW = Math.abs((int)(HEIGHT / trY));
                int dSX = Math.max(0, -sprW/2 + sprScrX);
                int dEX = Math.min(WIDTH-1, sprW/2 + sprScrX);
                for (int stripe = dSX; stripe < dEX; stripe++) {
                    int texX = (int)(256 * (stripe - (-sprW / 2 + sprScrX)) * TEX_SIZE / sprW) / 256;
                    if (stripe > 0 && stripe < WIDTH && trY < zBuffer[stripe]) {
                        for (int y = dSY; y < dEY; y++) {
                            int d = y * 256 - HEIGHT * 128 + sprH * 128;
                            int texY = ((d * TEX_SIZE) / sprH) / 256;
                            int color = (en.type == 2) ? wumpusPixels[TEX_SIZE * texY + texX] : batPixels[TEX_SIZE * texY + texX];
                            if ((color & 0xFF000000) != 0) pixels[y * WIDTH + stripe] = color;
                        }
                    }
                }
            }
        }
        g.drawImage(screen, 0, 0, null);
        int scale = 10;
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                g.setColor(map[x][y] > 0 ? Color.GRAY : Color.BLACK);
                g.fillRect(20 + x*scale, 50 + y*scale, scale, scale);
            }
        }
        g.setColor(Color.RED); g.fillOval(20+(int)(posX*scale)-3, 50+(int)(posY*scale)-3, 6, 6);
        for(Entity e : entities) {
            g.setColor(e.type == 2 ? Color.GREEN : Color.CYAN);
            g.fillOval(20+(int)(e.x*scale)-2, 50+(int)(e.y*scale)-2, 4, 4);
        }
        g.setColor(Color.WHITE);
        g.drawString("WASD: Move | SPACE: Shoot (Trivia Trigger) | Map: Top Left", 20, 30);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("SAT: Hunt The Wumpus 3D");
        f.add(new HuntTheWumpus3D());
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
