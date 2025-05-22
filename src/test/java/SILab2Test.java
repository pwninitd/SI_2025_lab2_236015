import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class SILab2Test {
    private final SILab2 siLab2 = new SILab2();

    @Test
    void testEveryStatement(){
        List<Item> allItems = new ArrayList<Item>();
        Item item = new Item("", 1,1,0.7);
        allItems.add(item);

        RuntimeException e = assertThrows(RuntimeException.class, ()->SILab2.checkCart(null, ""));
        assertTrue(e.getMessage().contains("allItems list can't be null!"));

        e = assertThrows(RuntimeException.class, ()->SILab2.checkCart(allItems, ""));
        assertTrue(e.getMessage().contains("Invalid item!"));

        item = new Item("Klima", 1,1,0.7);
        allItems.removeFirst();
        allItems.add(item);
        e = assertThrows(RuntimeException.class, ()->SILab2.checkCart(allItems, "1a34567891234567"));
        assertTrue(e.getMessage().contains("Invalid character in card number!"));

        e = assertThrows(RuntimeException.class, ()->SILab2.checkCart(allItems, null));
        assertTrue(e.getMessage().contains("Invalid card number!"));

        item = new Item("Klima", 1,1,0);
        allItems.removeFirst();
        allItems.add(item);
        assertEquals(1,SILab2.checkCart(allItems, "1234567891234567"));
    }

    @Test
    void testMultipleCondition(){
        List<Item> allItems = new ArrayList<Item>();
        Item item = new Item("Klima", 1,301,0);
        allItems.add(item);

        assertEquals(271, SILab2.checkCart(allItems, "1234567891234567"));

        item = new Item("Klima", 11,30,0);
        allItems.removeFirst();
        allItems.add(item);
        assertEquals(300, SILab2.checkCart(allItems, "1234567891234567"));

        item = new Item("Klima", 1,35,0.1);
        allItems.removeFirst();
        allItems.add(item);
        assertEquals(1.5, SILab2.checkCart(allItems, "1234567891234567"));

        item = new Item("Klima", 0,0,0);
        allItems.removeFirst();
        allItems.add(item);
        assertEquals(0, SILab2.checkCart(allItems, "1234567891234567"));

    }
}
