package algo.arrays;

import java.util.Arrays;

public class Lesson {

    public static void main(String[] args) {
//        int[] arr = new int[5];
//
//        int[] arr1 = {11,1};
//
//        arr[0];
//
//        arr[arr.length-1];
//
//
//        arr[0] =1;

        /*
        Два указателя

        Случаи применения:
            Поиск пары элементов с определённой суммой (в отсортированном массиве)
            Разворот массива или строки
            Удаление дубликатов из отсортированного массива
            Проверка палиндрома
            Разделение массива на части (partition)
            Слияние двух отсортированных массивов
         */

        // Развернуть массив на месте (без создания нового массива).


        /*
        [1,2,3,4]
        1) l = 0, r = 3
        [4,2,3,1]

        2) l=1; r=2
        [4,3,2,1]

        3)l = 2; r=1  ?!! цикл должен прерваться!

        [4,3,2,1]

        Алгоритм:
        1. определяем указатели:  left = начало, right = конец
        2. меняем элементьы местами
        3. двигаем указатели навстречу
        4. повторяем до тех пор пока указатели не встретятся
         */
//        reverse(new int[]{1,2,3,4,5});

        /*

        Найти пару с заданной суммой (в отсортированном массиве).
        Дан отсортированный массив. Найти два числа, сумма которых равна target

        [1,2,3,7]

        1) l3 = 0, r = 3, sum = 8, target = 5

        sum > target -> r--

        2) l = 0, r = 2, sum = 4, target = 5
        sum < target -> l++

        2) l = 1, r = 2, sum = 5, target = 5
        sum == target -> возращаем массив двух чисел [2,3]

        Алгоритм:

        1. определяем указатели:  left = начало, right = конец
        2. вычисляем сумму
        3. сравниваем сумму с таргетом и решаем как изменять указатели
        4. изменяем указатели или возвращаем результат
        5. повторяем до тех пор пока указатели не встретятся

         */
        int[] arrr = {1, 2, 3, 4, 7};
        findSum(arrr, 5);

    }

    /*
    {1, 2, 3, 4, 7}
    1) l = 0 и r = 4
    sum  = 8, target = 5
    8>5 -> r-- r = 3
    2) l<r (0<3)
    sum = 5, target = 5
    5 == 5

     */
    public static void findSum(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left<right) {
            int sum = arr[left] + arr[right];
            if (sum > target) {
                right--;
            } else if (sum < target) {
                left++;
            } else {
                int[] arr2 = new int[2];
                arr2[0] = left;
                arr2[1] = right;
                System.out.println(Arrays.toString(arr2));
                break;
            }
        }
    }

    public static void reverse(int[] arr){

        System.out.println("До реверса: " + Arrays.toString(arr));

        int left = 0;
        int right = arr.length -1;

        while (left<right){
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }

        System.out.println("После реверса: " + Arrays.toString(arr));
    }



}
