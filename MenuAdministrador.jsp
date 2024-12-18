<%@ page import="entidad.Administrador"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Gestion de Aulas - Menu Administrador</title>
<script src="https://cdn.tailwindcss.com"></script>
<link rel="stylesheet" href="/css/styles.css">
</head>

<body class="bg-gradient-to-br from-gray-100 via-white to-gray-50">

<!-- Contenido principal -->

	<header class="m-4 p-4 rounded-lg shadow-lg bg-white relative">
		<div class="flex flex-row items-center justify-between w-full">
			<div class="flex items-center">
				<img
					src="http://localhost:8081/TPINT_GRUPO_2_LAB4/images/Logo_Curs.gif"
					alt="Logo del CURS" class="w-48 h-auto mr-1">
			</div>

			<div class="flex flex-col items-end" style="margin-top: 70px;">
				<h1 class="text-4xl font-bold text-green-600">Menú de Administrador</h1>
			</div>
		</div>

		<!-- Contenedor para la sesión del usuario, posicionado de forma absoluta -->
		<div class="absolute top-0 right-0 mt-0 mr-4">
			<jsp:include page="usuarioSesion.jsp" />
		</div>
	<!-- Barra de navegación -->
	<jsp:include page="barranavegacion.jsp" />
	</header>


    <!-- Mensajes de estado -->
    <div class="container mx-auto mt-6 px-4">
        <%
            String estadoEditar = request.getParameter("estadoEditar");
            if ("exito".equals(estadoEditar)) {
        %>
        <div class="p-4 mb-4 text-sm text-green-800 bg-green-100 rounded-lg border border-green-300">
            <strong>¡Éxito!</strong> El usuario se editó correctamente.
        </div>
        <%
            } else if ("error".equals(estadoEditar)) {
        %>
        <div class="p-4 mb-4 text-sm text-red-800 bg-red-100 rounded-lg border border-red-300">
            <strong>¡Error!</strong> No se pudo editar el usuario.
        </div>
        <%
            }
        %>
    </div>

    <!-- Contenido principal -->
   <main class="w-full px-4">
	<section class="flex flex-col items-center space-y-4">
		<!-- Opciones del menú -->
		<div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
				<a href="ServletAulas?Param=list"
				class="mt-0 w-64 h-16 rounded-md border border-transparent bg-gradient-to-tr from-blue-800 to-emerald-500 px-4 py-2 text-center text-xl text-white shadow-md transition-all hover:shadow-lg hover:bg-slate-700 focus:bg-slate-700 focus:shadow-none active:bg-slate-700 active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none flex items-center justify-center">
				Aulas </a> 
				<a href="ServletInstituciones?Param=list"
				class="mt-0 w-64 h-16 rounded-md border border-transparent bg-gradient-to-tr from-blue-800 to-emerald-500 px-4 py-2 text-center text-xl text-white shadow-md transition-all hover:shadow-lg hover:bg-slate-700 focus:bg-slate-700 focus:shadow-none active:bg-slate-700 active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none flex items-center justify-center">
				Instituciones </a> 
				<a href="ServletCarreras?Param=list"
				class="mt-0 w-64 h-16 rounded-md border border-transparent bg-gradient-to-tr from-blue-800 to-emerald-500 px-4 py-2 text-center text-xl text-white shadow-md transition-all hover:shadow-lg hover:bg-slate-700 focus:bg-slate-700 focus:shadow-none active:bg-slate-700 active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none flex items-center justify-center">
				Carreras </a> 
				<a href="ServletProfesores?Param=list"
				class="mt-0 w-64 h-16 rounded-md border border-transparent bg-gradient-to-tr from-blue-800 to-emerald-500 px-4 py-2 text-center text-xl text-white shadow-md transition-all hover:shadow-lg hover:bg-slate-700 focus:bg-slate-700 focus:shadow-none active:bg-slate-700 active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none flex items-center justify-center">
				Profesores </a> 
				<a href="ServletMaterias?Param=list"
				class="mt-0 w-64 h-16 rounded-md border border-transparent bg-gradient-to-tr from-blue-800 to-emerald-500 px-4 py-2 text-center text-xl text-white shadow-md transition-all hover:shadow-lg hover:bg-slate-700 focus:bg-slate-700 focus:shadow-none active:bg-slate-700 active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none flex items-center justify-center">
				Materias </a>
				<a href="ServletReservas?Param=list"
				class="mt-0 w-64 h-16 rounded-md border border-transparent bg-gradient-to-tr from-blue-800 to-green-500 px-4 py-2 text-center text-xl text-white shadow-md transition-all hover:shadow-lg hover:bg-slate-700 focus:bg-slate-700 focus:shadow-none active:bg-slate-700 active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none flex items-center justify-center">
				Reservas </a> 
                <a href="Reportes.jsp" 
                class="mt-0 w-64 h-16 rounded-md border border-transparent bg-gradient-to-tr from-blue-800 to-emerald-500 px-4 py-2 text-center text-xl text-white shadow-md transition-all hover:shadow-lg hover:bg-slate-700 focus:bg-slate-700 focus:shadow-none active:bg-slate-700 active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none flex items-center justify-center ">
                Reportes
                </a>
                </div>
        </section>
    </main>

    <!-- Pie de página -->
    <footer class="mt-8 p-4 text-center bg-gray-200">
		<p class="text-gray-600">&copy; 2024 CURS - Todos los derechos
			reservados.</p>
	</footer>
</body>
</html>