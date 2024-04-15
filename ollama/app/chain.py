from langchain_community.chat_models import ChatOllama
from langchain_core.output_parsers import StrOutputParser
from langchain_core.prompts import ChatPromptTemplate

# LangChain이 지원하는 다른 채팅 모델을 사용합니다. 여기서는 Ollama를 사용합니다.
llm = ChatOllama(model="EEVE-Korean-10.8B:latest")

# 주제를 기반으로 짧은 농담을 요청하는 프롬프트 템플릿을 생성합니다.
prompt = ChatPromptTemplate.from_template(" You must answer in Korean. {topic} 에 대하여 간략히 설명해 줘.")

# LangChain 표현식 언어 체인 구문을 사용합니다.
chain = prompt | llm | StrOutputParser()

# 간결성을 위해 응답은 터미널에 출력됩니다.
# 프로덕션 환경에서 애플리케이션을 배포하기 위해 LangServe를 사용할 수 있습니다.
# print(chain.invoke({"topic": "임신성 고혈압에 대해 설명해줘"}))