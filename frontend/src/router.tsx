import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Layout } from "./components/Layout";
import { Login } from "./page/Login";
import { ListProjects } from "./page/ListProjects";
import { NoAuthentication } from "./page/NoAuthentication";

export function Router() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Login />} />
          <Route path="/project">
            <Route path="list" element={<ListProjects />} />
          </Route>
          <Route path="/no-authentication" element={<NoAuthentication />} />
        </Route>
        <Route path="*" element={<h1>Page not find</h1>} />
      </Routes>
    </BrowserRouter>
  );
}
