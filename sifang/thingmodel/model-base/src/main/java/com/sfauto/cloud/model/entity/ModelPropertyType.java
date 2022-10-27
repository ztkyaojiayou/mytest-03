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
 * 属性类型
 * </p>
 *
 * @author sifang
 * @since 2022-09-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ModelPropertyType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 类型编号
     */
    private Integer tid;

    /**
     * 属性类型名称
     */
    private String typename;

    /**
     * 父类型
     */
    private Integer pid;

    /**
     * 描述
     */
    private String tdesc;

    /**
     * 编码
     */
    private String code;


}
