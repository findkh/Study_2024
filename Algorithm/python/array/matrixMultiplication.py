# 2차원 행렬 arr1과 arr2를 입력 받아 arr1에 arr2를 곱한 결과를 반환하는 solution() 함수 완성
# 제약 조건
# 행렬 arr1, arr2의 행과 열의 길이는 2이상 100 이하이다.
# 행렬 arr1, arr2의 데이터는 -10 이상 20 이하인 자연수이다.
# 곱할 수 있는 배열만 주어진다.

def solution(arr1, arr2):
    # arr1, arr2의 행과 열의 수
    r1, c1 = len(arr1), len(arr1[0])
    r2, c2 = len(arr2), len(arr2[0])

    # 결과를 저장할 2차원 리스트 초기화
    result = [[0] * c2 for _ in range(r1)]
    # for _ in range(r1):
    #     row = [0] * c2
    #     result.append(row)

    for i in range(r1):
        for j in range(r2):
            for k in range(c1):
                result[i][j] += arr1[i][k] * arr2[k][j]
    return result
    

arr1 = [[1,4],[3,2],[4,1]]
arr2 = [[3,3],[3,3]]

print(solution(arr1, arr2))

arr1 = [[2,3,2],[4,2,4],[3,1,4]]
arr2 = [[5,4,3],[2,4,1],[3,1,1]]
print(solution(arr1, arr2))