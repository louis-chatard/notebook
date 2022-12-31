package notebook.appgradle.commands;

import notebook.appgradle.Notebook;
import notebook.appgradle.Page;

public class DeletePageCommand extends Command{

    public DeletePageCommand(Notebook notebook, Page page) {
        super(notebook, page);
    }

    @Override
    public void execute() {
        notebook.deletePageFile(page);
        notebook.removePage(page);
    }
}
