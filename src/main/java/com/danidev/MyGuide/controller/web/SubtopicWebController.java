package com.danidev.MyGuide.controller.web;


import com.danidev.MyGuide.dto.request.SubtopicRequest;
import com.danidev.MyGuide.dto.request.TopicRequest;
import com.danidev.MyGuide.dto.response.ContentResponse;
import com.danidev.MyGuide.dto.response.SubtopicResponse;
import com.danidev.MyGuide.dto.response.TopicResponse;
import com.danidev.MyGuide.service.ContentService;
import com.danidev.MyGuide.service.SubtopicService;
import com.danidev.MyGuide.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/subtopics")
@RequiredArgsConstructor
public class SubtopicWebController {

    private final SubtopicService subtopicService;
    private final ContentService contentService;
    private final TopicService topicService;

    @GetMapping
    public String listSubtopics(Model model) {
        model.addAttribute("subtopics", subtopicService.getAllSubtopics());
        return "subtopic/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {

        model.addAttribute("subtopic", new SubtopicRequest());

        List<TopicResponse> topics = topicService.getAllTopics();
        model.addAttribute("topics", topics);
        return "subtopic/form_create";
    }

    @PostMapping
    public String saveSubtopic(@ModelAttribute("subtopic") SubtopicRequest request) {
        if(request.getId() == null) {
            subtopicService.createSubtopic(request);
        }else {
            subtopicService.updateSubtopic(request.getId(), request);
        }
        return "redirect:/subtopics";
    }

    @GetMapping("/{id}")
    public String subtopicDetails(@PathVariable("id") Long id, Model model) {
        SubtopicResponse response = subtopicService.getSubtopicById(id);
        List<ContentResponse> contents = contentService.getContentsBySubtopicId(id);

        model.addAttribute("subtopic", response);
        model.addAttribute("contents", contents);

        return "subtopic/detail";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        SubtopicResponse response = subtopicService.getSubtopicById(id);
        SubtopicRequest request = new SubtopicRequest();
        request.setId(response.getId());
        request.setName(response.getName());
        request.setTopicId(response.getTopicId());
        model.addAttribute("subtopic", request);

        return "subtopic/form_edit";
    }

    @PostMapping("/delete/{id}")
    public String deleteTopic(@PathVariable Long id) {
        subtopicService.deleteSubtopic(id);
        return "redirect:/subtopics";
    }


}
