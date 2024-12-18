package dao;
import entidad.Administrador;

public interface AdminDao {
	
	public boolean existeadmin(String Admin , String pass);
	public Administrador traerAdmin(String dni);
	public boolean update(Administrador admin);
}
