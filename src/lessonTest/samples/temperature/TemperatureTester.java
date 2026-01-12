package lessonTest.samples.temperature;

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

public class TemperatureTester extends AbstractClassTester {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java -ea TemperatureTester <ClassName>");
            System.out.println("Available classes: Temperature");
            return;
        }

        String className = args[0];
        Class<? extends AbstractClassTester> testerClass = TemperatureTester.class;

        if (testerClass == null) {
            System.out.println("Unknown class: " + className);
            System.out.println("Available classes: Temperature");
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
    
    public TemperatureTester(TestCounter counter) {
        super(counter);
    }
    
    @Override
    protected String getTargetClassName() {
        return "Temperature";
    }
    
    @Override
    protected List<FieldSpec> getFieldSpecs() {
        return List.of(
            new FieldSpec("celsius", double.class)
        );
    }
    
    @Override
    protected List<MethodSpec> getGetterSpecs() {
        return List.of(
            MethodSpec.getter("getCelsius", double.class),
            MethodSpec.getter("getFahrenheit", double.class),
            MethodSpec.getter("getKelvin", double.class)
        );
    }
    
    @Override
    protected List<MethodSpec> getSetterSpecs() {
        return List.of(
            MethodSpec.setter("setCelsius", double.class),
            MethodSpec.setter("setFahrenheit", double.class)
        );
    }
    
    @Override
    protected List<MethodSpec> getForbiddenMethods() {
        return List.of();
    }
    
    @Override
    protected List<ConstructorSpec> getConstructorSpecs() {
        return List.of(
            ConstructorSpec.of(double.class)
        );
    }
    
    @Override
    protected List<ValidationRule> getValidationRules() {
        return List.of(
            ValidationRule.of(
                "celsius", "setCelsius", "getCelsius",
                List.of(-273.15, 0.0, 100.0, 1000.0),
                List.of(-273.16, -300.0, -1000.0),
                "Температура не ниже абсолютного нуля"
            )
        );
    }
    
    @Override
    protected List<TestData> getTestData() {
        return List.of(
            new TestData(
                new Object[]{0.0},
                Map.of("getCelsius", 0.0)
            ),
            new TestData(
                new Object[]{100.0},
                Map.of("getCelsius", 100.0)
            )
        );
    }
    
    @Override
    protected List<ComputedPropertyTest> getComputedPropertyTests() {
        return List.of(
            // 0°C = 32°F
            new ComputedPropertyTest(
                new Object[]{0.0},
                "getFahrenheit",
                32.0,
                0.0001
            ),
            // 100°C = 212°F
            new ComputedPropertyTest(
                new Object[]{100.0},
                "getFahrenheit",
                212.0,
                0.0001
            ),
            // 0°C = 273.15K
            new ComputedPropertyTest(
                new Object[]{0.0},
                "getKelvin",
                273.15,
                0.0001
            ),
            // -273.15°C = 0K
            new ComputedPropertyTest(
                new Object[]{-273.15},
                "getKelvin",
                0.0,
                0.0001
            )
        );
    }
    
    @Override
    protected List<BusinessMethodTest> getBusinessMethodTests() {
        return List.of();
    }
}
