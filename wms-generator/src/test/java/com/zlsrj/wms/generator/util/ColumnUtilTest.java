package com.zlsrj.wms.generator.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ColumnUtilTest {
	@Test
	public void commentTest() {
		String comment = "是否启用抄表账单通知短信（启用/不启用）";
		String c = comment.replaceAll("（.*）", "");
		log.info(c);
	}

	@Test
	public void commentTest2() {
		String comment = "是否启用抄表账单通知短信（启用/不启用）";
		String regEx = "（.*）";
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(comment);
		if (mat.find()) {
			log.info(mat.group(0));
			log.info("found");
		} else {
			log.info("Not found");
		}

	}

	@Test
	public void commentTest3() {
		String comment = "租户类型（1：使用单位；2：供应单位；3：内部运营）";

		String regEx = "（.*）";
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(comment);
		if (mat.find()) {
			String listString = mat.group(0);
			log.info(listString);
			listString = StrUtil.removePrefix(listString, "（");
			listString = StrUtil.removeSuffix(listString, "）");

			log.info(listString);

			String[] arr = listString.split("；");
			Map<String, String> options = new HashMap<String, String>();
			
			Arrays.asList(arr).forEach(s-> options.put(s.split("：")[0], s.split("：")[1]));
			
			log.info(options.toString());

		}

	}

}
