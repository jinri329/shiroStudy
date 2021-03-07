package com.zyy.shirodemo;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShiroApplicationTests {

	@Test
	void contextLoads() {
		//md5Hash 加密方式 可以传入三个参数 密码的明文 salt盐（随机字符串） 加密次数
		//13ee501394586a284b0e19c22f12bc2d 仅加密
		//95ac685cf38f11ecc484cae4850e7fe9	加密又加盐
		//b60f681be12a19ee002e3d5b93a71aa5 加密加盐+加密次数
		Md5Hash md5Hash = new Md5Hash("123","ac31",1024);
		//628466f3bac1c71041ec3bf8a786c144
		//628466f3bac1c71041ec3bf8a786c144
		System.out.println(md5Hash);
		//d47f4fec80ef7c32eb158d0c86ce705d  ac31
		//d47f4fec80ef7c32eb158d0c86ce705d
	}

}
