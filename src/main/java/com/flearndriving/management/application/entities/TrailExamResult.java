package com.flearndriving.management.application.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "trail_exam_result")
public class TrailExamResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trail_exam_result_id")
    private Long trailExamResultId;

    @Column(name = "point")
    private double point;

    @Column(name = "is_pass")
    private boolean isPass;

    @Column(name = "time_exam_start")
    private String timeExamStart;

    @Column(name = "time_exam_end")
    private String timeExamEnd;

    @JsonManagedReference
    @OneToMany(mappedBy = "trailExamResult", cascade = CascadeType.ALL)
    private List<HistoryAnswer> listHistoryAnswer;

    public Long getTrailExamResultId() {
        return trailExamResultId;
    }

    public void setTrailExamResultId(Long trailExamResultId) {
        this.trailExamResultId = trailExamResultId;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public boolean isPass() {
        return isPass;
    }

    public void setPass(boolean isPass) {
        this.isPass = isPass;
    }

    public String getTimeExamStart() {
        return timeExamStart;
    }

    public void setTimeExamStart(String timeExamStart) {
        this.timeExamStart = timeExamStart;
    }

    public String getTimeExamEnd() {
        return timeExamEnd;
    }

    public void setTimeExamEnd(String timeExamEnd) {
        this.timeExamEnd = timeExamEnd;
    }

    public List<HistoryAnswer> getListHistoryAnswer() {
        return listHistoryAnswer;
    }

    public void setListHistoryAnswer(List<HistoryAnswer> listHistoryAnswer) {
        this.listHistoryAnswer = listHistoryAnswer;
    }

}
