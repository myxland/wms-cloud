package com.zlsrj.wms.mbg.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 租户
 * </p>
 *
 * @author wms
 * @since 2019-10-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@TableName("t_op_tenant_info")
public class TenantInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 租户编号
     */
    private Long id;

    /**
     * 租户名称
     */
    private String tenantName;

    /**
     * 显示名称
     */
    private String displayName;

    /**
     * 省
     */
    private String tenantProvice;

    /**
     * 市
     */
    private String tenantCity;

    /**
     * 区县
     */
    private String tenantArea;

    /**
     * 联系地址
     */
    private String tenantAddress;

    /**
     * 联系人
     */
    private String tenantLinkman;

    /**
     * 手机号码
     */
    private String tenantMobile;

    /**
     * 单位电话
     */
    private String tenantTel;

    /**
     * 邮箱
     */
    private String tenantEmail;

    /**
     * QQ号码
     */
    private String tenantQq;

    /**
     * 租户类型（使用单位/供应单位/内部运营)
     */
    private Integer tenantType;

    /**
     * 租户状态（正式/试用）
     */
    private Integer tenantStatus;

    /**
     * 注册时间
     */
    private Date regTime;

    /**
     * 到期日期
     */
    private Date endDate;

    /**
     * 税号
     */
    private String creditNumber;

    /**
     * 开票地址
     */
    private String invoiceAddress;

    /**
     * 开户行行号
     */
    private String bankNo;

    /**
     * 开户行名称
     */
    private String bankName;

    /**
     * 开户账号
     */
    private String accountNo;

    /**
     * 是否启用部分缴费（启用/不启用）
     */
    private String partChargeOn;

    /**
     * 是否启用违约金（启用/不启用）
     */
    private String overDuefineOn;

    /**
     * 违约金宽限天数（从计费之日开始）
     */
    private Integer overDuefineDay;

    /**
     * 违约金每天收取比例
     */
    private BigDecimal overDuefineRatio;

    /**
     * 违约金封顶比例（与欠费金额相比）
     */
    private BigDecimal overDuefineTopRatio;

    /**
     * 预存抵扣方式（抄表后即时抵扣/人工发起抵扣）
     */
    private Integer ycdkType;


}
