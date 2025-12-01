// src/components/DiaryForm.jsx

import { useState } from "react";
import DatePicker from "react-datepicker"; // react-datepicker import

export default function DiaryForm() {
    const [content, setContent] = useState("");
    const [selectedDate, setSelectedDate] = useState(new Date());

    const handleSubmit = (e) => {
        e.preventDefault();
        alert("일기가 등록되었습니다!");
    };

    return (
        <div className="w-full max-w-md mx-auto bg-white p-6 rounded-xl shadow space-y-6 min-w-[300px]">
            {/* 날짜 선택 부분 */}
            <div className="flex justify-center items-center space-x-4">
                <DatePicker
                    selected={selectedDate}
                    onChange={(date) => setSelectedDate(date)}
                    dateFormat="dd/MM/yyyy EEE"
                    className="w-full p-3 border-2 border-gray-300 rounded-lg focus:outline-none focus:ring-0 focus:border-black justify-center text-center"
                />
            </div>

            {/* 일기 제목 */}
            <div className="text-center text-xl font-semibold mt-2">오늘의 일기</div>

            {/* 일기 작성 폼 */}
            <form onSubmit={handleSubmit} className="space-y-4 mt-4">
                <div>
                    <textarea
                        value={content}
                        onChange={(e) => setContent(e.target.value)}
                        className="w-full p-3 border rounded-lg focus:outline-none focus:ring-2 focus:ring-black/40"
                        placeholder="일기를 작성하세요..."
                        rows="6"
                        required
                    />
                </div>

                {/* 버튼들 */}
                <div className="flex justify-between">
                    {/* 취소 버튼 */}
                    <button
                        type="button"
                        onClick={() => setContent("")}
                        className="w-1/3 py-2 text-sm text-gray-600 border border-gray-300 rounded-lg 
                                   hover:bg-gray-200 transition-colors duration-300"
                    >
                        Cancel
                    </button>

                    {/* 일기 등록 버튼 */}
                    <button
                        type="submit"
                        className="w-1/3 py-2 text-sm bg-black text-white rounded-lg 
                                   hover:bg-gray-800 transition-all duration-300"
                    >
                        일기 등록
                    </button>
                </div>
            </form>
        </div>
    );
}
