package ctco.kurs;

class Person extends Record {
    private String fullName;
    private String phoneNumber;
    private String profession;
    private int age;

    Person(String name, String number, String speciality, int years) {
        fullName = name;
        phoneNumber = number;
        profession = speciality;
        age = years;
    }

    @Override
    public boolean contains(String str) {
        return fullName.contains(str) || phoneNumber.contains(str) || profession.contains(str);
    }

    @Override
    public void showRecordInfo() {
        System.out.println("Full name: " + fullName + " || Phone number: " + phoneNumber + " || Profession: " + profession + " || Age: " + age);
    }

    @Override
    public String getPrefix() {
        return "p";
    }

    @Override
    public void askData() {

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

    @Override
    public String toString() {
        return fullName + " " + phoneNumber + " " + profession + " " + age;
    }
}
