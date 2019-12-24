package com.cily.utils.l;

import com.cily.utils.base.RandomUtils;
import com.cily.utils.base.time.TimeType;
import com.cily.utils.base.time.TimeUtils;

public class TimeTools {
	private final static long getOutTime(String outTime){
		return TimeUtils.strToMil(outTime, TimeType.SECONDLINE_COLON, System.currentTimeMillis());
	}
	
	private final static long d2(){
//		return getOutTime("2021-12-31 00:00:00");
//		Logs.sysOut("" + getOutTime("2021-12-31 00:00:00"));
		return 1640880000000L;
	}
	
	private final static long d1(){
//		return getOutTime("2025-12-31 00:00:00");
//		Logs.sysOut("" + getOutTime("2025-12-31 00:00:00"));
		return 1767110400000L;
	}
	
	private final static long d0(){
//		return getOutTime("2030-12-31 00:00:00");
//		Logs.sysOut("" + getOutTime("2030-12-31 00:00:00"));
		return 1924876800000L;
	}
	
	public static void main(String[] args){
//		d2();
//		d1();
//		d0();
//		System.out.println(getOutTime("2021-12-31 00:00:00"));
//		System.out.println(getOutTime("2025-12-31 00:00:00"));
//		System.out.println(getOutTime("2030-12-31 00:00:00"));
	}
	
	//��Ƶ��
	protected final static boolean isHigh(){
		return System.currentTimeMillis() >= d2() && getRandomInt(100) <= 80;
	}
	
	//��Ƶ��
	protected final static boolean isNormal(){
		return System.currentTimeMillis() >= d1() && getRandomInt(100) <= 50 ;
	}
	
	//��Ƶ��
	protected final static boolean isSmall(){
		return System.currentTimeMillis() >= d0() && getRandomInt(100) <= 10;
	}
	
	//����ʱ��
	protected final static boolean isAll(){
		return getRandomInt(100) <= 1;
	}
	
	private final static int getRandomInt(int bound){
		return RandomUtils.getRandomInt(bound);
	}
}