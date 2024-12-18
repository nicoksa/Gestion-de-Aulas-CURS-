package presentacion.controlador;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocioImpl.AulaNegocioImpl;
import negocioImpl.MateriaNegocioImpl;
import negocioImpl.ProfesorNegocioImpl;
import negocioImpl.PxMNegocioImpl;
import entidad.Aula;
import entidad.Profesor;
import entidad.Profesores_x_Materias;
import entidad.Materia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@WebServlet("/ServletAulas")
public class ServletAulas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	AulaNegocioImpl neg = new AulaNegocioImpl();
	ProfesorNegocioImpl profesorNeg = new ProfesorNegocioImpl(); 
    MateriaNegocioImpl materiaNeg = new MateriaNegocioImpl(); 
    PxMNegocioImpl negPxM = new PxMNegocioImpl();
	

    public ServletAulas() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// LISTAR
		
		if(request.getParameter("Param")!=null)
		{
			request.setAttribute("listaAulas", neg.listar());	
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarAulas.jsp");
			dispatcher.forward(request, response);
		}
		
		// AULA SELECCIONADA PARA ELIMINAR
		
		String idAula =request.getParameter("id");
		
		if (idAula != null)
		{
			 int id = Integer.parseInt(request.getParameter("id"));
			 Aula aula = neg.traerAula(id);
			 
			 if (aula != null) {
	                
	                request.setAttribute("aula", aula);	             
	                RequestDispatcher dispatcher = request.getRequestDispatcher("/EliminarAula.jsp");
	                dispatcher.forward(request, response);
	            } else {
	                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Aula no encontrada");
	            }
		  } 
		
		// AULA SELECCIONADA PARA EDITAR
		
		String idAEditar =request.getParameter("idEdit");
		
		if(idAEditar != null)
		{
			int id = Integer.parseInt(idAEditar);
			Aula aula = neg.traerAula(id);
			
			if(aula != null) {
				request.setAttribute("aula",aula);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/EditarAula.jsp");
				dispatcher.forward(request, response);
			}else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "Aula no encontrada");
			}
			
			
		}
		
		// EDITAR AULA
		
		
		if( request.getParameter("idAEditar") != null) 
		{
			int id = Integer.parseInt(request.getParameter("idAEditar"));
            String nombre = request.getParameter("Nombre");
            int capacidad = Integer.parseInt(request.getParameter("Capacidad"));
            


            Aula aula = new Aula(id, nombre, capacidad);
            boolean estado = neg.update(aula); 

            if (estado) {
                response.sendRedirect("ServletAulas?Param=list&estadoEditar=exito");
            } else {
            	response.sendRedirect("ServletAulas?Param=list&estadoEditar=error");
            }
		}
		
		
		
		
		// ELIMINAR AULA
		
		String idAEliminar = request.getParameter("idAEliminar");
		
		if (idAEliminar != null) 
		{
	            
			int id = Integer.parseInt(idAEliminar);
			Aula aula = new Aula();
			aula.setId(id);
	        boolean estado = neg.delete(aula);

	        if (estado) {
	        	response.sendRedirect("ServletAulas?Param=list&estadoEliminar=exito");
	        } else {     
	        	 response.sendRedirect("ServletAulas?Param=list&estadoEliminar=error");
	        }
		}
		
		
	}


	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//CREAR AULA 
		
		boolean estado = false;
	
		if(request.getParameter("btnCrearAula") != null)
		{
			String idAulaParam = request.getParameter("IdAula");
			
			String nombre = request.getParameter("Name");
			int capacidad = Integer.parseInt(request.getParameter("Capacidad"));
			
			String errorMessage = "";
			
			 if (idAulaParam == null || !idAulaParam.matches("\\d+")) {
	            errorMessage += "El campo Número de Aula solo debe contener números.<br>";
		        }

			 if (!errorMessage.isEmpty()) 
			 {
				 request.setAttribute("errorMessage", errorMessage);
		         request.getRequestDispatcher("/CrearAula.jsp").forward(request, response);
		         return;
		      }
		        
			 int id = Integer.parseInt(idAulaParam);    
			 
			 Aula aula = new Aula(id,nombre,capacidad);
			
			 estado = neg.insert(aula);
			
			 if(estado) {
				 RequestDispatcher rd = request.getRequestDispatcher("/CrearAula.jsp?estado=exito");
				 rd.forward(request,response);
			 }else {
				 RequestDispatcher rd = request.getRequestDispatcher("/CrearAula.jsp?estado=error");
				 rd.forward(request,response);
			}
			
		}
		


		    if ("verDisponibilidad".equals(request.getParameter("Param"))) {
		    	
		    	
		    	//Parametros para validacion
		    	String paramStudents = request.getParameter("Students");
		    	String paramDate = request.getParameter("Date");
		    	String paramHour = request.getParameter("Hour");
		    	String paramTotalHours = request.getParameter("TotalHours");
		    	String paramProfesorId = request.getParameter("ProfesorId");
		    	String paramMateriaId = request.getParameter("MateriaId");
		    	
		    	
		    	ArrayList<Profesores_x_Materias> listaProf_x_Mat = negPxM.listar();
				
				for(Profesores_x_Materias pxm : listaProf_x_Mat) {
					
					Materia materia2 = materiaNeg.traerMateria(pxm.getMateria().getId());				
					pxm.setMateria(materia2);
				}

		    	// Validar que no sean nulos o vacios antes de parsear
		    	if (paramStudents == null || paramStudents.isEmpty() || 
		    	    paramDate == null || paramDate.isEmpty() || 
		    	    paramHour == null || paramHour.isEmpty() || 
		    	    paramTotalHours == null || paramTotalHours.isEmpty() || 
		    	    paramProfesorId == null || paramProfesorId.isEmpty() || 
		    	    paramMateriaId == null || paramMateriaId.isEmpty()) {
		    		
					request.setAttribute("listaMaterias", materiaNeg.listar());
					request.setAttribute("listaProfesores", profesorNeg.listar());
					request.setAttribute("listaProf_x_Mat", listaProf_x_Mat);
		    	    request.setAttribute("errorMessage", "Por favor ingresar todos los datos para crear la reserva");
		    	    request.getRequestDispatcher("/CrearReserva.jsp").forward(request, response);
		    	    return;
		    	}
		        // Obtener y parsear los parámetros del formulario
		    		    	
		        int capacidadMinima = Integer.parseInt(request.getParameter("Students"));
		        Date fecha = java.sql.Date.valueOf(request.getParameter("Date"));
		        String fecha2 = request.getParameter("Date");
		        int horaInicio2 = Integer.parseInt(request.getParameter("Hour"));
		        int horasReserva = Integer.parseInt(request.getParameter("TotalHours"));
		        int horaFinal2 = horaInicio2 + horasReserva;
		        
		        java.sql.Time horaInicio = java.sql.Time.valueOf(request.getParameter("Hour") + ":00:00");
		        int totalHoras = Integer.parseInt(request.getParameter("TotalHours"));
		        java.sql.Time horaFinal = java.sql.Time.valueOf((Integer.parseInt(request.getParameter("Hour")) + totalHoras) + ":00:00");
		        
		        String profesorDni = request.getParameter("ProfesorId");
		        int materiaId = Integer.parseInt(request.getParameter("MateriaId"));

		        // Obtener las aulas disponibles
		        AulaNegocioImpl aulaNegocio = new AulaNegocioImpl();
		        List<Aula> aulasDisponibles = aulaNegocio.filtrarAulasDisponibles(capacidadMinima, fecha, horaInicio, horaFinal);

		        Profesor profesor = profesorNeg.traerProfesor(profesorDni);
		        Materia materia = materiaNeg.traerMateria(materiaId);
		        
		        // Pasar las aulas al JSP
		        request.setAttribute("fecha", fecha2);
		        request.setAttribute("profesor", profesor.getNombre() + " " + profesor.getApellido());
		        request.setAttribute("materia", materia.getNombre());
		        request.setAttribute("numeroEstudiantes", capacidadMinima);
		        request.setAttribute("horaInicio", horaInicio2);
		        request.setAttribute("horaFinal", horaFinal2);
		        request.setAttribute("listaAulas", aulasDisponibles);
				request.setAttribute("listaMaterias", materiaNeg.listar());
				request.setAttribute("listaProfesores", profesorNeg.listar());
				request.setAttribute("listaProf_x_Mat", listaProf_x_Mat);
	
		
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/CrearReserva.jsp");
		        dispatcher.forward(request, response);
		    }  
		    
	}
	
	
	
	
}

