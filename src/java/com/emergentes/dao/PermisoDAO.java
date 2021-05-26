/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.dao;

import com.emergentes.modelo.Permisos;
import java.util.List;

/**
 *
 * @author Jorge
 */
public interface PermisoDAO {
    public void insert(Permisos permisos) throws Exception;
    public void update(Permisos permisos) throws Exception;
    public void delete(int id) throws Exception;
    public Permisos  getById (int id) throws Exception;
    public List<Permisos> getAll() throws Exception;
    
}
