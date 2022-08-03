import java.util.ArrayList;
import java.util.List;

public class SecurityPersonnel implements ParkingLotObserver {
    List<ParkingLot> parkingLots = new ArrayList<>();

    SecurityPersonnel(List<ParkingLot> parkingLots)
    {
        this.parkingLots = parkingLots;
    }

    @Override
    public void notifyParkingLotIsFull() {
        System.out.println("Redirect");
    }

    @Override
    public void notifyParkingLotIsBackAvailable() {

    }

    public void subscribeToParkingLotSpace(){
        for(ParkingLot parkingLot:parkingLots)
            parkingLot.subscribeToParkingLotObserversList(this);
    }
}
