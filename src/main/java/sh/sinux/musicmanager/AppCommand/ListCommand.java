package sh.sinux.musicmanager.AppCommand;

import sh.sinux.musicmanager.LibraryStorage.LibraryStorage;

public class ListCommand implements Command {
    private final LibraryStorage lib;
    private final String[] args;

    public ListCommand(LibraryStorage lib, String[] args) {
        this.lib = lib;
        this.args = args;
    }

    @Override
    public String execute() {
        if (args.length != 0) return "Invalid number of arguments.";
        return lib.toString();
    }
}
