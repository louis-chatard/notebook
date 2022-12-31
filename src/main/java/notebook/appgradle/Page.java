package notebook.appgradle;

import java.io.Serializable;
import java.time.LocalDate;

public class Page extends Observable implements Serializable {

    private String title;
    private String description;
    private String text;
    private String imageURL;
    private int pageNumber;
    private LocalDate beginDate;
    private LocalDate endDate;

    public Page() {
        this.title = "Name your trip";
        this.description = "Short description of the trip";
        this.text = "Explain more precisely what you have done";
        this.imageURL = "";
        this.pageNumber = -1;
    }
    public Page(String title, String description, String text, String imageURL) {
        this.title = title;
        this.description = description;
        this.text = text;
        this.imageURL = imageURL;
    }


    public String getTitle() {
        return this.title;
    }
    public String getDescription() {
        return this.description;
    }
    public String getText() {
        return this.text;
    }
    public String getImageURL() {
        return this.imageURL;
    }
    public int getPageNumber() {
        return this.pageNumber;
    }
    public LocalDate getBeginDate() {
        return this.beginDate;
    }
    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setText(String text) {
        this.text = text;
    }
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isEmpty() {
        return title.equals("Name your trip") && description.equals("Short description of the trip") && text.equals("Explain more precisely what you have done");
    }
}
