package ctco.kurs;

class StickyNote extends Record {
    private String text;

    StickyNote(String note) {
        text = note;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getPrefix() {
        return "s";
    }

    @Override
    public boolean contains(String str) {
        return text.contains(str);
    }

    @Override
    public void showRecordInfo() {
        System.out.println("Note: " + text);
    }

    @Override
    public String toString() {
        return text;
    }

    @Override
    public void askData() {

    }
}
