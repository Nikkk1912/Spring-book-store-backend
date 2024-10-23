package com.springbookserver.service;

import com.springbookserver.dao.interfaces.AuthorDao;
import com.springbookserver.dao.interfaces.BookDao;
import com.springbookserver.dao.interfaces.GenreDao;
import com.springbookserver.dto.reques.BookRequestDto;
import com.springbookserver.dto.response.BookResponseDto;
import com.springbookserver.model.Author;
import com.springbookserver.model.Book;
import com.springbookserver.model.Genre;
import com.springbookserver.model.SortingOrder;
import com.springbookserver.service.interfaces.BookService;
import com.springbookserver.utils.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final GenreDao genreDao;
    private final AuthorDao authorDao;

    @Override
    public List<BookResponseDto> getAll() {
        return bookDao.getAll().stream().map(DtoMapper::bookToDto).toList();
    }

    @Override
    public BookResponseDto getById(Long id) {
        return DtoMapper.bookToDto(bookDao.getById(id));
    }

    @Override
    public Page<BookResponseDto> getAllPagination(int pageNum, int pageSize, String sortColumn, SortingOrder sortOrder) {
        Pageable pageable = constructPageable(pageNum, pageSize, sortColumn, sortOrder);
        return bookDao.findAll(pageable)
                .map(DtoMapper::bookToDto);
    }

    @Override
    public Page<BookResponseDto> getByKeyWord(int pageNum, int pageSize, String searchWord) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return bookDao.getByKeyWord(searchWord, pageable).map(DtoMapper::bookToDto);
    }

    @Override
    public BookResponseDto createBook(BookRequestDto bookRequestDto) {
        String filename = createFilenameFromTitle(bookRequestDto.getTitle());
        List<Author> authorList = createAuthorListFromIds(bookRequestDto.getAuthorIds());
        List<Genre> genreList = createGenreListFromIds(bookRequestDto.getGenreIds());

        Book savedBook = bookDao.save(new Book(bookRequestDto.getTitle(), authorList, genreList, bookRequestDto.getPrice(), bookRequestDto.getStock(), filename));
        return DtoMapper.bookToDto(savedBook);
    }

    @Override
    public BookResponseDto updateBook(Long id, BookRequestDto bookRequestDto) {
        Book book = bookDao.getById(id);
        book.setTitle(bookRequestDto.getTitle());
        book.setPrice(bookRequestDto.getPrice());
        book.setStock(bookRequestDto.getStock());

        List<Author> authorList = createAuthorListFromIds(bookRequestDto.getAuthorIds());
        List<Genre> genreList = createGenreListFromIds(bookRequestDto.getGenreIds());
        book.setGenres(genreList);
        book.setAuthors(authorList);
        bookDao.update(book);
        return DtoMapper.bookToDto(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookDao.delete(id);
    }

    private Pageable constructPageable(int pageNum, int pageSize, String sortColumn, SortingOrder sortOrder) {
        Sort sort = (sortColumn != null) ? Sort.by(sortColumn) : Sort.unsorted();
        sort = (sortOrder == SortingOrder.DESC) ? sort.descending() : sort.ascending();
        return PageRequest.of(pageNum, pageSize, sort);
    }

    private String createFilenameFromTitle(String title) {
        return title.toLowerCase().replace(' ', '-') + ".jpg";
    }

    private List<Author> createAuthorListFromIds(List<Long> ids) {
        return ids.stream()
                .map(authorDao::getById)
                .collect(Collectors.toCollection(ArrayList::new));  // Ensures mutable list
    }

    private List<Genre> createGenreListFromIds(List<Long> ids) {
        return ids.stream()
                .map(genreDao::getById)
                .collect(Collectors.toCollection(ArrayList::new));  // Ensures mutable list
    }

}