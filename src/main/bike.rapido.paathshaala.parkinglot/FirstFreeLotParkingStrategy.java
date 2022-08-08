import java.util.List;
import java.util.Optional;

public class FirstFreeLotParkingStrategy implements ParkingStrategy{
    private List<ParkingLot> parkingLots;

    public FirstFreeLotParkingStrategy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    @Override
    public ParkingLot getParkingLot() {
        for(ParkingLot parkingLot : parkingLots){
            if(parkingLot.getParkingSlotsAvailable()>0){
                return parkingLot;
            }
        }
        return null;
    }
}
