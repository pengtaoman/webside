package com.webside.business.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webside.business.mapper.CodeEntityMapper;
import com.webside.business.model.CodeEntity;
import com.webside.business.model.CodeEntityExample;
import com.webside.business.service.CodeService;
@Service("codeService")
public class CodeServiceImpl implements CodeService{
	@Autowired 
	private CodeEntityMapper codeEntityMapper;
	
	@Override
	public List<CodeEntity> valueAndName(String type) {
		CodeEntityExample example = new CodeEntityExample();
		example.createCriteria().andTypeEqualTo(type);
		return codeEntityMapper.selectByExample(example);
	}
	@Override
	public CodeEntity query(String value, String type ){
		CodeEntityExample example = new CodeEntityExample();
		example.createCriteria().andValueEqualTo(value).andTypeEqualTo(type);
		List<CodeEntity> list = codeEntityMapper.selectByExample(example);
		if(list.isEmpty()) {
			return null;
		}
		return codeEntityMapper.selectByExample(example).get(0);
	}
	@Override
	public CodeEntity queryByName(String name, String type ){
		CodeEntityExample example = new CodeEntityExample();
		example.createCriteria().andNameEqualTo(name).andTypeEqualTo(type);
		List<CodeEntity> list = codeEntityMapper.selectByExample(example);
		if(list.isEmpty()) {
			return null;
		}
		return codeEntityMapper.selectByExample(example).get(0);
	}

}
