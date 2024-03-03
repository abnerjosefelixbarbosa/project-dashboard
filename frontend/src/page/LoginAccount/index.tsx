import { Footer } from "../../components/Footer";
import { NavbarLandingPage } from "../../components/NavbarLandingPage";
import { FormLogin } from "./formLogin";
import "./index.css";

export function LoginAccount() {
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
