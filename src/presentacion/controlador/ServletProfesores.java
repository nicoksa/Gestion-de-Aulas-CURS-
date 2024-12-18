package presentacion.controlador;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Institucion;
import entidad.Profesor;
import negocioImpl.ProfesorNegocioImpl;
import java.util.*;


@WebServlet("/ServletProfesores")
public class ServletProfesores extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    ProfesorNegocioImpl neg = new ProfesorNegocioImpl();
	

	
    public ServletProfesores() {
        super();
        
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	
    	String param = request.getParameter("Param");	
    	
        // Primero listar activos
        if (param != null && param.equals("list")) {
            request.setAttribute("listaProfesores", neg.listar());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarProfesores.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Luego listar inactivos
        if (param != null && param.equals("listinactivos")) {
            ArrayList<Profesor> inactivos = neg.listarinactivos();
            request.setAttribute("listarInactivos", inactivos);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarInactivos.jsp");
            dispatcher.forward(request, response);
            return;
        }
    

		
		// PROFESOR SELECCIONADO PARA ELIMINAR
		
		
		if (request.getParameter("dniParaEliminar") != null)
		{
			
			
			 String dni = request.getParameter("dniParaEliminar").trim();
			 Profesor profesor = neg.traerProfesor(dni);
			 
			 if (profesor != null) {
	                
	                request.setAttribute("profesor", profesor);	             
	                RequestDispatcher dispatcher = request.getRequestDispatcher("/EliminarProfesor.jsp");
	                dispatcher.forward(request, response);
	            } else {
	                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Profesor no encontrado");
	            }
		}
		
		
		// PROFESOR SELECCIONADO PARA EDITAR
		
	
		
		if(request.getParameter("dniParaEditar") != null)
		{
			String dni = request.getParameter("dniParaEditar").trim();
			Profesor profesor = neg.traerProfesor(dni);
			
			if(profesor != null) {
				request.setAttribute("profesor",profesor);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/EditarProfesor.jsp");
				dispatcher.forward(request, response);
			}else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "Profesor no encontrado");
			}
								
		}
		

	}
		
			
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//CREAR PROFESOR
		
		if(request.getParameter("btnCrearProfesor") != null)
		{
			
			boolean estado = false;
			
			String dni = request.getParameter("Dni");
			String nombre = request.getParameter("FirstName");
			String apellido = request.getParameter("LastName");
			String telefono = request.getParameter("PhoneNumber");
			String email = request.getParameter("Email");
			
			
		    String errorMessage = "";

		    // Validaciones
		    if (dni == null || !dni.matches("\\d+")) {
		        errorMessage = "El campo DNI debe contener solo n˙meros.<br>";
		    }

		    
		    if (nombre == null || !nombre.matches("[a-zA-Z·ÈÌÛ˙¡…Õ”⁄Ò—\\s]+")) {
		        errorMessage += "El campo Nombre solo puede contener letras y espacios.<br>";
		    }

		   
		    if (apellido == null || !apellido.matches("[a-zA-Z·ÈÌÛ˙¡…Õ”⁄Ò—\\s]+")) {
		        errorMessage += "El campo Apellido solo puede contener letras y espacios.<br>";
		    }

		    
		    if (telefono == null || !telefono.matches("\\d+")) {
		        errorMessage += "El campo TelÈfono debe contener solo n˙meros.<br>";
		    }

		    if (!errorMessage.isEmpty()) {
		       
		        request.setAttribute("errorMessage", errorMessage);
		        RequestDispatcher rd = request.getRequestDispatcher("/CrearProfesor.jsp");
		        rd.forward(request, response);
		        return;
		    }
		
	    
			Profesor profesor = new Profesor(dni,nombre,apellido,telefono,email);
			
			estado = neg.insert(profesor);	
			
			if(estado) {
				RequestDispatcher rd = request.getRequestDispatcher("/CrearProfesor.jsp?estado=exito");
				rd.forward(request,response);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/CrearProfesor.jsp?estado=error");
				rd.forward(request,response);
			}
			
		}
		
		
		// ELIMINAR PROFESOR
		

		
		if (request.getParameter("IdAEliminar") != null) 
		{
			String dni = request.getParameter("IdAEliminar");
			Profesor profesor = new Profesor();
			profesor.setDni(dni);
	        boolean estado = neg.delete(profesor);

	        if (estado) {
	        	response.sendRedirect("ServletProfesores?Param=list&estadoEliminar=exito");
	        	System.out.println("no se si hay exito");
	        } else {     
	        	response.sendRedirect("ServletProfesores?Param=list&estadoEliminar=error");
	        }
		}
		
		
		//EDITAR PROFESOR
		
		if( request.getParameter("dniAeditar") != null) 
		{
			String dni = request.getParameter("dniAeditar");
            String nombre = request.getParameter("FirstName");
            String apellido = request.getParameter("LastName");
            String email = request.getParameter("Email");
            String telefono = request.getParameter("PhoneNumber");
            
           
            String errorMessage = "";

            // Validaciones
            if (dni == null || !dni.matches("\\d+")) {
                errorMessage = "El campo DNI debe contener solo n˙meros.<br>";
            }

          
            if (nombre == null || !nombre.matches("[a-zA-Z·ÈÌÛ˙¡…Õ”⁄Ò—\\s]+")) {
                errorMessage += "El campo Nombre solo puede contener letras y espacios.<br>";
            }

            
            if (apellido == null || !apellido.matches("[a-zA-Z·ÈÌÛ˙¡…Õ”⁄Ò—\\s]+")) {
                errorMessage += "El campo Apellido solo puede contener letras y espacios.<br>";
            }

            
            if (telefono == null || !telefono.matches("\\d+")) {
                errorMessage += "El campo TelÈfono debe contener solo n˙meros.<br>";
            }

            if (!errorMessage.isEmpty()) {
               
                request.setAttribute("errorMessage", errorMessage);
                Profesor profesor = neg.traerProfesor(dni); 
                request.setAttribute("profesor", profesor); 
                RequestDispatcher rd = request.getRequestDispatcher("/EditarProfesor.jsp");
                rd.forward(request, response);
                return;
            }
            

           Profesor profesor = new Profesor(dni,nombre,apellido,telefono, email);
            boolean estado = neg.update(profesor); 

            if (estado) {
                response.sendRedirect("ServletProfesores?Param=list&estadoEditar=exito");
            } else {
            	response.sendRedirect("ServletProfesores?Param=list&estadoEditar=error");
            }
		}
		
		
		//REACTIVAR PROFESOR
		
		if (request.getParameter("reactivar") != null) 
		{
	            
			String dni = request.getParameter("reactivar");
			Profesor profesor = new Profesor();
			profesor.setDni(dni);
	        boolean estado = neg.reactivarProfesor(profesor);

	        request.setAttribute("estado", estado);
	        
	        ArrayList<Profesor>inactivos= neg.listarinactivos();
			request.setAttribute("listarInactivos", inactivos);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarInactivos.jsp");
			dispatcher.forward(request, response);
		}
	
	}

}
