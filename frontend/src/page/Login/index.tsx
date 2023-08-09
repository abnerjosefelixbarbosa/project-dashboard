import { Footer } from "../../components/Footer/index,";
import { NavbarLandingPage } from "../../components/NavbarLandingPage";
import { FormLogin } from "./formLogin";
import "./index.css";

export function Login() {
  return (
    <>
      <NavbarLandingPage />
      <div className="ajust2">
        <FormLogin />
      </div>
      <Footer />
    </>
  );
}
