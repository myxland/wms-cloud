package com.zlsrj.wms.api.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "IotDeviceDesign对象", description = "设备信息")
public class IotDeviceDesignVo implements Serializable {

	private static final long serialVersionUID = 2151310141493731281L;

	@ApiModelProperty(value = "系统编号")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "父级系统编号")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long parentId;

	@ApiModelProperty(value = "表具用途（1：贸易结算；2：数据监测）")
	private Integer devType;

	@ApiModelProperty(value = "产品型号编号")
	private Integer devVerId;

	@ApiModelProperty(value = "设备出厂编号")
	private String devMacId;

	@ApiModelProperty(value = "模组编号")
	private String imei;

	@ApiModelProperty(value = "通讯卡号")
	private String imsi;

	@ApiModelProperty(value = "设备所属区域编号")
	private Integer areaId;

	@ApiModelProperty(value = "用户编号")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long custId;

	@ApiModelProperty(value = "安装地址")
	private String instAddress;

	@ApiModelProperty(value = "地图X坐标")
	private Double mapX;

	@ApiModelProperty(value = "地图Y坐标")
	private Double mapY;

	@ApiModelProperty(value = "地图显示图标")
	private String mapIcon;

	@ApiModelProperty(value = "供应商编号（租户编号）")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long factId;

	@ApiModelProperty(value = "使用方编号（租户编号）")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long userId;

	@ApiModelProperty(value = "设备当前状态")
	private Integer devStatus;

	@ApiModelProperty(value = "注册时间")
	private Date devRegTime;

	@ApiModelProperty(value = "注册人")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long devRegister;

	@ApiModelProperty(value = "注册数据来源")
	private Integer devSegWay;

	@ApiModelProperty(value = "发货时间")
	private Date devSendoutTime;

	@ApiModelProperty(value = "发货人")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long devSendouter;

	@ApiModelProperty(value = "发货批次")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long devSendoutBatch;

	@ApiModelProperty(value = "发货数据来源")
	private Integer devSendoutWay;

	@ApiModelProperty(value = "入库时间")
	private Date devPutinTime;

	@ApiModelProperty(value = "入库人")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long devPutiner;

	@ApiModelProperty(value = "入库数据来源")
	private Integer devPutinWay;

	@ApiModelProperty(value = "安装时间")
	private Date devInstTime;

	@ApiModelProperty(value = "安装人")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long devInster;

	@ApiModelProperty(value = "安装数据来源")
	private Integer devInstWay;

	@ApiModelProperty(value = "最后一次上传时间")
	private Date devLastUpTime;

	@ApiModelProperty(value = "最后一次上传ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long devLastUpId;

	@ApiModelProperty(value = "表册编号")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long bookletId;

	@ApiModelProperty(value = "最后一次计费日期")
	private Date lastCalcDate;

	@ApiModelProperty(value = "年累计用水量")
	private BigDecimal yearusenum;

	@ApiModelProperty(value = "最后一次计费止码")
	private BigDecimal lastCalcCode;

	@ApiModelProperty(value = "设备编号")
	private String devNo;

	@ApiModelProperty(value = "上传渠道类型（1：移动物联平台）")
	private Integer channelType;

}
