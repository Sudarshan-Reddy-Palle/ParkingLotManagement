import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkingAttendantWithEvenlyDistributedParkingStrategyTest {

    private Owner owner;
    @BeforeEach
    void setUp() {
        owner = new Owner();
    }
    @Test
    void shouldParkTheCarInFirstAndSecondLotUniformly() {
        int[] lotCapacities = {2,2};
        owner.createParkingLots(lotCapacities);
        List<ParkingLot> parkingLots = owner.getParkingLots();
        ParkingAttendant parkingAttendant = new ParkingAttendant(parkingLots);
        ParkingStrategy parkingStrategy = new EvenlyDistributedParkingStrategy(parkingLots);
        parkingAttendant.setParkingStrategy(parkingStrategy);

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
        List<ParkingLot> parkingLots = owner.getParkingLots();
        ParkingAttendant parkingAttendant = new ParkingAttendant(parkingLots);
        ParkingStrategy parkingStrategy = new EvenlyDistributedParkingStrategy(parkingLots);
        parkingAttendant.setParkingStrategy(parkingStrategy);
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
        List<ParkingLot> parkingLots = owner.getParkingLots();
        ParkingAttendant parkingAttendant = new ParkingAttendant(parkingLots);
        ParkingStrategy parkingStrategy = new EvenlyDistributedParkingStrategy(parkingLots);
        parkingAttendant.setParkingStrategy(parkingStrategy);
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
        List<ParkingLot> parkingLots = owner.getParkingLots();
        ParkingAttendant parkingAttendant = new ParkingAttendant(parkingLots);
        ParkingStrategy parkingStrategy = new EvenlyDistributedParkingStrategy(parkingLots);
        parkingAttendant.setParkingStrategy(parkingStrategy);
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
    void shouldParkCarsAccordingToStrategyChange() {
        int[] lotCapacities = {3,3,3,3};
        Owner owner = new Owner();
        owner.createParkingLots(lotCapacities);
        List<ParkingLot> parkingLots = owner.getParkingLots();
        ParkingAttendant parkingAttendant = new ParkingAttendant(parkingLots);
        ParkingStrategy parkingStrategy = new EvenlyDistributedParkingStrategy(parkingLots);
        parkingAttendant.setParkingStrategy(parkingStrategy);
        Vehicle car1 = new Vehicle();
        Vehicle car2 = new Vehicle();
        Vehicle car3 = new Vehicle();
        Vehicle car4 = new Vehicle();
        Vehicle car5 = new Vehicle();
        Vehicle car6 = new Vehicle();

        ParkingLot firstCarParkedLot = parkingAttendant.park(car1);
        ParkingLot secondCarParkedLot = parkingAttendant.park(car2);
        ParkingLot thirdCarParkedLot = parkingAttendant.park(car3);
        ParkingLot fourthCarParkedLot = parkingAttendant.park(car4);
        parkingStrategy = new FirstFreeLotParkingStrategy(parkingLots);
        parkingAttendant.setParkingStrategy(parkingStrategy);
        ParkingLot fifthCarParkedLot = parkingAttendant.park(car5);
        ParkingLot sixthCarParkedLot = parkingAttendant.park(car6);

        assertEquals(1,firstCarParkedLot.getId());
        assertEquals(2,secondCarParkedLot.getId());
        assertEquals(3,thirdCarParkedLot.getId());
        assertEquals(4,fourthCarParkedLot.getId());
        assertEquals(1,fifthCarParkedLot.getId());
        assertEquals(1,sixthCarParkedLot.getId());
    }
}