package za.ac.cput.sibadaki.Repository;

import java.sql.SQLException;
import java.util.Set;

/**
 * Created by User on 2016/09/03.
 */
public interface Repository <E,ID>{
    E findById(ID id);

    E save(E entity) throws SQLException;

    E update(E entity) throws SQLException;

    E delete(E entity) throws SQLException;

    Set<E> findAll() throws SQLException;

    int deleteAll() throws SQLException;

}
