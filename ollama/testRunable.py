from langchain_community.llms import Ollama
from langchain.callbacks.streaming_stdout import StreamingStdOutCallbackHandler
from langchain.callbacks.manager import CallbackManager

callback_manager = CallbackManager([StreamingStdOutCallbackHandler()])

llm = Ollama(model="EEVE-Korean-10.8B:latest", callbacks=callback_manager)

response = llm.invoke("하시모토 갑상선에 대해 설명해주세요")

print(response)