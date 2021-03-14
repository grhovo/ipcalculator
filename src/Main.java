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
        int[] subnetOctet = convertMaskToOctets(mask);
        for (int i = 0; i < 4; i++) {
            System.out.println(subnetOctet[i]);
        }
        ipClass classNetwork = classDefinition(ipaddressoctets[0]);
        System.out.println("Class for this ip: " + classNetwork.getClassWord());
        System.out.println(classNetwork.print());
    }

    public static ipClass classDefinition(int ipaddressoctet) {
        ipClass classNetwork;
        if(ipaddressoctet < 128){
            classNetwork = new ipClassA();
        }
        else if(ipaddressoctet < 192){
            classNetwork = new ipClassB();
        }
        else if (ipaddressoctet < 224){
            classNetwork = new ipClassC();
        }
        else if(ipaddressoctet < 240){
            classNetwork = new ipClassD();
        }
        else {
            classNetwork = new ipClassE();
        }
        return classNetwork;
    }

    public static int[] convertMaskToOctets(int mask){
        int maskChangedOctets = mask % 8;
        int fullQualifiedOctets = mask/8;
        int changedOctet;
        int[] subnetOctet = new int[4];
        changedOctet = switch (maskChangedOctets) {
            case 0 -> 0;
            case 1 -> 128;
            case 2 -> 192;
            case 3 -> 224;
            case 4 -> 240;
            case 5 -> 248;
            case 6 -> 252;
            case 7 -> 254;
            default -> throw new IllegalStateException("Unexpected value: " + maskChangedOctets);
        };
        for(int i=0; i<4; i++){
            if(i<fullQualifiedOctets){
                subnetOctet[i] = 255;
            }
            else if(i==fullQualifiedOctets){
                subnetOctet[i] = changedOctet;
            }
            else {
                subnetOctet[i] = 0;
            }
        }

        return subnetOctet;
    }

}
