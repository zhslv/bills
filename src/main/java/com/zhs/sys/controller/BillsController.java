package com.zhs.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhs.common.DataGridView;
import com.zhs.common.ResultObj;
import com.zhs.sys.domain.Bills;
import com.zhs.sys.domain.Billtype;
import com.zhs.sys.service.BillsService;
import com.zhs.sys.service.BilltypeService;
import com.zhs.sys.vo.BillsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZHS
 * @since 2020-03-11
 */
@Controller
@RequestMapping("/bills")
public class BillsController {
    @Autowired
    private BillsService billService;
    @Autowired
    private BilltypeService billTypeService;
    /**
     * 跳转到系统主页
     */
    @RequestMapping("toBillsList")
    public String toBillsList() {
        return "list";
    }
    /**
     * 加载帐单数据
     */
    @RequestMapping("loadAllBills")
    @ResponseBody
    public DataGridView loadAllBills(BillsVo billsVo) {
        IPage<Bills> page=new Page<>(billsVo.getPage(), billsVo.getLimit());
        QueryWrapper<Bills> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq(null!=billsVo.getTypeid()&&billsVo.getTypeid()!=0, "typeid", billsVo.getTypeid());
        queryWrapper.ge(billsVo.getStartDate()!=null,"billtime", billsVo.getStartDate());
        queryWrapper.le(billsVo.getEndDate()!=null, "billtime",billsVo.getEndDate());
        queryWrapper.orderByDesc("billtime");
        billService.page(page, queryWrapper);

        List<Bills> records = page.getRecords();
        for (Bills bills : records) {
            Billtype billtype = this.billTypeService.getById(bills.getTypeid());
            bills.setTypeName(billtype.getName());
        }
        return new DataGridView(page.getTotal(), records);
    }

    /**
     * 添加账单
     */
    @RequestMapping("addBills")
    @ResponseBody
    public ResultObj addBills(BillsVo billsVo) {
        try {
            this.billService.save(billsVo);
            return new ResultObj(200, "录入成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultObj(-1, "录入失败");
        }
    }


}

