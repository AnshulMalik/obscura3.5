package com.obscuraconflu.api.dto;

import org.springframework.data.rest.core.annotation.RestResource;

import com.obscuraconflu.api.models.Level;

public class LevelResponse extends Response{
	private Long level;
	
	private String name;
	
	private Long plevel;
	
	private String image;
	
	private String url;
	
	private String html;
	
	private String js;
	
	private String hint;

	public LevelResponse(Level level) {
		this.level = level.getLevel();
		this.name = level.getName();
		this.plevel = level.getParentLevel();
		this.html = level.getHtml();
		this.image = level.getImage();
		this.url = level.getUrl();
		this.js = level.getJavascript();
		this.hint = level.getHint();
	}
	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}

	public Long getPlevel() {
		return plevel;
	}

	public void setPlevel(Long plevel) {
		this.plevel = plevel;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public String getJs() {
		return js;
	}

	public void setJs(String js) {
		this.js = js;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
}
