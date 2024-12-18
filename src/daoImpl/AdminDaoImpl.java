package daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.AdminDao;
import entidad.Administrador;

import daoImpl.Conexion;


public class AdminDaoImpl implements AdminDao{
	
	private String query = "select * from administradores where DNI_Administrador=? and Contraseña =?";
	private static final String traerAdmin= "SELECT * FROM administradores WHERE DNI_Administrador=?";
	
	
	
	@Override
	public boolean existeadmin(String Admin , String pass) {
		Administrador admin = new Administrador();
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(query);
			statement.setString(1,Admin);
			statement.setString(2,pass);
			resultSet  = statement.executeQuery();
			if (resultSet.next())
				{
					return true;
				}
			return false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}



	@Override
	public Administrador traerAdmin(String dni) {
		PreparedStatement statement;
		ResultSet resultSet; 
		Administrador admin = new Administrador();
		admin.setDni(dni);
		Conexion conexion = Conexion.getConexion();
		
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(traerAdmin);
			statement.setString(1, dni);
			resultSet = statement.executeQuery();
			 if (resultSet.next()) { 
		            admin = getAdmin(resultSet);
		        }
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return admin;
	}
	
	
		
	private Administrador getAdmin(ResultSet resultSet) throws SQLException
	{
		String dni = resultSet.getString("DNI_Administrador");
		String nombre = resultSet.getString("Nombre");
		String apellido = resultSet.getString("Apellido");
		String contrasenia = resultSet.getString("Contraseña");
		
		
		return new Administrador(dni,nombre,apellido,contrasenia);
	}



	@Override
	public boolean update(Administrador admin) {
		 boolean resultado = false;
		 String procedimiento = "{CALL sp_modificaadministradores(?, ?, ?, ?, ?)}"; 
		 Connection conexion = Conexion.getConexion().getSQLConexion();

		    try (CallableStatement cst = conexion.prepareCall(procedimiento)) {
		      
		        cst.setString(1, admin.getDni());
		        cst.setString(2, admin.getNombre());
		        cst.setString(3, admin.getApellido());
		        cst.setString(4, admin.getContrasenia());
		        cst.setBoolean(5, true);
	      
		        resultado = cst.executeUpdate() > 0;
		        conexion.commit();
		    } catch (SQLException e) {
		        e.printStackTrace();
		        try {
		            conexion.rollback();
		        } catch (SQLException rollbackEx) {
		            rollbackEx.printStackTrace();
		        }
		    }
		    return resultado;
	}

}
