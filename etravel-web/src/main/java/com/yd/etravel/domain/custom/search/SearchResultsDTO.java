/**
 * 
 */
package com.yd.etravel.domain.custom.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dharshana
 * 
 */
public class SearchResultsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4797133272899010716L;
	private List<RoomDTO> roomDTO = new ArrayList<RoomDTO>();
	private List<ExtraItemDTO> extraItemDTO = new ArrayList<ExtraItemDTO>();

	/**
	 * @return the extraItem
	 */
	public List<ExtraItemDTO> getExtraItemDTO() {
		return this.extraItemDTO;
	}

	/**
	 * @return the roomDTO
	 */
	public List<RoomDTO> getRoomDTO() {
		return this.roomDTO;
	}

	/**
	 * @param extraItem
	 *            the extraItem to set
	 */
	public void setExtraItemDTO(final List<ExtraItemDTO> extraItemDTO) {
		this.extraItemDTO = extraItemDTO;
	}

	/**
	 * @param roomDTO
	 *            the roomDTO to set
	 */
	public void setRoomDTO(final List<RoomDTO> roomDTO) {
		this.roomDTO = roomDTO;
	}

}
