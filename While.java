import java.io.FileOutputStream;
import java.io.IOException;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import org.objectweb.asm.Label;


import static org.objectweb.asm.Opcodes.*;

public class While{

    //Question 7
    //Implement a While Loop

	public static void main(String[] args){
		
		ClassWriter cw=new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		cw.visit(V11, ACC_PUBLIC+ACC_SUPER, "ClassWhile", null, "java/lang/Object", null);
	
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

            //Initializing the labels
            Label start = new Label();
            Label end = new Label();

            mv.visitCode();

            //Storing an integer i = 0
            mv.visitInsn(ICONST_0);
            mv.visitVarInsn(ISTORE, 1);

            //This is the loop, if i is greater than or eqal to 5, then it jumps to the end, if not it goes through the loop
            mv.visitLabel(start);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitInsn(ICONST_5);
            mv.visitJumpInsn(IF_ICMPGE, end); 
            //Prints the current value of i
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(ILOAD,1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            //Increments i by 1 and jumps back to the start of the loop
            mv.visitIincInsn(1,1);
            mv.visitJumpInsn(GOTO, start);    
            
            //End of the program
            mv.visitLabel(end);
            mv.visitInsn(RETURN); 
            mv.visitMaxs(4, 7);
            mv.visitEnd();

		}
		
        //Create the bytearray and writing the class file

        byte[] b = cw.toByteArray();

        try{
            FileOutputStream out = new FileOutputStream("ClassWhile.class");
            out.write(b);
            out.close();
        } 
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        
        System.out.println("Done! File was succesfully created.");

	}//end main

}//end class