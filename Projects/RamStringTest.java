package cmsc256;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;



public class RamStringTest {
    WackyStringInterface normalString;

    /**
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        normalString = new RamString("Computer Science @ VCU Spring 2021");
    }

    // test if the constructor properly sets the value of the string
    @Test
    public void testRamStringParameterizedConstructor1() {
        assertTrue("Computer Science @ VCU Spring 2021".equals(normalString.getWackyString()));
    }

    // test if the constructor correctly throws the exception for a null string
    @Test (expected = IllegalArgumentException.class)
    public void testRamStringParameterizedConstructor2() {
        RamString tryNull = new RamString(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetWackyString() {
        RamString tryNull = new RamString();
        tryNull.setWackyString(null);
        assertEquals("Let's Go Rams and CS@VCU!", tryNull.getWackyString());
    }


    @Test
    public void testGetEveryThirdCharacter1(){
        String everyThird = normalString.getEveryThirdCharacter();
        assertTrue("mt ic@CSi 2".equals(everyThird));
    }

    @Test
    public void testGetEveryThirdCharacter2(){
        RamString testString = new RamString("456789");
        assertTrue("69".equals(testString.getEveryThirdCharacter()));
    }

    @Test
    public void testGetEveryThirdCharacter3(){
        RamString testString = new RamString("daRling Test");
        assertTrue("RnTt".equals(testString.getEveryThirdCharacter()));
    }

    @Test
    public void testGetEveryThirdCharacter4(){
        RamString testString = new RamString("te");
        assertTrue("".equals(testString.getEveryThirdCharacter()));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testGetEvenOrOddCharacters1(){
        normalString.getEvenOrOddCharacters("test");
    }

    @Test
    public void testGetEvenOrOddCharacters2(){
        assertTrue("optrSine@VUSrn 01".equals(normalString.getEvenOrOddCharacters("even")));
    }

    @Test
    public void testGetEvenOrOddCharacters3(){
        assertTrue("Cmue cec  C pig22".equals(normalString.getEvenOrOddCharacters("odd")));
    }

    @Test
    public void testGetEvenOrOddCharacters4(){
        RamString testString = new RamString("");
        assertTrue("".equals(testString.getEvenOrOddCharacters("odd")));
    }

    @Test
    public void testGetEvenOrOddCharacters5(){
        RamString testString = new RamString("a");
        assertTrue("".equals(testString.getEvenOrOddCharacters("even")));
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
        RamString temp = new RamString("11");
        assertEquals(1, temp.countDoubleDigits());
    }

    @Test
    public void testCountDoubleDigits4() {
        RamString temp = new RamString("1122344566777789");
        assertEquals(4, temp.countDoubleDigits());
    }

    @Test
    public void testCountDoubleDigits5() {
        RamString temp = new RamString("In 4 St11g");
        assertEquals(1, temp.countDoubleDigits());
    }

    @Test
    public void testIsValidVCUEmail1(){
        RamString temp = new RamString("Test@vcu.edu");
        assertTrue(temp.isValidVCUEmail());
    }

    @Test
    public void testIsValidVCUEmail2(){
        RamString temp = new RamString("Test@gmail.com");
        assertFalse(temp.isValidVCUEmail());
    }

    @Test
    public void testIsValidVCUEmail3(){
        RamString temp = new RamString("Test@mymail.vcu.edu");
        assertTrue(temp.isValidVCUEmail());
    }

    @Test
    public void testIsValidVCUEmail4(){
        RamString temp = new RamString("@mymail.vcu.edu");
        assertFalse(temp.isValidVCUEmail());
    }

    @Test
    public void testIsValidVCUEmail5(){
        RamString temp = new RamString("Test@");
        assertFalse(temp.isValidVCUEmail());
    }

    @Test
    public void testIsValidVCUEmail6(){
        RamString temp = new RamString("test@mymail.test.edu");
        assertFalse(temp.isValidVCUEmail());
    }

    @Test
    public void testStandardizePhoneNumber1(){
        RamString temp = new RamString("8449564426");
        assertEquals("(844) 956-4426", temp.standardizePhoneNumber());
    }

    @Test
    public void testStandardizePhoneNumber2(){
        RamString temp = new RamString("A String with a phone number 540216 and 9568");
        assertEquals("(540) 216-9568", temp.standardizePhoneNumber());
    }

    @Test
    public void testStandardizePhoneNumber3(){
        RamString temp = new RamString("Not a phone number");
        assertEquals("This WackyString is not a phone number.", temp.standardizePhoneNumber());
    }

    @Test
    public void testRamifyString1(){
        RamString temp = new RamString("0 00");
        temp.ramifyString();
        assertEquals("Go Rams CS@VCU", temp.getWackyString());
    }

    @Test
    public void testRamifyString2(){
        RamString temp = new RamString("Test");
        temp.ramifyString();
        assertEquals("Test", temp.getWackyString());
    }

    @Test
    public void testRamifyString3(){
        RamString temp = new RamString("000");
        temp.ramifyString();
        assertEquals("000", temp.getWackyString());
    }

    @Test
    public void testRamifyString4(){
        RamString temp = new RamString("What do we say? 0");
        temp.ramifyString();
        assertEquals("What do we say? Go Rams", temp.getWackyString());
    }

    @Test
    public void testRamifyString5(){
        RamString temp = new RamString("0");
        temp.ramifyString();
        assertEquals("Go Rams", temp.getWackyString());
    }

    @Test
    public void testRamifyString6(){
        RamString temp = new RamString("00");
        temp.ramifyString();
        assertEquals("CS@VCU", temp.getWackyString());
    }

    @Test
    public void testRamifyString7(){
        RamString temp = new RamString("867530009");
        temp.ramifyString();
        assertEquals("867530009", temp.getWackyString());
    }

    @Test (expected = MyIndexOutOfBoundsException.class)
    public void testConvertDigitsToRomanNumeralsInSubstring1(){
        RamString temp = new RamString("5");
        temp.convertDigitsToRomanNumeralsInSubstring(1,5);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConvertDigitsToRomanNumeralsInSubstring2(){
        RamString temp = new RamString("Test String");
        temp.convertDigitsToRomanNumeralsInSubstring(4,1);
    }

    @Test
    public void testConvertDigitsToRomanNumeralsInSubstring3(){
        RamString temp = new RamString("5401");
        temp.convertDigitsToRomanNumeralsInSubstring(1,4);
        assertEquals("VIV0I", temp.getWackyString());
    }

    @Test
    public void testConvertDigitsToRomanNumeralsInSubstring4(){
        RamString temp = new RamString("1 3 6");
        temp.convertDigitsToRomanNumeralsInSubstring(1,5);
        assertEquals("I III VI", temp.getWackyString());
    }

    @Test
    public void testConvertDigitsToRomanNumeralsInSubstring5(){
        RamString temp = new RamString("12345");
        temp.convertDigitsToRomanNumeralsInSubstring(3,5);
        assertEquals("12IIIIVV", temp.getWackyString());
    }


    @Test
    public void testConvertDigitsToRomanNumeralsInSubstring6(){
        RamString temp = new RamString("12345678");
        temp.convertDigitsToRomanNumeralsInSubstring(4,7);
        assertEquals("123IVVVIVII8", temp.getWackyString());
    }
}