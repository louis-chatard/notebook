package notebook.appgradle.commands;

import notebook.appgradle.Notebook;

public abstract class Command {

    public Notebook notebook;

    public Command(Notebook book) {
        this.notebook = book;
    }


    public abstract void execute();

}
