<%@ page import = "entidad.*" %>
<%@ page import = "java.util.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reportes - Gestión de Aulas</title>
     
    <script src="https://cdn.tailwindcss.com"></script>

    
    <link rel="stylesheet" href="/css/styles.css?v=rU3HU1QSfkxPJ0-lQ0ZvVUMHCvb42LZ_1gaDqGPHD-c">
    <link rel="stylesheet" href="/css/site.css?v=eosHg4ZhvtgkBfUZkaEBebp3gtfCrK_1R3e0nwILrnc">
    <link rel="stylesheet" href="/GestionDeAulas.styles.css?v=I1fNPcq-pkXH6WjNPv20YwCQmuK3uJFx2WuWZVc9ebo">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://cdn.datatables.net/1.13.5/js/jquery.dataTables.min.js"></script>
	<link rel="stylesheet" href="https://cdn.datatables.net/1.13.5/css/jquery.dataTables.min.css">
	<script type="text/javascript">
    $(document).ready(function () {
        var table = $('#tabla_reportes').DataTable({
            "paging": true,
            "pageLength": 5,
            "lengthMenu": [5, 10, 20, 50],
            "language": {
                "url": "//cdn.datatables.net/plug-ins/1.10.19/i18n/Spanish.json",
                "search": "Buscar:",
                "searchPlaceholder": "Escribe para buscar..."
            },
            "initComplete": function () {
                // Personaliza el campo de b squeda
                $('#tabla_reportes_filter input').attr('placeholder', 'Buscar en todas las columnas...');

                // Cambiar el alto del campo de b squeda
                $("#tabla_reportes_filter input").css({
                    'padding': '8px',
                    'border': '1px solid #ccc',
                    'border-radius': '4px',
                    'box-shadow': '0 2px 5px rgba(0, 0, 0, 0.1)', // Agrega una sombra al campo de b squeda
                    'margin': '10px' // Agrega un margen alrededor del campo de b squeda
                });
                
                // A adir funcionalidad para limpiar el campo
                $(document).on('click', '.custom-clear', function () {
                    $('#tabla_reportes_filter input').val('').trigger('keyup');
                });
            }
        });
    });
    </script>
</head>
<body>
	



		<%
	 
				ArrayList<Reserva> reservasReporte = (ArrayList<Reserva>) request.getAttribute("reservasReporte");
                Integer totalReservas = (Integer) request.getAttribute("totalReservas");
                Integer totalHorasReservadas = (Integer) request.getAttribute("totalHorasReservadas");
                ArrayList<Profesor> listaProfesores = (ArrayList<Profesor>) request.getAttribute("listaProfesores");
                
           %>

    <header class="m-4 p-4 rounded-lg shadow-lg bg-white relative">
    <div class="flex flex-row items-center justify-between w-full">
        <div class="flex items-center">
            <img
                src="http://localhost:8080/TPINT_GRUPO_2_LAB4/images/Logo_Curs.gif"
                alt="Logo del CURS" class="w-40 h-auto mr-1 mb-2">
        </div>
    </div>
    <!-- Contenedor para la sesión del usuario, posicionado de forma absoluta -->
    <div class="absolute top-0 right-0 mt-0 mr-4">
        <jsp:include page="usuarioSesion.jsp" />
    </div>

    <jsp:include page="barranavegacion.jsp" /> </header>

    <main class="w-full px-4 pt-2">
        <section class="flex flex-col items-center">
            <h2 class="w-full bg-gradient-to-tr from-zinc-800 to-zinc-500 p-4 text-center text-4xl font-bold text-gray-50">REPORTE DE RESERVAS POR FECHAS</h2>

            <!-- Formulario para generar reportes -->
            <form class="flex flex-col w-1/2 my-4" method="POST" action="ServletReservas?Param=reporte">             	
                <label for="date1" class="mb-2">Fecha de Inicio</label>
                <input id="date1" type="date" name="fechaInicio" required class="p-2 border rounded-md">
                <label for="date2" class="mt-4 mb-2">Fecha de Fin</label>
                <input id="date2" type="date" name="fechaFin" required class="p-2 border rounded-md">
                <div class="flex items-center justify-center h-full">
                <button type="submit" href = "ServletReservas?Param=reporte" class="mt-3 w-48 h-11 rounded-md border border-transparent bg-gradient-to-tr from-sky-800 to-sky-500 px-4 py-2 gap-2 text-center text-l text-white shadow-md transition-all hover:shadow-lg hover:bg-slate-700 focus:bg-slate-700 focus:shadow-none active:bg-slate-700 active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none flex items-center justify-center">
                    <i class="fa-solid fa-calendar"></i>
                    Generar Reporte
                </button>
                </div>
            </form>
            

            <!-- Tabla de reportes -->
            <% if (reservasReporte != null) { %>
				 <% 
				    String fechaInicio = request.getParameter("fechaInicio");
				    String fechaFin = request.getParameter("fechaFin");
				    if (fechaInicio != null && fechaFin != null) {
				%>
				    <div class="w-full my-4 p-4 bg-blue-100 border border-blue-400 rounded-md text-center">
				        <p class="text-blue-700 text-lg">
				            <strong>Fechas Seleccionadas:</strong> 
				            Desde <span class="font-bold"><%= fechaInicio %></span> hasta <span class="font-bold"><%= fechaFin %></span>.
				        </p>
				    </div>
				<% 
				    } 
				%>
                <div class="relative w-full overflow-scroll rounded-lg bg-white shadow-md">
                    <table id="tabla_reportes" class="w-full text-left">
                        <thead>
                            <tr class="bg-gray-800 text-white">
                                <th class="p-4">Fecha</th>
                                <th class="p-4">Hora de Inicio</th>
                                <th class="p-4">Tiempo Reservado</th>
                                <th class="p-4">Docente Responsable</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Reserva reserva : reservasReporte) { %>
                                <tr class="hover:bg-gray-100">
                                    <td class="p-4"><%= reserva.getFecha() %></td>
                                    <td class="p-4"><%= reserva.getHoraInicio() %></td>
                                    <td class="p-4">
                                        <%= (reserva.getHoraFinal().getTime() - reserva.getHoraInicio().getTime()) / (1000 * 60 * 60) %> hs
                                    </td>
                                    <td class="p-4"><%= reserva.getProfesor().getNombre() + " " + reserva.getProfesor().getApellido() %></td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
                <p class="mt-4"><strong>Total de Reservas: <%= totalReservas %></strong></p>
                <p><strong>Horas Reservadas: <%= totalHorasReservadas %> hs</strong></p>
            <% } else { %>
                <p class="mt-4 text-red-600">No se encontraron reservas o no se genero ningun reporte.</p>
            <% } %>
 			 <a class="mt-2 w-64 h-11 rounded-md border border-transparent bg-gradient-to-tr from-sky-800 to-green-500 px-4 py-2 gap-2 text-center text-l text-white shadow-md transition-all hover:shadow-lg hover:bg-slate-700 focus:bg-slate-700 focus:shadow-none active:bg-slate-700 active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none flex items-center justify-center mr-auto" href="MenuAdministrador.jsp">
			    <i class="fa-solid fa-angles-left"></i>
			    Menu Administrador
			  </a>
        </section>
    </main>
    		<script>
  document.addEventListener("DOMContentLoaded", function () {
     const date1 = document.getElementById("date1");
     const date2 = document.getElementById("date2");

        // Actualiza la fecha mínima del campo "Fecha de Fin"
      date1.addEventListener("change", function () {
      date2.value = ""; // Resetea el valor del campo "Fecha de Fin"
      date2.min = date1.value; // Establece la fecha mínima
      });

        // Evita que se envíe el formulario si "Fecha de Fin" no es válida
      const form = document.querySelector("form");
      form.addEventListener("submit", function (event) {
      if (date2.value && date2.value < date2.min) {
           event.preventDefault();
           alert("La fecha de fin no puede ser anterior a la fecha de inicio.");
       }
      });
  });
</script>

</body>
</html>