
package com.emergentes.controlador;

import com.emergentes.dao.UsuarioDAO;
import com.emergentes.dao.UsuarioDAOimpl;
import com.emergentes.modelo.Usuario;
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
@WebServlet(name = "UsuarioControlador", urlPatterns = {"/UsuarioControlador"})
public class UsuarioControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Usuario us=new Usuario();
            int id;
            UsuarioDAO dao = new UsuarioDAOimpl();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

            switch (action) {
                case "add":
                    request.setAttribute("usuario",us);
                    request.getRequestDispatcher("frmusuario.jsp").forward(request, response);
                    break;
                case "edit":
                    id=Integer.parseInt(request.getParameter("id"));
                    us = dao.getById(id);
                    request.setAttribute("usuario",us);
                    request.getRequestDispatcher("frmusuario.jsp").forward(request, response);
                    break;
                case "delete":
                    id=Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("UsuarioControlador");
                    break;
                case "view":
                    //obtener la lsita de objetos
                    List<Usuario> lista = dao.getAll();
                    request.setAttribute("usuario",lista);
                    request.getRequestDispatcher("usuarios.jsp").forward(request, response);
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
        String usaurio=request.getParameter("usuario");
        String correo = request.getParameter("correo");
        String clave = request.getParameter("clave");
        
        Usuario us = new Usuario();
        us.setId(id);
        us.setUsuario(usaurio);
        us.setCorreo(correo);
        us.setClave(clave);
        
        UsuarioDAO dao = new UsuarioDAOimpl();
        if (id == 0) {
            try {
                //nuevo registro
                
                dao.insert(us);
            } catch (Exception ex) {
                System.out.println("Errre a insertar "+ex.getMessage());
            }
            
        } else {
            //edicion de registro
            try {
                dao.update(us);
                
            } catch (Exception ex) {
                System.out.println("Errre a insertar "+ex.getMessage());
            }
            
            
        }
        response.sendRedirect("UsuarioControlador");
        
    }

}
