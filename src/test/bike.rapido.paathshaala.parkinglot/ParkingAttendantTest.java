import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingAttendantTest {
    @Test
    void shouldParkTheCarInFirstAndSecondLotUniformly() {
        int[] lotCapacities = {2,2};
        Owner owner = new Owner();
        owner.createParkingLots(lotCapacities);
        ParkingAttendant parkingAttendant = owner.employNewParkingAttendant();
        Vehicle firstCar = new Vehicle();
        Vehicle secondCar = new Vehicle();
        ParkingLot parkedParkingLotOfFirstCar = parkingAttendant.park(firstCar);
        ParkingLot parkedParkingLotOfSecondCar = parkingAttendant.park(secondCar);

        assertEquals(1,parkedParkingLotOfFirstCar.getId());
        assertEquals(2,parkedParkingLotOfSecondCar.getId());
    }

    @Test
    void shouldUnParkTheCarOnlyIfItIsParked() {
        int[] lotCapacities = {2,2};
        Owner owner = new Owner();
        owner.createParkingLots(lotCapacities);
        ParkingAttendant parkingAttendant = owner.employNewParkingAttendant();
        Vehicle car = new Vehicle();
        ParkingLot parkedParkingLot = parkingAttendant.park(car);
        ParkingLot unparkedParkingLot = parkingAttendant.unpark(car);

        assertNotNull(unparkedParkingLot);
    }

    @Test
    void shouldNotUnparkTheCarIfItIsAlreadyUnParked() {
        int[] lotCapacities = {2,2};
        Owner owner = new Owner();
        owner.createParkingLots(lotCapacities);
        ParkingAttendant parkingAttendant = owner.employNewParkingAttendant();
        Vehicle car = new Vehicle();
        ParkingLot parkedParkingLot = parkingAttendant.park(car);
        ParkingLot unparkedParkingLot = parkingAttendant.unpark(car);

        ParkingLot unparkSameCarTwice = parkingAttendant.unpark(car);

        assertNull(unparkSameCarTwice);
    }

    @Test
    void shouldParkFourConsecutiveCarsInFourDifferentParkingLots() {
        int[] lotCapacities = {3,3,3,3};
        Owner owner = new Owner();
        owner.createParkingLots(lotCapacities);
        ParkingAttendant parkingAttendant = owner.employNewParkingAttendant();
        Vehicle car1 = new Vehicle();
        Vehicle car2 = new Vehicle();
        Vehicle car3 = new Vehicle();
        Vehicle car4 = new Vehicle();

        ParkingLot firstCarParkedLot = parkingAttendant.park(car1);
        ParkingLot secondCarParkedLot = parkingAttendant.park(car2);
        ParkingLot thirdCarParkedLot = parkingAttendant.park(car3);
        ParkingLot fourthCarParkedLot = parkingAttendant.park(car4);

        assertEquals(1,firstCarParkedLot.getId());
        assertEquals(2,secondCarParkedLot.getId());
        assertEquals(3,thirdCarParkedLot.getId());
        assertEquals(4,fourthCarParkedLot.getId());
    }
    @Test
    void shouldParkCarsFollowingUniformDistribution() {
        int[] lotCapacities = {3,3,3};
        Owner owner = new Owner();
        owner.createParkingLots(lotCapacities);
        ParkingAttendant parkingAttendant = owner.employNewParkingAttendant();
        Vehicle car1 = new Vehicle();
        Vehicle car2 = new Vehicle();
        Vehicle car3 = new Vehicle();
        Vehicle car4 = new Vehicle();
        Vehicle car5 = new Vehicle();
        Vehicle car6 = new Vehicle();
        Vehicle car7 = new Vehicle();
        Vehicle car8 = new Vehicle();
        ParkingLot firstCarParkedLot = parkingAttendant.park(car1);
        ParkingLot secondCarParkedLot = parkingAttendant.park(car2);
        ParkingLot thirdCarParkedLot = parkingAttendant.park(car3);
        ParkingLot fourthCarParkedLot = parkingAttendant.park(car4);
        ParkingLot fifthCarParkedLot = parkingAttendant.park(car5);
        ParkingLot sixthCarParkedLot = parkingAttendant.park(car6);
        ParkingLot thirdCarUnParkedLot = parkingAttendant.unpark(car3);
        ParkingLot fifthCarUnParkedLot = parkingAttendant.unpark(car5);
        ParkingLot seventhCarParkedLot = parkingAttendant.park(car7);
        ParkingLot eighthCarParkedLot = parkingAttendant.park(car8);

        assertEquals(1,firstCarParkedLot.getId());
        assertEquals(2,secondCarParkedLot.getId());
        assertEquals(3,thirdCarParkedLot.getId());
        assertEquals(1,fourthCarParkedLot.getId());
        assertEquals(2,fifthCarParkedLot.getId());
        assertEquals(3,sixthCarParkedLot.getId());
        assertEquals(3,thirdCarUnParkedLot.getId());
        assertEquals(2,fifthCarUnParkedLot.getId());
        assertEquals(2,seventhCarParkedLot.getId());
        assertEquals(3,eighthCarParkedLot.getId());
    }
}