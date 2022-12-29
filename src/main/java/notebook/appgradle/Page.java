package notebook.appgradle;

public class Page {

    private String title;
    private String description;
    private String text;
    private String imageURL;
    private int pageNumber;

    public Page() {
        this.title = "Name your trip";
        this.description = "Short description of the trip";
        this.text = "Explain more precisely what you have done";
        this.imageURL = "";
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

}
