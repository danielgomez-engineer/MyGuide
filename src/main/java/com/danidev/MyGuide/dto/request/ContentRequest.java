package com.danidev.MyGuide.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContentRequest {

    private Long id;

    @NotBlank(message = "El titulo es obligatorio")
    @Size(max = 100, message = "El titulo no puede tener más de 100 caracteres")
    private String title;
    @NotBlank(message = "La descripcion es obligatoria")
    private String text;
    @NotBlank(message = "La Url del video es obligatoria")
    private String urlVideo;
    @NotNull(message = "Debe seleccionar un subtema")
    private Long subtopicId;


}
