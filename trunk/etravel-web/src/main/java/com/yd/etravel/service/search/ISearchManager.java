/**
 * 
 */
package com.yd.etravel.service.search;

import com.yd.etravel.domain.custom.search.SearchRequestDTO;
import com.yd.etravel.service.exception.ServiceException;

/**
 * @author Dharsahana
 * 
 */
public interface ISearchManager {

	public com.yd.etravel.domain.custom.search.SearchResultsDTO searchRoom(
			SearchRequestDTO searchRequestDTO) throws ServiceException;

}
