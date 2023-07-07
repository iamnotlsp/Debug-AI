package com.lsp.pojo.score.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreDetail {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String userPhone;
    private Integer loginScore;
    private Integer articleScore;
    private Integer viewScore;
    private Integer answerScore;
    private Integer pkScore;
    private Integer aiScore;
    private Integer expenseScore;
    private java.sql.Timestamp createTime;

    public ScoreDetail(String userPhone) {
        this.userPhone = userPhone;
    }
}
