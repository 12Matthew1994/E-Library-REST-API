package Spring.Elibrary.Spring.entity;

import Spring.Elibrary.Spring.constant.Gendre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity(name = "author")
@Getter
@Setter
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private Date birthdate;
    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gendre gendre;


}

