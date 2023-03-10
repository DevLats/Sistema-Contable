/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.logic;

import java.util.List;
import modelo.beans.Documento;
import modelo.dao.DocumentoDao;

/**
 *
 * @author Jean Paul
 */
public class DocumentoLogic {
    String msj;
    DocumentoDao daoD = new DocumentoDao();
    Documento beansD = new Documento();
    List<Documento> documentos;
    
    public String Agregar(Documento documento) {

        documentos = daoD.listar("");

        if (!"".equals(documento.getSerie())
                && !"".equals(documento.getConcepto()) 
                && !"".equals(documento.getAmbito())
                && !"".equals(documento.getFormaPago())
                && !"".equals(documento.getCorrelativo())
                && !"".equals(documento.getTipoDoc())
                && documento.getNumerocuentacorriente() > 0) {
            
            int i = 0;
            int cont = 0;
            
            while (i < documentos.size()) {
                if (documento.getSerie().equals(documentos.get(i).getSerie()) && documento.getCorrelativo().equals(documentos.get(i).getCorrelativo())) {
                    cont++;
                    break;
                }
                i++;
            }
            
            if (cont == 0) {
                
                
                msj = daoD.Agregar(documento);
                
                
            } else {
                msj = "EXISTENTE";
            }

        } else {
            msj = "FALTAN DATOS";
        }
        return msj;
    }

    public List Listar(String busq) {

        documentos = daoD.listar(busq);
        return documentos;
    }
}
