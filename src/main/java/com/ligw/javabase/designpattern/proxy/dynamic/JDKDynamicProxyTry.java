package com.ligw.javabase.designpattern.proxy.dynamic;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

public class JDKDynamicProxyTry {

    /**
     * 实现动态的聚合方式的代理
     * 1.在本地磁盘创建代理类$ImplProxy.java
     * 2.编译生成字节码文件$ImplProxy.class
     * 3.利用classload讲字节码文件加载到jvm内存
     * 4.通过反射得到对象
     * 5.返回代理对象
     *
     * // TODO classload
     *
     * @param target 目标对象
     * @return
     */
    public static Object newImplProxyInstance(Object target) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, ClassNotFoundException, MalformedURLException {
        String packgeFileDirPath = "F:\\MyCode\\rainsunset\\java_base\\src\\main\\java\\";
        String proxyName = "$ImplProxy";
        String fieldName = "target";
        // 获取目标对象锁继承的接口，继承接口可继承多个，这里取第一个
        Class targetInfo = target.getClass().getInterfaces()[0];
        String targetInfoName = targetInfo.getSimpleName();

        String packgePath = "com.ligw.javabase.designpattern.proxy.dynamic";
        String packgeContent = "package " + packgePath + ";";
        String importContent = "import " + targetInfo.getName() + ";";
        String firstContent = "public class " + proxyName + " implements " + targetInfoName + "{";
        String fieldContent = "private " + targetInfoName + " " + fieldName + ";";
        String constructorContent = "public " + proxyName + "(" + targetInfoName + " " + fieldName + ") {this." + fieldName + " = " + fieldName + ";}";

        // 要实现所有的继承接口的方法
        // 获取所有接口-包括私有方法
        Method[] declaredMethods = targetInfo.getDeclaredMethods();
        StringBuilder overriceMethordsContentSB = new StringBuilder();
        for (Method declaredMethod : declaredMethods) {
            String overrideStatement = "@Override\n";

            String methodName = declaredMethod.getName();
            Class methodReturnType = declaredMethod.getReturnType();
            String methodReturnTypeSimpleName = methodReturnType.getSimpleName();
            String methodReturnTypeFullName = methodReturnType.getName();
            if (("void" != methodReturnTypeSimpleName) && (!methodReturnTypeFullName.startsWith("java."))) {
                importContent += ("import " + methodReturnTypeFullName + ";");
            }
            Class[] methodParameterTypes = declaredMethod.getParameterTypes();
            StringBuilder methodParamsWithTypeSB = new StringBuilder();
            StringBuilder methodParamsSB = new StringBuilder();
            final int[] i = {0};
            Arrays.stream(methodParameterTypes).forEach(methodParameterType -> {
                // 形参名称
                String formalParamName = "arg$" + i[0];
                methodParamsWithTypeSB.append(methodParameterType.getSimpleName()).append(" ").append(formalParamName).append(",");
                methodParamsSB.append(formalParamName).append(",");
                i[0]++;
            });
            String methodParamsWithType = (0 < methodParamsWithTypeSB.length()) ? methodParamsWithTypeSB.substring(0, methodParamsWithTypeSB.length() - 1) : "";
            String methodParams = (0 < methodParamsSB.length()) ? methodParamsSB.substring(0, methodParamsSB.length() - 1) : "";
            String methodFirstLine = "public " + methodReturnTypeSimpleName + " " + methodName + "(" + methodParamsWithType + ") {";
            String invoketargetMethos = fieldName + "." + methodName + "(" + methodParams + ");";
            String methodLastLine = "}\n";

            // 有意义的执行体
            // TODO 动态获取执行体
            String proxymethodBefore = "long current = System.currentTimeMillis();";
            String proxymethodAfter = "System.out.println(\"print cost time:\" + String.valueOf(System.currentTimeMillis() - current));";

            overriceMethordsContentSB.append(overrideStatement)
                    .append(methodFirstLine)
                    .append(proxymethodBefore).append(invoketargetMethos).append(proxymethodAfter)
                    .append(methodLastLine);
        }
        String overriceMethordsContent = overriceMethordsContentSB.toString();
        String lastContet = "}";

        // 最终得到的类内容
        String classContent = packgeContent + importContent + firstContent + fieldContent + constructorContent + overriceMethordsContent + lastContet;
        System.out.println(classContent);
        String javaFilePath = packgeFileDirPath + packgePath.replaceAll("\\.", "\\\\") + "\\" + proxyName + ".java";
        System.out.println(javaFilePath);
        File javaFile = new File(javaFilePath);
        if (!javaFile.exists()) {
            try {
                javaFile.createNewFile();
            } catch (IOException e) {
                System.out.println("文件生成失败：" + javaFilePath);
            }
        }
        try {
            FileWriter fileWriter = new FileWriter(javaFile);
            fileWriter.write(classContent);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("写文件失败");
        }

        // 编译
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        Iterable javaFileObjects = fileManager.getJavaFileObjects(javaFile);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, javaFileObjects);
        task.call();
        try {
            fileManager.close();
        } catch (IOException e) {
            System.out.println("fileManager关闭失败");
        }

        // classLoad url加载器
        URL[] urls = new URL[]{new URL("file:" + packgeFileDirPath)};
        URLClassLoader urlClassLoader = new URLClassLoader(urls);

        // 获取字节码文件对象
        Class clazz = urlClassLoader.loadClass(packgePath + "." + proxyName);
        // 通过反射获取对象实例  因为代理对象没有无参构造方法 只能通过先获取指定构造器再获取实例的方法获取
        Constructor constructor = clazz.getConstructor(targetInfo);
        Object res = constructor.newInstance(target);
        return res;
    }
}
