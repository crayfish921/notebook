package ctco.kurs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.*;

public class Main {
    private static final File RECORD_LIST = new File("records.txt");
    private static Scanner scanner = new Scanner(System.in);
    private static Set<Character> validPhoneNumberKeys = Set.of('1', '2', '3', '4', '5', '6', '7', '8', '9');
    private static Set<Integer> validOperationNumbers = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
    private static Set<Integer> validChoiceNumbers = Set.of(1, 2);
    private static List<Record> recordList = new ArrayList<>();

    public static void main(String[] args) {
        loadEntries();

        for (; ; ) {
            showAvailableOperations();
            int chosenOperationNumber = chooseOperationNumber();
            handleOperation(chosenOperationNumber);
        }
    }

    private static void handleOperation(int operationNumber) {
        switch (operationNumber) {
            case 1:
                createNewEntry();
                break;
            case 2:
                deleteEntry();
                break;
            case 3:
                recordList.forEach(Record::showRecordInfo);
                break;
            case 4:
                showFAQ();
                break;
            case 5:
                saveEntries();
                break;
            case 6:
                search();
                break;
            case 7:
                isStickyNoteExpired();
                break;
            case 8:
                dismiss();
                break;
            case 9:
                saveEntries();
                System.exit(0);
        }
    }

    private static void dismiss() {
        int maxEntryIndex = 0;

        for (Record record : recordList) {
            maxEntryIndex++;
            System.out.print(maxEntryIndex + " ");
            record.showRecordInfo();
        }

        System.out.println("PICK ID");
        int id = scanner.nextInt();

        Optional<Expirable> first = recordList.stream()
                .filter(record -> record.getId() == id)
                .filter(record -> record instanceof Expirable)
                .map(record -> (Expirable)record)
                .findFirst();
        first.ifPresent(Expirable::dismiss);
    }

    private static int chooseOperationNumber() {
        boolean validNumberEntered = false;
        int chosenOperationNumber = 0;

        while (!validNumberEntered) {
            try {
                chosenOperationNumber = scanner.nextInt();

                if (validOperationNumbers.contains(chosenOperationNumber)) {
                    validNumberEntered = true;
                } else {
                    System.out.println("Please provide a valid operation number");
                }

            } catch (InputMismatchException e) {
                e.getStackTrace();
                scanner.next();
            }
        }

        return chosenOperationNumber;
    }

    private static void showAvailableOperations() {
        System.out.println();
        System.out.println("1. Create new entry");
        System.out.println("2. Delete entry");
        System.out.println("3. Show existing entries");
        System.out.println("4. FAQ");
        System.out.println("5. Save changes");
        System.out.println("6. Search");
        System.out.println("7. Is sticky note expired");
        System.out.println("8. Dismiss");
        System.out.println("9. Exit");
        System.out.println();
    }

    private static void isStickyNoteExpired() {
        recordList.stream()
                .filter(r -> r instanceof Expirable)
                .map(r -> {
                    r.showRecordInfo();
                    return (Expirable)r;
                })
                .forEach(r -> System.out.println(r.isExpired() ? " Expired" : " Not expired"));
    }

    private static void createPersonEntry() {
        System.out.println("Please enter persons name:");
        String name = scanner.next();
        scanner.skip(".*");

        System.out.println("Please enter persons surname:");
        String surname = scanner.next();
        scanner.skip(".*");

        System.out.println("Please enter persons phone number");
        String number = "";
        boolean validPhoneNumberEntered = false;

        while (!validPhoneNumberEntered) {
            try {
                number = scanner.next();
                scanner.skip(".*");

                for (char c : number.toCharArray()) {
                    if (validPhoneNumberKeys.contains(c) && number.toCharArray().length > 4) {
                        validPhoneNumberEntered = true;
                    } else {
                        validPhoneNumberEntered = false;
                        break;
                    }
                }

                if (!validPhoneNumberEntered) {
                    System.out.println("Please enter a valid number ( more than 5 symbols and containing only numbers)");
                }

            } catch (InputMismatchException e) {
                e.printStackTrace();
                scanner.next();
            }
        }

        System.out.println("Please enter persons age:");
        int age = scanner.nextInt();
        scanner.skip(".*");

        System.out.println("Please enter persons profession:");
        String profession = scanner.next();
        scanner.skip(".*");

        Person person = new Person(name + " " + surname, number, profession, age);
        recordList.add(person);
    }

    private static void createStickyNote() {
        System.out.println("Please enter note text");
        String note = scanner.next();
        scanner.skip(".*");

        LocalTime now = LocalTime.now();

        System.out.println("How many hours do you want to pass before getting the expiration alarm?");
        int hoursBeforeAlarm = scanner.nextInt();

        Alarm alarm = new Alarm(note, now.getHour() + hoursBeforeAlarm, now.getMinute(), now.getSecond());
        recordList.add(alarm);
        scanner.skip(".*");

/*
        Reminder reminder = new Reminder(note, 12, 12, 12, 1992, 12, 1);
        recordList.add(reminder);


        StickyNote sn = new StickyNote(note);
        recordList.add(sn);*/
    }

    private static void createNewEntry() {
        System.out.println("Please choose the note type");
        System.out.println("1. Sticky note");
        System.out.println("2. Person");

        int selectedNumber = 0;
        boolean correctSelection = false;

        while (!correctSelection) {
            selectedNumber = scanner.nextInt();

            if (validChoiceNumbers.contains(selectedNumber)) {
                correctSelection = true;
            } else {
                System.out.println("Please choose correct number");
            }
        }

        switch (selectedNumber) {
            case 1:
                createStickyNote();
                break;
            case 2:
                createPersonEntry();
                break;
        }
    }

    private static void saveEntries() {
        try (PrintWriter fileOut = new PrintWriter(RECORD_LIST)) {
            for (Record record : recordList) {
                fileOut.println(record.getPrefix() + " " + record);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void loadEntries() {
        try (Scanner fileIn = new Scanner(RECORD_LIST)) {
            while (fileIn.hasNext()) {
                if (fileIn.next().equals("p")) {
                    Person person = new Person(fileIn.next() + " " + fileIn.next(), fileIn.next(), fileIn.next(), fileIn.nextInt());
                    recordList.add(person);
                } else {
                    StickyNote stickyNote = new StickyNote(fileIn.next());
                    recordList.add(stickyNote);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void deleteEntry() {
        int maxEntryIndex = 0;

        for (Record record : recordList) {
            maxEntryIndex++;
            System.out.print(maxEntryIndex + " ");
            record.showRecordInfo();
        }

        System.out.println("Please choose the number of a person you would like to remove");

        boolean validNumberEntered = false;
        int chosenEntryNumber;

        while (!validNumberEntered) {
            try {
                chosenEntryNumber = scanner.nextInt();
                if (chosenEntryNumber > 0 && chosenEntryNumber <= maxEntryIndex) {
                    validNumberEntered = true;
                    recordList.remove(chosenEntryNumber - 1);
                } else {
                    System.out.println("Please provide a valid entry number");
                }
            } catch (InputMismatchException e) {
                e.printStackTrace();
                scanner.next();
            }
        }
    }

    private static void showFAQ() {
        System.out.println("Enter:\n 1 to create a new person entry\n 2 to delete entry\n 3 to show a list of existing persons\n 4 to show this help menu\n 5 to save changes\n 6 to terminate program");
    }

    private static void search() {
        System.out.println("What do you want to find?");
        String searchTerm = scanner.next();

        recordList.stream()
                .filter(r -> r.contains(searchTerm))
                .forEach(Record::showRecordInfo);
    }
}
