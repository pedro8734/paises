package com.aluracursos.paises.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosMoneda(
        @JsonAlias("name") String nombre,
        @JsonAlias("symbol") String simbolo
) {
}
