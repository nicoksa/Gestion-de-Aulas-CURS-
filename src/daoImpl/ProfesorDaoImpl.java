package daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import dao.ProfesorDao;
import entidad.Institucion;
import entidad.Profesor;

public class ProfesorDaoImpl implements ProfesorDao {

	private static final String listar = "SELECT * FROM profesores WHERE Estado = 1 ORDER BY nombre";
	private static final String listarinactivos = "SELECT * FROM profesores WHERE Estado = 0 ";
	private static final String traerProfesor = "SELECT * FROM profesores WHERE DNI_Profesor = ?";
	private static final String insert = "INSERT INTO profesores(DNI_Profesor,Nombre,Apellido,Telefono,Email) VALUES (?,?,?,?,?)";
	private static final String delete = "UPDATE profesores SET Estado = 0 WHERE Dni_Profesor = ?";
	private static final String update = "UPDATE profesores SET  DNI_Profesor = ?, Nombre = ?, Apellido = ?, Telefono = ?, Email = ? WHERE DNI_Profesor = ?";
	private static final String buscarPorNombre = "SELECT * FROM profesores WHERE Estado = 1 AND Nombre LIKE ?";
	private static final String reactivate = "UPDATE profesores SET Estado = 1 WHERE Dni_Profesor = ?";
	
	
	@Override
	public boolean insert(Profesor profesor) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean insertExitoso = false;

		try {
			statement = conexion.prepareStatement(insert);
			statement.setString(1, profesor.getDni());
			statement.setString(2, profesor.getNombre());
			statement.setString(3, profesor.getApellido());
			statement.setString(4, profesor.getTelefono());
			statement.setString(5, profesor.getEmail());

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
	public boolean delete(Profesor profesor) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean deleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setString(1, profesor.getDni());
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				deleteExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deleteExitoso;
	}

	@Override
	public boolean update(Profesor profesor) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean updateExitoso = false;

		try {
			statement = conexion.prepareStatement(update);

			statement.setString(1, profesor.getDni());
			statement.setString(2, profesor.getNombre());
			statement.setString(3, profesor.getApellido());
			statement.setString(4, profesor.getTelefono());
			statement.setString(5, profesor.getEmail());
			statement.setString(6, profesor.getDni());

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				updateExitoso = true;
			}
		} catch (SQLException e) {
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
	public ArrayList<Profesor> listar() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Profesor> profesores = new ArrayList<Profesor>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(listar);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				profesores.add(getProfesor(resultSet));

				System.out.println(getProfesor(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return profesores;
	}

	@Override
	public ArrayList<Profesor> listarinactivos() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Profesor> profesores = new ArrayList<Profesor>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(listarinactivos);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				profesores.add(getProfesor(resultSet));

				System.out.println(getProfesor(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return profesores;
	}

	@Override
	public Profesor traerProfesor(String dni) {
		PreparedStatement statement;
		ResultSet resultSet;
		Profesor profesor = new Profesor();
		profesor.setDni(dni);
		Conexion conexion = Conexion.getConexion();

		try {
			statement = conexion.getSQLConexion().prepareStatement(traerProfesor);
			statement.setString(1, dni);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				profesor = getProfesor(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return profesor;
	}

	private Profesor getProfesor(ResultSet resultSet) throws SQLException {
		String dni = resultSet.getString("DNI_Profesor");
		String nombre = resultSet.getString("Nombre");
		String apellido = resultSet.getString("Apellido");
		String telefono = resultSet.getString("Telefono");
		String email = resultSet.getString("Email");

		return new Profesor(dni,nombre, apellido, telefono, email);
	}

	@Override
	public ArrayList<Profesor> buscarPorNombre(String busqueda) {
		ArrayList<Profesor> profesores = new ArrayList<>();
		try (Connection conexion = Conexion.getConexion().getSQLConexion();
				PreparedStatement statement = conexion.prepareStatement(buscarPorNombre)) {

			statement.setString(1, "%" + busqueda + "%");
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				profesores.add(getProfesor(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return profesores;
	}

	@Override
	public boolean reactivarProfesor(Profesor profesor) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean reactivateExitoso = false;
		try {
			statement = conexion.prepareStatement(reactivate);
			statement.setString(1, profesor.getDni());
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				reactivateExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reactivateExitoso;
	}

}
