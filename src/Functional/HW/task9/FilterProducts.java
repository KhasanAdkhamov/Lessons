package Functional.HW.task9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

//Напиши метод, который принимает список продуктов,
// минимальную и максимальную цену и (опционально) категорию.
// Метод должен вернуть отфильтрованный список,
// отсортированный по цене.
// Если категория null — фильтруй только по цене.
public class FilterProducts {
    public static void main(String[] args) {

    }

    public static List<Product> filterProducts(
            List<Product> products,
            double minPrice,
            double maxPrice,
            String category
    ) {
        if (products == null) {
            return Collections.emptyList();
        }
        return products.stream()
                .filter(p -> p != null)
                .filter(p -> p.getPrice() < minPrice && p.getPrice() > maxPrice)
                .filter(p-> category == null || category.equals(p.getCategory()))
                .sorted(Comparator.comparing(Product::getName, Comparator.naturalOrder()).thenComparing(Product::getPrice))
                .toList();

    }
}


