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
 * 属性点表对应
 * </p>
 *
 * @author sifang
 * @since 2022-09-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ModelPropertyPoint implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 属性ID
     */
    @TableId(value = "proid", type = IdType.INPUT)
    private String proid;

    /**
     * 点ID
     */
    private String pid;

    /**
     * 系统ID
     */
    private Integer sid;


}
