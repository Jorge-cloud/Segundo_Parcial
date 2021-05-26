
package com.emergentes.modelo;

/**
 *
 * @author Jorge
 */
public class Roles {
    
    private int id;
    private String descripcion;

    public Roles() {
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "roles{" + "id=" + id + ", descripcion=" + descripcion + '}';
    }
    
    
    
}
