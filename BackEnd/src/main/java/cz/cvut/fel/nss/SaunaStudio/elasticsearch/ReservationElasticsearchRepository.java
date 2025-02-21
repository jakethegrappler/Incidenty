package cz.cvut.fel.nss.SaunaStudio.elasticsearch;

import cz.cvut.fel.nss.SaunaStudio.model.Employee;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

//TODO Nainstalovat Postman
/**
 * Elasticsearch Repository pro správu entit {@link Reservation}.
 *
 * <p>Toto úložiště poskytuje metody pro dotazování rezervací na základě zákazníka,
 * časového rozsahu nebo specifických atributů pomocí Elasticsearch.</p>
 */
@Repository
public interface ReservationElasticsearchRepository extends ElasticsearchRepository<Reservation, Integer> {

    /**
     * Vyhledává rezervace spojené se specifickým zákazníkem.
     *
     * @param employee Zákazník, jehož rezervace mají být vyhledány
     * @return Seznam rezervací spojených s daným zákazníkem
     */
    List<Reservation> findByCustomer(Employee employee);

    /**
     * Vyhledává rezervace s počátečními časy mezi zadanými začátkem a koncem.
     *
     * @param start Začátek časového rozsahu
     * @param end Konec časového rozsahu
     * @return Seznam rezervací v zadaném časovém rozsahu
     */
    List<Reservation> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);

    /**
     * Vyhledává rezervace s konkrétním počátečním časem.
     *
     * @param start Počáteční čas rezervací, které mají být vyhledány
     * @return Seznam rezervací s daným počátečním časem
     */
    List<Reservation> findByStartTime(LocalDateTime start);

    /**
     * Vyhledává rezervace na základě počátečního času a ID studia pomocí vlastního Elasticsearch dotazu.
     *
     * @param startTime Počáteční čas rezervací, které mají být vyhledány
     * @param studioId ID studia
     * @return Seznam rezervací, které odpovídají danému počátečnímu času a ID studia
     */
    @Query("{\"bool\": {\"must\": [{\"match\": {\"startTime\": \"?0\"}}, {\"match\": {\"studio.id\": \"?1\"}}]}}")
    List<Reservation> findByStartTimeAndStudioId(LocalDateTime startTime, Integer studioId);
}
