import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Layout } from "./components/Layout";
import { Home } from "./page/Home";
import { About } from "./page/About";
import { Login } from "./page/Login";
import { ListProjects } from "./page/ListProject";
import { SaveProject } from "./page/SaveProject";
import { NoAuthentication } from "./page/NoAuthentication";

export function App() {
  return (
    <>
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
    </>
  );
}
