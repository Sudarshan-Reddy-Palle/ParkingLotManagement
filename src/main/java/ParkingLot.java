public class ParkingLot {
    int parkingSlotsAvailable;

    ParkingLot(int totalSlots)
    {
        this.parkingSlotsAvailable = totalSlots;
    }

    public void park(){
        parkingSlotsAvailable -= 1;
    }

    public String checkForParking()
    {
        if(parkingSlotsAvailable >0) {
            return "Yes";
        }
        return "No";
    }
}
