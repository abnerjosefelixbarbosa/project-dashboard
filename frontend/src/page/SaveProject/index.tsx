import { useEffect,  } from "react";
import { NavBar } from "../../components/Navbar";
import { useNavigate } from "react-router-dom";
import "./index.css";
import { FromSave } from "./FormSave";
import { getUser } from "../../service/auth";

export function SaveProject() {
  const navigate = useNavigate();

  useEffect(() => {
    checkUser();
  }, []);

  function checkUser() {
    getUser().then((user) => {
      if (user === null) {
        navigate("/no-authentication")
      }
    })
  }
  
  return (
    <>
      <NavBar />
      <FromSave />
    </>
  );
}