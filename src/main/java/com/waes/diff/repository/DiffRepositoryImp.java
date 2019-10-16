package com.waes.diff.repository;

import com.waes.diff.infra.DiffException;
import com.waes.diff.model.DiffBucket;
import lombok.NonNull;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
class DiffRepositoryImp implements DiffRepository {

    @Autowired
    private JdbcTemplate template;

    @Override
    public void insertOrUpdate(@NonNull final DiffBucket bucket) {
        Optional.ofNullable(bucket.getId()).orElseThrow(() -> new DiffException("Invalid id"));
        val diffBucket = get(bucket.getId());
        val sql = diffBucket.isPresent() ? SQL.UPDATE : SQL.INSERT;
        template.update(sql, bucket.getLeft(), bucket.getRight(), bucket.getId());
    }

    @Override
    public Optional<DiffBucket> get(@NonNull final Integer id) {
        try {
            val bucket = template.queryForObject(SQL.SELECT_ID, new Object[]{id}, createRowMapper());
            return Optional.of(bucket);
        } catch (IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void createTable() {
        template.update(SQL.CREATE_TABLE);
    }

    private RowMapper<DiffBucket> createRowMapper() {
        return (rs, rowNum) -> DiffBucket.builder()
                .id(rs.getInt("id"))
                .left(rs.getString("left_data"))
                .right(rs.getString("right_data"))
                .build();
    }

}