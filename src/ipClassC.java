public class ipClassC extends ipClass{
    public ipClassC(){
        super.classWord = "C";
    }
    @Override
    public String print() {
        return "It is used for a small network\n" +
                "It has 1 network bit and 3 host bits\n" +
                "192–223.0–255.0–255.0–255\n" +
                "[ N ] . [ N ] . [ N ] . [ H ]\n" +
                "The first IP (i.e. is 192.0.0.0–192.255.255.255 in this case) is called the network id.\n" +
                "The last IP (i.e. is 223.0.0.0–223.255.255.255 in this case) called the broadcast id.";
    }
}
