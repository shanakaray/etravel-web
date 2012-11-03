/**
 * 
 */
package com.yd.etravel.web.booking;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.util.ServiceHelper;
import com.yd.etravel.util.StringUtils;
import com.yd.etravel.web.common.BaseForm;

/**
 * @author com.yd.etravel.web.booking.IpgForm
 * 
 */
public class IpgForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8901449604861662336L;
	static final char[] HEX_TABLE = new char[] { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	static final String SECURE_SECRET = ServiceHelper.getInstance()
			.getIpgUtil().getSecureSecret();

	/*
	 * This method takes a byte array and returns a string of its contents
	 * 
	 * @param input - byte array containing the input data @return String
	 * containing the output String
	 */
	static String hex(final byte[] input) {
		// create a StringBuffer 2x the size of the hash array
		final StringBuffer sb = new StringBuffer(input.length * 2);

		// retrieve the byte array data, convert it to hex
		// and add it to the StringBuffer
		for (final byte element : input) {
			sb.append(HEX_TABLE[(element >> 4) & 0xf]);
			sb.append(HEX_TABLE[element & 0xf]);
		}
		return sb.toString();
	}

	/*
	 * This method takes a data String and returns a predefined value if empty
	 * If data Sting is null, returns string "No Value Returned", else returns
	 * input
	 * 
	 * @param in String containing the data String @return String containing the
	 * output String
	 */
	private static String null2unknown(final String in) {
		if ((in == null) || (in.length() == 0)) {
			return "No Value Returned";
		} else {
			return in;
		}
	} // null2unknown()

	private String AgainLink, vpc_Amount, vpc_Locale, vpc_BatchNo, vpc_Command,
			vpc_Message, vpc_Version, vpc_Card, vpc_OrderInfo, vpc_ReceiptNo,
			vpc_Merchant, vpc_MerchTxnRef, vpc_AuthorizeId, vpc_TransactionNo,
			vpc_AcqResponseCode, vpc_TxnResponseCode, vpc_CSCResultCode,
			vpc_CSCRequestCode, vpc_AcqCSCRespCode, vpc_AVS_City,
			vpc_AVS_Country, vpc_AVS_Street01, vpc_AVS_PostCode,
			vpc_AVS_StateProv, vpc_AVSResultCode, vpc_AVSRequestCode,
			vpc_AcqAVSRespCode, vpc_VerType, vpc_VerStatus, vpc_VerToken,
			vpc_VerSecurityLevel, vpc_3DSenrolled, vpc_3DSXID, vpc_3DSECI,
			vpc_3DSstatus;

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public IpgForm() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * This function uses the QSI AVS Result Code retrieved from the Digital
	 * Receipt and returns an appropriate description for this code.
	 * 
	 * @param vAVSResultCode
	 *            String containing the vpc_AVSResultCode
	 * @return description String containing the appropriate description
	 */
	private String displayAVSResponse(final String vAVSResultCode) {

		String result = "";
		if ((vAVSResultCode != null) || (vAVSResultCode.length() == 0)) {

			if (vAVSResultCode.equalsIgnoreCase("Unsupported")
					|| vAVSResultCode.equalsIgnoreCase("No Value Returned")) {
				result = "AVS not supported or there was no AVS data provided";
			} else {
				// Java cannot switch on a string so turn everything to a char
				final char input = vAVSResultCode.charAt(0);

				switch (input) {
				case 'X':
					result = "Exact match - address and 9 digit ZIP/postal code";
					break;
				case 'Y':
					result = "Exact match - address and 5 digit ZIP/postal code";
					break;
				case 'S':
					result = "Service not supported or address not verified (international transaction)";
					break;
				case 'G':
					result = "Issuer does not participate in AVS (international transaction)";
					break;
				case 'A':
					result = "Address match only";
					break;
				case 'W':
					result = "9 digit ZIP/postal code matched, Address not Matched";
					break;
				case 'Z':
					result = "5 digit ZIP/postal code matched, Address not Matched";
					break;
				case 'R':
					result = "Issuer system is unavailable";
					break;
				case 'U':
					result = "Address unavailable or not verified";
					break;
				case 'E':
					result = "Address and ZIP/postal code not provided";
					break;
				case 'N':
					result = "Address and ZIP/postal code not matched";
					break;
				case '0':
					result = "AVS not requested";
					break;
				default:
					result = "Unable to be determined";
				}
			}
		} else {
			result = "null response";
		}
		return result;
	}

	// ----------------------------------------------------------------------------

	/**
	 * This function uses the QSI CSC Result Code retrieved from the Digital
	 * Receipt and returns an appropriate description for this code.
	 * 
	 * @param vCSCResultCode
	 *            String containing the vpc_CSCResultCode
	 * @return description String containing the appropriate description
	 */
	private String displayCSCResponse(final String vCSCResultCode) {

		String result = "";
		if ((vCSCResultCode != null) || (vCSCResultCode.length() == 0)) {

			if (vCSCResultCode.equalsIgnoreCase("Unsupported")
					|| vCSCResultCode.equalsIgnoreCase("No Value Returned")) {
				result = "CSC not supported or there was no CSC data provided";
			} else {
				// Java cannot switch on a string so turn everything to a char
				final char input = vCSCResultCode.charAt(0);

				switch (input) {
				case 'M':
					result = "Exact code match";
					break;
				case 'S':
					result = "Merchant has indicated that CSC is not present on the card (MOTO situation)";
					break;
				case 'P':
					result = "Code not processed";
					break;
				case 'U':
					result = "Card issuer is not registered and/or certified";
					break;
				case 'N':
					result = "Code invalid or not matched";
					break;
				default:
					result = "Unable to be determined";
				}
			}

		} else {
			result = "null response";
		}
		return result;
	}

	public String getAgainLink() {
		return this.AgainLink;
	}

	// ----------------------------------------------------------------------------

	public String getResponseDesc() {
		return getResponseDescription(this.vpc_TxnResponseCode);
	}

	/*
	 * This function uses the returned status code retrieved from the Digital
	 * Response and returns an appropriate description for the code
	 * 
	 * @param vResponseCode String containing the vpc_TxnResponseCode @return
	 * description String containing the appropriate description
	 */
	private String getResponseDescription(final String vResponseCode) {

		String result = "";

		// check if a single digit response code
		if (vResponseCode.trim().length() > 0) {

			// Java cannot switch on a string so turn everything to a char
			final char input = vResponseCode.trim().charAt(0);

			switch (input) {
			case '0':
				result = "Transaction Successful";
				break;
			case '1':
				result = "Unknown Error";
				break;
			case '2':
				result = "Bank Declined Transaction";
				break;
			case '3':
				result = "No Reply from Bank";
				break;
			case '4':
				result = "Expired Card";
				break;
			case '5':
				result = "Insufficient Funds";
				break;
			case '6':
				result = "Error Communicating with Bank";
				break;
			case '7':
				result = "Payment Server System Error";
				break;
			case '8':
				result = "Transaction Type Not Supported";
				break;
			case '9':
				result = "Bank declined transaction (Do not contact Bank)";
				break;
			case 'A':
				result = "Transaction Aborted";
				break;
			case 'C':
				result = "Transaction Cancelled";
				break;
			case 'D':
				result = "Deferred transaction has been received and is awaiting processing";
				break;
			case 'F':
				result = "3D Secure Authentication failed";
				break;
			case 'I':
				result = "Card Security Code verification failed";
				break;
			case 'L':
				result = "Shopping Transaction Locked (Please try the transaction again later)";
				break;
			case 'N':
				result = "Cardholder is not enrolled in Authentication Scheme";
				break;
			case 'P':
				result = "Transaction has been received by the Payment Adaptor and is being processed";
				break;
			case 'R':
				result = "Transaction was not processed - Reached limit of retry attempts allowed";
				break;
			case 'S':
				result = "Duplicate SessionID (OrderInfo)";
				break;
			case 'T':
				result = "Address Verification Failed";
				break;
			case 'U':
				result = "Card Security Code Failed";
				break;
			case 'V':
				result = "Address Verification and Card Security Code Failed";
				break;
			case '?':
				result = "Transaction status is unknown";
				break;
			default:
				result = "Unable to be determined";
			}

			return result;
		} else {
			return "No Value Returned";
		}
	} //

	/**
	 * This method uses the 3DS verStatus retrieved from the Response and
	 * returns an appropriate description for this code.
	 * 
	 * @param vpc_VerStatus
	 *            String containing the status code
	 * @return description String containing the appropriate description
	 */
	private String getStatusDescription(final String vStatus) {

		String result = "";
		if ((vStatus != null) && !vStatus.equals("")) {

			if (vStatus.equalsIgnoreCase("Unsupported")
					|| vStatus.equals("No Value Returned")) {
				result = "3DS not supported or there was no 3DS data provided";
			} else {

				// Java cannot switch on a string so turn everything to a
				// character
				final char input = vStatus.charAt(0);

				switch (input) {
				case 'Y':
					result = "The cardholder was successfully authenticated.";
					break;
				case 'E':
					result = "The cardholder is not enrolled.";
					break;
				case 'N':
					result = "The cardholder was not verified.";
					break;
				case 'U':
					result = "The cardholder's Issuer was unable to authenticate due to some system error at the Issuer.";
					break;
				case 'F':
					result = "There was an error in the format of the request from the merchant.";
					break;
				case 'A':
					result = "Authentication of your Merchant ID and Password to the ACS Directory Failed.";
					break;
				case 'D':
					result = "Error communicating with the Directory Server.";
					break;
				case 'C':
					result = "The card type is not supported for authentication.";
					break;
				case 'S':
					result = "The signature on the response received from the Issuer could not be validated.";
					break;
				case 'P':
					result = "Error parsing input from Issuer.";
					break;
				case 'I':
					result = "Internal Payment Server system error.";
					break;
				default:
					result = "Unable to be determined";
					break;
				}
			}
		} else {
			result = "null response";
		}
		return result;
	}

	// ----------------------------------------------------------------------------

	public String getTxStatus() {
		return StringUtils.isNotEmpty(this.vpc_TxnResponseCode)
				&& ServiceHelper.getInstance().getIpgUtil().getSuccessCode()
						.equals(this.vpc_TxnResponseCode.trim()) ? "Successful"
				: "Failed";
	}

	// ----------------------------------------------------------------------------

	public String getVpc_3DSECI() {
		return this.vpc_3DSECI;
	}

	public String getVpc_3DSenrolled() {
		return this.vpc_3DSenrolled;
	}

	public String getVpc_3DSstatus() {
		return this.vpc_3DSstatus;
	}

	public String getVpc_3DSXID() {
		return this.vpc_3DSXID;
	}

	public String getVpc_AcqAVSRespCode() {
		return this.vpc_AcqAVSRespCode;
	}

	public String getVpc_AcqCSCRespCode() {
		return this.vpc_AcqCSCRespCode;
	}

	public String getVpc_AcqResponseCode() {
		return this.vpc_AcqResponseCode;
	}

	public String getVpc_Amount() {
		return this.vpc_Amount;
	}

	public String getVpc_AuthorizeId() {
		return this.vpc_AuthorizeId;
	}

	public String getVpc_AVS_City() {
		return this.vpc_AVS_City;
	}

	public String getVpc_AVS_Country() {
		return this.vpc_AVS_Country;
	}

	public String getVpc_AVS_PostCode() {
		return this.vpc_AVS_PostCode;
	}

	public String getVpc_AVS_StateProv() {
		return this.vpc_AVS_StateProv;
	}

	public String getVpc_AVS_Street01() {
		return this.vpc_AVS_Street01;
	}

	public String getVpc_AVSRequestCode() {
		return this.vpc_AVSRequestCode;
	}

	public String getVpc_AVSResultCode() {
		return this.vpc_AVSResultCode;
	}

	public String getVpc_BatchNo() {
		return this.vpc_BatchNo;
	}

	public String getVpc_Card() {
		return this.vpc_Card;
	}

	public String getVpc_Command() {
		return this.vpc_Command;
	}

	public String getVpc_CSCRequestCode() {
		return this.vpc_CSCRequestCode;
	}

	public String getVpc_CSCResultCode() {
		return this.vpc_CSCResultCode;
	}

	public String getVpc_Locale() {
		return this.vpc_Locale;
	}

	public String getVpc_Merchant() {
		return this.vpc_Merchant;
	}

	public String getVpc_MerchTxnRef() {
		return this.vpc_MerchTxnRef;
	}

	public String getVpc_Message() {
		return this.vpc_Message;
	}

	public String getVpc_OrderInfo() {
		return this.vpc_OrderInfo;
	}

	public String getVpc_ReceiptNo() {
		return this.vpc_ReceiptNo;
	}

	public String getVpc_TransactionNo() {
		return this.vpc_TransactionNo;
	}

	public String getVpc_TxnResponseCode() {
		return this.vpc_TxnResponseCode;
	}

	public String getVpc_VerSecurityLevel() {
		return this.vpc_VerSecurityLevel;
	}

	public String getVpc_Version() {
		return this.vpc_Version;
	}

	public String getVpc_VerStatus() {
		return this.vpc_VerStatus;
	}

	public String getVpc_VerToken() {
		return this.vpc_VerToken;
	}

	public String getVpc_VerType() {
		return this.vpc_VerType;
	}

	/**
	 * This method is for sorting the fields and creating an MD5 secure hash.
	 * 
	 * @param fields
	 *            is a map of all the incoming hey-value pairs from the VPC
	 * @return is the hash being returned for comparison to the incoming hash
	 */
	private String hashAllFields(final java.util.Map fields) {

		// create a list and sort it
		final List fieldNames = new ArrayList(fields.keySet());
		Collections.sort(fieldNames);

		// create a buffer for the md5 input and add the secure secret first
		final StringBuffer buf = new StringBuffer();
		buf.append(SECURE_SECRET);

		// iterate through the list and add the remaining field values
		final Iterator itr = fieldNames.iterator();

		while (itr.hasNext()) {
			final String fieldName = (String) itr.next();
			final String fieldValue = (String) fields.get(fieldName);
			if ((fieldValue != null) && (fieldValue.length() > 0)) {
				buf.append(fieldValue);
			}
		}

		MessageDigest md5 = null;
		byte[] ba = null;

		// create the md5 hash and UTF-8 encode it
		try {
			md5 = MessageDigest.getInstance("MD5");
			ba = md5.digest(buf.toString().getBytes("UTF-8"));
		} catch (final Exception e) {
		} // wont happen

		return hex(ba);

	} // end hashAllFields()

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yd.etravel.web.common.BaseForm#resetBean(org.apache.struts.action
	 * .ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void resetBean(final ActionMapping mapping,
			final HttpServletRequest request) {
		this.AgainLink = EMPTYSTRING;
		this.vpc_Amount = EMPTYSTRING;
		this.vpc_Locale = EMPTYSTRING;
		this.vpc_BatchNo = EMPTYSTRING;
		this.vpc_Command = EMPTYSTRING;
		this.vpc_Message = EMPTYSTRING;
		this.vpc_Version = EMPTYSTRING;
		this.vpc_Card = EMPTYSTRING;
		this.vpc_OrderInfo = EMPTYSTRING;
		this.vpc_ReceiptNo = EMPTYSTRING;
		this.vpc_Merchant = EMPTYSTRING;
		this.vpc_MerchTxnRef = EMPTYSTRING;
		this.vpc_AuthorizeId = EMPTYSTRING;
		this.vpc_TransactionNo = EMPTYSTRING;
		this.vpc_AcqResponseCode = EMPTYSTRING;
		this.vpc_TxnResponseCode = EMPTYSTRING;
		this.vpc_CSCResultCode = EMPTYSTRING;
		this.vpc_CSCRequestCode = EMPTYSTRING;
		this.vpc_AcqCSCRespCode = EMPTYSTRING;
		this.vpc_AVS_City = EMPTYSTRING;
		this.vpc_AVS_Country = EMPTYSTRING;
		this.vpc_AVS_Street01 = EMPTYSTRING;
		this.vpc_AVS_PostCode = EMPTYSTRING;
		this.vpc_AVS_StateProv = EMPTYSTRING;
		this.vpc_AVSResultCode = EMPTYSTRING;
		this.vpc_AVSRequestCode = EMPTYSTRING;
		this.vpc_AcqAVSRespCode = EMPTYSTRING;
		this.vpc_VerType = EMPTYSTRING;
		this.vpc_VerStatus = EMPTYSTRING;
		this.vpc_VerToken = EMPTYSTRING;
		this.vpc_VerSecurityLevel = EMPTYSTRING;
		this.vpc_3DSenrolled = EMPTYSTRING;
		this.vpc_3DSXID = EMPTYSTRING;
		this.vpc_3DSECI = EMPTYSTRING;
		this.vpc_3DSstatus = EMPTYSTRING;

	}

	public void setAgainLink(final String againLink) {
		this.AgainLink = againLink;
	}

	public void setVpc_3DSECI(final String vpc_3DSECI) {
		this.vpc_3DSECI = vpc_3DSECI;
	}

	public void setVpc_3DSenrolled(final String vpc_3DSenrolled) {
		this.vpc_3DSenrolled = vpc_3DSenrolled;
	}

	public void setVpc_3DSstatus(final String vpc_3DSstatus) {
		this.vpc_3DSstatus = vpc_3DSstatus;
	}

	public void setVpc_3DSXID(final String vpc_3DSXID) {
		this.vpc_3DSXID = vpc_3DSXID;
	}

	public void setVpc_AcqAVSRespCode(final String vpc_AcqAVSRespCode) {
		this.vpc_AcqAVSRespCode = vpc_AcqAVSRespCode;
	}

	public void setVpc_AcqCSCRespCode(final String vpc_AcqCSCRespCode) {
		this.vpc_AcqCSCRespCode = vpc_AcqCSCRespCode;
	}

	public void setVpc_AcqResponseCode(final String vpc_AcqResponseCode) {
		this.vpc_AcqResponseCode = vpc_AcqResponseCode;
	}

	public void setVpc_Amount(final String vpc_Amount) {
		this.vpc_Amount = vpc_Amount;
	}

	public void setVpc_AuthorizeId(final String vpc_AuthorizeId) {
		this.vpc_AuthorizeId = vpc_AuthorizeId;
	}

	public void setVpc_AVS_City(final String vpc_AVS_City) {
		this.vpc_AVS_City = vpc_AVS_City;
	}

	public void setVpc_AVS_Country(final String vpc_AVS_Country) {
		this.vpc_AVS_Country = vpc_AVS_Country;
	}

	public void setVpc_AVS_PostCode(final String vpc_AVS_PostCode) {
		this.vpc_AVS_PostCode = vpc_AVS_PostCode;
	}

	public void setVpc_AVS_StateProv(final String vpc_AVS_StateProv) {
		this.vpc_AVS_StateProv = vpc_AVS_StateProv;
	}

	public void setVpc_AVS_Street01(final String vpc_AVS_Street01) {
		this.vpc_AVS_Street01 = vpc_AVS_Street01;
	}

	public void setVpc_AVSRequestCode(final String vpc_AVSRequestCode) {
		this.vpc_AVSRequestCode = vpc_AVSRequestCode;
	}

	public void setVpc_AVSResultCode(final String vpc_AVSResultCode) {
		this.vpc_AVSResultCode = vpc_AVSResultCode;
	}

	public void setVpc_BatchNo(final String vpc_BatchNo) {
		this.vpc_BatchNo = vpc_BatchNo;
	}

	public void setVpc_Card(final String vpc_Card) {
		this.vpc_Card = vpc_Card;
	}

	public void setVpc_Command(final String vpc_Command) {
		this.vpc_Command = vpc_Command;
	}

	public void setVpc_CSCRequestCode(final String vpc_CSCRequestCode) {
		this.vpc_CSCRequestCode = vpc_CSCRequestCode;
	}

	public void setVpc_CSCResultCode(final String vpc_CSCResultCode) {
		this.vpc_CSCResultCode = vpc_CSCResultCode;
	}

	public void setVpc_Locale(final String vpc_Locale) {
		this.vpc_Locale = vpc_Locale;
	}

	public void setVpc_Merchant(final String vpc_Merchant) {
		this.vpc_Merchant = vpc_Merchant;
	}

	public void setVpc_MerchTxnRef(final String vpc_MerchTxnRef) {
		this.vpc_MerchTxnRef = vpc_MerchTxnRef;
	}

	public void setVpc_Message(final String vpc_Message) {
		this.vpc_Message = vpc_Message;
	}

	public void setVpc_OrderInfo(final String vpc_OrderInfo) {
		this.vpc_OrderInfo = vpc_OrderInfo;
	}

	public void setVpc_ReceiptNo(final String vpc_ReceiptNo) {
		this.vpc_ReceiptNo = vpc_ReceiptNo;
	}

	public void setVpc_TransactionNo(final String vpc_TransactionNo) {
		this.vpc_TransactionNo = vpc_TransactionNo;
	}

	public void setVpc_TxnResponseCode(final String vpc_TxnResponseCode) {
		this.vpc_TxnResponseCode = vpc_TxnResponseCode;
	}

	public void setVpc_VerSecurityLevel(final String vpc_VerSecurityLevel) {
		this.vpc_VerSecurityLevel = vpc_VerSecurityLevel;
	}

	public void setVpc_Version(final String vpc_Version) {
		this.vpc_Version = vpc_Version;
	}

	public void setVpc_VerStatus(final String vpc_VerStatus) {
		this.vpc_VerStatus = vpc_VerStatus;
	}

	public void setVpc_VerToken(final String vpc_VerToken) {
		this.vpc_VerToken = vpc_VerToken;
	}

	public void setVpc_VerType(final String vpc_VerType) {
		this.vpc_VerType = vpc_VerType;
	}

	@Override
	public ActionErrors validateBean(final ActionMapping mapping,
			final HttpServletRequest request) {
		return null;
	}

}
