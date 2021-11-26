package covidnews.models;

public class News {

    private String dateWritten;
    private String title;
    private String content;

    public String getDateWritten() {
        return dateWritten;
    }

    public void setDateWritten(String dateWritten) {
        this.dateWritten = dateWritten;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public News(String dateWritten, String title, String content) {
        this.dateWritten = dateWritten;
        this.title = title;
        this.content = content;
    }

    public News() {
    }
}
