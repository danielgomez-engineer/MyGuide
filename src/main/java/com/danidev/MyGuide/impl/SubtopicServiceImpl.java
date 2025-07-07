package com.danidev.MyGuide.impl;


import com.danidev.MyGuide.dto.request.SubtopicRequest;
import com.danidev.MyGuide.dto.response.SubtopicResponse;
import com.danidev.MyGuide.exception.ResourceNotFoundException;
import com.danidev.MyGuide.model.Subtopic;
import com.danidev.MyGuide.model.Topic;
import com.danidev.MyGuide.repository.SubtopicRepository;
import com.danidev.MyGuide.repository.TopicRepository;
import com.danidev.MyGuide.service.SubtopicService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubtopicServiceImpl implements SubtopicService {

    private final SubtopicRepository subtopicRepository;
    private final TopicRepository topicRepository;
    private final ModelMapper modelMapper;

    @Override
    public SubtopicResponse createSubtopic(SubtopicRequest request) {
        Topic topic = topicRepository.findById(request.getTopicId())
                .orElseThrow(() ->new  ResourceNotFoundException("Tema", request.getTopicId()));

        Subtopic subtopic = new Subtopic();
        subtopic.setName(request.getName());
        subtopic.setTopic(topic);

        subtopic = subtopicRepository.save(subtopic);
        return modelMapper.map(subtopic, SubtopicResponse.class);
    }

    @Override
    public List<SubtopicResponse> getAllSubtopics() {
        return subtopicRepository.findAll().stream()
                .map(sub -> modelMapper.map(sub, SubtopicResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public SubtopicResponse getSubtopicById(Long id) {
        Subtopic subtopic = subtopicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subtema", id));
        return modelMapper.map(subtopic, SubtopicResponse.class);
    }

    @Override
    public List<SubtopicResponse> getSubtopicsByTopicId(Long topicId) {
        return subtopicRepository.findByTopicId(topicId).stream()
                .map(sub -> modelMapper.map(sub, SubtopicResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public SubtopicResponse updateSubtopic(Long id, SubtopicRequest request) {
        Subtopic subtopic = subtopicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subtema",id));

        Topic topic = topicRepository.findById(request.getTopicId())
                .orElseThrow(() -> new ResourceNotFoundException("Tema", request.getTopicId()));

        subtopic.setName(request.getName());
        subtopic.setTopic(topic);

        subtopic = subtopicRepository.save(subtopic);
        return modelMapper.map(subtopic, SubtopicResponse.class);
    }

    @Override
    public void deleteSubtopic(Long id) {
        if (!subtopicRepository.existsById(id)) {
            throw new ResourceNotFoundException("Subtema",id);
        }
        subtopicRepository.deleteById(id);
    }
}
