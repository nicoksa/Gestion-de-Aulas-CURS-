package daoImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.InstitucionDao;
import entidad.Aula;
import entidad.Institucion;

public class InstitucionDaoImpl implements InstitucionDao{

	private static final String listar = "SELECT * FROM instituciones WHERE Estado = 1";
	private static final String traerInstitucion = "SELECT * FROM instituciones WHERE Id_Institucion = ?";
	private static final String insert = "INSERT INTO instituciones(Institucion) VALUES (?)";
	private static final String delete =  "UPDATE instituciones SET Estado = 0 WHERE Id_Institucion = ?"; 
	private static final String update = "UPDATE instituciones SET  Institucion = ? WHERE Id_Institucion = ?";
	
	
	@Override
	public boolean insert(Institucion institucion) {
		
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean insertExitoso = false;
		
		try
		{
			statement = conexion.prepareStatement(insert);			
			statement.setString(1, institucion.getNombre());
			
			
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				insertExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
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
	public ArrayList<Institucion> listar() {
		PreparedStatement statement;
		ResultSet resultSet; 
		ArrayList<Institucion> instituciones = new ArrayList<Institucion>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(listar);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				instituciones.add(getInstitucion(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return instituciones;
	}
	
	
	private Institucion getInstitucion(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("Id_Institucion");
		String nombre = resultSet.getString("Institucion");
		
		return new Institucion(id,nombre);
	}


	@Override
	public Institucion traerInstitucion(int id) {
		
		PreparedStatement statement;
		ResultSet resultSet; 
		Institucion institucion = new Institucion();
		Conexion conexion = Conexion.getConexion();
		
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(traerInstitucion);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				institucion=(getInstitucion(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return institucion;
	}


	@Override
	public boolean delete(Institucion institucion) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean deleteExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(delete);
			statement.setInt(1, institucion.getId());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				deleteExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return deleteExitoso;
	}


	@Override
	public boolean update(Institucion institucion) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean updateExitoso = false;
		
		try
		{
			statement = conexion.prepareStatement(update);
		
			statement.setString(1, institucion.getNombre());
			statement.setInt(2, institucion.getId());
			
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				updateExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
				
		return updateExitoso;
	}

}
