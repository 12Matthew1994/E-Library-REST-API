package Spring.Elibrary.Spring.entity.repository;


import Spring.Elibrary.Spring.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    Page<BookEntity> findAll (Pageable pageable);

}
