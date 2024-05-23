package rs.ac.uns.ftn.db.jdbc.theatre.dao;

import java.sql.SQLException;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.theatre.dto.complexquery4.PlayDTO;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Play;

public interface PlayDAO extends CRUDDao<Play, Integer> {

	//metoda koja vraca najposecenije predstave (moze ih biti vise jednako posecenih zbog toga je lista, a ne jedna predstava)
	List<PlayDTO> findMostVisitedPlays() throws SQLException;
}
