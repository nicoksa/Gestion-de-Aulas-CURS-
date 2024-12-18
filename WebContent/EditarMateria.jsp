<%@ page import = "entidad.*" %>
<%@ page import = "java.util.*" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en"><head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion De Aulas - abMl Materias</title>
    <script src="https://cdn.tailwindcss.com"></script>
    
    <link rel="stylesheet" href="/css/styles.css?v=rU3HU1QSfkxPJ0-lQ0ZvVUMHCvb42LZ_1gaDqGPHD-c">
    <link rel="stylesheet" href="/css/site.css?v=eosHg4ZhvtgkBfUZkaEBebp3gtfCrK_1R3e0nwILrnc">
    <link rel="stylesheet" href="/GestionDeAulas.styles.css?v=I1fNPcq-pkXH6WjNPv20YwCQmuK3uJFx2WuWZVc9ebo">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">

</head>
<body>
	
	<%
	Materia materia = (Materia) request.getAttribute("materia");
	%>
	
	<%
		List<Institucion> listaInst = new ArrayList<Institucion>();
		if (request.getAttribute("listaInstituciones") != null) {
			listaInst = (List<Institucion>) request.getAttribute("listaInstituciones");
		}
		
		
		
		List<Carrera> listaCarreras = new ArrayList<Carrera>();
		if (request.getAttribute("listaCarreras") != null) {
			listaCarreras = (List<Carrera>) request.getAttribute("listaCarreras");
		}
		
		
		List<Profesor> listaProfesores = new ArrayList<Profesor>();
		if (request.getAttribute("listaProfesores") != null) {
			listaProfesores= (List<Profesor>) request.getAttribute("listaProfesores");
		}
		
		
		List<Profesores_x_Materias> listaProf_x_Mat = new ArrayList<Profesores_x_Materias>();
		if (request.getAttribute("listaPxM") != null) {
			listaProf_x_Mat = (List<Profesores_x_Materias>) request.getAttribute("listaPxM");
		}
		
		
		String dniProfesorActual = "";
	%>


   <header class="m-4 p-4 rounded-lg shadow-lg bg-white relative">
		<div class="flex flex-row items-center justify-between w-full">
			<div class="flex items-center">
				<img
					src="http://localhost:8081/TPINT_GRUPO_2_LAB4/images/Logo_Curs.gif"
					alt="Logo del CURS" class="w-40 h-auto mr-1 mb-2">
			</div>
		</div>

		<!-- Contenedor para la sesión del usuario, posicionado de forma absoluta -->
		<div class="absolute top-0 right-0 mt-0 mr-4">
			<jsp:include page="usuarioSesion.jsp" />
		</div>
	<jsp:include page="barranavegacion.jsp" />
	<jsp:include page="errorMessage.jsp" />
	</header>
    
        <main b-dk557gbzy5="" role="main" class="w-full px-4 pt-2">
        	<section class="flex min-h-dvh w-full flex-col items-center">
			    <h2 class="w-full bg-gradient-to-tr from-zinc-800 to-zinc-500 p-4 text-center text-4xl font-bold text-gray-50">EDITAR MATERIA</h2>
			    <form class="flex h-full w-2/3 flex-col items-center justify-center rounded-md py-4" method="POST" action="ServletMaterias?Param=list">
			    
			    	 <input type="hidden" name="IdMateriaActual" value="<%= materia.getId() %>">
   					 <input type="hidden" name="DniProfesorActual" id="DniProfesorActual" value="<%= dniProfesorActual %>">
   					 
					<input type="hidden" data-val="true" data-val-required="The Id field is required." id="Id" name="Id" value="">
			        
			        <div class="flex w-2/3 flex-col mb-2 self-center">
			            <label for="Nombre">Nombre</label>
			            <input class="border-2 mx-1 rounded-md border-gray-900 p-1" type="text" data-val="true" data-val-required="The Name field is required." id="Nombre" name="Nombre" value="<%=materia.getNombre()%>" required>
			            <span class="text-red-600 field-validation-valid" data-valmsg-for="Name" data-valmsg-replace="true"></span>
			        </div>
			
			
			        <div class="flex w-2/3 flex-col mb-2 self-center">
					    <label for="CarreraId">Carrera </label>
					    <select class="border-2 mx-1 rounded-md border-gray-900 p-1" data-val="true" 
					            data-val-required="Debes Seleccionar una Carrera" id="CarreraId" name="CarreraId">
					        <% for (Carrera carrera : listaCarreras) { %>
					            <option value="<%= carrera.getId() %>" 
					                    <%= (carrera.getId() == materia.getCarrera().getId()) ? "selected" : "" %>>
					                <%= carrera.getNombre() %>
					            </option>
					        <% } %>
					    </select>
					    <span class="text-red-600 field-validation-valid" data-valmsg-for="CourseId" data-valmsg-replace="true"></span>
					</div>
					
			       
			

			        <div class="flex w-2/3 justify-between">
			            <button type="submit" name="EditarMateria" value="<%= materia.getId() %> "class="mt-3 w-48 h-11 rounded-md border border-transparent bg-gradient-to-tr from-sky-800 to-sky-500 px-4 py-2 gap-2 text-center text-l text-white shadow-md transition-all hover:shadow-lg hover:bg-slate-700 focus:bg-slate-700 focus:shadow-none active:bg-slate-700 active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none flex items-center justify-center">
			                <i class="fa fa-pencil" aria-hidden="true"></i>  Editar Materia
			            </button>
			            <a class="mt-3 w-48 h-11 rounded-md border border-transparent bg-gradient-to-tr from-purple-800 to-purple-300 px-4 py-2 gap-2 text-center text-l text-white shadow-md transition-all hover:shadow-lg hover:bg-slate-700 focus:bg-slate-700 focus:shadow-none active:bg-slate-700 active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none flex items-center justify-center" href="ServletMaterias?Param=list">
			                <i class="fa-solid fa-ban" aria-hidden="true"></i>  Cancelar
			            </a>
			
			        </div>
			
			
			    <input name="__RequestVerificationToken" type="hidden" value="CfDJ8PsupENnyaxJsSE9YBIZHT6dgu3F4p6F7SAPilvN82F3hdsHyM3IY7WEz_lW1O3XEw0dDfQXpsDh_AbTBd27JyKjVYAFtMi-FkD6qhcQ3jMUfj-hHBQVSh4rOME0MrsLUOgnQvsJN8dvFE8t285FrfQ"></form>
			
			</section>

        </main>
    

    <script src="/lib/jquery/dist/jquery.min.js"></script>

    <script src="/js/site.js?v=hRQyftXiu1lLX2P9Ly9xa4gHJgLeR1uGN5qegUobtGo"></script>
    

    

<!-- Visual Studio Browser Link -->
<script type="text/javascript" src="/_vs/browserLink" async="async" id="__browserLink_initializationData" data-requestid="2f8998a884764702a38b28b8792bed9d" data-requestmappingfromserver="false" data-connecturl="http://localhost:54306/d44215c8dbef47c699c036401f907765/browserLink"></script>
<!-- End Browser Link -->
<script src="/_framework/aspnetcore-browser-refresh.js"></script>

</body></html>