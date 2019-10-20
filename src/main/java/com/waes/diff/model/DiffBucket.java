package com.waes.diff.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "diff_bucket")
public final class DiffBucket {

    @Id
    @Column(name = "diff_id", nullable = false)
    private Integer id;

    @Column(name = "diff_right", columnDefinition = "TEXT")
    private String right;

    @Column(name = "diff_left", columnDefinition = "TEXT")
    private String left;

    public boolean isValid() {
        return !StringUtils.isEmpty(right) && !StringUtils.isEmpty(left);
    }

}