from fastapi import FastAPI
from pydantic import BaseModel
import torch
from transformers import BartForConditionalGeneration, PreTrainedTokenizerFast

app = FastAPI()

tn = PreTrainedTokenizerFast.from_pretrained("gogamza/kobart-base-v2")
model = BartForConditionalGeneration.from_pretrained("gogamza/kobart-base-v2")


class InputText(BaseModel):
    text: str

class OutputText(BaseModel):
    summary: str


@app.post("/summarize")
def summarize(input: InputText):
    
    input_text = input.text

    input_data = tn.encode(input_text, return_tensors="pt") #입력 텍스트 변환
    
    output_id = model.generate( #요약 결과 생성
        input_data,
        max_length=150, #요약 최대길이
        min_length=30, #요약 최소길이
        num_beams=5, #서치 크기
        repetition_penalty=2.0,
        no_repeat_ngram_size=3,
        eos_token_id=1,
        bad_words_ids=[[tn.unk_token_id]]
    )
    
    output_text = tn.decode( output_id.squeeze().tolist(), skip_special_tokens=True) #결과 변환
    
    return {
        "summary": output_text
    }
