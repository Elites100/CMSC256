package cmsc256;

import bridges.data_src_dependent.Song;
import java.util.Comparator;

public class SongComparator implements Comparator<Song> {
    public int compare(Song S1, Song S2){
        int album = S1.getAlbumTitle().compareTo(S2.getAlbumTitle());

        if (album == 0){
            album = S1.getSongTitle().compareTo(S2.getSongTitle());
        }

        return album;
    }
}
