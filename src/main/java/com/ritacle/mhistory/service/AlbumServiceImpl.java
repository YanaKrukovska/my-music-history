package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.Album;
import com.ritacle.mhistory.persistence.model.Artist;
import com.ritacle.mhistory.persistence.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl implements AlbumService{

    @Autowired
    private AlbumRepository repository;

    @Autowired
    private ArtistService artistService;

    @Override
    public Album findAlbumByTitleAndArtist(String title, Artist artist) {
        return repository.findAlbumByTitleIgnoreCaseAndArtist(title, artist);
    }

    @Override
    public Album save(Album album) {
        album.setArtist(artistService.save(album.getArtist()));
       Album persistedAlbum = findAlbumByTitleAndArtist(album.getTitle(), album.getArtist());

        if (persistedAlbum == null) {
            persistedAlbum = repository.save(album);
        }
        return persistedAlbum;
    }

    @Override
    public Album getAlbum(Long id) {
        return repository.getOne(id);
    }

}
