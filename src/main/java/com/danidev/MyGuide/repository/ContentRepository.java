package com.danidev.MyGuide.repository;

import com.danidev.MyGuide.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {

    List<Content> findBySubtopicId(Long subtopicId);
}
