import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Layout } from "./components/Layout";
import { Login } from "./page/Login";

export function Router() {
    return (
        <BrowserRouter>
        <Routes>
            <Route element={<Layout/>}>
                <Route index element={<Login />} />
                <Route path="*" element={<h1>Page not find</h1>} />
            </Route>           
        </Routes>
        </BrowserRouter>
    )
}