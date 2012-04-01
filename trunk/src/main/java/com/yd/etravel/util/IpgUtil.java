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
	return virtualPaymentClientURL;
    }

    public void setVirtualPaymentClientURL(String virtualPaymentClientURL) {
	this.virtualPaymentClientURL = virtualPaymentClientURL;
    }

    public String getVpc_Version() {
	return vpc_Version;
    }

    public void setVpc_Version(String vpc_Version) {
	this.vpc_Version = vpc_Version;
    }

    public String getVpc_Command() {
	return vpc_Command;
    }

    public void setVpc_Command(String vpc_Command) {
	this.vpc_Command = vpc_Command;
    }

    public String getVpc_AccessCode() {
	return vpc_AccessCode;
    }

    public void setVpc_AccessCode(String vpc_AccessCode) {
	this.vpc_AccessCode = vpc_AccessCode;
    }

    public String getVpc_Merchant() {
	return vpc_Merchant;
    }

    public void setVpc_Merchant(String vpc_Merchant) {
	this.vpc_Merchant = vpc_Merchant;
    }

    public String getVpc_OrderInfo() {
	return vpc_OrderInfo;
    }

    public void setVpc_OrderInfo(String vpc_OrderInfo) {
	this.vpc_OrderInfo = vpc_OrderInfo;
    }

    public String getVpc_ReturnURL() {
	return vpc_ReturnURL;
    }

    public void setVpc_ReturnURL(String vpc_ReturnURL) {
	this.vpc_ReturnURL = vpc_ReturnURL;
    }

    public String getVpc_Locale() {
	return vpc_Locale;
    }

    public void setVpc_Locale(String vpc_Locale) {
	this.vpc_Locale = vpc_Locale;
    }

    public String getSecureSecret() {
	return secureSecret;
    }

    public void setSecureSecret(String secureSecret) {
	this.secureSecret = secureSecret;
    }

    public String getSuccessCode() {
	return successCode;
    }

    public void setSuccessCode(String successCode) {
	this.successCode = successCode;
    }

}
