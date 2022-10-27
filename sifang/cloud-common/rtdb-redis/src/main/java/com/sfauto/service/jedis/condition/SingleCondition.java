package com.sfauto.service.jedis.condition;

import com.sfauto.service.jedis.consts.JedisConstant;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class SingleCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String property = context.getEnvironment().getProperty("redis.type");
        if (property == null) {
            throw new AssertionError();
        }
        return property.equalsIgnoreCase(JedisConstant.REDIS_TYPE_SINGLE);
    }
}
