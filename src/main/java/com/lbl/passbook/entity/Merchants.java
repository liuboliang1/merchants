package com.lbl.passbook.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 * 商家实体类
 */
@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Merchants {

    /** 主键ID */
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,length = 10)
    private Integer id;

    /** 商家名称 */
    @Column(name = "name",nullable = false)
    private String name;

    /** 商家logo */
    @Column(name = "logoUrl",unique = true, nullable = false)
    private String logoUrl;

    /** 商家营业执照 */
    @Column(name = "business_license_url",nullable = false)
    private String businessLicenseUrl;

    /** 电话号码 */
    @Column(name = "phone",nullable = false)
    private String phone;

    /** 商家地址 */
    @Column(name = "address",nullable = false)
    private String address;

    /** 商户是否通过审核 */
    @Column(name = "is_audit",nullable = false)
    private Boolean isAudit = false;

}
