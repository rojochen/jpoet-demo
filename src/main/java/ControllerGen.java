import java.io.File;
import java.io.IOException;

import javax.lang.model.element.Modifier;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

public class ControllerGen {
	public static void main(String[] args) throws IOException {
		// create mehtod
		File sourcePath = new File("src/main/java");
		
		//return type 
        ClassName person = ClassName.get("com.example.vo", "Person");
        // ArrayList類型
        ClassName httpEntity = ClassName.get("org.springframework.http", "HttpEntity");

        // 生成泛型類型，類型的名稱、參數的名稱
        TypeName httpEntityType = ParameterizedTypeName.get(httpEntity, person);
        
        //requestMapping 
        AnnotationSpec requestMapping = AnnotationSpec.builder(RequestMapping.class)
        		.addMember("value","$S","/departments")
        		.addMember("method", "$T.POST",RequestMethod.class)
        		.build();
        
        //controller annotaion
        AnnotationSpec controllerAnnotation = AnnotationSpec.builder(RestController.class).build();
        
		MethodSpec main = MethodSpec.methodBuilder("handel").addModifiers(Modifier.PUBLIC, Modifier.FINAL)
				.addAnnotation(requestMapping)
				.addCode("")
				.returns(httpEntityType).build();
		
		TypeSpec myContontroller = TypeSpec.classBuilder("MyController")
				.addAnnotation(controllerAnnotation)
				.addModifiers(Modifier.PUBLIC, Modifier.FINAL)
				.addMethod(main).build();

		JavaFile javaFile = JavaFile.builder("my.codegen", myContontroller).build();

		javaFile.writeTo(sourcePath);
	}

}
