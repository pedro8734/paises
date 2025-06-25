package com.aluracursos.paises.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosBandera(
        @JsonAlias("png") String png,
        @JsonAlias("svg") String svg
) {
}
