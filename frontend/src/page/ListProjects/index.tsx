import { useEffect, useState } from "react";
import "./index.css";
import { useNavigate } from "react-router-dom";
import { getUser, signOut } from "../../service/auth";
import { User } from "@supabase/supabase-js";
import { NavBar } from "../../components/Navbar";

export function ListProjects() {
  const [user, setUser] = useState<User>();
  const navigate = useNavigate();

  useEffect(() => {
    getUser().then((data) => {
      if (data) {
        setUser(data!);
      } else {
        navigate("/no-authentication")
      }
    })
  }, [setUser]);

  function SignOut() {
    signOut().then(() => {
      navigate("/", {
        replace: true,
      });
    });
  }

  return (
    <>
      <NavBar userName={user?.user_metadata.name} />
      <h1>list products</h1>
      <button onClick={SignOut}>Sign out</button>
    </>
  );
}
