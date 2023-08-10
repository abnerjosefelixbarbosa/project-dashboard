import "./index.css";
import { NavBar } from "../../components/Navbar";
import { List } from "./List";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import { getSession } from "../../service/auth";

export function ListProjects() {
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
      <List />
    </>
  );
}
