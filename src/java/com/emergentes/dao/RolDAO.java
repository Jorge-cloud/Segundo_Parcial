/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.dao;

import com.emergentes.modelo.Roles;
import java.util.List;

/**
 *
 * @author Jorge
 */
public interface RolDAO {
    
    public void insert(Roles roles) throws Exception;
    public void update(Roles roles) throws Exception;
    public void delete(int id) throws Exception;
    public Roles getById (int id) throws Exception;
    public List<Roles> getAll() throws Exception;
}
