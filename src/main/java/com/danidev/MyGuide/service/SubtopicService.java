package com.danidev.MyGuide.service;

import com.danidev.MyGuide.dto.request.SubtopicRequest;
import com.danidev.MyGuide.dto.response.SubtopicResponse;

import java.util.List;

public interface SubtopicService {

    SubtopicResponse createSubtopic(SubtopicRequest request);
    List<SubtopicResponse> getAllSubtopics();
    SubtopicResponse getSubtopicById(Long id);
    List<SubtopicResponse> getSubtopicsByTopicId(Long topicId);
    SubtopicResponse updateSubtopic(Long id, SubtopicRequest request);
    void deleteSubtopic(Long id);
}
