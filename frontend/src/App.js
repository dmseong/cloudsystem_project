import { BrowserRouter, Routes, Route } from "react-router-dom";
import MyPage from "./pages/MyPage/MyPage";
import LoginPage from "./pages/LoginPage/LoginPage";
import FindIdPage from "./pages/FindIdPage/FindIdPage";
import FindPwPage from "./pages/FindPwPage/FindPwPage";


function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/mypage" element={<MyPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/find-id" element={<FindIdPage />} />
        <Route path="/find-pw" element={<FindPwPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
