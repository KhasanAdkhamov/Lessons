package lessonTest.samples.user;

import base.TestCounter;
import base.specs.AbstractClassTester;
import base.specs.BusinessMethodTest;
import base.specs.ComputedPropertyTest;
import base.specs.ConstructorSpec;
import base.specs.FieldSpec;
import base.specs.MethodSpec;
import base.specs.TestData;
import base.specs.ValidationRule;

import java.util.List;
import java.util.Map;

public class UserTester extends AbstractClassTester {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java -ea UserTester <ClassName>");
            System.out.println("Available classes: User");
            return;
        }

        String className = args[0];
        Class<? extends AbstractClassTester> testerClass = UserTester.class;

        if (testerClass == null) {
            System.out.println("Unknown class: " + className);
            System.out.println("Available classes: User");
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
    
    public UserTester(TestCounter counter) {
        super(counter);
    }
    
    @Override
    protected String getTargetClassName() {
        return "User";
    }
    
    @Override
    protected List<FieldSpec> getFieldSpecs() {
        return List.of(
            new FieldSpec("username", String.class),
            new FieldSpec("password", String.class),
            new FieldSpec("isActive", boolean.class)
        );
    }
    
    @Override
    protected List<MethodSpec> getGetterSpecs() {
        return List.of(
            MethodSpec.getter("getUsername", String.class),
            MethodSpec.getter("isActive", boolean.class)
        );
    }
    
    @Override
    protected List<MethodSpec> getSetterSpecs() {
        return List.of(
            MethodSpec.setter("setPassword", String.class),
            MethodSpec.setter("setActive", boolean.class)
        );
    }
    
    @Override
    protected List<MethodSpec> getForbiddenMethods() {
        return List.of(
            MethodSpec.getter("getPassword", String.class)
        );
    }
    
    @Override
    protected List<ConstructorSpec> getConstructorSpecs() {
        return List.of(
            ConstructorSpec.of(String.class, String.class)
        );
    }
    
    @Override
    protected List<ValidationRule> getValidationRules() {
        return List.of(
            ValidationRule.of(
                "password", "setPassword", null,
                List.of("password123", "123456", "abcdef"),
                List.of("12345", "abc", ""),
                "Пароль минимум 6 символов"
            )
        );
    }
    
    @Override
    protected List<TestData> getTestData() {
        return List.of(
            new TestData(
                new Object[]{"user", "password123"},
                Map.of("getUsername", "user", "isActive", false)
            )
        );
    }
    
    @Override
    protected List<ComputedPropertyTest> getComputedPropertyTests() {
        return List.of();
    }
    
    @Override
    protected List<BusinessMethodTest> getBusinessMethodTests() {
        return List.of(
            // checkPassword() с правильным паролем
            BusinessMethodTest.returningMethod(
                new Object[]{"user", "password123"},
                "checkPassword",
                new Object[]{"password123"},
                true
            ),
            // checkPassword() с неправильным паролем
            BusinessMethodTest.returningMethod(
                new Object[]{"user", "password123"},
                "checkPassword",
                new Object[]{"wrong"},
                false
            )
        );
    }
}
