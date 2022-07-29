public class ParkingLot {
    int parkingSlots;

    ParkingLot(int avaialableSlots)
    {
        this.parkingSlots = avaialableSlots;
    }

    public String parkACar()
    {
        if(parkingSlots>0) {
            parkingSlots -= 1;
            return "Yes";
        }
        return "No";
    }
}
