import { useEffect, useState } from "react";
import { NavBar } from "../../components/Navbar";
import { useLocation, useNavigate } from "react-router-dom";
import { User } from "@supabase/supabase-js";
import "./index.css";
import { FromSave } from "./FormSave";

export function SaveProject() {
  const [user, setUser] = useState<User>();
  const navigate = useNavigate();
  const location = useLocation()

  useEffect(() => {
    checkUser();
  }, [user]);

  function checkUser() {
    
  }
  
  return (
    <>
      <NavBar />
      <FromSave />
    </>
  );
}