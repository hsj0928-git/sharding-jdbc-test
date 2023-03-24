package com.jingnan.data.algorithm;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.ArrayList;
import java.util.Collection;

/**
 *  <p>类描述：hit分表算法</p>
 * @ClassAuthor hsj 2022-09-17 15:18
 */
public class MyTableHintShardingAlgorithm implements HintShardingAlgorithm<String> {

    @Override
    public Collection<String> doSharding(Collection<String> tableNames, HintShardingValue<String> hintShardingValue) {

        Collection<String> result = new ArrayList<>();
        for (String tableName : tableNames) {
            for (String shardingValue : hintShardingValue.getValues()) {
                if (tableName.endsWith(String.valueOf(Long.valueOf(shardingValue) % tableNames.size()))) {
                    result.add(tableName);
                }
            }
        }
        return result;
    }
}
