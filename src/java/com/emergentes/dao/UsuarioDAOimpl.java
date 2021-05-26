
package com.emergentes.dao;

import com.emergentes.conexion.ConexionDB;
import com.emergentes.modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class UsuarioDAOimpl extends ConexionDB implements UsuarioDAO{

    @Override
    public void insert(Usuario usuario) throws Exception {
        try {
            this.conectar();
            String sql="INSERT into usuarios (usuario,correo,clave) values(?,?,MD5(?))";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getCorreo());
            ps.setString(3, usuario.getClave());
         
            ps.executeUpdate();
        } catch (Exception e) {
            throw  e;
        }finally{
            this.conectar();
        }
        
    }

    @Override
    public void update(Usuario usuario) throws Exception {
        
        try {
            this.conectar();
            
            String sql="update usuarios SET usuario=?, correo=?,clave=MD5(?) where id=?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getCorreo());
            ps.setString(3, usuario.getClave());
            ps.setInt(4, usuario.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw  e;
        }finally{
            this.conectar();
        }
        
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            this.conectar();
            
            PreparedStatement ps = this.conn.prepareStatement("delete from usuarios where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw  e;
        }finally{
            this.desconectar();
        }
        
    }

    @Override
    public Usuario getById(int id) throws Exception {
        
        Usuario us = new Usuario();
        try {
            this.conectar();
            
            PreparedStatement ps = this.conn.prepareStatement("select * from usuarios where id = ?");
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                us.setId(rs.getInt("id"));
                us.setUsuario(rs.getString("usuario"));
                us.setCorreo(rs.getString("correo"));
                us.setClave(rs.getString("clave"));
                
                
            }
        } catch (Exception e) {
            throw  e;
        }finally{
            this.desconectar();
        }
        return us;
        
    }

    @Override
    public List<Usuario> getAll() throws Exception {
        List<Usuario> lista = null;
        try {
            this.conectar();
            
            PreparedStatement ps = this.conn.prepareStatement("select * from usuarios");
            ResultSet rs = ps.executeQuery();
            
            lista = new ArrayList<Usuario>();
            while(rs.next()){
                Usuario us = new Usuario();
                
                us.setId(rs.getInt("id"));
                us.setUsuario(rs.getString("usuario"));
                us.setCorreo(rs.getString("correo"));
                us.setClave(rs.getString("clave"));
                
                lista.add(us);
            }
            rs.close();
            ps.close();
            
        } catch (Exception e) {
            throw  e;
        }finally{
            this.desconectar();
        }
        return lista;
    }
    
}
