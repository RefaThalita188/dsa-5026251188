package lw06.prelab.unguided;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner read = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));

        BinarySearchTree bst = new BinarySearchTree();

        if (read.hasNextLine()) {
            String[] numbers = read.nextLine().trim().split("\\s+");
            for (String num : numbers) {
                if (!num.isEmpty()) {
                    bst.insert(Integer.parseInt(num));
                }
            }
        }

        int target = 0;
        if (read.hasNextLine()) {
            target = Integer.parseInt(read.nextLine().trim());
        }

        System.out.print("Preorder : ");
        bst.preOrder();

        System.out.print("Inorder  : ");
        bst.inOrder();

        System.out.print("Postorder: ");
        bst.postOrder();

        System.out.println("Search " + target + ": " + bst.search(target));
        System.out.println("Minimum  : " + bst.findMin());
        System.out.println("Maximum  : " + bst.findMax());

        read.close();
    }
}