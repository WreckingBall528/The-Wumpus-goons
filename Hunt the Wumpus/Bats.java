import java.util.Random;


public class Bats {
    private int currentRoom;

    public Bats(int startRoom) {
        this.currentRoom = startRoom;
    }

    public int getRoom() {
        return currentRoom;
    }

    public void setRoom(int newRoom) {
        this.currentRoom = newRoom;
    }

    public int carryPlayer(Random rand, int totalRooms) {
        int droppedRoom = rand.nextInt(totalRooms);
        this.currentRoom = rand.nextInt(totalRooms);
        return droppedRoom;
    }
}