package rs.ac.uns.ftn.db.jdbc.theatre.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.theatre.dto.complexquery2.PlayShowingsStatsDTO;
import rs.ac.uns.ftn.db.jdbc.theatre.dto.complexquery3.PlayStatsDTO;
import rs.ac.uns.ftn.db.jdbc.theatre.dto.complexquery5.ShowingDTO;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Showing;

public interface ShowingDAO extends CRUDDao<Showing, Integer> {

	// metoda koja prikazuje sve predstave koje se prikazuju na odredjenoj sceni sa
	// akumuliranim podacima
	List<PlayStatsDTO> findBySceneId(Integer idScene) throws SQLException;

	// metoda koja racuna ukupan broj gledalaca, prosecan broj gledalaca i broj
	// prikazivanja za svaku predstavu
	List<PlayShowingsStatsDTO> findSumAvgCountForShowingPlays() throws SQLException;

	// metoda koja prikazuje predstave ciji je broj gledalaca veci od broja sedista
	List<ShowingDTO> findShowingForDeleting(Connection connection) throws SQLException;

	// metoda koja prikazuje sva prikazivanje odredjene predstave
	List<Showing> findShowingByPlayId(Integer idPlay) throws SQLException;

	// metoda koja vrsi jednu transakcionu obradu u zadatku 5
	List<ShowingDTO> deleteAndInsertIntoShowing() throws SQLException;

}
