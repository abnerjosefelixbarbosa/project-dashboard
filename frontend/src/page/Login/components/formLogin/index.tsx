import { Auth } from "@supabase/auth-ui-react";
import { ThemeSupa } from "@supabase/auth-ui-shared";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import { authentication as serviceAuthentication } from "../../../../service/auth";
import { supabase } from "../../../../service/subabase";
import { Container, Row } from "react-bootstrap";

export function FormLogin() {
  const navigate = useNavigate();

  useEffect(() => {
    authentication();
  });

  function authentication() {
    serviceAuthentication().then((value) => {
      if (value) {
        navigate("/project/list", {
          replace: true,
        });
      }
    });
  }

  return (
    <>
      <Container className="container_form">
        <Row>
          <Auth
            supabaseClient={supabase}
            appearance={{ theme: ThemeSupa }}
            providers={["github"]}
          />
        </Row>
      </Container>
    </>
  );
}
