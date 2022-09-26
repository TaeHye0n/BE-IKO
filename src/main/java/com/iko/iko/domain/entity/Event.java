package com.iko.iko.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_event")
@DynamicInsert
@DynamicUpdate
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class Event extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id_pk", unique = true, nullable = false)
    private Integer eventId;

    @Column(name = "member_id_fk")
    private Integer memberId;

    @Column(name = "event_title")
    private String eventTitle;

    @Column(name = "event_description")
    private String eventDescription;

    @Column(name = "event_start_time")
    private Date eventStartTime;

    @Column(name = "event_end_time")
    private Date eventEndTime;

}
