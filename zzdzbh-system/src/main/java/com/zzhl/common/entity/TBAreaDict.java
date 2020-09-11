package com.zzhl.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 中华人民共和国国家标准 GB/T 2260-2007 行政区划代码 字典表
 * </p>
 *
 * @author tanggewei
 * @since 2020-09-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TBAreaDict implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 行政区域编码
     */
    private String code;

    /**
     * 行政区域名称
     */
    private String areaName;

    /**
     * 全称/显示名称
     */
    private String fullName;

    /**
     * 父编码(默认:0 顶级编码, 暂时未做级联)
     */
    private String parentCode;

    /**
     * 地区类型代码
     */
    private String areaTypeCode;

    /**
     * 地区类型名字
     */
    private String areaTypeName;

    /**
     * 逻辑删除（0.正常 1.已删除）
     */
    @TableLogic
    private Integer del;


}
