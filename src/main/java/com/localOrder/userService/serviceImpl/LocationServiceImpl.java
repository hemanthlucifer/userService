package com.localOrder.userService.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.localOrder.userService.service.LocationService;
import com.localOrder.userService.util.UrlBuilder;

public class LocationServiceImpl implements LocationService {
	
	@Autowired
	private UrlBuilder builder;

	@Override
	public String getLocation(String latitude, String longitude) {
		String url = builder.urlBuilder(latitude, longitude);
		return null;
	}

}
