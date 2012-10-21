/**
 * 
 */
package com.yd.etravel.util;

/**
 * @author shanaka
 * 
 */
public class IpgUtil {

	private String virtualPaymentClientURL;
	private String vpc_Version;
	private String vpc_Command;
	private String vpc_AccessCode;
	private String vpc_Merchant;
	private String vpc_OrderInfo;
	private String vpc_ReturnURL;
	private String vpc_Locale;
	private String secureSecret;
	private String successCode;

	public String getVirtualPaymentClientURL() {
		return this.virtualPaymentClientURL;
	}

	public void setVirtualPaymentClientURL(final String virtualPaymentClientURL) {
		this.virtualPaymentClientURL = virtualPaymentClientURL;
	}

	public String getVpc_Version() {
		return this.vpc_Version;
	}

	public void setVpc_Version(final String vpc_Version) {
		this.vpc_Version = vpc_Version;
	}

	public String getVpc_Command() {
		return this.vpc_Command;
	}

	public void setVpc_Command(final String vpc_Command) {
		this.vpc_Command = vpc_Command;
	}

	public String getVpc_AccessCode() {
		return this.vpc_AccessCode;
	}

	public void setVpc_AccessCode(final String vpc_AccessCode) {
		this.vpc_AccessCode = vpc_AccessCode;
	}

	public String getVpc_Merchant() {
		return this.vpc_Merchant;
	}

	public void setVpc_Merchant(final String vpc_Merchant) {
		this.vpc_Merchant = vpc_Merchant;
	}

	public String getVpc_OrderInfo() {
		return this.vpc_OrderInfo;
	}

	public void setVpc_OrderInfo(final String vpc_OrderInfo) {
		this.vpc_OrderInfo = vpc_OrderInfo;
	}

	public String getVpc_ReturnURL() {
		return this.vpc_ReturnURL;
	}

	public void setVpc_ReturnURL(final String vpc_ReturnURL) {
		this.vpc_ReturnURL = vpc_ReturnURL;
	}

	public String getVpc_Locale() {
		return this.vpc_Locale;
	}

	public void setVpc_Locale(final String vpc_Locale) {
		this.vpc_Locale = vpc_Locale;
	}

	public String getSecureSecret() {
		return this.secureSecret;
	}

	public void setSecureSecret(final String secureSecret) {
		this.secureSecret = secureSecret;
	}

	public String getSuccessCode() {
		return this.successCode;
	}

	public void setSuccessCode(final String successCode) {
		this.successCode = successCode;
	}

}
