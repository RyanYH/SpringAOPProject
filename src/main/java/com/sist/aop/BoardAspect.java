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
 *   @Before("")// ���� 
     @After("") // finally
     @AfterReturning("") // ���� ���� 
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
                            �ٽ� �ڵ�               ===>   �ٽ��ڵ�
                              @~~ conn.commit()
                              
                              @Around
        }
        catch(Exception e)
        {
           @AfterThrowing("") ==> log����
        }
        finally
        {
           @After("")
        }
        return;  @AfterReturning("")
     }
     OOP VS AOP => OOP ���� (�ߺ��� ����) => ���ͼ��� 
     
     
 */

import com.sist.dao.MyDataSource;
@Aspect
@Component
public class BoardAspect {
   @Autowired
   private MyDataSource ds;
   // within(com.sist.dao)
   @Before("execution(* com.sist.dao.BoardDAO.*(..))")// ���� 
   public void getConnection()
   {
	   ds.getConnection();
   }
   @After("execution(* com.sist.dao.BoardDAO.*(..))") // finally
   public void disConnection()
   {
	   ds.disConnection();
   }
   /*@AfterReturning() // ���� ���� 
   
   @AfterThrowing("") // catch
*/}






