package com.iko.iko.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "tb_member")
@DynamicInsert
@DynamicUpdate
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id_pk", unique = true, nullable = false)
    private Integer memberId;

    private String name;

    private String readname;

    private Integer postCode;

    private String phone;

    @Column(unique = true)
    private String email;

    private String birthday;

    private String password;

    private Integer point;

    private Integer auth;

}
