# 제약 조건
# 정수 배열을 받아, 배열의 중복값을 제거하고 배열 데이터를 내림차순으로 정렬해서 반환하는 solution() 함수 구현

def solution(arr):
    # 중복 제거 후 내림 차순 정렬
    return sorted(set(arr), reverse=True)

arr1 = [4,2,2,1,3,4]
arr2 = [2,1,1,3,2,5,4]

print(solution(arr1))
print(solution(arr2))