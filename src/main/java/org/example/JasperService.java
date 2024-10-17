package org.example;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class JasperService {


    private Map<String, Object> params = new LinkedHashMap<>();


    public void addParams(String key, Object o) {
        this.params.put(key, o);
    }

    public void abrirPontoJasper(String jasperFile, Connection c){
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(jasperFile);
            JasperPrint print = JasperFillManager.fillReport(is, this.params, c);
            JasperViewer viewer = new JasperViewer(print);
            viewer.setVisible(true);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    public void abrirJasperViewer(String jrxml, Connection c){
        JasperReport jasperReport = compilarJrxml(jrxml);
        try {
            JasperPrint print = JasperFillManager.fillReport(jasperReport, this.params, c);
            JasperViewer viewer = new JasperViewer(print);
            viewer.setVisible(true);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    public void exportPdf(String jrxml, Connection c, String saida){
        JasperReport jasperReport = compilarJrxml(jrxml);
        try {
           OutputStream out = new FileOutputStream(saida);
           JasperPrint print = JasperFillManager.fillReport(jasperReport, this.params, c);
           JasperExportManager.exportReportToPdfStream(print, out);

        } catch (JRException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    private JasperReport compilarJrxml(String arquivo){


        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(arquivo);
            return JasperCompileManager.compileReport(is);
        } catch (JRException e) {
            e.printStackTrace();
        }
        return  null;
    }







}
