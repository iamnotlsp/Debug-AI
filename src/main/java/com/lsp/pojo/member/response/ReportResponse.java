package com.lsp.pojo.member.response;

import com.lsp.pojo.member.response.subclass.WeekAchievementInfo;
import com.lsp.pojo.member.response.subclass.WeekReportInfo;
import com.lsp.pojo.member.response.subclass.WeekScoreInfo;
import com.lsp.pojo.member.response.subclass.WeekStudyInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: LinShanPeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponse {
    private WeekReportInfo weekReportInfo;
    private WeekScoreInfo weekScoreInfo;
    private List<WeekStudyInfo> weekStudyInfos;
    private List<WeekAchievementInfo> weekAchievementInfos;
}
