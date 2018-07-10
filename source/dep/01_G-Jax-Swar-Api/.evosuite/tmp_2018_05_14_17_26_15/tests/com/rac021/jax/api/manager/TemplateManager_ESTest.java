/*
 * This file was automatically generated by EvoSuite
 * Mon May 14 15:34:50 GMT 2018
 */

package com.rac021.jax.api.manager;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.rac021.jax.api.manager.TemplateManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.regex.PatternSyntaxException;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class TemplateManager_ESTest extends TemplateManager_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      String string0 = TemplateManager.removeMultipleBlankSpaces("|");
      assertEquals("|", string0);
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      HashMap<String, String> hashMap0 = new HashMap<String, String>();
      String string0 = TemplateManager.applyValue(hashMap0, "");
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      // Undeclared exception!
      try { 
        TemplateManager.removeMultipleBlankSpaces((String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.rac021.jax.api.manager.TemplateManager", e);
      }
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      // Undeclared exception!
      try { 
        TemplateManager.readFile((String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      // Undeclared exception!
      try { 
        TemplateManager.extractArgs((String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      HashMap<String, String> hashMap0 = new HashMap<String, String>();
      hashMap0.put("{\"SVG_X@FHE|}MVOD", "{\"SVG_X@FHE|}MVOD");
      // Undeclared exception!
      try { 
        TemplateManager.applyValue(hashMap0, "");
        fail("Expecting exception: PatternSyntaxException");
      
      } catch(PatternSyntaxException e) {
         //
         // Illegal repetition near index 3
         // \\{\\{{\"SVG_X@FHE|}MVOD\\}\\}
         //    ^
         //
         verifyException("java.util.regex.Pattern", e);
      }
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      HashMap<String, String> hashMap0 = new HashMap<String, String>();
      hashMap0.put("|", "|");
      BiFunction<Object, String, String> biFunction0 = (BiFunction<Object, String, String>) mock(BiFunction.class, new ViolatedAssumptionAnswer());
      doReturn((Object) null).when(biFunction0).apply(any() , anyString());
      hashMap0.replaceAll(biFunction0);
      // Undeclared exception!
      try { 
        TemplateManager.applyValue(hashMap0, "}}");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("java.lang.AbstractStringBuilder", e);
      }
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      HashMap<String, String> hashMap0 = new HashMap<String, String>();
      hashMap0.put("|", "|");
      String string0 = TemplateManager.applyValue(hashMap0, "}}");
      assertEquals("|", string0);
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      HashMap<String, String> hashMap0 = new HashMap<String, String>();
      String string0 = TemplateManager.applyValue(hashMap0, (String) null);
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      String string0 = TemplateManager.applyValue((Map<String, String>) null, "{{((^\t| | \n))*");
      assertEquals("{{((^\t| | \n))*", string0);
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      List<String> list0 = TemplateManager.extractArgs("|");
      assertFalse(list0.contains("|"));
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      String string0 = TemplateManager.readFile("}}");
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      TemplateManager templateManager0 = new TemplateManager();
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      String string0 = TemplateManager.removeMultipleBlankSpaces("");
      assertEquals("", string0);
  }
}
