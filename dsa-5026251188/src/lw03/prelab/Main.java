package lw03.prelab;

import java.util.*;
        
public class Main {
    public static void main(String[] args) {
        
        System.out.println("=====Problem 1: Playlist=====");
        LinkedList<String> playlist = new LinkedList<>();
        
        Scanner sc1 = new Scanner(Main.class.getResourceAsStream("playlist.txt"));
        
        while (sc1.hasNextLine()) {
            String line = sc1.nextLine().trim();
            if (line.isEmpty()) continue;
            if (line.startsWith("ADD")) {
                String song = line.substring(4).trim();
                playlist.add(song);
            } else if (line.startsWith("INSERT")) {
                String[] parts = line.split(" ", 3);
                if (parts.length >= 3) {
                    int index = Integer.parseInt(parts[0]);
                    String song = parts[1].trim();
                    playlist.add(index, song);
                }
            } else if (line.startsWith("REMOVE")) {
                String song = line.substring(7).trim();
                playlist.remove(song);
            }
        }
        sc1.close();

        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }

        System.out.println("=====Problem 2: Participants=====");
        Set<String> uniqueParticipants = new LinkedHashSet<>();
        int duplicateCount = 0;
        
        Scanner sc2 = new Scanner(Main.class.getResourceAsStream("participants.txt"));
       
        while (sc2.hasNextLine()) {
            String name = sc2.nextLine().trim();
            if (name.isEmpty()) continue;
            
            boolean added = uniqueParticipants.add(name);
            if (!added) {
                duplicateCount++;
            }
        }
        sc2.close();

        System.out.println("Unique participants: " + uniqueParticipants.size());
        int number = 1;
        for (String participant : uniqueParticipants) {
            System.out.println(number + ". " + participant);
            number++;
        }
        System.out.println("Duplicate registrations: " + duplicateCount);

        System.out.println("=====Problem 3: Inventory =====");
        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;
        
        Scanner sc3 = new Scanner(Main.class.getResourceAsStream("inventory.txt"));
       
        while (sc3.hasNextLine()) {
            String line = sc3.nextLine().trim();
            if (line.isEmpty()) continue;
            
            String[] parts = line.split("\\s+");
            if (parts.length >= 3) {
                String type = parts[0];
                String product = parts[1];
                int quantity = Integer.parseInt(parts[2]);
                
                if (type.equals("ADD")) {
                    inventory.put(product, inventory.getOrDefault(product, 0) + quantity);
                } else if (type.equals("SELL")) {
                    if (inventory.containsKey(product) && inventory.get(product) >= quantity) {
                        inventory.put(product, inventory.get(product) - quantity);
                    } else {
                        failedSales++;
                    }
                }
            }
        }
        sc3.close();

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Failed sales: " + failedSales);
    }
}