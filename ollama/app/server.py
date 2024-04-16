from fastapi import FastAPI, HTTPException, Request
from fastapi.responses import RedirectResponse, JSONResponse, StreamingResponse
from fastapi.middleware.cors import CORSMiddleware
from typing import List, Union
from langserve.pydantic_v1 import BaseModel, Field
from langchain_core.messages import HumanMessage, AIMessage, SystemMessage
from langserve import add_routes
from chain import chain
from chat import chain as chat_chain
from llm import llm as model
import json
import asyncio


app = FastAPI()

# Set all CORS enabled origins
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
    expose_headers=["*"],
)


@app.get("/")
async def redirect_root_to_docs():
    return RedirectResponse("/prompt/playground")


add_routes(app, chain, path="/prompt")


class InputChat(BaseModel):
    """Input for the chat endpoint."""

    messages: List[Union[HumanMessage, AIMessage, SystemMessage]] = Field(
        ...,
        description="The chat messages representing the current conversation.",
    )


add_routes(
    app,
    chat_chain.with_types(input_type=InputChat),
    path="/chat",
    enable_feedback_endpoint=True,
    enable_public_trace_link_endpoint=True,
    playground_type="chat",
)

add_routes(app, model, path="/llm")

@app.post("/getAnswer")
async def get_answer(request: Request):
    try:
        req_info = await request.json()
        query = req_info.get("topic")

        if not query:
            raise ValueError("Topic is required")

        print('Received query:', query)

        async def stream_generator(query):
            index = 0  # 청크 인덱스 초기화
            async for chunk in chain.astream({"topic": query}):
                print(chunk)  # 로그 출력
                # 클라이언트에 JSON 형식으로 스트리밍 (각 청크는 별도의 JSON 객체)
                yield json.dumps({"index": index, "value": chunk}) + "\n"
                index += 1  # 인덱스 증가

        return StreamingResponse(stream_generator(query), media_type='application/json')

    except ValueError as e:
        return JSONResponse(status_code=400, content={"status": "ERROR", "message": str(e)})
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))
    
# @app.post("/getAnswer")
# async def get_answer(request: Request):
#     try:
#         req_info = await request.json()
#         query = req_info.get("topic")

#         if not query:
#             raise ValueError("Topic is required")

#         print('Received query:', query)

#         async def stream_generator(query):
#             # 여기서 chain.astream을 호출하여 LLM 모델로부터 데이터를 받습니다.
#             async for chunks in chain.astream({"topic": query}):
#                 print(chunks)  # 로그 출력
#                 yield json.dumps(chunks) + "\n"  # 클라이언트에 JSON 형식으로 스트리밍

#         return StreamingResponse(stream_generator(query), media_type='application/json')

    except ValueError as e:
        return JSONResponse(status_code=400, content={"status": "ERROR", "message": str(e)})
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))


if __name__ == "__main__":
    import uvicorn

    uvicorn.run(app, host="0.0.0.0", port=8000)
