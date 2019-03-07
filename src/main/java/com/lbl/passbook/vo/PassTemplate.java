package com.lbl.passbook.vo;

import com.lbl.passbook.constant.ErrorCode;
import com.lbl.passbook.dao.MerchantsDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 优惠券接口字段
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassTemplate {

    /** 所属商户id */
    private Integer id;

    /** 优惠券标题 */
    private String title;

    /** 优惠券摘要 */
    private String summary;

    /** 优惠券描述 */
    private String desc;

    /** 最大个数限制 */
    private Long limit;

    /** 优惠券是否有token */
    private Boolean hasToken;

    /** 优惠券背景色 */
    private Integer background;

    /** 优惠券开始使用时间 */
    private Date start;

    /** 优惠券结束使用时间 */
    private Date end;


    /**
     * <h2>校验优惠券对象的有效性</h2>
     * @param merchantsDao {@link MerchantsDao}
     * @return {@link ErrorCode}
     * */
    public ErrorCode validate(MerchantsDao merchantsDao) {

        if (null == merchantsDao.findById(id)) {
            return ErrorCode.MERCHANTS_NOT_EXIST;
        }

        return ErrorCode.SUCCESS;
    }

}
