
//Import the necessary classes
import java.io.FileOutputStream;
import java.io.IOException;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static org.objectweb.asm.Opcodes.*;

public class Multiply{

	public static void main(String[] args){

		ClassWriter cw=new ClassWriter(0);
		cw.visit(V11, ACC_PUBLIC+ACC_SUPER, "ClassMult", null, "java/lang/Object", null);
	
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
            //Creates the methodvisitor
			MethodVisitor mv=cw.visitMethod(ACC_PUBLIC+ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
			mv.visitCode();

            /*
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
			mv.visitLdcInsn("3 * 9 = ");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false);
            */

            //Section 1, pushes two integers, stores them, multiplies them, stores the product and prints the equation
            mv.visitIntInsn(BIPUSH, 3);
            mv.visitVarInsn(ISTORE,1);
			mv.visitIntInsn(BIPUSH, 9);
			mv.visitVarInsn(ISTORE,2);
            mv.visitVarInsn(ILOAD,1);
            mv.visitVarInsn(ILOAD,2);
			mv.visitInsn(IMUL);
			mv.visitVarInsn(ISTORE,3);

            //This is the printing section, here I loaded the first factor and printed it, then printed a string, then the second factor then a string and then the product
            //Basically manually concatenating the final string to be printed. For the double and long however I will just print the factors as part of the string and then the product.
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
			mv.visitVarInsn(ILOAD,1);
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(I)V", false);
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
			mv.visitLdcInsn(" * ");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false);
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
			mv.visitVarInsn(ILOAD,2);
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(I)V", false);
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
			mv.visitLdcInsn(" = ");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false);
			mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
			mv.visitVarInsn(ILOAD,3);
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
		


            //Here two longs are generated and then stored, then loaded and multiplied and the product is stored 
			mv.visitLdcInsn(234l);
			mv.visitVarInsn(LSTORE,4);
            mv.visitLdcInsn(322l);
			mv.visitVarInsn(LSTORE,6);
			mv.visitVarInsn(LLOAD,4);
			mv.visitVarInsn(LLOAD,6);
			mv.visitInsn(LMUL);
			mv.visitVarInsn(LSTORE,8);


            //This is the printing section where a string with the two factors are printed as well as the product loaded from storage 8
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
			mv.visitLdcInsn("234 * 322 = ");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false);
			mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
			mv.visitVarInsn(LLOAD,8);
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);
			
            //Here two doubles are generated and then stored, then loaded and multiplied and the product is stored 
			mv.visitLdcInsn(76.22);
			mv.visitVarInsn(DSTORE,10);
            mv.visitLdcInsn(11.43);
			mv.visitVarInsn(DSTORE,12);
			mv.visitVarInsn(DLOAD,10);
			mv.visitVarInsn(DLOAD,12);
			mv.visitInsn(DMUL);
			mv.visitVarInsn(DSTORE,14);

            //This is the printing section where a string with the two factors are printed as well as the product loaded from storage 14
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
			mv.visitLdcInsn("76.22 * 11.43 = ");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false);
			mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
			mv.visitVarInsn(DLOAD,14);
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);
			
			mv.visitInsn(RETURN); 
			mv.visitMaxs(20, 20);
			mv.visitEnd();
		}
		
        byte[] b = cw.toByteArray();       //Creates a byte array to be written into the classfile

        //Tries to write the class file defined by the asm code and catches with an IOException
        try{
            FileOutputStream out = new FileOutputStream("ClassMult.class");
            out.write(b);
            out.close();
        } 
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        
        System.out.println("Done! File was succesfully created.");  //Note to self 

	}//end main

}//end class