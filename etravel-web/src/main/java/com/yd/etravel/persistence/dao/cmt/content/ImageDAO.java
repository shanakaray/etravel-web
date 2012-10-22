package com.yd.etravel.persistence.dao.cmt.content;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yd.etravel.domain.cmt.content.Image;
import com.yd.etravel.persistence.dao.common.BaseDAO;
import com.yd.etravel.persistence.exception.PersistenceException;

@Repository
public class ImageDAO extends BaseDAO<Image> implements IImageDAO {

	@Override
	public void deleteContent(final Image image) throws PersistenceException {
		getCurrentSession().delete(image);
	}

	@Override
	protected Class getEntityClass() {
		return Image.class;
	}

	@Override
	public List<Long> getIds(final Image image) throws PersistenceException {
		return getCurrentSession().createQuery(
				"select i.id from Image i where i.object='" + image.getObject()
						+ "' and i.pk=" + image.getPk()).list();
	}

	@Override
	public void saveContent(final Image image) throws PersistenceException {
		getCurrentSession().save(image);
	}
}
