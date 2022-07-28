import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

    public class ParkingLotTest {
        private ParkingLot parkingLot;

//    @BeforeEach
//    void setUp() {
//        parkingLot = new ParkingLot();
//    }

        @Test
        void shouldReturnYesWhenAvailableIs4() {
            parkingLot = new ParkingLot(4);

            String actualResult = parkingLot.parkACar();

            assertEquals("Yes",actualResult);
        }

        @Test
        void shouldFailToReturnNoWhenAvailableIs3() {
            parkingLot = new ParkingLot(3);

            String actualResult = parkingLot.parkACar();

            assertEquals("No",actualResult);
        }

        @Test
        void shouldReturnNoWhenAvailableIs0() {
            parkingLot = new ParkingLot(0);

            String actualResult = parkingLot.parkACar();

            assertEquals("No",actualResult);
        }
}

