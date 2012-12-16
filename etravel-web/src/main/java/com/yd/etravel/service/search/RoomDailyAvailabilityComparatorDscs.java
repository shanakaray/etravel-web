package com.yd.etravel.service.search;

import java.util.Comparator;

import com.yd.etravel.domain.room.availability.RoomDailyAvailability;

public class RoomDailyAvailabilityComparatorDscs implements
		Comparator<RoomDailyAvailability> {

	@Override
	public int compare(final RoomDailyAvailability o1,
			final RoomDailyAvailability o2) {
		return o2.getAvailableUnit().compareTo(o1.getAvailableUnit());
	}
}