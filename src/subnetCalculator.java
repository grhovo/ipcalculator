public class subnetCalculator {
    private int[] ipaddressoctets;
    private int mask;

    public subnetCalculator(int[] ipaddressoctets, int mask) {
        this.ipaddressoctets = ipaddressoctets;
        this.mask = mask;
    }

    public int[] getIpaddressoctets() {
        return ipaddressoctets;
    }
    // define class(A,B,C,D,E)
    public ipClass classDefinition() {
        ipClass classNetwork;
        if(ipaddressoctets[0] < 128){
            classNetwork = new ipClassA();
        }
        else if(ipaddressoctets[0] < 192){
            classNetwork = new ipClassB();
        }
        else if (ipaddressoctets[0] < 224){
            classNetwork = new ipClassC();
        }
        else if(ipaddressoctets[0] < 240){
            classNetwork = new ipClassD();
        }
        else {
            classNetwork = new ipClassE();
        }
        return classNetwork;
    }
    //Convert Integer mask to x.x.x.x form, where x is integer
    public int[] convertMaskToOctets(){
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
    
    public int getMask() {
        return mask;
    }
    // Get Network Address
    public int[] networkIP(int[] maskOctet){
        int[] network = new int[4];
        for (int i = 0; i < 4; i++) {
            network[i] = ipaddressoctets[i] & maskOctet[i];
        }
        return network;
    }
    // Get Broadcast Address
    public int[] broadcastIP(int networkIP[]){
        int[] broadcast = new int[4];
        if(mask<=8){
            broadcast[0] = networkIP[0] + ((int) Math.pow(2,8-mask) )- 1;
            broadcast[1] = 255;
            broadcast[2] = 255;
            broadcast[3] = 255;
        }
        else if(mask <= 16){
            broadcast[0] = networkIP[0];
            broadcast[1] = networkIP[1] + ((int) Math.pow(2,16-mask) )- 1;
            broadcast[2] = 255;
            broadcast[3] = 255;
        }
        else if(mask <= 24){
            broadcast[0] = networkIP[0];
            broadcast[1] = networkIP[1];
            broadcast[2] = networkIP[2] + ((int) Math.pow(2,24-mask) )- 1;
            broadcast[3] = 255;
        }
        else {
            broadcast[0] = networkIP[0];
            broadcast[1] = networkIP[1];
            broadcast[2] = networkIP[2];
            broadcast[3] = networkIP[3] + ((int) Math.pow(2,32-mask) )- 1;
        }
        return broadcast;
    }
    // It's for first IP, just add 1 to last octet of ip address
    public int[] firstIP(int networkIP[]){
        int[] first = new int[4];
        first[0] = networkIP[0];
        first[1] = networkIP[1];
        first[2] = networkIP[2];
        first[3] = networkIP[3]+1;
        return first;
    }
    //Find last IP address
    public int[] lastIP(int broadcastIP[]){
        int[] last = new int[4];
        last[0] = broadcastIP[0];
        last[1] = broadcastIP[1];
        last[2] = broadcastIP[2];
        last[3] = broadcastIP[3]-1;
        return last;
    }
}
