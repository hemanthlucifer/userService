package com.localOrder.userService.util;

import org.springframework.stereotype.Component;

@Component
public class UrlBuilder {
	
	private static final String domain = "https://api.bigdatacloud.net/data/reverse-geocode-client?";
	
	//https://api.bigdatacloud.net/data/reverse-geocode-client?latitude=37.42159&longitude=-122.0837&localityLanguage=en
	
	public String urlBuilder(String latitude, String longitude) {
		
		String latitudePart = "latitude="+latitude+"&";
		
		String longitudePart = "longitude="+longitude+"&";
		
		String localityLanguage = "localityLanguage=en";
		
		return domain+latitudePart+longitudePart+localityLanguage;
	}

}
