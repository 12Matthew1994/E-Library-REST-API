package Spring.Elibrary.Spring.entity.repository;

import Spring.Elibrary.Spring.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<GenreEntity, Long> {
}
