package com.springbookserver.controller;

import com.springbookserver.dto.reques.AuthorRequestDto;
import com.springbookserver.dto.reques.GenreRequestDto;
import com.springbookserver.dto.response.AuthorResponseDto;
import com.springbookserver.dto.response.GenreResponseDto;
import com.springbookserver.service.interfaces.AuthorService;
import com.springbookserver.service.interfaces.GenreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    // GET
    @GetMapping
    public ResponseEntity<List<GenreResponseDto>> getAllGenres() {
        List<GenreResponseDto> genres = genreService.getAll();
        return ResponseEntity.ok(genres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreResponseDto> getGenreById(@PathVariable Long id) {
        GenreResponseDto genre = genreService.getById(id);
        return ResponseEntity.ok(genre);
    }

    // POST
    @PostMapping
    public ResponseEntity<GenreResponseDto> createGenre(@Valid @RequestBody GenreRequestDto genreRequestDto) {
        GenreResponseDto genre = genreService.createGenre(genreRequestDto);
        return ResponseEntity.ok(genre);
    }

    @PostMapping("/{id}")
    public ResponseEntity<GenreResponseDto> updateGenre(@Valid @RequestBody GenreRequestDto genreRequestDto, @PathVariable Long id) {
        GenreResponseDto genre = genreService.updateGenre(id, genreRequestDto);
        return ResponseEntity.ok(genre);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
        return ResponseEntity.ok("Genre with id " + id + " deleted successfully");
    }
}
