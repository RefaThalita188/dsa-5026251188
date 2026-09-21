package unguided;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Rental> rentals = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File ("rentals.txt")) {
            if (!scanner.hasNextInt()) return;
            int totalRecords = scanner.nextInt();
            
            Rental[] rentals = new Rental[totalRecords];
            int[] unitsArray = new int[totalRecords];

            for (int i = 0; i < totalRecords; i++) {
                String type = scanner.next();
                String id = scanner.next();
                int days = scanner.nextInt();
                int units = scanner.nextInt();

                if (type.equalsIgnoreCase("Laptop")) {
                    rentals.add(new LaptopRental(id, days));
                } else if (type.equalsIgnoreCase("Projector")) {
                    rentals.add(new ProjectorRental(id, units));
                }
            }
            for (int i = 0; i < rentals.length; i++) {
                System.out.println(rentals[i].summary(unitsArray[i]));
            }

        } catch (FileNotFoundException e) {
            System.err.println("rentals.txt not found: " + e.getMessage());
        }
    }
}