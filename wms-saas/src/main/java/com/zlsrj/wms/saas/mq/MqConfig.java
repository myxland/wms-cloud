package com.zlsrj.wms.saas.mq;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix ="mq-config.tenant-info")
public class MqConfig {
	private String topic;
	private List<String> insertTags;
	private List<String> removeTags;
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public List<String> getInsertTags() {
		return insertTags;
	}
	public void setInsertTags(List<String> insertTags) {
		this.insertTags = insertTags;
	}
	public List<String> getRemoveTags() {
		return removeTags;
	}
	public void setRemoveTags(List<String> removeTags) {
		this.removeTags = removeTags;
	}
	
	
	
}
