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
    checkUser();
  }, [setUser]);

  function checkUser() {
    serviceGetUser().then((data) => {
      if (data) {
        setUser(data!);
      } else {
        navigate("/no-authentication");
      }
    });
  }

  return (
    <>
      <NavBar userName={user?.user_metadata.name} />
      <List  userId={user?.id} />
    </>
  );
}
