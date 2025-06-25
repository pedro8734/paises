package com.aluracursos.paises.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosPais(
        @JsonAlias("name") DatosNombre datosNombre,
        @JsonAlias("tld") List<String> dominioDeInternet,
        @JsonAlias("cca2") String estándarISO,
        @JsonAlias("currencies") Map<String, DatosMoneda> monedas,
        @JsonAlias("capital") List<String> capital,
        @JsonAlias("region") String region,
        @JsonAlias("subregion") String subregion,
        @JsonAlias("languages") Map<String, String> languages,
        @JsonAlias("population") Long poblacion,
        @JsonAlias("area") Double area,
        @JsonAlias("flags") DatosBandera banderas) {
}
