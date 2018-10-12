package com.webside.business.service;

import java.util.List;

import com.webside.business.model.CodeEntity;

public interface CodeService {
	public List<CodeEntity> valueAndName(String type);
	public CodeEntity query(String value, String type);
	public CodeEntity queryByName(String name, String type);
}
