package ctco.kurs;

public abstract class Record {
    private static int counter = 0;
    private int id;

    Record() {
        id = ++counter;
    }

    public abstract void showRecordInfo();

    public abstract boolean contains(String str);

    public abstract String getPrefix();

    public abstract void askData();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


