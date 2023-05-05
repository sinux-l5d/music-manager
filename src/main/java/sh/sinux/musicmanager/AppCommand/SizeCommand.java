package sh.sinux.musicmanager.AppCommand;

import sh.sinux.musicmanager.LibraryStorage.LibraryStorage;

public class SizeCommand implements Command {
    private final LibraryStorage lib;
    public SizeCommand(LibraryStorage lib) {
        this.lib = lib;
    }

    @Override
    public String execute() {
        try {
            return "Library size: " + lib.size();
        } catch (RuntimeException e) {
            return "Inconsistent library storage";
        }
    }
}
