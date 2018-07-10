/*
 * This file was automatically generated by EvoSuite
 * Mon May 14 15:37:20 GMT 2018
 */

package com.rac021.jax.api.qualifiers;

import org.junit.Test;
import static org.junit.Assert.*;
import com.rac021.jax.api.qualifiers.ServiceRegistryAnnotationLiteral;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class ServiceRegistryAnnotationLiteral_ESTest extends ServiceRegistryAnnotationLiteral_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      ServiceRegistryAnnotationLiteral serviceRegistryAnnotationLiteral0 = ServiceRegistryAnnotationLiteral.ServiceRegistry((String) null);
      String string0 = serviceRegistryAnnotationLiteral0.value();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      ServiceRegistryAnnotationLiteral serviceRegistryAnnotationLiteral0 = ServiceRegistryAnnotationLiteral.ServiceRegistry("5U");
      String string0 = serviceRegistryAnnotationLiteral0.value();
      assertEquals("5U", string0);
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      ServiceRegistryAnnotationLiteral serviceRegistryAnnotationLiteral0 = ServiceRegistryAnnotationLiteral.ServiceRegistry("");
      String string0 = serviceRegistryAnnotationLiteral0.value();
      assertEquals("", string0);
  }
}
