package com.danidev.MyGuide.repository;

import com.danidev.MyGuide.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
