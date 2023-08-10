import { NavBar } from "../../components/Navbar";
import { useNavigate } from "react-router-dom";
import "./index.css";
import { FromSave } from "./FormSave";
import { useEffect } from "react";
import { getSession } from "../../service/auth";

export function SaveProject() {
  const navigate = useNavigate();

  useEffect(() => {
    getSession().then((value) => {
      if (value === null) {
        navigate("/no-authentication", {
          replace: true,
        });
      } 
    })
  }, []);
  
  return (
    <>
      <NavBar />
      <FromSave />
    </>
  );
}