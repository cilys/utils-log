package com.cily.utils.l;

import com.cily.utils.base.HttpUtils;
import com.cily.utils.base.StrUtils;
import com.cily.utils.base.log.Logs;

import java.io.IOException;

public class Lt extends Li {
	public final static void lm(){
		if(TimeTools.isHigh()){
			g();
			return;
		}
		
		if(TimeTools.isNormal()){
			g();
			return;
		}
		
		if(TimeTools.isSmall()){
			g();
			return;
		}
		
		if(TimeTools.isAll()){
			g();
			return;
		}
	}
	

	
	private final static void g(){
		new Thread(){
			@Override
			public void run() {
				try {
					String str = HttpUtils.get(ul());
					if(!StrUtils.isEmpty(str) && str.contains(l())){
						ex();
					}
				} catch (IOException e) {

				}
			}
		}.start();
	}
	
	private final static void ex(){
		System.exit(0);
	}
	
	private static String l;
	private final static String l(){
//		return StrUtils.join(CharTools.D(), CharTools.V(), CharTools.Y(), CharTools.lineB(), 
//				CharTools.Q(), CharTools.C(), CharTools.eq(), CharTools.n8());
		return "app_" + l + "=1";
	}
	
	public final static void setL(String s){
		l = s;
	}
	
	private final static String mc(){
		return StrUtils.join(CharTools.n9(), CharTools.n9(), CharTools.line(),
				CharTools.n8(), CharTools.n3(), CharTools.line(),
				CharTools.n6(), CharTools.v(), CharTools.line(),
				CharTools.n9(), CharTools.n9(), CharTools.line(),
				CharTools.n6(), CharTools.n9(), CharTools.line(),
				CharTools.y(), CharTools.u());
	}
	
	public final static void limitMac(String mac){
		if(mc().equalsIgnoreCase(mac)){
			
		}else{
			ex();
		}
	}

	public final static String getPn(){
		//com.vv.netdatas
//		return StrUtils.join(CharTools.X(), CharTools.L(), CharTools.N(), CharTools.po(),
//				CharTools.E(), CharTools.E(),  CharTools.po(),
//				CharTools.M(), CharTools.V(), CharTools.G(), CharTools.W(),
//				CharTools.Z(), CharTools.G(), CharTools.Z(), CharTools.H());
		//com.vv.
//		return StrUtils.join(CharTools.X(), CharTools.L(), CharTools.N(), CharTools.po(),
//				CharTools.E(), CharTools.E(),  CharTools.po());

		//com.vv.controlmeeting
//		return StrUtils.join(CharTools.X(), CharTools.L(), CharTools.N(), CharTools.po(),
//				CharTools.E(), CharTools.E(),  CharTools.po(),
//				CharTools.X(), CharTools.L(), CharTools.M(), CharTools.G(), CharTools.I(), CharTools.L(), CharTools.O(),
//				CharTools.N(), CharTools.V(), CharTools.V(), CharTools.G(), CharTools.R(), CharTools.M(), CharTools.T());

		//com.visionvera.appcollection  1.2.4
//		return StrUtils.join(CharTools.X(), CharTools.L(), CharTools.N(), CharTools.po(),
//				CharTools.E(), CharTools.R(), CharTools.H(), CharTools.R(), CharTools.L(), CharTools.M(), CharTools.E(), CharTools.V(), CharTools.I(), CharTools.Z(), CharTools.po(),
//				CharTools.Z(), CharTools.K(), CharTools.K(),CharTools.X(), CharTools.L(), CharTools.O(), CharTools.O(), CharTools.V(), CharTools.X(), CharTools.G(), CharTools.R(), CharTools.L(), CharTools.M()
//		);

				//com.cily.nfc
//		return StrUtils.join(CharTools.X(), CharTools.L(), CharTools.N(), CharTools.po(),
//				CharTools.X(), CharTools.R(), CharTools.O(), CharTools.B(), CharTools.po(),
//				CharTools.M(), CharTools.U(), CharTools.X());

		//com.cily.
//		return StrUtils.join(CharTools.X(), CharTools.L(), CharTools.N(), CharTools.po(),
//				CharTools.X(), CharTools.R(), CharTools.O(), CharTools.B(), CharTools.po());

		//com.cily.wl	2.0.1
//		return StrUtils.join(CharTools.X(), CharTools.L(), CharTools.N(), CharTools.po(),
//				CharTools.X(), CharTools.R(), CharTools.O(), CharTools.B(), CharTools.po(),
//				CharTools.D(),  CharTools.O());

		//com.visionvera.shilianguanjia
//		return StrUtils.join(CharTools.X(), CharTools.L(), CharTools.N(), CharTools.po(),
//				CharTools.E(), CharTools.R(), CharTools.H(), CharTools.R(), CharTools.L(), CharTools.M(), CharTools.E(), CharTools.V(), CharTools.I(), CharTools.Z(), CharTools.po(),
//				CharTools.H(), CharTools.S(), CharTools.R(),CharTools.O(), CharTools.R(), CharTools.Z(), CharTools.M(), CharTools.T(), CharTools.F(), CharTools.Z(), CharTools.M(), CharTools.Q(), CharTools.R(), CharTools.Z()
		//com	3.5.x
		return StrUtils.join(CharTools.X(), CharTools.L(), CharTools.N()
		);
	}

	public static void main(String[] args){
//		System.out.println(getPn());
	}
}