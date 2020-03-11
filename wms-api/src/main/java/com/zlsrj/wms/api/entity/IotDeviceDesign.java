package com.zlsrj.wms.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@TableName("t_iot_device_design")
@ApiModel(value = "IotDeviceDesign对象", description = "设备信息")
public class IotDeviceDesign implements Serializable {

	private static final long serialVersionUID = 2111321331013113253L;

	@ApiModelProperty(value = "系统编号")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "父级系统编号")
	@TableField("parent_id")
	private String parentId;

	@ApiModelProperty(value = "表具用途（1：贸易结算；2：数据监测）")
	@TableField("dev_type")
	private Integer devType;

	@ApiModelProperty(value = "产品型号编号")
	@TableField("dev_ver_id")
	private Integer devVerId;

	@ApiModelProperty(value = "设备出厂编号")
	@TableField("dev_mac_id")
	private String devMacId;

	@ApiModelProperty(value = "模组编号")
	@TableField("imei")
	private String imei;

	@ApiModelProperty(value = "通讯卡号")
	@TableField("imsi")
	private String imsi;

	@ApiModelProperty(value = "设备所属区域编号")
	@TableField("area_id")
	private Integer areaId;

	@ApiModelProperty(value = "用户编号")
	@TableField("cust_id")
	private Long custId;

	@ApiModelProperty(value = "安装地址")
	@TableField("inst_address")
	private String instAddress;

	@ApiModelProperty(value = "地图X坐标")
	@TableField("map_x")
	private Double mapX;

	@ApiModelProperty(value = "地图Y坐标")
	@TableField("map_y")
	private Double mapY;

	@ApiModelProperty(value = "地图显示图标")
	@TableField("map_icon")
	private String mapIcon;

	@ApiModelProperty(value = "供应商编号（租户编号）")
	@TableField("fact_id")
	private Long factId;

	@ApiModelProperty(value = "使用方编号（租户编号）")
	@TableField("user_id")
	private Long userId;

	@ApiModelProperty(value = "设备当前状态")
	@TableField("dev_status")
	private Integer devStatus;

	@ApiModelProperty(value = "注册时间")
	@TableField("dev_reg_time")
	private Date devRegTime;

	@ApiModelProperty(value = "注册人")
	@TableField("dev_register")
	private Long devRegister;

	@ApiModelProperty(value = "注册数据来源")
	@TableField("dev_seg_way")
	private Integer devSegWay;

	@ApiModelProperty(value = "发货时间")
	@TableField("dev_sendout_time")
	private Date devSendoutTime;

	@ApiModelProperty(value = "发货人")
	@TableField("dev_sendouter")
	private Long devSendouter;

	@ApiModelProperty(value = "发货批次")
	@TableField("dev_sendout_batch")
	private Long devSendoutBatch;

	@ApiModelProperty(value = "发货数据来源")
	@TableField("dev_sendout_way")
	private Integer devSendoutWay;

	@ApiModelProperty(value = "入库时间")
	@TableField("dev_putin_time")
	private Date devPutinTime;

	@ApiModelProperty(value = "入库人")
	@TableField("dev_putiner")
	private Long devPutiner;

	@ApiModelProperty(value = "入库数据来源")
	@TableField("dev_putin_way")
	private Integer devPutinWay;

	@ApiModelProperty(value = "安装时间")
	@TableField("dev_inst_time")
	private Date devInstTime;

	@ApiModelProperty(value = "安装人")
	@TableField("dev_inster")
	private Long devInster;

	@ApiModelProperty(value = "安装数据来源")
	@TableField("dev_inst_way")
	private Integer devInstWay;

	@ApiModelProperty(value = "最后一次上传时间")
	@TableField("dev_last_up_time")
	private Date devLastUpTime;

	@ApiModelProperty(value = "最后一次上传ID")
	@TableField("dev_last_up_id")
	private Long devLastUpId;

	@ApiModelProperty(value = "表册编号")
	@TableField("booklet_id")
	private String bookletId;

	@ApiModelProperty(value = "最后一次计费日期")
	@TableField("last_calc_date")
	private Date lastCalcDate;

	@ApiModelProperty(value = "年累计用水量")
	@TableField("yearusenum")
	private BigDecimal yearusenum;

	@ApiModelProperty(value = "最后一次计费止码")
	@TableField("last_calc_code")
	private BigDecimal lastCalcCode;

	@ApiModelProperty(value = "设备编号")
	@TableField("dev_no")
	private String devNo;

	@ApiModelProperty(value = "上传渠道类型（1：移动物联平台）")
	@TableField("channel_type")
	private Integer channelType;


}
