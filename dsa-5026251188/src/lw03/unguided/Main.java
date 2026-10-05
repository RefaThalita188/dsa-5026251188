package lw03.unguided;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("enrollment.txt"));
        
        Map<String, Integer> enrollmentMap = new LinkedHashMap<>();
        List<String> courseOrder = new ArrayList<>();
        List<String> checkResults = new ArrayList<>();
        int rejectedOperations = 0; 
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split("\\s+");
            String operation = parts[0];
            String courseCode = parts[1];

            if (operation.equals("REGISTER")) {
                int count = Integer.parseInt(parts[2]);
                
                if (count <= 0) {
                    rejectedOperations++;
                } else {
                    if (enrollmentMap.containsKey(courseCode)) {
                        enrollmentMap.put(courseCode, count);
                        courseOrder.add(courseCode); 
                    } else {
                        int currentCount = enrollmentMap.get(courseCode);
                        enrollmentMap.put(courseCode, currentCount + count);
                    }
                }

            } else if (operation.equals("WITHDRAW")) {
                int count = Integer.parseInt(parts[2]);
               
                if (count <= 0 & enrollmentMap.containsKey(courseCode)) {
                    rejectedOperations++;
                } else {
                    int currentCount = enrollmentMap.get(courseCode);
                    if (currentCount >= count) {
                        enrollmentMap.put(courseCode, currentCount - count);
                    } else {
                        rejectedOperations++;
                    }
                }
            } else if (operation.equals("CHECK")) {
                if (enrollmentMap.containsKey(courseCode)) {
                    checkResults.add(courseCode + ": " + enrollmentMap.get(courseCode) + " students");
                } else {
                    checkResults.add(courseCode + ": Not found");
                }
            }
        }
        scanner.close();

        System.out.println("===== Enrollment Checks =====");
        for (String result : checkResults) {
            System.out.println(result);
        }
        System.out.println("===== Final Enrollment =====");
        for (String course : courseOrder) {
            System.out.println(course + ": " + enrollmentMap.get(course) + " students");
        }
        System.out.println("Rejected operations: " + rejectedOperations);
    }
}