package com.aluracursos.paises.clase;

import com.aluracursos.paises.model.DatosPais;
import jakarta.persistence.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Entity
@Table(name = "paises")
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Nombre nombre;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "pais_dominios", joinColumns = @JoinColumn(name = "pais_id"))
    @Column(name = "dominio")
    private List<String> dominioDeInternet;
    @Column(name = "codigo_iso")
    private String estandarISO;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true , fetch = FetchType.EAGER)
    @JoinColumn(name = "pais_id") // FK en la tabla `monedas`
    private List<Moneda> monedas;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "pais_capitales", joinColumns = @JoinColumn(name = "pais_id"))
    @Column(name = "capital")
    private List<String> capital;
    private String region;
    private String subregion;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "pais_idiomas", joinColumns = @JoinColumn(name = "pais_id"))
    @MapKeyColumn(name = "codigo")
    @Column(name = "idioma")
    private Map<String, String> idiomas;
    private Long poblacion;
    private Double area;
    @Embedded
    private Bandera bandera;

    public Pais() {
    }

    public Pais(DatosPais datosPais){
        this.nombre = new Nombre(datosPais.datosNombre());
       this.dominioDeInternet= datosPais.dominioDeInternet();
       this.estandarISO= datosPais.estándarISO();
       this.capital= datosPais.capital();
       this.region= datosPais.region();
       this.subregion= datosPais.subregion();
       this.idiomas= datosPais.languages();
       this.poblacion= datosPais.poblacion();
       this.area= datosPais.area();
        this.bandera = new Bandera(datosPais.banderas());

        this.monedas = datosPais.monedas().entrySet().stream()
                .map(e -> new Moneda(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public void setNombre(Nombre nombre) {
        this.nombre = nombre;
    }

    public List<String> getDominioDeInternet() {
        return dominioDeInternet;
    }

    public void setDominioDeInternet(List<String> dominioDeInternet) {
        this.dominioDeInternet = dominioDeInternet;
    }

    public String getEstandarISO() {
        return estandarISO;
    }

    public void setEstandarISO(String estandarISO) {
        this.estandarISO = estandarISO;
    }

    public List<Moneda> getMonedas() {
        return monedas;
    }

    public void setMonedas(List<Moneda> monedas) {
        this.monedas = monedas;
    }

    public List<String> getCapital() {
        return capital;
    }

    public void setCapital(List<String> capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public Map<String, String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(Map<String, String> idiomas) {
        this.idiomas = idiomas;
    }

    public Long getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(Long poblacion) {
        this.poblacion = poblacion;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Bandera getBandera() {
        return bandera;
    }

    public void setBandera(Bandera bandera) {
        this.bandera = bandera;
    }

    @Override
    public String toString() {
        return """
                🌍 País: %s
                🏛️ Nombre oficial: %s
                🧾 Dominio(s): %s
                🆔 Código ISO: %s
                🏙️ Capital(es): %s
                🌎 Región: %s | Subregión: %s
                👥 Población: %d
                📐 Área: %.2f km²
                🗣️ Idiomas: %s
                💰 Monedas: %s
                🚩 Bandera PNG: %s
                🚩 Bandera SVG: %s
                """.formatted(
                nombre.getComun(),
                nombre.getOficial(),
                dominioDeInternet,
                estandarISO,
                capital,
                region,
                subregion,
                poblacion,
                area,
                idiomas,
                monedas,
                bandera.getPng(),
                bandera.getSvg()
        );
    }
}
