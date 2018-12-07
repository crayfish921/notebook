package ctco.kurs;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class Alarm extends StickyNote implements Expirable {
    private LocalDate dismissedAt;
    private LocalTime time;
    private boolean dismissed;

    Alarm(String note, int hour, int minute, int second) {
        super(note);
        this.time = LocalTime.of(hour, minute, second);
    }

    LocalTime getTime() {
        return this.time;
    }

    String getFormattedTime() {
        return this.time.format(DateTimeFormatter.ISO_DATE);
    }

    @Override
    public void dismiss() {
        this.dismissedAt = LocalDate.now();
        dismissed = this.dismissedAt.compareTo(LocalDate.now()) == 0;
    }

    @Override
    public boolean isExpired() {
        return !this.dismissed && this.time.compareTo(LocalTime.now()) < 0;
    }

    void setTime(LocalTime time) {
        this.time = time;
    }
}
