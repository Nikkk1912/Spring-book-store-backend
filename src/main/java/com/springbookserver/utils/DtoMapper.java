package com.springbookserver.utils;

import com.springbookserver.dto.response.*;
import com.springbookserver.model.*;
import com.springbookserver.xml.xml_dto.AuthorXmlDto;
import com.springbookserver.xml.xml_dto.BookXmlDto;
import com.springbookserver.xml.xml_dto.GenreXmlDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Utility class for mapping between domain models and response DTOs (Data Transfer Objects),
 * including XML DTOs for books, authors, and genres.
 */
@Component
public class DtoMapper {

    /**
     * Converts a Book entity to a BookResponseDto.
     *
     * @param book The Book entity to be converted.
     * @return The corresponding BookResponseDto.
     */
    public static BookResponseDto bookToDto(Book book) {
        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setId(book.getId());
        bookResponseDto.setTitle(book.getTitle());
        bookResponseDto.setPrice(book.getPrice());
        bookResponseDto.setStock(book.getStock());

        List<AuthorResponseDto> authorResponseDtoList =
                book.getAuthors().stream()
                        .map(DtoMapper::authorToDto)
                        .toList();

        List<GenreResponseDto> genreResponseDtoList =
                book.getGenres().stream()
                        .map(DtoMapper::genreToDto)
                        .toList();

        bookResponseDto.setAuthors(authorResponseDtoList);
        bookResponseDto.setGenres(genreResponseDtoList);
        bookResponseDto.setCoverImageFile(book.getCoverImageFile());

        return bookResponseDto;
    }

    /**
     * Converts an Author entity to an AuthorResponseDto.
     *
     * @param author The Author entity to be converted.
     * @return The corresponding AuthorResponseDto.
     */
    public static AuthorResponseDto authorToDto(Author author) {
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        authorResponseDto.setId(author.getId());
        authorResponseDto.setFistName(author.getFirstName());
        authorResponseDto.setMiddleName(author.getMiddleName());
        authorResponseDto.setLastName(author.getLastName());
        return authorResponseDto;
    }

    /**
     * Converts a Genre entity to a GenreResponseDto.
     *
     * @param genre The Genre entity to be converted.
     * @return The corresponding GenreResponseDto.
     */
    public static GenreResponseDto genreToDto(Genre genre) {
        GenreResponseDto genreResponseDto = new GenreResponseDto();
        genreResponseDto.setId(genre.getId());
        genreResponseDto.setGenre(genre.getGenre());
        return genreResponseDto;
    }

    /**
     * Converts a User entity to a UserResponseDto.
     *
     * @param user The User entity to be converted.
     * @return The corresponding UserResponseDto.
     */
    public static UserResponseDto userToDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setUsername(user.getUsername());
        userResponseDto.setCartResponseDto(cartToDto(user.getCart()));
        return userResponseDto;
    }

    /**
     * Converts a Cart entity to a CartResponseDto.
     *
     * @param cart The Cart entity to be converted.
     * @return The corresponding CartResponseDto.
     */
    public static CartResponseDto cartToDto(Cart cart) {
        CartResponseDto cartResponseDto = new CartResponseDto();
        cartResponseDto.setId(cart.getId());
        cartResponseDto.setCreatedAt(cart.getCreatedAt());
        cartResponseDto.setUserOwnerId(cart.getCartOwner().getId());
        cartResponseDto.setTotalPrice(cart.getItems().stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        cartResponseDto.setItems(cart.getItems().stream().map(DtoMapper::cartItemToDto).toList());
        return cartResponseDto;
    }

    /**
     * Converts a CartItem entity to a CartItemResponseDto.
     *
     * @param cartItem The CartItem entity to be converted.
     * @return The corresponding CartItemResponseDto.
     */
    public static CartItemResponseDto cartItemToDto(CartItem cartItem) {
        CartItemResponseDto cartItemResponseDto = new CartItemResponseDto();
        cartItemResponseDto.setId(cartItem.getId());
        cartItemResponseDto.setProductId(cartItem.getProductId());
        cartItemResponseDto.setQuantity(cartItem.getQuantity());
        cartItemResponseDto.setPrice(cartItem.getPrice());
        return cartItemResponseDto;
    }

    /**
     * Converts a BookResponseDto to a BookXmlDto.
     *
     * @param dto The BookResponseDto to be converted.
     * @return The corresponding BookXmlDto.
     */
    public static BookXmlDto convertToXmlDto(BookResponseDto dto) {
        BookXmlDto xmlDto = new BookXmlDto();
        xmlDto.setId(dto.getId());
        xmlDto.setTitle(dto.getTitle());
        xmlDto.setPrice(dto.getPrice());
        xmlDto.setStock(dto.getStock());
        xmlDto.setCoverImageFile(dto.getCoverImageFile());

        List<AuthorXmlDto> authors = dto.getAuthors().stream().map(author -> {
            AuthorXmlDto xmlAuthor = new AuthorXmlDto();
            xmlAuthor.setId(author.getId());
            xmlAuthor.setFirstName(author.getFistName());
            xmlAuthor.setMiddleName(author.getMiddleName());
            xmlAuthor.setLastName(author.getLastName());
            return xmlAuthor;
        }).collect(Collectors.toList());
        xmlDto.setAuthors(authors);

        List<GenreXmlDto> genres = dto.getGenres().stream().map(genre -> {
            GenreXmlDto xmlGenre = new GenreXmlDto();
            xmlGenre.setId(genre.getId());
            xmlGenre.setGenre(genre.getGenre());
            return xmlGenre;
        }).collect(Collectors.toList());
        xmlDto.setGenres(genres);

        return xmlDto;
    }
}
