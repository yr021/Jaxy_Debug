/*
 * This file was automatically generated by EvoSuite
 * Mon May 14 15:37:46 GMT 2018
 */

package com.rac021.jax.api.interceptor;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import com.rac021.jax.api.interceptor.PoweredByRequestFilter;
import javax.ws.rs.container.ContainerRequestContext;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class PoweredByRequestFilter_ESTest extends PoweredByRequestFilter_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      PoweredByRequestFilter poweredByRequestFilter0 = new PoweredByRequestFilter();
      ContainerRequestContext containerRequestContext0 = mock(ContainerRequestContext.class, new ViolatedAssumptionAnswer());
      poweredByRequestFilter0.filter(containerRequestContext0);
  }
}
