package com.sfauto.service.jedis.consts;

public class JedisConstant {

    /**
     * 默认，集群模式
     */
    public final static String REDIS_TYPE_CLUSTER = "cluster";

    /**
     * 单机模式
     */
    public final static String REDIS_TYPE_SINGLE = "single";

    /**
     * properties
     */
    public static final String ENABLE_AUTO_COMMIT_CONFIG = "enable.auto.commit";
    public static final String AUTO_COMMIT_INTERVAL_MS_CONFIG = "auto.commit.interval.ms";
    public static final String GROUP_ID_CONFIG = "group.id";
    public static final String CONSUMER_NAME = "redis.comsumer.name";

    public static final String AUTO_OFFSET_RESET_CONFIG = "auto.offset.reset";
    public static final String MAX_POLL_RECORDS_CONFIG = "max.poll.records";
    public static final String MAX_POLL_INTERVAL_MS_CONFIG = "max.poll.interval.ms";
    public static final String SUBSCRIBE_EXPIRE_TIME_MS = "redis.subscribe.expire.ms";
    public static final String SUBSCRIBE_CONSUMER_EXPIRE_TIME_MS = "redis.subscribe.consumer.expire.ms";

    public static final int DEFAULT_MAX_REDIRECTIONS = 5;
}
