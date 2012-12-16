
public class NullPointerExceptionSampleProgram
{
       private String field1 = null;
       private String field2 = null;   
      
       public String getField1()
       {
             return field1;
       }

       public void setField1(String field1)
       {
             this.field1 = field1;
       }

       public String getField2()
       {
             return field2;
            		 
       }

       public void setField2(String field2)
       {
             this.field2 = field2;
       }

       /**
        * @param args
        */
       public static void main(String[] args)
       {
            
             try {
                    // Create a fresh object instance
                    NullPointerExceptionSampleProgram objectInstance =
                                 new NullPointerExceptionSampleProgram();                   
                    // Initialize field1...
                    objectInstance.setField1("field1Value");
                   
                    // reset our object instance to null
                    //objectInstance = null;
                   
                    // Now initialize field2...BOOM! >> NullPointerException!
                    objectInstance.setField2("field1Value2");
            
             } catch (Throwable any) {
                    System.out.println("Java ERROR: "+any);
                    any.printStackTrace();
             }
            
       }

}