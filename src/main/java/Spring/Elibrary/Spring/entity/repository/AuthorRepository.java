package Spring.Elibrary.Spring.entity.repository;

import Spring.Elibrary.Spring.constant.Gendre;
import Spring.Elibrary.Spring.entity.AuthorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {


    Page<AuthorEntity> getAllByGendre(Gendre gendre, Pageable pageable);
}