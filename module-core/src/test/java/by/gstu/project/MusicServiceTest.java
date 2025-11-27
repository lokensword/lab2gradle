package by.gstu.project;

import by.gstu.project.Author;
import by.gstu.project.Song;
import by.gstu.project.Album;
import by.gstu.project.MusicService;
import org.junit.Test;
import static org.junit.Assert.*; 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MusicServiceTest {

    @Test
    public void testFindSongsByAuthor() {
        Author author1 = new Author("The Beatles", new ArrayList<>(), new Date());
        Author author2 = new Author("Queen", new ArrayList<>(), new Date());

        Song s1 = new Song("Hey Jude", author1, 300, null);
        Song s2 = new Song("Yesterday", author1, 200, null);
        Song s3 = new Song("Show must go on", author2, 250, null);

        List<Song> songList = Arrays.asList(s1, s2, s3);
        MusicService service = new MusicService();

        List<Song> result = service.findSongsByAuthor(songList, "The Beatles");

        assertEquals("Должно быть найдено 2 песни", 2, result.size());
        assertEquals("Hey Jude", result.get(0).getTitle());
    }

    @Test
    public void testFilterAlbumsByYear() {
        Author author = new Author("TestBand", new ArrayList<>(), new Date());

        Album a1 = new Album("Old Album", author, new ArrayList<>(), 1990);
        Album a2 = new Album("New Album", author, new ArrayList<>(), 2020);
        Album a3 = new Album("Another Old Album", author, new ArrayList<>(), 1990);

        List<Album> albums = Arrays.asList(a1, a2, a3);
        MusicService service = new MusicService();

        List<Album> result = service.filterAlbumsByYear(albums, 1990);

        assertEquals("Должно быть найдено 2 альбома 1990 года", 2, result.size());
    }

    @Test
    public void testNotFound() {
        MusicService service = new MusicService();
        List<Song> emptyList = new ArrayList<>();

        List<Song> result = service.findSongsByAuthor(emptyList, "Nobody");

        assertTrue("Список должен быть пустым", result.isEmpty());
    }
}