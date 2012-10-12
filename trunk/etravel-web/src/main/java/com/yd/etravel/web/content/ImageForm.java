package com.yd.etravel.web.content;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.yd.etravel.util.StringUtils;
import com.yd.etravel.web.common.BaseForm;

public class ImageForm extends BaseForm {
    private FormFile formFile;
    private Long pk;
    private String object;
    private String title;
    private String name;
    private List<Long> images;

    public ImageForm() {
    }

    @Override
    public void resetBean(final ActionMapping mapping,
	    final HttpServletRequest request) {
	this.images = Collections.emptyList();
	this.formFile = null;
	this.pk = null;
	this.object = StringUtils.EMPTY_STRING;
	this.title = StringUtils.EMPTY_STRING;
	this.name = StringUtils.EMPTY_STRING;

    }

    public FormFile getFormFile() {
	return this.formFile;
    }

    public void setFormFile(final FormFile formFile) {
	this.formFile = formFile;
    }

    public Long getPk() {
	return this.pk;
    }

    public void setPk(final Long pk) {
	this.pk = pk;
    }

    public String getObject() {
	return this.object;
    }

    public void setObject(final String object) {
	this.object = object;
    }

    public String getTitle() {
	return this.title;
    }

    public void setTitle(final String title) {
	this.title = title;
    }

    public String getName() {
	return this.name;
    }

    public void setName(final String name) {
	this.name = name;
    }

    public List<Long> getImages() {
	return this.images;
    }

    public void setImages(final List<Long> images) {
	this.images = images;
    }

    @Override
    public ActionErrors validateBean(final ActionMapping mapping,
	    final HttpServletRequest request) {
	final ActionErrors errors = new ActionErrors();

	if (this.formFile == null || this.formFile.getFileSize() < 1) {
	    addErrors(errors, "etravel.error.image.required");
	} else if (this.formFile != null
		&& !this.formFile.getContentType().startsWith("image")) {
	    addErrors(errors, "etravel.error.image.invalid.contenttype");
	}

	if (StringUtils.isEmpty(this.pk)) {
	    addErrors(errors, "etravel.error.image.id.required");
	}
	if (StringUtils.isEmpty(this.object)) {
	    addErrors(errors, "etravel.error.image.imagetype.required");
	}

	if (StringUtils.isEmpty(this.title)) {
	    addErrors(errors, "etravel.error.image.title.required");
	}

	if (StringUtils.isEmpty(this.name)) {
	    addErrors(errors, "etravel.error.image.name.required");
	}

	return errors;
    }

}
