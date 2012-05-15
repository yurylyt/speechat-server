package co.speechat.data;

/**
 * TODO: Document me
 * Author: Iurii Lytvynenko
 */
public class TimeBasedNameGenerator implements NameGenerator {
    @Override
    public String generate() {
        return "" + System.currentTimeMillis();
    }
}
