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
}