package com.springbookserver.controller;

import com.springbookserver.dto.reques.AuthorRequestDto;
import com.springbookserver.dto.reques.BookRequestDto;
import com.springbookserver.dto.response.AuthorResponseDto;
import com.springbookserver.dto.response.BookResponseDto;
import com.springbookserver.model.Author;
import com.springbookserver.repository.AuthorRepository;
import com.springbookserver.service.interfaces.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    // GET
    @GetMapping
    public ResponseEntity<List<AuthorResponseDto>> getAllAuthors() {
        List<AuthorResponseDto> authors = authorService.getAll();
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> getAuthorById(@PathVariable Long id) {
        AuthorResponseDto author = authorService.getById(id);
        return ResponseEntity.ok(author);
    }

    // POST
    @PostMapping
    public ResponseEntity<AuthorResponseDto> createAuthor(@Valid @RequestBody AuthorRequestDto authorRequestDto) {
        AuthorResponseDto author = authorService.createAuthor(authorRequestDto);
        return ResponseEntity.ok(author);
    }

    @PostMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> updateAuthor(@Valid @RequestBody AuthorRequestDto authorRequestDto, @PathVariable Long id) {
        AuthorResponseDto author = authorService.updateAuthor(id, authorRequestDto);
        return ResponseEntity.ok(author);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.ok("Author with id " + id + " deleted successfully");
    }
}
