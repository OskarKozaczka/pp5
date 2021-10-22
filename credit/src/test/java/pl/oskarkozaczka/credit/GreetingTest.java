package pl.oskarkozaczka.credit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetingTest {
    @Test
    public void itGreetsUser() {
        //Arrange
        String name = "Cebo";
        Greater greater = new Greater();
        //Act
        String greetingText = greater.hello(name);
        //Assert
        assertEquals("Hello Cebo!",greetingText);
    }
}
