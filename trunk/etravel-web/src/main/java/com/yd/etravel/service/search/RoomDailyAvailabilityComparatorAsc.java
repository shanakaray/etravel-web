package com.yd.etravel.service.search;

import java.util.Comparator;

import com.yd.etravel.domain.room.availability.RoomDailyAvailability;

public class RoomDailyAvailabilityComparatorAsc implements
		Comparator<RoomDailyAvailability> {

	@Override
	public int compare(final RoomDailyAvailability o1,
			final RoomDailyAvailability o2) {
		return o1.getAvailableUnit().compareTo(o2.getAvailableUnit());
	}
}