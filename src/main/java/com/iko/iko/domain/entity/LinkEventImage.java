package com.iko.iko.domain.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name="tb_event_image")
@DynamicInsert
@DynamicUpdate
@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LinkEventImage extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="event_image_id_pk",unique = true, nullable = false)
    private Integer linkId;

    @Column(name="event_id_fk",nullable = false)
    private Integer eventId;

    @Column(name="image_id_fk",nullable = false)
    private Integer imageId;
}
