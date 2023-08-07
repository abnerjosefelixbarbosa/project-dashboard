import { useEffect, useState } from "react";
import "./index.css";
import { useNavigate } from "react-router-dom";
import { getUser as serviceGetUser } from "../../service/auth";
import { User } from "@supabase/supabase-js";
import { NavBar } from "../../components/Navbar";
import { List } from "./List";

export function ListProjects() {
  const [user, setUser] = useState<User>();
  const navigate = useNavigate();

  useEffect(() => {
    serviceGetUser().then((data) => {
      if (data) {
        setUser(data);
      } else {
        navigate("/no-authentication");
      }
    });
  }, [setUser]);

  return (
    <>
      <NavBar />
      <List />
    </>
  );
}
