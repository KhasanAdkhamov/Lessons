package lessonTest.samples.person;

import base.TestCounter;
import base.specs.AbstractClassTester;
import base.specs.BusinessMethodTest;
import base.specs.ComputedPropertyTest;
import base.specs.ConstructorSpec;
import base.specs.FieldSpec;
import base.specs.MethodSpec;
import base.specs.TestData;
import base.specs.ValidationRule;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class PersonTester extends AbstractClassTester {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java -ea PersonTester <ClassName>");
            System.out.println("Available classes: Person");
            return;
        }

        String className = args[0];
        Class<? extends AbstractClassTester> testerClass = PersonTester.class;

        if (testerClass == null) {
            System.out.println("Unknown class: " + className);
            System.out.println("Available classes: Person");
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
    
    public PersonTester(TestCounter counter) {
        super(counter);
    }
    
    @Override
    protected String getTargetClassName() {
        return "Person";
    }
    
    @Override
    protected List<FieldSpec> getFieldSpecs() {
        return List.of(
            new FieldSpec("name", String.class),
            new FieldSpec("birthYear", int.class),
            new FieldSpec("birthMonth", int.class),
            new FieldSpec("birthDay", int.class)
        );
    }
    
    @Override
    protected List<MethodSpec> getGetterSpecs() {
        return List.of(
            MethodSpec.getter("getName", String.class),
            MethodSpec.getter("getBirthYear", int.class),
            MethodSpec.getter("getBirthMonth", int.class),
            MethodSpec.getter("getBirthDay", int.class),
            MethodSpec.getter("getAge", int.class),
            MethodSpec.getter("getBirthDate", String.class)
        );
    }
    
    @Override
    protected List<MethodSpec> getSetterSpecs() {
        return List.of(); // нет сеттеров
    }
    
    @Override
    protected List<MethodSpec> getForbiddenMethods() {
        return List.of();
    }
    
    @Override
    protected List<ConstructorSpec> getConstructorSpecs() {
        return List.of(
            ConstructorSpec.of(String.class, int.class, int.class, int.class)
        );
    }
    
    @Override
    protected List<ValidationRule> getValidationRules() {
        return List.of(); // валидация в конструкторе проверяется отдельно
    }
    
    @Override
    protected List<TestData> getTestData() {
        return List.of(
            new TestData(
                new Object[]{"Иван", 2000, 5, 15},
                Map.of("getName", "Иван", "getBirthYear", 2000, "getBirthMonth", 5, "getBirthDay", 15)
            )
        );
    }
    
    @Override
    protected List<ComputedPropertyTest> getComputedPropertyTests() {
        int currentYear = LocalDate.now().getYear();
        int expectedAge = currentYear - 2000;
        
        return List.of(
            // getAge() вычисляет возраст
            new ComputedPropertyTest(
                new Object[]{"Иван", 2000, 5, 15},
                "getAge",
                expectedAge,
                0
            ),
            // getBirthDate() формат ДД.ММ.ГГГГ
            new ComputedPropertyTest(
                new Object[]{"Иван", 1990, 1, 5},
                "getBirthDate",
                "05.01.1990",
                0
            )
        );
    }
    
    @Override
    protected List<BusinessMethodTest> getBusinessMethodTests() {
        return List.of(
            // isAdult() для взрослого (год рождения 2000, возраст > 18)
            BusinessMethodTest.returningMethod(
                new Object[]{"Иван", 2000, 5, 15},
                "isAdult",
                new Object[]{},
                true
            ),
            // isMinor() для взрослого
            BusinessMethodTest.returningMethod(
                new Object[]{"Иван", 2000, 5, 15},
                "isMinor",
                new Object[]{},
                false
            ),
            // isAdult() для несовершеннолетнего (год рождения 2010, возраст < 18)
            BusinessMethodTest.returningMethod(
                new Object[]{"Мария", 2010, 5, 15},
                "isAdult",
                new Object[]{},
                false
            ),
            // isMinor() для несовершеннолетнего
            BusinessMethodTest.returningMethod(
                new Object[]{"Мария", 2010, 5, 15},
                "isMinor",
                new Object[]{},
                true
            )
        );
    }
}
