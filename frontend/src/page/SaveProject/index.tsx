import { useEffect, useState } from "react";
import { NavBar } from "../../components/Navbar";
import { useNavigate } from "react-router-dom";
import { User } from "@supabase/supabase-js";
import { getUser as serviceGetUser } from "../../service/auth";
import "./index.css";
import { FromSave } from "./FormSave";

export function SaveProject() {
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
        navigate("/no-authentication", {
          replace: true,
        });
      }
    });
  }
  
  return (
    <>
      <NavBar userName={user?.user_metadata.name} />
      <FromSave />
    </>
  );
}