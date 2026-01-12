package lessonTest.samples.product;

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

public class ProductTester extends AbstractClassTester {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java -ea ProductTester <ClassName>");
            System.out.println("Available classes: Product");
            return;
        }

        String className = args[0];
        Class<? extends AbstractClassTester> testerClass = ProductTester.class;

        if (testerClass == null) {
            System.out.println("Unknown class: " + className);
            System.out.println("Available classes: Product");
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

    public ProductTester(TestCounter counter) {
        super(counter);
    }
    
    @Override
    protected String getTargetClassName() {
        return "Product";
    }
    
    @Override
    protected List<FieldSpec> getFieldSpecs() {
        return List.of(
            new FieldSpec("name", String.class),
            new FieldSpec("price", double.class),
            new FieldSpec("quantity", int.class)
        );
    }
    
    @Override
    protected List<MethodSpec> getGetterSpecs() {
        return List.of(
            MethodSpec.getter("getName", String.class),
            MethodSpec.getter("getPrice", double.class),
            MethodSpec.getter("getQuantity", int.class)
        );
    }
    
    @Override
    protected List<MethodSpec> getSetterSpecs() {
        return List.of(
            MethodSpec.setter("setName", String.class),
            MethodSpec.setter("setPrice", double.class),
            MethodSpec.setter("setQuantity", int.class)
        );
    }
    
    @Override
    protected List<MethodSpec> getForbiddenMethods() {
        return List.of();
    }
    
    @Override
    protected List<ConstructorSpec> getConstructorSpecs() {
        return List.of(
            ConstructorSpec.of(String.class, double.class, int.class)
        );
    }
    
    @Override
    protected List<ValidationRule> getValidationRules() {
        return List.of(
            ValidationRule.of(
                "name", "setName", "getName",
                List.of("Товар", "A"),
                Arrays.asList("", null),
                "Имя не должно быть пустым"
            ),
            ValidationRule.of(
                "price", "setPrice", "getPrice",
                List.of(0.01, 100.0, 1000.0),
                List.of(0.0, -1.0, -100.0),
                "Цена должна быть больше 0"
            ),
            ValidationRule.of(
                "quantity", "setQuantity", "getQuantity",
                List.of(0, 1, 100),
                List.of(-1, -100),
                "Количество не может быть отрицательным"
            )
        );
    }
    
    @Override
    protected List<TestData> getTestData() {
        return List.of(
            new TestData(
                new Object[]{"Товар", 100.0, 10},
                Map.of("getName", "Товар", "getPrice", 100.0, "getQuantity", 10)
            )
        );
    }
    
    @Override
    protected List<ComputedPropertyTest> getComputedPropertyTests() {
        return List.of(
            new ComputedPropertyTest(
                new Object[]{"Товар", 100.0, 5},
                "getTotalValue",
                500.0,
                0.0001
            ),
            new ComputedPropertyTest(
                new Object[]{"Товар", 50.0, 0},
                "getTotalValue",
                0.0,
                0.0001
            )
        );
    }
    
    @Override
    protected List<BusinessMethodTest> getBusinessMethodTests() {
        return List.of(
            // sell() успешная продажа
            BusinessMethodTest.voidMethod(
                new Object[]{"Товар", 100.0, 10},
                "sell",
                new Object[]{5},
                Map.of("getQuantity", 5)
            ),
            // sell() при недостатке товара
            BusinessMethodTest.throwingMethod(
                new Object[]{"Товар", 100.0, 5},
                "sell",
                new Object[]{10},
                IllegalArgumentException.class
            ),
            // restock() пополнение
            BusinessMethodTest.voidMethod(
                new Object[]{"Товар", 100.0, 5},
                "restock",
                new Object[]{10},
                Map.of("getQuantity", 15)
            )
        );
    }
}
