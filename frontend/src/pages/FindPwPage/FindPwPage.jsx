// frontend/src/pages/FindPwPage/FindPwPage.jsx

import Header from "../../components/Header";
import Footer from "../../components/Footer";
import FindPwForm from ".//FindPwForm";

export default function FindPwPage() {
    return (
        <div className="w-full min-h-screen bg-gray-100 flex flex-col">
            <Header />

            <div className="flex-grow flex justify-center items-center px-4">
                <FindPwForm />
            </div>

            <Footer />
        </div>
    );
}
