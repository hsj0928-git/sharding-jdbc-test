package com.jingnan.data.algorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;
/**
 *  <p>类描述：精准分库策略</p>
 * @ClassAuthor hsj 2022-09-17 14:15
 */
public class MyDBPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    @Override
    public String doSharding(Collection<String> databaseNames, PreciseShardingValue<Long> shardingValue) {

        /**
         * databaseNames 所有分片库的集合
         * shardingValue 为分片属性，
         * 其中 logicTableName 为逻辑表，
         * columnName 分片健（字段），
         * value 为从 SQL 中解析出的分片健的值
         */
        for (String databaseName : databaseNames) {
            String value = shardingValue.getValue() % databaseNames.size() + "";
            if (databaseName.endsWith(value)) {
                return databaseName;
            }
        }
        throw new IllegalArgumentException();
    }
}
