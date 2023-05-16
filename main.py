from typing import Any, Generator


def merge_sort(collection: [Any]) -> [Any]:
    def merge(left, right) -> Generator[Any]:
        while left and right:
            yield (right, left)[left[0] <= right[0]].pop(0)
        yield from left
        yield from right

    if len(collection) <= 1:
        return collection

    middle = len(collection) >> 1
    left_half = merge_sort(collection[:middle])
    right_half = merge_sort(collection[middle:])

    return list(merge(left_half, right_half))


if __name__ == '__main__':
    print(merge_sort([5,325,325,23,2,45,2,5,7,4,2,6,8,3,5]))