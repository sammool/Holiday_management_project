package sammool.holiday.repository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import javax.sql.DataSource;
import java.util.Optional;
import java.util.List;


import sammool.holiday.domain.Member;


//@Repository
public class JdbcMemberRepository implements MemberRepository{

    private final JdbcTemplate template;

    private static final List<Member> applyMember = new ArrayList<>();

    public JdbcMemberRepository(DataSource dataSource){
        this.template = new JdbcTemplate(dataSource);
    }



    @Override
    public void createTable(){
        String sql = "CREATE TABLE IF NOT EXISTS member ("
        + "member_id VARCHAR(30), "
        + "password String NOT NULL,"
        + "degree VARCHAR(3) NOT NULL, "
        + "name VARCHAR(10) NOT NULL, "
        + "leftover_days INTEGER DEFAULT 0,"
        + "points INTEGER DEFAULT 0,"
        + "PRIMARY KEY (member_id)"
        + ")";

        template.update(sql);
    }

    @Override
    public Optional<Member> save(Member member){
        String sql = "insert into member(member_id, password, degree, name, leftover_days, points) values (?, ?, ?, ?, ? ,?)";
        template.update(sql, member.getMember_id(),member.getPassword(), member.getDegree(),member.getName(),member.getLeftover_days(),member.getPoints());
        return Optional.of(member);
    }
    @Override
    public Optional<Member> findById(String member_id){
        String sql = "select * from member where member_id = ?";
        try{
            Member member = template.queryForObject(sql, memberRowMapper(),member_id);
            return Optional.of(member);
        }catch(EmptyResultDataAccessException e){
            //빈 객체 반환
            return Optional.empty();
        }
       
    }

    @Override
    public List<Member> findAll(){
        String sql = "select * from member";
        return template.query(sql, memberRowMapper());
    }

    @Override
    public Optional<Member> update(Member member){
        String sql = "UPDATE member SET degree = ?, leftover_days = ?, points = ? where member_id = ?";

        template.update(sql,member.getDegree(), member.getLeftover_days(),member.getPoints(),member.getMember_id());
        return Optional.of(member);
    }

    @Override
    public List<Member> findApplyMember(){
        return applyMember;
    }
    @Override
    public void addApplyMember(Member member){
        applyMember.add(member);
    }

    

    private RowMapper<Member> memberRowMapper(){
        return (rs,rowNum) -> {
            Member member = new Member();
            member.setMember_id(rs.getString("member_id"));
            member.setPassword(rs.getString("password"));
            member.setDegree(rs.getString("degree"));
            member.setName(rs.getString("name"));
            member.setLeftover_days(rs.getInt("leftover_days"));
            member.setPoints(rs.getInt("points"));
            
            return member;
        };
    }
}
