package com.springbookserver.utils;

import com.springbookserver.dao.interfaces.AuthorDao;
import com.springbookserver.dao.interfaces.BookDao;
import com.springbookserver.dao.interfaces.GenreDao;
import com.springbookserver.model.Author;
import com.springbookserver.model.Book;
import com.springbookserver.model.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabaseInsertionHelper {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    // Function to connect Books, Authors and Genres inserted by Liquibase changelogs
    public void connectBooksAndAuthorsAndGenres() {
        // Arrays of IDs for books and authors
        Long[] bookIds = {1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L};
        Long[] authorIds = {1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L};

        // List of genre IDs for each book (each entry can contain multiple genre IDs)
        List<List<Long>> genreIdsList = Arrays.asList(
                Arrays.asList(2L),                     // Game of Thrones → Fantasy
                Arrays.asList(1L, 15L),                // The Heart and the Rose → Romance, Children's Literature
                Arrays.asList(10L),                    // A Preface to Ezra Pound → Non-Fiction
                Arrays.asList(15L, 3L),                 // A Beatrice Letters → Children's Literature, Adventure
                Arrays.asList(7L, 10L),                 // Inca Gold → Adventure, Mystery
                Arrays.asList(6L, 12L),                 // The Bachman Books → Horror, Crime Fiction
                Arrays.asList(14L),                     // Psychology of adjustment → Psychology
                Arrays.asList(3L),                      // Eat that frog! → Self-Help
                Arrays.asList(3L, 11L),                 // Build mental muscle → Self-Help, Biography
                Arrays.asList(11L)                      // Fear no evil → Biography
        );

        for (int i = 0; i < bookIds.length; i++) {
            // Retrieve the book and author from the database
            Book book = bookDao.getById(bookIds[i]);
            Author author = authorDao.getById(authorIds[i]);

            // Establish connections
            if (book != null && author != null) {
                book.addAuthor(author);

                // Retrieve genres and establish multiple connections
                for (Long genreId : genreIdsList.get(i)) {
                    Genre genre = genreDao.getById(genreId);
                    if (genre != null) {
                        book.addGenre(genre);
                    }
                }

                // Save the updated book back to the database
                bookDao.save(book);
                authorDao.save(author); // Optional if you want to ensure the author is saved
            }
        }
    }
}
