import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] ipaddressoctets = new int[4];
        int mask;
        Scanner scanner = new Scanner(System.in);
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
        while (mask > 32 || mask < 0){
            System.out.println("Please give correct number for subnet mask");
            mask = scanner.nextInt();
        }
        subnetCalculator calculator = new subnetCalculator(ipaddressoctets,mask);
        int[] subnetOctet = calculator.convertMaskToOctets();
        for (int i = 0; i < 4; i++) {
            System.out.println(subnetOctet[i]);
        }
        ipClass classNetwork = calculator.classDefinition();
        System.out.println("Class for this ip: " + classNetwork.getClassWord());
        System.out.println(classNetwork.print());
    }

}
