<%@ page import = "entidad.Administrador" %>
<%@ page import = "negocioImpl.AdministradorNegocioImp" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en"><head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion De Aulas - Login de Usuario</title>
    <script src="https://cdn.tailwindcss.com"></script>
    
    <link rel="stylesheet" href="/css/styles.css?v=rU3HU1QSfkxPJ0-lQ0ZvVUMHCvb42LZ_1gaDqGPHD-c">
    <link rel="stylesheet" href="/css/site.css?v=eosHg4ZhvtgkBfUZkaEBebp3gtfCrK_1R3e0nwILrnc">
    <link rel="stylesheet" href="/GestionDeAulas.styles.css?v=I1fNPcq-pkXH6WjNPv20YwCQmuK3uJFx2WuWZVc9ebo">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">

</head>
<body>

    <header class="m-4 p-4 rounded-lg shadow-lg bg-white relative">
    
    <div class="flex flex-row items-center justify-between w-full">
			<div class="flex items-center">
				<img
					src="http://localhost:8081/TPINT_GRUPO_2_LAB4/images/Logo_Curs.gif"
					alt="Logo del CURS" class="w-48 h-auto mr-1">
			</div>
			<div class="flex flex-col items-end" style="margin-top: 120px;">
    <h1 class="text-2xl font-bold text-gray-600" id="fechaHora"></h1>
</div>

    </header>

        <main b-dk557gbzy5="" role="main" class="w-full px-4 pt-2">
        	<section class="flex min-h-dvh w-full flex-col items-center">
			    <h2 class="w-full bg-gradient-to-tr from-zinc-800 to-zinc-500 p-4 text-center text-4xl font-bold text-gray-50">GESTION DE AULAS - LOGIN</h2>
			    <form class="flex h-full w-2/3 flex-col items-center justify-center rounded-md py-4" method="POST" action="ServletAdmin">
			        
			        <div class="flex w-2/3 flex-col self-center">
			            <label for="UserName">Numero de Documento</label>
			            <input class="border-2 mx-1 rounded-md border-gray-900 p-1" type="text" data-val="true" data-val-email="The Nombre de Usuario field is not a valid e-mail address." data-val-required="The Nombre de Usuario field is required." id="UserName" name="UserName" value="">
			            <span class="text-red-600 field-validation-valid" data-valmsg-for="UserNama" data-valmsg-replace="true"></span>
			        </div>
			        <div class="flex w-2/3 flex-col self-center">
			            <label for="Password">Password </label>
			            <input class="border-2 mx-1 rounded-md border-gray-900 p-1" type="password" data-val="true" data-val-required="The Contrase�a field is required." id="Password" name="Password" value="">
			            <span class="text-red-600 field-validation-valid" data-valmsg-for="Password" data-valmsg-replace="true"></span>
			        </div>
			        <div class="mt-2 flex w-2/3 justify-between" style="width: 201px; ">
			           <div class="flex place-items-center align-middle"><button type="submit" name = "btningresar" class="mt-4 w-64 h-12 rounded-md border border-transparent bg-gradient-to-tr from-blue-800 to-green-500 shadow-lg px-4 py-2 gap-2 text-center text-xl text-white shadow-md transition-all hover:shadow-lg hover:bg-slate-700 focus:bg-slate-700 focus:shadow-none active:bg-slate-700 active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none flex items-center justify-center">
			                <i class="fa fa-sign-in" aria-hidden="true" ></i>
			
			                Ingresar
			           
			            </button>


			        
			            </div>
			            
			        </div>
			        
			    <input name="__RequestVerificationToken" type="hidden" value="CfDJ8PsupENnyaxJsSE9YBIZHT5KQT8kJrX3TIObg4_jQuDxNSek3DnldyWJLIrLG6t195oWUcNgFUdlJM0SZXn_Q1TwfZ73t0P6Yqv4kATn4hOnVSIMsoGxQHvpGFzKofgz3b3V-Sd-8ohH66hzI9rU2k0"><input name="Persist" type="hidden" value="false"></form>
			</section>
        	

        </main>
    

    <script src="/lib/jquery/dist/jquery.min.js"></script>

    <script src="/js/site.js?v=hRQyftXiu1lLX2P9Ly9xa4gHJgLeR1uGN5qegUobtGo"></script>
    

<!-- Visual Studio Browser Link -->
<script type="text/javascript" src="/_vs/browserLink" async="async" id="__browserLink_initializationData" data-requestid="2f8998a884764702a38b28b8792bed9d" data-requestmappingfromserver="false" data-connecturl="http://localhost:54306/d44215c8dbef47c699c036401f907765/browserLink"></script>
<!-- End Browser Link -->
<script src="/_framework/aspnetcore-browser-refresh.js"></script>

   <%
        int usuarioInexistente = 1;
        if (request.getAttribute("USUINEX") != null) {
            usuarioInexistente = Integer.parseInt(request.getAttribute("USUINEX").toString());
        }
        if (usuarioInexistente == 0) {
    %>
    <script>
        alert("Usuario Inexistente o Password Incorrecto");
    </script>
    <%
        }
    %>
    <script>
    function mostrarFechaHora() {
        const fecha = new Date();
        const opciones = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit', second: '2-digit' };
        const fechaHora = fecha.toLocaleString('es-ES', opciones);
        
        document.getElementById('fechaHora').textContent = fechaHora;
    }

    // Llamamos a la funci�n para mostrar la fecha y hora al cargar la p�gina
    mostrarFechaHora();
    
    // Actualizar la hora cada segundo (para mostrar la hora en tiempo real)
    setInterval(mostrarFechaHora, 1000);
	</script>

</body></html>