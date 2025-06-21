package Controller.reports;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class report_test implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        
         try {
            // System.out.println("bla alb");
            // Sample data
            List<Item> items = Arrays.asList(
                new Item("Blue-Eyes White Dragon", 3000),
                new Item("Dark Magician", 2500),
                new Item("Kuriboh", 300)
            );
            
            // System.out.println("CRIAMOS A LISTA");

            // Load and compile .jrxml
            JasperReport report = JasperCompileManager.compileReport(
                report_test.class.getResourceAsStream("/report.jrxml")
            );

            // System.out.println("CRIAMOS O REPORT");

            // Data source from list of beans
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(items);

            // System.out.println("CRIAMOS O DATASOURCE");

            // Criando um hashmap
            Map<String, Object> params = new HashMap<>();

            // System.out.println("CRIAMOS O hashmap");

            // No parameters, so empty map
            JasperPrint print = JasperFillManager.fillReport(report, params, dataSource);

            // System.out.println("CRIAMOS O JASPER PRINT");

            // Show the report
            JasperViewer.viewReport(print, false);
        } catch (JRException c) {
            // System.out.println("CATCH HCTAC");

            c.printStackTrace();
        }
        
    }
    
}
