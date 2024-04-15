from langchain_community.chat_models import ChatOllama
from langchain_core.output_parsers import StrOutputParser
from langchain_core.prompts import ChatPromptTemplate

llm = ChatOllama(model="EEVE-Korean-10.8B:latest")

prompt = ChatPromptTemplate.from_template(" You must answer in Korean. {topic} 에 대하여 간략히 설명해 줘.")

chain = prompt | llm | StrOutputParser()

print(chain.invoke({"topic": "임신성 고혈압에 대해 설명해줘"}))