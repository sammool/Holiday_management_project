package sammool.holiday.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import sammool.holiday.domain.Leader;


import javax.sql.DataSource;
import java.util.Optional;

//@Repository
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

     public Optional<Leader> findById(String leader_id){
        String sql = "select * from leader where leader_id = ?";
        try{
            Leader leader = template.queryForObject(sql, leaderRowMapper(),leader_id);
            return Optional.of(leader);
        }catch(EmptyResultDataAccessException e){
            //빈 객체 반환
            return Optional.empty();
        }
       
    }

    private RowMapper<Leader> leaderRowMapper(){
        return (rs,rowNum) -> {
            Leader leader = new Leader();
            leader.setLeader_id(rs.getString("leader_id"));
            leader.setPassword(rs.getString("password"));
            leader.setDegree(rs.getString("degree"));
            leader.setName(rs.getString("name"));
            
            return leader;
        };
    }

}