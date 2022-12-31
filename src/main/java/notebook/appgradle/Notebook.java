package notebook.appgradle;

import java.util.ArrayList;
import java.util.List;

public class Notebook extends Observable {
    private ArrayList<Page> pages;
    public Notebook() {
        pages = new ArrayList<Page>();
    }

    public void addPage(Page page) {
        pages.add(page);
        page.setNotebook(this);
        notifyObservers();
    }
    public List<Page> getPages() {
        return pages;
    }
    public Page getPage(int index) {
        return pages.get(index-1);
    }
    public void removePage(Page page) {
        pages.remove(page);
        notifyObservers();
    }

    public void updatePage(int pageNumber,Page page) {
        pages.set(pageNumber-1, page);
        notifyObservers();
    }
    public Notebook createDemoNotebook() {
        Notebook notebook = new Notebook();
        notebook.addPage(new Page(this, "Page 1", "Description 1", "Text 1", ""));
        notebook.addPage(new Page(this, "Page 2", "Description 2", "Text 2", ""));
        notebook.addPage(new Page(this, "Page 3", "Description 3", "Text 3", ""));
        notebook.addPage(new Page(this, "Page 4", "Description 4", "Text 4", ""));
        notebook.addPage(new Page(this, "Page 5", "Description 5", "Text 5", ""));
        notebook.addPage(new Page(this, "Page 6", "Description 6", "Text 6", ""));
        int pageNumber = 1;
        for (Page page : notebook.getPages()) {
            page.setPageNumber(pageNumber);
            pageNumber++;
        }
        return notebook;
    }
}
