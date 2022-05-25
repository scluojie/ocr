package com.hxcy.ocr.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.metadata.BaseRowModel;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;

/**
 * @author kevin
 * @date 2022/5/23
 * @desc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("vmware")
@ColumnWidth(20)
public class Vmware {
    @TableId(value = "vm_id",type = IdType.INPUT)
    @ExcelProperty(value = "vm_id", index = 0)
    private String vmId;
    @TableField(value = "group_id",fill = FieldFill.INSERT_UPDATE)
    @ExcelProperty(value = "group_id", index = 1)
    private Long groupId;
    @TableField(value="gst",fill = FieldFill.INSERT_UPDATE)
    @ExcelProperty(value = "gst", index = 2)
    private Double gst;
    @TableField(value="gmt",fill = FieldFill.INSERT_UPDATE)
    @ExcelProperty(value = "gmt", index = 3)
    private Double gmt;
    @TableField(value="bnb",fill = FieldFill.INSERT_UPDATE)//,fill = FieldFill.INSERT_UPDATE
    @ExcelProperty(value = "bnb", index = 4)
    private Double bnb;
    @ExcelProperty(value = "sol", index = 5)
    private Double sol;
    @ExcelProperty(value = "date", index = 6)
    private String date;
    @ExcelProperty(value = "chain_name", index = 7)
    private String chainName;
    @ExcelProperty(value = "earn", index = 8)
    private String earn;
    @ExcelProperty(value = "energy", index = 9)
    private String energy;
}
