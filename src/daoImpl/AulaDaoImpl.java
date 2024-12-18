package daoImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import dao.AulaDao;
import daoImpl.Conexion;

import entidad.Aula;



public class AulaDaoImpl implements AulaDao{
	

	private static final String traerAula = "SELECT * FROM aulas WHERE Id_Aula = ?";
	private static final String listar = "SELECT * FROM aulas WHERE Estado = 1 ";
	private static final String insert = "INSERT INTO aulas(Id_Aula, Nombre_Aula, Capacidad) VALUES (?,?,?)";
	private static final String delete =  "UPDATE aulas SET Estado = 0 WHERE Id_Aula = ?"; 
	private static final String update = "UPDATE aulas SET Capacidad = ?, Nombre_Aula = ? WHERE Id_Aula = ?";
	
	private static final String filtrarAulasDisponibles = "SELECT * FROM aulas WHERE Capacidad >= ? AND Id_Aula NOT IN ( " +
		    "    SELECT Id_Aula FROM reservas_aulas  WHERE Fecha = ?  AND ((? >= Hora_Inicio AND ? < Hora_Final) OR (? > Hora_Inicio AND ? <= Hora_Final)" +
		    "        OR (? <= Hora_Inicio AND ? >= Hora_Final))) ";
		   
		    
		    
		    
		    
		    
	
	@Override
	public boolean insert(Aula aula) {
				
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean insertExitoso = false;
		
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, aula.getId());
			statement.setString(2, aula.getNombre());
			statement.setInt(3, aula.getCapacidad());
			
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
	public ArrayList<Aula> listar() {
		PreparedStatement statement;
		ResultSet resultSet; 
		ArrayList<Aula> aulas = new ArrayList<Aula>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(listar);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				aulas.add(getAula(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return aulas;
	}
	
	
	
	private Aula getAula(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("Id_Aula");
		String nombre = resultSet.getString("Nombre_Aula");
		int capacidad = resultSet.getInt("Capacidad");
		return new Aula(id,nombre,capacidad);
	}




	@Override
	public Aula traerAula(int id) {
		PreparedStatement statement;
		ResultSet resultSet; 
		Aula aula = new Aula();
		Conexion conexion = Conexion.getConexion();
		
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(traerAula);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				aula=(getAula(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return aula;
		
		
	}




	@Override
	public boolean delete(Aula aula) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean deleteExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(delete);
			statement.setInt(1, aula.getId());
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
	public boolean update(Aula aula) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean updateExitoso = false;
		
		try
		{
			statement = conexion.prepareStatement(update);
			statement.setInt(1, aula.getCapacidad());
			statement.setString(2, aula.getNombre());
			statement.setInt(3, aula.getId());
			
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
	
	
	
	public List<Aula> filtrarAulasDisponibles(int capacidadMinima, Date fecha, java.sql.Time horaInicio, java.sql.Time horaFinal) {
		
	    List<Aula> aulasDisponibles = new ArrayList<>();
	    try {
	        PreparedStatement statement = Conexion.getConexion().getSQLConexion().prepareStatement(filtrarAulasDisponibles);
	        statement.setInt(1, capacidadMinima);
	        statement.setDate(2, new java.sql.Date(fecha.getTime()));
	        statement.setTime(3, horaInicio);
	        statement.setTime(4, horaInicio);
	        statement.setTime(5, horaFinal);
	        statement.setTime(6, horaFinal);
	        statement.setTime(7, horaInicio);
	        statement.setTime(8, horaFinal);

	        ResultSet resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            Aula aula = new Aula();
	            aula.setId(resultSet.getInt("Id_Aula"));
	            aula.setNombre(resultSet.getString("Nombre_Aula"));
	            aula.setCapacidad(resultSet.getInt("Capacidad"));
	            aulasDisponibles.add(aula);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return aulasDisponibles;
	}

}
