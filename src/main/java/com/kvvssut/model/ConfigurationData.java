package com.kvvssut.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "configuration")
public class ConfigurationData implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "configName", length = 200)
	private String configName;

	@Column(name = "configValue", nullable = false, length = 200)
	private String configValue;

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	@Override
	public String toString() {
		return "ConfigurationData [configName=" + configName + ", configValue=" + configValue + "]";
	}

}
