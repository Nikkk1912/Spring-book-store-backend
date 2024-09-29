package com.springbookserver.service.interfaces;

import com.springbookserver.dto.response.BookResponseDto;
import com.springbookserver.model.SortingOrder;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {
    List<BookResponseDto> getAll();
    BookResponseDto getById(Long id);
    Page<BookResponseDto> getAllPagination(int pageNum, int pageSize, String sortColumn, SortingOrder sortOrder);
    Page<BookResponseDto> getByKeyWord(int pageNum, int pageSize, String searchWord);
}
