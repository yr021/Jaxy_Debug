/*
 * This file was automatically generated by EvoSuite
 * Mon May 14 15:33:26 GMT 2018
 */

package com.rac021.jax.api.crypto.algos;

import org.junit.Test;
import static org.junit.Assert.*;
import com.rac021.jax.api.crypto.EncDecRyptor;
import com.rac021.jax.api.crypto.algos.Aes;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class Aes_ESTest extends Aes_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      EncDecRyptor._CIPHER_MODE encDecRyptor__CIPHER_MODE0 = EncDecRyptor._CIPHER_MODE.ECB;
      EncDecRyptor._CIPHER_SIZE encDecRyptor__CIPHER_SIZE0 = EncDecRyptor._CIPHER_SIZE._192;
      Aes aes0 = new Aes(encDecRyptor__CIPHER_MODE0, encDecRyptor__CIPHER_SIZE0, "'(aZ<[sdI#n<Y>T{g");
      EncDecRyptor._Operation encDecRyptor__Operation0 = EncDecRyptor._Operation.Decrypt;
      aes0.setOperationMode(encDecRyptor__Operation0);
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      EncDecRyptor._CIPHER_MODE encDecRyptor__CIPHER_MODE0 = EncDecRyptor._CIPHER_MODE.ECB;
      EncDecRyptor._CIPHER_SIZE encDecRyptor__CIPHER_SIZE0 = EncDecRyptor._CIPHER_SIZE._128;
      Aes aes0 = new Aes(encDecRyptor__CIPHER_MODE0, encDecRyptor__CIPHER_SIZE0, "");
      EncDecRyptor._CipherOperation encDecRyptor__CipherOperation0 = EncDecRyptor._CipherOperation.dofinal;
      EncDecRyptor._Operation encDecRyptor__Operation0 = EncDecRyptor._Operation.Encrypt;
      aes0.setOperationMode(encDecRyptor__Operation0);
      byte[] byteArray0 = aes0.process("dofinal", encDecRyptor__CipherOperation0);
      assertEquals(16, byteArray0.length);
      assertNotNull(byteArray0);
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      EncDecRyptor._CIPHER_MODE encDecRyptor__CIPHER_MODE0 = EncDecRyptor._CIPHER_MODE.CBC;
      EncDecRyptor._CIPHER_SIZE encDecRyptor__CIPHER_SIZE0 = EncDecRyptor._CIPHER_SIZE._128;
      Aes aes0 = new Aes(encDecRyptor__CIPHER_MODE0, encDecRyptor__CIPHER_SIZE0, "}XrCjs*PP[D");
      EncDecRyptor._CipherOperation encDecRyptor__CipherOperation0 = EncDecRyptor._CipherOperation.update;
      byte[] byteArray0 = aes0.decrypt("}XrCjs*PP[D", encDecRyptor__CipherOperation0);
      assertNull(byteArray0);
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      EncDecRyptor._CIPHER_MODE encDecRyptor__CIPHER_MODE0 = EncDecRyptor._CIPHER_MODE.ECB;
      EncDecRyptor._CIPHER_SIZE encDecRyptor__CIPHER_SIZE0 = EncDecRyptor._CIPHER_SIZE._128;
      Aes aes0 = new Aes(encDecRyptor__CIPHER_MODE0, encDecRyptor__CIPHER_SIZE0, "");
      EncDecRyptor._CipherOperation encDecRyptor__CipherOperation0 = EncDecRyptor._CipherOperation.dofinal;
      EncDecRyptor._Operation encDecRyptor__Operation0 = EncDecRyptor._Operation.Encrypt;
      aes0.setOperationMode(encDecRyptor__Operation0);
      byte[] byteArray0 = aes0.decrypt("k8", encDecRyptor__CipherOperation0);
      assertEquals(16, byteArray0.length);
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      EncDecRyptor._CIPHER_MODE encDecRyptor__CIPHER_MODE0 = EncDecRyptor._CIPHER_MODE.CBC;
      EncDecRyptor._CIPHER_SIZE encDecRyptor__CIPHER_SIZE0 = EncDecRyptor._CIPHER_SIZE._128;
      Aes aes0 = new Aes(encDecRyptor__CIPHER_MODE0, encDecRyptor__CIPHER_SIZE0, "#q3e");
      byte[] byteArray0 = aes0.getIvBytesEncoded64();
      assertEquals(24, byteArray0.length);
      assertNotNull(byteArray0);
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      EncDecRyptor._CIPHER_MODE encDecRyptor__CIPHER_MODE0 = EncDecRyptor._CIPHER_MODE.ECB;
      EncDecRyptor._CIPHER_SIZE encDecRyptor__CIPHER_SIZE0 = EncDecRyptor._CIPHER_SIZE._128;
      Aes aes0 = new Aes(encDecRyptor__CIPHER_MODE0, encDecRyptor__CIPHER_SIZE0, "");
      byte[] byteArray0 = aes0.getIvBytesEncoded64();
      assertNull(byteArray0);
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      EncDecRyptor._CIPHER_MODE encDecRyptor__CIPHER_MODE0 = EncDecRyptor._CIPHER_MODE.ECB;
      EncDecRyptor._CIPHER_SIZE encDecRyptor__CIPHER_SIZE0 = EncDecRyptor._CIPHER_SIZE._128;
      Aes aes0 = new Aes(encDecRyptor__CIPHER_MODE0, encDecRyptor__CIPHER_SIZE0, "");
      EncDecRyptor._CipherOperation encDecRyptor__CipherOperation0 = EncDecRyptor._CipherOperation.update;
      byte[] byteArray0 = aes0.process("", encDecRyptor__CipherOperation0);
      assertNull(byteArray0);
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      EncDecRyptor._CIPHER_MODE encDecRyptor__CIPHER_MODE0 = EncDecRyptor._CIPHER_MODE.ECB;
      EncDecRyptor._CIPHER_SIZE encDecRyptor__CIPHER_SIZE0 = EncDecRyptor._CIPHER_SIZE._256;
      Aes aes0 = new Aes(encDecRyptor__CIPHER_MODE0, encDecRyptor__CIPHER_SIZE0, (String) null);
      EncDecRyptor._Operation encDecRyptor__Operation0 = EncDecRyptor._Operation.Encrypt;
      aes0.setOperationMode(encDecRyptor__Operation0);
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      EncDecRyptor._CIPHER_MODE encDecRyptor__CIPHER_MODE0 = EncDecRyptor._CIPHER_MODE.CBC;
      EncDecRyptor._CIPHER_SIZE encDecRyptor__CIPHER_SIZE0 = EncDecRyptor._CIPHER_SIZE._128;
      Aes aes0 = new Aes(encDecRyptor__CIPHER_MODE0, encDecRyptor__CIPHER_SIZE0, "#q3e");
      EncDecRyptor._Operation encDecRyptor__Operation0 = EncDecRyptor._Operation.Encrypt;
      aes0.setOperationMode(encDecRyptor__Operation0);
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      EncDecRyptor._CIPHER_MODE encDecRyptor__CIPHER_MODE0 = EncDecRyptor._CIPHER_MODE.ECB;
      EncDecRyptor._CIPHER_SIZE encDecRyptor__CIPHER_SIZE0 = EncDecRyptor._CIPHER_SIZE._128;
      Aes aes0 = new Aes(encDecRyptor__CIPHER_MODE0, encDecRyptor__CIPHER_SIZE0, "");
      EncDecRyptor._CipherOperation encDecRyptor__CipherOperation0 = EncDecRyptor._CipherOperation.dofinal;
      aes0.decrypt("", encDecRyptor__CipherOperation0);
      byte[] byteArray0 = aes0.process("", encDecRyptor__CipherOperation0);
      assertEquals(0, byteArray0.length);
      assertNotNull(byteArray0);
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      EncDecRyptor._CIPHER_MODE encDecRyptor__CIPHER_MODE0 = EncDecRyptor._CIPHER_MODE.CBC;
      EncDecRyptor._CIPHER_SIZE encDecRyptor__CIPHER_SIZE0 = EncDecRyptor._CIPHER_SIZE._128;
      Aes aes0 = new Aes(encDecRyptor__CIPHER_MODE0, encDecRyptor__CIPHER_SIZE0, "#q3e");
      EncDecRyptor._CipherOperation encDecRyptor__CipherOperation0 = EncDecRyptor._CipherOperation.dofinal;
      EncDecRyptor._Operation encDecRyptor__Operation0 = EncDecRyptor._Operation.Decrypt;
      aes0.setOperationMode(encDecRyptor__Operation0);
      byte[] byteArray0 = aes0.process("#q3e", encDecRyptor__CipherOperation0);
      assertNull(byteArray0);
  }
}
