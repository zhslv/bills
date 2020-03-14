package com.zhs.sys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZHS
 * @since 2020-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Bills implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String title;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date billtime;

    private Integer typeid;

    @TableField(exist=false)
    private String typeName;//添加数据库不存在的属性

    private Double price;

    private String remark;


}
