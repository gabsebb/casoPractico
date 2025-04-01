package com.example.votacionpresidencial.services;

import com.example.votacionpresidencial.models.*;
import com.example.votacionpresidencial.repositories.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ReporteService {

    @Autowired
    private VotoRepository votoRepository;

    public record DatosGrafico(String label, long value, String color) {}
    public record ReporteCompleto(
            Map<String, Long> datosTabla,
            List<DatosGrafico> datosGrafico
    ) {}

    //Hecho
    public Map<String, Long> obtenerReporteGeneral() {
        List<Voto> votos = votoRepository.findAll();
        return votos.stream()
                .filter(voto -> voto.getPartido() != null)
                .collect(Collectors.groupingBy(
                        voto -> voto.getPartido().getNombre(),
                        Collectors.counting()
                ));
    }
    public ReporteCompleto obtenerReporteGeneralCompleto() {
        Map<String, Long> datos = obtenerReporteGeneral();
        List<DatosGrafico> grafico = generarDatosGrafico(datos);
        return new ReporteCompleto(datos, grafico);
    }
    //
     //hecho
    public Map<String, Map<String, Long>> obtenerReportePorGenero() {
        List<Voto> votos = votoRepository.findAll();
        Map<String, Map<String, Long>> reporte = new HashMap<>();

        votos.stream()
                .filter(voto -> voto.getPartido() != null && voto.getVotante().getGenero() != null)
                .forEach(voto -> {
                    String genero = voto.getVotante().getGenero().getNombre();
                    String partido = voto.getPartido().getNombre();

                    reporte.computeIfAbsent(genero, k -> new HashMap<>());
                    reporte.get(genero).merge(partido, 1L, Long::sum);
                });

        return reporte;
    }
    public Map<String, ReporteCompleto> obtenerReportePorGeneroCompleto() {
        Map<String, Map<String, Long>> datos = obtenerReportePorGenero();
        return transformarAReporteCompleto(datos);
    }
    //
     //hecho
    public Map<String, Map<String, Long>> obtenerReportePorCiudad() {
        List<Voto> votos = votoRepository.findAll();
        Map<String, Map<String, Long>> reporte = new HashMap<>();

        votos.stream()
                .filter(voto -> voto.getPartido() != null && voto.getVotante().getCiudad() != null)
                .forEach(voto -> {
                    String ciudad = voto.getVotante().getCiudad().getNombre();
                    String partido = voto.getPartido().getNombre();

                    reporte.computeIfAbsent(ciudad, k -> new HashMap<>());
                    reporte.get(ciudad).merge(partido, 1L, Long::sum);
                });

        return reporte;
    }
    public Map<String, ReporteCompleto> obtenerReportePorCiudadCompleto() {
        Map<String, Map<String, Long>> datos = obtenerReportePorCiudad();
        return transformarAReporteCompleto(datos);
    }
    //

    public Map<String, Map<String, Long>> obtenerReportePorProvincia() {
        List<Voto> votos = votoRepository.findAll();
        Map<String, Map<String, Long>> reporte = new HashMap<>();

        votos.stream()
                .filter(voto -> voto.getPartido() != null &&
                        voto.getVotante().getCiudad() != null &&
                        voto.getVotante().getCiudad().getProvincia() != null)
                .forEach(voto -> {
                    String provincia = voto.getVotante().getCiudad().getProvincia().getNombre();
                    String partido = voto.getPartido().getNombre();

                    reporte.computeIfAbsent(provincia, k -> new HashMap<>());
                    reporte.get(provincia).merge(partido, 1L, Long::sum);
                });

        return reporte;
    }
    public Map<String, ReporteCompleto> obtenerReportePorProvinciaCompleto() {
        Map<String, Map<String, Long>> datos = obtenerReportePorProvincia();
        return transformarAReporteCompleto(datos);
    }


    private List<DatosGrafico> generarDatosGrafico(Map<String, Long> datos) {
        String[] colores = {
                "#FF6384", "#36A2EB", "#FFCE56", "#4BC0C0",
                "#9966FF", "#FF9F40", "#8AC24A", "#EA5545"
        };

        List<String> partidos = datos.keySet().stream().toList();

        return IntStream.range(0, partidos.size())
                .mapToObj(i -> new DatosGrafico(
                        partidos.get(i),
                        datos.get(partidos.get(i)),
                        colores[i % colores.length]
                ))
                .collect(Collectors.toList());
    }

    private Map<String, ReporteCompleto> transformarAReporteCompleto(
            Map<String, Map<String, Long>> datosFiltrados) {
        return datosFiltrados.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> {
                            Map<String, Long> datos = entry.getValue();
                            List<DatosGrafico> grafico = generarDatosGrafico(datos);
                            return new ReporteCompleto(datos, grafico);
                        }
                ));
    }
}
