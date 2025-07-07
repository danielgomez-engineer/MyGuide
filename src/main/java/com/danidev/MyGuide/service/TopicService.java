package com.danidev.MyGuide.service;

import com.danidev.MyGuide.dto.request.TopicRequest;
import com.danidev.MyGuide.dto.response.TopicResponse;

import java.util.List;

public interface TopicService {

    TopicResponse createTopic(TopicRequest request);
    List<TopicResponse> getAllTopics();
    TopicResponse getTopicById(Long id);
    TopicResponse updateTopic(Long id, TopicRequest request);
    void deleteTopic(Long id);
}
