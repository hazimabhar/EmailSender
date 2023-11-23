package com.emailsender.emailsender.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BugRepository extends JpaRepository<Bug, Long> {

    List<Bug> findByStatus(String string);
    
}
