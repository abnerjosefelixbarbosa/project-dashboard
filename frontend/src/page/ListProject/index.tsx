import "./index.css";
import { NavBar } from "../../components/Navbar";
import { List } from "./List";
import { useLocation, useNavigate } from "react-router-dom";
import { useEffect } from "react";

export function ListProjects() {
  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    const token = location.state.token;
    const accountId = location.state.accountId;
    if (location.state === undefined) {
      navigate("/no-authentication", {
        replace: true,
      });
    }
    console.log(token,accountId);
  }, []);

  return (
    <>
      <NavBar />
      <List />
    </>
  );
}
