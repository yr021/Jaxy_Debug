/*
 * This file was automatically generated by EvoSuite
 * Mon May 14 15:37:07 GMT 2018
 */

package com.rac021.jax.api.crypto;

import org.junit.Test;
import static org.junit.Assert.*;
import com.rac021.jax.api.crypto.JceSecurity;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class JceSecurity_ESTest extends JceSecurity_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      JceSecurity.unlimit();
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      JceSecurity jceSecurity0 = new JceSecurity();
  }
}
