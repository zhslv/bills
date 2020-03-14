package com.zhs.sys.controller;


import com.zhs.common.DataGridView;
import com.zhs.sys.service.BilltypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZHS
 * @since 2020-03-11
 */
@RestController
@RequestMapping("/billtype")
public class BilltypeController {
    @Autowired
    private BilltypeService billtypeService;


    @RequestMapping("loadAllBillType")
    public DataGridView loadAllBillType() {
        return new DataGridView(0L, billtypeService.list());
    }
}

