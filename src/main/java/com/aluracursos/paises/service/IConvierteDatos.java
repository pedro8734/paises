package com.aluracursos.paises.service;

public interface IConvierteDatos {

    <T> T obtenerDatos(String json, Class<T> clase);
}
