import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Layout } from "./components/Layout";
import { Login } from "./page/Login";
import { ListProjects } from "./page/ListProject";
import { NoAuthentication } from "./page/NoAuthentication";
import { SaveProject } from "./page/SaveProject";
import { Home } from "./page/Home";
import { About } from "./page/About";

export function Router() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="/about" element={<About />} />
          <Route path="/login" element={<Login />} />
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
