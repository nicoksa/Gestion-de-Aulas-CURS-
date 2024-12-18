<%@ page language="java" import="javax.servlet.http.HttpSession" %>
<div class="flex justify-end items-center space-x-4 p-4">
    <% 
        HttpSession misession = request.getSession(false); // No crear una sesión nueva si no existe
        if (misession != null) {
            String nombreUsuario = (String) misession.getAttribute("nombreUsuario");
            String apellidoUsuario = (String) misession.getAttribute("apellidoUsuario");
            if (nombreUsuario != null && apellidoUsuario != null) {
    %>
                <span class="text-white font-medium">
                    <%= nombreUsuario %> <%= apellidoUsuario %>
              </span>
               <!--     <a href="ServletLogout" class="text-blue-600 hover:underline">Cerrar sesi�n</a> -->
    <% 
            }
        } 
    %>
</div>