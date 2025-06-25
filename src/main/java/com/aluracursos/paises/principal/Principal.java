package com.aluracursos.paises.principal;

import com.aluracursos.paises.clase.Moneda;
import com.aluracursos.paises.clase.Pais;
import com.aluracursos.paises.model.DatosPais;
import com.aluracursos.paises.repository.PaisRepository;
import com.aluracursos.paises.service.ConsumoApi;
import com.aluracursos.paises.service.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private static final String URL_BASE = "https://restcountries.com/v3.1/name/";
    private static final String PARAMETRO = "?fullText=true";
    private ConvierteDatos conversor = new ConvierteDatos();
    private ConsumoApi consumoAPI = new ConsumoApi();
    private  PaisRepository paisRepository;
    private List<Pais> paises;
    private Optional<Pais> paisBuscado;


    public Principal(PaisRepository paisRepository) {
        this.paisRepository= paisRepository;
    }

    public void mostrarMenu() {
        int opcion = -1;

        while (opcion != 0) {
            String menu = """
                    ==============================================================
                    🌍           MENÚ DE CONSULTA DE PAÍSES           🌍
                    ==============================================================
                    1 - Buscar país por nombre exacto (desde la Web)
                    2 - Monstrar todos los Paises buscado
                    3 - Buscar pais por su nombre exacto (desde la Bases de datos)
                    4 - Monstrar los 5 paises mas poblados
                    5 - Mostrar los paises registrados por su nombre oficial
                    6 - Mostrar los idiomas registrados
                    7 - Mostrar las monedas registrads
                    8 - Mostrar la cantidad de paises por region
                    9 - Monstrar paises con mayor area de mayor a menor
                    0 - Salir
                    --------------------------------------------------------------
                    Elija una opción:
                    """;

            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1 -> buscarPaisPorNombre();
                case 2 -> mostrarPaisesBuscados();
                case 3 -> buscarPaisPorNombreBD();
                case 4 -> mostrarTop5MasPoblados();
                case 5 -> listarNombresOficialesDePaises();
                case 6 -> listarIdiomas();
                case 7 -> listarMonedasPorPais();
                case 8 -> contarPaisesPorRegion();
                case 9 -> listarPaisesPorAreaDescendente();
                case 0 -> System.out.println("👋 Saliendo de la aplicación...");
                default -> System.out.println("❌ Opción inválida. Intente nuevamente.");
            }
        }
    }


    private DatosPais[] getDatosPais() {
        System.out.print("🔍 Escribe el nombre exacto de un país: ");
        String nombrePais = teclado.nextLine().trim();
        String url = URL_BASE + nombrePais + PARAMETRO;

        String json = consumoAPI.obtenerDatos(url);
        return conversor.obtenerDatos(json, DatosPais[].class);
    }


    private void buscarPaisPorNombre() {
        try {
            DatosPais[] datosPaises = getDatosPais();
            List<Pais> listaPaises = Arrays.stream(datosPaises)
                    .map(Pais::new)
                    .toList();

            for (Pais pais : listaPaises) {
                Optional<Pais> existente = paisRepository.findByNombre_ComunIgnoreCase(pais.getNombre().getComun());
                if (existente.isPresent()) {
                    System.out.println("⚠️ El país \"" + pais.getNombre().getComun() + "\" ya está almacenado en la base de datos.");
                    System.out.println(existente.get());
                } else {
                    paisRepository.save(pais);
                    System.out.println("✅ País guardado correctamente:");
                    System.out.println(pais);
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Error al obtener o guardar el país: " + e.getMessage());
        }
    }

    private void buscarPaisPorNombreBD() {
        System.out.println("🔍 Escribe el nombre del pais que deseas buscar:");
        String nombre = teclado.nextLine();
        paisBuscado = paisRepository.findByNombre_ComunIgnoreCase(nombre);
        if (paisBuscado.isPresent()) {
            System.out.println("Pais encontrado :" + paisBuscado.get());
        } else {
            System.out.println("❌ No se encontró el pais en la base de datos.");
        }


    }

    private void mostrarPaisesBuscados() {
        paises = paisRepository.findAll();
        if (paises.isEmpty()) {
            System.out.println("📭 No hay guardado guardados aún.");
            return;
        }
        paises.stream()
                .sorted(Comparator.comparing(p -> p.getNombre().getComun()))
                .forEach(System.out::println);
    }
    private void mostrarTop5MasPoblados() {
        List<Pais> topPaises = paisRepository.findTop5ByOrderByPoblacionDesc();
        System.out.println("🌍 Los 5 países con mayor población:");
        topPaises.forEach(System.out::println);
    }
    private void listarNombresOficialesDePaises() {
        List<Pais> paises = paisRepository.findAll();

        System.out.println("📜 Nombres oficiales de los países registrados:");
        for (Pais pais : paises) {
            System.out.println("• " + pais.getNombre().getOficial());
        }
    }

    private void listarIdiomas() {
        List<Pais> paises = paisRepository.findAll();

        Set<String> idiomasUnicos = new TreeSet<>();
        for (Pais pais : paises) {
            idiomasUnicos.addAll(pais.getIdiomas().values());
        }

        System.out.println("🗣️ Idiomas únicos registrados en todos los países:");
        idiomasUnicos.forEach(idioma -> System.out.println("• " + idioma));
    }
    private void listarMonedasPorPais() {
        List<Pais> paises = paisRepository.findAll();

        System.out.println("💰 MONEDAS POR PAÍS");
        System.out.println("=".repeat(50));

        for (Pais pais : paises) {
            String nombrePais = pais.getNombre().getComun();
            List<Moneda> monedas = pais.getMonedas();

            System.out.println("🌍 País: " + nombrePais);

            if (monedas == null || monedas.isEmpty()) {
                System.out.println("   ❌ No tiene monedas registradas.");
            } else {
                monedas.forEach(moneda ->
                        System.out.println("   💰 " + moneda.getNombre() + " (" + moneda.getSimbolo() + ")")
                );
            }

            System.out.println("-".repeat(50));
        }
    }
    private void contarPaisesPorRegion() {
        Map<String, Long> conteo = paisRepository.findAll().stream()
                .collect(Collectors.groupingBy(Pais::getRegion, Collectors.counting()));

        conteo.forEach((region, cantidad) ->
                System.out.printf("🗺️ Región: %s -> %d países%n", region, cantidad));
    }
    private void listarPaisesPorAreaDescendente() {
        List<Pais> paises = paisRepository.findAll();

        System.out.println("📐 PAÍSES ORDENADOS POR ÁREA (de mayor a menor)");
        System.out.println("=".repeat(60));

        paises.stream()
                .filter(p -> p.getArea() != null)
                .sorted((p1, p2) -> Double.compare(p2.getArea(), p1.getArea()))
                .forEach(p -> System.out.printf("🌍 %-20s 📐 %.2f km²%n",
                        p.getNombre().getComun(), p.getArea()));
    }






}
