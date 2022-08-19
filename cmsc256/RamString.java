package cmsc256;
/***********************************************************************************************************************************************************************************
 * RamString.java
 ************************************************************************************************************************************************************************************
 * project two - RamString
 ************************************************************************************************************************************************************************************
 * Project description
 * RamString an input string and change strings
 * Kevin Phung
 * 2/18/2022
 * CMSC-256
 *************************************************************************************************************************************************************************************/

public class RamString implements WackyStringInterface {
    private String string;


    //default constructor
    public RamString(){
        this.string = "Let's Go Rams and CS@VCU!";

    }

    //parameterized constructor
    public RamString(String input) throws IllegalArgumentException{
        if(input == null) {
            throw new IllegalArgumentException("String cannot be null");
        }
        this.string = input;
    }

    // set the string and throw null via illegalArguments
    public void setWackyString(String string) throws IllegalArgumentException{
        if(string == null) {
            throw new IllegalArgumentException("String cannot be null");
        }
        else {
            this.string = string;
        }
    }

    //returns the current String
    public String getWackyString() {
        return this.string;
    }

    //if length is less than three there is no third character and find interval of 3 with i + 1
    public String getEveryThirdCharacter() {
        // less than 3 characters or null
        if(string.length() < 3){
            return "";
        }
        else{
            StringBuilder builder = new StringBuilder();
            // would go from the string length and search for the third characters w/ StringBuilder
            for(int i = 0; i < string.length(); i++){
                if((i + 1) % 3 == 0){
                    builder.append(string.charAt(i));
                }
            }
            return builder.toString();
        }
    }

    //checks for even or odd input first and throws exception if not
    public String getEvenOrOddCharacters(String evenOrOdd) throws IllegalArgumentException {
       String builder = "";
        if(evenOrOdd.equals("Odd") && string.length() > 0){
            //every odd characters ( position start at 0, so every even is odd
            for(int i = 0; i < string.length(); i++){
                if(i % 2 == 0){
                  builder+=string.charAt(i);
                }
            }
            return builder;
        }

        else if(evenOrOdd.equals("Even") && string.length() > 0){
            //every even characters (position start at 0, so every odd is even)
            for(int i = 0; i < string.length(); i++){
                if(i % 2 != 0){
                    builder+=string.charAt(i);
                }
            }
            return builder;
        }
        else if(!evenOrOdd.equals("even") && !evenOrOdd.equals("odd")){
            //invalid input and throw exception
            throw new IllegalArgumentException("Invalid Input");
        }
        return builder;
    }


    public int countDoubleDigits() {
        int count = 0;

        // String[] temp = input.split("");
        String[] temp = new String[string.length()];
        for(int i = 0; i < string.length(); i++){
            temp[i] = string.substring(i,i+1);
        }

        for(int i = 0; i < temp.length - 1; i++){
            //checks for an integer in the array
            //same as the regular expression [0-9]
            if(Character.isDigit(temp[i].charAt(0)) && i + 2 <= temp.length - 1){
                //makes sure the double digit is actually only a double digit and a triple or more
                if (temp[i + 1].equals(temp[i]) && !temp[i + 2].equals(temp[i + 1])) {
                    count++;
                    i++;
                } else {
                    //finds where the repeating characters end
                    for (int j = i; j < temp.length; j++) {
                        if (!temp[j].equals(temp[i])) {
                            if (j != i + 1) {
                                i = j;
                            }
                            break;
                        }
                    }
                }
            } //checks second to last position for a double
            else if(Character.isDigit(temp[i].charAt(0)) && i == temp.length - 2){
                if(temp[i + 1].equals(temp[i])){
                    count++;
                    break;
                }
            }
        }
        return count;
    }


    public boolean isValidVCUEmail() {
        int searchBegin = string.charAt('@');
        int searchEnd = string.lastIndexOf("vcu.edu");

        if (string.contains("@") && string.contains("vcu.edu") && string.contains("mymail.vcu.edu") && string.endsWith("vcu.edu") && searchBegin > 1 && searchEnd > searchBegin){
            return true;
        }
        else {
            return false;
        }

    }

    //iterates through the original string and appends only the digits to the string builder

    public String standardizePhoneNumber() {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < string.length(); i++){
            // checking for input is number
            if(Character.isDigit(string.charAt(i))){
                builder.append(string.charAt(i));
            }
        }
        //formats the string after checking the size of the phone number
        if(builder.length() > 0){
            Long tempPhone = Long.parseLong(builder.toString());
            if(tempPhone.toString().length() != 10){
                return "This is not a phone number.";
            }
            else{
                return "(" + tempPhone.toString().substring(0,3) + ") " + tempPhone.toString().substring(3,6) + "-" + tempPhone.toString().substring(6,10);
            }
        }
        //invalid string
        return "This is not a phone number.";
    }


    public void ramifyString() {
        // would convert string and start
        String changeRamified = string.replace("000", "111");
        // replace all 00 to string
        changeRamified = changeRamified.replace("00", "CS@VCU");
        // replace all 000 to string
        changeRamified = changeRamified.replace("0", "Go Rams");
       changeRamified = changeRamified.replace("111", "000");
       input = changeRamified;
    }

    @Override
    public void convertDigitsToRomanNumeralsInSubstring(int startPosition, int endPosition) throws MyIndexOutOfBoundsException, IllegalArgumentException {
        //checks for MyIndexOutOfBoundsException
        if(startPosition < 1 || startPosition > string.length() || endPosition < 1 || endPosition > string.length()){
            throw new MyIndexOutOfBoundsException("Index is out of range");
        }
        //checks for IllegalArgumentException after MyIndexOutOfBoundsException is ruled out
        if(startPosition > endPosition){
            throw new IllegalArgumentException("Start position cannot be larger than endPosition");
        }
        // all numbers would be changed/replaced from the start to end position inputted
        String changeNum = string.substring(startPosition - 1, endPosition);
        changeNum = changeNum.replace("1", "I");
        changeNum = changeNum.replace("2", "II");
        changeNum = changeNum.replace("3", "III");
        changeNum = changeNum.replace("4", "IV");
        changeNum = changeNum.replace("5", "V");
        changeNum = changeNum.replace("6", "VI");
        changeNum = changeNum.replace("7", "VII");
        changeNum = changeNum.replace("8", "VIII");
        changeNum = changeNum.replace("9", "IX");

        string = string.replace(string.substring(startPosition - 1, endPosition), changeNum);

    }
}