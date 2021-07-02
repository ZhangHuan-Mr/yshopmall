package co.yixiang.modules.quartz.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 随机数生成工具类
 *
 * @author maobl
 * @date 2021-05-28
 */
public class RandomNumberUtil {

	private RandomNumberUtil() {
	}

	public static String createRandomNumber(int length) {
		StringBuilder strBuffer = new StringBuilder();
		Random rd = new Random();
		for (int i = 0; i < length; i++) {
			strBuffer.append(rd.nextInt(10));
		}

		return strBuffer.toString();
	}

	/**
	 * 生成长度为32位的流水号
	 *
	 * @return 请求单号
	 */
	public static String getRandomSequnceId() {
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = sdf.format(new Date());
		sb.append(date);
		sb.append("0000000000").append(createRandomNumber(8));
		return sb.toString();
	}
}
