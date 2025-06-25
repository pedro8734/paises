package com.aluracursos.paises.clase;
import jakarta.persistence.*;
import com.aluracursos.paises.model.DatosMoneda;
@Entity
@Table(name = "monedas")
public class Moneda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String nombre;
    private String simbolo;

    public Moneda() {
    }
    public Moneda(String codigo , DatosMoneda datosMoneda){
        this.codigo = codigo;
        this.nombre= datosMoneda.nombre();
        this.simbolo= datosMoneda.simbolo();
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }
    @Override
    public String toString() {
        return "%s - %s (%s)".formatted(codigo, nombre, simbolo);
    }
}
