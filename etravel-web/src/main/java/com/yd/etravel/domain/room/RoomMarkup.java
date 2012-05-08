/**
 * 
 */
package com.yd.etravel.domain.room;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yd.etravel.domain.common.BaseObject;
import com.yd.etravel.domain.markup.Markup;

@Entity
@Table(name = "T_ROOM_MARKUP")
public class RoomMarkup extends BaseObject {
    @ManyToOne
    private Room room;

    @ManyToOne
    private Markup markup;

    public Room getRoom() {
	return this.room;
    }

    public void setRoom(final Room room) {
	this.room = room;
    }

    public Markup getMarkup() {
	return this.markup;
    }

    public void setMarkup(final Markup markup) {
	this.markup = markup;
    }

}
