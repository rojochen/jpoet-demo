 
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

public class TypeGen {

	public static void main(String[] args) throws IOException {
		//PrintStream stream = new PrintStream("YourTargetFile.java", "UTF-8");
		File sourcePath = new File("src/main/java");
		TypeSpec helloWorld = TypeSpec.enumBuilder("Color")
			    .addModifiers(Modifier.PUBLIC)
			    .addEnumConstant("RED")
			    .addEnumConstant("BLUE")
			    .addEnumConstant("BLACK")
			    .build();
		JavaFile javaFile = JavaFile.builder("my.codegen", helloWorld)
			    .build();
		javaFile.writeTo(sourcePath);
	}

}
