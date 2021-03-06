/*
 * This file was automatically generated by EvoSuite
 * Mon May 14 15:36:20 GMT 2018
 */

package com.rac021.jax.api.streamers;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.rac021.jax.api.manager.IDto;
import com.rac021.jax.api.manager.IResource;
import com.rac021.jax.api.streamers.ResourceWraper;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class ResourceWraper_ESTest extends ResourceWraper_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      Class<String> class0 = String.class;
      LinkedList<String> linkedList0 = new LinkedList<String>();
      ResourceWraper resourceWraper0 = new ResourceWraper((IResource) null, class0, linkedList0);
      linkedList0.add("");
      List<IDto> list0 = resourceWraper0.getDtoIterable((EntityManager) null, 1, 1, linkedList0);
      assertTrue(list0.isEmpty());
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      IResource iResource0 = mock(IResource.class, new ViolatedAssumptionAnswer());
      Class<Object> class0 = Object.class;
      ResourceWraper resourceWraper0 = new ResourceWraper(iResource0, class0, (List<String>) null);
      resourceWraper0.setIsFinished(true);
      boolean boolean0 = resourceWraper0.isFinished();
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      Class<Integer> class0 = Integer.class;
      LinkedList<String> linkedList0 = new LinkedList<String>();
      ResourceWraper resourceWraper0 = new ResourceWraper((IResource) null, class0, linkedList0);
      IResource iResource0 = resourceWraper0.getResource();
      assertNull(iResource0);
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      LinkedList<String> linkedList0 = new LinkedList<String>();
      ResourceWraper resourceWraper0 = new ResourceWraper((IResource) null, (Class) null, linkedList0);
      Class class0 = resourceWraper0.getDto();
      assertNull(class0);
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      IResource iResource0 = mock(IResource.class, new ViolatedAssumptionAnswer());
      Class<String> class0 = String.class;
      ResourceWraper resourceWraper0 = new ResourceWraper(iResource0, class0, (List<String>) null);
      // Undeclared exception!
      try { 
        resourceWraper0.getDtoIterable((EntityManager) null, 2, 2, (List<String>) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.rac021.jax.api.streamers.ResourceWraper", e);
      }
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      Class<String> class0 = String.class;
      LinkedList<String> linkedList0 = new LinkedList<String>();
      ResourceWraper resourceWraper0 = new ResourceWraper((IResource) null, class0, linkedList0);
      // Undeclared exception!
      try { 
        resourceWraper0.getDtoIterable((EntityManager) null, (-1853), (-1853), linkedList0);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Index: -1853, Size: 0
         //
         verifyException("java.util.LinkedList", e);
      }
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      Class<String> class0 = String.class;
      LinkedList<String> linkedList0 = new LinkedList<String>();
      ResourceWraper resourceWraper0 = new ResourceWraper((IResource) null, class0, linkedList0);
      linkedList0.add("");
      linkedList0.add("");
      linkedList0.add("");
      List<IDto> list0 = resourceWraper0.getDtoIterable((EntityManager) null, 2, (-1), (List<String>) null);
      assertTrue(list0.isEmpty());
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      Class<String> class0 = String.class;
      LinkedList<String> linkedList0 = new LinkedList<String>();
      ResourceWraper resourceWraper0 = new ResourceWraper((IResource) null, class0, linkedList0);
      List<IDto> list0 = resourceWraper0.getDtoIterable((EntityManager) null, 2, (-1853), linkedList0);
      assertEquals(0, list0.size());
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      IResource iResource0 = mock(IResource.class, new ViolatedAssumptionAnswer());
      doReturn((String) null).when(iResource0).toString();
      Class<String> class0 = String.class;
      ResourceWraper resourceWraper0 = new ResourceWraper(iResource0, class0, (List<String>) null);
      IResource iResource1 = resourceWraper0.getResource();
      assertSame(iResource1, iResource0);
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      Class<String> class0 = String.class;
      LinkedList<String> linkedList0 = new LinkedList<String>();
      ResourceWraper resourceWraper0 = new ResourceWraper((IResource) null, class0, linkedList0);
      resourceWraper0.setOffset(2);
      assertFalse(resourceWraper0.isFinished());
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      IResource iResource0 = mock(IResource.class, new ViolatedAssumptionAnswer());
      Class<Object> class0 = Object.class;
      LinkedList<String> linkedList0 = new LinkedList<String>();
      ResourceWraper resourceWraper0 = new ResourceWraper(iResource0, class0, linkedList0);
      boolean boolean0 = resourceWraper0.isFinished();
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      Class<String> class0 = String.class;
      LinkedList<String> linkedList0 = new LinkedList<String>();
      ResourceWraper resourceWraper0 = new ResourceWraper((IResource) null, class0, linkedList0);
      Class class1 = resourceWraper0.getDto();
      assertEquals("class java.lang.String", class1.toString());
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      Class<String> class0 = String.class;
      LinkedList<String> linkedList0 = new LinkedList<String>();
      ResourceWraper resourceWraper0 = new ResourceWraper((IResource) null, class0, linkedList0);
      resourceWraper0.initResource((-1));
      assertFalse(resourceWraper0.isFinished());
  }
}
