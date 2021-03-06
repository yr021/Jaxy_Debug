/*
 * This file was automatically generated by EvoSuite
 * Mon May 14 15:32:18 GMT 2018
 */

package com.rac021.jax.api.crypto;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.rac021.jax.api.crypto.FactoryCipher;
import com.rac021.jax.api.crypto.ICryptor;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class FactoryCipher_ESTest extends FactoryCipher_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      ICryptor iCryptor0 = FactoryCipher.getCipher("AES_192_ECB", "DES");
      assertNotNull(iCryptor0);
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      ICryptor iCryptor0 = FactoryCipher.getCipher("aes_192_cbc", "teF.j!o");
      assertNotNull(iCryptor0);
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      ICryptor iCryptor0 = FactoryCipher.getCipher("aes_128_ecb", "Iz?H!#HFMvWy/xPyHn");
      assertNotNull(iCryptor0);
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      ICryptor iCryptor0 = FactoryCipher.getCipher("AES_128_CBC", " No Cipher found ! ");
      assertNotNull(iCryptor0);
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      ICryptor iCryptor0 = FactoryCipher.getCipher("DESede_192_ECB", "DESede_192_ECB");
      assertNotNull(iCryptor0);
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      ICryptor iCryptor0 = FactoryCipher.getCipher("desede_192_cbc", "desede_192_cbc");
      assertNotNull(iCryptor0);
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      ICryptor iCryptor0 = FactoryCipher.getCipher("des_64_ecb", "des_64_ecb");
      assertNotNull(iCryptor0);
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      ICryptor iCryptor0 = FactoryCipher.getCipher("DES_64_CBC", "DES_64_CBC");
      assertNotNull(iCryptor0);
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      ICryptor iCryptor0 = FactoryCipher.getCipher("aes_256_ecb", "z5-D!d-WGY1{OTei");
      assertNotNull(iCryptor0);
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      ICryptor iCryptor0 = FactoryCipher.getCipher("AES_256_CBC", "DES_64_CBC");
      assertNotNull(iCryptor0);
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      try { 
        FactoryCipher.getCipher((String) null, "Sk");
        fail("Expecting exception: Exception");
      
      } catch(Exception e) {
         //
         //  Password Can't be null 
         //
         verifyException("com.rac021.jax.api.crypto.FactoryCipher", e);
      }
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      // Undeclared exception!
      try { 
        FactoryCipher.getCipher("", "<6)#[");
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         //  No Cipher found ! 
         //
         verifyException("com.rac021.jax.api.crypto.FactoryCipher", e);
      }
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      try { 
        FactoryCipher.getCipher((String) null, (String) null);
        fail("Expecting exception: Exception");
      
      } catch(Exception e) {
         //
         //  Password Can't be null 
         //
         verifyException("com.rac021.jax.api.crypto.FactoryCipher", e);
      }
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      FactoryCipher factoryCipher0 = new FactoryCipher();
  }
}
