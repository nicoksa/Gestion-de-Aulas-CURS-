package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.CarreraDao;
import entidad.Institucion;
import entidad.Carrera;

public class CarreraDaoImpl implements CarreraDao {

	private static final String listar = "SELECT Id_Carrera, Carrera, instituciones.Id_Institucion, Institucion FROM carreras " +
	        "JOIN instituciones  ON carreras.Id_Institucion = instituciones.Id_Institucion WHERE carreras.Estado = 1";
	private static final String insert = "INSERT INTO carreras(Id_Institucion, Carrera) VALUES (?,?)";
	
	private static final String update = "UPDATE carreras SET  Carrera = ?,Id_Institucion = ? WHERE Id_Carrera = ?";
	
	private static final String traerCarrera = 
		    "SELECT carreras.Id_Carrera, carreras.Carrera, instituciones.Id_Institucion, instituciones.Institucion " +
		    "FROM carreras JOIN instituciones ON carreras.Id_Institucion = instituciones.Id_Institucion " +
		    "WHERE carreras.Id_Carrera = ?";
	
	private static final String delete = "UPDATE carreras SET Estado = 0 WHERE Id_Carrera = ?";
	
	@Override
	public ArrayList<Carrera> listar() {
		
		PreparedStatement statement;
		ResultSet resultSet; 
		ArrayList<Carrera> carreras = new ArrayList<Carrera>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(listar);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				carreras.add(getCarrera(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return carreras;
	}
	
	private Carrera getCarrera(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("Id_Carrera");
		int idInstitucion = resultSet.getInt("Id_Institucion");
		String nombreInstitucion = resultSet.getString("Institucion");
		Institucion institucion= new Institucion(idInstitucion, nombreInstitucion);		
		String nombre = resultSet.getString("Carrera");
		return new Carrera(id,institucion,nombre);
	}

	@Override
	public boolean insert(Carrera carrera) {
		
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean insertExitoso = false;
		
		try
		{
			statement = conexion.prepareStatement(insert);	
			statement.setInt(1,carrera.getInstitucion().getId());
			statement.setString(2, carrera.getNombre());
			
			
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
	public Carrera traerCarrera(int id) {
		
		PreparedStatement statement;
		ResultSet resultSet; 
		Carrera carrera = new Carrera();
		Conexion conexion = Conexion.getConexion();
		
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(traerCarrera);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				carrera=(getCarrera(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return carrera;
	}
	
	

	@Override
	public boolean update(Carrera carrera) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean updateExitoso = false;
		
		try
		{
			statement = conexion.prepareStatement(update);
		
			statement.setString(1, carrera.getNombre());
			statement.setInt(2, carrera.getInstitucion().getId());
			statement.setInt(3, carrera.getId());
			
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

	@Override
	public boolean delete(Carrera carrera) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean deleteExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(delete);
			statement.setInt(1, carrera.getId());
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

}
