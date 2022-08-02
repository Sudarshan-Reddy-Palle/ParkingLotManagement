public class SecurityPersonnal implements Observer{
    ParkingLot parkingLot;
    boolean isParkingLotFull;

    public void setParkingLot(ParkingLot parkingLot)
    {
        this.parkingLot = parkingLot;
    }

    public boolean checkIfParkingLotIsFull()
    {
        return isParkingLotFull;
    }

    @Override
    public void notifyParkingLotIsFull() {
        isParkingLotFull = true;
        System.out.println("Redirect");
    }

    @Override
    public void notifyParkingLotIsBackAvailable() {

    }

    public void subscribe() {
        this.parkingLot.attach(this);
    }
}
