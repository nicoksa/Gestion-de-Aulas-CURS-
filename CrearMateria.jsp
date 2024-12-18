<%@ page import="entidad.*"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Gestion De Aulas - Abml Materias</title>
<script src="https://cdn.tailwindcss.com"></script>

<link rel="stylesheet"
	href="/css/styles.css?v=rU3HU1QSfkxPJ0-lQ0ZvVUMHCvb42LZ_1gaDqGPHD-c">
<link rel="stylesheet"
	href="/css/site.css?v=eosHg4ZhvtgkBfUZkaEBebp3gtfCrK_1R3e0nwILrnc">
<link rel="stylesheet"
	href="/GestionDeAulas.styles.css?v=I1fNPcq-pkXH6WjNPv20YwCQmuK3uJFx2WuWZVc9ebo">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">

</head>
<body>
	

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
			listaProfesores = (List<Profesor>) request.getAttribute("listaProfesores");
		}

		List<Profesores_x_Materias> listaProf_x_Mat = new ArrayList<Profesores_x_Materias>();
		if (request.getAttribute("listaProf_x_Mat") != null) {
			listaProf_x_Mat = (List<Profesores_x_Materias>) request.getAttribute("listaProf_x_Mat");
		}
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


	<main b-dk557gbzy5="" role="main" class="w-full px-4 pt-2"> <section
		class="flex min-h-dvh w-full flex-col items-center">
	
	<h2 class="w-full bg-gradient-to-tr from-zinc-800 to-zinc-500 p-4 text-center text-4xl font-bold text-gray-50">ALTA DE MATERIA</h2>

	<%
		String estado = request.getParameter("estado");
		if ("exito".equals(estado)) {
	%>
	<div
		style="background-color: #d4edda; color: #155724; padding: 10px; border: 1px solid #c3e6cb; border-radius: 5px; margin-bottom: 15px;">
		<strong> Materia agregado exitosamente.</strong>
	</div>
	<%
		} else if ("error".equals(estado)) {
	%>
	<div
		style="background-color: #f8d7da; color: #721c24; padding: 10px; border: 1px solid #f5c6cb; border-radius: 5px; margin-bottom: 15px;">
		<strong> Error: No se pudo agregar la materia.</strong>
	</div>
	<%
		}
	%>
	<form
		class="flex h-full w-2/3 flex-col items-center justify-center rounded-md py-4"
		method="POST" action="ServletMaterias?Param=list">

		<div class="flex w-2/3 flex-col self-center">
			<label for="Name" class="font-regular text-gray-700">Nombre de
				la Materia</label> <input
				class="border-2 mx-1 rounded-md border-gray-900 p-1" type="text"
				data-val="true" data-val-required="The Name field is required."
				id="Name" name="Name" placeholder="Ingrese un Nombre" value="" required>
			<span class="text-red-600 field-validation-valid"
				data-valmsg-for="Name" data-valmsg-replace="true"></span>
		</div>
		

		<div class="flex flex-col w-2/3 self-center my-2">
			<label for="InstitucionId" class="font-regular text-gray-700">Seleccionar
				Institución</label> <select
				class="border-2 mx-1 rounded-md border-gray-900 p-1" data-val="true"
				data-val-required="La institución es un campo requerido"
				id="InstitucionId" name="InstitucionId" required>
				<option value="" selected>-- Seleccionar Institucion --</option>
				<% 
					for (Institucion institucion : listaInst) {
				%>
				<option value="<%=institucion.getId()%>">
					<%=institucion.getNombre()%>
				</option>
				<%
					}
				%>
			</select>
		</div>


		<div class="flex flex-col w-2/3 self-center my-2">
			<label for="CarreraId" class="font-regular text-gray-700">Seleccionar
				Carrera</label> <select class="border-2 mx-1 rounded-md border-gray-900 p-1"
				data-val="true"
				data-val-required="La carrera es un campo requerido." id="CarreraId"
				name="CarreraId" required>
				<option value="" selected>-- Seleccionar Carrera --</option>
				<%
					for (Carrera carrera : listaCarreras) {
				%>
				<option value="<%=carrera.getId()%>"
					data-institucion-id="<%=carrera.getInstitucion().getId()%>">
					<%=carrera.getNombre()%>
				</option>
				<%
					}
				%>
			</select>
		</div>


		<div class="flex flex-col w-2/3 self-center my-2">
			<label for="ProfesorId" class="font-regular text-gray-700">Seleccionar
				Profesor</label> <select
				class="border-2 mx-1 rounded-md border-gray-900 p-1" data-val="true"
				data-val-required="El profesor es un campo requerido."
				id="ProfesorId" name="ProfesorId" required>
				<option value="" selected>-- Seleccionar Profesor --</option>
				<%
					for (Profesor profesor : listaProfesores) {
				%>
				<option value="<%=profesor.getDni()%>"><%=profesor.getNombre()%>
					<%=profesor.getApellido()%></option>
				<%
					}
				%>
			</select>
		</div>

		<div class="flex w-2/3 justify-between">
			<button type="submit" name="btnCrearMateria"
				class="mt-3 w-48 h-11 rounded-md border border-transparent bg-gradient-to-tr from-sky-800 to-sky-500 px-4 py-2 gap-2 text-center text-l text-white shadow-md transition-all hover:shadow-lg hover:bg-slate-700 focus:bg-slate-700 focus:shadow-none active:bg-slate-700 active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none flex items-center justify-center">
				<i class="fa-solid fa-book-open" aria-hidden="true"></i> Crear Materia
			</button>
			<a
				class="mt-3 w-48 h-11 rounded-md border border-transparent bg-gradient-to-tr from-purple-800 to-purple-300 px-4 py-2 gap-2 text-center text-l text-white shadow-md transition-all hover:shadow-lg hover:bg-slate-700 focus:bg-slate-700 focus:shadow-none active:bg-slate-700 active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none flex items-center justify-center"
				href="ServletMaterias?Param=list"> 
				<i class="fa-solid fa-ban"></i>
				Cancelar </a>

		</div>


		<input name="__RequestVerificationToken" type="hidden"
			value="CfDJ8PsupENnyaxJsSE9YBIZHT4jE1XlSojmyHECMcE9iw54k3sKQib8OkPICnCrQvAL6oNPkohDsNiYFFdK3I-cwvr5qPrrTF6Bo1veq5Bjl9hY8wbpVoGRh2abQkdUSu-hdO8GPk1W8_YTOaTEFwZsQYo">
	</form>

	</section> </main>

	<script>
	document.addEventListener("DOMContentLoaded", function () {
	    const institucionSelect = document.getElementById("InstitucionId");
	    const carreraSelect = document.getElementById("CarreraId");

	    // Guarda todas las opciones de carreras al cargar la página
	    const carreras = Array.from(carreraSelect.options);

	    // Escucha los cambios en el desplegable de instituciones
	    institucionSelect.addEventListener("change", function () {
	        const institucionId = this.value;

	        // Limpia las opciones actuales del desplegable de carreras
	        carreraSelect.innerHTML = "";

	        // Filtra las carreras que coinciden con la institución seleccionada
	        const filteredCarreras = carreras.filter(option => option.dataset.institucionId === institucionId);

	        // Si no hay carreras disponibles para la institución seleccionada
	        if (filteredCarreras.length === 0) {
	            const defaultOption = document.createElement("option");
	            defaultOption.textContent = "-- No hay carreras disponibles --";
	            defaultOption.value = "";
	            carreraSelect.appendChild(defaultOption);
	        } else {
	            // Agrega las opciones filtradas al desplegable
	            filteredCarreras.forEach(option => carreraSelect.appendChild(option));
	        }
	    });
	});
</script>

	<script src="/lib/jquery/dist/jquery.min.js"></script>

	<script src="/js/site.js?v=hRQyftXiu1lLX2P9Ly9xa4gHJgLeR1uGN5qegUobtGo"></script>
	


	<!-- Visual Studio Browser Link -->
	<script type="text/javascript" src="/_vs/browserLink" async="async"
		id="__browserLink_initializationData"
		data-requestid="2f8998a884764702a38b28b8792bed9d"
		data-requestmappingfromserver="false"
		data-connecturl="http://localhost:54306/d44215c8dbef47c699c036401f907765/browserLink"></script>
	<!-- End Browser Link -->
	<script src="/_framework/aspnetcore-browser-refresh.js"></script>

</body>
</html>