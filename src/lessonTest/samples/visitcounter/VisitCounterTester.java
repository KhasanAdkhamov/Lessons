package lessonTest.samples.visitcounter;

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

public class VisitCounterTester extends AbstractClassTester {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java -ea VisitCounterTester <ClassName>");
            System.out.println("Available classes: VisitCounter");
            return;
        }

        String className = args[0];
        Class<? extends AbstractClassTester> testerClass = VisitCounterTester.class;

        if (testerClass == null) {
            System.out.println("Unknown class: " + className);
            System.out.println("Available classes: VisitCounter");
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
    
    public VisitCounterTester(TestCounter counter) {
        super(counter);
    }
    
    @Override
    protected String getTargetClassName() {
        return "VisitCounter";
    }
    
    @Override
    protected List<FieldSpec> getFieldSpecs() {
        return List.of(
            new FieldSpec("totalVisits", int.class),
            new FieldSpec("todayVisits", int.class)
        );
    }
    
    @Override
    protected List<MethodSpec> getGetterSpecs() {
        return List.of(
            MethodSpec.getter("getTotalVisits", int.class),
            MethodSpec.getter("getTodayVisits", int.class)
        );
    }
    
    @Override
    protected List<MethodSpec> getSetterSpecs() {
        return List.of(); // нет сеттеров для счётчиков!
    }
    
    @Override
    protected List<MethodSpec> getForbiddenMethods() {
        return List.of(
            MethodSpec.setter("setTotalVisits", int.class),
            MethodSpec.setter("setTodayVisits", int.class)
        );
    }
    
    @Override
    protected List<ConstructorSpec> getConstructorSpecs() {
        return List.of(
            ConstructorSpec.of()
        );
    }
    
    @Override
    protected List<ValidationRule> getValidationRules() {
        return List.of(); // валидация не применяется, так как нет сеттеров
    }
    
    @Override
    protected List<TestData> getTestData() {
        return List.of(
            new TestData(
                new Object[]{},
                Map.of("getTotalVisits", 0, "getTodayVisits", 0)
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
            // registerVisit() увеличивает счётчики
            BusinessMethodTest.voidMethod(
                new Object[]{},
                "registerVisit",
                new Object[]{},
                Map.of("getTotalVisits", 1, "getTodayVisits", 1)
            ),
            // hasReachedLimit() начально false
            BusinessMethodTest.returningMethod(
                new Object[]{},
                "hasReachedLimit",
                new Object[]{},
                false
            )
        );
    }
}
