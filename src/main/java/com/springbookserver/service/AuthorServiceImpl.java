package com.springbookserver.service;

import com.springbookserver.dao.interfaces.AuthorDao;
import com.springbookserver.dto.reques.AuthorRequestDto;
import com.springbookserver.dto.response.AuthorResponseDto;
import com.springbookserver.model.Author;
import com.springbookserver.service.interfaces.AuthorService;
import com.springbookserver.utils.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    public List<AuthorResponseDto> getAll() {
        return authorDao.getAll().stream().map(DtoMapper::authorToDto).toList();
    }

    @Override
    public AuthorResponseDto getById(Long id) {
        return DtoMapper.authorToDto(authorDao.getById(id));
    }

    @Override
    public AuthorResponseDto createAuthor(AuthorRequestDto authorRequestDto) {
        Author authorSave = authorDao.save(new Author(authorRequestDto.getFirstName(), authorRequestDto.getMiddleName(), authorRequestDto.getLastName()));
        return DtoMapper.authorToDto(authorSave);
    }

    @Override
    public AuthorResponseDto updateAuthor(Long id, AuthorRequestDto authorRequestDto) {
        Author author = authorDao.getById(id);
        author.setFirstName(authorRequestDto.getFirstName());
        author.setMiddleName(authorRequestDto.getMiddleName());
        author.setLastName(authorRequestDto.getLastName());
        authorDao.update(author);
        return DtoMapper.authorToDto(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        authorDao.delete(id);
    }
}
