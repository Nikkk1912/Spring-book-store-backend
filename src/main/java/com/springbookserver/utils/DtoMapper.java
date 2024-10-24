package com.springbookserver.utils;

import com.springbookserver.dto.response.*;
import com.springbookserver.model.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

@Component
public class DtoMapper {

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

    public static AuthorResponseDto authorToDto(Author author) {
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        authorResponseDto.setId(author.getId());
        authorResponseDto.setFistName(author.getFirstName());
        authorResponseDto.setMiddleName(author.getMiddleName());
        authorResponseDto.setLastName(author.getLastName());
        return authorResponseDto;
    }

    public static GenreResponseDto genreToDto(Genre genre) {
        GenreResponseDto genreResponseDto = new GenreResponseDto();
        genreResponseDto.setId(genre.getId());
        genreResponseDto.setGenre(genre.getGenre());
        return genreResponseDto;
    }

    public static UserResponseDto userToDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setUsername(user.getUsername());
        userResponseDto.setCartResponseDto(cartToDto(user.getCart()));
        return userResponseDto;
    }

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

    public static CartItemResponseDto cartItemToDto(CartItem cartItem) {
        CartItemResponseDto cartItemResponseDto = new CartItemResponseDto();
        cartItemResponseDto.setId(cartItem.getId());
        cartItemResponseDto.setProductId(cartItem.getProductId());
        cartItemResponseDto.setQuantity(cartItem.getQuantity());
        cartItemResponseDto.setPrice(cartItem.getPrice());
        return cartItemResponseDto;
    }
}
