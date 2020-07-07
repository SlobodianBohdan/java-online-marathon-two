// Create single-value annotation TestSuite with default element that returns array of strings.
//  Create class TestSuitHandler with static method run(Class<?> clazz).
// This method invokes all public non-static zero args methods.
// This method print to console information about all executed methods in form:
//    -- Method <class>.<methodname> started --
// <result of  methodname invocation>
//   -- Method <class><methodname> finished --
// (before -- should printed tab character)
// If clazz doesn't contain the <methodName> defined in annotation information Method with name <methodName> doesn't exist or is not public in class clazz should be displayed.
// If clazz is not marked with TestSuite annotation message Class clazz isn't annotated  should be displayed.
// For example
// We have
// @TestSuite({"m1", "m2"})
// class Class1{
//   public void m2(){
//     System.out.println("Hello");
//   }
// }
// We run 
// TestSuiteHandler.run(Class1.class);

// As a result we have
// Method with name m1 doesn't exists or not public in class Class1
// 	-- Method Class1.method2 started --
// Hello
// 	-- Method Class1.method2 finished --


