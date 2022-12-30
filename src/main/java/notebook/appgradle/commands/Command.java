package notebook.appgradle.commands;

import notebook.appgradle.Notebook;
import notebook.appgradle.Page;

public abstract class Command {

    public Notebook notebook;
    public Page page;

    public Command(Notebook notebook, Page page) {
        this.notebook = notebook;
        this.page = page;
    }


    public abstract void execute();

}
