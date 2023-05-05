package sh.sinux.musicmanager.AppCommand;

public class InvalidCommand implements Command{
    @Override
    public String execute() {
        return "Invalid command.";
    }
}
