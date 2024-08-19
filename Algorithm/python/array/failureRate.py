# 실패율
# 스테이지에 도달했으나 아직 클리어 하지 못한 스테이지의 번호가 담긴 배열 stages가 주어질 때 실패율이 높은 스테이지부터 내림차순으로 반환하는 solution함수 반환
# 제약 조건
# 스테이지 개수 N은 1 이상 500 이하의 자연수
# stages의 길이는 1 이상 200,000 이하
# stages에는 1 이상 N + 1 이하의 자연수가 있다.
# 실패율이 같은 스테이지가 있다면 작은 번호의 스테이지 먼저 오면 된다.
# 스테이지에 도달한 유저가 없는 경우 해당 스테이지의 실패율은 0으로 정의한다.

def solution(N, stages):
    # 도전자 수 구함
    challenger = [0] * (N + 2)
    for stage in stages:
        challenger[stage] += 1

    # 스이지별 실패한 사용자 수 계산
    fails = {}
    total = len(stages)


    # 실패율 계산
    for i in range(1, N + 1):
        if total == 0:
            fails[i] = 0  # 더 이상 도달한 사용자가 없는 경우 실패율은 0
        else:
            fails[i] = challenger[i] / total  # 실패율 계산
            print(fails)
            total -= challenger[i]  # 다음 스테이지로 넘어갈 때 남은 도전자 수 감소

    result = sorted(fails, key = lambda x: fails[x], reverse=True)
    return result

N = 5
stages = [2, 1, 2, 6, 2, 4, 3, 3]
print(solution(N, stages))