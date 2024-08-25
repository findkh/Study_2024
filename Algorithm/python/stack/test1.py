# 괄호 짝 맞추기
# 소괄호는 짝을 맞춘 열린 괄호와 닫힌 괄호로 구성된다. 
# 문제에서는 열린 괄호나 닫힌 괄호가 마구 뒤섞인 문자열은준다.
# 이때 소괄호가 정상으로 열고 닫혔는지 판별하는 solution()함수를 구현하라.
# 소괄호가 정상적으로 열고 닫혔다면 True를 그렇지 않다면 False를 리턴하라.

def solution(str):
    stack = []
    for char in str:
        if char == '(':
            stack.append(char)
        elif char == ')':
            if not stack: 
                return False
            else:
                stack.pop()
    
    if stack:
        return False
    else:
        return True


print(solution("(())()"))
print(solution("((())()"))
