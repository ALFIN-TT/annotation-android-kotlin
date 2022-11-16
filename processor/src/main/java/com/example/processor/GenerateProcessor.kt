package com.example.processor

import com.example.annotation.CheckCamelSource
import com.example.annotation.Encapsulate
import com.example.annotation.GenerateSource
import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.*
import java.io.File
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

//CASE 03 STEP 03
@AutoService(Processor::class)// The @AutoService is required to get the GenerateProcessor executed.
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedOptions(GenerateProcessor.KAPT_KOTLIN_GENERATED_OPTION_NAME)
class GenerateProcessor : AbstractProcessor() {

    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }


    /***
     * The getSupportedAnnotationType register the annotation that is will check out i.e. CheckCamelSource annotation.
     */
    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(
            GenerateSource::class.java.canonicalName,
            CheckCamelSource::class.java.canonicalName,
            Encapsulate::class.java.name
        )
    }

    private val generatedSourcesRoot by lazy { processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME].orEmpty() }


    /**
     * The process helps to get all the variable within the class annotation
     */
    override fun process(
        annotations: MutableSet<out TypeElement>,
        roundEnv: RoundEnvironment
    ): Boolean {

        // camel case check region start
        roundEnv.getElementsAnnotatedWith(CheckCamelSource::class.java).forEach { classElement ->
            if (classElement.kind != ElementKind.CLASS) {
                printError("Can only be applied to field, element: $classElement")
                return false
            } else {
                checkCamelVariable(classElement as TypeElement)
            }
        }
        // camel case check region end

        // kotlin poet way region start
        if (generatedSourcesRoot.isEmpty()) {
            printError("Can't find the target directory for generated Kotlin files.")
            return false
        }

        roundEnv.getElementsAnnotatedWith(GenerateSource::class.java).forEach { fieldElement ->
            if (fieldElement.kind != ElementKind.FIELD) {
                printError("Can only be applied to field, element: $fieldElement")
                return false
            } else {
                prepareFieldInitialization(fieldElement)
            }
        }
        // kotlin poet way region end


        //manage encapsulate annotation region start
        roundEnv.getElementsAnnotatedWith(Encapsulate::class.java).forEach {
            if (it.kind != ElementKind.CLASS) {
                processingEnv.messager.printMessage(
                    Diagnostic.Kind.ERROR,
                    "Only classes can be annotated"
                )
                return true
            }
            processEncapsulateAnnotation(it)
        }
        //manage encapsulate annotation region start

        return false
    }

    /**
     * withCheckCamelSource, to be checked if it is named in CamelCase.
     */
    private fun checkCamelVariable(classElement: TypeElement) {
        classElement.enclosedElements.filter {
            !it.simpleName.toString().isDefinedCamelCase()
        }.forEach {
            printWarning("Detected non-camelcase name: ${it.simpleName}.")
        }
    }

    private fun String.isDefinedCamelCase(): Boolean {
        val toCharArray = toCharArray()
        return toCharArray
            .mapIndexed { index, current -> current to toCharArray.getOrNull(index + 1) }
            .none { it.first.isUpperCase() && it.second?.isUpperCase() ?: false }
    }


    /***
     * Using the below is a snippet of to process the given variable that is annotated with GenerateSource, we could use it to write a function to a file using the below function.
     */
    private fun prepareFieldInitialization(fieldElement: Element) {
        val packageOfMethod = processingEnv.elementUtils.getPackageOf(fieldElement).toString()
        val annotatedValue = fieldElement.getAnnotation(GenerateSource::class.java).value
        val funcBuilder = FunSpec.builder("bindGenerationValue")
            .addModifiers(KModifier.PUBLIC)
            .addParameter("parent", fieldElement.enclosingElement.asType().asTypeName())
            .addStatement("parent.%L = %L", fieldElement.simpleName, annotatedValue)
        val file = File(generatedSourcesRoot)
        file.mkdir()
        FileSpec.builder(packageOfMethod, "GeneratedFunction").addFunction(funcBuilder.build())
            .build().writeTo(file)
    }


    private fun printError(message: String) {
        processingEnv.messager.printMessage(Diagnostic.Kind.ERROR, message)
    }

    private fun printWarning(message: String) {
        processingEnv.messager.printMessage(Diagnostic.Kind.WARNING, message)
    }


    /***
     * create class
     */
    private fun processEncapsulateAnnotation(element: Element) {

        val className = element.simpleName.toString()
        val packageName = processingEnv.elementUtils.getPackageOf(element).toString()

        val fileName = "Encapsulated$className"
        val fileBuilder = FileSpec.builder(packageName, fileName)
        val classBuilder = TypeSpec.classBuilder(fileName)

        for (enclosed in element.enclosedElements) {
            if (enclosed.kind == ElementKind.FIELD) {

                //create variable
                classBuilder.addProperty(
                    PropertySpec.varBuilder(
                        enclosed.simpleName.toString(),
                        enclosed.asType().asTypeName().asNullable(),
                        KModifier.PRIVATE
                    ).initializer("null")
                        .build()
                )

                //create get function
                classBuilder.addFunction(
                    FunSpec.builder("get${enclosed.simpleName}")
                        .returns(enclosed.asType().asTypeName().asNullable())
                        .addStatement("return ${enclosed.simpleName}")
                        .build()
                )

                //create set function
                classBuilder.addFunction(
                    FunSpec.builder("set${enclosed.simpleName}")
                        .addParameter(
                            ParameterSpec.builder(
                                "${enclosed.simpleName}",
                                enclosed.asType().asTypeName().asNullable()
                            ).build()
                        )
                        .addStatement("this.${enclosed.simpleName} = ${enclosed.simpleName}")
                        .build()
                )
            }
        }

        val file = fileBuilder.addType(classBuilder.build()).build()
        val kaptKotlinGeneratedDir = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME]
        file.writeTo(File(kaptKotlinGeneratedDir))
    }
}