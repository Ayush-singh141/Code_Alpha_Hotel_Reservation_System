import java.util.ArrayList;
import java.util.Scanner;

class Room {
    int roomNumber;
    String category;
    boolean isAvailable;

    public Room(int roomNumber, String category, boolean isAvailable) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isAvailable = isAvailable;
    }

    public void bookRoom() {
        this.isAvailable = false;
    }

    public void cancelBooking() {
        this.isAvailable = true;
    }

    @Override
    public String toString() {
        return "Room Number: " + roomNumber + ", Category: " + category + ", Available: " + isAvailable;
    }
}

class Booking {
    int bookingId;
    Room room;
    String customerName;
    String paymentStatus;

    public Booking(int bookingId, Room room, String customerName, String paymentStatus) {
        this.bookingId = bookingId;
        this.room = room;
        this.customerName = customerName;
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId + ", Room: " + room + ", Customer: " + customerName + ", Payment Status: " + paymentStatus;
    }
}

public class HotelReservationSystem {
    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();
    static int bookingCounter = 1;

    public static void main(String[] args) {
        // Initialize rooms
        rooms.add(new Room(101, "Single", true));
        rooms.add(new Room(102, "Double", true));
        rooms.add(new Room(103, "Suite", true));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Search for available rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View booking details");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    searchRooms();
                    break;
                case 2:
                    makeReservation(scanner);
                    break;
                case 3:
                    viewBookingDetails(scanner);
                    break;
                case 4:
                    System.exit(0);
            }
        }
    }

    public static void searchRooms() {
        for (Room room : rooms) {
            if (room.isAvailable) {
                System.out.println(room);
            }
        }
    }

    public static void makeReservation(Scanner scanner) {
        System.out.println("Enter room number to book:");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber && room.isAvailable) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom != null) {
            System.out.println("Enter your name:");
            String customerName = scanner.nextLine();
            System.out.println("Processing payment...");
            String paymentStatus = "Paid";

            Booking booking = new Booking(bookingCounter++, selectedRoom, customerName, paymentStatus);
            bookings.add(booking);
            selectedRoom.bookRoom();

            System.out.println("Booking successful! " + booking);
        } else {
            System.out.println("Room not available or does not exist.");
        }
    }

    public static void viewBookingDetails(Scanner scanner) {
        System.out.println("Enter booking ID:");
        int bookingId = scanner.nextInt();

        for (Booking booking : bookings) {
            if (booking.bookingId == bookingId) {
                System.out.println(booking);
                return;
            }
        }
        System.out.println("Booking not found.");
    }
}
