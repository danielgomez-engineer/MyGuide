package com.danidev.MyGuide.service;

import com.danidev.MyGuide.dto.request.ContentRequest;
import com.danidev.MyGuide.dto.response.ContentResponse;

import java.util.List;

public interface ContentService {

    ContentResponse createContent(ContentRequest request);
    List<ContentResponse> getAllContents();
    List<ContentResponse> getContentsBySubtopicId(Long subtopicId);
    ContentResponse getContentById(Long id);
    ContentResponse updateContent(Long id, ContentRequest request);
    void deleteContent(Long id);
}
