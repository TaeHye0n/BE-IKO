package com.iko.iko.domain.entity;

import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.sql.Date;

@Getter
@DynamicInsert
@DynamicUpdate
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Column(columnDefinition = "DATETIME", updatable = false, nullable = false)
    private Date createdAt;

    private String createdBy;

    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    private Date updatedAt;

    private String updatedBy;

}
