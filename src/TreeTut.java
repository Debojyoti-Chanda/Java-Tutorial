import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class TreeTut {
    public static void main(String[] args) {

    }
    static class Node{
        int val;
        Node left;
        Node right;
        
        Node(int val){
            this.val=val;
            left=null;
            right=null;
        }
    }

    public static void createTree(Node root0, ArrayList<Integer> v) {
        // Code here
        int i = 1;
        Queue<Node> que = new LinkedList<>();
        que.add(root0);
        while (i < v.size()) {
            Node top = que.remove();
            if (i < v.size()) {
                Node newNode = new Node(v.get(i));
                que.add(newNode);
                top.left = newNode;
                i++;
            }
            if (i < v.size()) {
                Node newNode = new Node(v.get(i));
                que.add(newNode);
                top.right = newNode;
                i++;
            }
        }
    }
    public static ArrayList<Integer> inOrder(Node root) {
        // Inorder Traversal --  https://www.geeksforgeeks.org/problems/inorder-traversal/1
        // ArrayList<Integer> arr = new ArrayList<>();
        // helperInorder(root,arr);
        // return arr;
        // OR------------------
        ArrayList<Integer> arr = new ArrayList<>();
        Stack<Node> s = new Stack<>();
        if(root == null){
            return arr;
        }
        Node curr = root;
        while (curr != null || s.size() > 0) {
            while (curr != null) {
                s.push(curr);
                curr = curr.left;
            }
            // Current must be NULL at this point
            curr = s.pop();
            arr.add(curr.val);
            curr = curr.right;
        }
        return arr;
    }

    public static void helperInorder(Node root, ArrayList<Integer> arr) {
        if (root == null) {
            return;
        }
        helperInorder(root.left, arr);
        arr.add(root.val);
        helperInorder(root.right, arr);
    }

    public static void helperPreorder(Node root, ArrayList<Integer> arr) {
        if (root == null) {
            return;
        }
        arr.add(root.val);
        helperPreorder(root.left, arr);
        helperPreorder(root.right, arr);
    }

    public static ArrayList<Integer> preorder(Node root) {
        // Code here
        ArrayList<Integer> arr = new ArrayList<>();
        Stack<Node> s = new Stack<>();
        if (root == null) {
            return arr;
        }
        Node curr = root;
        while (curr != null || s.size() > 0) {
            while (curr != null) {
                arr.add(curr.val);
                s.push(curr);
                curr = curr.left;
            }
            // Current must be NULL at this point
            curr = s.pop();
            curr = curr.right;
        }
        return arr;
    }
    public static void helperPostorder(Node root, ArrayList<Integer> arr) {
        if (root == null) {
            return;
        }
        helperPostorder(root.left, arr);
        helperPostorder(root.right, arr);
        arr.add(root.val);
    }
    
}
