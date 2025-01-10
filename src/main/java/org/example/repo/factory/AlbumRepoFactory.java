package org.example.repo.factory;

import org.example.model.Album;
import org.example.model.Song;
import org.example.repo.AlbumRepo;
import org.example.repo.AlbumRepoInMemImpl;
import org.example.repo.ArtistRepo;

public class AlbumRepoFactory {
    private static AlbumRepo instance = null;

    public static AlbumRepo instance() {
        if (instance == null) {
            instance = new AlbumRepoInMemImpl(20);

            ArtistRepo artists = ArtistRepoFactory.instance();

            Album album = new Album();
            album.setTitle("In FourDee");
            album.setArtist(artists.getArtist(0));
            Song[] songs = new Song[3];
            Song song = new Song();
            song.setId(1);
            song.setTitle("Scout Camp Hullabaloo");
            songs[0] = song;
            song = new Song();
            song.setId(2);
            song.setTitle("Nothing You Know Is Right");
            songs[1] = song;
            song = new Song();
            song.setId(3);
            song.setTitle("The All Consuming Clog Dance Of The Gods");
            songs[2] = song;
            album.setSongs(songs);
            album.setReview("");
            instance.addAlbum(album);

            album = new Album();
            album.setTitle("Bookcases");
            album.setArtist(artists.getArtist(1));
            songs = new Song[3];

            song = new Song();
            song.setId(1);
            song.setTitle("The Sound of Quiet");
            songs[0] = song;
            song = new Song();
            song.setId(2);
            song.setTitle("Mrs. Robertson");
            songs[1] = song;
            song = new Song();
            song.setId(3);
            song.setTitle("The Pugilist");
            songs[2] = song;
            album.setSongs(songs);
            album.setReview("");
            instance.addAlbum(album);

            album = new Album();
            album.setTitle("R4httown:  Level Infinity");
            album.setArtist(artists.getArtist(2));
            songs = new Song[3];
            song = new Song();
            song.setId(1);
            song.setTitle("Garble - Remix");
            songs[0] = song;
            song.setId(2);
            song = new Song();
            song.setTitle("Tsunami - Feat. Malick Famousdude");
            songs[1] = song;
            song = new Song();
            song.setId(3);
            song.setTitle("And Out");
            songs[2] = song;
            album.setSongs(songs);
            album.setReview("");
            instance.addAlbum(album);

        }

        return instance;
    }
}
