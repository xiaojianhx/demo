package com.xiaojianhx.demo.quartz.config;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class QuartzConfig {

    private final static String GROUP_NAME = "QuartzJobGroups";

    @Value("${quartz.scheduler.instanceName}")
    private String quartzInstanceName;

    @Value("${spring.datasource.driverClassName}")
    private String myDSDriver;

    @Value("${spring.datasource.url}")
    private String myDSUrl;

    @Value("${spring.datasource.username}")
    private String myDSUser;

    @Value("${spring.datasource.password}")
    private String myDSPassword;

    @Value("${org.quartz.dataSource.myDS.maxConnections}")
    private int myDSMaxConnections;

    private Properties quartzProperties() throws IOException {

        var prop = new Properties();

        // 调度标识名 集群中每一个实例都必须使用相同的名称
        prop.put("quartz.scheduler.instanceName", quartzInstanceName);

        // ID设置为自动获取 每一个必须不同
        prop.put("org.quartz.scheduler.instanceId", "AUTO");

        // 禁用quartz软件更新
        prop.put("org.quartz.scheduler.skipUpdateCheck", "true");

        prop.put("org.quartz.scheduler.jmx.export", "true");

        // 数据库代理类，一般org.quartz.impl.jdbcjobstore.StdJDBCDelegate可以满足大部分数据库
        prop.put("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");

        // 数据保存方式为数据库持久化
        prop.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");

        // 数据库别名 随便取
        prop.put("org.quartz.jobStore.dataSource", "quartzDataSource");

        // prop.put("org.quartz.jobStore.dataSource", "myDS");
        // 表的前缀，默认QRTZ_
        prop.put("org.quartz.jobStore.tablePrefix", "QRTZ_");

        // 是否加入集群
        prop.put("org.quartz.jobStore.isClustered", "true");

        // 调度实例失效的检查时间间隔
        prop.put("org.quartz.jobStore.clusterCheckinInterval", "20000");
        prop.put("org.quartz.jobStore.maxMisfiresToHandleAtATime", "1");
        // 信息保存时间 ms 默认值60秒
        prop.put("org.quartz.jobStore.misfireThreshold", "120000");
        prop.put("org.quartz.jobStore.txIsolationLevelSerializable", "true");
        prop.put("org.quartz.jobStore.selectWithLockSQL", "SELECT * FROM {0}LOCKS WHERE LOCK_NAME = ? FOR UPDATE");

        // 程池的实现类（一般使用SimpleThreadPool即可满足几乎所有用户的需求）
        prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        // 定线程数，至少为1（无默认值）(一般设置为1-100之间的整数合适)
        prop.put("org.quartz.threadPool.threadCount", "10");
        // 设置线程的优先级（最大为java.lang.Thread.MAX_PRIORITY 10，最小为Thread.MIN_PRIORITY 1，默认为5）
        prop.put("org.quartz.threadPool.threadPriority", "5");
        prop.put("org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread", "true");

        prop.put("org.quartz.plugin.triggHistory.class", "org.quartz.plugins.history.LoggingJobHistoryPlugin");
        prop.put("org.quartz.plugin.shutdownhook.class", "org.quartz.plugins.management.ShutdownHookPlugin");
        prop.put("org.quartz.plugin.shutdownhook.cleanShutdown", "true");

        // #自定义连接池
        // org.quartz.dataSource.myDS.connectionProvider.class=com.poly.pay.schedule.DruidConnectionProvider

        return prop;
    }

    @Bean
    public HikariDataSource createDataSource() throws PropertyVetoException {

        var dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(myDSUrl);
        dataSource.setDriverClassName(myDSDriver);
        dataSource.setUsername(myDSUser);
        dataSource.setPassword(myDSPassword);
        dataSource.setMaximumPoolSize(myDSMaxConnections);

        return dataSource;
    }

    private static CronTriggerFactoryBean cronTriggerFactoryBean(JobDetail jobDetail, String cronExpression) {

        var factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetail);
        factoryBean.setCronExpression(cronExpression);
        return factoryBean;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(@Qualifier("executeJobTrigger") Trigger executeJobTrigger)
            throws IOException, PropertyVetoException {

        var factory = new SchedulerFactoryBean();
        factory.setOverwriteExistingJobs(true);
        factory.setAutoStartup(true);
        factory.setQuartzProperties(quartzProperties());
        factory.setApplicationContextSchedulerContextKey("applicationContext");
        factory.setDataSource(createDataSource());

        // 注册触发器
        Trigger[] triggers = { executeJobTrigger };
        factory.setTriggers(triggers);

        return factory;
    }

    @Bean(name = "executeJobTrigger")
    public CronTriggerFactoryBean executeJobTrigger(@Qualifier("executeJobDetail") JobDetail jobDetail) {
        return cronTriggerFactoryBean(jobDetail, "0/1 * * * * ? ");
    }

    @Bean
    public JobDetailFactoryBean executeJobDetail() {
        return createJobDetail(InvokingJobDetailFactory.class, GROUP_NAME, "executeJob");
    }

    private static JobDetailFactoryBean createJobDetail(Class<? extends Job> jobClass, String groupName, String targetObject) {

        var factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(jobClass);
        factoryBean.setDurability(true);
        factoryBean.setRequestsRecovery(true);
        factoryBean.setGroup(groupName);
        Map<String, String> map = new HashMap<>();
        map.put("targetMethod", "execute");
        map.put("targetObject", targetObject);
        factoryBean.setJobDataAsMap(map);

        return factoryBean;
    }
}