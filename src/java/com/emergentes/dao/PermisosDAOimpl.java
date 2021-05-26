/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.dao;

import com.emergentes.conexion.ConexionDB;
import com.emergentes.modelo.Permisos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class PermisosDAOimpl extends ConexionDB implements PermisoDAO {

    @Override
    public void insert(Permisos permisos) throws Exception {
        try {
            this.conectar();

            String sql = "INSERT INTO permisos(id_usuario,id_rol)values(?,?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, permisos.getId_usuario());
            ps.setInt(2, permisos.getId_rol());

            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.conectar();
        }
    }

    @Override
    public void update(Permisos permisos) throws Exception {
        try {
            this.conectar();

            String sql = "update permisos SET id_usuario=?, id_rol=? where id=?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, permisos.getId_usuario());
            ps.setInt(2, permisos.getId_rol());
            ps.setInt(3, permisos.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.conectar();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            this.conectar();

            PreparedStatement ps = this.conn.prepareStatement("delete from permisos where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Permisos getById(int id) throws Exception {
        Permisos pr = new Permisos();
        try {
            this.conectar();

            PreparedStatement ps = this.conn.prepareStatement("select * from permisos where id = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pr.setId(rs.getInt("id"));
                pr.setId_usuario(rs.getInt("id_usuario"));
                pr.setId_rol(rs.getInt("id_rol"));

            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return pr;
    }

    @Override
    public List<Permisos> getAll() throws Exception {

        List<Permisos> lista = null;
        try {
            this.conectar();
            String sql = "select p.id,id_usuario,usuario,descripcion from usuarios u,permisos p,roles r\n"
                    + "where u.id=p.id_usuario and p.id_rol=r.id";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            lista = new ArrayList<Permisos>();
            while (rs.next()) {
                Permisos pr = new Permisos();
                pr.setId(rs.getInt("id"));
                pr.setId_usuario(rs.getInt("id_usuario"));
                pr.setUsuario(rs.getString("usuario"));
                pr.setDescripcion(rs.getString("descripcion"));

                lista.add(pr);
            }
            rs.close();
            ps.close();

        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }

}
