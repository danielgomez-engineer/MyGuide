package com.danidev.MyGuide.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContentResponse {

    private Long id;
    private String title;
    private String text;
    private String urlVideo;
    private Long subtopicId;
    private String subtopicName;
    private LocalDateTime date;

}
