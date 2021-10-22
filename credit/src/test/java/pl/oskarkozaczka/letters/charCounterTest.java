package pl.oskarkozaczka.letters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class charCounterTest {
    @Test
    public void itCountCharInInputString(){
        String inputString = "Cebo ma kota";
        CharCounter charCounter= new CharCounter();
        int charCount = charCounter.count('a',inputString);
        assertEquals(2,charCount);

    }
}
