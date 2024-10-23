package com.springbookserver.controller;

import com.springbookserver.dto.reques.BookRequestDto;
import com.springbookserver.dto.response.BookResponseDto;
import com.springbookserver.model.SortingOrder;
import com.springbookserver.service.interfaces.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // GET
    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAllBooks() {
        List<BookResponseDto> books = bookService.getAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long id) {
        BookResponseDto book = bookService.getById(id);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/pagination")
    public ResponseEntity<PagedModel<EntityModel<BookResponseDto>>> getPaginatedBooks(@RequestParam(defaultValue = "0") int pageNum,
                                                                                     @RequestParam(defaultValue = "5") int pageSize,
                                                                                     @RequestParam(required = false) String sortColumn,
                                                                                     @RequestParam(defaultValue = "ASC") SortingOrder sortingOrder,
                                                                                     PagedResourcesAssembler<BookResponseDto> assembler)
    {
        Page<BookResponseDto> books = bookService.getAllPagination(pageNum, pageSize, sortColumn, sortingOrder);
        PagedModel<EntityModel<BookResponseDto>> pagedModel = assembler.toModel(books);
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("search")
    public ResponseEntity<PagedModel<EntityModel<BookResponseDto>>> getByKeyWord(@RequestParam String searchKeyWord,
                                                                                 @RequestParam(defaultValue = "0") int pageNum,
                                                                                 @RequestParam(defaultValue = "5") int pageSize,
                                                                                 PagedResourcesAssembler<BookResponseDto> assembler)
    {
        Page<BookResponseDto> books = bookService.getByKeyWord(pageNum, pageSize, searchKeyWord);
        PagedModel<EntityModel<BookResponseDto>> pagedModel = assembler.toModel(books);
        return ResponseEntity.ok(pagedModel);
    }

    // POST
    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(@Valid @RequestBody BookRequestDto bookRequestDto) {
        BookResponseDto book = bookService.createBook(bookRequestDto);
        return ResponseEntity.ok(book);
    }

    @PostMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(@Valid @RequestBody BookRequestDto bookRequestDto, @PathVariable Long id) {
        BookResponseDto book = bookService.updateBook(id, bookRequestDto);
        return ResponseEntity.ok(book);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok("Book with id " + id + " deleted successfully");
    }

}
