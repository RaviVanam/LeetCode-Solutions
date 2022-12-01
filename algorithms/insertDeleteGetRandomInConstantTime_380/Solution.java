package insertDeleteGetRandomInConstantTime_380;

import java.util.*;

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
class RandomizedSet {

    Random rand;
    List<Integer> arr;
    Map<Integer, Integer> map;

    public RandomizedSet() {
        this.arr = new ArrayList<>();
        this.map = new HashMap<>();
        this.rand = new Random();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        map.put(val, arr.size());
        arr.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;

        int removeIndex = map.get(val);
        int lastIndexVal = arr.get(arr.size() - 1);
        arr.set(removeIndex, lastIndexVal);
        arr.remove(arr.size() - 1);

        map.put(lastIndexVal, removeIndex);
        map.remove(val);
        return true;
    }

    public int getRandom() {
        return arr.get(rand.nextInt(arr.size()));
    }
}

