import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class AddressbookTest {
    private IAddressbook book;
    private IAddress addr1;
    private IAddress addr2;
    private IAddress addr3;

    @Before
    public void setUp() throws Exception {
        book = new Addressbook();
        addr1 = new Address("key1", "First1", "Last1", "mail1");
        addr2 = new Address("key2", "First2", "Last2", "mail2");
        addr3 = new Address("key3", "First3", "Last3", "mail3");
    }
    
    @Test
    public void constructor() {
        assertEquals(0, book.size());
    }

    @Test
    public void testAddAddress() {
        book.addAddress(addr1);
        assertEquals(1, book.size());
        assertSame(addr1, book.addressOf("key1"));
    }
    
    @Test
    public void addMore() {
        book.addAddress(addr1);
        book.addAddress(addr2);
        book.addAddress(addr3);
        assertEquals(3, book.size());
        assertSame(addr1, book.addressOf("key1"));
        assertSame(addr2, book.addressOf("key2"));
        assertSame(addr3, book.addressOf("key3"));
    }

    @Test
    public void createAddress() {
        book.addAddress("key1", "First1", "Last1", "mail1");
        IAddress addr = book.addressOf("key1");
        assertEquals("key1", addr.nickname());
        assertEquals("First1", addr.firstname());
        assertEquals("Last1", addr.lastname());
        assertEquals("mail1", addr.mailAddress());
    }

    @Test
    public void testContains() {
        book.addAddress(addr1);
        book.addAddress(addr2);
        book.addAddress(addr3);
        assertTrue(book.contains("key1"));
        assertTrue(book.contains("key2"));
        assertTrue(book.contains("key3"));
        assertFalse(book.contains("key4"));
    }

    @Test
    public void testGetNicknames() {
        book.addAddress(addr1);
        book.addAddress(addr2);
        book.addAddress(addr3);
        List<String> names = book.nicknames();
        assertEquals(3, names.size());
        assertTrue(names.contains("key1"));
        assertTrue(names.contains("key2"));
        assertTrue(names.contains("key3"));
   }
    
    @Test
    public void testRemove() {
        book.addAddress(addr1);
        book.addAddress(addr2);
        book.addAddress(addr3);
        book.remove("key2");
        assertEquals(2, book.size());
        assertTrue(book.contains("key1"));
        assertFalse(book.contains("key2"));
        assertTrue(book.contains("key3"));
        book.remove("key2");
        assertEquals(2, book.size());
        book.remove("key3");
        book.remove("key1");
        assertEquals(0, book.size());
    }
}
