package pl.oskarkozaczka.letters;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CharCounterTest {
    @Test
    public void itCountCharsInInputString(){
        String inputString = "Ala ma kota";
        CharCounter charCounter = new CharCounter();

        int charCount = charCounter.count('a', inputString);
        assertEquals(4, charCount);
    }
}
