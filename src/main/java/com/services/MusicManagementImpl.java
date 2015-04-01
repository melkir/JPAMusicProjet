package com.services;

import com.bean.Album;
import com.bean.Artist;
import com.bean.Music;
import com.dao.AlbumRepository;
import com.dao.ArtistRepository;
import com.dao.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
