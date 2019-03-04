import org.junit.*;

import static org.junit.Assert.*;

public class TestATM {

    private  static CityATM atm;

    @BeforeClass
    public static void init() {
        atm = new CityATM("ABCD");
        atm.add(280);
    }
    @AfterClass
    public static void tearDowm() {
        atm = null;
    }

    @Test
    public void addmoney() {
        assertFalse(atm.add(101));
        assertFalse(atm.add(30));
        assertFalse(atm.add(-30));
        assertTrue(atm.add(20));
        assertTrue(atm.add(180));
    }

    @Test
    public void withdrawal() {
        assertEquals(null,atm.withdrawal(101));
        assertEquals(null,atm.withdrawal(9));
        assertEquals(null,atm.withdrawal(-20));
        assertArrayEquals(new int[]{0,0,1}, atm.withdrawal(20));
        assertArrayEquals(new int[]{1,0,2},atm.withdrawal(140));
    }
}
