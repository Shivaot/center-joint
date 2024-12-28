package com.hitachi.epdi2.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * Shiva Created on 29/12/24
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class DateAudit implements Serializable {
    @CreatedDate
    @Column(name = "date_created", nullable = false, updatable = false)
    protected Date createdAt;

    @LastModifiedDate
    @Column(name = "last_updated")
    protected Date updatedAt;
}
