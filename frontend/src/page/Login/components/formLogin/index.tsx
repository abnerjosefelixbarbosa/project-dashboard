import { Auth } from "@supabase/auth-ui-react";
import { ThemeSupa } from "@supabase/auth-ui-shared";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import { authentication } from "../../../../service/auth";
import { supabase } from "../../../../service/subabase";

export function FormLogin() {
  const navigate = useNavigate();

  useEffect(() => {
    authentication().then((value) => {
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
