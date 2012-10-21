package com.yd.etravel.service.cmt.content;

import java.util.List;

import com.yd.etravel.domain.cmt.content.Image;
import com.yd.etravel.service.exception.ServiceException;

public interface IContentManager {

	public Image saveImage(Image image) throws ServiceException;

	public Image getImage(Long id) throws ServiceException;

	public List<Long> getImageList(Image image) throws ServiceException;

}
