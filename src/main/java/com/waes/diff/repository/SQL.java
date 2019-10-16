package com.waes.diff.repository;

class SQL {

    static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS diff_bucket (id INTEGER NOT NULL PRIMARY KEY, left_data TEXT, right_data TEXT)";
    static final String UPDATE = "UPDATE diff_bucket SET left_data = ?, right_data =? WHERE id = ?";
    static final String INSERT = "INSERT INTO diff_bucket (left_data, right_data, id) values(?,?,?)";
    static final String SELECT_ID = "SELECT id, left_data, right_data FROM diff_bucket WHERE id = ?";

}
