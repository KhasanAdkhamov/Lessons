package lessonTest.samples.email;

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

public class EmailTester extends AbstractClassTester {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java -ea work_30_12.samples.email.EmailTester <ClassName>");
            System.out.println("Available classes: Email");
            return;
        }

        String className = args[0];
        Class<? extends AbstractClassTester> testerClass = EmailTester.class;

        if (testerClass == null) {
            System.out.println("Unknown class: " + className);
            System.out.println("Available classes: Email");
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
    
    public EmailTester(TestCounter counter) {
        super(counter);
    }
    
    @Override
    protected String getTargetClassName() {
        return "Email";
    }
    
    @Override
    protected List<FieldSpec> getFieldSpecs() {
        return List.of(
            new FieldSpec("email", String.class)
        );
    }
    
    @Override
    protected List<MethodSpec> getGetterSpecs() {
        return List.of(
            MethodSpec.getter("getEmail", String.class),
            MethodSpec.getter("getDomain", String.class),
            MethodSpec.getter("getUsername", String.class)
        );
    }
    
    @Override
    protected List<MethodSpec> getSetterSpecs() {
        return List.of(
            MethodSpec.setter("setEmail", String.class)
        );
    }
    
    @Override
    protected List<MethodSpec> getForbiddenMethods() {
        return List.of();
    }
    
    @Override
    protected List<ConstructorSpec> getConstructorSpecs() {
        return List.of(
            ConstructorSpec.of(String.class)
        );
    }
    
    @Override
    protected List<ValidationRule> getValidationRules() {
        return List.of(
            ValidationRule.of(
                "email", "setEmail", "getEmail",
                List.of("user@example.com", "test.user@mail.ru", "a@b.c"),
                List.of("invalid", "@example.com", "user@", "user @example.com", "user@@example.com"),
                "Email должен быть валидным"
            )
        );
    }
    
    @Override
    protected List<TestData> getTestData() {
        return List.of(
            new TestData(
                new Object[]{"user@example.com"},
                Map.of("getEmail", "user@example.com")
            )
        );
    }
    
    @Override
    protected List<ComputedPropertyTest> getComputedPropertyTests() {
        return List.of(
            new ComputedPropertyTest(
                new Object[]{"user@example.com"},
                "getDomain",
                "example.com",
                0
            ),
            new ComputedPropertyTest(
                new Object[]{"user@example.com"},
                "getUsername",
                "user",
                0
            )
        );
    }
    
    @Override
    protected List<BusinessMethodTest> getBusinessMethodTests() {
        return List.of(
            BusinessMethodTest.staticMethod(
                "isValid",
                new Object[]{"test@example.com"},
                true
            ),
            BusinessMethodTest.staticMethod(
                "isValid",
                new Object[]{"invalid"},
                false
            )
        );
    }
}
