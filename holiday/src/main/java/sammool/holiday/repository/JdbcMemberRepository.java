package sammool.holiday.repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

import sammool.holiday.domain.Member;
import sammool.holiday.repository.MemberRepository; 

@Repository
public class JdbcMemberRepository implements MemberRepository{

    private final JdbcTemplate template;

    public JdbcMemberRepository(DataSource dataSource){
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public void createTable(){
        String sql = "CREATE TABLE IF NOT EXISTS member ("
        + "member_id VARCHAR(30), "
        + "degree VARCHAR(3) NOT NULL, "
        + "name VARCHAR(10) NOT NULL, "
        + "leftover_days INTEGER DEFAULT 0,"
        + "points INTEGER DEFAULT 0,"
        + "PRIMARY KEY (member_id)"
        + ")";

        template.update(sql);
    }

    @Override
    public Member save(Member member){
        String sql = "insert into member(member_id, degree, name, leftover_days, points) values(?, ?, ?, ? ,?)";
        template.update(sql, member.getMember_id(),member.getDegree(),member.getName(),member.getLeftover_days(),member.getPoints());
        return member;
    }
    @Override
    public Member findById(String member_id){
        String sql = "select * from member where member_id = ?";
        return template.queryForObject(sql, memberRowMapper(),member_id);
    }

    @Override
    public List<Member> findAll(){
        String sql = "select * from member";
        return template.query(sql, memberRowMapper());
    }

    @Override
    public void update(Member member){
        String sql = "UPDATE member SET leftover_days = ?, points = ? where member_id = ?";

        template.update(sql,member.getLeftover_days(),member.getPoints(),member.getMember_id());
    }

    private RowMapper<Member> memberRowMapper(){
        return (rs,rowNum) -> {
            Member member = new Member();
            member.setMember_id(rs.getString("member_id"));
            member.setDegree(rs.getString("degree"));
            member.setName(rs.getString("name"));
            member.setLeftover_days(rs.getInt("leftover_days"));
            member.setPoints(rs.getInt("points"));
            return member;
        };
    }
}
