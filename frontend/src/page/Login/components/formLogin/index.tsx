import { Auth } from "@supabase/auth-ui-react";
import { supabase } from "../../../../service/subabase";
import { ThemeSupa } from "@supabase/auth-ui-shared";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import { isAuthenticated } from "../../../../service/auth";

export function FormLogin() {
  const navigate = useNavigate();

  useEffect(() => {
    isAuthenticated().then((value) => {
      if (value) {
        navigate("/project/list", {
          replace: true,
        });
      }
    });
  });

  return (
    <>
      <Auth
        supabaseClient={supabase}
        appearance={{ theme: ThemeSupa }}
        providers={["github"]}
      />
    </>
  );
}
