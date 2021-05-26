
package com.emergentes.controlador;

import com.emergentes.dao.PermisoDAO;
import com.emergentes.dao.PermisosDAOimpl;
import com.emergentes.modelo.Permisos;
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
@WebServlet(name = "PermisoControlador", urlPatterns = {"/PermisoControlador"})
public class PermisoControlador extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            Permisos pr=new Permisos();
            int id;
            PermisoDAO dao = new PermisosDAOimpl();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

            switch (action) {
                case "add":
                    request.setAttribute("permiso",pr);
                    request.getRequestDispatcher("frmpermisos.jsp").forward(request, response);
                    break;
                case "edit":
                    id=Integer.parseInt(request.getParameter("id"));
                    pr = dao.getById(id);
                    request.setAttribute("permiso",pr);
                    request.getRequestDispatcher("frmpermisos.jsp").forward(request, response);
                    break;
                case "delete":
                    id=Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("PermisoControlador");
                    break;
                case "view":
                    //obtener la lsita de objetos
                    List<Permisos> lista = dao.getAll();
                    request.setAttribute("permiso",lista);
                    request.getRequestDispatcher("permisos.jsp").forward(request, response);
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }
        
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id= Integer.parseInt(request.getParameter("id"));
        int id_usuario= Integer.parseInt(request.getParameter("id_usuario"));
        int id_rol= Integer.parseInt(request.getParameter("id_rol"));
        
        Permisos pr=new Permisos();
        pr.setId(id);
        pr.setId_usuario(id_usuario);
        pr.setId_rol(id_rol);
        
        PermisoDAO dao = new PermisosDAOimpl();
        if (id == 0) {
            try {
                //nuevo registro
                
                dao.insert(pr);
            } catch (Exception ex) {
                System.out.println("Errre a insertar "+ex.getMessage());
            }
            
        } else {
            //edicion de registro
            try {
                dao.update(pr);
                
            } catch (Exception ex) {
                System.out.println("Errre a insertar "+ex.getMessage());
            }
            
            
        }
        response.sendRedirect("PermisoControlador");
        
    }

}
