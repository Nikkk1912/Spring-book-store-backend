package com.springbookserver.service;

import com.springbookserver.dao.interfaces.BookDao;
import com.springbookserver.dto.response.BookResponseDto;
import com.springbookserver.model.SortingOrder;
import com.springbookserver.service.interfaces.BookService;
import com.springbookserver.utils.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;


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

    private Pageable constructPageable(int pageNum, int pageSize, String sortColumn, SortingOrder sortOrder) {
        Sort sort = (sortColumn != null) ? Sort.by(sortColumn) : Sort.unsorted();
        sort = (sortOrder == SortingOrder.DESC) ? sort.descending() : sort.ascending();
        return PageRequest.of(pageNum, pageSize, sort);
    }
}
