import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Layout } from "./components/Layout";
import { Home } from "./page/Home";
import { About } from "./page/About";
import { LoginAccount } from "./page/LoginAccount";
import { ListProjects } from "./page/ListProject";
import { SaveProject } from "./page/SaveProject";
import { NoAuthentication } from "./page/NoAuthentication";
import { CreateAccount } from "./page/CreateAccount";

export function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Layout />}>
            <Route index element={<Home />} />
            <Route path="/about" element={<About />} />
            <Route path="/account">
              <Route path="login" element={<LoginAccount />} />
              <Route path="create" element={<CreateAccount />} />
            </Route>
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
