/*
 * This file was automatically generated by EvoSuite
 * Mon May 14 15:38:01 GMT 2018
 */

package com.rac021.jax.api.pojos;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.rac021.jax.api.pojos.Query;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class Query_ESTest extends Query_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      Query query0 = new Query((String) null);
      query0.register((String) null, (String) null, (String) null);
      int int0 = query0.size();
      assertEquals(1, int0);
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      Query query0 = new Query((String) null);
      assertFalse(query0.containsAggregationFunction);
      
      query0.setContainsAggregationFunction(true);
      boolean boolean0 = query0.isContainsAggregationFunction();
      assertTrue(query0.containsAggregationFunction);
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      Query query0 = new Query("N\"s");
      query0.register("", "N\"s", (String) null);
      query0.getType("");
      assertEquals(1, query0.size());
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      Query query0 = new Query("N\"s");
      query0.register("N\"s", "N\"s", "N\"s");
      query0.getType("N\"s");
      assertEquals(1, query0.size());
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      Query query0 = new Query();
      query0.register("", "", "");
      query0.getType("");
      assertEquals(1, query0.size());
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      Query query0 = new Query((String) null);
      String string0 = query0.getQuery();
      assertNull(string0);
      assertFalse(query0.containsAggregationFunction);
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      Query query0 = new Query("&R~MDRExz@%v}QuF6");
      String string0 = query0.getQuery();
      assertEquals("&R~MDRExz@%v}QuF6", string0);
      assertFalse(query0.containsAggregationFunction);
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      Query query0 = new Query("i_");
      query0.parameters = null;
      query0.getParameters();
      assertFalse(query0.containsAggregationFunction);
      assertEquals("i_", query0.getQuery());
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      Query query0 = new Query("N\"s");
      query0.register("", (String) null, "ll");
      query0.getParameters();
      assertEquals(1, query0.size());
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      Query query0 = new Query((String) null);
      query0.register((String) null, (String) null, (String) null);
      query0.getFullName((String) null);
      assertEquals(1, query0.size());
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      Query query0 = new Query();
      query0.register("1h$z(!t\"**1c", "1h$z(!t\"**1c", "s;S3/M:(");
      query0.getFullName("1h$z(!t\"**1c");
      assertEquals(1, query0.size());
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      Query query0 = new Query();
      query0.register("", "", "");
      query0.getFullName("");
      assertEquals(1, query0.size());
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      Query query0 = new Query("i_");
      query0.parameters = null;
      // Undeclared exception!
      try { 
        query0.size();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.rac021.jax.api.pojos.Query", e);
      }
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      Query query0 = new Query("com.rac021.jax.api.pojos.Query");
      query0.parameters = null;
      // Undeclared exception!
      try { 
        query0.register("XzE1B!", "", "");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.rac021.jax.api.pojos.Query", e);
      }
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      Query query0 = new Query("");
      String string0 = query0.getQuery();
      assertEquals("", string0);
      assertFalse(query0.containsAggregationFunction);
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      Query query0 = new Query("");
      query0.getParameters();
      assertEquals("", query0.getQuery());
      assertFalse(query0.containsAggregationFunction);
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      Query query0 = new Query("");
      boolean boolean0 = query0.isContainsAggregationFunction();
      assertEquals("", query0.getQuery());
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      Query query0 = new Query("");
      // Undeclared exception!
      try { 
        query0.getType("");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.rac021.jax.api.pojos.Query", e);
      }
  }

  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      Query query0 = new Query("");
      assertEquals("", query0.getQuery());
      
      query0.setQuery("TYPE");
      assertFalse(query0.isContainsAggregationFunction());
  }

  @Test(timeout = 4000)
  public void test19()  throws Throwable  {
      Query query0 = new Query();
      // Undeclared exception!
      try { 
        query0.getFullName("FULL");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.rac021.jax.api.pojos.Query", e);
      }
  }

  @Test(timeout = 4000)
  public void test20()  throws Throwable  {
      Query query0 = new Query("");
      query0.size();
      assertFalse(query0.containsAggregationFunction);
      assertEquals("", query0.getQuery());
  }
}
