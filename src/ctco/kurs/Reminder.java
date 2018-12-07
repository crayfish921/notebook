package ctco.kurs;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Reminder extends Alarm implements Expirable {
    private LocalDateTime date;
    private boolean dismissed;

    Reminder(String note, int hour, int minute, int second, int year, int month, int day) {
        super(note, hour, minute, second);
        this.date = LocalDateTime.of(year, month, day, hour, minute, second);
    }

    LocalDateTime getDate() {
        return date;
    }

    String getFormattedDate() {
        return this.date.format(DateTimeFormatter.ISO_DATE);
    }

    void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public void dismiss() {
        this.dismissed = true;
    }

    @Override
    public boolean isExpired() {
        return !this.dismissed && this.getDate().compareTo(LocalDateTime.now()) < 0;
    }
}
