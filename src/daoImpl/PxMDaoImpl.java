package daoImpl;

import dao.PxMDao;
import entidad.Aula;
import entidad.Profesores_x_Materias;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidad.Materia;
import entidad.Profesor;


public class PxMDaoImpl implements PxMDao{
	
	private static final String insert = "INSERT INTO profesores_por_materias(Dni_Profesor, Id_Materia) VALUES (?, ?)";
	
	private static final String listar = "SELECT * FROM profesores_por_materias";

	
	@Override
	public boolean insert(Profesores_x_Materias pxm) {

	
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean insertExitoso = false;

	    try {
		        statement = conexion.prepareStatement(insert);
		        statement.setString(1, pxm.getProfesor().getDni());
		        statement.setInt(2, pxm.getMateria().getId());

		        if (statement.executeUpdate() > 0) {
		            conexion.commit();
		            insertExitoso = true;
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        try {
		            conexion.rollback();
		        } catch (SQLException e1) {
		            e1.printStackTrace();
		        }
		    }

		    return insertExitoso;
	}


	@Override
	public ArrayList<Profesores_x_Materias> listar() {
		
		PreparedStatement statement;
		ResultSet resultSet; 
		ArrayList<Profesores_x_Materias> pxms = new ArrayList<Profesores_x_Materias>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(listar);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				pxms.add(getPxMs(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return pxms;
	}

	
	private Profesores_x_Materias getPxMs(ResultSet resultSet) throws SQLException
	{
		int idMateria = resultSet.getInt("Id_Materia");
		String dniProfesor = resultSet.getString("DNI_Profesor");
		
		Materia materia = new Materia();
		materia.setId(idMateria);
		
		Profesor profesor = new Profesor();
		profesor.setDni(dniProfesor);
		
		return new Profesores_x_Materias(profesor,materia);
	}
	
	
	public boolean update(Profesores_x_Materias pxmNuevo, Profesores_x_Materias pxmActual) {
		 boolean resultado = false;
		 String procedimiento = "{CALL sp_modificarprofesorespormaterias(?, ?, ?, ?,?)}"; 
		 Connection conexion = Conexion.getConexion().getSQLConexion();

		 try (CallableStatement cst = conexion.prepareCall(procedimiento)) {
		       
			 cst.setString(1, "UPDATE");                             
		     cst.setString(2, pxmActual.getProfesor().getDni());                   // DNI actual del profesor
		     cst.setInt(3, pxmActual.getMateria().getId());                        // ID actual de la materia
		     cst.setString(4, pxmActual.getProfesor().getDni());              // Nuevo DNI del profesor
		     cst.setInt(5, pxmActual.getMateria().getId());
		      
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
