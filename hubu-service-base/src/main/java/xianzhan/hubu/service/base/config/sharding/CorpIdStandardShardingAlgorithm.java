package xianzhan.hubu.service.base.config.sharding;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.infra.exception.ShardingSphereException;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.Collection;
import java.util.Properties;

/**
 * 分片算法
 *
 * @author xianzhan
 * @see java.util.ServiceLoader
 * @see org.apache.shardingsphere.sharding.spi.ShardingAlgorithm
 * @since 2022-05-31
 */
@Slf4j
public class CorpIdStandardShardingAlgorithm implements StandardShardingAlgorithm<String> {

    @Getter
    @Setter
    private Properties props = new Properties();

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> preciseShardingValue) {
        log.info("Hubu - Precise. availableTargetNames: {}, preciseShardingValue: {}", availableTargetNames, preciseShardingValue);
        String corpId = preciseShardingValue.getValue();
        if (corpId == null || corpId.isEmpty()) {
            throw new ShardingSphereException("corpId is empty");
        }

        // 此处简单分片，根据 corpId 最后一个字符的 ascii 编码对 2 取模
        String end = Integer.toString(corpId.charAt(corpId.length() - 1) % 2);
        for (String availableTargetName : availableTargetNames) {
            if (availableTargetName.endsWith(end)) {
                return availableTargetName;
            }
        }
        throw new ShardingSphereException("No matching corpId found. corpId: %s, end: %s, availableTargetNames: %s", corpId, end, availableTargetNames);
    }

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<String> rangeShardingValue) {
        log.info("Hubu - Range. availableTargetNames: {}, rangeShardingValue: {}", availableTargetNames, rangeShardingValue);
        // 表全路由都需要查，可根据 rangeShardingValue 过滤
        return availableTargetNames;
    }

    @Override
    public void init() {
        log.info("Hubu - CorpIdStandardShardingAlgorithm#init()");
    }

    @Override
    public String getType() {
        return "corp_id";
    }
}
