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

/**
 * @author Simon Leonard
 * @author Zi Jie Lee
 */
public class Musicmanager {
    private static boolean running = true;
    private static final String prompt = ">> ";
    private static final String libraryCommands = """
            Available commands:
            lib add "<title>" "<album>" "<artist>" "<genre>"
            lib list
            lib list <title|artist|album|genre>
            lib size
            lib search title <title>
            lib search artist <artist>
            lib search album <album>
            lib remove "<title>" "<artist>"

            pl create <name>
            pl list
            pl add <playlist-id>
            pl show <playlist-id>
            pl rmsong <playlist-id> <song-position>
            pl remove <playlist-id>
            pl rename <playlist-id> <new-name>

            select <id-from-last-lib-search>
            help
            quit""";
    private static Song selected = null;

    public static void main(String[] args) {
        String sb = "Welcome to the Music Manager!\n" +
                "To start, please choose a file to load:\n" +
                Arrays.stream(findMMFiles()).map(File::getName).reduce((a, b) -> a + "\n" + b).orElse("No files found.");
        System.out.println(sb);

        String filepath = promptUser();
        var lib = loadLibrary(new File(filepath));

        System.out.println(libraryCommands);
        var cmdFactory = new CommandFactory(lib, libraryCommands);

        String txt;
        while(running) {
            txt = promptUser();
            var command = cmdFactory.getCommand(txt);
            System.out.println(command.execute() + "\n");
        }

        writeLibrary(lib, filepath);
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

    public static void writeLibrary(LibraryStorageCommon lib, String filepath) {
        try (var f = new FileWriter(filepath)) {
            Arrays.stream(lib.searchByTitle("")).map(Song::format).forEach(s -> {
                try {
                    f.write(s + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
            System.exit(1);
        }
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
