package pl.oskarkozaczka.letters;

public class CharCounter {

    public int count(Character letter,String inputString) {
        return (int)inputString.chars().mapToObj(c -> (char) c).filter(c -> c.equals(letter)).count();
    }
}
