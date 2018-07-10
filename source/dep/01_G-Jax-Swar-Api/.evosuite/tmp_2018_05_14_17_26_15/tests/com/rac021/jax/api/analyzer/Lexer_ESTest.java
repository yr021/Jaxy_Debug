/*
 * This file was automatically generated by EvoSuite
 * Mon May 14 15:30:45 GMT 2018
 */

package com.rac021.jax.api.analyzer;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.rac021.jax.api.analyzer.Lexer;
import com.rac021.jax.api.pojos.Query;
import java.util.List;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class Lexer_ESTest extends Lexer_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      String string0 = Lexer.decodeExpression("FULL", "TYPE");
      assertEquals("TYPE", string0);
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      Lexer[] lexerArray0 = Lexer.values();
      assertEquals(11, lexerArray0.length);
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      Lexer lexer0 = Lexer.valueOf("and");
      assertEquals(Lexer.and, lexer0);
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      String string0 = Lexer.decodeExpression("", "");
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      // Undeclared exception!
      try { 
        Lexer.decodeExpression("6.K2NV? @w=3)", (String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      Query query0 = new Query();
      MultivaluedHashMap<String, String> multivaluedHashMap0 = new MultivaluedHashMap<String, String>();
      multivaluedHashMap0.put("O> v]j", (List<String>) null);
      // Undeclared exception!
      try { 
        Lexer.decodeExpression(query0, (MultivaluedMap<String, String>) multivaluedHashMap0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.rac021.jax.api.analyzer.Lexer", e);
      }
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      Query query0 = new Query();
      MultivaluedHashMap<String, String> multivaluedHashMap0 = new MultivaluedHashMap<String, String>();
      query0.register("FULL", "TYPE", "TYPE");
      multivaluedHashMap0.addFirst("FULL", ">=2");
      List<String> list0 = Lexer.decodeExpression(query0, (MultivaluedMap<String, String>) multivaluedHashMap0);
      assertFalse(list0.contains("TYPE"));
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      Query query0 = new Query();
      MultivaluedHashMap<String, String> multivaluedHashMap0 = new MultivaluedHashMap<String, String>();
      query0.register("FULL", "A", "A");
      multivaluedHashMap0.addFirst("FULL", "_OR");
      List<String> list0 = Lexer.decodeExpression(query0, (MultivaluedMap<String, String>) multivaluedHashMap0);
      assertEquals(1, list0.size());
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      Query query0 = new Query();
      MultivaluedHashMap<String, String> multivaluedHashMap0 = new MultivaluedHashMap<String, String>();
      query0.register("FULL", "TYPE", "TYPE");
      multivaluedHashMap0.addFirst("FULL", "or");
      List<String> list0 = Lexer.decodeExpression(query0, (MultivaluedMap<String, String>) multivaluedHashMap0);
      assertFalse(list0.contains("or"));
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      Query query0 = new Query();
      MultivaluedHashMap<String, String> multivaluedHashMap0 = new MultivaluedHashMap<String, String>();
      query0.register("FULL", "FULL", "FULL");
      multivaluedHashMap0.addFirst("FULL", "_<");
      List<String> list0 = Lexer.decodeExpression(query0, (MultivaluedMap<String, String>) multivaluedHashMap0);
      assertFalse(list0.contains("FULL"));
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      Query query0 = new Query();
      MultivaluedHashMap<String, String> multivaluedHashMap0 = new MultivaluedHashMap<String, String>();
      query0.register("FULL", "FULL", "FULL");
      multivaluedHashMap0.putSingle("FULL", "_not_");
      List<String> list0 = Lexer.decodeExpression(query0, (MultivaluedMap<String, String>) multivaluedHashMap0);
      assertFalse(list0.contains("FULL"));
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      Query query0 = new Query();
      MultivaluedHashMap<String, String> multivaluedHashMap0 = new MultivaluedHashMap<String, String>();
      query0.register("FULL", "FULL", "FULL");
      multivaluedHashMap0.putSingle("FULL", "_NOT_");
      List<String> list0 = Lexer.decodeExpression(query0, (MultivaluedMap<String, String>) multivaluedHashMap0);
      assertFalse(list0.isEmpty());
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      Query query0 = new Query();
      MultivaluedHashMap<String, String> multivaluedHashMap0 = new MultivaluedHashMap<String, String>();
      query0.register("FULL", "FULL", "FULL");
      multivaluedHashMap0.addFirst("FULL", "<]Uep(^");
      List<String> list0 = Lexer.decodeExpression(query0, (MultivaluedMap<String, String>) multivaluedHashMap0);
      assertFalse(list0.contains("FULL"));
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      Query query0 = new Query();
      MultivaluedHashMap<String, String> multivaluedHashMap0 = new MultivaluedHashMap<String, String>();
      query0.register("FULL", "TYPE", "TYPE");
      multivaluedHashMap0.addFirst("FULL", "_>_<");
      List<String> list0 = Lexer.decodeExpression(query0, (MultivaluedMap<String, String>) multivaluedHashMap0);
      assertFalse(list0.isEmpty());
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      Query query0 = new Query();
      MultivaluedHashMap<String, String> multivaluedHashMap0 = new MultivaluedHashMap<String, String>();
      query0.register("FULL", "FULL", "FULL");
      multivaluedHashMap0.putSingle("FULL", "= > >= < != ");
      List<String> list0 = Lexer.decodeExpression(query0, (MultivaluedMap<String, String>) multivaluedHashMap0);
      assertFalse(list0.isEmpty());
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      Query query0 = new Query();
      MultivaluedHashMap<String, String> multivaluedHashMap0 = new MultivaluedHashMap<String, String>();
      query0.register("FULL", "TYPE", "TYPE");
      multivaluedHashMap0.addFirst("FULL", "");
      List<String> list0 = Lexer.decodeExpression(query0, (MultivaluedMap<String, String>) multivaluedHashMap0);
      assertEquals(1, list0.size());
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      Query query0 = new Query();
      MultivaluedHashMap<String, String> multivaluedHashMap0 = new MultivaluedHashMap<String, String>();
      query0.register("FULL", "FULL", "FULL");
      multivaluedHashMap0.putSingle("FULL", "> >= < = ");
      List<String> list0 = Lexer.decodeExpression(query0, (MultivaluedMap<String, String>) multivaluedHashMap0);
      assertFalse(list0.contains("> >= < = "));
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      Lexer lexer0 = Lexer.NOT;
      String string0 = lexer0.toString();
      assertEquals("_NOT_", string0);
  }

  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      Query query0 = new Query();
      MultivaluedHashMap<String, String> multivaluedHashMap0 = new MultivaluedHashMap<String, String>();
      List<String> list0 = Lexer.decodeExpression(query0, (MultivaluedMap<String, String>) multivaluedHashMap0);
      assertEquals(0, list0.size());
  }
}
