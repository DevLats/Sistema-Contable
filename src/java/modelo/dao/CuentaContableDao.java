/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.beans.CuentaContable;

/**
 *
 * @author ferdinand
 */
public class CuentaContableDao {

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion objcon = new Conexion();
    String mensaje;

    public List listar(String busq) {

        List<CuentaContable> Cuentac = new ArrayList<>();

        String sql = "SELECT * FROM cuenta_contable "
                + "WHERE nombre LIKE '%" + busq + "%' "
                + "OR numero LIKE '%" + busq + "%';";

        try {

            con = objcon.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                CuentaContable cc = new CuentaContable();
                cc.setNumero(rs.getInt("numero"));
                cc.setNombre(rs.getString("nombre"));
                cc.setMoneda(rs.getString("moneda"));

                Cuentac.add(cc);

            }

        } catch (SQLException e) {
            System.out.println("OCURRIO EL SIGUIENTE ERROR: " + e);
        }
        return Cuentac;

    }

    public String Agregar(CuentaContable Cuentac) {

        String sql = "INSERT INTO cuenta_contable("
                + "numero, "
                + "nombre, "
                + "moneda) "
                + "VALUES (?,?,?)";

        try {

            con = objcon.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, Cuentac.getNumero());
            ps.setString(2, Cuentac.getNombre());
            ps.setString(3, Cuentac.getMoneda());

            ps.executeUpdate();

            mensaje = "CUENTA CONTABLE AGREGADA";

        } catch (SQLException e) {

            mensaje = "ERROR AL AGREGAR CUENTA CONTABLE: " + e;
        }

        return mensaje;
    }

    public String Modificar(CuentaContable cuentac) {

        String sql = "UPDATE cuenta_contable SET "
                + "nombre = ?, "
                + "moneda = ? "
                + "WHERE numero = "
                + cuentac.getNumero();

        try {

            con = objcon.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cuentac.getNombre());
            ps.setString(2, cuentac.getMoneda());
            ps.executeUpdate();

            mensaje = "CUENTA CONTABLE EDITADA";

        } catch (SQLException e) {

            mensaje = "ERROR AL EDITAR CUENTA CONTABLE: " + e;

        }

        return mensaje;

    }

    public String Eliminar(int n) {

        String sql = "DELETE FROM cuenta_contable WHERE numero=" + n;

        try {

            con = objcon.getConexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();

            mensaje = "CUENTA CONTABLE ELIMINADA";

        } catch (SQLException e) {

            mensaje = "ERROR: " + e;

        }

        return mensaje;
    }

}
