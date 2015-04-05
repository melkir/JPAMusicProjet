package com.services;

import com.bean.Album;
import com.bean.Artist;
import com.bean.Music;
import com.dao.AlbumRepository;
import com.dao.ArtistRepository;
import com.dao.MusicRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    private void updateArtist(Music music) {
        Artist artist = artistRepository.findByName(music.getArtist().getName());
        if (artist != null) music.setArtist(artist);
        else artist = artistRepository.save(music.getArtist());
        artist.addProduct(music);
    }

    private void updateAlbum(Music music) {
        Album album = albumRepository.findByTitle(music.getAlbum().getTitle());
        if (album != null) {
            music.setAlbum(album);
        } else {
            album = albumRepository.save(music.getAlbum());
            album.setArtist(music.getArtist());
            music.getArtist().addProduct(album);
        }
        album.addMusic(music);
    }

    @Override
    public Music save(Music music) {
        updateArtist(music);
        updateAlbum(music);
        musicRepository.save(music);
        return music;
    }

    @Override
    public Music update(Integer id, Music music) {
        music = musicRepository.update(id, music);
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
            musics = mapper.readValue(url, new TypeReference<List<MusicSimplified>>() {
            });
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
