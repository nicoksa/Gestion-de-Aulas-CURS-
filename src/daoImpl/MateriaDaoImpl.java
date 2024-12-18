package daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.MateriaDao;
import entidad.Carrera;
import entidad.Institucion;
import entidad.Materia;
import entidad.Profesor;

public class MateriaDaoImpl implements MateriaDao{

	private static final String listar =  "SELECT materias.Id_Materia, carreras.Id_Carrera, instituciones.Id_Institucion, " +
		    "materias.Materia , carreras.Carrera , instituciones.Institucion  " +
		    "FROM materias " +
		    "JOIN carreras ON carreras.Id_Carrera = materias.Id_Carrera " +
		    "JOIN instituciones ON carreras.Id_Institucion = instituciones.Id_Institucion WHERE materias.Estado = 1";
	private static final String listarfiltrado =  "SELECT materias.Id_Materia, carreras.Id_Carrera, instituciones.Id_Institucion, " +
		    "materias.Materia , carreras.Carrera , instituciones.Institucion  " +
		    "FROM materias " +
		    "JOIN carreras ON carreras.Id_Carrera = materias.Id_Carrera " +
		    "JOIN instituciones ON carreras.Id_Institucion = instituciones.Id_Institucion WHERE materias.Estado = 1 and carreras.Id_Carrera =?";
	private static final String traerMateria =  listar + " AND materias.Id_Materia = ?";
	private static final String delete = "UPDATE materias SET Estado = 0 WHERE Id_Materia = ?";
	
	//private static final String update = "UPDATE materias SET  Materia = ?,Id_Carrera = ? WHERE Id_Institucion = ?";
	
	private static final String insert = "INSERT INTO materias(Id_Carrera,Id_Institucion, Materia) VALUES (?,?,?)";
	
	
	@Override
	public ArrayList<Materia> listar() {

			
			PreparedStatement statement;
			ResultSet resultSet; 
			ArrayList<Materia> materias = new ArrayList<Materia>();
			Conexion conexion = Conexion.getConexion();
			try 
			{
				statement = conexion.getSQLConexion().prepareStatement(listar);
				resultSet = statement.executeQuery();
				while(resultSet.next())
				{
					materias.add(getMateria(resultSet));
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			return materias;
	}
	
	
	
	private Materia getMateria(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("Id_Materia");
		int idInstitucion = resultSet.getInt("Id_Institucion");
		int IdCarrera = resultSet.getInt("Id_Carrera");
		String nombreInstitucion = resultSet.getString("Institucion");
		String nombreMateria = resultSet.getString("Materia");
		String nombreCarrera = resultSet.getString("Carrera");
		
		Institucion institucion= new Institucion(idInstitucion, nombreInstitucion);		
		Carrera carrera = new Carrera (IdCarrera, institucion, nombreCarrera);
		
		return new Materia(id,carrera,institucion,nombreMateria);
	}
	

	
	public int insert(Materia materia) {
	    PreparedStatement statement;
	    Connection conexion = Conexion.getConexion().getSQLConexion();
	    int generatedId = -1;
	    
	    try {
	        statement = conexion.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
	        statement.setInt(1, materia.getCarrera().getId());
	        statement.setInt(2, materia.getInstitucion().getId());
	        statement.setString(3, materia.getNombre());

	        if (statement.executeUpdate() > 0) {
	            // Obtener el ID generado de la materia
	            ResultSet generatedKeys = statement.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                generatedId = generatedKeys.getInt(1);
	            }
	            conexion.commit();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            conexion.rollback();
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	    }

	    return generatedId; // Retorna el ID generado para la materia
	}
	
	
	@Override
	public Materia traerMateria(int id) {
		PreparedStatement statement;
		ResultSet resultSet; 
		Materia materia = new Materia();
		Conexion conexion = Conexion.getConexion();
		
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(traerMateria);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				materia=(getMateria(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return materia;
	}

	@Override
	public boolean update(Materia materia) {
		 boolean resultado = false;
		 String procedimiento = "{CALL sp_modificamateria(?, ?, ?, ?,?)}"; 
		 Connection conexion = Conexion.getConexion().getSQLConexion();

		 try (CallableStatement cst = conexion.prepareCall(procedimiento)) {
		        
			 cst.setInt(1,materia.getId());
		     cst.setInt(2, materia.getCarrera().getId());
		     cst.setInt(3, materia.getInstitucion().getId());
		     cst.setString(4, materia.getNombre());
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

	@Override
	public boolean delete(Materia materia) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean deleteExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(delete);
			statement.setInt(1, materia.getId());
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
	public ArrayList<Materia> listarfiltrado(int idmateria) {
		
		PreparedStatement statement;
		ResultSet resultSet; 
		ArrayList<Materia> materias = new ArrayList<Materia>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(listarfiltrado);
			statement.setInt(1, idmateria);
			
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				materias.add(getMateria(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return materias;
	}

}
