package com.gmail.vladimir.chevalier.web.repo;

import com.gmail.vladimir.chevalier.web.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {

}
