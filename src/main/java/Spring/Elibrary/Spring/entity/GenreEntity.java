package Spring.Elibrary.Spring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "genre")
@Getter
@Setter
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity bookEntity;

}
