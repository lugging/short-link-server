package com.mimik.link.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sl_target_url")
public class TargetUrl {
    public static final int STATUS_ENABLE = 1;
    public static final int STATUS_DISABLE = 0;

    /**
     * 主键，用于存储 Key
     */
    @Id
    private Long id;

    /**
     * 目标 URL
     */
    @Column(updatable = false)
    private String url;

    private int status = STATUS_ENABLE;

    public void disable() {
        setStatus(STATUS_DISABLE);
    }

    @JsonIgnore
    public boolean isEnable() {
        return this.getStatus() == STATUS_ENABLE;
    }
}