import org.junit.*;

import static org.junit.Assert.*;

public class TestATM {

    private  static CityATM atm;

    @BeforeClass
    public static void init() {
        atm = new CityATM("ABCD");
        atm.add(60);
        atm.add(80);
        atm.add(110);
        atm.add(130);
    }
    @AfterClass
    public static void tearDowm() {
        atm = null;
    }

    @Test
    public void addmoney() {
        assertFalse(atm.add(32));
        assertFalse(atm.add(30));
        assertFalse(atm.add(-30));
        assertTrue(atm.add(60));
        assertTrue(atm.add(110));
        assertTrue(atm.add(130));
    }

    @Test
    public void withdrawal() {
        assertEquals(null,atm.withdrawal(30));
        assertEquals(null,atm.withdrawal(9));
        assertEquals(null,atm.withdrawal(-20));
        assertArrayEquals(new int[]{0,0,3}, atm.withdrawal(60));
        assertArrayEquals(new int[]{0,0,4},atm.withdrawal(80));
        assertArrayEquals(new int[]{0,1,3},atm.withdrawal(110));
        assertArrayEquals(new int[]{0,1,4},atm.withdrawal(130));
    }
}
