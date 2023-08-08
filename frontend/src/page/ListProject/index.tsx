import { useEffect } from "react";
import "./index.css";
import { NavBar } from "../../components/Navbar";
import { List } from "./List";
import { useNavigate } from "react-router-dom";
import { getUser } from "../../service/auth";

export function ListProjects() {
  const navigate = useNavigate();

  useEffect(() => {
    checkUser();
  }, []);

  function checkUser() {
    getUser().then((user) => {
      console.log(user)
      if (user === null) {
        navigate("/no-authentication");
      } 
    });
  }

  return (
    <>
      <NavBar />
      <List />
    </>
  );
}
