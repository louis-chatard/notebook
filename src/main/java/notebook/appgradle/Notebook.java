package notebook.appgradle;

import java.util.ArrayList;

public class Notebook extends Observable {
    private ArrayList<Page> pages;
    private int currentPage;

    public Notebook() {
        pages = new ArrayList<Page>();
        pages.add(new Page());
        currentPage = 0;
        addObserver(new PageController());
    }

}
