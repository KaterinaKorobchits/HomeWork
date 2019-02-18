import java.util.ArrayList;

public class Building {

    String name;
    ArrayList<Room> listOfRomm = new ArrayList<>();

    public Building(String name) {
        this.name = name;
    }

    void addRoom(String name, double square, int numberOfWindows) throws IllimunanceTooMuchException {
        Room room = new Room(name, square, numberOfWindows);
        listOfRomm.add(room);
    }

    Room getRoom(String name) {
        for(Room room: listOfRomm)
            if(room.name.equals(name)) {
                return room;
            }
        return null;
    }

    public void print() {
        System.out.println(name);
        for (Room room : listOfRomm) {
            System.out.print("  ");
            room.print();
        }
    }
}
