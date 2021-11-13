package org.rbrtwlz.issuetracking.repository;

import org.rbrtwlz.issuetracking.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
