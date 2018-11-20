package ctco.kurs;

public class Person {
    private static int counter = 0;
    private String fullName;
    private String phoneNumber;
    private String profession;
    private int age;
    private int id;

    Person(String name, String number, String speciality, int years) {
        fullName = name;
        phoneNumber = number;
        profession = speciality;
        age = years;
        id = ++counter;
    }

    void showPersonInfo() {
        System.out.println("Full name: " + fullName + " || Phone number: " + phoneNumber + " || Profession: " + profession + " || Age: " + age);
    }

    String getFullName() {
        return fullName;
    }

    String getPhoneNumber() {
        return phoneNumber;
    }

    String getProfession() {
        return profession;
    }

    int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }
}
