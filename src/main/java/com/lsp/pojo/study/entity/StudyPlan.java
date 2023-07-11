package com.lsp.pojo.study.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyPlan implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String userPhone;
    private String planName;
    private String startTime;
    private String endTime;
    private Integer planState;
    private String createTime;
    @TableField(update = "now()")
    private String updateTime;
    private Integer isDeleted;

}
