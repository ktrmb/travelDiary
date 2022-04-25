package at.jku.se.diary;

public class ShortDiaryEntry {
    private String title;
    private String date;

    public ShortDiaryEntry() {

    }

    public ShortDiaryEntry(String title, String date) {
        setTitle(title);
        setDate(date);
    }

    public void setTitle(String title) {
        if (title != null && title.length() > 0)
            this.title= title;
    }

    public void setDate(String date) {
        if (date != null)
            this.date= date;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }
}
