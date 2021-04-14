import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // ip address definition in array
        int[] ipaddressoctets = new int[4];
        // mask in integer
        int mask;
        Scanner scanner = new Scanner(System.in);
        // define variables
        for(int i = 0; i < 4; i++) {
            System.out.println("Give ip address of octet " + (i + 1));
            ipaddressoctets[i] = scanner.nextInt();
            while (ipaddressoctets[i] > 255 || ipaddressoctets[i] < 0) {
               System.out.println("Please give correct number for octet");
               ipaddressoctets[i] = scanner.nextInt();
            }
        }
        System.out.println("Give subnet mask");
        mask = scanner.nextInt();
        //check is the number is correct
        while (mask > 32 || mask < 0){
            System.out.println("Please give correct number for subnet mask");
            mask = scanner.nextInt();
        }
        //Subnet calculator class definition
        subnetCalculator calculator = new subnetCalculator(ipaddressoctets,mask);
        //Integer mask to x.x.x.x form, where x is integer
        int[] subnetOctet = calculator.convertMaskToOctets();
        for (int i = 0; i < 4; i++) {
            System.out.println(subnetOctet[i]);
        }
        //Class definition
        ipClass classNetwork = calculator.classDefinition();
        System.out.println("Class for this ip: " + classNetwork.getClassWord());
        System.out.println(classNetwork.print());
        int[] networkIP = calculator.networkIP(subnetOctet);
        for (int i = 0; i < 4; i++) {
            System.out.print(networkIP[i] + ".");
        }
        System.out.println();
        int[] firstIP = calculator.firstIP(networkIP);
        for (int i = 0; i < 4; i++) {
            System.out.print(firstIP[i] + ".");
        }
        System.out.println();
        int[] broadcastIP = calculator.broadcastIP(networkIP);
        for (int i = 0; i < 4; i++) {
            System.out.print(broadcastIP[i] + ".");
        }
        System.out.println();
        int[] lastIP = calculator.lastIP(broadcastIP);
        for (int i = 0; i < 4; i++) {
            System.out.print(lastIP[i] + ".");
        }
        System.out.println();
    }

}
