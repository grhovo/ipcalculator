public class ipClassE extends ipClass{
    public ipClassE(){
        super.classWord = "D";
    }
    @Override
    public String print() {
        return "This series is only for R&D and military,\n " +
                "This IP series is not available for WAN or LAN";
    }
}
