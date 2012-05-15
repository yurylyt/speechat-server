package co.speechat.data;

import java.util.LinkedList;
import java.util.List;

/**
 * Author: Iurii Lytvynenko
 */
public class PasswordNameGenerator implements NameGenerator {
    private List<String> generated = new LinkedList<String>();
    
    @Override
    public String generate() {
        String name;
        do {
            int length = (int) ((Math.random() * 10) + 3);
            name = new PasswordGenerator().generate(length);
        } while (generated.contains(name));
        generated.add(name);
        return name;
    }
}
