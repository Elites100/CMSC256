
package Testing;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
    // import cmsc256.MyIndexOutOfBoundsException;
// import Testing.RamString;
// import Testing.WackyStringInterface;
    public class RamStringTest {
        WackyStringInterface normalString;

        /**
         * @throws java.lang.Exception
         */
        @Before
        public void setUp() throws Exception {
            normalString = new RamString("Let's Go Rams!");
        }

        // test if the constructor properly sets the value of the string
        @Test
        public void testRamStringParameterizedConstructor1() {
            assertTrue("Let's Go Rams!".equals(normalString.getWackyString()));
        }

        // test if the constructor correctly throws the exception for a null string
        @Test(expected = IllegalArgumentException.class)
        public void testRamStringParameterizedConstructor2() {
            RamString tryNull = new RamString(null);
        }

        // test the countDoubleDigits with a string containing 0 double digits
        @Test
        public void testCountDoubleDigits1() {
            assertEquals(0, normalString.countDoubleDigits());
        }

        // test the countDoubleDigits with a string containing 1 double digit pair
        @Test
        public void testCountDoubleDigits2() {
            RamString temp = new RamString("Computer Science @ VCU 50 g00d!");
            assertEquals(1, temp.countDoubleDigits());
        }

        @Test
            public void testCountDoubleDigits3() {
                RamString temp = new RamString("333");
                assertEquals(0, temp.countDoubleDigits());
        }
        @Test
        public void testCountDoubleDigits4() {
            RamString temp = new RamString("3131");
            assertEquals(0, temp.countDoubleDigits());
        }
        @Test
        public void testCountDoubleDigits5() {
            RamString temp = new RamString("0123");
            assertEquals(0, temp.countDoubleDigits());
        }
        @Test
        public void testCountDoubleDigits6() {
            RamString temp = new RamString("3301");
            assertEquals(2, temp.countDoubleDigits());
        }

        @Test
        public void testCountDoubleDigits7() {
            RamString temp = new RamString("33");
            assertEquals(1, temp.countDoubleDigits());
        }

        @Test
        public void testCountDoubleDigits8() {
            RamString temp = new RamString("null");
            assertEquals(false, temp.isValidVCUEmail());
        }
        // test for email
        @Test
        public void isValidVCUEmail1() {
            RamString temp = new RamString("@vcu.edu");
            assertEquals(false, temp.isValidVCUEmail());
        }
        @Test
        public void  isValidVCUEmail2() {
            RamString temp = new RamString("max@vcu.edu");
            assertEquals(true, temp.isValidVCUEmail());
        }
        @Test
        public void  isValidVCUEmail3() {
            RamString temp = new RamString("kevin@gmail.com");
            assertEquals(false, temp.isValidVCUEmail());
        }
        @Test
        public void  isValidVCUEmail4() {
            RamString temp = new RamString("email");
            assertEquals(false, temp.isValidVCUEmail());
        }
        @Test
        public void  isValidVCUEmail5() {
            RamString temp = new RamString("kevin@mymail.vcu.edu");
            assertEquals(true, temp.isValidVCUEmail());
        }

        @Test
        public void  isValidVCUEmail6() {
            RamString temp = new RamString("Rodney, the ram");
            assertEquals("dyt m", temp.getEveryThirdCharacter());
        }
        // test for every third character
        @Test
        public void getEveryThirdCharacter1() {
            RamString temp = new RamString("An");
            assertEquals("", temp.getEveryThirdCharacter());
        }
        @Test
        public void getEveryThirdCharacter2() {
            RamString temp = new RamString("null");
            assertEquals("", temp.getEveryThirdCharacter());
        }

        @Test
        public void getEveryThirdCharacter3() {
            RamString temp = new RamString("null");
            assertEquals("", temp.getEveryThirdCharacter());
        }
        // test for even or odd with the character Rodney, the Ram
        @Test
        public void getEvenOrOddCharacters() {
            RamString temp = new RamString("Rodney, the Ram");
            assertEquals("ony h a", temp.getEvenOrOddCharacters("Even"));
        }

        @Test
        public void getEveryOrOddCharacters() {
            RamString temp = new RamString("Rodney, the Ram");
            assertEquals("Rde,teRm", temp.getEvenOrOddCharacters("Odd"));
        }
        // ramifyString test
        @Test
        public void ramifyString() {
            RamString temp = new RamString("0 string 00");
            assertEquals("Go Rams string CS@VCU", temp.ramifyString());
        }
        @Test
        public void ramifyString() {
            RamString temp = new RamString("String");
            assertEquals("String", temp.ramifyString());
        }
        @Test
        public void ramifyString() {
            RamString temp = new RamString("000");
            assertEquals("000", temp.ramifyString());
        }
        // converting digits to Numerals test
        @Test
        public void convertDigitsToRomanNumeralsInSubstring() {
            RamString temp = new RamString("rams0123456789");
            assertEquals("MyIndexOutOfBoundsException", temp.convertDigitsToRomanNumeralsInSubstring(0,2));
        }
        @Test
        public void convertDigitsToRomanNumeralsInSubstring() {
            RamString temp = new RamString("rams0123456789");
            assertEquals("rams0123456789", temp.convertDigitsToRomanNumeralsInSubstring(1,5));
        }
        @Test
        public void convertDigitsToRomanNumeralsInSubstring() {
            RamString temp = new RamString("rams0123456789");
            assertEquals("rams0IIIIIIIVVVIVIIVIIIIX”, temp.convertDigitsToRomanNumeralsInSubstring(5,14));
        }


    }
