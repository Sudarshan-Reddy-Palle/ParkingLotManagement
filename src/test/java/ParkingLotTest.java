import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

    public class ParkingLotTest {
        private ParkingLot parkingLot;

        @Test
        void shouldReturnYesWhenAvailableIs4() {
            int input = 4;
            parkingLot = new ParkingLot(input);

            String actualResult = parkingLot.parkACar();

            assertEquals("Yes",actualResult);
        }

        @Test
        void shouldFailToReturnNoWhenAvailableIs3() {
            int input = 3;
            parkingLot = new ParkingLot(input);

            String actualResult = parkingLot.parkACar();

            assertEquals("Yes",actualResult);
        }

        @Test
        void shouldReturnNoWhenAvailableIs0() {
            int input = 0;
            parkingLot = new ParkingLot(input);

            String actualResult = parkingLot.parkACar();

            assertEquals("No",actualResult);
        }
}

