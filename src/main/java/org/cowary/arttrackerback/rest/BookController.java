package org.cowary.arttrackerback.rest;

import jakarta.validation.Valid;
import lombok.Setter;
import org.cowary.arttrackerback.dbCase.book.BookCrud;
import org.cowary.arttrackerback.rest.converter.BookDtoConverter;
import org.cowary.arttrackerback.rest.dto.request.BookDtoRq;
import org.cowary.arttrackerback.rest.dto.response.BookDtoRs;
import org.cowary.arttrackerback.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/title")
@Setter
public class BookController implements TitleController<BookDtoRs, BookDtoRq> {

    @Autowired
    private BookCrud bookCrud;
    @Autowired
    private UserService userService;

    @Override
    @GetMapping("/book")
    public ResponseEntity<List<BookDtoRs>> getAllByUsrId() {
        var userId = userService.getIdCurrentUser();
        var bookList = bookCrud.getAllByUserId(userId);
        var bookDtoList = bookList.stream().map(BookDtoConverter::convert).toList();
        return ResponseEntity.ok(
                bookDtoList
        );
    }

    @Override
    @GetMapping("/book/{titleId}")
    public ResponseEntity<BookDtoRs> getTitle(@PathVariable long titleId) {
        var book = bookCrud.findById(titleId);
        var bookDto = BookDtoConverter.convert(book);
        return ResponseEntity.ok(
                bookDto
        );
    }

    @Override
    @PostMapping("/book")
    public ResponseEntity<BookDtoRs> postTitle(@Valid @RequestBody BookDtoRq title) {
        var book = BookDtoConverter.convert(title);
        bookCrud.save(book);
        title.setId(book.getId());
        var rs = BookDtoConverter.convert(book);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(rs);
    }

    @Override
    @PutMapping("/book")
    public ResponseEntity<BookDtoRs> putTitle(@Valid @RequestBody BookDtoRq title) {
        var book = BookDtoConverter.convert(title);
        book = bookCrud.save(book);
        var rs = BookDtoConverter.convert(book);
        return ResponseEntity.ok(rs);
    }

    @Override
    @DeleteMapping("/book")
    public ResponseEntity<String> deleteTitle(@RequestHeader long id) {
        bookCrud.deleteById(id);
        return ResponseEntity.ok(String.format("book №%s deleted", id));
    }
}
