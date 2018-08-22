package com.fenfei.pets.models;

import lombok.Data;

import java.util.Date;

/**
 * mot事件因子 (fact)
 */
@Data
public class MotMessage {

    /* 会员模块 以 user开头 */
    //会员注册 - 注册入口
    private String userRegEnter;
    private String userRegPlazaId;

    //会员生日 - 会员生日时间 yyyy-MM-dd
    private Date userBirthday;

    //会员访问 - 访问时间 yyyy-MM-dd HH:mm:ss
    private Date userVisitDate;
    private String userEnterModule;

    //会员标签 - 会员打标时间 yyyy-MM-dd HH:mm:ss
    private Date userMarkingDate;

    /*小程序模块 以applets开头*/
    //登录入口
    private String appletsLoginEnter;
    private Date appletsLoginDate;

    //注册入口
    private String appletsRegEnter;
    private Date appletsRegDate;

    //模块访问
    private String appletsVisitModule;
    private Date appletsVisitDate;
    private Long appletsVisitCount;

    //领取成功
    private String appletsReceiveModule;
    private Date appletsReceiveDate;

    //购买成功
    private String appletsBuyModule;
    private Date appletsBuyDate;
    private Double appletsBuyAmount;

    //核销
    private String appletsAccountingModule;
    private Date appletsAccountingDate;
    private Long appletsAccountingCount;
    private Double appletsAccountingAmount;

    //活动参与
    //todo 待定

    //会员转发
    private Date appletsShareDate;
    private Long appletsShareCount;
    private Long appletsShareCountPerDay;


    /*停车 以Car 开头*/
    //车辆入场
    private Date carEntryDate;

    //绑定车牌
    private Date carCodeBindDate;

    //车辆缴费-在线缴费
    private Date carFeePayDate;
    private Double carFeePayAmount;
    private Long carFeePayCount;
    private Double carFeePayTotal;

    //会员开通无感支付
    private Date carOpenNoPassPayDate;

    //车辆缴费-无感支付
    private Date carNoPassPayDate;
    private Double carNoPassPayAmount;
    private Long carNoPassPayCount;
    private Double carNoPassPayTotal;

    //车辆出场
    private Date carAppearanceDate;

}
