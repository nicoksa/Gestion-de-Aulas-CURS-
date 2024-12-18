package presentacion.controlador;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocioImpl.AdministradorNegocioImp;
import entidad.Administrador;
import entidad.Profesor;


@WebServlet("/ServletAdmin")
public class ServletAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	AdministradorNegocioImp adminNegocio = new AdministradorNegocioImp();
	
    public ServletAdmin() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
			
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		boolean usuarioexistente = false;
		int usuarioinexistente = 0;
 		if (request.getParameter("btningresar") != null)
 		{
			String usuario = request.getParameter("UserName");
			String pass = request.getParameter("Password");
			
			usuarioexistente= adminNegocio.existeusuario(usuario, pass);
			if (usuarioexistente == true)
			{
				Administrador usuarioAdmin = new Administrador();
				usuarioAdmin = adminNegocio.traerAdmin(usuario);
				
				if(usuarioAdmin != null) {
					
					HttpSession misession = request.getSession(true);
					
					misession.setAttribute("nombreUsuario", usuarioAdmin.getNombre());
					misession.setAttribute("apellidoUsuario", usuarioAdmin.getApellido());
					misession.setAttribute("dniUsuario",usuarioAdmin.getDni());

					response.sendRedirect("MenuAdministrador.jsp");
					
				}

			}else
			{
				request.setAttribute("USUINEX", usuarioinexistente);
				RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
				rd.forward(request, response);
				
			}
 		}
 		
 		
 		
 		if( request.getParameter("dniAeditar") != null) 
		{
			String dni = request.getParameter("dniAeditar");
            String nombre = request.getParameter("FirstName");
            String apellido = request.getParameter("LastName");
            
            
            String errorMessage = "";

            // Validacion de campos sin numeros
            if (nombre == null || !nombre.matches("[a-zA-Z·ÈÌÛ˙¡…Õ”⁄Ò—\\s]+")) {
                errorMessage = "El campo Nombre solo puede contener letras y espacios.";
            } else if (apellido == null || !apellido.matches("[a-zA-Z·ÈÌÛ˙¡…Õ”⁄Ò—\\s]+")) {
                
                errorMessage = "El campo Apellido solo puede contener letras y espacios.";
            }

            if (!errorMessage.isEmpty()) {
               
                request.setAttribute("errorMessage", errorMessage);
                Administrador admin = adminNegocio.traerAdmin(dni);
                request.setAttribute("admin", admin);
                RequestDispatcher rd = request.getRequestDispatcher("/editarAdministrador.jsp");
                rd.forward(request, response);
                return;
            }

  
            Administrador admin = adminNegocio.traerAdmin(dni);
            admin.setNombre(nombre);
            admin.setApellido(apellido);
          
            boolean estado = adminNegocio.update(admin); 

            if (estado) {
            	 HttpSession misession = request.getSession();
            	 misession.setAttribute("nombreUsuario", nombre);
            	 misession.setAttribute("apellidoUsuario", apellido);
            	 response.sendRedirect("MenuAdministrador.jsp?estadoEditar=exito");
            } else {
            	response.sendRedirect("MenuAdministrador.jsp?estadoEditar=error");
            }
		}
 		
		
	}
	
	
	
	
	

}
