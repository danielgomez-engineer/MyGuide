package com.danidev.MyGuide.controller.rest;


import com.danidev.MyGuide.dto.request.SubtopicRequest;
import com.danidev.MyGuide.dto.response.SubtopicResponse;
import com.danidev.MyGuide.service.SubtopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subtopics")
@RequiredArgsConstructor
public class SubtopicController {

    private final SubtopicService subtopicService;

    @PostMapping
    public ResponseEntity<SubtopicResponse> createSubtopic(@RequestBody SubtopicRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subtopicService.createSubtopic(request));
    }

    @GetMapping
    public ResponseEntity<List<SubtopicResponse>> getAllSubtopics() {
        return ResponseEntity.ok(subtopicService.getAllSubtopics());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubtopicResponse> getSubtopicById(@PathVariable Long id) {
        return ResponseEntity.ok(subtopicService.getSubtopicById(id));
    }

    @GetMapping("/topic/{topicId}")
    public ResponseEntity<List<SubtopicResponse>> getSubtopicsByTopicId(@PathVariable Long topicId) {
        return ResponseEntity.ok(subtopicService.getSubtopicsByTopicId(topicId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubtopicResponse> updateSubtopic(@PathVariable Long id, @RequestBody SubtopicRequest request) {
        return ResponseEntity.ok(subtopicService.updateSubtopic(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubtopic(@PathVariable Long id) {
        subtopicService.deleteSubtopic(id);
        return ResponseEntity.noContent().build();
    }
}
