package ctco.kurs;

abstract class Record {
    private static int counter = 0;
    private int id;

    Record() {
        id = ++counter;
    }

    abstract void showRecordInfo();

    abstract boolean contains(String str);

    abstract String getPrefix();

    abstract void askData();

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }
}


