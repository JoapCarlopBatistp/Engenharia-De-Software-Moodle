package Controller.reports;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.sessao;
import Model.turma;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class report_minhas_turmas implements ActionListener{

    private sessao sessao;
    public report_minhas_turmas(sessao sessao){
        this.sessao = sessao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
         try {
            // System.out.println("bla alb");            
            
            // turmaController controller = new turmaController();
            List<turma> items = this.sessao.getTurmasMatriculadas();
            
            System.out.println("criamos a lista");

            JasperReport report = JasperCompileManager.compileReport(
                report_minhas_turmas.class.getResourceAsStream("/report_turma_vagas.jrxml")
            );

             System.out.println("CRIAMOS O REPORT");

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(items, false);

             System.out.println("CRIAMOS O DATASOURCE");

            // Criando um hashmap
            Map<String, Object> params = new HashMap<>();

             System.out.println("CRIAMOS O hashmap");
             System.out.println(dataSource);
            
            JasperPrint print = JasperFillManager.fillReport(report, params, dataSource);

             System.out.println("CRIAMOS O JASPER PRINT");

            JasperViewer.viewReport(print, false);
        } catch (JRException c) {
             System.out.println("CAIMOS NO CATCH");

            c.printStackTrace();
        }
        
    }
    
}
