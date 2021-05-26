/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.controlador;

import com.emergentes.dao.RolDAO;
import com.emergentes.dao.RolDAOimpl;
import com.emergentes.modelo.Roles;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jorge
 */
@WebServlet(name = "RolControlador", urlPatterns = {"/RolControlador"})
public class RolControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            Roles r=new Roles();
            int id;
            RolDAO dao = new RolDAOimpl();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

            switch (action) {
                case "add":
                    request.setAttribute("lista",r);
                    request.getRequestDispatcher("frmroles.jsp").forward(request, response);
                    break;
                case "edit":
                    id=Integer.parseInt(request.getParameter("id"));
                    r = dao.getById(id);
                    request.setAttribute("lista",r);
                    request.getRequestDispatcher("frmroles.jsp").forward(request, response);
                    break;
                case "delete":
                    id=Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("RolControlador");
                    break;
                case "view":
                    //obtener la lsita de objetos
                    List<Roles> lista = dao.getAll();
                    request.setAttribute("lista",lista);
                    request.getRequestDispatcher("roles.jsp").forward(request, response);
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id =Integer.parseInt(request.getParameter("id"));
        String descripcion = request.getParameter("descripcion");
        
        Roles r = new Roles();
        r.setId(id);
        r.setDescripcion(descripcion);
        
        RolDAO dao =new RolDAOimpl();
        
        if (id == 0) {
            try {
                //nuevo registro
                
                dao.insert(r);
            } catch (Exception ex) {
                System.out.println("Errre a insertar "+ex.getMessage());
            }
            
        } else {
            //edicion de registro
            try {
                dao.update(r);
                
            } catch (Exception ex) {
                System.out.println("Errre a insertar "+ex.getMessage());
            }
            
            
        }
        response.sendRedirect("RolControlador");
        
    }


}
