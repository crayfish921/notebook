package ctco.kurs;

class Person extends Record {
    private String fullName;
    private String phoneNumber;
    private String profession;
    private int age;

    Person(String fullName, String phoneNumber, String profession, int age) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.profession = profession;
        this.age = age;
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
    public void askData() { }

    @Override
    public String toString() {
        return fullName + " " + phoneNumber + " " + profession + " " + age;
    }
}
