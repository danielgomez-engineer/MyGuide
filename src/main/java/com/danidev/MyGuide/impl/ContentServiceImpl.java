package com.danidev.MyGuide.impl;

import com.danidev.MyGuide.dto.request.ContentRequest;
import com.danidev.MyGuide.dto.response.ContentResponse;
import com.danidev.MyGuide.exception.ResourceNotFoundException;
import com.danidev.MyGuide.model.Content;
import com.danidev.MyGuide.model.Subtopic;
import com.danidev.MyGuide.repository.ContentRepository;
import com.danidev.MyGuide.repository.SubtopicRepository;
import com.danidev.MyGuide.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {


    private final ContentRepository contentRepository;
    private final SubtopicRepository subtopicRepository;
    private final ModelMapper modelMapper;

    @Override
    public ContentResponse createContent(ContentRequest request) {
        Subtopic subtopic = subtopicRepository.findById(request.getSubtopicId())
                .orElseThrow(() -> new ResourceNotFoundException("Subtema", request.getSubtopicId()));

        Content content = new Content();
        content.setTitle(request.getTitle());
        content.setText(request.getText());
        content.setUrlVideo(request.getUrlVideo());
        content.setSubtopic(subtopic);

        content = contentRepository.save(content);
        return modelMapper.map(content, ContentResponse.class);
    }

    @Override
    public List<ContentResponse> getAllContents() {
        return contentRepository.findAll().stream()
                .map(c -> modelMapper.map(c, ContentResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ContentResponse> getContentsBySubtopicId(Long subtopicId) {
        return contentRepository.findBySubtopicId(subtopicId).stream()
                .map(c -> modelMapper.map(c, ContentResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public ContentResponse getContentById(Long id) {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contenido", id));
        return modelMapper.map(content, ContentResponse.class);
    }

    @Override
    public ContentResponse updateContent(Long id, ContentRequest request) {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contenido", id));

        Subtopic subtopic = subtopicRepository.findById(request.getSubtopicId())
                .orElseThrow(() -> new ResourceNotFoundException("Subtema", id));

        content.setTitle(request.getTitle());
        content.setText(request.getText());
        content.setSubtopic(subtopic);
        content.setUrlVideo(request.getUrlVideo());

        content = contentRepository.save(content);
        return modelMapper.map(content, ContentResponse.class);
    }

    @Override
    public void deleteContent(Long id) {
        if (!contentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contenido", id);
        }
        contentRepository.deleteById(id);
    }
}
