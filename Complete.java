import java.io.FileOutputStream;
import java.io.IOException;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import org.objectweb.asm.Label;

import static org.objectweb.asm.Opcodes.*;

public class Complete{

    //Question 9
    //Get input (I or D), from the user, run a loop that adds that number to an accumulator and then prints the result.

	public static void main(String[] args){

		ClassWriter cw=new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		cw.visit(V11, ACC_PUBLIC+ACC_SUPER, "ClassComplete", null, "java/lang/Object", null);
	
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

			//Intializes the labels    
            Label start = new Label();
            Label end = new Label();
            
            //Creates the scanner and stores it
            mv.visitTypeInsn(NEW, "java/util/Scanner");
            mv.visitInsn(DUP);
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "in", "Ljava/io/InputStream;");
            mv.visitMethodInsn(INVOKESPECIAL, "java/util/Scanner", "<init>", "(Ljava/io/InputStream;)V" , false);
            mv.visitVarInsn(ASTORE,1);

            //Prompts the user to type an input(double)
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");    
            mv.visitLdcInsn("Please type a double: ");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            //Loads the scanner and stores the double
            mv.visitVarInsn(ALOAD,1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/Scanner", "nextDouble", "()D", false);
            mv.visitVarInsn(DSTORE,2);

            //Stores an int i = 0 
            mv.visitInsn(ICONST_0);
            mv.visitVarInsn(ISTORE, 4);

            //Runs the loop, loads i and checks if its less than 5 or not
            mv.visitLabel(start);
            mv.visitVarInsn(ILOAD, 4);
            mv.visitInsn(ICONST_5);
            mv.visitJumpInsn(IF_ICMPGE, end);  
            //Doubles the stored double, then stores it      
            mv.visitVarInsn(DLOAD,2);
            mv.visitVarInsn(DLOAD,2);
            mv.visitInsn(DADD);
            mv.visitVarInsn(DSTORE,2);
            //Prints the current state of the double
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");    
            mv.visitLdcInsn("Current state of input after being doubled: ");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false);
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(DLOAD,2);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);
            //Increments i by one
            mv.visitIincInsn(4,1);
            //Jumps back to the beginning of the loop
            mv.visitJumpInsn(GOTO, start);   

            //After the loop, prints the double
            mv.visitLabel(end);
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");    
            mv.visitLdcInsn("Input after loop: ");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false);
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(DLOAD,2);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);
        
			mv.visitInsn(RETURN); 
			mv.visitMaxs(4, 7);
			mv.visitEnd();
		}
		

        //Create the bytearray and writing the class file

        byte[] b = cw.toByteArray();

        try{
            FileOutputStream out = new FileOutputStream("ClassComplete.class");
            out.write(b);
            out.close();
        } 
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        
        System.out.println("Done! File was succesfully created.");

	}//end main

}//end class