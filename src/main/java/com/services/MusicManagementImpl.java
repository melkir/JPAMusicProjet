package com.services;

import com.bean.Album;
import com.bean.Artist;
import com.bean.Music;
import com.dao.AlbumRepository;
import com.dao.ArtistRepository;
import com.dao.MusicRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by melkir on 01/04/15.
 */
@Service
public class MusicManagementImpl implements MusicManagement {
    private final MusicRepository musicRepository;
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;

    @Autowired
    public MusicManagementImpl(MusicRepository musicRepository, ArtistRepository artistRepository, AlbumRepository albumRepository) {
        this.musicRepository = musicRepository;
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
    }

    private void updateInfo(Music music) {
        Artist musicArtist = music.getArtist();
        Album musicAlbum = music.getAlbum();
        // Check if artist name already exist
        Artist artist = artistRepository.findByName(musicArtist.getName());
        // Check if album name already exist
        Album album = albumRepository.findByTitle(musicAlbum.getTitle());
        if (artist != null && album != null) {
            music.setArtist(artist);
            music.setAlbum(album);
            artist.addProduct(music);
            album.addMusic(music);
        } else if (artist != null) {
            album = musicAlbum;
            album.addMusic(music);
            albumRepository.save(album);
            music.setArtist(artist);
            artist.addProduct(album);
            artist.addProduct(music);
        } else if (album != null) {
            artist = musicArtist;
            artist.addProduct(music);
            music.setAlbum(album);
            album.setArtist(artist);
            album.addMusic(music);
            artistRepository.save(artist);
            albumRepository.save(album);
        } else {
            artist = musicArtist;
            album = musicAlbum;
            album.setArtist(artist);
            album.addMusic(music);
            artist.addProduct(album);
            artist.addProduct(music);
            artistRepository.save(artist);
            albumRepository.save(album);
        }
    }

    @Override
    public Music save(Music music) {
        updateInfo(music);
        musicRepository.save(music);
        return music;
    }

    @XmlRootElement
    private static class MusicSimplified {
        @JsonProperty("title")
        String title;
        @JsonProperty("artist")
        String artist;
        @JsonProperty("album")
        String album;

        public MusicSimplified() {
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public String getAlbum() {
            return album;
        }

        public void setAlbum(String album) {
            this.album = album;
        }

        @Override
        public String toString() {
            return "MusicSimplified{" +
                    "title='" + title + '\'' +
                    ", artist='" + artist + '\'' +
                    ", album='" + album + '\'' +
                    '}';
        }
    }

    public void loadMusicFromJson() {
        URL url = MusicManagementImpl.class.getResource("/playlist.json");
        ObjectMapper mapper = new ObjectMapper();
        List<MusicSimplified> musics = null;
        try {
            musics = mapper.readValue(url, new TypeReference<List<MusicSimplified>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert musics != null;
        Album album;
        Artist artist;
        Music music;
        for (MusicSimplified m : musics) {
            album = new Album();
            artist = new Artist();
            album.setTitle(m.getAlbum());
            artist.setName(m.getArtist());
            music = new Music(m.getTitle(), artist, album);
            save(music);
        }
    }

}
