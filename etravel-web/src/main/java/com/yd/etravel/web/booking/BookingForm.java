/**
 * 
 */
package com.yd.etravel.web.booking;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.domain.custom.search.RoomDTO;
import com.yd.etravel.domain.user.User;
import com.yd.etravel.util.StringUtils;
import com.yd.etravel.web.common.BaseForm;

/**
 * @author Dharshana
 * 
 */
public class BookingForm extends BaseForm {

    private Long uid;
    private String cusUsername;
    private String cusPassword;
    private String repassword;
    private String address;
    private String contact;
    private String email;
    private String firstName;
    private String lastName;
    private String nic;
    private boolean guest;
    private boolean singleNight;
    private boolean newCustomer;
    private Long bookedUserId;

    private boolean registerd;
    private String rUsername;
    private String rPassword;

    private Long id;
    private Integer adult;
    private Integer child;
    private Integer infant;
    private Integer noOfRoom;
    private String comments;

    private RoomDTO roomDTO;
    private Integer totalPax;
    private String paymentMethodId;
    private List<User> allAgent;
    private List<User> allCustomers;
    private Long agentId;

    private String bookingId;
    private String payed;

    public boolean isRegisterd() {
	return this.registerd;
    }

    public void setRegisterd(final boolean registerd) {
	this.registerd = registerd;
    }

    public String getRUsername() {
	return this.rUsername;
    }

    public void setRUsername(final String username) {
	this.rUsername = username;
    }

    public String getRPassword() {
	return this.rPassword;
    }

    public void setRPassword(final String password) {
	this.rPassword = password;
    }

    /**
     * @return the totalPax
     */
    public Integer getTotalPax() {
	return this.totalPax;
    }

    /**
     * @param totalPax
     *            the totalPax to set
     */
    public void setTotalPax(final Integer totalPax) {
	this.totalPax = totalPax;
    }

    /**
     * @return the adult
     */
    public Integer getAdult() {
	return this.adult;
    }

    /**
     * @param adult
     *            the adult to set
     */
    public void setAdult(final Integer adult) {
	this.adult = adult;
    }

    /**
     * @return the child
     */
    public Integer getChild() {
	return this.child;
    }

    /**
     * @param child
     *            the child to set
     */
    public void setChild(final Integer child) {
	this.child = child;
    }

    /**
     * @return the infant
     */
    public Integer getInfant() {
	return this.infant;
    }

    /**
     * @param infant
     *            the infant to set
     */
    public void setInfant(final Integer infant) {
	this.infant = infant;
    }

    /**
     * @return the noOfRoom
     */
    public Integer getNoOfRoom() {
	return this.noOfRoom;
    }

    /**
     * @param noOfRoom
     *            the noOfRoom to set
     */
    public void setNoOfRoom(final Integer noOfRoom) {
	this.noOfRoom = noOfRoom;
    }

    /**
     * @return the comments
     */
    public String getComments() {
	return this.comments;
    }

    /**
     * @param comments
     *            the comments to set
     */
    public void setComments(final String comments) {
	this.comments = comments;
    }

    /**
     * @return the roomDTO
     */
    public RoomDTO getRoomDTO() {
	return this.roomDTO;
    }

    /**
     * @param roomDTO
     *            the roomDTO to set
     */
    public void setRoomDTO(final RoomDTO roomDTO) {
	this.roomDTO = roomDTO;
    }

    /**
     * @return the paymentMethodId
     */
    public String getPaymentMethodId() {
	return this.paymentMethodId;
    }

    /**
     * @param paymentMethodId
     *            the paymentMethodId to set
     */
    public void setPaymentMethodId(final String paymentMethodId) {
	this.paymentMethodId = paymentMethodId;
    }

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
	// this.address = EMPTY_STRING;
	// this.contact = EMPTY_STRING;
	// this.email = EMPTY_STRING;
	// this.firstName = EMPTY_STRING;
	this.cusUsername = EMPTY_STRING;
	this.rPassword = EMPTY_STRING;
	this.rUsername = EMPTY_STRING;
	this.cusPassword = EMPTY_STRING;
	this.repassword = EMPTY_STRING;
	this.bookingId = EMPTY_STRING;
	this.payed = EMPTY_STRING;
	this.uid = 0L;
	this.guest = false;
	this.newCustomer = false;
	this.allCustomers = Collections.emptyList();
    }

    public List<User> getAllCustomers() {
	return this.allCustomers;
    }

    public void setAllCustomers(final List<User> allCustomers) {
	this.allCustomers = allCustomers;
    }

    public Long getBookedUserId() {
	return this.bookedUserId;
    }

    public void setBookedUserId(final Long bookedUserId) {
	this.bookedUserId = bookedUserId;
    }

    public boolean isNewCustomer() {
	return this.newCustomer;
    }

    public void setNewCustomer(final boolean newCustomer) {
	this.newCustomer = newCustomer;
    }

    public String getBookingId() {
	return this.bookingId;
    }

    public void setBookingId(final String bookingId) {
	this.bookingId = bookingId;
    }

    public String getPayed() {
	return this.payed;
    }

    public void setPayed(final String payed) {
	this.payed = payed;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.yd.etravel.web.common.BaseForm#validateBean(org.apache.struts.action
     * .ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    @Override
    public ActionErrors validateBean(final ActionMapping mapping,
	    final HttpServletRequest request) {
	// TODO Auto-generated method stub

	final ActionErrors errors = new ActionErrors();
	if (isGuest() && !isRegisterd() || isNewCustomer()) {
	    if (StringUtils.isEmpty(this.firstName)) {
		addErrors(errors, "etravel.error.customer.firstname.required");
	    }

	    if (StringUtils.isEmpty(this.lastName)) {
		addErrors(errors, "etravel.error.customer.lastname.required");
	    }

	    if (StringUtils.isEmpty(this.contact)) {
		addErrors(errors, "etravel.error.customer.contact.required");
	    }

	    // if (StringUtils.isEmpty(this.nic)) {
	    // addErrors(errors, "etravel.error.customer.nic.required");
	    // }

	    if (StringUtils.isEmpty(this.cusUsername)) {
		addErrors(errors, "etravel.error.username.username.required");
	    }

	    if (StringUtils.isEmpty(this.email)) {
		addErrors(errors, "etravel.error.email.empty");

	    } else if (!StringUtils.isValidEmail(this.email)) {
		addErrors(errors, "etravel.error.email.invalid");
		this.email = EMPTY_STRING;
	    }

	    if (StringUtils.isEmpty(this.cusPassword)) {
		addErrors(errors, "etravel.error.pw.required");
	    } else if (this.cusPassword.trim().length() < 6) {
		addErrors(errors, "etravel.error.pw.length");
		this.cusPassword = EMPTY_STRING;
		this.repassword = EMPTY_STRING;
	    } else if (!this.cusPassword.equals(this.repassword)) {
		addErrors(errors, "etravel.error.pw.notmached");
		this.cusPassword = EMPTY_STRING;
		this.repassword = EMPTY_STRING;
	    }

	} else if (isRegisterd()) {

	    if (StringUtils.isEmpty(this.rUsername)) {
		addErrors(errors, "etravel.error.username.required");
	    }

	    if (StringUtils.isEmpty(this.rPassword)) {
		addErrors(errors, "etravel.error.password.required");
	    }

	}

	if (this.noOfRoom <= 0) {
	    addErrors(errors, "etravel.error.booking.noOfRoom.zero");

	}

	if (this.totalPax <= 0) {
	    addErrors(errors, "etravel.error.booking.pax.count.zero");

	}
	if (this.paymentMethodId == null || this.paymentMethodId.equals("0")) {
	    addErrors(errors, "etravel.error.booking.select.payment.method");

	}

	return errors;
    }

    /**
     * @return the id
     */
    public Long getId() {
	return this.id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final Long id) {
	this.id = id;
    }

    /**
     * @return the allAgent
     */
    public List<User> getAllAgent() {
	return this.allAgent;
    }

    /**
     * @param allAgent
     *            the allAgent to set
     */
    public void setAllAgent(final List<User> allAgent) {
	this.allAgent = allAgent;
    }

    /**
     * @return the agentId
     */
    public Long getAgentId() {
	return this.agentId;
    }

    /**
     * @param agentId
     *            the agentId to set
     */
    public void setAgentId(final Long agentId) {
	this.agentId = agentId;
    }

    public Long getUid() {
	return this.uid;
    }

    public void setUid(final Long uid) {
	this.uid = uid;
    }

    public String getCusUsername() {
	return this.cusUsername;
    }

    public void setCusUsername(final String cusUsername) {
	this.cusUsername = cusUsername;
    }

    public String getCusPassword() {
	return this.cusPassword;
    }

    public void setCusPassword(final String cusPassword) {
	this.cusPassword = cusPassword;
    }

    public String getRepassword() {
	return this.repassword;
    }

    public void setRepassword(final String repassword) {
	this.repassword = repassword;
    }

    public String getAddress() {
	return this.address;
    }

    public void setAddress(final String address) {
	this.address = address;
    }

    public String getContact() {
	return this.contact;
    }

    public void setContact(final String contact) {
	this.contact = contact;
    }

    public String getEmail() {
	return this.email;
    }

    public void setEmail(final String email) {
	this.email = email;
    }

    public String getFirstName() {
	return this.firstName;
    }

    public void setFirstName(final String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return this.lastName;
    }

    public void setLastName(final String lastName) {
	this.lastName = lastName;
    }

    public String getNic() {
	return this.nic;
    }

    public void setNic(final String nic) {
	this.nic = nic;
    }

    public boolean isGuest() {
	return this.guest;
    }

    public void setGuest(final boolean guest) {
	this.guest = guest;
    }

    public boolean isSingleNight() {
	return this.singleNight;
    }

    public void setSingleNight(final boolean singleNight) {
	this.singleNight = singleNight;
    }

}
