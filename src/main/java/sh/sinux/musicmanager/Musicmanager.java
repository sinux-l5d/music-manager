/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package sh.sinux.musicmanager;

import sh.sinux.musicmanager.AppCommand.CommandFactory;
import sh.sinux.musicmanager.LibraryStorage.LibraryStorageCommon;
import sh.sinux.musicmanager.Song.Song;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

public class Musicmanager {
    private static boolean running = true;
    private static final String prompt = ">> ";
    private static final String libraryCommands = "Available commands:\n" +
            "lib add <title> <album> <artist> <genre>\n" +
            "lib list\n" +
            "lib size\n" +
            "lib search title <title>\n" +
            "lib search artist <artist>\n" +
            "lib search album <album>\n" +
            "lib remove \"<title>\" \"<artist>\"\n" +
            "\n" +
            "pl create <name>\n" +
            "pl list\n" +
            "pl add <playlist-id>\n" +
            "pl show <playlist-id>\n" +
            "\n" +
            "select <id-from-last-lib-search>\n" +
            "help\n" +
            "quit";
    private static Song selected = null;

    public static void main(String[] args) {
        String sb = "Welcome to the Music Manager!\n" +
                "To start, please choose a file to load:\n" +
                Arrays.stream(findMMFiles()).map(File::getName).reduce((a, b) -> a + "\n" + b).orElse("No files found.");
        System.out.println(sb);

        String txt = promptUser();
        var lib = loadLibrary(new File(txt));

        System.out.println(libraryCommands);
        var cmdFactory = new CommandFactory(lib, libraryCommands);

        while(running) {
            txt = promptUser();
            var command = cmdFactory.getCommand(txt);
            System.out.println(command.execute() + "\n");
        }
    }

    public static void stop() {
        running = false;
    }

    public static void select(Song song) {
        selected = song;
    }

    public static Song getSelected() {
        return selected;
    }

    public static String promptUser() {
        Scanner scanner = new Scanner(System.in);
        var p = selected != null ? "(id:" + selected.getTrackNumber() + ") " + prompt : prompt;
        System.out.print(p);
        return scanner.nextLine().trim();
    }

    /**
     * Search current directory for .mm files
     *
     * @return String[] of .mm files
     */
    public static File[] findMMFiles() {
        return new File(".").listFiles((dir, name) -> name.endsWith(".mm"));
    }

    public static LibraryStorageCommon loadLibrary(File file) {
        var lib = LibraryStorageCommon.getInstance();

        try (var lines = Files.lines(Path.of(file.getAbsolutePath()))) {
            lines.map(Song::parse).forEach(lib::add);
        } catch (IOException e) {
            System.err.println("Error loading file: " + e.getMessage());
            System.exit(1);
        }

        return lib;
    }

   public static void generateTestFile()  {
       Song[] songs = new Song[]{
               new Song("Bohemian Rhapsody", "A Night at the Opera", "Queen", "rock"),
               new Song("Like a Rolling Stone", "Highway 61 Revisited", "Bob Dylan", "rock"),
               new Song("Shape of You", "÷ (Divide)", "Ed Sheeran", "pop"),
               new Song("Bad Guy", "When We All Fall Asleep, Where Do We Go?", "Billie Eilish", "pop"),
               new Song("Uptown Funk", "Uptown Special", "Mark Ronson ft. Bruno Mars", "funk"),
               new Song("Le vent nous portera", "Des visages des figures", "Noir Désir", "rock"),
               new Song("La Bohème", "La Bohème", "Charles Aznavour", "chanson"),
               new Song("Je t'aime... moi non plus", "Jane Birkin - Serge Gainsbourg", "Serge Gainsbourg & Jane Birkin", "chanson"),
               new Song("Alors on danse", "Cheese", "Stromae", "electro"),
               new Song("Formidable", "Racine Carrée", "Stromae", "pop"),
       };

       try (var f = new FileWriter("test.mm")) {
           Arrays.stream(songs).map(Song::format).forEach(s -> {
               try {
                   f.write(s + "\n");
               } catch (IOException e) {
                   e.printStackTrace();
               }
           });
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
}
