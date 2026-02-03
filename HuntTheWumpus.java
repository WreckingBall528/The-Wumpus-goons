import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class HuntTheWumpus extends JFrame {

    JTextArea output;
    JButton moveButton, shootButton;

    int playerRoom, wumpusRoom, pit1, pit2, bat1, bat2;
    Random rand = new Random();

    int[][] tunnels = {
        {1,4,7},{0,2,9},{1,3,11},{2,4,13},
        {0,3,5},{4,6,14},{5,7,16},{0,6,8},
        {7,9,17},{1,8,10},{9,11,18},{2,10,12},
        {11,13,19},{3,12,14},{5,13,15},{14,16,19},
        {6,15,17},{8,16,18},{10,17,19},{12,15,18}
    };

    public HuntTheWumpus() {
        setTitle("Hunt the Wumpus");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        output = new JTextArea();
        output.setEditable(false);
        output.setFont(new Font("Monospaced", Font.PLAIN, 14));
        add(new JScrollPane(output), BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        moveButton = new JButton("Move");
        shootButton = new JButton("Shoot");

        buttons.add(moveButton);
        buttons.add(shootButton);
        add(buttons, BorderLayout.SOUTH);

        setupGame();
        displayStatus();

        moveButton.addActionListener(e -> move());
        shootButton.addActionListener(e -> shoot());

        setVisible(true);
    }

    void setupGame() {
        playerRoom = rand.nextInt(20);
        wumpusRoom = rand.nextInt(20);
        pit1 = rand.nextInt(20);
        pit2 = rand.nextInt(20);
        bat1 = rand.nextInt(20);
        bat2 = rand.nextInt(20);

        output.append("Welcome to Hunt the Wumpus!\n");
    }

    void displayStatus() {
        output.append("\nYou are in room " + playerRoom + "\n");
        output.append("Tunnels lead to: ");

        for (int r : tunnels[playerRoom]) {
            output.append(r + " ");
        }
        output.append("\n");

        for (int r : tunnels[playerRoom]) {
            if (r == wumpusRoom)
                output.append("You smell something terrible...\n");
            if (r == pit1 || r == pit2)
                output.append("You feel a cold wind...\n");
            if (r == bat1 || r == bat2)
                output.append("You hear flapping...\n");
        }
    }

    void move() {
        String input = JOptionPane.showInputDialog(this, "Move to which room?");
        if (input == null) return;

        int target = Integer.parseInt(input);
        if (!isConnected(playerRoom, target)) {
            output.append("You can't move there.\n");
            return;
        }

        playerRoom = target;
        checkHazards();
        displayStatus();
    }

    void shoot() {
        String input = JOptionPane.showInputDialog(this, "Shoot into which room?");
        if (input == null) return;

        int target = Integer.parseInt(input);
        if (!isConnected(playerRoom, target)) {
            output.append("That room isn't connected.\n");
            return;
        }

        if (target == wumpusRoom) {
            output.append("You killed the Wumpus! You win!\n");
            endGame();
        } else {
            output.append("Missed!\n");
            wumpusMove();
        }
    }

    boolean isConnected(int a, int b) {
        for (int r : tunnels[a])
            if (r == b) return true;
        return false;
    }

    void checkHazards() {
        if (playerRoom == wumpusRoom) {
            output.append("The Wumpus ate you.\n");
            endGame();
        }
        if (playerRoom == pit1 || playerRoom == pit2) {
            output.append("You fell into a pit.\n");
            endGame();
        }
        if (playerRoom == bat1 || playerRoom == bat2) {
            output.append("Bats carried you away!\n");
            playerRoom = rand.nextInt(20);
            checkHazards();
        }
    }

    void wumpusMove() {
        if (rand.nextBoolean()) {
            wumpusRoom = tunnels[wumpusRoom][rand.nextInt(3)];
            if (wumpusRoom == playerRoom) {
                output.append("The Wumpus moved into your room.\n");
                endGame();
            }
        }
    }

    void endGame() {
        moveButton.setEnabled(false);
        shootButton.setEnabled(false);
    }

    public static void main(String[] args) {
        new HuntTheWumpus();
    }
}