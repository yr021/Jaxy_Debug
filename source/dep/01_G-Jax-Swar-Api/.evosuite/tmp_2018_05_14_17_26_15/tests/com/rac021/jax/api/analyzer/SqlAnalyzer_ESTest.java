/*
 * This file was automatically generated by EvoSuite
 * Mon May 14 15:29:11 GMT 2018
 */

package com.rac021.jax.api.analyzer;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.rac021.jax.api.analyzer.SqlAnalyzer;
import com.rac021.jax.api.pojos.Query;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class SqlAnalyzer_ESTest extends SqlAnalyzer_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      Query query0 = SqlAnalyzer.buildQueryObject((Connection) null, "unknown join type for orace found (type=");
      assertNull(query0);
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      boolean boolean0 = SqlAnalyzer.containsAggregationFuntion("39fr9dLFd3S2;");
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      LinkedList<Query> linkedList0 = new LinkedList<Query>();
      Query query0 = new Query();
      linkedList0.add(query0);
      LinkedList<String> linkedList1 = new LinkedList<String>();
      MultivaluedHashMap<String, String> multivaluedHashMap0 = new MultivaluedHashMap<String, String>();
      multivaluedHashMap0.put("FULL", (List<String>) linkedList1);
      List<String> list0 = SqlAnalyzer.buildFilters(linkedList0, multivaluedHashMap0);
      assertNull(list0);
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      // Undeclared exception!
      try { 
        SqlAnalyzer.getSqlParamsWithTypes((Connection) null, "H");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.rac021.jax.api.analyzer.SqlAnalyzer", e);
      }
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      // Undeclared exception!
      try { 
        SqlAnalyzer.containsAggregationFuntion("`r>'m?XFK5]nw8+");
        fail("Expecting exception: Error");
      
      } catch(Error e) {
         //
         // Lexical error at line 1, column 16.  Encountered: <EOF> after : \"`r>\\'m?XFK5]nw8+\"
         //
         verifyException("net.sf.jsqlparser.parser.CCJSqlParserTokenManager", e);
      }
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      // Undeclared exception!
      try { 
        SqlAnalyzer.containsAggregationFuntion((String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("java.io.StringReader", e);
      }
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      // Undeclared exception!
      try { 
        SqlAnalyzer.buildQueryObject((Connection) null, "#");
        fail("Expecting exception: Error");
      
      } catch(Error e) {
         //
         // Lexical error at line 1, column 2.  Encountered: <EOF> after : \"\"
         //
         verifyException("net.sf.jsqlparser.parser.CCJSqlParserTokenManager", e);
      }
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      // Undeclared exception!
      try { 
        SqlAnalyzer.buildQueryObject((Connection) null, (String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.rac021.jax.api.analyzer.SqlAnalyzer", e);
      }
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      LinkedList<Query> linkedList0 = new LinkedList<Query>();
      // Undeclared exception!
      try { 
        SqlAnalyzer.buildFilters(linkedList0, (MultivaluedMap<String, String>) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.rac021.jax.api.analyzer.SqlAnalyzer", e);
      }
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      LinkedList<Query> linkedList0 = new LinkedList<Query>();
      MultivaluedHashMap<String, String> multivaluedHashMap0 = new MultivaluedHashMap<String, String>();
      multivaluedHashMap0.addFirst("BW$", (String) null);
      // Undeclared exception!
      try { 
        SqlAnalyzer.buildFilters(linkedList0, multivaluedHashMap0);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Index: 0, Size: 0
         //
         verifyException("java.util.LinkedList", e);
      }
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      LinkedList<Query> linkedList0 = new LinkedList<Query>();
      Query query0 = new Query();
      linkedList0.add(query0);
      LinkedList<String> linkedList1 = new LinkedList<String>();
      List<String> list0 = SqlAnalyzer.applyParams(linkedList0, linkedList1);
      assertNull(list0);
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      Query query0 = new Query(") AND ( ");
      LinkedList<Query> linkedList0 = new LinkedList<Query>();
      MultivaluedHashMap<String, String> multivaluedHashMap0 = new MultivaluedHashMap<String, String>();
      linkedList0.add(query0);
      List<String> list0 = SqlAnalyzer.buildFilters(linkedList0, multivaluedHashMap0);
      assertFalse(list0.isEmpty());
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      LinkedList<Query> linkedList0 = new LinkedList<Query>();
      MultivaluedHashMap<String, String> multivaluedHashMap0 = new MultivaluedHashMap<String, String>();
      Query query0 = new Query("()");
      linkedList0.add(query0);
      List<String> list0 = SqlAnalyzer.applyParams(linkedList0, (List<String>) null);
      assertNotNull(list0);
      
      multivaluedHashMap0.put("()", list0);
      List<String> list1 = SqlAnalyzer.buildFilters(linkedList0, multivaluedHashMap0);
      assertNotNull(list1);
      assertTrue(list1.equals((Object)list0));
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      LinkedList<Query> linkedList0 = new LinkedList<Query>();
      linkedList0.add((Query) null);
      List<String> list0 = SqlAnalyzer.applyParams(linkedList0, (List<String>) null);
      assertEquals(1, list0.size());
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      Query query0 = SqlAnalyzer.buildQueryObject((Connection) null, "unknown join type for oracle found (type=");
      assertNull(query0);
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      LinkedList<Query> linkedList0 = new LinkedList<Query>();
      MultivaluedHashMap<String, String> multivaluedHashMap0 = new MultivaluedHashMap<String, String>();
      List<String> list0 = SqlAnalyzer.buildFilters(linkedList0, multivaluedHashMap0);
      List<String> list1 = SqlAnalyzer.applyParams(linkedList0, list0);
      assertNotSame(list1, list0);
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      SqlAnalyzer sqlAnalyzer0 = new SqlAnalyzer();
  }
}
