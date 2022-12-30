package notebook.appgradle;

public class Page extends Observable {

    private Notebook notebook;
    private String title;
    private String description;
    private String text;
    private String imageURL;
    private int pageNumber;

    public Page(Notebook notebook) {
        this.notebook = notebook;
        this.title = "Name your trip";
        this.description = "Short description of the trip";
        this.text = "Explain more precisely what you have done";
        this.imageURL = "";
    }
    public Page(Notebook notebook, String title, String description, String text, String imageURL) {
        this.notebook = notebook;
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
    public Notebook getNotebook() {
        return this.notebook;
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
    public void setNotebook(Notebook notebook) {
        this.notebook = notebook;
    }

    public void showDates() {
        if (description.length() > 40) {
            System.out.println(description.substring(14, 24) + " - " + description.substring(32, 42));
        }
    }
}
