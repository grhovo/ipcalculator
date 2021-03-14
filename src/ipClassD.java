public class ipClassD extends ipClass{
    public ipClassD(){
        super.classWord = "D";
    }
    @Override
    public String print() {
        return "Broadcasting and research range,\n" +
                "This IP series is not available for WAN or LAN";
    }
}
