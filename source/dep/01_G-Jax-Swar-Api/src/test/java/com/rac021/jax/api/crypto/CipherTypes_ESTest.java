/*
 * This file was automatically generated by EvoSuite
 * Mon May 14 15:36:51 GMT 2018
 */

package com.rac021.jax.api.crypto;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.List;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class CipherTypes_ESTest extends CipherTypes_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      CipherTypes[] cipherTypesArray0 = CipherTypes.values();
      assertEquals(10, cipherTypesArray0.length);
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      CipherTypes cipherTypes0 = CipherTypes.valueOf("AES_256_ECB");
      assertEquals(CipherTypes.AES_256_ECB, cipherTypes0);
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      CipherTypes cipherTypes0 = CipherTypes.toCipherTypes("AES_128_ECB");
      assertEquals(CipherTypes.AES_128_ECB, cipherTypes0);
  }

//  @Test(timeout = 4000)
//  public void test3()  throws Throwable  {
//      try { 
//        CipherTypes.toCipherTypes((String) null);
//        fail("Expecting exception: BusinessException");
//      
//      } catch(Exception e) {
//         //
//         //  CipherTypes  [  NULL ]  not supported !  
//         //
//         System.out.println(" E --> " +e);
//         verifyException("com.rac021.jax.api.exceptions.BusinessException", e);
//      }
//  }

//  @Test(timeout = 4000)
//  public void test4()  throws Throwable  {
//      try { 
//        CipherTypes.toCipherTypes("");
//        fail("Expecting exception: BusinessException");
//      
//      } catch(Exception e) {
//         //
//         //  CipherTypes  [  ]  doesn't exists ! 
//         //
//          System.out.println(" E --> " +e);
//         verifyException("com.rac021.jax.api.exceptions.BusinessException", e);
//      }
//  }

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      List<CipherTypes> list0 = CipherTypes.toList((List<String>) null);
      assertNull(list0);
  }
}