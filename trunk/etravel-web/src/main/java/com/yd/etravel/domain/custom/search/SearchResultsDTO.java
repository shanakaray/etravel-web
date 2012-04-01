/**
 * 
 */
package com.yd.etravel.domain.custom.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dharshana
 * 
 */
public class SearchResultsDTO {

    private List<RoomDTO> roomDTO = new ArrayList<RoomDTO>();
    private List<ExtraItemDTO> extraItemDTO = new ArrayList<ExtraItemDTO>();

    /**
     * @return the roomDTO
     */
    public List<RoomDTO> getRoomDTO() {
	return roomDTO;
    }

    /**
     * @param roomDTO
     *            the roomDTO to set
     */
    public void setRoomDTO(List<RoomDTO> roomDTO) {
	this.roomDTO = roomDTO;
    }

    /**
     * @return the extraItem
     */
    public List<ExtraItemDTO> getExtraItemDTO() {
	return extraItemDTO;
    }

    /**
     * @param extraItem
     *            the extraItem to set
     */
    public void setExtraItemDTO(List<ExtraItemDTO> extraItemDTO) {
	this.extraItemDTO = extraItemDTO;
    }

}
