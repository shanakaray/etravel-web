package com.yd.etravel.domain.cmt.content;

import java.io.File;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.yd.etravel.domain.common.BaseObject;

@Entity
@Table(name = "T_IMAGE")
public class Image extends BaseObject {

	private static final long serialVersionUID = 5477542185234310018L;
	@Basic(fetch = FetchType.LAZY)
	@Column(length = 100000)
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] source;
	@Column(nullable = false)
	private Long pk;
	@Column(nullable = false)
	private String object;
	@Column
	private String title;
	private File file;
	@Column
	private String type;

	@Column
	private int sequence;

	public File getFile() {
		return this.file;
	}

	public String getObject() {
		return this.object;
	}

	public Long getPk() {
		return this.pk;
	}

	public int getSequence() {
		return this.sequence;
	}

	public byte[] getSource() {
		return this.source;
	}

	public String getTitle() {
		return this.title;
	}

	public String getType() {
		return this.type;
	}

	public void setFile(final File file) {
		this.file = file;
	}

	public void setObject(final String object) {
		this.object = object;
	}

	public void setPk(final Long pk) {
		this.pk = pk;
	}

	public void setSequence(final int sequence) {
		this.sequence = sequence;
	}

	public void setSource(final byte[] source) {
		this.source = source;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public void setType(final String type) {
		this.type = type;
	}

}
