import java.util.ArrayList;

class Node {
    int data;
    Node next;

    Node() {
        data = 0;
    }

    Node(int d) {
        data = d;
    } // constructor to create a new node
}

public class LinkedListTut {

    public static void main(String[] args) {
        Node ans = constructLL(new int[] { 1, 2, 3, 4, 5 });
        while (ans != null) {
            System.out.print(ans.data + " ");
            ans = ans.next;
        }
        System.out.println();
    }

    public static Node constructLL(int arr[]) {
        // Introduction to Linked List --
        // https://www.geeksforgeeks.org/problems/introduction-to-linked-list/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=introduction-to-linked-list
        Node head = new Node();
        Node current = new Node();
        for (int i = 0; i < arr.length; i++) {
            Node node = new Node(arr[i]);
            if (i == 0) {
                head = node;
                current = node;
                continue;
            }
            current.next = node;
            current = current.next;
        }
        return head;
    }

    // Function to insert a node at the beginning of the linked list.
    public static Node insertAtBeginning(Node head, int x) {
        // https://www.geeksforgeeks.org/problems/linked-list-insertion-1587115620/1
        Node node = new Node(x);
        if (head == null) {
            head = node;
            return head;
        }
        node.next = head;
        head = node;
        return head;

    }

    // Function to insert a node at the end of the linked list.
    public static Node insertAtEnd(Node head, int x) {
        // https://www.geeksforgeeks.org/problems/linked-list-insertion-1587115620/1
        Node node = new Node(x);
        if (head == null) {
            head = node;
            return head;
        }
        Node curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = node;
        return head;
    }

    public static Node deleteNode(Node head, int x) {
        // Delete a Node in Single Linked List --
        // https://www.geeksforgeeks.org/problems/delete-a-node-in-single-linked-list/1
        if (head == null) {
            return head;
        }
        if (x == 1) {
            head = head.next;
            return head;
        }
        Node curr = head;
        int y = 1;
        while (y != x - 1) {
            curr = curr.next;
            y++;
        }
        curr.next = curr.next.next;
        return head;
    }

    public static int getCount(Node head) {
        // https://www.geeksforgeeks.org/problems/count-nodes-of-linked-list/1
        int count = 0;
        Node curr = head;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        return count;
    }

    public static boolean searchKey(int n, Node head, int key) {
        // https://www.geeksforgeeks.org/problems/search-in-linked-list-1664434326/1
        Node curr = head;
        if (head == null) {
            return false;
        }
        while (curr != null) {
            if (curr.data == key) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public static Node constructDLL(int arr[]) {
        // Introduction to Doubly Linked List
        Node head = new Node(arr[0]);
        Node curr = head;
        for (int i = 1; i < arr.length; i++) {
            Node node = new Node(arr[i]);
            curr.next = node;
            node.prev = curr;
            curr = curr.next;
        }
        return head;
    }

    public static void addNode(Node head_ref, int pos, int data) {
        // https://www.geeksforgeeks.org/problems/insert-a-node-in-doubly-linked-list/1
        Node node = new Node(data);
        if (head_ref == null) {
            head_ref = node;
            return;
        }
        Node curr = head_ref;
        int y = 0;
        while (y != pos) {
            curr = curr.next;
            y++;
        }
        node.next = curr.next;
        if (curr.next != null) {
            curr.next.prev = node;
        }
        node.prev = curr;
        curr.next = node;
    }

    public static Node deleteNodeDLL(Node head, int x) {
        // Delete node in Doubly Linked List --
        // https://www.geeksforgeeks.org/problems/delete-node-in-doubly-linked-list/1
        Node curr = head;
        int y = 1;
        if (x == 1) {
            head = head.next;
            curr.next = null;
            head.prev = null;
            return head;
        }
        while (y != x - 1) {
            curr = curr.next;
            y++;
        }
        curr.next.prev = null;
        if (curr.next.next != null) {
            curr.next.next.prev = curr;
        }
        Node temp = curr.next.next; // null / 3->4
        curr.next.next = null;
        curr.next = temp;

        return head;
    }

    public static Node reverseDLL(Node head) {
        // Reverse a Doubly Linked List --
        // https://www.geeksforgeeks.org/problems/reverse-a-doubly-linked-list/1
        Node curr = head;
        while (curr.next != null) {
            Node temp = curr.prev;
            curr.prev = curr.next;
            curr.next = temp;
            curr = curr.prev;
        }
        Node temp = curr.prev;
        curr.prev = curr.next;
        curr.next = temp;
        head = curr;
        return head;
    }

    public static int getMiddle(Node head) {
        // Finding middle element in a linked list --
        // https://www.geeksforgeeks.org/problems/finding-middle-element-in-a-linked-list/1
        Node curr = head;
        int count = 0;
        while (curr != null) {
            curr = curr.next;
            count++;
        }
        int val = (count / 2);
        int i = 0;
        curr = head;
        while (i != val) {
            curr = curr.next;
            i++;
        }
        return curr.data;
    }

    public static Node reverseList(Node head) {
        // Reverse a linked list(recursive) --
        // https://www.geeksforgeeks.org/problems/reverse-a-linked-list/1
        Node reversedHead = reverseList(head, null);
        return reversedHead;
    }

    public static Node reverseList(Node curr, Node prev) {
        // Base case: If current node is null or next node is null
        if (curr == null) {
            return prev;
        }
        // Recursive step: Reverse the list starting from the next node
        Node nextNode = curr.next;
        curr.next = prev;
        // Recursively call with next node as current and current node as previous
        return reverseList(nextNode, curr);
    }

    Node reverseList1(Node head) {
        // Reverse a linked list(iterative) --- https://www.geeksforgeeks.org/problems/reverse-a-linked-list/1
        Node prv = null;
        Node curr = head;

        while (curr != null) {
            Node tempp2 = curr.next;
            curr.next = prv;
            prv = curr;
            curr = tempp2;
        }
        head = prv;
        return head;
    }

    public static boolean detectLoop(Node head) {
        // Detect Loop in linked list -- https://www.geeksforgeeks.org/problems/detect-loop-in-linked-list/1
        Node step1 = head;
        if (head.next == null) {
            return false;
        }
        Node step2 = head.next;
        while (step1 != null || step2 != null) {
            step1 = step1.next;
            if (step2.next == null) {
                return false;
            }
            if (step2.next.next == null) {
                return false;
            }
            step2 = step2.next.next;
            if (step1 == step2) {
                break;
            }
        }
        return true;
    }

    public static int findFirstNode(Node head) {
        // Find the first node of loop in linked list -- https://www.geeksforgeeks.org/problems/find-the-first-node-of-loop-in-linked-list--170645/1
        Node slow = head;
        Node fast = head;
        Node temp = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                while (slow != temp) {
                    slow = slow.next;
                    temp = temp.next;
                }
                return temp.data;
            }
        }
        return -1;
    }

    public static int countNodesinLoop(Node head) {
        //Find length of Loop -- https://www.geeksforgeeks.org/problems/find-length-of-loop/1  
        Node slow = head;
        Node fast = head;
        Node temp = head;
        int count = 0;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                while (slow != temp) {
                    slow = slow.next;
                    temp = temp.next;
                }
                slow = slow.next;
                count++;
                while (slow != temp) {
                    count++;
                    slow = slow.next;
                }
                return count;
            }
        }
        return count;
    }

    public static boolean isPalindrome(Node head) {
        // Check if Linked List is Palindrome -- https://www.geeksforgeeks.org/problems/check-if-linked-list-is-pallindrome/1
        Node curr = head;
        int num = 0;
        while (curr != null) {
            num = num * 10 + curr.data;
            curr = curr.next;
        }
        String st = Integer.toString(num);
        for (int i = 0; i < st.length() / 2; i++) {
            if (st.charAt(i) != st.charAt(st.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
    public static Node divide(int N, Node head){
        // Segregate even and odd nodes in a Linked List -- https://www.geeksforgeeks.org/problems/segregate-even-and-odd-nodes-in-a-linked-list5035/1
        Node curr = head;
        Node even = new Node(0);
        Node odd = new Node(0);
        Node evenstart=even;
        Node oddstart=odd;
        
        while(curr != null){
            if(curr.data %2==0){
                evenstart.next = curr;
                evenstart = curr;
            }else{
                oddstart.next= curr;
                oddstart= curr;
            }
            curr = curr.next;
        }
        evenstart.next=odd.next;
        oddstart.next=null;
        return even.next;
    }

    public static int getNthFromLast(Node head, int n) {
        // Nth node from end of linked list --- https://www.geeksforgeeks.org/problems/nth-node-from-end-of-linked-list/1
        Node fast = head;
        Node slow = head;

        for (int i = 0; i < n; i++) {
            if (fast == null)
                return -1;
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow.data;
    }


    static Node mergeSort(Node head){
        // Merge Sort for Linked List -- https://www.geeksforgeeks.org/problems/sort-a-linked-list/1
        if(head==null || head.next==null) return head;
        
        Node middle = findMiddle(head);
        
        Node lhead = head;
        Node rhead = middle.next;
        middle.next = null;
            
        Node left = mergeSort(lhead);
        Node right = mergeSort(rhead);
        Node res = merge(left,right);
        return res;
        
        
    }
    public static Node findMiddle(Node head){
        Node slow=head;
        Node fast=head.next;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }

    static Node merge(Node left, Node right) {
        Node lcurr = left;
        Node rcurr = right;
        Node newHead = new Node(0);
        Node currHead = newHead;
        while (lcurr != null && rcurr != null) {
            if (lcurr.data <= rcurr.data) {
                currHead.next = lcurr;
                currHead = currHead.next;
                lcurr = lcurr.next;
            } else { //lcurr.data >= rcurr.data
                currHead.next = rcurr;
                currHead = currHead.next;
                rcurr = rcurr.next;
            }
        }
        while (lcurr == null && rcurr != null) {
            currHead.next = rcurr;
            currHead = currHead.next;
            rcurr = rcurr.next;
        }
        while (rcurr == null && lcurr != null) {
            currHead.next = lcurr;
            currHead = currHead.next;
            lcurr = lcurr.next;
        }
        return newHead.next;
    }

    public static Node segregate(Node head) {
        // Given a linked list of 0s, 1s and 2s, sort it -- https://www.geeksforgeeks.org/problems/given-a-linked-list-of-0s-1s-and-2s-sort-it/1
        Node list0 = new Node(-1);
        Node list1 = new Node(-1);
        Node list2 = new Node(-1);
        Node curr0 = list0;
        Node curr1 = list1;
        Node curr2 = list2;
        Node curr = head;
        while (curr != null) {
            if (curr.data == 0) {
                curr0.next = curr;
                curr0 = curr0.next;
            } else if (curr.data == 1) {
                curr1.next = curr;
                curr1 = curr1.next;
            } else {
                curr2.next = curr;
                curr2 = curr2.next;
            }
            Node temp = curr;
            curr = curr.next;
            temp.next = null;
        }
        curr1.next = list2.next;
        curr0.next = list1.next;
        return list0.next;
    }

    public static int intersectPoint(Node head1, Node head2) {
        // Intersection Point in Y Shaped Linked Lists -- https://www.geeksforgeeks.org/problems/intersection-point-in-y-shapped-linked-lists/1
        Node curr1 = head1;
        Node curr2 = head2;
        int count = 0;
        while (curr1 != null && curr2 != null) {
            curr1 = curr1.next;
            curr2 = curr2.next;
            count++;
        }
        //  System.out.println(count + " Count");
        if (curr2 == null) {
            int count1 = 0;
            curr1 = head1;
            while (curr1 != null) {
                curr1 = curr1.next;
                count1++;
            }
            // System.out.println(count1 + " Count1");
            int diff = count1 - count;
            int i = 0;
            curr1 = head1;
            while (i < diff) {
                curr1 = curr1.next;
                i++;
            }
            // System.out.println(curr1.data + " curr1.data");
            curr2 = head2;
            while (curr1 != null && curr2 != null) {
                if (curr1 == curr2) {
                    return curr1.data;
                }
                curr1 = curr1.next;
                curr2 = curr2.next;
            }
        }
        if (curr1 == null) {
            int count2 = 0;
            curr2 = head2;
            while (curr2 != null) {
                curr2 = curr2.next;
                count2++;
            }
            // System.out.println(count1 + " Count1");
            int diff = count2 - count;
            int i = 0;
            curr2 = head2;
            while (i < diff) {
                curr2 = curr2.next;
                i++;
            }
            // System.out.println(curr1.data + " curr1.data");
            curr1 = head1;
            while (curr1 != null && curr2 != null) {
                if (curr1 == curr2) {
                    return curr1.data;
                }
                curr1 = curr1.next;
                curr2 = curr2.next;
            }
        }

        return -1;
    }
    
    public static Node addOne(Node head) {
        // Add 1 to a number represented as linked list -- https://www.geeksforgeeks.org/problems/add-1-to-a-number-represented-as-linked-list/1
        int carry = helper(head);
        if(carry == 1){
            Node node = new Node(1);
            node.next = head;
            head = node;
        }
        return head;
    }

    public static int helper(Node temp) {
        if (temp == null) {
            return 1;
        }
        int carry = helper(temp.next);
        temp.data = carry + temp.data;
        if (temp.data < 10) {
            return 0;
        } else {
            temp.data = 0;
            return 1;
        }
    }
    static Node addTwoLists(Node num1, Node num2){
        // Add two numbers represented by linked lists -- https://www.geeksforgeeks.org/problems/add-two-numbers-represented-by-linked-lists/1 
        Node curr1 = reverse(num1);
        Node curr2 = reverse(num2);
        
        Node head = new Node(-1);
        Node curr = head;
        
        int carry = 0;
        while(curr1 != null && curr2 != null){
            int sum = curr1.data + curr2.data + carry;
            int ones = sum % 10;
            carry = sum/10;
            Node node = new Node(ones);
            curr.next = node;
            curr = curr.next;
            
            curr1 = curr1.next;
            curr2 = curr2.next;
        }
        while(curr1 != null){
            int sum = curr1.data + carry;
            int ones = sum % 10;
            carry = sum/10;
            Node node = new Node(ones);
            curr.next = node;
            curr = curr.next;
            curr1 = curr1.next;
        }
        while(curr2 != null){
            int sum = curr2.data + carry;
            int ones = sum % 10;
            carry = sum/10;
            Node node = new Node(ones);
            curr.next = node;
            curr = curr.next;
            curr2 = curr2.next;
        }
        Node node = new Node(carry);
        curr.next = node;
        curr = curr.next;
        
        head = head.next;
        Node p = reverse(head);
        while(p.data==0 && p.next!=null){
            p = p.next;
        }
        return p;
    }

    public static Node reverse(Node head) {
        Node curr = head;
        Node next = head;
        Node prev = null;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static Node deleteAllOccurOfX(Node head, int x) {
        // Delete all occurrences of a given key in a doubly linked list -- https://www.geeksforgeeks.org/problems/delete-all-occurrences-of-a-given-key-in-a-doubly-linked-list/1
        Node curr = head;
        while (curr != null) {
            if (curr.data != x) {
                head = curr;
                if (head.prev != null) {
                    head.prev.next = null;
                    head.prev = null;
                }
                break;
            }
            curr = curr.next;
        }

        while (curr != null) {
            if (curr.data == x) {
                if (curr.prev != null) {
                    curr.prev.next = curr.next;
                }
                if (curr.next != null) {
                    curr.next.prev = curr.prev;
                }
                curr.prev = null;
            }

            curr = curr.next;
        }
        return head;
    }

    static Node deleteAllOccurOfX(Node head, int x) {
        // Delete all occurrences of a given key in a doubly linked list -- https://www.geeksforgeeks.org/problems/delete-all-occurrences-of-a-given-key-in-a-doubly-linked-list/1
        Node curr = head;
        while (curr != null) {
            if (curr.data == x) {
                if (curr == head) {
                    head = head.next;
                }
                Node nextNode = curr.next;
                Node prevNode = curr.prev;
                if (nextNode != null) {
                    nextNode.prev = prevNode;
                }
                if (prevNode != null) {
                    prevNode.next = nextNode;
                }
                curr = nextNode;
            } else {
                curr = curr.next;
            }

        }
        return head;
    }
    
    public static ArrayList<ArrayList<Integer>> findPairsWithGivenSum(int target, Node head) {
        //Find pairs with given sum in doubly linked list -- https://www.geeksforgeeks.org/problems/find-pairs-with-given-sum-in-doubly-linked-list/1 
        Node tail = head;
        Node curr = head;
        ArrayList<ArrayList<Integer>> aoa = new ArrayList<ArrayList<Integer>>();
        while (tail.next != null) {
            tail = tail.next;
        }
        // System.out.println(tail.data);
        while (curr.data < tail.data) {
            if ((curr.data + tail.data) == target) {
                ArrayList<Integer> a = new ArrayList<>();
                a.add(curr.data);
                a.add(tail.data);
                aoa.add(a);
                curr = curr.next;
                tail = tail.prev;
            } else if ((curr.data + tail.data) > target) {
                tail = tail.prev;
            } else {
                curr = curr.next;
            }
        }
        return aoa;
    }
    public static Node removeDuplicates(Node head){
        // Remove duplicates from a sorted doubly linked list -- https://www.geeksforgeeks.org/problems/remove-duplicates-from-a-sorted-doubly-linked-list/1
        Node curr = head;
        while (curr != null) {
            if (curr.next != null && curr.data == curr.next.data) {
                if (curr == head) {
                    head = head.next;
                }
                Node nextNode = curr.next;
                Node prevNode = curr.prev;
                if (nextNode != null) {
                    nextNode.prev = prevNode;
                }
                if (prevNode != null) {
                    prevNode.next = nextNode;
                }
                curr = nextNode;
            } else {
                curr = curr.next;
            }

        }
        return head;
    }
}
