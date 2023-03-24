package com.jingnan.data.algorithm;

import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *  <p>类描述：自定义范围分片</p>
 * @ClassAuthor hsj 2022-09-17 14:29
 */
public class MyDBRangeShardingAlgorithm implements RangeShardingAlgorithm<Integer> {

    @Override
    public Collection<String> doSharding(Collection<String> databaseNames, RangeShardingValue<Integer> rangeShardingValue) {

        Set<String> result = new LinkedHashSet<>();
        //lowerEndpoint 表示起始值， upperEndpoint 表示截止值。
        int lower = rangeShardingValue.getValueRange().lowerEndpoint();
        int upper = rangeShardingValue.getValueRange().upperEndpoint();
        // 循环范围计算分库逻辑
        for (int i = lower; i <= upper; i++) {
            for (String databaseName : databaseNames) {
                if (databaseName.endsWith(i % databaseNames.size() + "")) {
                    result.add(databaseName);
                }
            }
        }
        return result;
    }
}
