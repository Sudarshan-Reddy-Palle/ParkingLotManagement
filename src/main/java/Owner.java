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
        System.out.print("Full Sign Board");
    }

    public void subscribe(){
        this.parkingLot.attach(this);
    }
    public void unsubscribe()
    {
        this.parkingLot.detach(this);
    }
}
