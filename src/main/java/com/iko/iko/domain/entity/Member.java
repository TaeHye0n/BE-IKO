package com.iko.iko.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "member")
@DynamicInsert
@DynamicUpdate
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", unique = true, nullable = false)
    private Integer memberId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String readName;

    private String postCode;

    private String phone;

    @Column(unique = true, nullable = false)
    private String email;

    private String birthDay;

    @Column(unique = true)
    private String password;

    private String recommendCode;

    private Float point;


}
