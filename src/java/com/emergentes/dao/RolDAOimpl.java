/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.dao;

import com.emergentes.conexion.ConexionDB;
import com.emergentes.modelo.Roles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class RolDAOimpl extends ConexionDB implements RolDAO{

    @Override
    public void insert(Roles roles) throws Exception {
        try {
            this.conectar();

            String sql = "INSERT into roles(descripcion)VALUES(?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1,roles.getDescripcion());
            

            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.conectar();
        }
    }

    @Override
    public void update(Roles roles) throws Exception {
        try {
            this.conectar();

            String sql = "update roles SET descripcion=? where id=?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1,roles.getDescripcion());
            ps.setInt(2,roles.getId());
            

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

            PreparedStatement ps = this.conn.prepareStatement("delete from roles where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Roles getById(int id) throws Exception {
        Roles r=new Roles();
        try {
            this.conectar();

            PreparedStatement ps = this.conn.prepareStatement("select * from roles where id = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                r.setId(rs.getInt("id"));
                r.setDescripcion(rs.getString("descripcion"));
                

            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return r;
    }

    @Override
    public List<Roles> getAll() throws Exception {
        
        List<Roles> lista = null;
        try {
            this.conectar();
            String sql = "select *from roles";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            lista = new ArrayList<Roles>();
            while (rs.next()) {
                Roles r = new Roles();
                r.setId(rs.getInt("id"));
                r.setDescripcion(rs.getString("descripcion"));
               

                lista.add(r);
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
