package com.cyou.base.aop;


import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.springframework.security.context.SecurityContextHolder;

import com.cyou.base.bean.User;
  

public class ExecuteLog {
	private Logger log = Logger.getLogger(ExecuteLog.class);

    public void doSystemLog(JoinPoint joinPoint){ 
    	StringBuffer logmsg = new StringBuffer("");
    	logmsg.append("class name：");
    	logmsg.append(joinPoint.getTarget().getClass().getName());
    	logmsg.append(";   ");
    	logmsg.append("method name：");
    	logmsg.append(joinPoint.getSignature().getName());
    	logmsg.append(";   ");
    	 //得到被拦截方法参数，并打印  
        Object[] args = joinPoint.getArgs();  
        for (int i = 0; i < args.length; i++) {  
        	logmsg.append("method args: " + i + " ： " + args[i]);
        } 
        logmsg.append(";   ");
        User currentUser = null;
        try{
        	if(SecurityContextHolder.getContext()!=null && SecurityContextHolder.getContext().getAuthentication()!=null){
            	currentUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            	logmsg.append("username："+currentUser.getUsername());
            }
        }catch(Exception e){
        }
        
        logmsg.append(";   ");
        //记录系统日志具体实现  
        log.info(logmsg);
    } 
}
