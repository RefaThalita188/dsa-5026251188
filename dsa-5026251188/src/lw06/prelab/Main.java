package lw06.prelab;

import java.util.*;

class Node {
    int data;
    Node left, right;

    Node(int data) {
    this.data = data;
    this.left = null;
    this.right = null;

    }
}

public class Main {
    static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        }
        if (data < root.data) {
            root.left = insert(root.left, data);
        } else if (data > root.data) {
            root.right = insert(root.right, data);
        }
        return root; 
    }

    static boolean search(Node root, int target) {
        if (root == null) {
            return false;
        }
        if (target == root.data) {
            return true;
        }
        if (target < root.data) {
            return search(root.left, target);
        }
        return search(root.right, target);
    }

    static void preorder(Node root, List<Integer> result) {
        if (root == null) return;
        result.add(root.data);
        preorder(root.left, result);
        preorder(root.right, result);
    }

    static void inorder(Node root, List<Integer> result) {
        if (root == null) return;
        inorder(root.left, result);
        result.add(root.data);
        inorder(root.right, result);
    }

    static void postorder(Node root, List<Integer> result) {
        if (root == null) return;
        postorder(root.left, result);
        postorder(root.right, result);
        result.add(root.data);
    }

    static String join(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) sb.append(" ");
            sb.append(list.get(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("/treenumbers.txt"));
        Node root = null;

        String[] values = sc.nextLine().trim().split("\\s+");
        for (String v : values) {
            root = insert(root, Integer.parseInt(v));
        }

        int target = Integer.parseInt(sc.nextLine().trim());
        sc.close();

        List<Integer> pre = new ArrayList<>();
        List<Integer> in = new ArrayList<>();
        List<Integer> post = new ArrayList<>();

        preorder(root, pre);
        inorder(root, in);
        postorder(root, post);

        System.out.println("Preorder : " + join(pre));
        System.out.println("Inorder : " + join(in));
        System.out.println("Postorder : " + join(post));
        System.out.println("Search " + target + " : " + search(root, target));
    }
}