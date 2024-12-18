package negocio;

import entidad.Institucion;
import java.util.ArrayList;

public interface InstitucionNegocio {

	public boolean insert (Institucion institucion);
	public boolean delete (Institucion institucion);
	public boolean update (Institucion institucion);
	public ArrayList<Institucion> listar();
	public Institucion traerInstitucion(int id);
}
