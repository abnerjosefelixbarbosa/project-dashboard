import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Layout } from "./components/Layout";
import { Login } from "./page/Login";
import { ListProjects } from "./page/ListProjects";

export function Router() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />} >
          <Route index element={<Login />}  />
          <Route path="/project">
            <Route index element={<ListProjects />} />
          </Route>
          <Route path="*" element={<h1>Page not find</h1>} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}
