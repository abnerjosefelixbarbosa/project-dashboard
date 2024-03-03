import { NavBar } from "../../components/Navbar";
import { useLocation, useNavigate } from "react-router-dom";
import "./index.css";
import { FromCreate } from "./FormCreate";
import { useEffect } from "react";

export function CreateProject() {
  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    console.log(location.state)
    if (location.state === undefined) {
      navigate("/no-authentication", {
        replace: true,
      });
    } 
  }, []);
  
  return (
    <>
      <NavBar />
      <FromCreate />
    </>
  );
}