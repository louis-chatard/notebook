package notebook.appgradle.commands;

import notebook.appgradle.Notebook;

public abstract class Command {

    private Notebook notebook;

    protected Command(Notebook book) {
        this.notebook = book;
    }

    public abstract void execute();

}
