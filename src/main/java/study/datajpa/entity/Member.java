package study.datajpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id" , "username" , "age"})
@NamedQuery(name = "Member.findByUsername",
        query = "select m from Member m where m.username = :username")
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;
    private int age;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username, int i, Team team) {
        this.username = username;
        this.age = i;
        if(team != null){
            this.changeTeam(team);
        }

    }

    public Member(String username, int i) {
        this.username = username;
        this.age = i;
    }

    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }

    public Member(String username){
        this.username = username;
    }
}
