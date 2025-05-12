package damx.jasperdemo1.services;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private DataSource dataSource;

    public byte[] generarReport(String reportName) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            InputStream reportStream = getClass().getClassLoader().getResourceAsStream("reports/" + reportName + ".jasper");

            if (reportStream == null) {
                throw new JRException("El informe no se encontró: " + reportName);
            }

            Map<String, Object> parms = new HashMap<>();
            // Puedes agregar parámetros aquí si es necesario
            //parms.put("email_filter", correo);

            JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, parms, connection);
            return JasperExportManager.exportReportToPdf(jasperPrint);
        }catch (SQLException e) {
            System.err.println("Error al obtener conexión desde el DataSource");
            throw e;
        }
    }
}
