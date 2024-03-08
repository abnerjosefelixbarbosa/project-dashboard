import "./index.css";
import { NavBar } from "../../components/Navbar";
import { List } from "./List";
import { useSearchParams } from "react-router-dom";
import { useEffect } from "react";

export function ListProjects() {
  const [params] = useSearchParams();
  const token = params.get('token');
  const accountId = params.get('accountId');

  useEffect(() => {
    //console.log(token);
    //console.log(accountId);
  }, []);

  return (
    <>
      <NavBar />
      <List />
    </>
  );
}
