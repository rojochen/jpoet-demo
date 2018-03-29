import java.io.IOException;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

public class AnnotationGen {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		MethodSpec main = MethodSpec.methodBuilder("main").addAnnotation(Override.class)
			    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
			    .returns(void.class)
			    .addParameter(String[].class, "args")
			    .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
			    .addCode(""
			            + "int total = 0;\n"
			            + "for (int i = 0; i < 10; i++) {\n"
			            + "  total += i;\n"
			            + "}\n")
			    .build();

			TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
			    .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
			    .addMethod(main)
			    .build();

			JavaFile javaFile = JavaFile.builder("my.codegen", helloWorld)
			    .build();

			javaFile.writeTo(System.out);
	}

}
