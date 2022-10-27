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
 * 设备类型
 * </p>
 *
 * @author sifang
 * @since 2022-09-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ModelDeviceType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 类型编号
     */
    @TableId(value = "tid", type = IdType.INPUT)
    private Integer tid;

    /**
     * 类型名称
     */
    private String typename;

    /**
     * 父类型
     */
    private Integer pid;

    /**
     * 描述
     */
    private String ddesc;

    /**
     * 编码
     */
    private String code;


}
