package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;

import dao.ReservaDao;
import java.sql.CallableStatement;
import java.sql.Connection;

import daoImpl.Conexion;

import entidad.*;


import java.util.Date;

import org.apache.tomcat.jni.Time;

//IMPLEMENTACIÓN DE LA INTERFAZ ReservaDao PARA LA GESTIÓN DE DATOS DE RESERVAS
public class ReservaDaoImpl implements ReservaDao{
	
	private static final String listar =  "SELECT Id_Reserva ,Id_Materia, Id_Aula, Dni_Profesor, Dni_Administrador, Fecha, Hora_Inicio, Hora_Final, Estado FROM reservas_aulas WHERE Estado = 1"; 
	private static final String darBaja = "DELETE from reservas_aulas WHERE Id_Reserva = ?"; 
	private static final String reporteReservas = "SELECT * FROM reservas_aulas WHERE Estado = 1 AND Fecha BETWEEN ? AND ?";
		    
	//DECLARO LOS STRINGS DE CADA QUERIE

	@Override
	public ArrayList<Reserva> listar() {
		
		// MÉTODO PARA OBTENER TODAS LAS RESERVAS ACTIVAS
				PreparedStatement statement; //DECLARO EL STATEMENT PARA PASAR LA QUERIE
				ResultSet resultSet;  //DECLARO EL RESULSET QUE VA A ALMACENAR LOS RESULTADOS DE LA CONSULTA
				ArrayList<Reserva> reservas = new ArrayList<>(); //GENERO LA LISTA DE TIPO RESERVA
				Conexion conexion = Conexion.getConexion(); //ESTABLEZCO LA CONEXION
				try {
					statement = conexion.getSQLConexion().prepareStatement(listar); // LE PASO LA CONEXION Y LA QUERIE
					resultSet = statement.executeQuery(); // LAS EJECUTO
					while (resultSet.next()) { //REVISO SI HAY ALGUN RESULTADO MAS
						// AGREGA UNA RESERVA A LA LISTA UTILIZANDO EL MÉTODO getReserva
						reservas.add(getReserva(resultSet)); // SI HAY LO AGREGO AL ARRAYLIST
					}
				} catch (SQLException e) {
					e.printStackTrace(); //MUESTRO LA TRAZA DEL ERROR
				}
				return reservas;
	}
	
	
	private Reserva getReserva(ResultSet resultSet) throws SQLException //METODO PARA GENERAR UN OBJETO DE TIPO RESERVA Y SINO LO PUEDO GENERAR ME TIRA UNA EXCEPTION
	{																	// RECIBE UN RESULSET -- GUARDA CADA DATO EN UNA VARIABLE -- LUEGO CON CADA DATO LO METE EN UN OBJETO DE CADA TIPO
		int id = resultSet.getInt("Id_Reserva");
		int idMateria = resultSet.getInt("Id_Materia");
		int idAula = resultSet.getInt("Id_Aula");
		String dniProfesor = resultSet.getString("Dni_Profesor");
		String dniAdministrador = resultSet.getString("Dni_Administrador");
		Date fecha = resultSet.getDate("Fecha");
		java.sql.Time horaInicio = resultSet.getTime("Hora_Inicio");
		java.sql.Time horaFinal = resultSet.getTime("Hora_Final");
		boolean estado = resultSet.getBoolean("Estado");
		
		Materia materia = new Materia();
		materia.setId(idMateria);		
		
		Aula aula = new Aula();
		aula.setId(idAula);
		
		Profesor profesor = new Profesor();
		profesor.setDni(dniProfesor);
		
		Administrador admin = new Administrador();
		admin.setDni(dniAdministrador);
		
		return new Reserva(id,aula,admin,profesor,materia,fecha,horaInicio,horaFinal,estado);
	}


	@Override  // METODO PARA INSERTAR UNA NUEVA RESERVA
	public boolean insert(Reserva reserva) {
		 boolean resultado = false;
		    String procedimiento = "{CALL sp_cargareserva(?, ?, ?, ?, ?, ?, ?)}"; //DECLARO EL STRING CON EL SP Y LA CANT DE DATOS QUE LE VOY A MANDAR
		    Connection conexion = Conexion.getConexion().getSQLConexion();

		    try (CallableStatement cst = conexion.prepareCall(procedimiento)) { //CALLABLESTATEMENT LLAMA AL SP -- LE PASO EL STRING PROCEDIMIENTO QUE DECLARE ARRIBA
		        // Configurar parámetros del procedimiento almacenado			//Y EN QUE POSICION LOS RECIBE
		        cst.setInt(1, reserva.getAula().getId());
		        cst.setString(2, reserva.getAdministrador().getDni());
		        cst.setString(3, reserva.getProfesor().getDni());
		        cst.setInt(4, reserva.getMateria().getId());
		        cst.setDate(5, new java.sql.Date(reserva.getFecha().getTime()));
		        cst.setTime(6, reserva.getHoraInicio());
		        cst.setTime(7, reserva.getHoraFinal());
		       

		        // Ejecutar procedimiento almacenado
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
	public boolean darBaja(Reserva reserva) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean bajaExitosa = false;
		try 
		{
			statement = conexion.prepareStatement(darBaja);
			statement.setInt(1, reserva.getId()); //LE PASA EL ID QUE VA A USAR LA CONSULTA PARA LA BAJA
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				bajaExitosa = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return bajaExitosa;
	}


	@Override
	public ArrayList<Reserva> reporteReservas(java.sql.Date fechaInicio, java.sql.Date fechaFin) {
		
		
		PreparedStatement statement;
		ResultSet resultSet; 
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(reporteReservas);
			statement.setDate(1,fechaInicio);
			statement.setDate(2,fechaFin);
			resultSet = statement.executeQuery();
			
			
			while(resultSet.next())
			{
				reservas.add(getReserva(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return reservas;
	}
	


}
