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
 * 事件模板
 * </p>
 *
 * @author sifang
 * @since 2022-09-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ModelEventTmpl implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 服务ID
     */
    @TableId(value = "uuid", type = IdType.INPUT)
    private String uuid;

    /**
     * 模板ID
     */
    private String tid;

    /**
     * 事件名称
     */
    private String ename;

    /**
     * 类别
     */
    private Integer etype;

    /**
     * 事件说明
     */
    private String edes;


}
