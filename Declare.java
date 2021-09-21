import java.io.FileOutputStream;
import java.io.IOException;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static org.objectweb.asm.Opcodes.*;

public class Declare{

    //Question 5
    //Declare String Variables
    
	public static void main(String[] args){

		ClassWriter cw=new ClassWriter(0);
		cw.visit(V11, ACC_PUBLIC+ACC_SUPER, "ClassDec", null, "java/lang/Object", null);
	
		//Create the class
		{
			MethodVisitor mv=cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			mv.visitVarInsn(ALOAD, 0); //load the first local variable: this
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
			mv.visitInsn(RETURN);
			mv.visitMaxs(1,1);
			mv.visitEnd();
		}
		
		//create the main method
		{
			MethodVisitor mv=cw.visitMethod(ACC_PUBLIC+ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
			mv.visitCode();
			
            //Declare and store the strings
			mv.visitLdcInsn("Hello World!");
			mv.visitVarInsn(ASTORE,1);
            mv.visitLdcInsn("I'm so glad this worked!");
			mv.visitVarInsn(ASTORE,2);

            //Printing the strings
			mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(ALOAD,1);
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
			mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(ALOAD,2);
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
			
			mv.visitInsn(RETURN); 
			mv.visitMaxs(4, 7);
			mv.visitEnd();
		}
		
        //Create the bytearray and writing the class file
        
        byte[] b = cw.toByteArray();

        try{
            FileOutputStream out = new FileOutputStream("ClassDec.class");
            out.write(b);
            out.close();
        } 
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        
        System.out.println("Done! File was succesfully created.");

     
	}//end main

}//end class