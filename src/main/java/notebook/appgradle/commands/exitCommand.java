package notebook.appgradle.commands;

public class exitCommand extends Command {
    public exitCommand() {
        super(null, null);
    }
    @Override
    public void execute() {
        System.exit(0);
    }
}
