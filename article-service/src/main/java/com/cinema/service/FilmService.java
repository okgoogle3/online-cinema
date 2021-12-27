package com.cinema.service;

import com.cinema.repo.FilmRepo;
import com.cinema.repo.model.Film;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class FilmService {
    private final FilmRepo filmRepo;

    public List<Film> fetchAll(){
        return filmRepo.findAll();
    }

    public Film fetchById(long id) throws Exception {
        final Optional<Film> maybeFilm = filmRepo.findById(id);
        if (maybeFilm.isEmpty()) throw new Exception("Film not found");
        else return maybeFilm.get();
    }

    public long create(long videoId, String title, String producer, String date, long boxOffice) throws Exception {
        final Film film = new Film(videoId, title, producer, date, boxOffice);
        final Film savedFilm = filmRepo.save(film);
        return savedFilm.getId();
    }

    public void update (long id, long videoId, String title, String producer, String date, long boxOffice) throws Exception {
        final Optional<Film> maybeFilm = filmRepo.findById(id);
        if (maybeFilm.isEmpty()) throw new Exception("Film not found");
        final Film film = maybeFilm.get();
        if (videoId > 0) film.setVideoId(videoId);
        if (title != null) film.setTitle(title);
        if (producer != null)  film.setProducer(producer);
        if (date != null) film.setDate(date);
        if (boxOffice > 0) film.setBoxOffice(boxOffice);
        filmRepo.save(film);
    }

    public void delete(long id) {
        filmRepo.deleteById(id);
    }
}
