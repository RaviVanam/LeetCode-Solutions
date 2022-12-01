package findMedianFromDataStream_295;

import java.util.PriorityQueue;

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
class MedianFinder {

    PriorityQueue<Integer> leftHeap;
    PriorityQueue<Integer> rightHeap;

    public MedianFinder() {
        this.leftHeap = new PriorityQueue<>((a, b) -> Integer.compare(b,a));
        this.rightHeap = new PriorityQueue<>(Integer::compare);
    }

    public void addNum(int num) {
        if (leftHeap.size() == rightHeap.size()) {
            if (!rightHeap.isEmpty() && num > rightHeap.peek()) {
                leftHeap.add(rightHeap.poll());
                rightHeap.add(num);
            } else {
                leftHeap.add(num);
            }
        } else {
            if (!leftHeap.isEmpty() && num < leftHeap.peek()) {
                rightHeap.add(leftHeap.poll());
                leftHeap.add(num);
            } else {
                rightHeap.add(num);
            }
        }
    }

    public double findMedian() {
        if (!rightHeap.isEmpty() && leftHeap.size() == rightHeap.size())
            return (double)(leftHeap.peek() + rightHeap.peek()) / 2;
        return (double)leftHeap.peek();
    }
}