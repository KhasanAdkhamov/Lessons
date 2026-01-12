package lessonTest.samples.rectangle;

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

public class RectangleTester extends AbstractClassTester {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java -ea RectangleTester <ClassName>");
            System.out.println("Available classes: Rectangle");
            return;
        }

        String className = args[0];
        Class<? extends AbstractClassTester> testerClass = RectangleTester.class;

        if (testerClass == null) {
            System.out.println("Unknown class: " + className);
            System.out.println("Available classes: Rectangle");
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
    
    public RectangleTester(TestCounter counter) {
        super(counter);
    }
    
    @Override
    protected String getTargetClassName() {
        return "Rectangle";
    }
    
    @Override
    protected List<FieldSpec> getFieldSpecs() {
        return List.of(
            new FieldSpec("width", double.class),
            new FieldSpec("height", double.class)
        );
    }
    
    @Override
    protected List<MethodSpec> getGetterSpecs() {
        return List.of(
            MethodSpec.getter("getWidth", double.class),
            MethodSpec.getter("getHeight", double.class),
            MethodSpec.getter("getArea", double.class),
            MethodSpec.getter("getPerimeter", double.class)
        );
    }
    
    @Override
    protected List<MethodSpec> getSetterSpecs() {
        return List.of(
            MethodSpec.setter("setWidth", double.class),
            MethodSpec.setter("setHeight", double.class)
        );
    }
    
    @Override
    protected List<MethodSpec> getForbiddenMethods() {
        return List.of();
    }
    
    @Override
    protected List<ConstructorSpec> getConstructorSpecs() {
        return List.of(
            ConstructorSpec.of(double.class, double.class),
            ConstructorSpec.of(double.class)
        );
    }
    
    @Override
    protected List<ValidationRule> getValidationRules() {
        return List.of(
            ValidationRule.of(
                "width", "setWidth", "getWidth",
                List.of(0.01, 1.0, 100.0),
                List.of(0.0, -1.0, -100.0),
                "Ширина должна быть больше 0"
            ),
            ValidationRule.of(
                "height", "setHeight", "getHeight",
                List.of(0.01, 1.0, 100.0),
                List.of(0.0, -1.0, -100.0),
                "Высота должна быть больше 0"
            )
        );
    }
    
    @Override
    protected List<TestData> getTestData() {
        return List.of(
            new TestData(
                new Object[]{3.0, 4.0},
                Map.of("getWidth", 3.0, "getHeight", 4.0)
            ),
            new TestData(
                new Object[]{5.0},
                Map.of("getWidth", 5.0, "getHeight", 5.0)
            )
        );
    }
    
    @Override
    protected List<ComputedPropertyTest> getComputedPropertyTests() {
        return List.of(
            // Площадь 3*4 = 12
            new ComputedPropertyTest(
                new Object[]{3.0, 4.0},
                "getArea",
                12.0,
                0.0001
            ),
            // Периметр 2*(3+4) = 14
            new ComputedPropertyTest(
                new Object[]{3.0, 4.0},
                "getPerimeter",
                14.0,
                0.0001
            )
        );
    }
    
    @Override
    protected List<BusinessMethodTest> getBusinessMethodTests() {
        return List.of(
            // isSquare() для квадрата
            BusinessMethodTest.returningMethod(
                new Object[]{5.0, 5.0},
                "isSquare",
                new Object[]{},
                true
            ),
            // isSquare() для прямоугольника
            BusinessMethodTest.returningMethod(
                new Object[]{3.0, 4.0},
                "isSquare",
                new Object[]{},
                false
            ),
            // resize() увеличение в 2 раза
            BusinessMethodTest.voidMethod(
                new Object[]{2.0, 3.0},
                "resize",
                new Object[]{2.0},
                Map.of("getWidth", 4.0, "getHeight", 6.0)
            )
        );
    }
}
