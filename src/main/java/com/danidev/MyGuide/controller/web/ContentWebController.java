package com.danidev.MyGuide.controller.web;


import com.danidev.MyGuide.dto.request.ContentRequest;
import com.danidev.MyGuide.dto.response.ContentResponse;
import com.danidev.MyGuide.dto.response.SubtopicResponse;
import com.danidev.MyGuide.service.ContentService;
import com.danidev.MyGuide.service.SubtopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/contents")
@RequiredArgsConstructor
public class ContentWebController {

    private final ContentService contentService;
    private final SubtopicService subtopicService;

    @GetMapping
    public String listContents(Model model) {
        model.addAttribute("content", contentService.getAllContents());
        return "content/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {

        model.addAttribute("content", new ContentRequest());

        List<SubtopicResponse> subtopics = subtopicService.getAllSubtopics();
        model.addAttribute("subtopics", subtopics);
        return "content/form_create";
    }


    @PostMapping
    public String saveContent(@ModelAttribute("content") ContentRequest request) {
        if(request.getId() == null) {
            contentService.createContent(request);
        }else {
            contentService.updateContent(request.getId(), request);
        }

        return "redirect:/contents";
    }


    @GetMapping("/{id}")
    public String contentDetails(@PathVariable("id") Long id, Model model) {
        ContentResponse response = contentService.getContentById(id);
        model.addAttribute("content", response);
        return "content/detail";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        ContentResponse response = contentService.getContentById(id);
        ContentRequest request = new ContentRequest();
        request.setId(response.getId());
        request.setTitle(response.getTitle());
        request.setText(response.getText());
        request.setUrlVideo(response.getUrlVideo());
        request.setSubtopicId(response.getSubtopicId());
        model.addAttribute("content", request);

        return "content/form_edit";
    }


    @PostMapping("/delete/{id}")
    public String deleteContent(@PathVariable Long id) {
        contentService.deleteContent(id);
        return "redirect:/contents";
    }

}
