package MainPackage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void checkSerialNumberFormat() {
        Item test1 = new Item();
        test1.serialNumber = "A-1A2-B3C-4dg";
        assertTrue(test1.checkSerialNumberFormat(test1.serialNumber));

        Item test2 = new Item();
        test2.serialNumber = "A-1A2-B3C";
        assertFalse(test2.checkSerialNumberFormat(test2.serialNumber));
    }
}