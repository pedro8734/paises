package com.aluracursos.paises.clase;

import com.aluracursos.paises.model.DatosBandera;
import jakarta.persistence.Embeddable;

@Embeddable
public class Bandera {
    private String png;
    private String svg;

    public Bandera() {
    }
    public Bandera(DatosBandera datosBandera){
     this.png=datosBandera.png();
     this.svg= datosBandera.svg();;
    }

    public String getPng() {
        return png;
    }

    public void setPng(String png) {
        this.png = png;
    }

    public String getSvg() {
        return svg;
    }

    public void setSvg(String svg) {
        this.svg = svg;
    }
    @Override
    public String toString() {
        return "PNG: %s | SVG: %s".formatted(png, svg);
    }
}
