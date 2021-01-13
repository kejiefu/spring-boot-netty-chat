package com.mountain.chat.service.config;

import com.google.common.collect.Lists;
import com.mountain.chat.service.util.SequenceUtils;
import com.zaxxer.hikari.HikariDataSource;
import io.shardingsphere.api.algorithm.masterslave.RoundRobinMasterSlaveLoadBalanceAlgorithm;
import io.shardingsphere.api.config.rule.MasterSlaveRuleConfiguration;
import io.shardingsphere.api.config.rule.ShardingRuleConfiguration;
import io.shardingsphere.api.config.rule.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.InlineShardingStrategyConfiguration;
import io.shardingsphere.api.config.strategy.NoneShardingStrategyConfiguration;
import io.shardingsphere.core.keygen.KeyGenerator;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * sharding-jdbc 的数据源配置
 *
 * @description: sharding-jdbc 的数据源配置
 */
@Configuration
public class DataSourceShardingConfig {

    /**
     * 需要手动配置事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 依赖于hikariDataSource01要先创建，不加DependsOn的话，会爆spring循环依赖的问题
     */
    @Bean(name = "dataSource")
    @Primary
    @DependsOn({"hikariDataSource0", "hikariDataSource1"})
    public DataSource dataSource(@Qualifier("hikariDataSource0") HikariDataSource hikariDataSource0,
                                 @Qualifier("hikariDataSource1") HikariDataSource hikariDataSource1) throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();

        // 设置分库策略
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "ds${user_id % 2}"));
        // 设置规则适配的表
        shardingRuleConfig.getBindingTableGroups().add("t_chat_record");
        // 设置分表策略
        shardingRuleConfig.getTableRuleConfigs().add(chartRecordTableRule());
        // 设置规则适配的表
        shardingRuleConfig.getBindingTableGroups().add("t_group_record");
        // 设置分表策略
        shardingRuleConfig.getTableRuleConfigs().add(groupRecordTableRule());

        //设置主库
        shardingRuleConfig.setDefaultDataSourceName("ds0");

        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new NoneShardingStrategyConfiguration());

        Properties properties = new Properties();
        properties.setProperty("sql.show", "true");

        //数据库配置
        Map<String, DataSource> dataSourceMap = new HashMap<>(16);
        dataSourceMap.put("ds0", hikariDataSource0);
        dataSourceMap.put("ds1", hikariDataSource1);

        //主从配置，支持多主多从
        //shardingRuleConfig.setMasterSlaveRuleConfigs(getMasterSlaveRuleConfigurations());

        return ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new ConcurrentHashMap<>(16), properties);
    }

    private static List<MasterSlaveRuleConfiguration> getMasterSlaveRuleConfigurations() {
        RoundRobinMasterSlaveLoadBalanceAlgorithm loadBalanceAlgorithm = new RoundRobinMasterSlaveLoadBalanceAlgorithm();
        MasterSlaveRuleConfiguration masterSlaveRuleConfig1 =
                new MasterSlaveRuleConfiguration("ds_0", "demo_ds_master_0",
                        Arrays.asList("demo_ds_master_0_slave_0", "demo_ds_master_0_slave_1"), loadBalanceAlgorithm);
        MasterSlaveRuleConfiguration masterSlaveRuleConfig2 = new MasterSlaveRuleConfiguration("ds_1", "demo_ds_master_1",
                Arrays.asList("demo_ds_master_1_slave_0", "demo_ds_master_1_slave_1"), loadBalanceAlgorithm);
        return Lists.newArrayList(masterSlaveRuleConfig1, masterSlaveRuleConfig2);
    }

    private TableRuleConfiguration chartRecordTableRule() {
        TableRuleConfiguration tableRule = new TableRuleConfiguration();
        // 设置逻辑表名
        tableRule.setLogicTable("t_chat_record");
        // ds${0..1}.t_order_${0..2} 也可以写成 ds$->{0..1}.t_order_$->{0..1}
        tableRule.setActualDataNodes("ds${0..1}.t_chat_record_${0..2}");
        tableRule.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "t_chat_record_$->{user_id % 3}"));
        tableRule.setKeyGenerator(customKeyGenerator());
        tableRule.setKeyGeneratorColumnName("user_id");
        return tableRule;
    }

    private TableRuleConfiguration groupRecordTableRule() {
        TableRuleConfiguration tableRule = new TableRuleConfiguration();
        // 设置逻辑表名
        tableRule.setLogicTable("t_group_record");
        // ds${0..1}.t_order_${0..2} 也可以写成 ds$->{0..1}.t_order_$->{0..1}
        tableRule.setActualDataNodes("ds${0..1}.t_group_record_${0..2}");
        tableRule.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("group_id", "t_group_record_$->{user_id % 3}"));
        tableRule.setKeyGenerator(customKeyGenerator());
        tableRule.setKeyGeneratorColumnName("group_id");
        return tableRule;
    }


    /**
     * 自定义主键生成器
     */
    private KeyGenerator customKeyGenerator() {
        return new CustomSnowflakeKeyGenerator(SequenceUtils.sequence);
    }

}
