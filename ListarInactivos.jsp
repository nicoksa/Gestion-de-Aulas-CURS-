<%@ page import="entidad.Profesor"%>
<%@ page import="java.util.*"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Gestion de Aulas - abmL Profesores</title>
<script src="https://cdn.tailwindcss.com"></script>

<link rel="stylesheet"
	href="/css/styles.css?v=rU3HU1QSfkxPJ0-lQ0ZvVUMHCvb42LZ_1gaDqGPHD-c">
<link rel="stylesheet"
	href="/css/site.css?v=eosHg4ZhvtgkBfUZkaEBebp3gtfCrK_1R3e0nwILrnc">
<link rel="stylesheet"
	href="/GestionDeAulas.styles.css?v=I1fNPcq-pkXH6WjNPv20YwCQmuK3uJFx2WuWZVc9ebo">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">

<!-- DataTables CSS -->
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">

<!-- JQuery y DataTables JS -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var table = $('#profesores_table')
								.DataTable(
										{
											"paging" : true,
											"pageLength" : 5,
											"lengthMenu" : [ 5, 10, 20, 50 ],
											"language" : {
												"url" : "//cdn.datatables.net/plug-ins/1.10.19/i18n/Spanish.json",
												"search" : "Buscar:",
												"searchPlaceholder" : "Escribe para buscar..."
											},
											"initComplete" : function() {
												// Personaliza el campo de b�squeda
												$(
														'#profesores_table_filter input')
														.attr('placeholder',
																'Buscar en todas las columnas...');

												// Cambiar el alto del campo de b�squeda
												$(
														"#profesores_table_filter input")
														.css(
																{
																	'padding' : '8px',
																	'border' : '1px solid #ccc',
																	'border-radius' : '4px',
																	'box-shadow' : '0 2px 5px rgba(0, 0, 0, 0.1)', // Agrega una sombra al campo de b�squeda
																	'margin' : '10px' // Agrega un margen alrededor del campo de b�squeda
																});

												// A�adir funcionalidad para limpiar el campo
												$(document)
														.on(
																'click',
																'.custom-clear',
																function() {
																	$(
																			'#profesores_table_filter input')
																			.val(
																					'')
																			.trigger(
																					'keyup');
																});
											}
										});
					});
</script>


</head>
<body>

	<%
		List<Profesor> listaProf = new ArrayList<Profesor>();
		if (request.getAttribute("listaProfesores") != null) {
			listaProf = (List<Profesor>) request.getAttribute("listaProfesores");
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
	<jsp:include page="barranavegacion.jsp" /> </header>

	<main b-dk557gbzy5="" role="main" class="w-full px-4 pt-2"> <section
		class="flex min-h-dvh w-full flex-col items-center">
	<h2
		class="w-full bg-gradient-to-tr from-zinc-800 to-zinc-500 p-4 text-center text-4xl font-bold text-gray-50">LISTADO
		DE PROFESORES INACTIVOS</h2>
	<br>


	


	<div
		class="relative mt-4 flex h-full w-full flex-col overflow-scroll rounded-lg bg-white bg-clip-border shadow-md">

		<table id="profesores_table" class="display">
			<thead>
				<tr>
					<th
						class="border-b border-slate-500 bg-zinc-700 p-4 text-sm font-normal leading-none text-slate-50">
						Nombre</th>
					<th
						class="border-b border-slate-500 bg-zinc-700 p-4 text-sm font-normal leading-none text-slate-50">
						DNI</th>
					<th
						class="border-b border-slate-500 bg-zinc-700 p-4 text-sm font-normal leading-none text-slate-50">
						Email</th>
					<th
						class="border-b border-slate-500 bg-zinc-700 p-4 text-sm font-normal leading-none text-slate-50">
						Telefono</th>
					<th
						class="border-b border-slate-500 bg-zinc-700 p-4 text-sm font-normal leading-none text-slate-50">
						Acciones</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<Profesor> listarInactivos = (List<Profesor>) request.getAttribute("listarInactivos");
				if (listarInactivos != null) {
					for (Profesor profesor : listarInactivos) {
				%>
				<tr class="hover:bg-slate-50">
					<td class="border-b border-slate-200 p-4 text-sm text-slate-800">
						<%=profesor.getNombre() + " " + profesor.getApellido()%>
					</td>
					<td class="border-b border-slate-200 p-4 text-sm text-slate-800">
						<%=profesor.getDni()%>
					</td>
					<td class="border-b border-slate-200 p-4 text-sm text-slate-800">
						<%=profesor.getEmail()%>
					</td>
					<td class="border-b border-slate-200 p-4 text-sm text-slate-800">
						<%=profesor.getTelefono()%>
					</td>
					<td class="flex justify-end border-b border-slate-200 p-4">
						<!-- Clase compartida para estilos comunes -->
						<style>
.button-fixed-width {
	width: 180px; /* Ajusta el ancho según sea necesario */
}
</style> 
					<form method="post" action="ServletProfesores" >
							<button type="submit" class="button-fixed-width mt-0 h-11 rounded-md border border-transparent bg-gradient-to-tr from-orange-900 to-pink-500 px-4 py-2 gap-2 text-center text-l text-white shadow-md transition-all hover:shadow-lg hover:bg-slate-700 focus:bg-slate-700 focus:shadow-none active:bg-slate-700 active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none flex items-center justify-center mr-2"id="reactivar"
								name="reactivar" value=<%=profesor.getDni()%>>Reactivar</button>
						</form>
					</td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
		<a
			class="mt-2 w-64 h-11 rounded-md border border-transparent bg-gradient-to-tr from-sky-800 to-green-500 px-4 py-2 gap-2 text-center text-l text-white shadow-md transition-all hover:shadow-lg hover:bg-slate-700 focus:bg-slate-700 focus:shadow-none active:bg-slate-700 active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none flex items-center justify-center mr-auto"
			href="ServletProfesores?Param=list"> <i class="fa-solid fa-angles-left"></i>
			Profesores Activos
		</a>


	</div>

	</section> </main>


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
	<%
		boolean estado = false;
		if (request.getAttribute("estado") != null) {

			estado = (boolean) request.getAttribute("estado");
		}
	%>
	<%
		if (estado) {
	%>
	<script>
		alert("Profesor reactivado exitosamente");
	</script>
	<%
		} else if (request.getAttribute("estado") != null) {
	%>
	<script>
		alert("El DNI ya existe en la base de datos.");
	</script>
	<%
		}
				}
	%>

</body>
</html>