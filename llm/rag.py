from langchain_community.document_loaders.csv_loader import CSVLoader
from langchain_community.vectorstores import FAISS
from langchain_community.vectorstores.utils import DistanceStrategy
from langchain_community.embeddings import OllamaEmbeddings

# CSV 파일 경로
file_path = "csv 경로"

# CSVLoader를 사용하여 데이터 로드
qa_loader = CSVLoader(file_path=file_path)
qa_data = qa_loader.load()

# Document 객체에서 질문만 추출하여 벡터화할 데이터 준비
questions = []
qa_pairs = []

for doc in qa_data:
    content = doc.page_content
    if 'Q:' in content and 'A:' in content:
        q = content.split('Q:')[1].split('A:')[0].strip()
        a = content.split('A:')[1].strip()
        questions.append(q)
        qa_pairs.append((q, a))

# FAISS 벡터스토어 생성
vectorstore = FAISS.from_texts(questions,
                               embedding=OllamaEmbeddings(model="EEVE-Korean-10.8B:latest"),
                               distance_strategy=DistanceStrategy.COSINE)

# 로컬에 FAISS 인덱스 저장
MY_FAISS_INDEX = "test_index"
vectorstore.save_local(MY_FAISS_INDEX)

# 로컬에서 FAISS 인덱스 로드
vectorstore = FAISS.load_local(MY_FAISS_INDEX, OllamaEmbeddings(model="EEVE-Korean-10.8B:latest"), allow_dangerous_deserialization=True)
retriever = vectorstore.as_retriever(search_type="similarity", search_kwargs={"k": 2})

# 사용자의 질문
user_query = """DEHP 분석 검사 할 수 있나요?"""

# 질문과 유사한 문서를 검색
retrieved_docs = retriever.invoke(user_query)

print("==============")
print(retrieved_docs)
print("==============")

# 검색 결과에서 답변을 추출하고 포맷팅
results = []
for doc in retrieved_docs:
    retrieved_question = doc.page_content
    for q, a in qa_pairs:
        if retrieved_question == q:
            results.append(f"Q: {q}\nA: {a}")
            break

# 결과 출력
context = "\n\n".join(results)
print(context)