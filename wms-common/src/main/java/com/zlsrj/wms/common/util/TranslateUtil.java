package com.zlsrj.wms.common.util;

import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;

public class TranslateUtil {
	public static <T> T translate(Object source, Class<T> target) {
		return JSON.parseObject(JSON.toJSONString(source), target);
	}

	public static <T> List<T> translateList(List<Object> sourceList, Class<T> target) {
//		List<T> targetList = new ArrayList<T>();
//		sourceList.forEach((object) -> {
//			T t = JSON.parseObject(JSON.toJSONString(object), target);
//			targetList.add(t);
//		});
//		
//		return targetList;

		return sourceList.stream().map(e -> translate(e, target)).collect(Collectors.toList());
	}
}
