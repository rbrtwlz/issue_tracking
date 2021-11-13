package org.rbrtwlz.issuetracking.repository;

import org.rbrtwlz.issuetracking.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Long> {

}
