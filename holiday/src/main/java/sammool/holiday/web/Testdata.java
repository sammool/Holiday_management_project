// package sammool.holiday.web;

// import org.springframework.stereotype.Component;

// import jakarta.annotation.PostConstruct;
// import lombok.RequiredArgsConstructor;
// import sammool.holiday.domain.Leader;
// import sammool.holiday.domain.Member;
// import sammool.holiday.repository.JpaLeaderRepository;
// import sammool.holiday.repository.JpaMemberRepository;

// @Component
// @RequiredArgsConstructor
// public class Testdata {
//     private final JpaMemberRepository memberRepository;
//     private final JpaLeaderRepository leaderRepository;

//     @PostConstruct
//     public void init(){
//         Member member = new Member();
//         member.setMember_id("23-00000000");
//         member.setDegree("상병");
//         member.setName("박찬규");
//         member.setPassword("sa2003");

//         memberRepository.save(member);

//         Leader leader = new Leader();
//         leader.setLeader_id("23-11111111");
//         leader.setPassword("test");
//         leader.setDegree("병장");
//         leader.setName("이후");
//         leaderRepository.save(leader);
//     }
// }
