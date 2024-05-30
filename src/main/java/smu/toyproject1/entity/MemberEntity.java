package smu.toyproject1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import smu.toyproject1.dto.MemberDTO;

@Entity
@Setter
@Getter
@Table(name="member_table")
public class MemberEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column(unique = true)
    private String memberName;

    @Column(unique = true) // unique 제약조건 추가
    private String memberEmail;

    @Column
    private String memberPassword;
    @Column
    private String gender;
    @Column
    private String age;


    public static MemberEntity toMemberEntity(MemberDTO memberDTO){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setGender(memberDTO.getGender());
        memberEntity.setAge(memberDTO.getAge());
        return memberEntity;
    }
}
