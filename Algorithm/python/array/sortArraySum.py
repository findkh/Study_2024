# 두 개 뽑아서 더하기
# 정수 배열 numbers가 주어진다. numbers에서 서로 다른 인덱스에 있는 2개의 수를 뽑아 더해 만들 수 있는 모든 수를 배열에 오름차순으로 담아 반환하는 solution()함수를 완성.
# 제약 조건
# numbers의 길이는 2이상 100이하
# numbers의 모든 수는 0이상 100이하

def solution(arr):
    sumArr = []
    for i in range(len(arr)):
        for j in range(i + 1, len(arr)):
            sumArr.append(arr[i] + arr[j]) 
    # print(sumArr)
    return sorted(set(sumArr))

numbers1 = [2, 1, 3, 4, 1]
numbers2 = [5, 0, 2, 7]

print(solution(numbers1))
print(solution(numbers2))