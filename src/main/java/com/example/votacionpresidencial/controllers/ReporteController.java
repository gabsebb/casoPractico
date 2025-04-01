package com.example.votacionpresidencial.controllers;

import com.example.votacionpresidencial.services.ReporteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/reportes")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }
    @GetMapping("/index")
    public String index(Model model) {
        return "reportes/index_reportes";
    }
    @GetMapping("/general")
    public String reporteGeneral(Model model) {
        ReporteService.ReporteCompleto reporte = reporteService.obtenerReporteGeneralCompleto();
        model.addAttribute("titulo", "Reporte General de Votos");
        model.addAttribute("reporte", reporte.datosTabla());
        model.addAttribute("grafico", reporte.datosGrafico());
        return "reportes/r_general";
    }

    @GetMapping("/genero")
    public String reportePorGenero(Model model) {
        Map<String, ReporteService.ReporteCompleto> reportes = reporteService.obtenerReportePorGeneroCompleto();
        model.addAttribute("titulo", "Reporte por Género");
        model.addAttribute("reporte", reportes);
        return "reportes/r_genero";
    }

    @GetMapping("/ciudad")
    public String reportePorCiudad(Model model) {
        Map<String, ReporteService.ReporteCompleto> reportes = reporteService.obtenerReportePorCiudadCompleto();
        model.addAttribute("titulo", "Reporte por Ciudad");
        model.addAttribute("reporte", reportes);
        return "reportes/r_ciudad";
    }

    @GetMapping("/provincia")
    public String reportePorProvincia(Model model) {
        Map<String, ReporteService.ReporteCompleto> reportes = reporteService.obtenerReportePorProvinciaCompleto();
        model.addAttribute("titulo", "Reporte por Provincia");
        model.addAttribute("reporte", reportes);
        return "reportes/r_provincia";
    }
}
