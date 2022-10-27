package com.sfauto.base.global;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLDecoder;

/**
 * @author zxw
 *
 */

public class Resource {

	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	private  String rootPath ;

	public String getRootPath() throws IOException, URISyntaxException {

		if(rootPath!=null){
			return rootPath;
		}
		
		String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();

		//jar包
		if(path.indexOf("jar")!=-1){
			log.debug("=========jar config========");
			path = path.substring(0, path.indexOf("jar") + 3);
		//非jar包
		}else if(path.indexOf(System.getProperty("path.separator"))!=-1){
			log.debug("=========project config========");
			path = path.substring(0, path.indexOf(System.getProperty("path.separator")) + System.getProperty("path.separator").length());
		}

		path = URLDecoder.decode(path, "utf-8");
		log.debug("getRootPath：{}",path);
		rootPath = path;
		return path;
	}


}
