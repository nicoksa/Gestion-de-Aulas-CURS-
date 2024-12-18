package negocio;
import entidad.Administrador;

public interface AdministradorNegocio {
	
	public boolean existeusuario(String usuario , String Pass);
	public Administrador traerAdmin(String usuario);
	public boolean update(Administrador admin);

}
