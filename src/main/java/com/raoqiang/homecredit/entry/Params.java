package com.raoqiang.homecredit.entry;

import java.util.Map;
import java.util.Set;

/**
 * hbase请求参数类
 */
public class Params {

    // 表名
    private String tableName;

    // 索引名
    private String indexName;

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    // 查询条件
    private Map<String, Object> condition;

    // 需要的查询字段，null则查询所有字段
    private Set<String> columns;

    public Params() {
    }

    public Params(String tableName, String indexName, Map<String, Object> condition, Set<String> columns) {
        this.tableName = tableName;
        this.indexName = indexName;
        this.condition = condition;
        this.columns = columns;
    }

    public Params(String tableName, Map<String, Object> condition, Set<String> columns) {
        this.tableName = tableName;
        this.condition = condition;
        this.columns = columns;
    }

    public Params(String tableName, Map<String, Object> condition) {
        this.tableName = tableName;
        this.condition = condition;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<String, Object> getCondition() {
        return condition;
    }

    public void setCondition(Map<String, Object> condition) {
        this.condition = condition;
    }

    public Set<String> getColumns() {
        return columns;
    }

    public void setColumns(Set<String> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "Params{" +
                "tableName='" + tableName + '\'' +
                ", condition=" + condition +
                ", columns=" + columns +
                '}';
    }
}
