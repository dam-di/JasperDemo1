package damx.jasperdemo1.controllers;

import damx.jasperdemo1.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/api")
public class ReportsControllers {

    @Autowired
    private ReportService reportService;

    @GetMapping("/obtenerInformeDemo")
    public ResponseEntity<byte[]> generarInformeDemo() {
        System.out.println("Obteniendo informe");
        try {
            byte[] report = reportService.generarReport("Report1");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.add("Content-Disposition","inline; filename=report.pdf");

            return new ResponseEntity<>(report, headers, HttpStatus.OK);

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @GetMapping("/obtenerInforme/")
    public ResponseEntity<String> getReport() {
        System.out.println("Obteniendo informe");
        return  ResponseEntity.ok("Informe obtenido");
    }

    @GetMapping("/obtenerInformeMes/{mes}")
    public ResponseEntity<String> getReportMount(@PathVariable String mes) {
        System.out.println("Obteniendo informe por mes" + mes);

        return  ResponseEntity.ok("Informe obtenido por mes " + mes);
    }


}
