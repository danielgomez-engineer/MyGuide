package com.danidev.MyGuide.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubtopicResponse {
    private Long id;
    private String name;
    private Long topicId;
    private String topicName;
    private LocalDateTime date;
    private List<ContentResponse> contents;

}
