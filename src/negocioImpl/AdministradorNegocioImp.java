package negocioImpl;
import negocio.AdministradorNegocio;
import daoImpl.AdminDaoImpl;
import entidad.Administrador;

public class AdministradorNegocioImp implements AdministradorNegocio{
	
	AdminDaoImpl dao = new AdminDaoImpl();

	@Override
	public boolean existeusuario(String usuario, String Pass) {
		boolean existe = false;
		existe= dao.existeadmin(usuario, Pass);
		if (existe == true)
		{
			return true;
		}
		return false;
	}

	@Override
	public Administrador traerAdmin(String usuario) {
		
		return dao.traerAdmin(usuario);
	}

	@Override
	public boolean update(Administrador admin) {
		
		boolean estado = false;
		
		if(admin.getNombre().trim().length() > 0 && admin.getDni().trim().length() > 0 && admin.getApellido().trim().length() > 0 )
		{
			estado = dao.update(admin);
		}
			
		return estado;
	}
	
	
	
	

}
