package notebook.appgradle;

public class Notebook extends Observable {
    private String text = "";
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
        notifyObservers();
    }

    public Notebook() {
        addObserver(new Controller());
    }
}