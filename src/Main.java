import java.io.File;
import java.util.*;

public class Main {
    private static final File PERSON_LIST = new File("persons.txt");
    private static Scanner scanner = new Scanner(System.in);
    private static Set validOperationNumbers = Set.of(1, 2, 3, 4, 5);
    private static List<Person> personList = new ArrayList<>();

    public static void main(String[] args) {
        for(;;) {
            showAvailableOperations();

            int chosenOperationNumber = chooseOperation();

            handleOperation(chosenOperationNumber);
        }
    }

    private static void handleOperation(int operationNumber) {
        switch (operationNumber) {
            case 1:
                createNewEntry();
                break;
            case 2:
                System.out.println("Ain't working yet ;<");
                break;
            case 3:
                showCurrentEntries();
                break;
            case 4:
                System.out.println("4");
                break;
            case 5:
                return;
        }
    }

    private static int chooseOperation() {
        boolean validNumberEntered = false;
        int chosenOperation = 0;

        while (!validNumberEntered) {
            try {
                chosenOperation = scanner.nextInt();

                if (validOperationNumbers.contains(chosenOperation)) {
                    validNumberEntered = true;
                } else {
                    System.out.println("Please provide a valid operation number");
                }

            } catch (InputMismatchException e) {
                e.getStackTrace();
                scanner.next();
            }
        }

        return chosenOperation;
    }

    private static void showAvailableOperations() {
        System.out.println("1. Create new entry");
        System.out.println("2. Delete entry");
        System.out.println("3. Show existing entries");
        System.out.println("4. FAQ");
        System.out.println("5. Exit");
    }

    private static void createNewEntry() {
        System.out.println("Please enter persons name:");
        String name = scanner.next();

        System.out.println("Please enter persons surname:");
        String surname = scanner.next();

        System.out.println("Please enter phone number");
        String number = scanner.next();

        System.out.println("Please enter persons age:");
        int age = scanner.nextInt();

        System.out.println("Please enter persons profession:");
        String profession = scanner.next();

        Person person = new Person(name + " " + surname, number, profession, age);
        personList.add(person);
    }

    private static void showCurrentEntries() {
        personList.forEach(Person::showPersonInfo);
    }
}
