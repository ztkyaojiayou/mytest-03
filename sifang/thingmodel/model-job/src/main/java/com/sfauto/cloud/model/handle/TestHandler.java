package com.sfauto.cloud.model.handle;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * xxl-job开发示例
 * @author qzz
 *
 * 开发步骤：
 *  *      1、任务开发：在Spring Bean实例中，开发Job方法；
 *  *      2、注解配置：为Job方法添加注解 "@XxlJob(value="自定义jobhandler名称", init = "JobHandler初始化方法", destroy = "JobHandler销毁方法")"，注解value值对应的是调度中心新建任务的JobHandler属性的值。
 *  *      3、执行日志：需要通过 "XxlJobHelper.log" 打印执行日志；
 *  *      4、任务结果：默认任务结果为 "成功" 状态，不需要主动设置；如有诉求，比如设置任务结果为失败，可以通过 "XxlJobHelper.handleFail/handleSuccess" 自主设置任务结果；
 *  *
 */
@Component
public class TestHandler {
    private Logger logger = LoggerFactory.getLogger(TestHandler.class);

    @XxlJob(value = "TestHandler")
    public boolean demoJobHandler(){
        String param = XxlJobHelper.getJobParam();

        logger.info(XxlJobHelper.getShardIndex() + ", " + XxlJobHelper.getShardTotal());

        logger.info("定时任务开始 job start");
        Long startTime  = System.currentTimeMillis();
        testHandler();
        Long endTime  = System.currentTimeMillis();
        logger.info("定时任务开始 job end, cost: {} ms"+(endTime - startTime));
        return XxlJobHelper.handleSuccess();
    }

    /**
     * 定时任务要执行的业务逻辑
     */
    public void testHandler() {
        System.out.println("hello world");
    }
}

