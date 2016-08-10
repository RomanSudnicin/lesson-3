package dbService.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by roman on 10.08.16.
 */
public interface ResultHandler<T> {
    T handler(ResultSet resultSet) throws SQLException;
}
