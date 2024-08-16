# sort(): 메서드는 리스트 원본 자체의 값을 바꾼다.
a = [1, -5, 2, 4, 3]
b = [2, 1, 1, 3, 2, 5, 4]
c = [6, 1, 7]

def arrayWithSort(arr):
    arr.sort()
    return arr

print(arrayWithSort(a))
print(a)
print(arrayWithSort(b))
print(arrayWithSort(c))

# 원본 리스트를 변경하지 않고 정렬된 리스트를 반환하는 sorted()
def originListWithSort(arr):
    sorted_list = list(sorted(arr))  # 새로운 리스트를 반환
    return sorted_list

d = [2, 1, 1, 3, 2, 5, 4]
print(originListWithSort(d))
print(d)