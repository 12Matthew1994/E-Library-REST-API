package Spring.Elibrary.Spring.entity.filter;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookFilter {

    private Long authorId;
    private String name;
    private String description;
    private LocalDate year;
    private String Language;
    private String isAvailable;
    private int limit = 10;


}
