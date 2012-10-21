package com.yd.etravel.persistence.dao.cmt.content;

import java.util.List;

import com.yd.etravel.domain.cmt.content.Image;
import com.yd.etravel.persistence.dao.common.IBaseDAO;
import com.yd.etravel.persistence.exception.PersistenceException;

public interface IImageDAO extends IBaseDAO<Image> {

	void deleteContent(Image image) throws PersistenceException;

	List<Long> getIds(final Image image) throws PersistenceException;

	void saveContent(Image image) throws PersistenceException;
}
