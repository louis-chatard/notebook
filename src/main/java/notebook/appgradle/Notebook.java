package notebook.appgradle;

import java.util.ArrayList;
import java.util.List;

public class Notebook extends Observable {
    private ArrayList<Page> pages;
    private int currentPage;
    public Notebook() {
        pages = new ArrayList<Page>();
        currentPage = 0;
    }

    public void addPage(Page page) {
        pages.add(page);
        notifyObservers();
    }
    public List<Page> getPages() {
        return pages;
    }

    public Notebook createDemoNotebook() {
        Notebook notebook = new Notebook();
        notebook.addPage(new Page("Page 1", "Description 1", "Text 1", ""));
        notebook.addPage(new Page("Page 2", "Description 2", "Text 2", ""));
        notebook.addPage(new Page("Page 3", "Description 3", "Text 3", ""));
        notebook.addPage(new Page("Page 4", "Description 4", "Text 4", ""));
        notebook.addPage(new Page("Page 5", "Description 5", "Text 5", ""));
        notebook.addPage(new Page("Page 6", "Description 6", "Text 6", ""));
        int pageNumber = 1;
        for (Page page : notebook.getPages()) {
            page.setPageNumber(pageNumber);
            pageNumber++;
        }
        return notebook;
    }

}
