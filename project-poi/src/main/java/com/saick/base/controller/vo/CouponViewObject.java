package com.saick.base.controller.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Controller 层返回的实体VO
 * 
 * @author Liubao
 * @2014年12月14日
 * 
 */
public class CouponViewObject implements Serializable {

    private static final long serialVersionUID = 6027416721535882276L;

    private Long id;
    private String code;
    private String name;
    private Integer fitToMinKm;
    private Integer fitToMaxKm;
    private Integer fitToMinDaysUsed;
    private Integer fitToMaxDaysUsed;
    private String fitToEmissionVolume;
    private String fitToVehicleScope;
    private String fitToCity;
    private Timestamp beginDatetime;
    private Timestamp endDatetime;
    private Integer validInDays;
    private Float moneyAmount;
    private String color1;
    private String color2;
    private String ruleBrief;
    private String description;
    private Boolean actived;
    private Boolean deleted;
    private Timestamp createdDatetime;
    private Timestamp updatedDatetime;
    private String ruleDetail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFitToMinKm() {
        return fitToMinKm;
    }

    public void setFitToMinKm(Integer fitToMinKm) {
        this.fitToMinKm = fitToMinKm;
    }

    public Integer getFitToMaxKm() {
        return fitToMaxKm;
    }

    public void setFitToMaxKm(Integer fitToMaxKm) {
        this.fitToMaxKm = fitToMaxKm;
    }

    public Integer getFitToMinDaysUsed() {
        return fitToMinDaysUsed;
    }

    public void setFitToMinDaysUsed(Integer fitToMinDaysUsed) {
        this.fitToMinDaysUsed = fitToMinDaysUsed;
    }

    public Integer getFitToMaxDaysUsed() {
        return fitToMaxDaysUsed;
    }

    public void setFitToMaxDaysUsed(Integer fitToMaxDaysUsed) {
        this.fitToMaxDaysUsed = fitToMaxDaysUsed;
    }

    public String getFitToEmissionVolume() {
        return fitToEmissionVolume;
    }

    public void setFitToEmissionVolume(String fitToEmissionVolume) {
        this.fitToEmissionVolume = fitToEmissionVolume;
    }

    public String getFitToVehicleScope() {
        return fitToVehicleScope;
    }

    public void setFitToVehicleScope(String fitToVehicleScope) {
        this.fitToVehicleScope = fitToVehicleScope;
    }

    public String getFitToCity() {
        return fitToCity;
    }

    public void setFitToCity(String fitToCity) {
        this.fitToCity = fitToCity;
    }

    public Timestamp getBeginDatetime() {
        return beginDatetime;
    }

    public void setBeginDatetime(Timestamp beginDatetime) {
        this.beginDatetime = beginDatetime;
    }

    public Timestamp getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(Timestamp endDatetime) {
        this.endDatetime = endDatetime;
    }

    public Integer getValidInDays() {
        return validInDays;
    }

    public void setValidInDays(Integer validInDays) {
        this.validInDays = validInDays;
    }

    public Float getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(Float moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public String getColor1() {
        return color1;
    }

    public void setColor1(String color1) {
        this.color1 = color1;
    }

    public String getColor2() {
        return color2;
    }

    public void setColor2(String color2) {
        this.color2 = color2;
    }

    public String getRuleBrief() {
        return ruleBrief;
    }

    public void setRuleBrief(String ruleBrief) {
        this.ruleBrief = ruleBrief;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActived() {
        return actived;
    }

    public void setActived(Boolean actived) {
        this.actived = actived;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Timestamp getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(Timestamp createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public Timestamp getUpdatedDatetime() {
        return updatedDatetime;
    }

    public void setUpdatedDatetime(Timestamp updatedDatetime) {
        this.updatedDatetime = updatedDatetime;
    }

    public String getRuleDetail() {
        return ruleDetail;
    }

    public void setRuleDetail(String ruleDetail) {
        this.ruleDetail = ruleDetail;
    }

    @Override
    public String toString() {
        return "CouponViewObject [id=" + id + ", code=" + code + ", name="
                + name + ", fitToMinKm=" + fitToMinKm + ", fitToMaxKm="
                + fitToMaxKm + ", fitToMinDaysUsed=" + fitToMinDaysUsed
                + ", fitToMaxDaysUsed=" + fitToMaxDaysUsed
                + ", fitToEmissionVolume=" + fitToEmissionVolume
                + ", fitToVehicleScope=" + fitToVehicleScope + ", fitToCity="
                + fitToCity + ", beginDatetime=" + beginDatetime
                + ", endDatetime=" + endDatetime + ", validInDays="
                + validInDays + ", moneyAmount=" + moneyAmount + ", color1="
                + color1 + ", color2=" + color2 + ", ruleBrief=" + ruleBrief
                + ", description=" + description + ", actived=" + actived
                + ", deleted=" + deleted + ", createdDatetime="
                + createdDatetime + ", updatedDatetime=" + updatedDatetime
                + ", ruleDetail=" + ruleDetail + "]";
    }

}
