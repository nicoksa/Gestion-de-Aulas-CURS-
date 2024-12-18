<%@ page import = "entidad.Materia" %>
<%@ page import = "java.util.*" %>
<%@ page import = "entidad.Profesores_x_Materias" %>
<%@ page import = "entidad.Carrera" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en"><head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login de Usuario - Gestion De Aulas</title>
    <script src="https://cdn.tailwindcss.com"></script>
    
    <link rel="stylesheet" href="/css/styles.css?v=rU3HU1QSfkxPJ0-lQ0ZvVUMHCvb42LZ_1gaDqGPHD-c">
    <link rel="stylesheet" href="/css/site.css?v=eosHg4ZhvtgkBfUZkaEBebp3gtfCrK_1R3e0nwILrnc">
    <link rel="stylesheet" href="/GestionDeAulas.styles.css?v=I1fNPcq-pkXH6WjNPv20YwCQmuK3uJFx2WuWZVc9ebo">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">
    
            <!-- DataTables CSS -->
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    
    <!-- JQuery y DataTables JS -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    
    <script type="text/javascript">
    $(document).ready(function () {
        var table = $('#materias_table').DataTable({
            "paging": true,
            "pageLength": 5,
            "lengthMenu": [5, 10, 20, 50],
            "language": {
                "url": "//cdn.datatables.net/plug-ins/1.10.19/i18n/Spanish.json",
                "search": "Buscar:",
                "searchPlaceholder": "Escribe para buscar..."
            },
            "initComplete": function () {
                // Personaliza el campo de b�squeda
                $('#materias_table_filter input').attr('placeholder', 'Buscar en todas las columnas...');

                // Cambiar el alto del campo de b�squeda
                $("#materias_table_filter input").css({
                    'padding': '8px',
                    'border': '1px solid #ccc',
                    'border-radius': '4px',
                    'box-shadow': '0 2px 5px rgba(0, 0, 0, 0.1)', // Agrega una sombra al campo de b�squeda
                    'margin': '10px' // Agrega un margen alrededor del campo de b�squeda
                });
                
                // Añadir funcionalidad para limpiar el campo
                $(document).on('click', '.custom-clear', function () {
                    $('#materias_table_filter input').val('').trigger('keyup');
                });
            }
        });
    });
    </script>

</head>
<body>
	

		<%
		List<Materia> listaMaterias = new ArrayList<Materia>();
		if (request.getAttribute("listaMaterias") != null) {
			listaMaterias = (List<Materia>) request.getAttribute("listaMaterias");
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
	</header>
    

    
        <main b-dk557gbzy5="" role="main" class="w-full px-4 pt-2">
        
        	<section class="flex min-h-dvh w-full flex-col items-center">
			    <h2 class="w-full bg-gradient-to-tr from-zinc-800 to-zinc-500 p-4 text-center text-4xl font-bold text-gray-50">LISTADO DE MATERIAS</h2>
			    
			    
			     <%
				    String estadoEliminar = request.getParameter("estadoEliminar");
				    if ("exito".equals(estadoEliminar)) {
				%>
				    <div style="background-color: #d4edda; color: #155724; padding: 10px; border: 1px solid #c3e6cb; border-radius: 5px; margin-bottom: 15px;">
				        <strong> Materia eliminada correctamente</strong>
				    </div>
				<%
				    } else if ("error".equals(estadoEliminar)) {
				%>
				    <div style="background-color: #f8d7da; color: #721c24; padding: 10px; border: 1px solid #f5c6cb; border-radius: 5px; margin-bottom: 15px;">
				        <strong>Error: No se pudo eliminar la materia.</strong> 
				    </div>
				<%
				    }
				%>
				
				
					<%
				   	String estadoEditar = request.getParameter("estadoEditar");
				    if ("exito".equals(estadoEditar)) {
				%>
				    <div style="background-color: #d4edda; color: #155724; padding: 10px; border: 1px solid #c3e6cb; border-radius: 5px; margin-bottom: 15px;">
				        <strong> Materia editada correctamente</strong>
				    </div>
				<%
				    } else if ("error".equals(estadoEditar)) {
				%>
				    <div style="background-color: #f8d7da; color: #721c24; padding: 10px; border: 1px solid #f5c6cb; border-radius: 5px; margin-bottom: 15px;">
				        <strong>Error: No se pudo editar la materia</strong> 
				    </div>
				<%
				    }
				%>
				<form method="post" action="ServletMaterias">
				<div class="mb-3 mt-4">
				<label for="lblcarrera" class="form-label"><strong>Carrera</strong></label> <select
					class="form-control" id="ddlcarreran" name="ddlcarrera" required>
					<option value="">Seleccione una Carrera</option> 
					
				 	
					<%
						List<Carrera> carreras = (List<Carrera>) request.getAttribute("listaCarreras");
						if (carreras != null) {
							for (Carrera carrera : carreras) {
					%>
					<option value=<%=carrera.getId() %>><%=carrera.getNombre() %></option>
					<%
						}
							
						}
					%>
					
					
					
				</select>
					
				<button type="submit" class="mt-2 w-25 h-7 rounded-md border border-transparent bg-gradient-to-tr from-amber-800 to-amber-500 px-4 py-2 gap-2 text-center text-l text-white shadow-md transition-all hover:shadow-lg hover:bg-slate-700 focus:bg-slate-700 focus:shadow-none active:bg-slate-700 active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none flex items-center justify-center ml-auto" id="btnFiltrar" name="btnFiltrar">
				<i class="fa-solid fa-filter"></i>
				Filtrar</button>
			</div>
				</form>
			    <a class="mt-2 w-64 h-11 rounded-md border border-transparent bg-gradient-to-tr from-sky-800 to-green-500 px-4 py-2 gap-2 text-center text-l text-white shadow-md transition-all hover:shadow-lg hover:bg-slate-700 focus:bg-slate-700 focus:shadow-none active:bg-slate-700 active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none flex items-center justify-center ml-auto" href="ServletMaterias?Param=datos">
			        <i class="fa-solid fa-book-open" aria-hidden="true"></i>
			        Crear Materia
			    </a>
			    <div class="relative mt-4 flex h-full w-full flex-col overflow-scroll rounded-lg bg-white bg-clip-border shadow-md">
			
				<table id="materias_table" class="display">
				    <thead>
				        <tr>
				            <th class="border-b border-slate-500 bg-zinc-700 p-4 text-sm font-normal leading-none text-slate-50" style="width: 20%;">
				                Materia
				            </th>
				            <th class="border-b border-slate-500 bg-zinc-700 p-4 text-sm font-normal leading-none text-slate-50" style="width: 20%;">
				                Carrera
				            </th>
				            <th class="border-b border-slate-500 bg-zinc-700 p-4 text-sm font-normal leading-none text-slate-50" style="width: 20%;">
				                Institucion
				            </th>
				            <th class="border-b border-slate-500 bg-zinc-700 p-4 text-sm font-normal leading-none text-slate-50" style="width: 20%;">
				                Docente
				            </th>
				            <th class="border-b border-slate-500 bg-zinc-700 p-4 text-sm font-normal leading-none text-slate-50" style="width: 20%;">
				                Acciones
				            </th>
				        </tr>
				    </thead>
				    <tbody>
				        <% for (Materia materia : listaMaterias) { %>
				            <tr class="hover:bg-slate-50">
				                <td class="border-b border-slate-200 p-4 text-sm text-slate-800">
				                    <%= materia.getNombre() %>
				                </td>
				                <td class="border-b border-slate-200 p-4 text-sm text-slate-800">
				                    <%= materia.getCarrera().getNombre() %>
				                </td>
				                <td class="border-b border-slate-200 p-4 text-sm text-slate-800">
				                    <%= materia.getInstitucion().getNombre() %>
				                </td>
				                <td class="border-b border-slate-200 p-4 text-sm text-slate-800">
				                    <% 
				                    for (Profesores_x_Materias pxm : listaProf_x_Mat) {
				                        if (materia.getId() == pxm.getMateria().getId()) {
				                            out.print(pxm.getProfesor().getNombre() + " " + pxm.getProfesor().getApellido());
				                        }
				                    }
				                    %>
				                </td>
				                <td class="flex justify-end border-b border-slate-200 p-4">
				                    <a class="mt-0 w-36 h-11 rounded-md border border-transparent bg-gradient-to-tr from-orange-900 to-pink-500 px-4 py-2 gap-2 text-center text-l text-white shadow-md transition-all hover:shadow-lg hover:bg-slate-700 focus:bg-slate-700 focus:shadow-none active:bg-slate-700 active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none flex items-center justify-center mr-2"
				                       href="ServletMaterias?eliminar=<%= materia.getId() %>" onclick="return confirmarBorrar();">
				                        <i class="fa fa-trash" aria-hidden="true"></i> Dar de Baja
				                    </a>
				                     <a class="mt-0 w-36 h-11 rounded-md border border-transparent bg-gradient-to-tr from-sky-800 to-sky-500 px-4 py-2 gap-2 text-center text-l text-white shadow-md transition-all hover:shadow-lg hover:bg-slate-700 focus:bg-slate-700 focus:shadow-none active:bg-slate-700 active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none flex items-center justify-center"
				                       	href="ServletMaterias?idParaEditar=<%= materia.getId() %>">
				                        <i class="fa fa-pencil" aria-hidden="true"></i> Editar
				                    </a>

				                </td>
				            </tr>
				        <% } %>
				    </tbody>
				</table>
			        			   <a class="w-64 h-11 rounded-md border border-transparent bg-gradient-to-tr from-sky-800 to-green-500 px-4 py-2 gap-2 text-center text-l text-white shadow-md transition-all hover:shadow-lg hover:bg-slate-700 focus:bg-slate-700 focus:shadow-none active:bg-slate-700 active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none flex items-center justify-center" href="MenuAdministrador.jsp">
			                		<i class="fa-solid fa-angles-left"></i>
        							Menu Administrador
			            			</a>
			    </div>
			
			</section>

        </main>


    <script src="/lib/jquery/dist/jquery.min.js"></script>

    <script src="/js/site.js?v=hRQyftXiu1lLX2P9Ly9xa4gHJgLeR1uGN5qegUobtGo"></script>
    
          <script>
    function confirmarBorrar() {
        return confirm("¿Estás seguro de que quieres dar de baja esta materia?");
    }
	</script>
    

<!-- Visual Studio Browser Link -->
<script type="text/javascript" src="/_vs/browserLink" async="async" id="__browserLink_initializationData" data-requestid="2f8998a884764702a38b28b8792bed9d" data-requestmappingfromserver="false" data-connecturl="http://localhost:54306/d44215c8dbef47c699c036401f907765/browserLink"></script>
<!-- End Browser Link -->
<script src="/_framework/aspnetcore-browser-refresh.js"></script>

</body></html>