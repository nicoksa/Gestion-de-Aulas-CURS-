<%@ page import = "entidad.Administrador" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en"><head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> Gestion De Aulas</title>
    <script src="https://cdn.tailwindcss.com"></script>
    
    <link rel="stylesheet" href="/css/styles.css?v=rU3HU1QSfkxPJ0-lQ0ZvVUMHCvb42LZ_1gaDqGPHD-c">
    <link rel="stylesheet" href="/css/site.css?v=eosHg4ZhvtgkBfUZkaEBebp3gtfCrK_1R3e0nwILrnc">
    <link rel="stylesheet" href="/GestionDeAulas.styles.css?v=I1fNPcq-pkXH6WjNPv20YwCQmuK3uJFx2WuWZVc9ebo">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">

</head>
<body>

	<%
                // Recuperar los datos de sesión
        HttpSession misession = request.getSession(false);
    	String nombreUsuario = (String) misession.getAttribute("nombreUsuario");
    	String apellidoUsuario = (String) misession.getAttribute("apellidoUsuario");
    	String dniUsuario = (String) misession.getAttribute("dniUsuario");
    %>
	<jsp:include page="barranavegacion.jsp" />


    <header b-dk557gbzy5="" class="m-1 rounded-md p-2 shadow-md shadow-gray-500">
        <div b-dk557gbzy5="" class="m-4 flex flex-row items-center justify-between align-middle">
            <a class="my-auto flex flex-col items-center justify-center" href="/">
                <h1 b-dk557gbzy5="" class="h-fit p-1 text-6xl font-bold text-green-600">CURS</h1>
                <h2 b-dk557gbzy5="" class="h-fit p-1 text-4xl font-bold text-red-600">Gestion de Aulas</h2>
            </a>
			<jsp:include page="usuarioSesion.jsp" />
			
			<jsp:include page="errorMessage.jsp" />
			
        </div>
    </header>
    
        <main b-dk557gbzy5="" role="main" class="w-full px-4 pt-2">
			<section class="flex min-h-dvh w-full flex-col items-center">
			    <h2 class="w-full bg-gray-900 p-4 text-center text-6xl font-bold text-gray-50">Editar Administrador</h2>
			    <form class="flex h-full w-2/3 flex-col items-center justify-center rounded-md py-4" method="post" action="ServletAdmin?Param=list">
			    
			       
			        
			        <div class="flex w-2/3 flex-col self-center">
			            <label for="FirstName">Nombre</label>
			            <input class="border-2 mx-1 rounded-md border-gray-900 p-1" type="text" data-val="true" data-val-required="El campo nombre es obligatorio" id="FirstName" name="FirstName" value="<%=nombreUsuario %>" required>
			            <span class="text-red-600 field-validation-valid" data-valmsg-for="FirstName" data-valmsg-replace="true"></span>
			        </div>
			        <div class="flex w-2/3 flex-col self-center">
			            <label for="LastName">Apellido</label>
			            <input class="border-2 mx-1 rounded-md border-gray-900 p-1" type="text" data-val="true" data-val-required="El campo apellido es obligatorio" id="LastName" name="LastName" value="<%=apellidoUsuario %>" required>
			            <span class="text-red-600 field-validation-valid" data-valmsg-for="LastName" data-valmsg-replace="true"></span>
			        </div>
			        <div class="flex w-2/3 flex-col self-center">
			            <label for="Dni">DNI</label>
			            <input class="border-2 mx-1 rounded-md border-gray-900 p-1" type="text" data-val="true" data-val-required="El campo DNI es obligatorio" id="Dni" name="Dni" value="<%= dniUsuario%>" disabled>
			            <span class="text-red-600 field-validation-valid" data-valmsg-for="Dni" data-valmsg-replace="true"></span>
			        </div>

	
			        </div>
			        <div class="flex w-2/3 justify-between">
			            <button type="submit" name="dniAeditar" value="<%= dniUsuario %>" class="mt-4 w-40 rounded-md border border-transparent bg-gradient-to-tr from-blue-800 to-blue-700 px-4 py-2 text-center text-sm text-white shadow-md transition-all hover:shadow-lg hover:bg-slate-700 focus:bg-slate-700 focus:shadow-none active:bg-slate-700 active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none">
			                Editar Usuario
			            </button>
			            <a class="mt-4 w-40 rounded-md border border-transparent bg-gradient-to-tr from-green-800 to-green-700 px-4 py-2 text-center text-sm text-white shadow-md transition-all hover:shadow-lg hover:bg-slate-700 focus:bg-slate-700 focus:shadow-none active:bg-slate-700 active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none" href="MenuAdministrador.jsp">
			                Volver
			            </a>
			
			        </div>
			
			
			    <input name="__RequestVerificationToken" type="hidden" value="CfDJ8PsupENnyaxJsSE9YBIZHT4mk4117J9VfFV50-pyMYyoW3Kt3hBD9eYg-u75Bxrau-S4tnDqzYo_4N9Rjm5fysog9Wcx-chC4QHXwCUKqfgziZpRwNQFa4byaQ-vd-kkjzhNw2DFklIIxpjZx65jbUc"></form>

			</section>

        </main>
    

    <script src="/lib/jquery/dist/jquery.min.js"></script>

    <script src="/js/site.js?v=hRQyftXiu1lLX2P9Ly9xa4gHJgLeR1uGN5qegUobtGo"></script>
    

<!-- Visual Studio Browser Link -->
<script type="text/javascript" src="/_vs/browserLink" async="async" id="__browserLink_initializationData" data-requestid="2f8998a884764702a38b28b8792bed9d" data-requestmappingfromserver="false" data-connecturl="http://localhost:54306/d44215c8dbef47c699c036401f907765/browserLink"></script>
<!-- End Browser Link -->
<script src="/_framework/aspnetcore-browser-refresh.js"></script>

</body></html>