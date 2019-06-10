package com.sm.business.service;

import org.iframework.commons.util.MavenUtil;
import org.junit.Test;

public class MavenUtilsTest extends MavenUtil {

	@Test
	public void test() {
		MavenUtil.delLastUpdated("D:\\apache-maven-repo-yxb");
	}

}
