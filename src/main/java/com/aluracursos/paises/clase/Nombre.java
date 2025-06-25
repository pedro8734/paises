package com.aluracursos.paises.clase;

import com.aluracursos.paises.model.DatosNombre;
import jakarta.persistence.*;


@Embeddable
public class Nombre {
    private String comun;
    private String oficial;

    public Nombre() {
    }

    public Nombre(DatosNombre datosNombre) {
        this.comun = datosNombre.comun();
        this.oficial = datosNombre.oficial();
    }

    public String getComun() {
        return comun;
    }

    public void setComun(String comun) {
        this.comun = comun;
    }

    public String getOficial() {
        return oficial;
    }

    public void setOficial(String oficial) {
        this.oficial = oficial;
    }
    @Override
    public String toString() {
        return "%s (%s)".formatted(comun, oficial);
    }
}
