package com.danielmonsalve.permitflow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudRequestDTO {

    @NotBlank(message = "El tipo de licencia es obligatorio")
    private String tipoLicencia;

    private String descripcion;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long usuarioId;
}
