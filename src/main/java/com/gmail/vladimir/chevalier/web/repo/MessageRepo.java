package com.gmail.vladimir.chevalier.web.repo;

import com.gmail.vladimir.chevalier.web.entities.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {
    Page<Message> findAll(Pageable pageable);
}
