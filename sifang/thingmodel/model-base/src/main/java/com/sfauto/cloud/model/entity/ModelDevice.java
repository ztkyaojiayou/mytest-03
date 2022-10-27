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
 * 设备
 * </p>
 *
 * @author sifang
 * @since 2022-09-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ModelDevice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 设备ID
     */
    @TableId(value = "uuid", type = IdType.INPUT)
    private String uuid;

    /**
     * 模板ID
     */
    private String tid;

    /**
     * 设备名称
     */
    private String dname;

    /**
     * 设备类型
     */
    private Integer dtype;

    /**
     * 父设备ID
     */
    private String pid;


}
