public class Owner implements Observer{
    boolean isParkingLotFull ;
    ParkingLot parkingLot;

    public boolean checkIfParkingLotIsFull()
    {
        return isParkingLotFull;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public void notifyParkingLotIsFull() {
        isParkingLotFull = true;
        System.out.println("Put Full Sign Board");
    }

    @Override
    public void notifyParkingLotIsBackAvailable() {
        isParkingLotFull = false;
        System.out.println("Remove Full Sign Board");
    }

    public void subscribe(){
        this.parkingLot.attach(this);
    }

    public boolean checkIfParkingLotIsAvailable() {
        return !isParkingLotFull;
    }
}
