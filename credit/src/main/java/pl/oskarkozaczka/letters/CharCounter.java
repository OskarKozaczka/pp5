package pl.oskarkozaczka.letters;


public class CharCounter {
    public int count(Character a, String inputString) {
        return (int) inputString.toLowerCase().chars()
                .mapToObj(c -> (char) c)
                .filter(c -> c.equals(a))
                .count();
    }
}
