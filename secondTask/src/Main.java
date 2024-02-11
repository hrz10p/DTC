import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Map<String, Class<?>> typeMap = new HashMap<>();

    static {
        typeMap.put("int", int.class);
        typeMap.put("Integer", Integer.class);
        typeMap.put("double", double.class);
        typeMap.put("Double", Double.class);
        typeMap.put("boolean", boolean.class);
        typeMap.put("Boolean", Boolean.class);
        typeMap.put("String", String.class);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the path to the jar file:");
        String jarPath = scanner.nextLine();

        System.out.println("Enter the class name:");
        String className = scanner.nextLine();

        System.out.println("Enter the method name:");
        String methodName = scanner.nextLine();

        System.out.println("Enter the number of parameters:");
        int numParams = Integer.parseInt(scanner.nextLine());

        Class<?>[] paramTypes = new Class<?>[numParams];
        Object[] paramValues = new Object[numParams];

        for (int i = 0; i < numParams; i++) {
            System.out.println("Enter type of parameter " + (i + 1) + " (e.g., String, Integer):");
            String paramType = scanner.nextLine();
            Class<?> typeClass = typeMap.getOrDefault(paramType, null);

            if (typeClass == null) {
                System.out.println("Type " + paramType + " is not recognized. Please enter a valid type.");
                return;
            }

            paramTypes[i] = typeClass;

            System.out.println("Enter value of parameter " + (i + 1) + ":");
            String paramValue = scanner.nextLine();
            paramValues[i] = convertStringToType(paramValue, paramTypes[i]);
        }

        try {
            File file = new File(jarPath);
            URLClassLoader child = new URLClassLoader(new URL[]{file.toURI().toURL()}, Main.class.getClassLoader());
            Class<?> classToLoad = Class.forName(className, true, child);

            Method method = classToLoad.getMethod(methodName, paramTypes);
            Object instance = classToLoad.getDeclaredConstructor().newInstance();
            Object result = method.invoke(instance, paramValues);
            System.out.println("Result: " + result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static Object convertStringToType(String value, Class<?> type) {
        if (Integer.class == type || int.class == type) {
            return Integer.parseInt(value);
        } else if (Double.class == type || double.class == type) {
            return Double.parseDouble(value);
        } else if (Boolean.class == type || boolean.class == type) {
            return Boolean.parseBoolean(value);
        }
        return value;
    }
}