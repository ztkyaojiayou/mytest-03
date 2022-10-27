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
 * 设备属性
 * </p>
 *
 * @author sifang
 * @since 2022-09-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ModelProperty implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 属性ID
     */
    @TableId(value = "uuid", type = IdType.INPUT)
    private String uuid;

    /**
     * 设备ID
     */
    private String tid;

    /**
     * 属性名称
     */
    private String pname;

    /**
     * 属性说明
     */
    private String pdes;

    /**
     * 业务类型
     */
    private Integer ptype;

    /**
     * 业务类别
     */
    private Integer pclass;

    /**
     * 单位
     */
    private String unit;

    /**
     * 上限
     */
    private String uplimit;

    /**
     * 下限
     */
    private String lowlimit;

    /**
     * 步长
     */
    private String step;

    /**
     * 计算类型
     */
    private Integer ctype;

    /**
     * 属性值
     */
    private String pvalue;


}
