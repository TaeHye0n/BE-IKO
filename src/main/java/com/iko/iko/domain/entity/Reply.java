package com.iko.iko.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "tb_reply")
@DynamicInsert
@DynamicUpdate
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id_pk", unique = true, nullable = false)
    private Integer replyId;

    @Column(name = "member_id_fk")
    private Integer memberId;

    @Column(name = "product_details_id_fk")
    private Integer productDetailsId;

    @Column(name = "content")
    private Integer content;

    @Column(name = "reply_title")
    private String replyTitle;

    @Column(name = "rating")
    private Float rating;

    @Column(name = "like")
    private Integer like;
}
