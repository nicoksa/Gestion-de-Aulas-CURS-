  package presentacion.controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Materia;
import entidad.Profesor;
import entidad.Institucion;
import entidad.Aula;
import entidad.Carrera;
import entidad.Profesores_x_Materias;
import entidad.Reserva;
import negocioImpl.*;


@WebServlet("/ServletMaterias")
public class ServletMaterias extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	MateriaNegocioImpl neg = new MateriaNegocioImpl();
	InstitucionNegocioImpl negInstitucion = new InstitucionNegocioImpl();
	CarreraNegocioImpl negCarrera = new CarreraNegocioImpl();
	ProfesorNegocioImpl negProfesor = new ProfesorNegocioImpl();
	PxMNegocioImpl negPxm = new PxMNegocioImpl();
	
	
    public ServletMaterias() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// LISTAR
		
			if("list".equals(request.getParameter("Param")))
			{
				 ArrayList<Profesores_x_Materias> listaPxM = negPxm.listar();
										
				 for (Profesores_x_Materias pxm : listaPxM) {
					 Profesor profesor = negProfesor.traerProfesor(pxm.getProfesor().getDni()); 	                                            
		             pxm.setProfesor(profesor);		          	            
		         }
				
				
				request.setAttribute("listaMaterias", neg.listar());
				request.setAttribute("listaCarreras", negCarrera.listar());
				request.setAttribute("listaProf_x_Mat" , listaPxM);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarMaterias.jsp");
				dispatcher.forward(request, response);
			}
				
				
			// LISTAS PARA CREAR MATERIA
				
			if("datos".equals(request.getParameter("Param")))
			{
				request.setAttribute("listaInstituciones",negInstitucion.listar());
				request.setAttribute("listaCarreras", negCarrera.listar());
				request.setAttribute("listaProfesores", negProfesor.listar());
				RequestDispatcher dispatcher = request.getRequestDispatcher("/CrearMateria.jsp");
				dispatcher.forward(request, response);
							
			}
			
			
			
			// MATERIA SELECCIONADA PARA EDITAR
				
				if(request.getParameter("idParaEditar") != null)
				{
					int id = Integer.parseInt(request.getParameter("idParaEditar").trim());
   					Materia materia = neg.traerMateria(id);
   					
		
					if(materia != null) {
						request.setAttribute("materia",materia);
						request.setAttribute("listaPxM", negPxm.listar());
						request.setAttribute("listaProfesores", negProfesor.listar());
						request.setAttribute("listaCarreras", negCarrera.listar());
						request.setAttribute("listaInstituciones", negInstitucion.listar());
						RequestDispatcher dispatcher = request.getRequestDispatcher("/EditarMateria.jsp");
						dispatcher.forward(request, response);
					}else {
						response.sendError(HttpServletResponse.SC_NOT_FOUND, "Materia no encontrada");
					}
										
				}
				
				
				// ELIMINAR MATERIA
				
				
				
				
				if (request.getParameter("eliminar") != null) 
				{
			            
					int id = Integer.parseInt(request.getParameter("eliminar").trim());
					Materia materia = new Materia();
					materia.setId(id);
			        boolean estado = neg.delete(materia);

			        if (estado) {
			        	response.sendRedirect("ServletMaterias?Param=list&estadoEliminar=exito");
			        } else {     
			        	response.sendRedirect("ServletMaterias?Param=list&estadoEliminar=error");
			        }
				}


	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// LISTARfiltrado

		if (request.getParameter("btnFiltrar") != null)
		{
			ArrayList<Profesores_x_Materias> listaPxM = negPxm.listar();
			int id = Integer.parseInt(request.getParameter("ddlcarrera"));
			for (Profesores_x_Materias pxm : listaPxM) {
				Profesor profesor = negProfesor.traerProfesor(pxm.getProfesor().getDni());
				pxm.setProfesor(profesor);
			}

			request.setAttribute("listaMaterias", neg.listarfiltrado(id));
			request.setAttribute("listaCarreras", negCarrera.listar());
			request.setAttribute("listaProf_x_Mat", listaPxM);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarMaterias.jsp");
			dispatcher.forward(request, response);
		}
		
		//CREAR MATERIA
		
		if(request.getParameter("btnCrearMateria") != null)
		{
			
	        String nombre = request.getParameter("Name");
	        String dniProfesor = request.getParameter("ProfesorId");
	        String carreraParam = request.getParameter("CarreraId");
	        String institucionParam = request.getParameter("InstitucionId");

	    
	        String errorMessage = "";

	        // Validacion
	        if (nombre == null || !nombre.matches("[a-zA-Z·ÈÌÛ˙¡…Õ”⁄Ò—\\s]+")) {
	            errorMessage = "El campo Nombre solo puede contener letras y espacios.";
	            request.setAttribute("errorMessage", errorMessage);
	            request.setAttribute("listaInstituciones", negInstitucion.listar());
	            request.setAttribute("listaCarreras", negCarrera.listar());
	            request.setAttribute("listaProfesores", negProfesor.listar());
	            RequestDispatcher rd = request.getRequestDispatcher("/CrearMateria.jsp");
	            rd.forward(request, response);
	            return;
	        }
			
			int idActual;
			boolean estadoPxm= false;
			

			int idCarrera = Integer.parseInt(carreraParam);
			int idInstitucion = Integer.parseInt(institucionParam);
			
			Profesor profesor = new Profesor();
			profesor.setDni(dniProfesor);
			
			Institucion institucion = new Institucion();
			institucion.setId(idInstitucion);
			
			Carrera carrera = new Carrera();
			carrera.setId(idCarrera);
			
			
			
			Materia materia = new Materia();
			materia.setNombre(nombre);
			materia.setCarrera(carrera);
			materia.setInstitucion(institucion);
					
			idActual = neg.insert(materia);			
			materia.setId(idActual);
			
			Profesores_x_Materias pxm = new Profesores_x_Materias(profesor,materia);
			
			estadoPxm = negPxm.insert(pxm);
			
			request.setAttribute("listaInstituciones",negInstitucion.listar());
			request.setAttribute("listaCarreras", negCarrera.listar());
			request.setAttribute("listaProfesores", negProfesor.listar());
			
			if(estadoPxm) {
				RequestDispatcher rd = request.getRequestDispatcher("/CrearMateria.jsp?estado=exito");
				rd.forward(request,response);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/CrearMateria.jsp?estado=error");
				rd.forward(request,response);
			}
			
		}
		
		

		
	
		//EDITAR MATERIA
		
		if( request.getParameter("EditarMateria") != null) 
		{
			String dniProfesorActual = request.getParameter("DniProfesorActual").trim();
			Profesor profesorActual = new Profesor();
			profesorActual.setDni(dniProfesorActual);
			
			int idMateria = Integer.parseInt(request.getParameter("EditarMateria").trim());
			String nombre = request.getParameter("Nombre");
			//String dniProfesor = request.getParameter("ProfesorId").trim();
			//Profesor profesorNuevo = new Profesor();
			//profesorNuevo.setDni(dniProfesor);
			int idCarrera = Integer.parseInt(request.getParameter("CarreraId"));
			Carrera carrera = negCarrera.traerCarrera(idCarrera);
			
			//TRAER INSTITUCION
			Institucion institucion = new Institucion();
			institucion.setId(carrera.getInstitucion().getId());
			
			
		    String errorMessage = "";

		    // Validacion
		    if (nombre == null || !nombre.matches("[a-zA-Z·ÈÌÛ˙¡…Õ”⁄Ò—\\s]+")) {
		        errorMessage = "El campo Nombre solo puede contener letras y espacios.<br>";
		    }

		    if (!errorMessage.isEmpty()) {
		        
		        Materia materiaActual = neg.traerMateria(idMateria);
		        request.setAttribute("materia", materiaActual);
		        request.setAttribute("errorMessage", errorMessage);
		        request.setAttribute("listaCarreras", negCarrera.listar());
		        RequestDispatcher rd = request.getRequestDispatcher("/EditarMateria.jsp");
		        rd.forward(request, response);
		        return;
		    }
			
			Materia materia = new Materia(idMateria,carrera,institucion,nombre);
			
			//Profesores_x_Materias pxmNuevo = new Profesores_x_Materias(profesorNuevo,materia);
			//Profesores_x_Materias pxmActual = new Profesores_x_Materias(profesorActual,materia);
            

           
            boolean estado = neg.update(materia); 
            //boolean estadoPxm = negPxm.update(pxmNuevo, pxmActual);

            if (estado) {
                response.sendRedirect("ServletMaterias?Param=list&estadoEditar=exito");
            } else {
            	response.sendRedirect("ServletMaterias?Param=list&estadoEditar=error");
            }
		}
		
	}

}
