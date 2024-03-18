import java.util.ArrayList;

public class BitwiseOperationsExample {
    public static void main(String[] args) {
        int a = 5; // Binary: 0101
        int b = 3; // Binary: 0011

        // Bitwise AND
        int resultAnd = a & b; // Binary: 0001
        System.out.println("Bitwise AND: " + resultAnd);

        // Bitwise OR
        int resultOr = a | b; // Binary: 0111
        System.out.println("Bitwise OR: " + resultOr);

        // Bitwise XOR
        int resultXor = a ^ b; // Binary: 0110
        System.out.println("Bitwise XOR: " + resultXor);

        // Bitwise complement
        int resultComplementA = ~a; // Binary: 11111111111111111111111111111010
        int resultComplementB = ~b; // Binary: 11111111111111111111111111111100
        System.out.println("Bitwise complement of a: " + resultComplementA);
        System.out.println("Bitwise complement of b: " + resultComplementB);

        // Left shift
        int resultLeftShift = a << 1; // Binary: 1010
        System.out.println("Left shift of a: " + resultLeftShift);

        // Right shift
        int resultRightShift = a >> 1; // Binary: 0010
        System.out.println("Right shift of a: " + resultRightShift);

        // Unsigned right shift
        int c = -5; // Binary: 11111111111111111111111111111011
        int resultUnsignedRightShift = c >>> 1; // Binary: 01111111111111111111111111111101
        System.out.println("Unsigned right shift of c: " + resultUnsignedRightShift);


        System.out.println("No of set Bits in Integer is: " + findSetBitsInInteger(13));

        System.out.println("No of  First set Bits in Integer is: " + findFirstSetBitInInteger(1));

        System.out.println("The number is power of 2 : " + checkWhetherNumberIsPowerOf2(0));
        
        System.out.println("Number of flips Required : " + countTheNoOfBitsToFlipAtoB(7,1));
    }

    

    public static int findSetBitsInInteger(int num) {
        // eg  13 = 01101 has 3 set bits , 2 = 10 has 1 set bit
        int count = 0;
        // while (num > 0) {
        //     int rem = num % 2; 
        //     if (rem == 1)
        //         count++;
        //     num = num/2;
        // }
        // Optimization with bit manupulation
        while (num > 0) {
            if ((num & 1) == 1) { // check if odd
                count++;
            }
            num = num >> 1; // divide by 2
        }

        return count;
    }

    public static int findFirstSetBitInInteger(int num) {
        // 2 = 010  , ans 1 ,, 3= 011 ans -1  ,, 4=100 ans 3 ,, 5=101 ans -1
        int count = 0;
        boolean one1 = true;
        while (num > 0) {
            if ((num & 1) == 0) {
                if (one1) {
                    count++;
                    num = num >> 1;
                } else {
                    num = num >> 1;
                }
            } else {
                if (one1 == false) {
                    count = -2;
                    break;
                }
                num = num >> 1;
                one1 = false;
            }
        }
        return count + 1;
    }
    
    public static boolean checkWhetherNumberIsPowerOf2(int num) {
        // eg 8 - 1000 --> 0111 & 1000 --> 0000
        if (num != 0 && (((num - 1) & num) == 0)) {
            return true;
        } else {
            return false;
        }
    }

    public static int countTheNoOfBitsToFlipAtoB(int a, int b) {
        //eg 5- 101 to 1- 001 ans 1
        int count = 0;
        int change = a ^ b; // 100
        while (change > 0) {
            if ((change & 1) == 0) { //even
                change = change >> 1;
            } else {
                count++;
                change = change >> 1;
            }
        }
        return count;
    }

    public static int findNonRepeatingElementsInArrayInPair(int arr[]) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = sum ^ arr[i];
        }
        return sum;
    }

    public static void find2NonRepeatingElementsInArrayInPair(int arr[]) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = (sum ^ arr[i]);
        }
        // Bitwise & the sum with it's 2's Complement
        // Bitwise & will give us the sum containing
        // only the rightmost set bit
        sum = (sum & -sum);
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & sum) > 0) {
                sum1 = (sum1 ^ arr[i]);
            } else {
                sum2 = (sum2 ^ arr[i]);
            }
        }
        System.out.println("The non-repeating elements are " + sum1 + " and " + sum2);
    }
    
    public static ArrayList<String> allPossibleStrings(String str) {
        ArrayList<String> arr = new ArrayList<>();
        int totalComb = (int) Math.pow(2, str.length());
        for (int i = 1; i < totalComb; i++) {
            int j = 0;
            String s = "";
            int x = i;
            while (x > 0) {
                if ((x & 1) == 1) {
                    s += str.charAt(j);
                }
                j++;
                x = x >> 1;
            }
            arr.add(s);
        }
        return arr;
    }
    
    public static boolean checkIfithBitIsSetOrNot(int num, int i) {
        int mask = 1 << (i - 1);
        if ((num & mask) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static int setTheithBit(int num, int i) {
        int mask = 1 << (i - 1);
        return num | mask;
    }
    public static int deleteTheithBit(int num, int i) {
        int mask = 1 << (i - 1);
        return num | ~mask;
    }
}

// For example, bitwise AND (&) can be used to efficiently check if a number is even or odd (e.g., x & 1 == 0 to check if x is even).

