package com.yd.etravel.service.cmt.content;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yd.etravel.domain.cmt.content.Image;
import com.yd.etravel.persistence.dao.cmt.content.IImageDAO;
import com.yd.etravel.persistence.exception.PersistenceException;
import com.yd.etravel.service.exception.ServiceException;

@Service(value = "contentService")
@Transactional(propagation = Propagation.SUPPORTS)
public class ContentManager implements IContentManager {
	@Autowired(required = true)
	private IImageDAO imageDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Image saveImage(final Image image) throws ServiceException {
		try {
			this.imageDao.saveOrUpdate(image);
		} catch (final PersistenceException exception) {
			throw new ServiceException(null, exception);
		}
		return image;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Image getImage(final Long id) throws ServiceException {
		Image image = null;
		FileOutputStream fos = null;
		try {
			image = this.imageDao.findById(id);
			final File img = new File(System.getProperty("java.io.tmpdir")
					+ image.getId() + image.getCode());
			if (!img.exists()) {
				final byte[] bytes = image.getSource();
				fos = new FileOutputStream(System.getProperty("java.io.tmpdir")
						+ image.getId() + image.getCode());
				fos.write(bytes);
			}
			image.setFile(img);
		} catch (final Exception exception) {
			throw new ServiceException(null, exception);
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
		return image;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<Long> getImageList(final Image image) throws ServiceException {
		try {
			return this.imageDao.getIds(image);
		} catch (final PersistenceException exception) {
			throw new ServiceException(null, exception);
		}

	}

}
