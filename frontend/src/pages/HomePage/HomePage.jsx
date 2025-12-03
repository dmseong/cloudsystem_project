// frontend/src/pages/HomePage/HomePage.jsx

import Header from "../../components/Header";
import Footer from "../../components/Footer";
import { useNavigate } from "react-router-dom"
import logo from "../../assets/logo.png";;

export default function HomePage() {
    const navigate = useNavigate();

    return (
        <div className="w-full min-h-screen bg-gray-100 flex flex-col">

            <Header />

            {/* 메인 컨텐츠 */}
            <div className="flex-grow flex flex-col items-center text-center px-6 py-10">

                {/* Hero Section */}
                <h1 className="text-2xl font-bold mt-6">
                    감정으로 하루를 기록하는 감성 일기 서비스
                </h1>

                {/* 감성 일러스트 느낌 배경 */}
                <div className="w-64 h-64 mt-8 rounded-full bg-gradient-to-br from-green-100 to-yellow-100 shadow-lg flex items-center justify-center">
                    <img src={logo} alt="logo" className="w-24" />
                </div>

                {/* 주요 기능 버튼 */}
                <div className="mt-10 w-full max-w-sm space-y-4">

                    <button
                        onClick={() => navigate("/diary")}
                        className="
                            w-full py-4 bg-black text-white rounded-xl 
                            hover:bg-gray-800 transition-all duration-300
                            font-medium text-lg
                        "
                    >
                        오늘의 일기 작성하기
                    </button>

                    <button
                        onClick={() => navigate("/mypage")}
                        className="
                            w-full py-4 bg-white border border-gray-300 rounded-xl 
                            hover:bg-gray-100 transition-all duration-300
                            font-medium text-lg
                        "
                    >
                        내 감정 기록 확인하기
                    </button>
                </div>
            </div>

            <Footer />
        </div>
    );
}
