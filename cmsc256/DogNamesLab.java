package cmsc256;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class DogNamesLab {
    private static String promptForFileName() {
        System.out.println("Enter the file name: ");
        @SuppressWarnings("resource")
        Scanner keyIn = new Scanner(System.in);
        return keyIn.next();
    }

    private static Scanner openFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        while (!file.exists()) {
            file = new File(promptForFileName());
        }
        return new Scanner(file);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Read data file to build data structure
        ArrayList<Dog> doglist = new ArrayList<>();

        try {
            // verify file and create file Scanner
            Scanner fileReader = openFile("Dog_Names.csv");

            //  Discard header line
            fileReader.nextLine();

            while(fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                int commaIndex = line.indexOf(',');
                String name = line.substring(0, commaIndex).trim();
                int count = Integer.parseInt(line.substring(commaIndex+1).trim());
                doglist.add(new Dog(name, count));
            }
            fileReader.close();
        }
        catch(FileNotFoundException noFile){
            System.out.println("There was an error opening or reading from the file.");
            System.exit(0);
        }

        Scanner readInput = new Scanner(System.in);
        String prompt = "\nWhat do you want to do?\n"
                + "\t1. Check a dog name\n" + "\t2. See all the dog names\n"
                + "\t3. Play a game\n" + "\t4. Exit"
                + ".\n"
                + "Enter the number corresponding to your choice.";

        System.out.println(prompt);
        int option = readInput.nextInt();

        switch(option) {
            case 1:
                System.out.println("Enter a dog’s name?");
                String name = in.nextLine();
                int nameCount = getCountForDog(doglist, name);
                System.out.println(name + " is registered " + nameCount + " times.");
                break;
            case 2:
                System.out.println(getDogNamesAlphabetically(doglist));
                break;
            case 3:
                playGuessingGame(doglist, in);
                break;
            default: System.out.println("Invalid option.");
        }
        in.close();
    }

    public static int getCountForDog(ArrayList<Dog> dogs, String name) {
        // TODO:
        // search the list for the Dog named name
        // display dogs name and the number of registrations for that name
        for(Dog overallDog : dogs){
            if (overallDog.getDogName().equals(name)) {
                return overallDog.getDogCount();

            }
        }
        return 0;
    }

    public static String getDogNamesAlphabetically(ArrayList<Dog> dogs) {
        // TODO Sort the list of Dog by name return
        ArrayList<String> names = new ArrayList<>();
        for(Dog dog : dogs) {
            names.add(dog.getDogName());
        }
        Collections.sort(names);
        return names.toString();

    }

    public static void playGuessingGame(ArrayList<Dog> dogs, Scanner readIn) {
        // TODO: implement the guessing game.
        // while not done playing
        // pull two random Dogs from the list
        // display the names and prompt player to pick the more popular name
        // read player input
        // increment total number of guesses
        // check registration counts to determine if player is correct
        // if correct, respond and increment number of correct answers
        // if wrong, respond
        // ask user if they want to quit
        // if yes, display number of correct out of total number of guesses
        // if no, continue
        Random rand = new Random();
        int correctGuesses = 0;
        int totalGuesses = 0;
        boolean again = true;

        while(again) {
            totalGuesses++;
            Dog one = dogs.get(rand.nextInt(dogs.size()));
            Dog two = dogs.get(rand.nextInt(dogs.size()));
            System.out.println("Which name is more popular for Anchorage dogs? (Type 1 or 2) +"
                    + "\n\t1. " + one.getDogName()
                    + "\n\t2. " + two.getDogName());
            int oneOrTwo = readIn.nextInt();

            if(oneOrTwo == 1) {
                if(one.getDogCount() > two.getDogCount()) {
                    System.out.println("Correct!");
                    correctGuesses++;
                } else {
                    System.out.println("Nope, the more popular dog name is " + two.getDogName());
                }
            } else if(oneOrTwo == 2) {
                if(two.getDogCount() > one.getDogCount()) {
                    System.out.println("Correct!");
                    correctGuesses++;
                } else {
                    System.out.println("Nope, the more popular dog name is " + one.getDogName());
                }
            } else {
                System.out.println("That's not 1 or 2!");
            }

            System.out.println("Do you want to play again? (Y/N)");
            String yOrN = readIn.next();

            if(yOrN.equals("N")) {
                System.out.println("You guessed correctly " + correctGuesses + " out of " + totalGuesses + " times.");
                again = false;
            }
        }


    }


}
