package com.kh.springboot_02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.springboot_02.configurationProperties.AppProperties;

@Service
public class AppService {
	private final AppProperties appProperties;
	
	@Autowired
	public AppService(AppProperties appProperties) {
		this.appProperties = appProperties;
	}
	
	public AppProperties getAppProperties() {
		return this.appProperties;
	}
}
