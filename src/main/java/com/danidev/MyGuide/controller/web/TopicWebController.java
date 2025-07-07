package com.danidev.MyGuide.controller.web;


import com.danidev.MyGuide.dto.request.TopicRequest;
import com.danidev.MyGuide.dto.response.ContentResponse;
import com.danidev.MyGuide.dto.response.SubtopicResponse;
import com.danidev.MyGuide.dto.response.TopicResponse;
import com.danidev.MyGuide.service.ContentService;
import com.danidev.MyGuide.service.SubtopicService;
import com.danidev.MyGuide.service.TopicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/topics")
@RequiredArgsConstructor
public class TopicWebController {

    private final TopicService topicService;
    private final SubtopicService subtopicService;
    private final ContentService contentService;


    @GetMapping
    public String listTopics(Model model) {
        model.addAttribute("topics", topicService.getAllTopics());
        return "topic/list";
    }

    @GetMapping("/{id}")
    public String topicDetail(@PathVariable Long id, Model model) {
        TopicResponse topic = topicService.getTopicById(id);
        List<SubtopicResponse> subtopics = subtopicService.getSubtopicsByTopicId(id);


        for (SubtopicResponse sub : subtopics) {
            List<ContentResponse> contents = contentService.getContentsBySubtopicId(sub.getId());
            sub.setContents(contents);
        }

        model.addAttribute("topic", topic);
        model.addAttribute("subtopics", subtopics);

        return "topic/detail";
    }




    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("topic", new TopicRequest());
        return "topic/form_create";
    }

    @PostMapping
    public String saveTopic(@Valid @ModelAttribute("topic") TopicRequest request,
                            BindingResult result,
                            RedirectAttributes redirectAttributes,
                            Model model) {
        if (result.hasErrors()) {
            return "topic/list"; // o "topic/form_edit" si estás editando
        }

        if (request.getId() == null) {
            topicService.createTopic(request);
            redirectAttributes.addFlashAttribute("success", "Tema creado correctamente.");
        } else {
            topicService.updateTopic(request.getId(), request);
            redirectAttributes.addFlashAttribute("success", "Tema actualizado correctamente.");
        }

        return "redirect:/topics";
    }




    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        TopicResponse topic = topicService.getTopicById(id);
        TopicRequest request = new TopicRequest();
        request.setId(topic.getId());
        request.setName(topic.getName());
        model.addAttribute("topic", request);
        return "topic/form_edit";
    }



    // Eliminar
    @PostMapping("/delete/{id}")
    public String deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
        return "redirect:/topics";
    }
}
