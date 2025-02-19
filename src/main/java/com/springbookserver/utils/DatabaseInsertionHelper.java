package com.springbookserver.utils;

import com.springbookserver.dao.interfaces.AuthorDao;
import com.springbookserver.dao.interfaces.BookDao;
import com.springbookserver.dao.interfaces.GenreDao;
import com.springbookserver.model.Author;
import com.springbookserver.model.Book;
import com.springbookserver.model.Genre;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DatabaseInsertionHelper {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    // Function to connect Books, Authors and Genres inserted by Liquibase changelogs
    @Transactional
    public void connectBooksAndAuthorsAndGenres() {
        Long[] bookIds = {1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L};
        Long[] authorIds = {1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L};

        List<List<Long>> genreIdsList = Arrays.asList(
                List.of(2L),                     // Game of Thrones → Fantasy
                Arrays.asList(1L, 15L),                // The Heart and the Rose → Romance, Children's Literature
                List.of(10L),                    // A Preface to Ezra Pound → Non-Fiction
                Arrays.asList(15L, 3L),                 // A Beatrice Letters → Children's Literature, Adventure
                Arrays.asList(7L, 10L),                 // Inca Gold → Adventure, Mystery
                Arrays.asList(6L, 12L),                 // The Bachman Books → Horror, Crime Fiction
                List.of(14L),                     // Psychology of adjustment → Psychology
                List.of(3L),                      // Eat that frog! → Self-Help
                Arrays.asList(3L, 11L),                 // Build mental muscle → Self-Help, Biography
                List.of(11L)                      // Fear no evil → Biography
        );

        for (int i = 0; i < bookIds.length; i++) {
            Book book = bookDao.getById(bookIds[i]);
            Author author = authorDao.getById(authorIds[i]);

            if (book != null && author != null && !book.getAuthors().contains(author)) {
                book.addAuthor(author);

                Set<Genre> existingGenres = new HashSet<>(book.getGenres());
                for (Long genreId : genreIdsList.get(i)) {
                    Genre genre = genreDao.getById(genreId);
                    if (genre != null && !existingGenres.contains(genre)) {
                        book.addGenre(genre);
                    }
                }

                bookDao.save(book);
            }
        }
    }
}
