import java.io.FileOutputStream;
import java.io.IOException;

import org.objectweb.asm.*;
import org.objectweb.asm.Opcodes;
import static org.objectweb.asm.Opcodes.*;

public class IfElse{

    //Question 8
    //Implement If. . . Then . . . Else

	public static void main(String[] args){

		ClassWriter cw=new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		cw.visit(V11, ACC_PUBLIC+ACC_SUPER, "ClassIfelse", null, "java/lang/Object", null);
	
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

            MethodVisitor mv=cw.visitMethod(ACC_PUBLIC+Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);

            //Initializes the labels
            Label skip = new Label();
            Label end = new Label();

            mv.visitCode();

            //I decided to implement user input for this If Else statement, it will check whether the input equals 0 or not.
            
            //Creates and stores a scanner object
            mv.visitTypeInsn(NEW, "java/util/Scanner");
            mv.visitInsn(DUP);
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "in", "Ljava/io/InputStream;");
            mv.visitMethodInsn(INVOKESPECIAL, "java/util/Scanner", "<init>", "(Ljava/io/InputStream;)V" , false);
            mv.visitVarInsn(ASTORE,1);

            //Prompts user to type an integer, scans it and then stores it
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");    
            mv.visitLdcInsn("Please type an integer: ");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            mv.visitVarInsn(ALOAD,1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/Scanner", "nextInt", "()I", false);
            mv.visitVarInsn(ISTORE,2);

            //Loads the input and checks whether it equals 0 or not
            mv.visitVarInsn(ILOAD, 2);
            mv.visitJumpInsn(IFNE, skip);  
            //The input was equal to 0 so it didnt skip          
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Input equals 0");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            //Skips to the end
            mv.visitJumpInsn(GOTO, end);             

            //The input was not 0 so it jumped to here
            mv.visitLabel(skip);
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Input does NOT equal 0");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

            //End of main method
            mv.visitLabel(end);
            mv.visitInsn(RETURN); 
            mv.visitMaxs(4, 7);
            mv.visitEnd();
            
        }
		
        //Create the bytearray and writing the class file

        byte[] b = cw.toByteArray();

        try{
            FileOutputStream out = new FileOutputStream("ClassIfelse.class");
            out.write(b);
            out.close();
        } 
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        
        System.out.println("Done! File was succesfully created.");

	}//end main

}//end class