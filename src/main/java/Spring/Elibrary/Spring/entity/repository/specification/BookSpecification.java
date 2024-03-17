package Spring.Elibrary.Spring.entity.repository.specification;

import Spring.Elibrary.Spring.entity.AuthorEntity;
import Spring.Elibrary.Spring.entity.AuthorEntity_;
import Spring.Elibrary.Spring.entity.BookEntity;
import Spring.Elibrary.Spring.entity.BookEntity_;
import Spring.Elibrary.Spring.entity.filter.BookFilter;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class BookSpecification implements Specification<BookEntity> {

    private final BookFilter filter;


    @Override
    public Predicate toPredicate(Root<BookEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getName() != null) {
            predicates.add(criteriaBuilder.equal(root.get(BookEntity_.NAME), filter.getName()));
        }

        if (filter.getYear() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(BookEntity_.YEAR), filter.getYear()));
        }

        if (filter.getLanguage() != null) {
            predicates.add(criteriaBuilder.equal(root.get(BookEntity_.LANGUAGE), filter.getLanguage()));
        }

        if (filter.getAuthorId() != null) {
            Join<AuthorEntity, BookEntity> maleJoin = root.join(BookEntity_.MALE);
            predicates.add(criteriaBuilder.equal(maleJoin.get(AuthorEntity_.ID), filter.getAuthorId()));
        }

        if (filter.getAuthorId() != null) {
            Join<AuthorEntity, BookEntity> femaleJoin = root.join(BookEntity_.FEMALE);
            predicates.add(criteriaBuilder.equal(femaleJoin.get(AuthorEntity_.ID), filter.getAuthorId()));
        }

        if (filter.getIsAvailable() != null) {
            if ("available".equalsIgnoreCase(filter.getIsAvailable())) {
                predicates.add(criteriaBuilder.isTrue(root.get(BookEntity_.IS_AVAILABLE)));
            } else if ("unavailable".equalsIgnoreCase(filter.getIsAvailable())) {
                predicates.add(criteriaBuilder.isFalse(root.get(BookEntity_.IS_AVAILABLE)));
            }
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}