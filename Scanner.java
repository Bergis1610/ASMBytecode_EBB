import java.io.FileOutputStream;
import java.io.IOException;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static org.objectweb.asm.Opcodes.*;

public class Scanner{

    //Question 6
    //Get input from user using the Scanner class (I, L, and D)

	public static void main(String[] args){
	
		ClassWriter cw=new ClassWriter(0);
		cw.visit(V11, ACC_PUBLIC+ACC_SUPER, "ClassScan", null, "java/lang/Object", null);
	
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
            
            //Creates and stores the scanner method
            mv.visitTypeInsn(NEW, "java/util/Scanner");
            mv.visitInsn(DUP);
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "in", "Ljava/io/InputStream;");
            mv.visitMethodInsn(INVOKESPECIAL, "java/util/Scanner", "<init>", "(Ljava/io/InputStream;)V" , false);
            mv.visitVarInsn(ASTORE,1);

            //Prompts the user to type an integer, loads the scanner and stores the input
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");    
            mv.visitLdcInsn("Please type an integer: ");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            mv.visitVarInsn(ALOAD,1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/Scanner", "nextInt", "()I", false);
            mv.visitVarInsn(ISTORE,2);

            //Prompts the user to type a long, loads the scanner and stores the input
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");    
            mv.visitLdcInsn("Please type a long: ");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            mv.visitVarInsn(ALOAD,1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/Scanner", "nextLong", "()J", false);
            mv.visitVarInsn(LSTORE,3);

            //Prompts the user to type a double, loads the scanner and stores the input
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");    
            mv.visitLdcInsn("Please type a double: ");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            mv.visitVarInsn(ALOAD,1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/Scanner", "nextDouble", "()D", false);
            mv.visitVarInsn(DSTORE,5);


            //Prints the inputs
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Input: ");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

            //Prints the stored integer input
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Integer: ");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false);
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(ILOAD,2);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);

            //Prints the stored long input
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Long: ");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false);
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(LLOAD,3);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);
            
            //Prints the stored double input
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Double: ");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false);
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(DLOAD,5);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);
        
			mv.visitInsn(RETURN); 
			mv.visitMaxs(4, 7);
			mv.visitEnd();
		}
		
        //Create the bytearray and writing the class file

        byte[] b = cw.toByteArray();

        try{
            FileOutputStream out = new FileOutputStream("ClassScan.class");
            out.write(b);
            out.close();
        } 
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        
        System.out.println("Done! File was succesfully created.");


	}//end main

}//end class