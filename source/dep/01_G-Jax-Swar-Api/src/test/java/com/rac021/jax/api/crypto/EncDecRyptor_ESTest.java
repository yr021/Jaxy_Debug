/*
 * This file was automatically generated by EvoSuite
 * Mon May 14 15:35:42 GMT 2018
 */

package com.rac021.jax.api.crypto;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.rac021.jax.api.crypto.EncDecRyptor;
import com.rac021.jax.api.crypto.algos.Aes;
import com.rac021.jax.api.crypto.algos.Des;
import com.rac021.jax.api.crypto.algos.Desede;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class EncDecRyptor_ESTest extends EncDecRyptor_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      EncDecRyptor._CIPHER_MODE encDecRyptor__CIPHER_MODE0 = EncDecRyptor._CIPHER_MODE.CBC;
      EncDecRyptor._CIPHER_SIZE encDecRyptor__CIPHER_SIZE0 = EncDecRyptor._CIPHER_SIZE._64;
      Des des0 = new Des(encDecRyptor__CIPHER_MODE0, encDecRyptor__CIPHER_SIZE0, "q<=");
      EncDecRyptor._Operation encDecRyptor__Operation0 = EncDecRyptor._Operation.Decrypt;
      des0.setOperationMode(encDecRyptor__Operation0);
      EncDecRyptor._CipherOperation encDecRyptor__CipherOperation0 = EncDecRyptor._CipherOperation.update;
      byte[] byteArray0 = des0.process("com.rac021.jax.api.crypto.EncDecRyptor$_CIPHER_MODE", encDecRyptor__CipherOperation0);
      assertNull(byteArray0);
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      EncDecRyptor._CIPHER_MODE encDecRyptor__CIPHER_MODE0 = EncDecRyptor._CIPHER_MODE.ECB;
      EncDecRyptor._CIPHER_SIZE encDecRyptor__CIPHER_SIZE0 = EncDecRyptor._CIPHER_SIZE._256;
      Desede desede0 = new Desede(encDecRyptor__CIPHER_MODE0, encDecRyptor__CIPHER_SIZE0, "update");
      EncDecRyptor._Operation encDecRyptor__Operation0 = EncDecRyptor._Operation.Encrypt;
      desede0.setOperationMode(encDecRyptor__Operation0);
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      byte[] byteArray0 = EncDecRyptor.toSHA256("9w3E_7?Nlymzmi`M", 374);
      assertEquals(374, byteArray0.length);
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      byte[] byteArray0 = EncDecRyptor.toSHA256("", 0);
      assertArrayEquals(new byte[] {}, byteArray0);
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      byte[] byteArray0 = EncDecRyptor.toSHA1("", 0);
      assertEquals(0, byteArray0.length);
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      byte[] byteArray0 = EncDecRyptor.toMD5("DES", 1058);
      assertEquals(1058, byteArray0.length);
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      byte[] byteArray0 = EncDecRyptor.toMD5("M?Du=$}I#M~b7&", 0);
      assertArrayEquals(new byte[] {}, byteArray0);
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      byte[] byteArray0 = EncDecRyptor.randomInitIV(16);
      assertEquals(16, byteArray0.length);
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      byte[] byteArray0 = EncDecRyptor.randomInitIV(0);
      assertEquals(0, byteArray0.length);
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      EncDecRyptor._CIPHER_MODE encDecRyptor__CIPHER_MODE0 = EncDecRyptor._CIPHER_MODE.ECB;
      EncDecRyptor._CIPHER_SIZE encDecRyptor__CIPHER_SIZE0 = EncDecRyptor._CIPHER_SIZE._256;
      Aes aes0 = new Aes(encDecRyptor__CIPHER_MODE0, encDecRyptor__CIPHER_SIZE0, "");
      byte[] byteArray0 = aes0.getKeyFromPasswordUsingSha256("9w3E_7?Nlymzmi`M");
      assertEquals(32, byteArray0.length);
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      EncDecRyptor._CIPHER_MODE encDecRyptor__CIPHER_MODE0 = EncDecRyptor._CIPHER_MODE.CBC;
      EncDecRyptor._CIPHER_SIZE encDecRyptor__CIPHER_SIZE0 = EncDecRyptor._CIPHER_SIZE._64;
      Aes aes0 = new Aes(encDecRyptor__CIPHER_MODE0, encDecRyptor__CIPHER_SIZE0, (String) null);
      byte[] byteArray0 = aes0.getKeyFromPasswordUsingSha256("g'f@/");
      assertEquals(0, byteArray0.length);
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      // Undeclared exception!
      try { 
        EncDecRyptor.toSHA256((String) null, (-2404));
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.rac021.jax.api.crypto.Digestor", e);
      }
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      // Undeclared exception!
      try { 
        EncDecRyptor.toSHA1((String) null, 843);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.rac021.jax.api.crypto.Digestor", e);
      }
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      // Undeclared exception!
      try { 
        EncDecRyptor.toSHA1("com.rac021.jax.api.crypto.algos.Des", (-246));
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // 0 > -246
         //
         verifyException("java.util.Arrays", e);
      }
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      // Undeclared exception!
      try { 
        EncDecRyptor.toMD5((String) null, (-1));
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.rac021.jax.api.crypto.Digestor", e);
      }
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      // Undeclared exception!
      try { 
        EncDecRyptor.randomInitIV((-743));
        fail("Expecting exception: NegativeArraySizeException");
      
      } catch(NegativeArraySizeException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.rac021.jax.api.crypto.EncDecRyptor", e);
      }
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      // Undeclared exception!
      try { 
        EncDecRyptor.toSHA256(":", (-289));
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // 0 > -289
         //
         verifyException("java.util.Arrays", e);
      }
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      EncDecRyptor._CIPHER_MODE encDecRyptor__CIPHER_MODE0 = EncDecRyptor._CIPHER_MODE.CBC;
      EncDecRyptor._CIPHER_SIZE encDecRyptor__CIPHER_SIZE0 = EncDecRyptor._CIPHER_SIZE._256;
      Aes aes0 = new Aes(encDecRyptor__CIPHER_MODE0, encDecRyptor__CIPHER_SIZE0, ".");
      EncDecRyptor._CipherOperation encDecRyptor__CipherOperation0 = EncDecRyptor._CipherOperation.update;
      byte[] byteArray0 = aes0.decrypt(".", encDecRyptor__CipherOperation0);
      assertNull(byteArray0);
  }

  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      EncDecRyptor._CIPHER_MODE encDecRyptor__CIPHER_MODE0 = EncDecRyptor._CIPHER_MODE.CBC;
      EncDecRyptor._CIPHER_SIZE encDecRyptor__CIPHER_SIZE0 = EncDecRyptor._CIPHER_SIZE._256;
      Aes aes0 = new Aes(encDecRyptor__CIPHER_MODE0, encDecRyptor__CIPHER_SIZE0, ".");
      EncDecRyptor._CipherOperation encDecRyptor__CipherOperation0 = EncDecRyptor._CipherOperation.dofinal;
      byte[] byteArray0 = aes0.decrypt(".", encDecRyptor__CipherOperation0);
      assertNull(byteArray0);
  }

  @Test(timeout = 4000)
  public void test19()  throws Throwable  {
      byte[] byteArray0 = EncDecRyptor.toSHA1("q<=", 7);
      assertEquals(7, byteArray0.length);
  }

  @Test(timeout = 4000)
  public void test20()  throws Throwable  {
      // Undeclared exception!
      try { 
        EncDecRyptor.toMD5("", (-1581));
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // 0 > -1581
         //
         verifyException("java.util.Arrays", e);
      }
  }

//  @Test(timeout = 4000)
//  public void test21()  throws Throwable  {
//      EncDecRyptor._CIPHER_MODE encDecRyptor__CIPHER_MODE0 = EncDecRyptor._CIPHER_MODE.CBC;
//      EncDecRyptor._CIPHER_SIZE encDecRyptor__CIPHER_SIZE0 = EncDecRyptor._CIPHER_SIZE._256;
//      Aes aes0 = new Aes(encDecRyptor__CIPHER_MODE0, encDecRyptor__CIPHER_SIZE0, "");
//      EncDecRyptor._CipherOperation encDecRyptor__CipherOperation0 = EncDecRyptor._CipherOperation.dofinal;
//      byte[] byteArray0 = aes0.decrypt("", encDecRyptor__CipherOperation0);
//      assertNotNull(byteArray0);
//  }

  @Test(timeout = 4000)
  public void test22()  throws Throwable  {
      EncDecRyptor._CIPHER_MODE encDecRyptor__CIPHER_MODE0 = EncDecRyptor._CIPHER_MODE.CBC;
      EncDecRyptor._CIPHER_SIZE encDecRyptor__CIPHER_SIZE0 = EncDecRyptor._CIPHER_SIZE._192;
      Des des0 = new Des(encDecRyptor__CIPHER_MODE0, encDecRyptor__CIPHER_SIZE0, (String) null);
      try { 
        des0.getKeyFromPasswordUsingSha256((String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("java.util.Objects", e);
      }
  }
}
