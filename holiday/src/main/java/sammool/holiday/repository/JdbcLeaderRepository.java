package sammool.holiday.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sammool.holiday.domain.Leader;

import javax.sql.DataSource;
import java.util.Optional;

@Repository
public class JdbcLeaderRepository {
    private final JdbcTemplate template;

    public JdbcLeaderRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    public void createTable(){
        String sql = "CREATE TABLE IF NOT EXISTS leader ("
        + "leader_id VARCHAR(30), "
        + "password VARCHAR(30) NOT NULL,"
        + "degree VARCHAR(5) NOT NULL, "
        + "name VARCHAR(10) NOT NULL, "
        + "PRIMARY KEY (leader_id)"
        +")";

        template.update(sql);
    }

    public Optional<Leader> save(Leader leader){
        String sql = "insert into leader(leader_id, password, degree, name) values (?, ?, ?, ?)";
        template.update(sql, leader.getLeader_id(),leader.getPassword(), leader.getDegree(),leader.getName());
        return Optional.of(leader);
    }

}
