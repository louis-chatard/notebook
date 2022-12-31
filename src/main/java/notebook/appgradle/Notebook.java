package notebook.appgradle;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Notebook extends Observable implements java.io.Serializable {
    private ArrayList<Page> pages;

    public Notebook() {
        pages = new ArrayList<Page>();
    }

    public void addPage(Page page) {
        pages.add(page);
        notifyObservers();
    }

    public List<Page> getPages() {
        return pages;
    }

    public Page getPage(int index) {
        return pages.get(index - 1);
    }

    public void removePage(Page page) {
        int i = page.getPageNumber();
        for (int j = i; j < pages.size() + 1; j++) {
            getPage(j).setPageNumber(j - 1);
        }
        pages.remove(page);
        notifyObservers();
    }

    public void updatePage(int pageNumber, Page page) {
        pages.set(pageNumber - 1, page);
        savePage(page);
        notifyObservers();
    }

    public void savePage(Page page) {
        String path = "src/main/resources/notebook/appgradle/pages/page" + page.getPageNumber() + ".dat";
        Path filePath = Paths.get(path);
        Path tempFilePath = Paths.get(path + ".tmp");
        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(tempFilePath))) {
            out.writeObject(page);
            Files.move(tempFilePath, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Notebook load() {
        Notebook notebook = new Notebook();
        int pageNumber = 1;
        while (true) {
            Page newPage = loadPage(pageNumber);
            if ( newPage != null) {
                notebook.addPage(newPage);
                notebook.savePage(newPage);
                pageNumber++;
            } else {
                break;
            }
        }
        return notebook;
    }

    public Page loadPage(int pageNumber) {
        String path = "src/main/resources/notebook/appgradle/pages/page" + pageNumber + ".dat";
        Path filePath = Paths.get(path);
        if ( Files.exists(filePath) ) {
            Page page = null;
            try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(filePath))) {
                page = (Page) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return page;
        } else {
            return null;
        }
    }
}
