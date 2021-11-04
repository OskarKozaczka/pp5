package pl.mbruzda.misc;

import org.junit.jupiter.api.Test;
import pl.mbruzda.misc.Greeter;

import static org.junit.jupiter.api.Assertions.*;

public class GreetingTest {
    @Test
    public void itGreetsUser(){
        //Arrange
        String name = "Marcin";
        Greeter greeter = new Greeter();
        //Act
        String greetingText = greeter.hello("Marcin");
        //Assertions
        assertEquals("Hello Marcin!", greetingText);
    }
}
