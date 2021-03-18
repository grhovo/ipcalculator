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
}
