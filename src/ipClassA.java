public class ipClassA extends ipClass{
    public ipClassA(){
        super.classWord = "A";
    }
    @Override
    public String print() {
        return "It is used for a large network\n" +
                "It has 1 network bit and 3 host bits\n" +
                "0–127.0–255.0–255.0–255\n" +
                "[ N ]. [ H ] .[ H ] .[ H ]\n" +
                "The 1st IP (i.e. is 0.0.0.0–0.255.255.255 in this case) is called the network id\n" +
                "The last IP (i.e. is 127.0.0.0–127.255.255.255 in this case) is called the broadcast id\n" +
                "Network ID: This is the address that identifies the subnet of a host. Broadcast ID: An IP Address that allows information to be sent to all machines on a given subnet rather than a specific machine.";
    }
}
