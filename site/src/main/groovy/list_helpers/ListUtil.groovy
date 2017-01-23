package list_helpers

public class ListUtil {

    public static Integer sum(List<Integer> list) {
        if (list == null || list.size() < 1)
            return 0

        int sum = 0
        for (Integer i : list)
            sum = sum + i

        return sum
    }
}