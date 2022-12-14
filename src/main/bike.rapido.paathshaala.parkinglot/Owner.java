import java.util.ArrayList;
import java.util.List;

public class Owner implements ParkingLotObserver {
    List<ParkingLot> parkingLots = new ArrayList<>();
    ParkingAttendant parkingAttendant ;
    SecurityPersonnel securityPersonnel;

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public void setParkingLots(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public void createParkingLots(int[] capacitiesOfParkingLots ){
        int i=1;
        for(int capacityOfParkingLot: capacitiesOfParkingLots){
            parkingLots.add(new ParkingLot(capacityOfParkingLot,i++));
        }
    }
    @Override
    public void notifyParkingLotIsFull() {
        System.out.println("Put Full Sign Board");
    }
    @Override
    public void notifyParkingLotIsBackAvailable() {
        System.out.println("Remove Full Sign Board");
    }
    public void subscribeToParkingLotSpace(){
        for(ParkingLot parkingLot:parkingLots)
        parkingLot.subscribeToParkingLotObserversList(this);
    }
    public SecurityPersonnel associateToASecurityPersonnel(){
        return this.securityPersonnel = new SecurityPersonnel(parkingLots);
    }
    public void setParkingLot(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }
}
