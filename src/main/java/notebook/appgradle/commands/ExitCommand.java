package notebook.appgradle.commands;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(null, null);
    }
    @Override
    public void execute() {
        System.exit(0);
    }
}
