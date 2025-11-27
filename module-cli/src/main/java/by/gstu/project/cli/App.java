package by.gstu.project.cli;

import by.gstu.project.Author;
import by.gstu.project.Album;
import by.gstu.project.Song;
import by.gstu.project.MusicService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class App {
    public static void main(String[] args) throws ParseException {
        AnsiConsole.systemInstall();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            // Инициализация данных
            Author beatles = new Author("The Beatles", new ArrayList<>(), sdf.parse("1960-01-01"));
            Author queen = new Author("Queen", new ArrayList<>(), sdf.parse("1970-06-27"));

            Album album1 = new Album("Abbey Road", beatles, new ArrayList<>(), 1969);
            Album album2 = new Album("A Night at the Opera", queen, new ArrayList<>(), 1975);
            Album album3 = new Album("Help!", beatles, new ArrayList<>(), 1965);

            Song song1 = new Song("Come Together", beatles, 259, album1);
            Song song2 = new Song("Bohemian Rhapsody", queen, 354, album2);
            Song song3 = new Song("Yesterday", beatles, 125, album3);

            album1.AddSong(song1);
            album2.AddSong(song2);
            album3.AddSong(song3);

            List<Song> allSongs = Arrays.asList(song1, song2, song3);
            List<Album> allAlbums = Arrays.asList(album1, album2, album3);

            MusicService service = new MusicService();
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println(ansi().fg(CYAN).a("\n--- Menu ---").reset());
                System.out.println("1 - Find song by author");
                System.out.println("2 - Filter albums by year");
                System.out.println("3 - Exit");
                System.out.print("Choose: ");

                if (!scanner.hasNextInt()) {
                    scanner.next();
                    continue;
                }
                int choice = scanner.nextInt();
                scanner.nextLine(); // Очистка буфера

                switch (choice) {
                    case 1:
                        System.out.print("Enter author's name: ");
                        String authorName = scanner.nextLine();
                        List<Song> foundSongs = service.findSongsByAuthor(allSongs, authorName);

                        System.out.println(ansi().fg(GREEN).a("Found songs: " + foundSongs.size()).reset());
                        for (Song song : foundSongs) {
                            System.out.println("- " + song.getTitle() + " (" + song.getDuration() + " сек)");
                        }
                        break;

                    case 2:
                        System.out.print("Enter the year: ");
                        int year = scanner.nextInt();
                        List<Album> filteredAlbums = service.filterAlbumsByYear(allAlbums, year);

                        System.out.println(ansi().fg(GREEN).a("Found albums: " + filteredAlbums.size()).reset());
                        for (Album album : filteredAlbums) {
                            System.out.println("- " + album.getTitle() + " - " + album.getAuthor().getName());
                        }
                        break;

                    case 3:
                        System.out.println(ansi().fg(YELLOW).a("Exiting...").reset());
                        return;

                    default:
                        System.out.println(ansi().fg(RED).a("Incorrect choice").reset());
                }
            }
        } finally {
            AnsiConsole.systemUninstall();
        }
    }
}