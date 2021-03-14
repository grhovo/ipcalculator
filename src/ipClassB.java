public class ipClassB extends ipClass{
    public ipClassB(){
        super.classWord = "B";
    }
    @Override
    public String print() {
        return "It is used for a medium network\n" +
                "It has 2 network bits and 2 host bits\n" +
                "128–191.0–255.0–255.0–255\n" +
                "[ N ] . [ N ] . [ H ] . [ H ]\n" +
                "The first IP (i.e. is 128.0.0.0–128.255.255.255 in this case) is called the network id.\n" +
                "The last IP (i.e. is 191.0.0.0–191.255.255.255 in this case) called the broadcast id.\n";
    }

}
