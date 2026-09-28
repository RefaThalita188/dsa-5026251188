package lw02.unguided;

    import java.util.*;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> requestList = new LinkedList<>();
        LinkedList<String[]> bookList = new LinkedList<>();
        LinkedList<String[]> memberList = new LinkedList<>();

        Queue<String[]> q = new LinkedList<>();
        Stack<String[]> fails = new Stack<>();
        LinkedList<String[]> successList = new LinkedList<>();

        Scanner sc = new Scanner(Main.class.getResourceAsStream("borrowing.txt"));

        while (sc.hasNext()) {
            String[] request = new String[2];
            request[0] = sc.next(); // borrower's name
            request[1] = sc.next(); // title of the requested book
            requestList.add(request);
        }
        sc.close();

        bookList.add(new String[]{"Kalkulus", "2"});
        bookList.add(new String[]{"Fisika", "1"});
        bookList.add(new String[]{"Statistika", "2"});

        q.addAll(requestList);

        int MAX_BORROW = 2;

        while (!q.isEmpty()) {
            String[] currentRequest = q.poll();
            String name = currentRequest [0];
            String bookTitle = currentRequest [1];

            
            String[] targetBook = null;
            for (String[] book : bookList) {
                if (book[0].equals(bookTitle)) {
                    targetBook = book;
                    break;
                }
            }

            String[] targetMember = null;
            for (String[] member : memberList) {
                if (member[0].equals(name)) {
                    targetMember = member;
                    break;
                }
            }

            if (targetMember == null) {
                targetMember = new String[]{name, "0"};
                memberList.add(targetMember);
            }

            int bookStock = Integer.parseInt(targetBook[1]);
            int borrowedCount = Integer.parseInt(targetMember[1]);

            if (bookStock > 0 & borrowedCount < MAX_BORROW) {
      
                successList.add(currentRequest);
                targetBook[1] = String.valueOf(bookStock - 1);
                targetMember[1] = String.valueOf(borrowedCount + 1);
            } else {
          
                fails.push(currentRequest);
            }
        }

        System.out.println("=== Successfully Processed Requests ===");
        for (String[] success : successList) {
            System.out.println(success[0] + " " + success[1]);
        }

        System.out.println("=== Remaining Book Stock ===");
        for (String[] book : bookList) {
            System.out.println(book[0] + " : " + book[1]);
        }

        System.out.println("=== Failed Requests ===");
        while (!fails.isEmpty()) {
            String[] fail = fails.pop();
            System.out.println(fail[0] + " " + fail[1]);
        }
    }
}