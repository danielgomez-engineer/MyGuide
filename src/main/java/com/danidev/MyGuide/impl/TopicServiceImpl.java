package com.danidev.MyGuide.impl;


import com.danidev.MyGuide.dto.request.TopicRequest;
import com.danidev.MyGuide.dto.response.TopicResponse;
import com.danidev.MyGuide.exception.ResourceNotFoundException;
import com.danidev.MyGuide.model.Topic;
import com.danidev.MyGuide.repository.TopicRepository;
import com.danidev.MyGuide.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;
    private final ModelMapper modelMapper;


    @Override
    public TopicResponse createTopic(TopicRequest request) {
        Topic topic = modelMapper.map(request, Topic.class);
        topic = topicRepository.save(topic);
        return modelMapper.map(topic, TopicResponse.class);
    }

    @Override
    public List<TopicResponse> getAllTopics() {
        return topicRepository.findAll().stream()
                .map(topic -> modelMapper.map(topic, TopicResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public TopicResponse getTopicById(Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tema", id));
        return modelMapper.map(topic, TopicResponse.class);
    }

    @Override
    public TopicResponse updateTopic(Long id, TopicRequest request) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tema", id));
        topic.setName(request.getName());
        topic = topicRepository.save(topic);
        return modelMapper.map(topic, TopicResponse.class);
    }

    @Override
    public void deleteTopic(Long id) {
        if (!topicRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tema", id);
        }
        topicRepository.deleteById(id);

    }
}
