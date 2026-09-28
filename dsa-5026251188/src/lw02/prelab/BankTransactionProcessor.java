package lw02.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;

public class BankTransactionProcessor {
    public static void main(String[] args) {
    
        LinkedList<String[]> transactionList = new LinkedList<>();
        
        try {
        Scanner fileScanner = new Scanner(new File("transactions.txt"));
        while (fileScanner.hasNextLine()) {

            String line = fileScanner.nextLine().trim();
                if (!line.isEmpty()) {
            String[] parts = line.split("\\s+");

                if (parts.length == 3) {
                        transactionList.add(parts);
                    }
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
        System.out.println("File transactions.txt not found!");
        return;
        }

        LinkedList<String[]> customerList = new LinkedList<>();
        for (String[] tx : transactionList) {
            String name = tx[0];
            boolean exists = false; 
        
        for (String[] customer : customerList) {
                if (customer[0].equals(name)) {
                    exists = true;
                    break;
                }
            }
                if (!exists) {
                customerList.add(new String[]{name, "0"});
            }
        }

        Queue<String[]> transactionQueue = new LinkedList<>(transactionList);
        Stack<String[]> failedStack = new Stack<>();

        while (!transactionQueue.isEmpty()) {
            String[] tx = transactionQueue.poll(); 
            String name = tx[0];
            String type = tx[1];
            int amount = Integer.parseInt(tx[2]);

            String[] targetCustomer = null;
            for (String[] customer : customerList) {
                if (customer[0].equals(name)) {
                    targetCustomer = customer;
                    break;
                }
            }

            if (targetCustomer != null) {
                int currentBalance = Integer.parseInt(targetCustomer[1]);
                if (type.equals("DEPOSIT")) {
                    currentBalance += amount;
                    targetCustomer[1] = String.valueOf(currentBalance);
                } else if (type.equals("WITHDRAW")) {
                   
                    if (amount > currentBalance) {
                        failedStack.push(tx); 
                    } else {
                        currentBalance -= amount;
                        targetCustomer[1] = String.valueOf(currentBalance);
                    }
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] customer : customerList) {
            System.out.println(customer[0] + " : " + customer[1]);
        }

        System.out.println("=== Failed Transactions ===");
        while (!failedStack.isEmpty()) {
            String[] failedTx = failedStack.pop();
            System.out.println(failedTx[0] + " " + failedTx[1] + " " + failedTx[2]);
        }
    }
}