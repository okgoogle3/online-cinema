package com.cinema.api;

import com.cinema.api.dto.FilmDTO;
import com.cinema.repo.model.Film;
import com.cinema.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/films")
public final class FilmController {

    private final FilmService filmService;

    @GetMapping
    public ResponseEntity<List<Film>> index(){
        List<Film> films = filmService.fetchAll();

        return ResponseEntity.ok(films);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Film> showById(@PathVariable long id){
        try {
            Film film = filmService.fetchById(id);
            return ResponseEntity.ok(film);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody FilmDTO filmDTO){
        final long videoId = filmDTO.getVideoId();
        final String title = filmDTO.getTitle();
        final String producer = filmDTO.getProducer();
        final String date = filmDTO.getDate();
        final long boxOffice = filmDTO.getBoxOffice();
        try {
            final long id = filmService.create(videoId, title,producer,date,boxOffice);
            final String location = String.format("/films/%d", id);
            return ResponseEntity.created(URI.create(location)).build();
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody FilmDTO filmDTO){
        final long videoId = filmDTO.getVideoId();
        final String title = filmDTO.getTitle();
        final String producer = filmDTO.getProducer();
        final String date = filmDTO.getDate();
        final long boxOffice = filmDTO.getBoxOffice();
        try {
            filmService.update(id, videoId, title, producer, date, boxOffice);
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}/{username}/{password}/{usertype}")
    public ResponseEntity<Void> deleteFilmById(@PathVariable long id, @PathVariable String username, @PathVariable String password){
        try{
            ResponseEntity<Void> response =
                    new RestTemplate().exchange("http://localhost:8081/users/permission/"+username+"/"+password+"/"+"ADMIN", HttpMethod.GET, null, Void.class);
            response.getStatusCode();
            if (response.getStatusCodeValue() == 200) {
                filmService.delete(id);
                return ResponseEntity.noContent().build();
            }
            throw new Exception();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
