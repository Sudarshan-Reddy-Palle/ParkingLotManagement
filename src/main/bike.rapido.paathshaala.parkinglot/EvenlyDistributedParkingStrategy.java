import java.util.List;

public class EvenlyDistributedParkingStrategy implements ParkingStrategy{
    private List<ParkingLot> parkingLots;

    public EvenlyDistributedParkingStrategy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    @Override
    public ParkingLot getParkingLot() {
        ParkingLot parkingLot = identifyLotWithMaximumAvailableSlots();
        return parkingLot;
    }
    private ParkingLot identifyLotWithMaximumAvailableSlots() {
        ParkingLot maxAvailableSlotedLot = null;
        int maxAvailableSlotSize = 0;
        for (ParkingLot parkingLot:parkingLots)
        {
            if(maxAvailableSlotSize<parkingLot.getParkingSlotsAvailable())
            {
                maxAvailableSlotSize = parkingLot.getParkingSlotsAvailable();
                maxAvailableSlotedLot = parkingLot;
            }
        }
        return maxAvailableSlotedLot;
    }
}
