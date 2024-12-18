package dao;
import entidad.Aula;
import java.util.ArrayList;

public interface AulaDao {

	
	public boolean insert (Aula aula);
	public boolean delete (Aula aula);
	public boolean update (Aula aula);
	public ArrayList<Aula> listar();
	public Aula traerAula(int id);
}
