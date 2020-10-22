package Com.FirstAssignment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Dmessages{

    Dmessages dmg;
    String invalidmessage="Incorrect option: ....";
    String invalidProject="Test Project";


    @Test
    byte invalidMessage() {
        assertEquals(invalidmessage,dmg.invalidMessage());
        return 0;
    }
}