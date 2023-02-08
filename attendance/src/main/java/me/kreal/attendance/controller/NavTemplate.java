package me.kreal.attendance.controller;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class NavTemplate {
	private Map<String, String> nav = new HashMap<>();

	public void setCurrentPage(String... pages) {
		nav = new HashMap<>();
		for (String page : pages) {
			nav.put(page, "active");
		}

	}

	public void setNonShowPages(String... strs) {
		for (String str : strs) {
			nav.put(str, "d-none");
		}
	}

	public void setDisabledPages(String... strs) {
		for (String str : strs) {
			nav.put(str, "disabled");
		}
	}

	public Map<String, String> getUserNav() {
		return nav;
	}

}
