# 게임 캐릭터 명렁어
# U: 위쪽으로 한 칸 이동
# D: 아래쪽으로 한 칸 이동
# R: 오른쪽으로 한 칸 이동
# L: 왼쪽으로 한 칸 이동
# 캐릭터는 좌표평면의 (0,0)위치에서 시작, 좌표평면의 경계는 왼쪽 위(-5, 5), 왼쪽 아래(-5, -5), 오른쪽 위(5, 5), 오른쪽 아래(5, -5)구성.
# 게임 케릭터가 지나간 길 중 캐릭터가 처음 걸어본 길의 길이를 구해라.

def solution(dirs):
    # 현재 위치
    x, y = 0, 0
    # 지나온 경로를 저장하는 set
    visited_paths = set()
    
    # 방향에 따른 이동 변화량
    move = {'U': (0, 1), 'D': (0, -1), 'R': (1, 0), 'L': (-1, 0)}
    
    for d in dirs:
        # 이동할 좌표 계산
        nx, ny = x + move[d][0], y + move[d][1]
        
        # 경계를 넘어가지 않는다면
        if -5 <= nx <= 5 and -5 <= ny <= 5:
            # 경로 저장 (양방향 모두 저장)
            visited_paths.add(((x, y), (nx, ny)))
            visited_paths.add(((nx, ny), (x, y)))
            # 현재 위치 갱신
            x, y = nx, ny
    
    # 지나간 길의 개수 / 2를 반환 (양방향을 모두 저장했으므로)
    return len(visited_paths) // 2

# 테스트
dirs = 'ULURRDLLU'
print(solution(dirs))