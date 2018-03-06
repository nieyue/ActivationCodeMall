package com.nieyue.weixin.bean;

/**
 * 微信公众号统一下单类
 * 
 * @author yy
 * 
 */
public class UnifiedOrder {
	/**
	 * 公众账号ID
	 */
	private String appid;
	/**
	 * 商户号
	 */
	private String mchId;
	/**
	 * 设备号 必填：否
	 */
	private String deviceInfo;
	/**
	 * 随机字符串
	 */
	private String nonceStr;
	/**
	 * 商品描述
	 */
	private String body;
	/**
	 * 商品详情 必填：否
	 */
	private String detail;
	/**
	 * 附加数据 必填：否
	 */
	private String attach;
	/**
	 * 微信订单号
	 */
	private String transactionId;
	/**
	 * 商户订单号
	 */
	private String outTradeNo;
	/**
	 * 货币类型 必填：否
	 */
	private String feeType;
	/**
	 * 总金额
	 */
	private Integer totalFee;
	/**
	 * 终端IP
	 */
	private String spbillCreateIp;
	/**
	 * 交易起始时间 必填：否
	 */
	private String timeStart;
	/**
	 * 交易结束时间 必填：否
	 */
	private String timeExpire;
	/**
	 * 商品标记 必填：否
	 */
	private String goodsTag;
	/**
	 * 通知地址
	 */
	private String notifyUrl;
	/**
	 * 交易类型
	 */
	private String tradeType;
	/**
	 * 商品ID 必填：否
	 */
	private String productId;
	/**
	 * 指定支付方式 必填：否
	 */
	private String limitPay;
	/**
	 * 用户标识
	 */
	private String openid;
	/**
	 * 商户退款单号
	 */
	private String outRefundNo;
	/**
	 * 商户退款金额
	 */
	private Integer refundFee;
	/**
	 * 操作员账号（默认为商户号）
	 */
	private String opUserId;
	/**
	 * s 签名
	 */
	private String sign;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public Integer getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}

	public String getSpbillCreateIp() {
		return spbillCreateIp;
	}

	public void setSpbillCreateIp(String spbillCreateIp) {
		this.spbillCreateIp = spbillCreateIp;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getTimeExpire() {
		return timeExpire;
	}

	public void setTimeExpire(String timeExpire) {
		this.timeExpire = timeExpire;
	}

	public String getGoodsTag() {
		return goodsTag;
	}

	public void setGoodsTag(String goodsTag) {
		this.goodsTag = goodsTag;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getLimitPay() {
		return limitPay;
	}

	public void setLimitPay(String limitPay) {
		this.limitPay = limitPay;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getOutRefundNo() {
		return outRefundNo;
	}

	public void setOutRefundNo(String outRefundNo) {
		this.outRefundNo = outRefundNo;
	}

	public Integer getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(Integer refundFee) {
		this.refundFee = refundFee;
	}

	public String getOpUserId() {
		return opUserId;
	}

	public void setOpUserId(String opUserId) {
		this.opUserId = opUserId;
	}

	@Override
	public String toString() {
		return "UnifiedOrder [appid=" + appid + ", mchId=" + mchId
				+ ", deviceInfo=" + deviceInfo + ", nonceStr=" + nonceStr
				+ ", body=" + body + ", detail=" + detail + ", attach="
				+ attach + ", transactionId=" + transactionId + ", outTradeNo="
				+ outTradeNo + ", feeType=" + feeType + ", totalFee="
				+ totalFee + ", spbillCreateIp=" + spbillCreateIp
				+ ", timeStart=" + timeStart + ", timeExpire=" + timeExpire
				+ ", goodsTag=" + goodsTag + ", notifyUrl=" + notifyUrl
				+ ", tradeType=" + tradeType + ", productId=" + productId
				+ ", limitPay=" + limitPay + ", openid=" + openid
				+ ", outRefundNo=" + outRefundNo + ", refundFee=" + refundFee
				+ ", opUserId=" + opUserId + ", sign=" + sign + "]";
	}

}
