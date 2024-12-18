package negocio;

import entidad.Reserva;
import java.util.ArrayList;

public interface ReservaNegocio {

	public ArrayList<Reserva> listar ();
	public boolean insert(Reserva reserva);
	public boolean darBaja(Reserva reserva);
	public ArrayList<Reserva> reporteReservas(java.sql.Date fechaInicio,java.sql.Date fechaFin);
}
