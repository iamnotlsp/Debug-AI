package com.lsp.pojo.question.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionMistake implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String userPhone;
    private Integer questionId;
    private String createTime;
    @TableLogic(value = "0",delval = "1")
    private Integer isDeleted;

    public QuestionMistake(String userPhone, Integer questionId) {
        this.userPhone = userPhone;
        this.questionId = questionId;
    }
}
