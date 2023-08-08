import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Layout } from "./components/Layout";
import { Login } from "./page/Login";
import { ListProjects } from "./page/ListProject";
import { NoAuthentication } from "./page/NoAuthentication";
import { SaveProject } from "./page/SaveProject";
import { Home } from "./page/Home";

export function Router() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route element={<Home />} />
          <Route index element={<Login />} />
          <Route path="/project">
            <Route path="list" element={<ListProjects />} />
            <Route path="save" element={<SaveProject />} />
          </Route>
          <Route path="/no-authentication" element={<NoAuthentication />} />
        </Route>
        <Route path="*" element={<h1>Page not find</h1>} />
      </Routes>
    </BrowserRouter>
  );
}
