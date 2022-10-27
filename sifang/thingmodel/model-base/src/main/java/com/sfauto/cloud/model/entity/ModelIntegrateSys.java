/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.sfauto.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 对接系统
 * </p>
 *
 * @author sifang
 * @since 2022-09-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ModelIntegrateSys implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 系统标识;与MQTT主题中相同
     */
    @TableId(value = "sid", type = IdType.INPUT)
    private String sid;

    /**
     * 系统名称
     */
    private String sname;

    /**
     * 系统描述
     */
    private String sdes;

    /**
     * 所有设备描述文件;JSON或XML
     */
    private String model;


}
