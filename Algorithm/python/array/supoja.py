# 수포자 삼인방은 모의고사에 수학 문제를 전부 찍으려 한다.
# 수포자1: 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, ...
# 수포자2: 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, ...
# 수포자3: 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, ...
# 1번 문제부터 마지막 문제까지의 정답이 수서대로 저장된 배열 answer가 주어졌을 때 가장 많은 문제를 맞힌 사람이 누구인지 배열에 담아 반환한도록 solution() 함수를 작성
# 제약 조건
# 시험은 최대 10,000 문제, 문제의 정답은 1,2,3,4,5 중 하나
# 가장 높은 점수를 받은 사람이 여럿이라면 반환하는 값을 오름차순으로 정렬.

def solution():
    patterns = [
        [1, 2, 3, 4, 5],
        [2, 1, 2, 3, 2, 4, 2, 5],
        [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]
    ]

    answers = [1,2,3,4,5,1,2,3,4,5,1,2,3,4,5]

    scores = [0] * 3

    for i, answer in enumerate(answers):
        for j, pattern in enumerate(patterns):
            if answer == pattern[i % len(pattern)]:
                scores[j] += 1
    
    max_score = max(scores)

    highest_scores = []

    for i, score, in enumerate(scores):
        if score == max_score:
            highest_scores.append(i + 1)

    return highest_scores

print(solution())


