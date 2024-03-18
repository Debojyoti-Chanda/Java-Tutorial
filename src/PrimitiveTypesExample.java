public class PrimitiveTypesExample {
    public static void main(String[] args) {
        // Declaration and initialization of variables
        byte myByte = 10;
        short myShort = 20;
        int myInt = 30;
        long myLong = 40L; // Note the 'L' suffix to indicate a long literal
        float myFloat = 5.5f; // Note the 'f' suffix to indicate a float literal
        double myDouble = 10.123456789;
        char myChar = 'A';
        boolean myBoolean = true;

        // Performing some operations
        int sum = myInt + myShort;
        double product = myDouble * myFloat;
        long difference = myLong - myByte;
        boolean isEqual = myDouble == myFloat;

        // Displaying the results
        System.out.println("Sum: " + sum);
        System.out.println("Product: " + product);
        System.out.println("Difference: " + difference);
        System.out.println("Are myDouble and myFloat equal? " + isEqual);
    }

    /**
     * 
     * @param number
     * @return A binary String
     */

    // Method to convert an integer to binary
    public static String intToBinary(int number) {
        return Integer.toBinaryString(number);
    }

    /**
     * 
     * @param binaryString
     * @return int
     */
    // Method to convert binary to integer
    public static int binaryToInt(String binaryString) {
        return Integer.parseInt(binaryString, 2);
    }

     // Method to convert an integer to hexadecimal
    public static String intToHex(int number) {
         return Integer.toHexString(number);
    }
    
     // Method to convert hexadecimal to integer
     public static int hexToInt(String hexString) {
         return Integer.parseInt(hexString, 16);
     }
    
     // Method to convert a hexadecimal string to binary string
     public static String hexToBinary(String hexString) {
         // Convert hexadecimal string to integer
         int decimal = Integer.parseInt(hexString, 16);
         // Convert integer to binary string
         return Integer.toBinaryString(decimal);
     }
    
     
}
