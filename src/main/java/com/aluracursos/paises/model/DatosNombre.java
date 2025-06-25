package com.aluracursos.paises.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosNombre(
        @JsonAlias("common") String comun,
        @JsonAlias("official") String oficial
) {
}
