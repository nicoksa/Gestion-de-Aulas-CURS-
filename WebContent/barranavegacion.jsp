<nav class="bg-gray-800">
  <div class="w-full px-2 sm:px-6 lg:px-8"> 
    <div class="relative flex h-12 items-center justify-between">
      <!-- Enlaces de navegación -->
      <div class="flex flex-1 items-center sm:items-stretch sm:justify-start">
        <div class="hidden sm:ml-6 sm:block">
          <div class="flex space-x-4">
            <a href="ServletAulas?Param=list" class="rounded-md px-3 py-2 text-sm font-medium text-gray-300 hover:bg-gray-700 hover:text-white">Aulas</a>
            <a href="ServletInstituciones?Param=list" class="rounded-md px-3 py-2 text-sm font-medium text-gray-300 hover:bg-gray-700 hover:text-white">Instituciones</a>
            <a href="ServletCarreras?Param=list" class="rounded-md px-3 py-2 text-sm font-medium text-gray-300 hover:bg-gray-700 hover:text-white">Carreras</a>
            <a href="ServletProfesores?Param=list" class="rounded-md px-3 py-2 text-sm font-medium text-gray-300 hover:bg-gray-700 hover:text-white">Profesores</a>
            <a href="ServletMaterias?Param=list" class="rounded-md px-3 py-2 text-sm font-medium text-gray-300 hover:bg-gray-700 hover:text-white">Materias</a>
            <a href="ServletReservas?Param=list" class="rounded-md bg-gray-900 px-3 py-2 text-sm font-medium text-white" aria-current="page">Reservas</a>
            <a href="Reportes.jsp" class="rounded-md px-3 py-2 text-sm font-medium text-gray-300 hover:bg-gray-700 hover:text-white">Reportes</a>
          </div>
        </div>
      </div>

      <!-- Logo y Menú Administrador -->
      <div class="flex items-center space-x-4">
        <!-- Logo -->
        <img class="h-8 w-auto rounded-full ml-16" src="http://localhost:8081/TPINT_GRUPO_2_LAB4/images/Loguito.png" alt="CURS Saladillo">

        <!-- Mostrar el nombre y apellido del usuario -->
        <jsp:include page="usuarioSesion.jsp" />

        <!-- Menú Administrador -->
        <div class="relative">
          <button class="text-white font-medium hover:underline" id="menuButton">
            Menú de Gestión
          </button>

          <!-- Menú desplegable -->
          <div id="menuOptions" class="hidden absolute left-1/2 transform -translate-x-[55%] mt-2 w-60 bg-white text-gray-800 rounded-md shadow-lg z-10">
            <a href="MenuAdministrador.jsp" class="block px-4 py-2 hover:bg-gray-200" id="inicioLink">Ir a Inicio</a>
           
            <a href="editarAdministrador.jsp" class="block px-4 py-2 hover:bg-gray-200" id="editarAdministrador">Editar Administrador</a>
            
            <a href="ServletLogout" class="block px-4 py-2 hover:bg-gray-200">Cerrar sesión</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</nav>
<script>
  // Mostrar/Ocultar menú desplegable
  const menuButton = document.getElementById('menuButton');
  const menuOptions = document.getElementById('menuOptions');
  const inicioLink = document.getElementById('inicioLink'); // Referencia al enlace "Ir a Inicio"
  
  const editarAdministrador = document.getElementById('editarAdministrador');
 

  // Ocultar la opción "Ir a Inicio" si estamos en MenuAdministrador.jsp
  if (window.location.pathname.includes("MenuAdministrador.jsp")) {
    inicioLink.style.display = "none"; // Ocultar el enlace "Ir a Inicio"
    // Mostrar las opciones de administrador solo en MenuAdministrador.jsp
   
    editarAdministrador.style.display = "block";
    
  } else {
    // Ocultar las opciones de administrador en otras páginas
    
    editarAdministrador.style.display = "none";
    
  }

  menuButton.addEventListener('click', () => {
    menuOptions.classList.toggle('hidden');
  });

  // Cerrar el menú si se hace clic fuera
  document.addEventListener('click', (event) => {
    if (!menuButton.contains(event.target) && !menuOptions.contains(event.target)) {
      menuOptions.classList.add('hidden');
    }
  });
</script>