package com.zhs.sys.vo;

import com.zhs.sys.domain.Bills;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
public class BillsVo extends Bills {
    private static final long serialVersionUID = 1L;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;

    private Integer page=1;
    private Integer limit=10;
}
