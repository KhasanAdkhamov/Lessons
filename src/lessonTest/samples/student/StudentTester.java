package lessonTest.samples.student;

import base.TestCounter;
import base.specs.AbstractClassTester;
import base.specs.BusinessMethodTest;
import base.specs.ComputedPropertyTest;
import base.specs.ConstructorSpec;
import base.specs.FieldSpec;
import base.specs.MethodSpec;
import base.specs.TestData;
import base.specs.ValidationRule;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class StudentTester extends AbstractClassTester {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java -ea StudentTester <ClassName>");
            System.out.println("Available classes: Student");
            return;
        }

        String className = args[0];
        Class<? extends AbstractClassTester> testerClass = StudentTester.class;

        if (testerClass == null) {
            System.out.println("Unknown class: " + className);
            System.out.println("Available classes: Student");
            return;
        }

        try {
            TestCounter counter = new TestCounter(testerClass, 0, Map.of());
            AbstractClassTester tester = testerClass.getDeclaredConstructor(TestCounter.class).newInstance(counter);
            tester.test();
            counter.printStatus();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public StudentTester(TestCounter counter) {
        super(counter);
    }
    
    @Override
    protected String getTargetClassName() {
        return "Student";
    }
    
    @Override
    protected List<FieldSpec> getFieldSpecs() {
        // Временно заполнено для тестирования задачи 2.1
        return List.of(
            new FieldSpec("name", String.class),
            new FieldSpec("age", int.class),
            new FieldSpec("averageGrade", double.class)
        );
    }
    
    @Override
    protected List<MethodSpec> getGetterSpecs() {
        return List.of(
            MethodSpec.getter("getName", String.class),
            MethodSpec.getter("getAge", int.class),
            MethodSpec.getter("getAverageGrade", double.class)
        );
    }
    
    @Override
    protected List<MethodSpec> getSetterSpecs() {
        return List.of(
            MethodSpec.setter("setName", String.class),
            MethodSpec.setter("setAge", int.class),
            MethodSpec.setter("setAverageGrade", double.class)
        );
    }
    
    @Override
    protected List<MethodSpec> getForbiddenMethods() {
        return List.of();
    }
    
    @Override
    protected List<ConstructorSpec> getConstructorSpecs() {
        return List.of(
            ConstructorSpec.of(String.class, int.class, double.class)
        );
    }
    
    @Override
    protected List<ValidationRule> getValidationRules() {
        return List.of(
            ValidationRule.of(
                "name", "setName", "getName",
                List.of("Иван", "A", "Мария Петровна"),
                Arrays.asList("", null),
                "Имя не должно быть пустым"
            ),
            ValidationRule.of(
                "age", "setAge", "getAge",
                List.of(16, 50, 100),
                List.of(15, 101, 0, -1),
                "Возраст от 16 до 100"
            ),
            ValidationRule.of(
                "averageGrade", "setAverageGrade", "getAverageGrade",
                List.of(0.0, 2.5, 5.0),
                List.of(-0.1, 5.1, -1.0, 10.0),
                "Средний балл от 0.0 до 5.0"
            )
        );
    }
    
    @Override
    protected List<TestData> getTestData() {
        return List.of(
            new TestData(
                new Object[]{"Иван", 20, 4.5},
                Map.of("getName", "Иван", "getAge", 20, "getAverageGrade", 4.5)
            ),
            new TestData(
                new Object[]{"Мария", 16, 0.0},
                Map.of("getName", "Мария", "getAge", 16, "getAverageGrade", 0.0)
            ),
            new TestData(
                new Object[]{"Пётр", 100, 5.0},
                Map.of("getName", "Пётр", "getAge", 100, "getAverageGrade", 5.0)
            )
        );
    }
    
    @Override
    protected List<ComputedPropertyTest> getComputedPropertyTests() {
        return List.of(); // нет вычисляемых свойств у Student
    }
    
    @Override
    protected List<BusinessMethodTest> getBusinessMethodTests() {
        return List.of(
            // displayInfo() не должен бросать исключений
            BusinessMethodTest.noThrowMethod(
                new Object[]{"Иван", 20, 4.5},
                "displayInfo",
                new Object[]{}
            )
        );
    }
}
