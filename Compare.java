import java.io.FileOutputStream;
import java.io.IOException;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static org.objectweb.asm.Opcodes.*;

public class Compare{

    //Question 4
    //Compare two numbers (I, L, and D) to determine which is bigger/smaller

	public static void main(String[] args){

        //String name = "TesterAdder";
		
		ClassWriter cw=new ClassWriter(0);
		cw.visit(V11, ACC_PUBLIC+ACC_SUPER, "ClassComp", null, "java/lang/Object", null);
	
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
            

            //Comparing two integers

            mv.visitIntInsn(BIPUSH, 10);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;" , false);
            mv.visitVarInsn(ASTORE,1);
            mv.visitIntInsn(BIPUSH, 15);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;" , false);
            mv.visitVarInsn(ASTORE,2);
            
            //Comparing the integers and then printing them
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
			mv.visitLdcInsn("10 compared to 15 = ");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false);
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(ALOAD,1);
            mv.visitVarInsn(ALOAD,2);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Integer", "compareTo", "(Ljava/lang/Integer;)I", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);


            //Comparing two longs

            mv.visitLdcInsn(1455l);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;" , false);
            mv.visitVarInsn(ASTORE,3);
            mv.visitLdcInsn(1442l);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;" , false);
            mv.visitVarInsn(ASTORE,4);

            //Comparing the longs and then printing them
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("1455 compared to 1442 = ");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false);
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(ALOAD,3);
            mv.visitVarInsn(ALOAD,4);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Long", "compareTo", "(Ljava/lang/Long;)I", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);


            //Comparing two doubles

            mv.visitLdcInsn(34.22334d);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;" , false);
            mv.visitVarInsn(ASTORE,5);
            mv.visitLdcInsn(34.22334d);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;" , false);
            mv.visitVarInsn(ASTORE,6);

            //Comparing the doubles and then printing them
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
			mv.visitLdcInsn("34.22334 compared to 34.22334 = ");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false);
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(ALOAD,5);
            mv.visitVarInsn(ALOAD,6);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Double", "compareTo", "(Ljava/lang/Double;)I", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);

			mv.visitInsn(RETURN); 
			mv.visitMaxs(10, 10);
			mv.visitEnd();
		}
		
        //Create the bytearray and writing the class file

        byte[] b = cw.toByteArray();

        try{
            FileOutputStream out = new FileOutputStream("ClassComp.class");
            out.write(b);
            out.close();
        } 
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        
        System.out.println("Done! File was succesfully created.");

	}//end main

}//end class