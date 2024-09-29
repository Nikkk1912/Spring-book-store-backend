package com.springbookserver.controller;

import com.springbookserver.dto.response.BookResponseDto;
import com.springbookserver.model.SortingOrder;
import com.springbookserver.service.interfaces.BookService;
import lombok.RequiredArgsConstructor;
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

}
