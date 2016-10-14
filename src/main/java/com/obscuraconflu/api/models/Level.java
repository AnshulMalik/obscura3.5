package com.obscuraconflu.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "level")
@Entity
public class Level {
	
	public @Id @GeneratedValue Long id;
	
	public Long level;
	
	public String name;
	
	public Long parentLevel;
	
	public Long nextLevelId;
	
	public String image;
	
	public String url;
	
	public String html;
	
	public String javascript;
	
	public String hint;
	
	public String answer;
	
	private Level() {
		
	}
	
	public Level(Long level, String name, Long parentLevel, Long nextLevel, String image, String url, String html, String js, String hint, String answer) {
		this.level = level;
		this.name = name;
		this.parentLevel = parentLevel;
		this.nextLevelId = nextLevel;
		this.image = image;
		this.url = url;
		this.html = html;
		this.javascript = js;
		this.hint = hint;
		this.answer = answer;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getLevel() {
		return this.level;
	}
	
	public void setLevel(Long level) {
		this.level = level;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getParentLevel() {
		return parentLevel;
	}
	
	public void setParentLevel(Long parentLevel) {
		this.parentLevel = parentLevel;
	}
	
	public Long getNextLevelId() {
		return this.nextLevelId;
	}
	
	public void setNextLevelId(Long id) {
		this.nextLevelId = id;
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
	public String getHtml() {
		return html;
	}
	
	public void setHtml(String html) {
		this.html = html;
	}
	
	public String getJavascript() {
		return javascript;
	}
	
	public void setJavascript(String js) {
		this.javascript = js;
	}
	
	public String getHint() {
		return hint;
	}
	
	public void setHint(String hint) {
		this.hint = hint;
	}
	
	public String getAnswer() {
		return this.answer;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
}
