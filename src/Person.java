public class Person {
    private String fullName;
    private String phoneNumber;
    private String profession;
    private int age;

    public Person(String name, String number, String speciality, int years) {
        fullName = name;
        phoneNumber = number;
        profession = speciality;
        age = years;
    }

    public void showPersonInfo() {
        System.out.println("Full name: " + fullName + " || Phone number: " + phoneNumber + " || Profession: " + profession + " || Age: " + age);
    }
}