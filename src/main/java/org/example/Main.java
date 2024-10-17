package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {


      //  abrirJrxml("18");

      //  exportarPdf("02","C:\\dev\\" +"jasper-"+ UUID.randomUUID()+".pdf");

        abrirPontoJasper("09");

        


    }

    private static void abrirPontoJasper(String numero) {
        Connection connection = JdbcConnection.connection();
        JasperService service = new JasperService();
        service.addParams("UF","RN");
        service.addParams("NIVEL_DESC","PLENO");
        service.abrirPontoJasper("jasper/funcionarios-"+numero+".jasper", connection);

        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static void exportarPdf(String numero, String s) {
        Connection connection = JdbcConnection.connection();
        JasperService service = new JasperService();
        service.exportPdf("relatorios/jrxml/funcionarios-"+numero+".jrxml", connection, s);
    }

    private static void abrirJrxml(String numero) {
        Connection connection = JdbcConnection.connection();
        JasperService service = new JasperService();
       // service.addParams("NIVEL_DESC","JUNIOR");
       // service.addParams("UF","SP");
        service.abrirJasperViewer("relatorios/jrxml/funcionarios-"+numero+".jrxml", connection);

        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}