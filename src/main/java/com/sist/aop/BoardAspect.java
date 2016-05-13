package com.sist.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/*
 *   JoinPoint
 *   
 *   @Before("")// 이전 
     @After("") // finally
     @AfterReturning("") // 정상 수행 
     @AfterThrowing("") // catch
     
     public void getConnection()
     {
     }
     public void disConnection()
     {
     }
     
     public void insert()
     {
        try
        {
           @Before("")
           @Arround() 1
                              @~~ conn.setAutoCommit(false)
                            핵심 코드               ===>   핵심코드
                              @~~ conn.commit()
                              
                              @Around
        }
        catch(Exception e)
        {
           @AfterThrowing("") ==> log파일
        }
        finally
        {
           @After("")
        }
        return;  @AfterReturning("")
     }
     OOP VS AOP => OOP 보완 (중복을 제거) => 인터셉터 
     
     
 */

import com.sist.dao.MyDataSource;
@Aspect
@Component
public class BoardAspect {
   @Autowired
   private MyDataSource ds;
   // within(com.sist.dao)
   @Before("execution(* com.sist.dao.BoardDAO.*(..))")// 이전 
   public void getConnection()
   {
	   ds.getConnection();
   }
   @After("execution(* com.sist.dao.BoardDAO.*(..))") // finally
   public void disConnection()
   {
	   ds.disConnection();
   }
   /*@AfterReturning() // 정상 수행 
   
   @AfterThrowing("") // catch
*/}






