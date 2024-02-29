import { Footer } from "../../components/Footer/index,";
import { NavbarLandingPage } from "../../components/NavbarLandingPage";
import { FormCreateAccount } from "./FormCreateAccount";

export function CreateAccount() {
  return (
    <>
      <NavbarLandingPage />
      <div className="ajust2">
        <FormCreateAccount />
      </div>
      <Footer />
    </>
  );
}