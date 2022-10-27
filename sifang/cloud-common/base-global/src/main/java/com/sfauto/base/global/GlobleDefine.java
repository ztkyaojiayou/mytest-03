package com.sfauto.base.global;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;

/**
 * @author zxw
 *
 */

public class GlobleDefine {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static GlobleDefine globleDefine = null;
	private final static Resource res = new Resource();

	public static GlobleDefine getInstance(){
		if(globleDefine == null){
			globleDefine = new GlobleDefine();
		}
		return globleDefine;
	}	
		
	private GlobleDefine() {
	    //String filePath = getConfigPath() +File.separator+ "BaseTableDefine.xml";
	    //parseDefine = new ParseDefine(filePath);
	}

	public static String getRootPath(){
		String parentPath = null;
		
		String path;
		try {
			path = res.getRootPath();
			File file = null;
			//System.out.println("-------------path:"+path);
			if(path.startsWith("file:")){				
				URL url = new URL(path);
				file = new File(url.getFile());  
			}else {
				file = new File(path);
			}
			
			if(file != null && file.exists() && file.isFile()){
				String filepath = file.getParent();
				file = new File(filepath);
				if(file.exists() && file.isDirectory())
					parentPath = file.getParent();
				//如果不是jar包直接访问config
			}else if(file != null && file.exists() && file.isDirectory()){
				parentPath = path;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//log.debug("RootPath:{}",parentPath);
		/*System.out.printf("parentPath %s ", parentPath);
		System.out.println();*/
		return parentPath;
	}
	
	public static String getConfigPath(){
		String configPath = null;
		if(getRootPath() != null) {
			configPath = getRootPath()+File.separator+"config";
		}
		
		/*System.out.printf("configPath %s ", configPath);
		System.out.println();*/
		return configPath;
	}
	
	public static String getConfigFilePath(String fileName){
		String configPath = null;
		if(getRootPath() != null) {
			configPath = getRootPath()+File.separator+"config"+File.separator+fileName;
		}
		
		/*System.out.printf("configFilePath %s ", configPath);
		System.out.println();*/
		return "file:"+configPath;
	}

	public static boolean isFlagBitSet(int flag, int bit){
		int filter = 1 << bit;
		int res = flag & filter;
		return res == 0 ? false : true;
	}

	//返回设置过的flag
	public static int setFlagBit(int flag, int bit, boolean setTrue){
		int lv = 1 << bit;
		if(setTrue == true){
			return ( lv | flag);
		}else{
			lv = ~lv;
			return (flag & lv);
		}
	}

}
